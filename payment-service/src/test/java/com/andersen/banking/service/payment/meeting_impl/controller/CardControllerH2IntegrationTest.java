package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.dto.CardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.TypeCardResponseDto;
import com.andersen.banking.service.payment.meeting_api.dto.TypeCardUpdateDto;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.entities.TypeCard;
import com.andersen.banking.service.payment.meeting_db.repository.AccountRepository;
import com.andersen.banking.service.payment.meeting_db.repository.CardRepository;
import com.andersen.banking.service.payment.meeting_db.repository.TypeCardRepository;
import com.andersen.banking.service.payment.meeting_impl.controller.util.RestResponsePage;
import com.andersen.banking.service.payment.meeting_impl.mapper.CardMapper;
import com.andersen.banking.service.payment.meeting_impl.util.CryptWithSHA;
import com.andersen.banking.service.payment.meeting_test.generators.AccountUnitTestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

import static com.andersen.banking.service.payment.meeting_test.generators.CardUnitTestGenerator.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CardControllerH2IntegrationTest {
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
        acc1.setOwnerId(1L);
        account1 = accountRepository.save(acc1);

        Account acc2 = AccountUnitTestGenerator.populateAccount(new Account());
        acc2.setOwnerId(1L);
        account2 = accountRepository.save(acc2);

        Account acc3 = AccountUnitTestGenerator.populateAccount(new Account());
        acc3.setOwnerId(2L);
        account3 = accountRepository.save(acc3);

        cards = generateCards();
    }

    @Test
    void findTypeCardById_AndOk() {
        final Long typeCardId = 1L;

        TypeCardResponseDto response = restTemplate
                .getForObject(baseUrl + "/types/{id}", TypeCardResponseDto.class, typeCardId);

        Assertions.assertEquals(typeCardResponseDto, response);
        Assertions.assertEquals(3, typeCardRepository.findAll().size());
    }

    @Test
    void updateTypeCard_andOk() {
        final Long typeCardId = 1L;

        restTemplate.put(baseUrl + "/types/{id}", typeCardId, typeCardUpdateDto);

        TypeCard response = typeCardRepository.findById(typeCardId).get();

        Assertions.assertEquals(typeCard, response);
        Assertions.assertEquals(3, typeCardRepository.findAll().size());
    }

    @Test
    void findAllByTypeCardWithType_ShouldReturnSizeOfCards() {
        String checkType = "STANDARD";

        int result = getSizeFromRepository(checkType, null);

        List<CardResponseDto> expected = generateCards()
                .stream()
                .filter(c -> c.getTypeCard().getTypeName().equals(checkType))
                .map(c -> cardMapper.toCardResponseDto(c))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), result);
    }

    @Test
    void findAllByTypeCardWithTypeAndPayment_ShouldReturnSizeOfCards() {
        String checkType = "STANDARD";
        String checkPayment = "VISA";

        int result = getSizeFromRepository(checkType, checkPayment);

        List<CardResponseDto> expected = generateCards()
                .stream()
                .filter(c -> c.getTypeCard().getTypeName().equals(checkType))
                .filter(c -> c.getTypeCard().getPaymentSystem().equals(checkPayment))
                .map(c -> cardMapper.toCardResponseDto(c))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), result);
    }

    @Test
    void findAllByTypeCardWithoutTypeAndPayment_ShouldReturnSizeOfCards() {
        int result = getSizeFromRepository(null, null);

        List<CardResponseDto> expected = generateCards()
                .stream()
                .map(c -> cardMapper.toCardResponseDto(c))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), result);
    }

    @Test
    void findAllByOwner_ShouldReturnSizeOfCards() {
        Page<Card> cardByAccount_ownerId = cardRepository.findCardByAccount_OwnerId(1L, Pageable.unpaged());
        Assertions.assertEquals(5, cardByAccount_ownerId.getSize());
    }

    @Test
    void findByOwnerIdAndAccountIdIsNot_ShouldReturnSizeOfCards() {
        Page<Card> cardByAccount_ownerId = cardRepository.findByAccount_OwnerIdAndAccount_IdNot(1L, 2L, Pageable.unpaged());
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
                    c.setTypeCard(createTypeCard("MASTERCARDS"));
                })
                .collect(Collectors.toList());
        List<Card> addOtherAccountAndType = Stream
                .generate(Card::new)
                .limit(3)
                .peek(c -> {
                    populateCard(c);
                    c.setAccount(account2);
                    c.setTypeCard(createTypeCard("VISAS"));
                })
                .collect(Collectors.toList());
        List<Card> addOtherAccountAndTypeMore = Stream
                .generate(Card::new)
                .limit(3)
                .peek(c -> {
                    populateCard(c);
                    c.setAccount(account3);
                    c.setTypeCard(createTypeCard("MASTERCARDSPLAT"));
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
                typeCard.setId(1L);
                typeCard.setPaymentSystem("VISA");
                typeCard.setTypeName("SILVER");
                break;
            }
            case "MASTERCARDS": {
                typeCard.setId(2L);
                typeCard.setPaymentSystem("MASTERCARD");
                typeCard.setTypeName("SILVER");
                break;
            }
            case "MASTERCARDSPLAT": {
                typeCard.setId(3L);
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
        card.setFirstTwelveNumbers(CryptWithSHA.getCrypt(firstTwelveNums));
    }
}
