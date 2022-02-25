package com.xabe.binarytree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

public class BinaryTreeTest {

  private BinaryTree binaryTree;

  @BeforeEach
  public void setUp() throws Exception {
    this.binaryTree = new BinaryTree(LoggerFactory.getLogger(BinaryTree.class));
  }

  @AfterEach
  public void setDown() throws Exception {
    binaryTree.shutdown();
  }

  @Test
  public void searchBinaryTree() throws Exception {
    //Given

    //When
    final List<String> result = binaryTree.search(21);

    //Then
    assertThat(result, is(notNullValue()));
  }

}
