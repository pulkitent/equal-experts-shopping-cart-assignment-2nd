package client;

import domain.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductAPIClient {
  @GET("/backend-take-home-test-data/{product}.json")
  Call<Product> getProduct(@Path("product") String productName);
}
