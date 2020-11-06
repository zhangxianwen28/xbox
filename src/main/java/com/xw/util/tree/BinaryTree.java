package com.xw.util.tree;

public class BinaryTree {
	// 根节点指针。对于空树来说，将是null。
	private Node root;
	
	/**
	 * 节点 二进制树是使用这个嵌套的节点类构建的。 每个节点存储一个数据元素，并且已经左右了 子树指针，可能为空。
	 * 这个节点是一个“愚蠢”的嵌套类——我们只是用它来做 存储;它没有任何方法。
	 */
	private static class Node {
		Node left;
		Node right;
		int data;

		Node(int newData) {
			left = null;
			right = null;
			data = newData;
		}
	}

	/**
	 * 创建一个空的二叉树—一个空根指针。
	 */
	public void BinaryTree() {
		root = null;
	}

	/**
	 * 如果给定的目标位于二进制树中，则返回true。 使用递归
	 */
	public boolean lookup(int data) {
		return (lookup(root, data));
	}

	/**
	 * 递归查找——给定一个节点，重现 向下搜索给定的数据。
	 */
	private boolean lookup(Node node, int data) {
		if (node == null) {
			return (false);
		}

		if (data == node.data) {
			return (true);
		} else if (data < node.data) {
			return (lookup(node.left, data));
		} else {
			return (lookup(node.right, data));
		}
	}

	/**
	 * 将给定的数据插入到二叉树中。 使用递归的帮手。
	 */
	public void insert(int data) {
		root = insert(root, data);
	}

	/**
	 * 递归插入——给定一个节点指针，再次出现 将给定的数据插入到树中。返回新的 节点指针(用于通信的标准方法 将指针更改回调用者)。
	 */
	private Node insert(Node node, int data) {
		if (node == null) {
			node = new Node(data);
		} else {
			if (data <= node.data) {
				node.left = insert(node.left, data);
			} else {
				node.right = insert(node.right, data);
			}
		}

		return (node); // 在任何情况下，将新指针返回给调用者
	}

	// 获得树的高度

	/**
	 * 返回树的最大叶节点深度。 使用递归助手，递归查找 的最大深度。
	 */
	public int maxDepth() {
		return (maxDepth(root));
	}

	private int maxDepth(Node node) {
		if (node == null) {
			return (0);
		} else {
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);

			// use the larger + 1
			return (Math.max(lDepth, rDepth) + 1);
		}
	}
	// 获得最小值

	/**
	 * 在非空的二叉搜索树中返回最小值。 使用一个助手方法，迭代到左边来查找 最小值。
	 */
	public int minValue() {
		return (minValue(root));
	}

	/**
	 * 在一个非空的二叉搜索树中找到最小值。
	 */
	private int minValue(Node node) {
		Node current = node;
		while (current.left != null) {
			current = current.left;
		}

		return (current.data);
	}
	public static void main(String[] args) {
		BinaryTree b = new BinaryTree();
		b.insert(1);
		b.insert(1);
		b.insert(2);
		b.insert(4);
	}
}