package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckoutTest {
  private Checkout checkout;
  private final RoundingMode roundingOffStrategy = RoundingMode.HALF_UP;

  @Mock
  private ShoppingCart mockShoppingCart;
  @Mock
  private Tax mockTax;

  @Test
  @DisplayName("Test calculateTotalPayable should return expected total payable when cheerios with two quantity having 8.0 unit price are added to cart")
  void testCalculateTotalPayable_ShouldReturnTotalPayableWhenCheeriosWithTwoQuantityIsAddedToCart() {
    //Given
    checkout = new Checkout(mockShoppingCart, mockTax, roundingOffStrategy);
    Product cheerios = new Product("cheerios", new BigDecimal("8.0"));
    BigDecimal expectedTotalPrice = new BigDecimal("10");
    List<CartItem> cartItems = Collections.singletonList(new CartItem(cheerios, 1));
    when(mockTax.calculate(new BigDecimal("8.0"))).thenReturn(new BigDecimal("2.0"));
    when(mockShoppingCart.getCartItems()).thenReturn(cartItems);

    //When
    BigDecimal totalPayable = checkout.calculateTotalPayable();

    //Then
    assertEquals(expectedTotalPrice, totalPayable);
  }

  @Test
  @DisplayName("Test calculateTotalPayable should return expected total payable when two cornflakes with 2.52 unit price & one wettabix with 9.98 unit price are added to cart")
  void testCalculateTotalPayable_ShouldReturnTotalPayableWhenCornflakesWithTwoQuantityAndWettabixWithOneQuantityAreAddedToCart() {
    //Given
    checkout = new Checkout(mockShoppingCart, mockTax, roundingOffStrategy);
    Product cornflakes = new Product("cornflakes", new BigDecimal("2.52"));
    Product weetabix = new Product("weetabix", new BigDecimal("9.98"));
    BigDecimal expectedTotalPrice = new BigDecimal("17");
    List<CartItem> cartItems = Arrays.asList(new CartItem(cornflakes, 2), new CartItem(weetabix, 1));
    when(mockTax.calculate(new BigDecimal("15.02"))).thenReturn(new BigDecimal("1.8775"));
    when(mockShoppingCart.getCartItems()).thenReturn(cartItems);

    //When
    BigDecimal totalPayable = checkout.calculateTotalPayable();

    //Then
    assertEquals(expectedTotalPrice, totalPayable);
  }

  @Test
  @DisplayName("Test calculateSubTotalPayable should return expected sub total payable when two cornflakes with 2.52 unit price & one wettabix with 9.98 unit price are added to cart")
  void testCalculateSubTotalPayable_ShouldReturnTotalPayableWhenCornflakesWithTwoQuantityAndWettabixWithOneQuantityAreAddedToCart() {
    //Given
    checkout = new Checkout(mockShoppingCart, mockTax, roundingOffStrategy);
    Product cornflakes = new Product("cornflakes", new BigDecimal("2.52"));
    Product weetabix = new Product("weetabix", new BigDecimal("9.98"));
    BigDecimal expectedSubTotalPrice = new BigDecimal("15.02");
    List<CartItem> cartItems = Arrays.asList(new CartItem(cornflakes, 2), new CartItem(weetabix, 1));
    when(mockShoppingCart.getCartItems()).thenReturn(cartItems);

    //When
    BigDecimal actualSubTotalPayable = checkout.calculateSubTotalPayable();

    //Then
    assertEquals(expectedSubTotalPrice, actualSubTotalPayable);
  }

  @Test
  @DisplayName("Test calculateTaxPayable should return expected tax payable when two cornflakes with 2.52 unit price & one wettabix with 9.98 unit price are added to cart")
  void testCalculateTaxPayable_ShouldReturnTaxPayableWhenCornflakesWithTwoQuantityAndWettabixWithOneQuantityAreAddedToCart() {
    //Given
    checkout = new Checkout(mockShoppingCart, mockTax, roundingOffStrategy);
    Product cornflakes = new Product("cornflakes", new BigDecimal("2.52"));
    Product weetabix = new Product("weetabix", new BigDecimal("9.98"));
    BigDecimal expectedTaxPayable = new BigDecimal("1.8775");
    List<CartItem> cartItems = Arrays.asList(new CartItem(cornflakes, 2), new CartItem(weetabix, 1));
    when(mockTax.calculate(new BigDecimal("15.02"))).thenReturn(new BigDecimal("1.8775"));
    when(mockShoppingCart.getCartItems()).thenReturn(cartItems);

    //When
    BigDecimal actualTaxPayable = checkout.calculateTaxPayable();

    //Then
    assertEquals(expectedTaxPayable, actualTaxPayable);
  }
}
