package com.andersen.banking.meeting_impl.util;

import static com.andersen.banking.meeting_impl.util.PaymentInfoMaps.getCurrencyNumberMap;
import static com.andersen.banking.meeting_impl.util.PaymentInfoMaps.getPaymentSystemMap;
import static com.andersen.banking.meeting_impl.util.PaymentInfoMaps.getTypeNumberMap;

import com.andersen.banking.meeting_db.entities.Card;
import com.andersen.banking.meeting_impl.exception.PaymentServiceException;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardGenerator {

  private static final Random randomizer = new Random(System.currentTimeMillis());

  public static void generateExpirationTime(Card card) {
    // need to add some logic;
    card.setExpireDate(card.getValidFromDate().plusYears(3));
  }

  public static String generateCardNumber(String cardPaymentSystem, String typeName, String currency, long countAllCardCreated) {
    StringBuilder cardNumberBuilder = new StringBuilder();

    appendStartNumbers(cardNumberBuilder, cardPaymentSystem); //first 4 digits
    appendCurrencyNumber(cardNumberBuilder, currency);
    appendTypeNumber(cardNumberBuilder, typeName);
    appendUniqueNumbers(cardNumberBuilder, countAllCardCreated + 1);
    appendCheckDigit(cardNumberBuilder);

    log.info("card number was generated: {}", cardNumberBuilder);
    return cardNumberBuilder.toString();
  }


  private static void appendStartNumbers(StringBuilder cardNumberBuilder, String cardPaymentSystem){
    List<String> startNumbersList = getPaymentSystemMap().get(cardPaymentSystem);
    if (startNumbersList == null) {
      log.error("Payment System [{}] not found. Couldn't generate card", cardPaymentSystem);
      throw new PaymentServiceException("Wrong card payment system");
    }

    String startNumbers = startNumbersList.get(randomizer.nextInt(startNumbersList.size()));
    cardNumberBuilder.append(startNumbers);

    //To make 4 length number if needed
    for (int i = 0; i < 4 - startNumbers.length(); i++) {
      cardNumberBuilder.append(randomizer.nextInt(10));
    }
  }

  private static void appendCurrencyNumber(StringBuilder cardNumberBuilder, String currency){
    String currencyNumber = getCurrencyNumberMap().get(currency);
    if (currencyNumber != null) {
      cardNumberBuilder.append(currencyNumber);
    } else {
      log.error("Currency [{}] not found. Couldn't generate card", currency);
      throw new PaymentServiceException("Wrong currency");
    }
  }

  private static void appendTypeNumber(StringBuilder cardNumberBuilder, String typeName){
    String typeNumber = getTypeNumberMap().get(typeName);
    if (typeNumber != null) {
      cardNumberBuilder.append(typeNumber);
    } else {
      log.error("Type [{}] not found. Couldn't generate card", typeName);
      throw new PaymentServiceException("Wrong type of card");
    }
  }

  private static void appendUniqueNumbers(StringBuilder cardNumberBuilder, long countAllCardCreated){
    String countAllCardCreatedStr = String.valueOf(countAllCardCreated);
    int zeroesNeeded = 15 - cardNumberBuilder.length() - countAllCardCreatedStr.length();
    for (int i = 0; i < zeroesNeeded; i++) {
      cardNumberBuilder.append("0");
    }
    cardNumberBuilder.append(countAllCardCreatedStr);
  }

  private static void appendCheckDigit(StringBuilder cardNumberBuilder){
    cardNumberBuilder.append(getCheckDigit(cardNumberBuilder.toString()));
  }

  // required to make sum % 10 == 0
  private static int getCheckDigit(String number) {
    int sum = 0;
    for (int i = 0; i < number.length(); i++) {
      int digit = Integer.parseInt(number.substring(i, (i + 1)));

      if ((i % 2) == 0) {
        digit = digit * 2;
        if (digit > 9) {
          digit = (digit / 10) + (digit % 10);
        }
      }
      sum += digit;
    }

    int mod = sum % 10;
    return ((mod == 0) ? 0 : 10 - mod);
  }
}
