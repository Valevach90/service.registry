package com.andersen.banking.meeting_impl.controller;

import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.createPageable;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositProduct;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generateDepositProductDto;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generatePageOfDepositProducts;
import static com.andersen.banking.meeting_impl.generators.DepositServiceTestEntitiesGenerator.generatePageOfDepositProductsDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.andersen.banking.meeting_api.controller.DepositProductController;
import com.andersen.banking.meeting_api.dto.deposit_product.DepositProductResponseDto;
import com.andersen.banking.meeting_db.entities.DepositProduct;
import com.andersen.banking.meeting_impl.exceptions.MapperException;
import com.andersen.banking.meeting_impl.exceptions.NotFoundException;
import com.andersen.banking.meeting_impl.mapping.DepositProductMapper;
import com.andersen.banking.meeting_impl.service.DepositProductService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest(classes = DepositProductControllerImpl.class)
public class DepositProductControllerImplTest {

    private DepositProduct product;
    private UUID id;
    private Optional<DepositProduct> productOptional;
    private DepositProductResponseDto productDto;


    @Autowired
    DepositProductController depositProductController;
    @MockBean
    DepositProductService depositProductService;
    @MockBean
    DepositProductMapper depositProductMapper;

    @BeforeEach
    void initialize(){
        product = generateDepositProduct();
        id = product.getId();
        productOptional = Optional.of(product);
        productDto = generateDepositProductDto(product);
    }

    @Test
    void create_whenOk_shouldReturnSavedDepositProductDto(){
        Mockito
                .when(depositProductMapper.toDepositProduct(productDto))
                .thenReturn(product);
        Mockito
                .when(depositProductService.create(product))
                .thenReturn(product);
        Mockito
                .when(depositProductMapper.toDepositProductDto(product))
                .thenReturn(productDto);

        DepositProductResponseDto result = depositProductController.create(productDto);

        assertEquals(productDto, result);
    }

    @Test
    void create_whenDtoIsIncorrect_shouldThrowMapperException(){
        Mockito
                .when(depositProductMapper.toDepositProduct(productDto))
                .thenThrow(new MapperException());

        assertThrows(MapperException.class, () -> depositProductController.create(productDto));
    }

    @Test
    void findById_whenOk_shouldReturnFoundDepositProductDto(){
        Mockito
                .when(depositProductService.findById(id))
                .thenReturn(productOptional);
        Mockito
                .when(depositProductMapper.toDepositProductDto(productOptional.get()))
                .thenReturn(productDto);

        DepositProductResponseDto result = depositProductController.findById(id);

        assertEquals(productDto, result);
    }

    @Test
    void findById_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .when(depositProductService.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> depositProductController.findById(id));
        Mockito
                .verify(depositProductMapper, Mockito.never())
                .toDepositProductDto(Mockito.any(DepositProduct.class));
    }

    @Test
    void findAll_whenOk_shouldReturnPageOfDepositProductDto(){
        Pageable pageable = createPageable();
        Page<DepositProduct> pageOfProducts = generatePageOfDepositProducts(pageable);
        Page<DepositProductResponseDto> pageOfProductsDto = generatePageOfDepositProductsDto(pageOfProducts);

        Mockito
                .when(depositProductService.findAll(pageable))
                .thenReturn(pageOfProducts);

        Mockito
                .when(depositProductMapper.toDepositProductDto(Mockito.any(DepositProduct.class)))
                .thenReturn(new DepositProductResponseDto());

        Page<DepositProductResponseDto> result = depositProductController.findAll(pageable);

        assertEquals(pageOfProductsDto, result);
    }

    @Test
    void update_whenOk(){
        Mockito
                .when(depositProductMapper.toDepositProduct(productDto))
                .thenReturn(product);

        depositProductController.update(productDto);

        Mockito
                .verify(depositProductService, Mockito.times(1))
                .update(product);
    }

    @Test
    void update_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .when(depositProductMapper.toDepositProduct(productDto))
                .thenReturn(product);
        Mockito
                .doThrow(NotFoundException.class)
                .when(depositProductService)
                .update(product);

        assertThrows(NotFoundException.class, () -> depositProductController.update(productDto));
    }

    @Test
    void delete_whenOk(){
        depositProductController.deleteById(id);

        Mockito
                .verify(depositProductService, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    void delete_whenNotFound_shouldThrowNotFoundException(){
        Mockito
                .doThrow(NotFoundException.class)
                .when(depositProductService)
                .deleteById(id);

        assertThrows(NotFoundException.class, () -> depositProductController.deleteById(id));
    }
}
