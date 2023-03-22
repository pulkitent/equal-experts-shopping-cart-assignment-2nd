package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
  private final List<CartItem> cartItems;
  private final Tax tax;
  private final RoundingMode roundingOffStrategy;

  public ShoppingCart(final List<CartItem> cartItems, final Tax tax, final RoundingMode roundingOffStrategy) {
    this.cartItems = cartItems;
    this.tax = tax;
    this.roundingOffStrategy = roundingOffStrategy;
  }

  void addProduct(Product product, int quantity) {
    final CartItem givenCartItem = new CartItem(product, quantity);

    for (CartItem currentCartItem : this.cartItems) {
      if (currentCartItem.hasSameProduct(givenCartItem.getProduct())) {
        this.cartItems.remove(currentCartItem);
        this.cartItems.add(currentCartItem.addQuantityBy(quantity));
        return;
      }
    }
    this.cartItems.add(givenCartItem);
  }

  List<CartItem> getCartItems() {
    return Collections.unmodifiableList(cartItems);
  }

  BigDecimal calculateTotalPayable() {
    return this.calculateSubTotalPayable()
        .add(this.calculateTaxPayable())
        .setScale(0, this.roundingOffStrategy);
  }

  BigDecimal calculateSubTotalPayable() {
    return this.getCartItems()
        .stream()
        .map(CartItem::calculateTotalAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  BigDecimal calculateTaxPayable() {
    return this.tax.calculate(this.calculateSubTotalPayable());
  }
}
