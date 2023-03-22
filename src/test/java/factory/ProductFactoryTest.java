package factory;

import client.ProductAPIClient;
import domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductFactoryTest {
  private Retrofit retrofit;
  private Call<Product> productCall;
  private Response<Product> productResponse;
  private ProductFactory productFactory;

  @BeforeEach
  void setup() {
    retrofit = mock(Retrofit.class);
    productCall = mock(Call.class);
    productResponse = mock(Response.class);
    productFactory = new ProductFactory(retrofit);
  }

  @Test
  @DisplayName("Test createProduct should return product wtih given name & price fetched from product API call")
  void testCreateProduct_ShouldReturnProductWithGivenNameAndPrice() throws IOException {
    //Given
    when(retrofit.create(ProductAPIClient.class)).thenReturn(productName -> productCall);
    when(productCall.execute()).thenReturn(productResponse);
    when(productResponse.body()).thenReturn(new Product("cheerios", new BigDecimal("10.01")));
    Product expectedProduct = new Product("cheerios", new BigDecimal("10.01"));

    //When
    Product actualProduct = productFactory.createProduct("cheerios");

    //Then
    Assertions.assertEquals(expectedProduct, actualProduct);
  }

  @Test
  @DisplayName("Test createProduct should throw runtime exception when product API call fails")
  void testCreateProduct_ShouldHandleIOExceptionGracefully() throws IOException {
    //Given
    when(retrofit.create(ProductAPIClient.class)).thenReturn(productName -> productCall);
    when(productCall.execute()).thenThrow(new IOException("something went wrong"));

    //When & Then
    Assertions.assertThrows(RuntimeException.class, () -> productFactory.createProduct("cheerios"));
  }
}
