package com.andersen.banking.meeting_impl.util;

import java.util.List;
import java.util.Map;

public class PaymentInfoMaps {

  private static final Map<String, List<String>> paymentSystemMap =
      Map.of(
          "VISA", List.of("4"),
          "MASTERCARD", List.of("51", "52", "53", "54", "55"));

  private static final Map<String, String> currencyNumberMap =
      Map.of(
          "BEL", "0",
          "EUR", "1",
          "USD", "2");

  private static final Map<String, String> typeNumberMap =
      Map.of(
          "SILVER", "1",
          "GOLD", "2",
          "PLATINUM", "3");

  public static Map<String, List<String>> getPaymentSystemMap() {
    return paymentSystemMap;
  }

  public static Map<String, String> getCurrencyNumberMap() {
    return currencyNumberMap;
  }

  public static Map<String, String> getTypeNumberMap() {
    return typeNumberMap;
  }
}
