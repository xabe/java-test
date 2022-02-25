package com.xabe.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.xabe.completablefuture.CompletableFutureUtil;
import org.slf4j.Logger;

public class BinaryTree {

  private final Logger logger;

  private static final int MIN_DEPTH = 4;

  private final ExecutorService executorService;

  public BinaryTree(Logger logger) {
    this.logger = logger;
    this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }

  public List<String> search(final int init_depth) throws ExecutionException, InterruptedException {
    final int maxDepth = Math.max(init_depth, (MIN_DEPTH + 2));
    final int stretchDepth = maxDepth + 1;

    logger.info("stretch tree of depth {} check: {}", stretchDepth, bottomUpTree(stretchDepth).itemCheck());

    final TreeNode longLivedTree = bottomUpTree(maxDepth);

    final int initialCapacity = Math.max(10, (maxDepth - MIN_DEPTH) / 2 + 1);
    final List<String> results = new ArrayList<>(initialCapacity);
    final List<CompletableFuture<Void>> completableFutureList = new ArrayList<>(initialCapacity);
    for (int d = MIN_DEPTH; d <= maxDepth; d += 2) {
      final int depth = d;
      completableFutureList.add(CompletableFuture.runAsync(() -> {
        int check = 0;

        final int iterations = 1 << (maxDepth - depth + MIN_DEPTH);
        for (int i = 1; i <= iterations; ++i) {
          final TreeNode treeNode1 = bottomUpTree(depth);
          check += treeNode1.itemCheck();
        }
        final int i = (depth - MIN_DEPTH) / 2;
        results.add(i, iterations + " trees of depth " + depth + " check: " + check);
      }, executorService));
    }
    CompletableFutureUtil.sequence(completableFutureList, executorService).get();
    results.forEach(logger::info);
    logger.info("long lived tree of depth {} check: {}", maxDepth, longLivedTree.itemCheck());
    return results;
  }

  private TreeNode bottomUpTree(final int depth) {
    if (0 < depth) {
      return new TreeNode(bottomUpTree(depth - 1), bottomUpTree(depth - 1));
    }
    return new TreeNode();
  }

  public void shutdown() throws InterruptedException {
    executorService.shutdown();
    executorService.awaitTermination(120L, TimeUnit.SECONDS);
  }
}
