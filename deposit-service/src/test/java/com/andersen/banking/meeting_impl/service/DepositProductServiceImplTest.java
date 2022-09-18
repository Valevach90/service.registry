package com.andersen.banking.meeting_impl.service;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.createPageable;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositProduct;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generatePageOfDepositProducts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_db.repositories.DepositProductRepository;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.service.impl.DepositProductServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@SpringBootTest(classes = DepositProductServiceImpl.class)
public class DepositProductServiceImplTest {

    private DepositProduct product;
    private UUID id;
    private Optional<DepositProduct> productOptional;

    @SpyBean
    DepositProductService depositProductService;

    @MockBean
    DepositProductRepository depositProductRepository;

    @BeforeEach
    void initialize(){
        product = generateDepositProduct();
        id = product.getId();
        productOptional = Optional.of(product);
    }

    @Test
    void create_whenOk_shouldReturnSavedDepositProduct(){
        Mockito
                .when(depositProductRepository.save(product))
                .thenReturn(product);

        DepositProduct result = depositProductService.create(product);

        assertEquals(product, result);
    }

    @Test
    void findById_whenOk_shouldReturnFoundDepositProduct(){
        Mockito
                .when(depositProductRepository.findById(id))
                .thenReturn(productOptional);

        Optional<DepositProduct> result = depositProductService.findById(id);

        assertEquals(productOptional, result);
    }

    @Test
    void findById_whenNotFound_shouldReturnEmptyOptional(){
        Optional<DepositProduct> empty = Optional.empty();
        Optional<DepositProduct> result = depositProductService.findById(id);

        assertEquals(empty, result);
    }

    @Test
    void findAll_whenOk_shouldReturnPageOfDepositProduct(){
        Pageable pageable = createPageable();
        Page<DepositProduct> pageOfProducts = generatePageOfDepositProducts(pageable);

        Mockito
                .when(depositProductRepository.findAll(pageable))
                .thenReturn(pageOfProducts);

        Page<DepositProduct> result = depositProductService.findAll(pageable);

        assertEquals(pageOfProducts, result);
    }

    @Test
    void update_whenOk(){
        Mockito
                .when(depositProductRepository.findById(id))
                .thenReturn(productOptional);

        depositProductService.update(product);

        Mockito
                .verify(depositProductRepository, Mockito.times(1))
                .save(product);
    }

    @Test
    void update_whenNotFound_shouldThrowNotFoundException(){

        Mockito
                .when(depositProductRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> depositProductService.update(product));
        Mockito
                .verify(depositProductRepository, Mockito.never())
                .save(product);
    }

    @Test
    void delete_whenOk(){
        Mockito
                .when(depositProductRepository.findById(id))
                .thenReturn(productOptional);

        depositProductService.deleteById(id);

        Mockito
                .verify(depositProductRepository, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    void delete_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .when(depositProductRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> depositProductService.deleteById(id));
        Mockito
                .verify(depositProductRepository, Mockito.never())
                .deleteById(id);
    }
}
