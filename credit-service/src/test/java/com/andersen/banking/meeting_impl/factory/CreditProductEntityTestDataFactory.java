package com.andersen.banking.meeting_impl.factory;

import com.andersen.banking.meeting_api.dto.CreditProductRequestDTO;
import com.andersen.banking.meeting_api.dto.CreditProductResponseDTO;
import com.andersen.banking.meeting_db.entity.CalculationMode;
import com.andersen.banking.meeting_db.entity.CreditProduct;
import com.andersen.banking.meeting_db.entity.Currency;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public final class CreditProductEntityTestDataFactory {

        private CreditProductEntityTestDataFactory() {
        }

        private static final UUID FIRST_ID = UUID
            .fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
        private static final UUID SECOND_ID = UUID
            .fromString("1427b785-17b3-494c-a9a4-ab5dbea14c22");
        private static final String FIRST_NAME = "Family mortgage";
        private static final String SECOND_NAME = "Consumer credit";
        private static final BigDecimal MIN_SUM = BigDecimal.valueOf(1234556.899290);
        private static final BigDecimal MAX_SUM = BigDecimal.valueOf(1234556000.899290);
        private static final Currency CURRENCY = CurrencyTestDataFactory.buildCurrency();
        private static final BigDecimal MIN_LOAN_RATE = BigDecimal.valueOf(2.5);
        private static final BigDecimal MAX_LOAN_RATE = BigDecimal.valueOf(16.7);
        private static final Boolean NEED_GUARANTEE = true;
        private static final Boolean EARLY_REPAYMENT = true;
        private static final Integer MIN_TERM = 3;
        private static final Integer MAX_TERM = 45;
        private static final String FIRST_DESCRIPTION = "Credit for family";
        private static final String SECOND_DESCRIPTION = "Consumer credit";
        private static final CalculationMode CALCULATION_MODE = CalculationMode.ANNUITY;
        private static final Integer GRACE_PERIOD_MONTH = 2;
        private static final Boolean NEED_INCOME_STATEMENT = true;

        public static CreditProduct buildCreditProduct(){
                return CreditProduct.builder().
                uuid(FIRST_ID).name(FIRST_NAME).minSum(MIN_SUM).maxSum(MAX_SUM).currency(CURRENCY)
                    .minLoanRate(MIN_LOAN_RATE).maxLoanRate(MAX_LOAN_RATE).needGuarantee(
                        NEED_GUARANTEE)
                    .earlyRepayment(EARLY_REPAYMENT).minTerm(MIN_TERM).maxTerm(MAX_TERM)
                    .description(FIRST_DESCRIPTION).calculationMode(CALCULATION_MODE)
                    .gracePeriodMonth(GRACE_PERIOD_MONTH).needIncomeStatement(NEED_INCOME_STATEMENT)
                .build();
        }

        public static CreditProduct buildSecondCreditProduct(){
                return CreditProduct.builder().
                    uuid(SECOND_ID).name(SECOND_NAME).minSum(MIN_SUM).maxSum(MAX_SUM)
                    .currency(CURRENCY).minLoanRate(MIN_LOAN_RATE).maxLoanRate(MAX_LOAN_RATE)
                    .needGuarantee(NEED_GUARANTEE).earlyRepayment(EARLY_REPAYMENT)
                    .minTerm(MIN_TERM).maxTerm(MAX_TERM).description(SECOND_DESCRIPTION)
                    .calculationMode(CALCULATION_MODE).gracePeriodMonth(GRACE_PERIOD_MONTH)
                    .needIncomeStatement(NEED_INCOME_STATEMENT)
                    .build();
        }

        public static CreditProduct buildCreditProductForUpdate(){
                return CreditProduct.builder().
                    uuid(FIRST_ID).name(SECOND_NAME).minSum(MIN_SUM).maxSum(MAX_SUM).currency(CURRENCY)
                    .minLoanRate(MIN_LOAN_RATE).maxLoanRate(MAX_LOAN_RATE).needGuarantee(
                        NEED_GUARANTEE)
                    .earlyRepayment(EARLY_REPAYMENT).minTerm(MIN_TERM).maxTerm(MAX_TERM)
                    .description(SECOND_DESCRIPTION).calculationMode(CALCULATION_MODE)
                    .gracePeriodMonth(GRACE_PERIOD_MONTH).needIncomeStatement(NEED_INCOME_STATEMENT)
                    .build();
        }

        public static CreditProductRequestDTO buildCreditProductRequestDTO(){
                return CreditProductRequestDTO.builder()
                    .name(buildCreditProduct().getName())
                    .minSum(buildCreditProduct().getMinSum())
                    .maxSum(buildCreditProduct().getMaxSum())
                    .currencyId(buildCreditProduct().getCurrency().getId())
                    .minLoanRate(buildCreditProduct().getMinLoanRate())
                    .maxLoanRate(buildCreditProduct().getMaxLoanRate())
                    .needGuarantee(buildCreditProduct().getNeedGuarantee())
                    .earlyRepayment(buildCreditProduct().getEarlyRepayment())
                    .minTerm(buildCreditProduct().getMinTerm())
                    .maxTerm(buildCreditProduct().getMaxTerm())
                    .description(buildCreditProduct().getDescription())
                    .calculationMode(buildCreditProduct().getCalculationMode())
                    .gracePeriodMonth(buildCreditProduct().getGracePeriodMonth())
                    .needIncomeStatement(buildCreditProduct().getNeedIncomeStatement())
                    .build();
        }

        public static CreditProductRequestDTO buildCreditProductRequestDTOForUpdate(){
                return CreditProductRequestDTO.builder()
                    .name(buildCreditProductForUpdate().getName())
                    .minSum(buildCreditProductForUpdate().getMinSum())
                    .maxSum(buildCreditProductForUpdate().getMaxSum())
                    .currencyId(buildCreditProductForUpdate().getCurrency().getId())
                    .minLoanRate(buildCreditProductForUpdate().getMinLoanRate())
                    .maxLoanRate(buildCreditProductForUpdate().getMaxLoanRate())
                    .needGuarantee(buildCreditProductForUpdate().getNeedGuarantee())
                    .earlyRepayment(buildCreditProductForUpdate().getEarlyRepayment())
                    .minTerm(buildCreditProductForUpdate().getMinTerm())
                    .maxTerm(buildCreditProductForUpdate().getMaxTerm())
                    .description(buildCreditProductForUpdate().getDescription())
                    .calculationMode(buildCreditProductForUpdate().getCalculationMode())
                    .gracePeriodMonth(buildCreditProductForUpdate().getGracePeriodMonth())
                    .needIncomeStatement(buildCreditProductForUpdate().getNeedIncomeStatement())
                    .build();
        }

        public static CreditProductResponseDTO buildCreditProductResponseDTO(){
                return CreditProductResponseDTO.builder()
                    .uuid(buildCreditProduct().getUuid())
                    .name(buildCreditProduct().getName())
                    .minSum(buildCreditProduct().getMinSum())
                    .maxSum(buildCreditProduct().getMaxSum())
                    .currency(CurrencyTestDataFactory.buildCurrencyResponseDTO())
                    .minLoanRate(buildCreditProduct().getMinLoanRate())
                    .maxLoanRate(buildCreditProduct().getMaxLoanRate())
                    .needGuarantee(buildCreditProduct().getNeedGuarantee())
                    .earlyRepayment(buildCreditProduct().getEarlyRepayment())
                    .minTerm(buildCreditProduct().getMinTerm())
                    .maxTerm(buildCreditProduct().getMaxTerm())
                    .description(buildCreditProduct().getDescription())
                    .calculationMode(buildCreditProduct().getCalculationMode())
                    .gracePeriodMonth(buildCreditProduct().getGracePeriodMonth())
                    .needIncomeStatement(buildCreditProduct().getNeedIncomeStatement())
                    .build();
        }

        public static CreditProductResponseDTO buildSecondCreditProductResponseDTO(){
                return CreditProductResponseDTO.builder()
                    .uuid(buildSecondCreditProduct().getUuid())
                    .name(buildSecondCreditProduct().getName())
                    .minSum(buildSecondCreditProduct().getMinSum())
                    .maxSum(buildSecondCreditProduct().getMaxSum())
                    .currency(CurrencyTestDataFactory.buildCurrencyResponseDTO())
                    .minLoanRate(buildSecondCreditProduct().getMinLoanRate())
                    .maxLoanRate(buildSecondCreditProduct().getMaxLoanRate())
                    .needGuarantee(buildSecondCreditProduct().getNeedGuarantee())
                    .earlyRepayment(buildSecondCreditProduct().getEarlyRepayment())
                    .minTerm(buildSecondCreditProduct().getMinTerm())
                    .maxTerm(buildSecondCreditProduct().getMaxTerm())
                    .description(buildSecondCreditProduct().getDescription())
                    .calculationMode(buildSecondCreditProduct().getCalculationMode())
                    .gracePeriodMonth(buildSecondCreditProduct().getGracePeriodMonth())
                    .needIncomeStatement(buildSecondCreditProduct().getNeedIncomeStatement())
                    .build();
        }

        public static CreditProductResponseDTO buildCreditProductResponseDTOForUpdate(){
                return CreditProductResponseDTO.builder()
                    .uuid(buildCreditProductForUpdate().getUuid())
                    .name(buildCreditProductForUpdate().getName())
                    .minSum(buildCreditProductForUpdate().getMinSum())
                    .maxSum(buildCreditProductForUpdate().getMaxSum())
                    .currency(CurrencyTestDataFactory.buildCurrencyResponseDTO())
                    .minLoanRate(buildCreditProductForUpdate().getMinLoanRate())
                    .maxLoanRate(buildCreditProductForUpdate().getMaxLoanRate())
                    .needGuarantee(buildCreditProductForUpdate().getNeedGuarantee())
                    .earlyRepayment(buildCreditProductForUpdate().getEarlyRepayment())
                    .minTerm(buildCreditProductForUpdate().getMinTerm())
                    .maxTerm(buildCreditProductForUpdate().getMaxTerm())
                    .description(buildCreditProductForUpdate().getDescription())
                    .calculationMode(buildCreditProductForUpdate().getCalculationMode())
                    .gracePeriodMonth(buildCreditProductForUpdate().getGracePeriodMonth())
                    .needIncomeStatement(buildCreditProductForUpdate().getNeedIncomeStatement())
                    .build();
        }

        public static List<CreditProduct> creditProductList(){
                return List.of(buildCreditProduct(),
                    buildSecondCreditProduct()
                    );
        }

        public static List<CreditProductResponseDTO> creditProductResponseDTOList(){
                return List.of(buildCreditProductResponseDTO(),
                    buildSecondCreditProductResponseDTO()
                );
        }
}
