package com.andersen.banking.deposit.service.deposit_impl.controller;

import com.andersen.banking.deposit.service.deposit_api.dto.DepositProductDto;
import com.andersen.banking.deposit.service.deposit_db.entities.DepositProduct;
import com.andersen.banking.deposit.service.deposit_db.repositories.DepositProductRepository;
import com.andersen.banking.deposit.service.deposit_impl.generators.CustomPageImpl;
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

import java.util.ArrayList;
import java.util.List;

import static com.andersen.banking.deposit.service.deposit_impl.generators.DepositServiceTestEntitiesGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepositProductControllerH2IntegrationTest {

    private DepositProduct product;
    private DepositProductDto productDto;
    private Long id;

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @Autowired
    private DepositProductRepository productRepository;

    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        product = generateDepositProduct();
        productDto = generateDepositProductDto(product);
        id = product.getId();

        baseUrl = baseUrl.concat(":").concat(port+ "").concat("/api/v1/products");
    }

    @Test
    void create_whenOk_shouldReturnSavedDepositProductDto(){
        DepositProductDto response = restTemplate.postForObject(baseUrl, productDto, DepositProductDto.class);

        assertEquals(productDto, response);
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void create_whenDtoIsIncorrect_shouldThrowException(){
        DepositProductDto emptyProductDto = new DepositProductDto();

        assertThrows(Exception.class, () -> restTemplate.postForObject(baseUrl, emptyProductDto, DepositProductDto.class));
    }

    @Test
    void findById_whenOk_shouldReturnFoundDepositProductDto(){
        productRepository.save(product);

        DepositProductDto response = restTemplate.getForObject(baseUrl + "/{id}", DepositProductDto.class, id);

        assertEquals(productDto, response);
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void findById_whenNotFound_shouldThrowException(){
        assertEquals(0, productRepository.findAll().size());

        assertThrows(Exception.class, () -> restTemplate.getForObject(baseUrl + "/{id}", DepositProductDto.class, id));
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
        productRepository.save(product);
        product.setDepositName("SecondName");

        restTemplate.put(baseUrl, product);

        DepositProduct response = productRepository.findById(1L).get();

        assertEquals(product, response);
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    void update_whenNotFound_shouldThrowException(){
        assertEquals(0, productRepository.findAll().size());

        assertThrows(Exception.class, () -> restTemplate.put(baseUrl, product));
    }

    @Test
    void delete_whenOk(){
        assertEquals(0, productRepository.findAll().size());

        productRepository.save(product);

        assertEquals(1, productRepository.findAll().size());

        restTemplate.delete(baseUrl + "/{id}", id);

        assertEquals(0, productRepository.findAll().size());
    }

    @Test
    void delete_whenNotFound_shouldThrowException(){
        assertEquals(0, productRepository.findAll().size());

        assertThrows(Exception.class, () -> restTemplate.delete(baseUrl + "/{id}", id));
    }
}
