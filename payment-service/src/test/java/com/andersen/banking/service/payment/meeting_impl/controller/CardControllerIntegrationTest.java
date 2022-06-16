package com.andersen.banking.service.payment.meeting_impl.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.andersen.banking.service.payment.meeting_api.controller.CardController;
import com.andersen.banking.service.payment.meeting_db.entities.Account;
import com.andersen.banking.service.payment.meeting_db.entities.Card;
import com.andersen.banking.service.payment.meeting_db.repositories.CardRepository;
import com.andersen.banking.service.payment.meeting_test.generators.AccountGenerator;
import com.andersen.banking.service.payment.meeting_test.generators.CardGenerator;
import com.andersen.banking.service.payment.testcontainer.IntegrationTestWithPostgres;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@IntegrationTestWithPostgres
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CardControllerIntegrationTest {

  private static List<Card> cards;

  private static final String URL_CARDS = "/api/v1/cards/";
  private static final String PARAM_ID = "$.id";
  private static final String PARAM_CARD_NUMBER = "$.card_number";
  private static final String PARAM_PIN_CODE = "$.pin_code";
  private static final String PARAM_EXP_DATE = "$.exp_date";
  private static final String PARAM_HOLDER_NAME = "$.holder_name";
  private static final String PARAM_NUMBER = "number";
  private static final String PARAM_SIZE = "size";
  private static final String PARAM_PAGE = "page";
  private static final String PARAM_TOTAL_ELEMENTS = "totalElements";
  private static final String PARAM_TOTAL_PAGES = "totalPages";
  private static final String PARAM_FIRST = "first";
  private static final String PARAM_LAST = "last";
  private static final String PARAM_CONTENT = "$.content";

  @Autowired
  private CardController cardController;
  @Autowired
  private MockMvc mockMvc;

  @Value("application/json")
  private String contentType;

  private final Random random = new Random();

  @BeforeAll
  static void add(
      @Autowired CardRepository cardRepository,
      @Autowired AccountGenerator accountGenerator,
      @Autowired CardGenerator cardGenerator) {

    cards = Stream.generate(() -> {
          Account account = accountGenerator.generateAccount();
          return cardGenerator.generateCard(account);
        })
        .limit(100)
        .toList();
    cards.forEach(cardRepository::save);
  }

  @AfterAll
  static void clear(
      @Autowired CardRepository cardRepository) {
    cardRepository.deleteAll();
  }

  @Test
  void contextLoads() {
    assertThat(cardController).isNotNull();
  }

  @Test
  void findById_ShouldWorkProperly() throws Exception {
    Card card = cards.get(random.nextInt(0, cards.size()));

    mockMvc.perform(
            get(URL_CARDS + card.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath(PARAM_ID).value(card.getId()))
        .andExpect(jsonPath(PARAM_CARD_NUMBER).value(card.getCardNumber()))
        .andExpect(jsonPath(PARAM_PIN_CODE).value(card.getPinCode()))
        .andExpect(jsonPath(PARAM_EXP_DATE).value(card.getExpirationDate().toString()))
        .andExpect(jsonPath(PARAM_HOLDER_NAME).value(card.getHolderName()))
    ;
  }

  @Test
  void findAll_ShouldWorkProperly() throws Exception {
    Integer pageNumber = 0;
    Integer pageSize = 10;

    mockMvc.perform(
            get(URL_CARDS)
                .param(PARAM_PAGE, String.valueOf(pageNumber))
                .param(PARAM_SIZE, String.valueOf(pageSize))
        ).andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath(PARAM_NUMBER).value(pageNumber))
        .andExpect(jsonPath(PARAM_SIZE).value(pageSize))
        .andExpect(jsonPath(PARAM_TOTAL_ELEMENTS).value(cards.size()))
        .andExpect(jsonPath(PARAM_TOTAL_PAGES).value(
            cards.size() % pageSize == 0 ? cards.size() / pageSize : cards.size() / pageSize + 1))
        .andExpect(jsonPath(PARAM_FIRST).value(pageNumber == 0))
        .andExpect(jsonPath(PARAM_LAST).value(cards.size() / pageSize == pageNumber))
        .andExpect(jsonPath(PARAM_CONTENT).isArray())
        .andExpect(jsonPath(PARAM_CONTENT, hasSize(pageSize)));
  }
}
