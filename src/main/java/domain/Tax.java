package domain;

import java.math.BigDecimal;

public class Tax {
  private final BigDecimal rate;

  public Tax(final BigDecimal rate) {
    this.rate = rate;
  }

  protected BigDecimal calculate(BigDecimal amount) {
    return this.rate.multiply(amount);
  }
}
