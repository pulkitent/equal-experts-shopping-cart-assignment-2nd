package domain;

import domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductTest {
  private Product product;

  @Test
  @DisplayName("Test equals for reflexive behaviour as per the equal's contract")
  void testEqualsForReflexiveBehaviour_ShouldReturnTrueWhenGivenSameProduct() {
    //Given
    product = new Product("cheerios", new BigDecimal("10.01"));

    //When
    boolean result = product.equals(product);

    //Then
    Assertions.assertTrue(result);
  }

  @Test
  @DisplayName("Test equals for symmetric behaviour as per the equal's contract")
  void testEqualsForSymmetricBehaviour_ShouldReturnTrueWhenGivenSameProduct() {
    //Given
    product = new Product("cheerios", new BigDecimal("10.01"));
    Product anotherProduct = new Product("cheerios", new BigDecimal("10.01"));

    //When
    boolean result = product.equals(anotherProduct);
    boolean anotherResult = anotherProduct.equals(product);

    //Then
    Assertions.assertTrue(result);
    Assertions.assertTrue(anotherResult);
  }

  @Test
  @DisplayName("Test equals for transitive behaviour as per the equal's contract")
  void testEqualsForTransitiveBehaviour_ShouldReturnTrueWhenGivenSameProduct() {
    //Given
    product = new Product("cheerios", new BigDecimal("10.01"));
    Product secondProduct = new Product("cheerios", new BigDecimal("10.01"));
    Product thirdProduct = new Product("cheerios", new BigDecimal("10.01"));

    //When
    boolean result = product.equals(secondProduct);
    boolean secondResult = secondProduct.equals(thirdProduct);
    boolean thirdResult = product.equals(thirdProduct);

    //Then
    Assertions.assertTrue(result);
    Assertions.assertTrue(secondResult);
    Assertions.assertTrue(thirdResult);
  }

  @Test
  @DisplayName("Test hashCode should return expected hashcode on the basis of product's name & price")
  void testHashCode_ShouldReturnExpectedHashCodeValueByNameAndPrice() {
    //Given
    product = new Product("cheerios", new BigDecimal("10.01"));
    int expectedHashCode = 462969241;

    //When
    int actualHashCode = product.hashCode();

    Assertions.assertEquals(expectedHashCode, actualHashCode);
  }

  @Test
  @DisplayName("Test getPrice should return given product's price")
  void testGetPrice_ShouldReturnPriceOfGivenProduct() {
    //Given
    BigDecimal price = new BigDecimal("10.01");
    product = new Product("cheerios", price);

    //When
    BigDecimal actualPrice = product.getPrice();

    //Then
    Assertions.assertEquals(price, actualPrice);
  }
}
