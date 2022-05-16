package edu.miracosta.cs113;

import java.io.*;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable {
	//INNER CLASS
	protected static class Node<E> implements Serializable {
		protected E data;
		protected Node<E> left;
		protected Node<E> right;
		
		public Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public String toString() {
			return data.toString();
		}
	}
	
	//FIELDS
	protected Node<E> root;
	
	//CONSTRUCTORS
	//No parameters
	public BinaryTree() {
		root = null;
	}
	
	//Creates a tree with a given node at the root
	protected BinaryTree(Node<E> root) {
		this.root = root;
	}
	
	//Builds a tree from a data value and two trees
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<E>(data);
		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	}
	
	/**
	 * The left subtree is returned.
	 * @return BinaryTree<E>	
	 */
	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null) {
			return new BinaryTree<E>(root.left);
		} else {
			return null;
		}
	}
	
	/**
	 * The right subtree is returned.
	 * @return BinaryTree<E>	
	 */
	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right != null) {
			return new BinaryTree<E>(root.right);
		} else {
			return null;
		}
	}
	
	/**
	 * The data stored at the binary tree's root is returned.
	 * @return E	
	 */
	public E getData() {
		return root.data;
	}
	
	/**
	 * Returns boolean true if current node is a leaf node, else returns false.
	 * @return boolean	
	 */
	public boolean isLeaf() {
		return (root.left == null && root.right == null);
	}
	
	/**
	 * Displays preorder traversal of binary tree in string format.
	 * @return String	
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	/**
	 * Performs a preorder traversal.
	 * @param node	The local root
	 * @param depth	The depth
	 * @param sb	The StringBuilder to save the output
	 */
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append(" ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString() + "\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
	
	/**
	 * Method to read a binary tree.
	 * @param scan	Scanner attached to the input file.
	 * @return BinaryTree<E>  The binary tree.	
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
		String data = scan.next();
		if (data.equals("null")) {
			return null;
		} else {
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			return new BinaryTree<String>(data, leftTree, rightTree);
		}
	}

}
