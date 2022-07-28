package com.andersen.banking.deposit_impl.controller;

import com.andersen.banking.deposit_db.entities.Currency;
import com.andersen.banking.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit_db.entities.DepositType;
import com.andersen.banking.deposit_db.repositories.CurrencyRepository;
import com.andersen.banking.deposit_db.repositories.DepositProductRepository;
import com.andersen.banking.deposit_db.repositories.DepositTypeRepository;
import com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator;
import com.andersen.banking.deposit_impl.service.DepositProductService;
import com.andersen.banking.deposit_impl.testcontainer.IntegrationTestWithPostgres;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.createCustomPageable;
import static com.andersen.banking.deposit_impl.generators.DepositServiceTestEntitiesGenerator.possibleCurrencyNames;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@IntegrationTestWithPostgres
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DepositProductControllerIntegrationTest {

    private static final int CUSTOM_PAGE_SIZE = 150;
    private static final int CUSTOM_PAGE_NUMBER = 0;
    public static final int LIMIT_FOR_INSERTABLE_ELEMENTS = 100;
    @Autowired
    private DepositProductService depositProductService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private DepositControllerImpl depositController;

    private final Random rand = new Random();
    private static final List<String> prodNames = List.of("Name1", "Name2", "Name3", "Name4");



    @BeforeAll
    static void add(
            @Autowired DepositProductRepository depositProductRepository,
            @Autowired DepositTypeRepository depositTypeRepository,
            @Autowired CurrencyRepository currencyRepository) {

        Random random = new Random();

        DepositType depositType = new DepositType();
        depositType.setName("default deposit type");

        List<Currency> generatedCurrencies = Stream
                .generate(DepositServiceTestEntitiesGenerator::generateRandomCurrency)
                .limit(5)
                .toList();

        final DepositType saved = depositTypeRepository.save(depositType);


        Stream.generate(DepositServiceTestEntitiesGenerator::generateSemiEmptyDepositProduct)
                .limit(LIMIT_FOR_INSERTABLE_ELEMENTS)
                .forEach(x -> {
                    x.setDepositName(prodNames.get(random.nextInt(prodNames.size())));
                    x.setType(saved);
                    x.setCurrency(generatedCurrencies.get(random.nextInt(generatedCurrencies.size())));
                    currencyRepository.save(x.getCurrency());
                    depositProductRepository.save(x);
                });
    }

    @AfterAll
    static void clear(@Autowired DepositProductRepository depositProductRepository,
                      @Autowired DepositTypeRepository depositTypeRepository,
                      @Autowired CurrencyRepository currencyRepository) {
        depositProductRepository.deleteAll();
        depositTypeRepository.deleteAll();
        currencyRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertThat(depositController).isNotNull();
    }



    @Test
    void whenSearchByCurrency_andOk() throws Exception {
        List<DepositProduct> allProducts = depositProductService
                .findAll(createCustomPageable(CUSTOM_PAGE_NUMBER, CUSTOM_PAGE_SIZE))
                .getContent();

        String currency = possibleCurrencyNames.get(rand.nextInt(possibleCurrencyNames.size()));

        long numberWithCurrencyRUB = allProducts.stream()
                .filter(x -> x.getCurrency().getName().equals(currency))
                .count();
        mvc
                .perform(get("/api/v1/products/search?page=0&size=10&currency=%s".formatted(currency))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalElements").value(numberWithCurrencyRUB))
                .andDo(print());
    }

    @Test
    void whenSearchWithNoParameters_andOk() throws Exception {
        long totalProductsNumber = depositProductService
                .findAll(createCustomPageable(CUSTOM_PAGE_NUMBER, CUSTOM_PAGE_SIZE))
                .getTotalElements();

        mvc
                .perform(get("/api/v1/products/search?page=0&size=10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalElements").value(totalProductsNumber))
                .andDo(print());
    }

    @Test
    void whenSearchWithDepositProductName_andOk() throws Exception {
        List<DepositProduct> products = depositProductService
                .findAll(createCustomPageable(CUSTOM_PAGE_NUMBER, CUSTOM_PAGE_SIZE))
                .getContent();

        String searchableName = prodNames.get(rand.nextInt(prodNames.size()));

        long numberOfProductsWithName = products.stream()
                .filter(x -> x.getDepositName().equals(searchableName))
                .count();

        mvc
                .perform(get("/api/v1/products/search?page=0&size=10&prodName=" + searchableName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalElements").value(numberOfProductsWithName))
                .andDo(print());
    }

    @Test
    void whenSearchWithBothParameters_andOk() throws Exception {
        List<DepositProduct> products = depositProductService
                .findAll(createCustomPageable(CUSTOM_PAGE_NUMBER, CUSTOM_PAGE_SIZE))
                .getContent();

        String searchableName = products.stream()
                .findAny()
                .orElseGet(DepositServiceTestEntitiesGenerator::generateDepositProduct)
                .getDepositName();

        String currency = "EU";

        long numberOfProductsWithNameAndCurrencyEU = products.stream()
                .filter(x -> x.getDepositName().equals(searchableName))
                .filter(x -> x.getCurrency().getName().equals(currency))
                .count();

        mvc
                .perform(get("/api/v1/products/search?page=0&size=10&prodName=%s&currency=%s".formatted(searchableName, currency))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalElements").value(numberOfProductsWithNameAndCurrencyEU))
                .andDo(print());
    }

}
