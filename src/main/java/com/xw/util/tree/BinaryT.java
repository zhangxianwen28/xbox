package com.xw.util.tree;
// 下面给出二叉树的代码实现。由于在二叉树中进行节点的删除非常繁琐，因此，下面的代码使用的是利用节点的isDelete字段对节点的状态进行标识

/**
 * 
 * @ClassName: com.tree.Tree
 * @Description: 二叉树的定义
 * @author zhaokaiqiang
 * @date 2014-12-8 上午7:51:49
 * 
 */

public class BinaryT {

	// 根节点
	private TreeNode root;
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * 插入操作
	 * 
	 * @param value
	 */
	public void insert(int value) {

		TreeNode newNode = new TreeNode(value);

		if (root == null) {
			root = newNode;
			root.setLefTreeNode(null);
			root.setRightNode(null);
		} else {

			TreeNode currentNode = root;
			TreeNode parentNode;

			while (true) {
				parentNode = currentNode;
				// 往右放
				if (newNode.getValue() > currentNode.getValue()) {
					currentNode = currentNode.getRightNode();
					if (currentNode == null) {
						parentNode.setRightNode(newNode);
						return;
					}
				} else {
					// 往左放
					currentNode = currentNode.getLefTreeNode();
					if (currentNode == null) {
						parentNode.setLefTreeNode(newNode);
						return;
					}

				}
			}
		}
	}

	/**
	 * 查找
	 * 
	 * @param key
	 * @return
	 */
	public TreeNode find(int key) {

		TreeNode currentNode = root;

		if (currentNode != null) {

			while (currentNode.getValue() != key) {

				if (currentNode.getValue() > key) {
					currentNode = currentNode.getLefTreeNode();
				} else {
					currentNode = currentNode.getRightNode();
				}

				if (currentNode == null) {
					return null;
				}

			}

			if (currentNode.isDelete()) {
				return null;
			} else {
				return currentNode;
			}

		} else {
			return null;
		}

	}

	/**
	 * 中序遍历
	 * 
	 * @param treeNode
	 */
	public void inOrder(TreeNode treeNode) {
		if (treeNode != null && treeNode.isDelete() == false) {
			inOrder(treeNode.getLefTreeNode());
			System.out.println("--" + treeNode.getValue());
			inOrder(treeNode.getRightNode());
		}
	}
	/*
	 * 在上面对二叉树的遍历操作中，使用的是中序遍历，这样遍历出来的数据是增序的。
	 * 
	 * 下面是测试代码:
	 */
	public static void main(String[] args) {
		

			BinaryT tree = new BinaryT();
			// 添加数据测试
			tree.insert(10);
			tree.insert(40);
			tree.insert(20);
			tree.insert(3);
			tree.insert(49);
			tree.insert(13);
			tree.insert(123);

			System.out.println("root=" + tree.getRoot().getValue());
			// 排序测试
			tree.inOrder(tree.getRoot());
			// 查找测试
			if (tree.find(10) != null) {
				System.out.println("找到了");
			} else {
				System.out.println("没找到");
			}
			// 删除测试
			tree.find(40).setDelete(true);

			if (tree.find(40) != null) {
				System.out.println("找到了");
			} else {
				System.out.println("没找到");
			}

		}
}
