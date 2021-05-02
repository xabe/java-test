package com.xabe.completablefuture;

import com.xabe.completablefuture.dto.Product;
import com.xabe.completablefuture.dto.ProductFactory;
import com.xabe.completablefuture.dto.ProductType;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ServiceProductImpl implements ServiceProduct<Product> {

  private final Map<String, List<ProductType>> data;

  private final Map<ProductType, ServiceProductDetail> serviceProductDetailMap;

  private final ExecutorService executorService;

  public ServiceProductImpl(final Map<ProductType, ServiceProductDetail> serviceProductDetailMap) {
    this.serviceProductDetailMap = serviceProductDetailMap;
    this.data = Map.of("231231212G", List.of(ProductType.ACCOUNT), "47518458G", List.of(ProductType.ACCOUNT, ProductType.CARD));
    this.executorService = Executors.newFixedThreadPool(10);
  }

  @Override
  public CompletableFuture<List<Product>> getProductsAsync(final String id) {
    final CompletableFuture<List<ProductType>> productsType = this.getProductsUser(id);
    final CompletableFuture<List<Product>> products =
        CompletableFutureUtil.map(productsType, this::getProductsFromProductType, this.executorService);
    return CompletableFutureUtil.flatMap(products, this::getProductsDetails, this.executorService);
  }

  private CompletableFuture<List<ProductType>> getProductsUser(final String id) {
    return CompletableFuture.supplyAsync(() ->
        this.data.getOrDefault(id, List.of())
    );
  }

  private List<Product> getProductsFromProductType(final List<ProductType> productTypes) {
    return productTypes.stream().flatMap(type -> ProductFactory.createProduct(type).stream()).collect(Collectors.toList());
  }

  private CompletableFuture<List<Product>> getProductsDetails(final List<Product> products) {
    final List<CompletableFuture<Product>> productDetails = products.stream().map(this::getDetail).collect(Collectors.toList());
    return CompletableFutureUtil.sequence(productDetails, this.executorService);
  }

  private CompletableFuture<Product> getDetail(final Product product) {
    return this.serviceProductDetailMap.getOrDefault(product.getProductType(), ServiceProductDetail.DEFAULT).getProductDetail(product,
        this.executorService);
  }
}
