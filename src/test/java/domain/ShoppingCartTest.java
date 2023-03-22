package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class ShoppingCartTest {
  private ShoppingCart shoppingCart;
  private ArrayList<CartItem> cartItems;

  @Test
  @DisplayName("Test addProduct should add cheerios with two quantity into the shopping cart")
  void testAddProduct_ShouldAddCorrectProductsWhenCheeriosWithTwoQuantityAreAddedToCart() {
    //Given
    cartItems = new ArrayList<>();
    shoppingCart = new ShoppingCart(cartItems);
    String name = "cheerios";
    Product cheerios = new Product(name, new BigDecimal("8.0"));
    Product expectedProduct = new Product(name, new BigDecimal("8.0"));

    //When
    shoppingCart.addProduct(cheerios, 2);

    //Then
    List<CartItem> shoppingCartItems = shoppingCart.getCartItems();
    assertFalse(shoppingCartItems.isEmpty());
    assertEquals(expectedProduct, shoppingCartItems.get(0).getProduct());
    assertEquals(2, shoppingCartItems.get(0).getQuantity());
  }

  @Test
  @DisplayName("Test addProduct should add cheerios with one quantity into the shopping cart when already one cheerios was present")
  void testAddProduct_ShouldAddCorrectProductsWhenCheeriosWithOneQuantityIsAddedIntoCartHavingCheeriosAlreadyPresent() {
    //Given
    cartItems = new ArrayList<>();
    String name = "cheerios";
    Product cheerios = new Product(name, new BigDecimal("8.0"));
    cartItems.add(new CartItem(cheerios, 1));
    Product expectedProduct = new Product(name, new BigDecimal("8.0"));
    shoppingCart = new ShoppingCart(cartItems);

    //When
    shoppingCart.addProduct(cheerios, 1);

    //Then
    List<CartItem> shoppingCartItems = shoppingCart.getCartItems();
    assertFalse(shoppingCartItems.isEmpty());
    assertEquals(expectedProduct, shoppingCartItems.get(0).getProduct());
    assertEquals(2, shoppingCartItems.get(0).getQuantity());
  }

  @Test
  @DisplayName("Test addProduct should add cornflakes with two quantity and weetabix with one quantity into the shopping cart")
  void testAddProduct_ShouldAddCorrectProductsWhenCornflakesWithTwoQuantityAndWettabixWithOneQuantityAreAddedToCart() {
    //Given
    cartItems = new ArrayList<>();
    shoppingCart = new ShoppingCart(cartItems);
    String name = "cornflakes";
    Product cornflakes = new Product(name, new BigDecimal("2.52"));
    String anotherName = "weetabix";
    Product weetabix = new Product(anotherName, new BigDecimal("9.98"));
    Product expectedProduct = new Product(name, new BigDecimal("2.52"));
    Product anotherExpectedProduct = new Product(anotherName, new BigDecimal("9.98"));

    //When
    shoppingCart.addProduct(cornflakes, 2);
    shoppingCart.addProduct(weetabix, 1);

    //Then
    List<CartItem> shoppingCartItems = shoppingCart.getCartItems();
    assertFalse(shoppingCartItems.isEmpty());
    assertEquals(expectedProduct, shoppingCartItems.get(0).getProduct());
    assertEquals(2, shoppingCartItems.get(0).getQuantity());
    assertEquals(anotherExpectedProduct, shoppingCartItems.get(1).getProduct());
    assertEquals(1, shoppingCartItems.get(1).getQuantity());
  }
}
