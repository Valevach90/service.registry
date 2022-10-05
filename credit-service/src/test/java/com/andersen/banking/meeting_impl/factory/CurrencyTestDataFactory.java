package com.andersen.banking.meeting_impl.factory;


import com.andersen.banking.meeting_api.dto.CurrencyResponseDTO;
import com.andersen.banking.meeting_db.entity.Currency;
import java.util.UUID;

public final class CurrencyTestDataFactory {

    private CurrencyTestDataFactory() {
    }

    private static final UUID uuid = UUID
        .fromString("6c9a8c03-432c-47a9-9e9e-cc80fdace43d");
    private static final String NAME = "USD";

    public static Currency buildCurrency() {
        return Currency.builder()
            .id(uuid).name(NAME)
            .build();
    }

    public static CurrencyResponseDTO buildCurrencyResponseDTO() {
        return CurrencyResponseDTO.builder()
            .id(buildCurrency().getId())
            .name(buildCurrency().getName())
            .build();
    }
}
