package com.xabe.binarytree;

public class TreeNode {

  private final TreeNode left;

  private final TreeNode right;

  public TreeNode(final TreeNode left, final TreeNode right) {
    this.left = left;
    this.right = right;
  }

  public TreeNode() {
    this(null, null);
  }

  public int itemCheck() {
    // if necessary deallocate here
    if (null == left) {
      return 1;
    }
    return 1 + left.itemCheck() + right.itemCheck();
  }

}
