package com.xabe.completablefuture;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import com.xabe.completablefuture.dto.Account;
import com.xabe.completablefuture.dto.Product;
import com.xabe.completablefuture.dto.ProductType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceProductImplTest {

  private ServiceProductDetail<Account> serviceProductDetail;

  private ServiceProduct<Product> serviceProduct;

  @BeforeEach
  public void setUp() throws Exception {
    this.serviceProductDetail =
        (product, executorService) -> List.of(CompletableFuture.completedFuture(product1 -> {
          product1.setProfit(BigDecimal.TEN);
          return product1;
        }));
    this.serviceProduct = new ServiceProductImpl(Map.of(ProductType.ACCOUNT, this.serviceProductDetail));
  }

  @Test
  public void givenAIdWhenInvokeGetProductsThenReturnListProducts() throws Exception {
    //Given
    final String id = "231231212G";

    //When
    final List<Product> result = this.serviceProduct.getProductsAsync(id).get();

    //then
    assertThat(result, is(notNullValue()));
    assertThat(result, is(hasSize(1)));
    assertThat(result.get(0).getProductType(), is(ProductType.ACCOUNT));
    final Account account = (Account) result.get(0);
    assertThat(account.getProfit(), is(BigDecimal.TEN));
  }

}
