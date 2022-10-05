package com.andersen.banking.meeting_impl.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.andersen.banking.meeting_api.dto.CreditProductRequestDTO;
import com.andersen.banking.meeting_api.dto.CreditProductResponseDTO;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_db.repository.CreditProductRepository;
import com.andersen.banking.meeting_db.repository.CurrencyRepository;
import com.andersen.banking.meeting_impl.exception.CreditProductNotFoundException;
import com.andersen.banking.meeting_impl.factory.CreditProductEntityTestDataFactory;
import com.andersen.banking.meeting_impl.mapper.CreditProductMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditProductServiceImplTest {

    @Mock
    private CreditProductRepository creditProductRepository;
    @Mock
    private CreditProductMapper creditProductMapper;
    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private CurrencyServiceImpl currencyService;

    @InjectMocks
    private CreditProductServiceImpl creditProductService;

    private CreditProduct creditProduct;
    private CreditProductRequestDTO creditProductRequestDTO;
    private CreditProductRequestDTO creditProductRequestDTOForUpdate;
    private CreditProductResponseDTO creditProductResponseDTO;
    private CreditProductResponseDTO creditProductResponseDTOForUpdate;
    private List<CreditProduct> creditProductList;
    private List<CreditProductResponseDTO> creditProductResponseDTOList;

    @BeforeEach
    void init() {

        creditProduct = CreditProductEntityTestDataFactory.buildCreditProduct();
        creditProductRequestDTO = CreditProductEntityTestDataFactory.buildCreditProductRequestDTO();
        creditProductRequestDTOForUpdate = CreditProductEntityTestDataFactory
            .buildCreditProductRequestDTOForUpdate();
        creditProductResponseDTO = CreditProductEntityTestDataFactory
            .buildCreditProductResponseDTO();
        creditProductResponseDTOForUpdate = CreditProductEntityTestDataFactory
            .buildCreditProductResponseDTOForUpdate();
        creditProductList = CreditProductEntityTestDataFactory.creditProductList();
        creditProductResponseDTOList = CreditProductEntityTestDataFactory
            .creditProductResponseDTOList();
    }

    @Test
    void createCreditProductWithSuccess() {

        when(creditProductMapper.toCreditProduct(creditProductRequestDTO))
            .thenReturn(creditProduct);
        when(creditProductRepository.save(creditProduct)).thenReturn(creditProduct);
        when(creditProductMapper.toCreditProductDTO(creditProduct)).thenReturn(
            creditProductResponseDTO);

        var actualResult = creditProductService
            .createCreditProduct(creditProductRequestDTO);

        verify(creditProductRepository, times(1)).save(creditProduct);
        verify(creditProductMapper, times(1))
            .toCreditProductDTO(creditProduct);

        assertThat(actualResult).isEqualTo(creditProductResponseDTO);
    }

    @Test
    void getCreditProductByIdWithSuccess() {

        when(creditProductRepository.findById(creditProduct.getUuid())).thenReturn(
            Optional.ofNullable(creditProduct));
        when(creditProductMapper.toCreditProductDTO(creditProduct)).thenReturn(
            creditProductResponseDTO);

        var actualResult = creditProductService
            .getCreditProductById(creditProduct.getUuid());

        verify(creditProductRepository, times(1))
            .findById(creditProduct.getUuid());
        verify(creditProductMapper, times(1))
            .toCreditProductDTO(creditProduct);

        assertThat(actualResult).isEqualTo(creditProductResponseDTO);
    }

    @Test
    void getCreditProductByIdWithFailureWhenCreditProductNotFoundException() {

        when(creditProductRepository.findById(creditProduct.getUuid()))
            .thenThrow(new CreditProductNotFoundException(creditProduct.getUuid()));

        assertThrows(CreditProductNotFoundException.class,
            () -> creditProductService.getCreditProductById(creditProduct.getUuid()));

        verify(creditProductRepository, times(1))
            .findById(creditProduct.getUuid());
    }

    @Test
    void getListOfCreditProductsWithSuccess() {

        when(creditProductRepository.findAll()).thenReturn(creditProductList);
        when(creditProductMapper.toCreditProductDTOList(creditProductList)).thenReturn(
            creditProductResponseDTOList);

        var actualResult = creditProductService.getListOfCreditProducts();

        verify(creditProductRepository, times(1))
            .findAll();
        verify(creditProductMapper, times(1))
            .toCreditProductDTOList(creditProductList);

        assertThat(actualResult).isEqualTo(creditProductResponseDTOList);
    }

    @Test
    void updateCreditProductWithSuccess() {

        when(creditProductRepository.findById(creditProduct.getUuid())).thenReturn(
            Optional.of(creditProduct));

        creditProductMapper.updateCreditProduct(creditProductRequestDTOForUpdate,creditProduct);

        when(creditProductRepository.save(creditProduct))
            .thenReturn(creditProduct);
        when(creditProductMapper.toCreditProductDTO(creditProduct))
            .thenReturn(creditProductResponseDTOForUpdate);

        var actualResult = creditProductService
            .updateCreditProduct(creditProduct.getUuid(),creditProductRequestDTOForUpdate);

        verify(creditProductRepository, times(1))
            .findById(creditProduct.getUuid());
        verify(creditProductMapper, times(1))
            .toCreditProductDTO(creditProduct);

        assertThat(actualResult).isEqualTo(creditProductResponseDTOForUpdate);
    }

    @Test
    void deleteCreditProductByIdWithSuccess() {

        creditProductService.deleteCreditProductById(creditProduct.getUuid());

        verify(creditProductRepository,times(1)).deleteById(creditProduct
            .getUuid());
    }
}