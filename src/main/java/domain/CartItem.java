package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
  private final Product product;
  private final int quantity;

  public CartItem(final Product product, final int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CartItem cartItem = (CartItem) o;

    if (this.getQuantity() != cartItem.getQuantity()) return false;
    return Objects.equals(this.getProduct(), cartItem.getProduct());
  }

  @Override
  public int hashCode() {
    int result = this.getProduct() != null ? this.getProduct().hashCode() : 0;
    result = 31 * result + this.getQuantity();
    return result;
  }

  protected boolean hasSameProduct(Product product) {
    return this.getProduct().equals(product);
  }

  protected CartItem addQuantityBy(int quantity) {
    return new CartItem(this.getProduct(), this.getQuantity() + quantity);
  }

  protected BigDecimal calculateTotalAmount() {
    return this.getProduct()
        .getPrice()
        .multiply(BigDecimal.valueOf(this.getQuantity()));
  }

  protected Product getProduct() {
    return product;
  }

  protected int getQuantity() {
    return quantity;
  }
}
