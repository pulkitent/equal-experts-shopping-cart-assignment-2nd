package domain;

import java.math.BigDecimal;

public class Product {
  private final String name;
  private final BigDecimal price;

  public Product(final String name, final BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Product product = (Product) o;

    if (!name.equals(product.name)) return false;
    return this.getPrice().equals(product.getPrice());
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + this.getPrice().hashCode();
    return result;
  }

  protected BigDecimal getPrice() {
    return price;
  }
}
