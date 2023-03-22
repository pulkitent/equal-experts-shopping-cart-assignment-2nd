package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Domain service class
public class Checkout {
  private final ShoppingCart shoppingCart;
  private final Tax tax;
  private final RoundingMode roundingOffStrategy;

  public Checkout(ShoppingCart shoppingCart, Tax tax, RoundingMode roundingOffStrategy) {
    this.shoppingCart = shoppingCart;
    this.tax = tax;
    this.roundingOffStrategy = roundingOffStrategy;
  }

  BigDecimal calculateTotalPayable() {
    return this.calculateSubTotalPayable()
        .add(this.calculateTaxPayable())
        .setScale(0, this.roundingOffStrategy);
  }

  BigDecimal calculateSubTotalPayable() {
    return this.shoppingCart.getCartItems()
        .stream()
        .map(CartItem::calculateTotalAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  BigDecimal calculateTaxPayable() {
    return this.tax.calculate(this.calculateSubTotalPayable());
  }
}
