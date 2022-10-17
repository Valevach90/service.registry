package com.andersen.banking.meeting_impl.controller;

import static com.andersen.banking.meeting_test.generators.CardUnitTestGenerator.populateTypeCard;
import static com.andersen.banking.meeting_test.generators.CardUnitTestGenerator.populateTypeCardResponseDto;
import static com.andersen.banking.meeting_test.generators.CardUnitTestGenerator.populateTypeCardUpdateDto;

import com.andersen.banking.meeting_api.dto.CardResponseDto;
import com.andersen.banking.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.meeting_api.dto.TypeCardUpdateDto;
import com.andersen.banking.meeting_db.entities.Account;
import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_db.entities.TypeCard;
import com.andersen.banking.meeting_db.repository.AccountRepository;
import com.andersen.banking.meeting_db.repository.CardRepository;
import com.andersen.banking.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.meeting_impl.controller.util.RestResponsePage;
import com.andersen.banking.meeting_impl.mapper.CardMapper;
import com.andersen.banking.meeting_impl.util.Crypter;
import com.andersen.banking.meeting_test.generators.AccountUnitTestGenerator;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CardControllerH2IntegrationTest {

    private final UUID TYPE_CARD_ID_1 = UUID.randomUUID();

    private final UUID TYPE_CARD_ID_2 = UUID.randomUUID();

    private final UUID TYPE_CARD_ID_3 = UUID.randomUUID();
    private final UUID OWNER_ID = UUID.randomUUID();
    private Account account1;
    private Account account2;
    private Account account3;
    private List<Card> cards;
    private final TypeCard typeCard = populateTypeCard();
    private final TypeCardResponseDto typeCardResponseDto = populateTypeCardResponseDto();
    private final TypeCardUpdateDto typeCardUpdateDto = populateTypeCardUpdateDto();

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TypeCardRepository typeCardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    CardMapper cardMapper;

    private static RestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {

        typeCardRepository.save(createTypeCard("VISAS"));
        typeCardRepository.save(createTypeCard("MASTERCARDS"));
        typeCardRepository.save(createTypeCard("MASTERCARDSPLAT"));

        Account acc1 = AccountUnitTestGenerator.populateAccount(new Account());
        acc1.setOwnerId(OWNER_ID);
        account1 = accountRepository.save(acc1);

        Account acc2 = AccountUnitTestGenerator.populateAccount(new Account());
        acc2.setOwnerId(OWNER_ID);
        account2 = accountRepository.save(acc2);

        Account acc3 = AccountUnitTestGenerator.populateAccount(new Account());
        acc3.setOwnerId(UUID.randomUUID());
        account3 = accountRepository.save(acc3);

        cards = generateCards();
        cardRepository.saveAll(cards);

        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/v1/cards");
    }

    @Test
    void findTypeCardById_AndOk() {
        final UUID typeCardId = UUID.randomUUID();

        TypeCardResponseDto response = restTemplate
                .getForObject(baseUrl + "/types/{id}", TypeCardResponseDto.class, typeCardId);

        Assertions.assertEquals(typeCardResponseDto, response);
        Assertions.assertEquals(3, typeCardRepository.findAll().size());
    }

    //fix with authorization
    @Disabled
    void updateTypeCard_andOk() {
        final UUID typeCardId = UUID.randomUUID();

        restTemplate.put(baseUrl + "/types/{id}", typeCardId, typeCardUpdateDto);

        TypeCard response = typeCardRepository.findById(typeCardId).get();

        Assertions.assertEquals(typeCard, response);
        Assertions.assertEquals(3, typeCardRepository.findAll().size());
    }

    @Test
    void findAllByTypeCardWithType_ShouldReturnSizeOfCards() {
        String checkType = "SILVER";

        int result = getSizeFromRepository(checkType, null);

        List<Card> expected = generateCards()
                .stream()
                .filter(c -> c.getCardProduct().getTypeCard().getTypeName().equals(checkType))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), result);
        Assertions.assertEquals(5, result);
    }

    @Test
    void findAllByTypeCardWithTypeAndPayment_ShouldReturnSizeOfCards() {
        String checkType = "SILVER";
        String checkPayment = "MASTERCARD";

        int result = getSizeFromRepository(checkType, checkPayment);

        List<Card> expected = generateCards()
                .stream()
                .filter(c -> c.getCardProduct().getTypeCard().getTypeName().equals(checkType))
                .filter(c -> c.getCardProduct().getTypeCard().getPaymentSystem().equals(checkPayment))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), result);
        Assertions.assertEquals(2, result);
    }

    @Test
    void findAllByTypeCardWithoutTypeAndPayment_ShouldReturnSizeOfCards() {
        int result = getSizeFromRepository(null, null);

        List<Card> expected = generateCards();

        Assertions.assertEquals(expected.size(), result);
        Assertions.assertEquals(8, result);
    }

    @Test
    void findAllByOwner_ShouldReturnSizeOfCards() {

        Page<Card> cardByAccount_ownerId =
                cardRepository.findCardByAccount_OwnerId(OWNER_ID, Pageable.unpaged());
        Assertions.assertEquals(5, cardByAccount_ownerId.getSize());
    }

    @Test
    void findByOwnerIdAndAccountIdIsNot_ShouldReturnSizeOfCards() {
        Page<Card> cardByAccount_ownerId = cardRepository.findByAccount_OwnerIdAndAccount_IdNot(OWNER_ID, TYPE_CARD_ID_2, Pageable.unpaged());
        Assertions.assertEquals(2, cardByAccount_ownerId.getSize());
    }

    private int getSizeFromRepository(String type, String payment) {
        baseUrl = baseUrl.concat("/search/");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl);

        if (type != null) {
            uriComponentsBuilder.queryParam("type", type);
        }
        if (payment != null) {
            uriComponentsBuilder.queryParam("payment", payment);
        }
        URI uri = uriComponentsBuilder.build().toUri();
        ParameterizedTypeReference<RestResponsePage<CardResponseDto>> typeRef = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<RestResponsePage<CardResponseDto>> response = restTemplate.exchange(
                uri, HttpMethod.GET, null, typeRef);
        return response.getBody().getContent().size();
    }

    private List<Card> generateCards() {
        List<Card> collect = Stream.generate(Card::new)
                .limit(2)
                .peek(c -> {
                    populateCard(c);
                    c.setAccount(account1);
                    c.getCardProduct().setTypeCard(createTypeCard("MASTERCARDS"));
                })
                .collect(Collectors.toList());
        List<Card> addOtherAccountAndType = Stream
                .generate(Card::new)
                .limit(3)
                .peek(c -> {
                    populateCard(c);
                    c.setAccount(account2);
                    c.getCardProduct().setTypeCard(createTypeCard("VISAS"));
                })
                .collect(Collectors.toList());
        List<Card> addOtherAccountAndTypeMore = Stream
                .generate(Card::new)
                .limit(3)
                .peek(c -> {
                    populateCard(c);
                    c.setAccount(account3);
                    c.getCardProduct().setTypeCard(createTypeCard("MASTERCARDSPLAT"));
                })
                .collect(Collectors.toList());

        collect.addAll(addOtherAccountAndType);
        collect.addAll(addOtherAccountAndTypeMore);
        return collect;
    }

    private TypeCard createTypeCard(String type) {
        TypeCard typeCard = new TypeCard();
        switch (type) {
            case "VISAS": {
                typeCard.setId(TYPE_CARD_ID_1);
                typeCard.setPaymentSystem("VISA");
                typeCard.setTypeName("SILVER");
                break;
            }
            case "MASTERCARDS": {
                typeCard.setId(TYPE_CARD_ID_2);
                typeCard.setPaymentSystem("MASTERCARD");
                typeCard.setTypeName("SILVER");
                break;
            }
            case "MASTERCARDSPLAT": {
                typeCard.setId(TYPE_CARD_ID_3);
                typeCard.setPaymentSystem("MASTERCARD");
                typeCard.setTypeName("PLATINUM");
                break;
            }
        }
        return typeCard;
    }

    private void populateCard(Card card) {
        card.setValidFromDate(LocalDate.of(2021, 10, 23));
        card.setExpireDate(LocalDate.of(2024, 10, 23));
        card.setLastFourNumbers("1234");
        card.setHolderName("Ivanov Ivan Ivanovich");
        String firstTwelveNums = "123456789012";
        card.setFirstTwelveNumbers(Crypter.getCrypt(firstTwelveNums));
    }
}
