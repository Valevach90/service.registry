package com.andersen.banking.service.payment.meeting_impl.controller;

import com.andersen.banking.service.payment.meeting_api.dto.CardResponseDto;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CardControllerH2IntegrationTest {
    private Account account;
    private List<Card> cards;

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
        account = AccountUnitTestGenerator.populateAccount(new Account());
        account = accountRepository.save(account);
        cards = generateCardsWithTypeCard();

        for (Card card : cards) {
            cardRepository.save(card);
        }

        baseUrl = baseUrl.concat(":").concat(String.valueOf(port)).concat("/api/v1/cards");
    }

    @Test
    void findAllByTypeCardWithType_ShouldReturnSizeOfCards() {
        String checkType = "STANDARD";

        int result = getSizeFromRepository(checkType, null);

        List<CardResponseDto> expected = generateCardsWithTypeCard()
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

        List<CardResponseDto> expected = generateCardsWithTypeCard()
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

        List<CardResponseDto> expected = generateCardsWithTypeCard()
                .stream()
                .map(c -> cardMapper.toCardResponseDto(c))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), result);
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
        ParameterizedTypeReference<RestResponsePage<CardResponseDto>> typeRef = new ParameterizedTypeReference<>() {};
        ResponseEntity<RestResponsePage<CardResponseDto>> response = restTemplate.exchange(
                uri, HttpMethod.GET, null, typeRef);
        return response.getBody().getContent().size();
    }

    private List<Card> generateCardsWithTypeCard() {
        List<Card> collect = Stream.generate(Card::new)
                .limit(2)
                .peek(c -> {
                    populateCard(c);
                    c.setTypeCard(createTypeCard("MASTERCARDS"));
                })
                .collect(Collectors.toList());
        List<Card> addOtherType = Stream
                .generate(Card::new)
                .limit(2)
                .peek(c -> {
                    populateCard(c);
                    c.setTypeCard(createTypeCard("VISAS"));
                })
                .collect(Collectors.toList());
        List<Card> addOtherTypeMore = Stream
                .generate(Card::new)
                .limit(2)
                .peek(c -> {
                    populateCard(c);
                    c.setTypeCard(createTypeCard("MASTERCARDSPLAT"));
                })
                .collect(Collectors.toList());

        collect.addAll(addOtherType);
        collect.addAll(addOtherTypeMore);
        return collect;
    }

    private TypeCard createTypeCard(String type) {
        TypeCard typeCard = new TypeCard();
        switch (type) {
            case "VISAS": {
                typeCard.setId(1L);
                typeCard.setPaymentSystem("VISA");
                typeCard.setTypeName("STANDARD");
                break;
            }
            case "MASTERCARDS": {
                typeCard.setId(2L);
                typeCard.setPaymentSystem("MASTERCARD");
                typeCard.setTypeName("STANDARD");
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
        card.setAccount(accountRepository.findById(account.getId()).get());
        card.setValidFromDate(LocalDate.of(2021, 10, 23));
        card.setExpireDate(LocalDate.of(2024, 10, 23));
        card.setLastFourNumbers("1234");
        card.setHolderName("Ivanov Ivan Ivanovich");
        String firstTwelveNums = "123456789012";
        card.setFirstTwelveNumbers(CryptWithSHA.getCrypt(firstTwelveNums));
    }
}
