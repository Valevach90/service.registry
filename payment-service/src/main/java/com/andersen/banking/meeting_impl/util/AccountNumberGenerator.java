package com.andersen.banking.meeting_impl.util;

import static com.andersen.banking.meeting_impl.util.PaymentInfoMaps.getCurrencyNumberMap;

import com.andersen.banking.meeting_impl.exception.PaymentServiceException;
import java.util.Random;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountNumberGenerator {

  private static final Random randomizer = new Random(System.currentTimeMillis());

  public static String generateAccountNumber(String bankName, String currency, UUID ownerId) {
    StringBuilder accountNumberBuilder = new StringBuilder();
    String currencyNumber = getCurrencyNumberMap().get(currency);
    String bankHash = String.valueOf(Math.abs(bankName.hashCode()));
    String uuidHash = String.valueOf(Math.abs(ownerId.toString().hashCode()));

    appendCurrencyNumber(accountNumberBuilder, currencyNumber);
    appendBankHashAndMakeTo10Numbers(accountNumberBuilder, bankHash, currencyNumber);
    appendUuidHashAndMakeTo20Numbers(accountNumberBuilder, uuidHash);

    return accountNumberBuilder.toString();
  }

  private static void appendCurrencyNumber(
      StringBuilder accountNumberBuilder, String currencyNumber) {
    if (currencyNumber != null) accountNumberBuilder.append(currencyNumber);
    else {
      log.error("Currency not found. Couldn't generate account number");
      throw new PaymentServiceException("Wrong currency");
    }
  }

  private static void appendBankHashAndMakeTo10Numbers(
      StringBuilder accountNumberBuilder, String bankHash, String currencyNumber) {
    if (bankHash.length() > 10 - currencyNumber.length()) {
      bankHash = bankHash.substring(0, 10 - currencyNumber.length());
      accountNumberBuilder.append(bankHash);
    } else {
      accountNumberBuilder.append(bankHash);
      for (int i = 0; i < 10 - bankHash.length(); i++) {
        appendRandomDigit(accountNumberBuilder);
      }
    }
  }

  private static void appendUuidHashAndMakeTo20Numbers(
      StringBuilder accountNumberBuilder, String uuidHash) {
    accountNumberBuilder.append(uuidHash);

    int needToBeAddedTimes = 20 - accountNumberBuilder.length();
    for (int i = 0; i < needToBeAddedTimes; i++) {
      accountNumberBuilder.append(randomizer.nextInt(10));
    }
  }

  private static void appendRandomDigit(StringBuilder accountNumberBuilder) {
    accountNumberBuilder.append(randomizer.nextInt(10));
  }
}
