//Mohammed Awan 2/6/17 HHB 5037
package BinarySearchTree;

import BinarySearchTree.BST;

public class BST_main {

	public static void main(String [] args){

		BST tree = new BST();

		System.out.println("------Performing 1st set of operation i.e. Adding, traversing and getting size of the tree-------");
		System.out.println("1 has been added to the tree");
		tree.add(39);
		System.out.print("Traversing in the tree: ");
		tree.InTraverse(tree.root);
		System.out.println();
		System.out.println("------------------X----------X--------------");

		System.out.println("------Performing 2nd set of operation i.e. Adding, traversing and getting size of the tree-------");
		System.out.println("Adding nodes have been done");
		tree.add(17);
		tree.add(43);
		tree.add(12);
		tree.add(25);
		tree.add(47);
		tree.add(10);
		tree.add(13);
		tree.add(20);
		tree.add(29);
		tree.add(7);
		tree.add(19);
		tree.add(22);
		tree.add(23);
		System.out.print("Traversing in the tree: ");
		tree.InTraverse(tree.root);
		System.out.println();
		System.out.println("------------------X----------X--------------");
		System.out.print("Traversing in the tree: ");
		System.out.println();
		tree.delete(10);
		tree.delete(10);
		System.out.println("------------------X----------X--------------");
		System.out.print("Traversing in the tree: ");
		tree.InTraverse(tree.root);

	}
}
