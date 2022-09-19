package com.andersen.banking.meeting_impl.controller;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateCurrency;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositProduct;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositProductDto;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.andersen.banking.meeting_api.dto.DepositProductDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_db.repositories.CurrencyRepository;
import com.andersen.banking.meeting_db.repositories.DepositProductRepository;
import com.andersen.banking.meeting_db.repositories.DepositTypeRepository;
import com.andersen.banking.meeting_impl.generators.CustomPageImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepositProductControllerH2IntegrationTest {

    private DepositProduct product;
    private DepositProductDto productDto;
    private UUID id;

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @Autowired
    private DepositProductRepository productRepository;

    @Autowired
    private DepositTypeRepository depositTypeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        depositTypeRepository.save(generateDepositType());
        currencyRepository.save(generateCurrency());

        product = productRepository.save(generateDepositProduct());
        productDto = generateDepositProductDto(product);
        id = product.getId();

        baseUrl = baseUrl.concat(":").concat(port+ "").concat("/api/v1/products");
    }

    @Test
    void create_whenOk_shouldReturnSavedDepositProductDto(){
        DepositProductDto response = restTemplate.postForObject(baseUrl, productDto, DepositProductDto.class);
        productDto.setId(response.getId());

        assertEquals(productDto, response);
        assertEquals(2, productRepository.findAll().size());
    }

    @Test
    void create_whenDtoIsIncorrect_shouldThrowException(){
        DepositProductDto emptyProductDto = new DepositProductDto();

        assertThrows(Exception.class, () -> restTemplate.postForObject(baseUrl, emptyProductDto, DepositProductDto.class));
    }

    @Test
    void findById_whenOk_shouldReturnFoundDepositProductDto() {
        UUID id = productRepository.save(product).getId();

        DepositProductDto response = restTemplate.getForObject(baseUrl + "/{id}", DepositProductDto.class, id);

        assertEquals(productDto, response);
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void findById_whenNotFound_shouldThrowException(){
        assertEquals(1, productRepository.findAll().size());

        assertThrows(Exception.class, () -> restTemplate.getForObject(baseUrl + "/{id}", DepositProductDto.class, UUID.randomUUID()));
    }

    @Test
    void findAll_whenOk_shouldReturnPageOfDepositProductDto(){
        productRepository.save(product);
        List<DepositProductDto> products = new ArrayList<>();
        products.add(productDto);

        ResponseEntity<CustomPageImpl<DepositProductDto>> exchange = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<CustomPageImpl<DepositProductDto>>() {
                });

        assertEquals(products, exchange.getBody().getContent());
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void update_whenOk(){
        product.setDepositName("SecondName");
        productDto.setDepositName("SecondName");

        restTemplate.put(baseUrl, productDto);

        DepositProduct response = productRepository.findById(product.getId()).get();

        assertEquals(product, response);
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void update_whenNotFound_shouldThrowException(){
        assertEquals(1, productRepository.findAll().size());

        assertThrows(Exception.class, () -> restTemplate.put(baseUrl, product));
    }

    @Test
    void delete_whenOk(){
        assertEquals(1, productRepository.findAll().size());

        restTemplate.delete(baseUrl + "/{id}", id);

        assertEquals(0, productRepository.findAll().size());
    }

    @Test
    void delete_whenNotFound_shouldThrowException(){
        assertEquals(1, productRepository.findAll().size());

        assertThrows(Exception.class, () -> restTemplate.delete(baseUrl + "/{id}", UUID.randomUUID()));
    }
}
