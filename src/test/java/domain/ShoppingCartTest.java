package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingCartTest {
  private ShoppingCart shoppingCart;
  private ArrayList<CartItem> cartItems;
  private final RoundingMode roundingOffStrategy = RoundingMode.HALF_UP;

  @Mock
  private Tax mockTax;

  @Test
  @DisplayName("Test calculateTotalPayable should return expected total payable when cheerios with two quantity having 8.0 unit price are added to cart")
  void testCalculateTotalPayable_ShouldReturnTotalPayableWhenCheeriosWithTwoQuantityIsAddedToCart() {
    //Given
    cartItems = new ArrayList<>();
    shoppingCart = new ShoppingCart(cartItems, mockTax, roundingOffStrategy);
    Product cheerios = new Product("cheerios", new BigDecimal("8.0"));
    BigDecimal expectedTotalPrice = new BigDecimal("18");
    when(mockTax.calculate(new BigDecimal("16.0"))).thenReturn(new BigDecimal("2.0"));

    //When
    shoppingCart.addProduct(cheerios, 2);

    //Then
    assertEquals(expectedTotalPrice, shoppingCart.calculateTotalPayable());
  }

  @Test
  @DisplayName("Test calculateTotalPayable should return expected total payable when two cornflakes with 2.52 unit price & one wettabix with 9.98 unit price are added to cart")
  void testCalculateTotalPayable_ShouldReturnTotalPayableWhenCornflakesWithTwoQuantityAndWettabixWithOneQuantityAreAddedToCart() {
    //Given
    cartItems = new ArrayList<>();
    shoppingCart = new ShoppingCart(cartItems, mockTax, roundingOffStrategy);
    Product cornflakes = new Product("cornflakes", new BigDecimal("2.52"));
    Product weetabix = new Product("weetabix", new BigDecimal("9.98"));
    BigDecimal expectedTotalPrice = new BigDecimal("17");
    when(mockTax.calculate(new BigDecimal("15.02"))).thenReturn(new BigDecimal("1.8775"));

    //When
    shoppingCart.addProduct(cornflakes, 2);
    shoppingCart.addProduct(weetabix, 1);

    //Then
    assertEquals(expectedTotalPrice, shoppingCart.calculateTotalPayable());
  }

  @Test
  @DisplayName("Test calculateSubTotalPayable should return expected sub total payable when two cornflakes with 2.52 unit price & one wettabix with 9.98 unit price are added to cart")
  void testCalculateSubTotalPayable_ShouldReturnTotalPayableWhenCornflakesWithTwoQuantityAndWettabixWithOneQuantityAreAddedToCart() {
    //Given
    cartItems = new ArrayList<>();
    shoppingCart = new ShoppingCart(cartItems, mockTax, roundingOffStrategy);
    Product cornflakes = new Product("cornflakes", new BigDecimal("2.52"));
    Product weetabix = new Product("weetabix", new BigDecimal("9.98"));
    BigDecimal expectedTotalPrice = new BigDecimal("15.02");

    //When
    shoppingCart.addProduct(cornflakes, 2);
    shoppingCart.addProduct(weetabix, 1);

    //Then
    assertEquals(expectedTotalPrice, shoppingCart.calculateSubTotalPayable());
  }

  @Test
  @DisplayName("Test calculateTaxPayable should return expected tax payable when two cornflakes with 2.52 unit price & one wettabix with 9.98 unit price are added to cart")
  void testCalculateTaxPayable_ShouldReturnTaxPayableWhenCornflakesWithTwoQuantityAndWettabixWithOneQuantityAreAddedToCart() {
    //Given
    cartItems = new ArrayList<>();
    shoppingCart = new ShoppingCart(cartItems, mockTax, roundingOffStrategy);
    Product cornflakes = new Product("cornflakes", new BigDecimal("2.52"));
    Product weetabix = new Product("weetabix", new BigDecimal("9.98"));
    BigDecimal taxValue = new BigDecimal("1.8775");
    when(mockTax.calculate(new BigDecimal("15.02"))).thenReturn(taxValue);

    //When
    shoppingCart.addProduct(cornflakes, 2);
    shoppingCart.addProduct(weetabix, 1);

    //Then
    assertEquals(taxValue, shoppingCart.calculateTaxPayable());
  }

  @Test
  @DisplayName("Test addProduct should add cheerios with two quantity into the shopping cart")
  void testAddProduct_ShouldAddCorrectProductsWhenCheeriosWithTwoQuantityAreAddedToCart() {
    //Given
    cartItems = new ArrayList<>();
    shoppingCart = new ShoppingCart(cartItems, mockTax, roundingOffStrategy);
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
    shoppingCart = new ShoppingCart(cartItems, mockTax, roundingOffStrategy);

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
    shoppingCart = new ShoppingCart(cartItems, mockTax, roundingOffStrategy);
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
