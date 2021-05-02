package com.xabe.completablefuture;

import com.xabe.completablefuture.dto.Product;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface ServiceProductDetail<T extends Product> {

  ServiceProductDetail<Product> DEFAULT = (product, executorService) -> List.of(CompletableFuture.completedFuture(product1 -> product1));

  List<CompletableFuture<UnaryOperator<T>>> getParallelDetailProduct(T product, ExecutorService executorService);

  default CompletableFuture<T> getProductDetail(final T product, final ExecutorService executorService) {
    final List<CompletableFuture<UnaryOperator<T>>> parallelDetailProduct = this.getParallelDetailProduct(product, executorService);
    final CompletableFuture<List<UnaryOperator<T>>> sequence = CompletableFutureUtil.sequence(parallelDetailProduct, executorService);
    final Function<List<UnaryOperator<T>>, T> merge = items -> items.stream().reduce(product, (p, unary) -> unary.apply(p), (a, b) -> a);
    return CompletableFutureUtil.map(sequence, merge, executorService);
  }
}
