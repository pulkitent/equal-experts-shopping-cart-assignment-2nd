package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartItemTest {
  private CartItem cartItem;
  private Product product;

  @Mock
  private Product mockProduct;

  @Test
  @DisplayName("Test hasSameProduct should return true when same cheerios product is given")
  void testHasSameProduct_ShouldReturnTrueWhenGivenSameProduct() {
    //Given
    product = new Product("cheerios", new BigDecimal("10"));
    cartItem = new CartItem(product, 1);

    //When
    boolean result = cartItem.hasSameProduct(new Product("cheerios", new BigDecimal("10")));

    //Then
    Assertions.assertTrue(result);
  }

  @Test
  @DisplayName("Test hasSameProduct should return false when different product is given")
  void testHasSameProduct_ShouldReturnTrueWhenGivenDifferentProduct() {
    //Given
    product = new Product("cornflakes", new BigDecimal("10"));
    cartItem = new CartItem(product, 1);

    //When
    boolean result = cartItem.hasSameProduct(new Product("cheerios", new BigDecimal("10")));

    //Then
    Assertions.assertFalse(result);
  }

  @Test
  @DisplayName("Test calculateTotalAmount should return 20.02 when cart items has 2 quantity of cornflakes with 10.01 unit price")
  void testCalculateTotalAmount_ShouldReturnTotalAmountWhenCartItemHasTwoQuantities() {
    //Given
    cartItem = new CartItem(mockProduct, 2);
    when(mockProduct.getPrice()).thenReturn(new BigDecimal("10.01"));
    BigDecimal expectedTotalAmount = new BigDecimal("20.02");

    //When
    BigDecimal actualTotalAmount = cartItem.calculateTotalAmount();

    //Then
    Assertions.assertEquals(expectedTotalAmount, actualTotalAmount);
  }

  @Test
  @DisplayName("Test getProduct should return the cornflakes product having 10.01 price present in the cart item")
  void testGetProduct_ShouldReturnProductPresentInCartItem() {
    //Given
    product = new Product("cornflakes", new BigDecimal("10.01"));
    cartItem = new CartItem(product, 2);

    //When
    Product actualProduct = cartItem.getProduct();

    //Then
    Assertions.assertEquals(product, actualProduct);
  }

  @Test
  @DisplayName("Test getQuantity should return the correct quantity of cornflakes product in the cart item")
  void testGetQuantity_ShouldReturnQuantityPresentInCartItem() {
    //Given
    product = new Product("cornflakes", new BigDecimal("10.01"));
    int quantity = 2;
    cartItem = new CartItem(product, quantity);

    //When
    int actualQuantity = cartItem.getQuantity();

    //Then
    Assertions.assertEquals(quantity, actualQuantity);
  }

  @Test
  @DisplayName("Test equals for reflexive behaviour as per the equal's contract")
  void testEqualsForReflexiveBehaviour_ShouldReturnTrueWhenGivenSameProduct() {
    //Given
    int quantity = 2;
    product = new Product("cornflakes", new BigDecimal("10.01"));
    cartItem = new CartItem(product, quantity);

    //When
    boolean result = cartItem.equals(cartItem);

    //Then
    Assertions.assertTrue(result);
  }

  @Test
  @DisplayName("Test equals for symmetric behaviour as per the equal's contract")
  void testEqualsForSymmetricBehaviour_ShouldReturnTrueWhenGivenSameProduct() {
    //Given
    product = new Product("cornflakes", new BigDecimal("10.01"));
    cartItem = new CartItem(product, 2);
    CartItem secondCartItem = new CartItem(new Product("cornflakes", new BigDecimal("10.01")), 2);

    //When
    boolean result = cartItem.equals(secondCartItem);
    boolean anotherResult = secondCartItem.equals(cartItem);

    //Then
    Assertions.assertTrue(result);
    Assertions.assertTrue(anotherResult);
  }

  @Test
  @DisplayName("Test equals for transitive behaviour as per the equal's contract")
  void testEqualsForTransitiveBehaviour_ShouldReturnTrueWhenGivenSameProduct() {
    //Given
    product = new Product("cornflakes", new BigDecimal("10.01"));
    cartItem = new CartItem(product, 2);
    CartItem secondCartItem = new CartItem(new Product("cornflakes", new BigDecimal("10.01")), 2);
    CartItem thirdCartItem = new CartItem(new Product("cornflakes", new BigDecimal("10.01")), 2);

    //When
    boolean result = cartItem.equals(secondCartItem);
    boolean secondResult = secondCartItem.equals(thirdCartItem);
    boolean thirdResult = thirdCartItem.equals(cartItem);

    //Then
    Assertions.assertTrue(result);
    Assertions.assertTrue(secondResult);
    Assertions.assertTrue(thirdResult);
  }

  @Test
  @DisplayName("Test hashCode should return expected hashcode on the basis of product's name & price")
  void testHashCode_ShouldReturnExpectedHashCodeValueByProductAndQuantity() {
    //Given
    product = new Product("cheerios", new BigDecimal("10.01"));
    cartItem = new CartItem(product, 2);
    int expectedHashCode = 1467144585;

    //When
    int actualHashCode = cartItem.hashCode();

    Assertions.assertEquals(expectedHashCode, actualHashCode);
  }
}
