package com.xabe.completablefuture;

import com.xabe.completablefuture.dto.Product;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ServiceProduct<T extends Product> {

  CompletableFuture<List<T>> getProductsAsync(String id);
}
