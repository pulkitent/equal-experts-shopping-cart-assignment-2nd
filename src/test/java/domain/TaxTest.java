package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class TaxTest {

  @Test
  @DisplayName("Test calculate should return the tax amount payable for given amount")
  void testCalculate_ShouldReturnTaxPayableForGivenAmount() {
    //Given
    String rate = "0.125";
    Tax tax = new Tax(new BigDecimal(rate));

    //When
    BigDecimal taxAmount = tax.calculate(new BigDecimal("10"));

    //Then
    Assertions.assertEquals(new BigDecimal("1.250"), taxAmount);
  }
}
