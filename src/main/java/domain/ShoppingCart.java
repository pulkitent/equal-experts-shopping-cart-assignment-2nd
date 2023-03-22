package domain;

import java.util.Collections;
import java.util.List;

public class ShoppingCart {
  private final List<CartItem> cartItems;

  public ShoppingCart(final List<CartItem> cartItems) {
    this.cartItems = cartItems;
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
}
