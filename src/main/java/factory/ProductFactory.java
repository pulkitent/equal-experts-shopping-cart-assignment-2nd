package factory;

import client.ProductAPIClient;
import domain.Product;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

public class ProductFactory {
  private final Retrofit retrofit;

  public ProductFactory(final Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  public Product createProduct(String name) {
    final ProductAPIClient client = retrofit.create(ProductAPIClient.class);
    final Call<Product> productCall = client.getProduct(name);

    try {
      final Response<Product> response = productCall.execute();
      return response.body();
    } catch (IOException ioException) {
      throw new RuntimeException("error while fetching product price from product API");
    }
  }
}
