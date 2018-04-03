//Mohammed Awan 2/6/17 HHB 5037
package BinarySearchTree;

public class BST {

	public static Node root;
	public BST(){
		BST.root = null;
	}

	/*--------------Node insertion-------------------*/
	public void add(int key){
		Node node = new Node(key);
		Node nptr = null;

		//If the root is null then make the new node the root
		if (root == null){
			root = node;
			return;
		}
		//Is the root isn't null then go through the nodes
		//one-by-one checking if the new node is smaller or
		//larger until you get to the bottom of the tree
		else{
			nptr = root;
			Node nptr_parent = root;

			while(nptr != null){
				nptr_parent = nptr;
				if(nptr.key >= key){
					nptr = nptr.leftChild;
					if(nptr == null){
						nptr_parent.leftChild = node;
					}
				}
				else{
					nptr = nptr.rightChild;
					if(nptr == null){
						nptr_parent.rightChild = node;
					}
				}
			}
		}
	}

	/*--------------Inorder Tree traversal-------------------*/
	public void InTraverse(Node root){
		if(root != null){
			InTraverse(root.leftChild);
			System.out.print("-->"+ root.key);
			InTraverse(root.rightChild);
		}
	}
	/*--------------Preorder Tree traversal-------------------*/
	public void PreTraverse(Node root){
		if(root != null){
			System.out.print("-->"+ root.key);
			PreTraverse(root.leftChild);
			PreTraverse(root.rightChild);
		}
	}
	/*--------------PostOrderTree traversal-------------------*/
	public void PostTraverse(Node root){
		if(root != null){
			PostTraverse(root.leftChild);
			PostTraverse(root.rightChild);
			System.out.print("-->"+ root.key);
		}
	}

	/*--------------Node Deletion-------------------*/
	public void delete(int key){

		Node nptr = root;
		Node nptr_parent = root;
		//I added two objects to make it easier to move around the successor
		Node nptr_successor = root;
		Node nptr_successor_parent = root;

		while(nptr.key != key){
			nptr_parent = nptr;
			if(nptr.key > key){
				nptr = nptr.leftChild;
			}
			else if(nptr.key < key){
				nptr = nptr.rightChild;
			}
			if(nptr  == null){
				System.out.println("key is not found.");
				break;
			}
		}

		if(nptr != null){
			System.out.println("Key is found for deletion.");
			System.out.println("nptr.key : " + nptr.key);
			System.out.println("nptr_parent.key : " + nptr_parent.key);

			// CASE 1: If the node has no child
			if(nptr.leftChild == null && nptr.rightChild == null){
				if(nptr == root){
					root = null;
					nptr = null;
					nptr_parent = null;
				}
				if(nptr_parent.rightChild == nptr){
					nptr_parent.rightChild = null;
				}
				else if(nptr_parent.leftChild == nptr){
					nptr_parent.leftChild = null;
				}

			}

			// CASE 2: If the node has only one child
			if(nptr.leftChild != null && nptr.rightChild == null || nptr.leftChild == null && nptr.rightChild != null){
				//If the root only has one child and we want to delete the root
				//then the child becomes the root
				if(nptr == root){
					if(nptr.leftChild != null){
						root = nptr.leftChild;
					}
					else
						root = nptr.rightChild;
				}
				//Two cases for if the node we want to delete is
				//the left or right child
				if(nptr_parent.leftChild == nptr){
					if(nptr.rightChild != null){
						nptr_parent.leftChild = nptr.rightChild;
						nptr.rightChild = null;
					}
					else{
						nptr_parent.leftChild = nptr.leftChild;
						nptr.leftChild = null;
					}

				}
				else if(nptr_parent.rightChild == nptr){
					if(nptr.rightChild != null){
						nptr_parent.rightChild = nptr.rightChild;
						nptr.rightChild = null;
					}
					else{
						nptr_parent.rightChild = nptr.leftChild;
						nptr.leftChild = null;
					}

				}

			}
			// CASE 3: If the node has two children
			if (nptr.leftChild != null && nptr.rightChild != null) {
				nptr_successor = successor(nptr);
				if(nptr.rightChild != successor(nptr)) {
					nptr_successor_parent = nptr.rightChild;
				}

				//If the node we want to remove is the root node
				if (nptr == root) {
					//Created two new variable t use to remove the successor
					//and use it to replace the node we want to delete
					if (nptr.rightChild != nptr_successor){
						while(nptr_successor_parent.leftChild != nptr_successor) {
							nptr_successor_parent = nptr_successor_parent.leftChild;
						}
						if(nptr_successor.rightChild != null){
							nptr_successor_parent.leftChild = nptr_successor.rightChild;
							nptr_successor.leftChild = root.leftChild;
							nptr_successor.rightChild = root.rightChild;
							root.leftChild = null;
							root.rightChild = null;
							root = nptr_successor;
						} else {
							nptr_successor_parent.leftChild = null;
							nptr_successor.leftChild = root.leftChild;
							nptr_successor.rightChild = root.rightChild;
							root.leftChild = null;
							root.rightChild = null;
							root = nptr_successor;
						}
					}
					//If the root node's child doesn't have any left children then
					//we have to use different logic
					else {
						nptr_successor.leftChild = root.leftChild;
						root.leftChild = null;
						root.rightChild = null;
						root = nptr_successor;
					}
				}
				//In this case the node we want to delete is the right child of it's parent
				if (nptr_parent.rightChild == nptr){
					if (nptr.rightChild != nptr_successor) {
						while (nptr_successor_parent.leftChild != nptr_successor) {
							nptr_successor_parent = nptr_successor_parent.leftChild;
						}
						//This is to determine if the successor has any children or not
						//and what to do with them in wach case
						System.out.println("b");
						if (nptr_successor.rightChild != null) {
							nptr_successor_parent.leftChild = nptr_successor.rightChild;
							nptr_successor.leftChild = nptr.leftChild;
							nptr_successor.rightChild = nptr.rightChild;
							nptr.leftChild = null;
							nptr.rightChild = null;
							nptr = nptr_successor;
							nptr_parent.rightChild = nptr_successor;
						} else {
							nptr_successor_parent.leftChild = null;
							nptr_successor.leftChild = nptr.leftChild;
							nptr_successor.rightChild = nptr.rightChild;
							nptr.leftChild = null;
							nptr.rightChild = null;
							nptr = nptr_successor;
							nptr_parent.rightChild = nptr_successor;
						}
					}
					else {
						nptr.leftChild = nptr_successor.leftChild;
						nptr_parent.rightChild = nptr_successor;
						nptr.leftChild = null;
					}
				}
				//In this case the node we want to delete is the left child of it's parent
				else if(nptr_parent.leftChild == nptr){
						//nptr_successor = successor(nptr);
						//nptr_successor_parent = nptr.rightChild;
					if (nptr.rightChild != nptr_successor) {
						while (nptr_successor_parent.leftChild != nptr_successor) {
							nptr_successor_parent = nptr_successor_parent.leftChild;
						}
						if (nptr_successor.rightChild != null) {
							nptr_successor_parent.leftChild = nptr_successor.rightChild;
							nptr_successor.leftChild = nptr.leftChild;
							nptr_successor.rightChild = nptr.rightChild;
							nptr.leftChild = null;
							nptr.rightChild = null;
							nptr = nptr_successor;
							nptr_parent.leftChild = nptr_successor;
						} else {
							nptr_successor_parent.leftChild = null;
							nptr_successor.leftChild = nptr.leftChild;
							nptr_successor.rightChild = nptr.rightChild;
							nptr.leftChild = null;
							nptr.rightChild = null;
							nptr = nptr_successor;
							nptr_parent.leftChild = nptr_successor;
						}
					}
					else {
						nptr.leftChild = nptr_successor.leftChild;
						nptr_parent.leftChild = nptr_successor;
						nptr.leftChild = null;
					}

				}



			}
		}


	}

	//This function finds the closest node larger than the one entered
	public Node successor(Node x){
		Node nptr_parent = x.rightChild;
		Node nptr = nptr_parent;
		while(nptr.leftChild != null){
			nptr_parent = nptr;
			nptr = nptr.leftChild;
		}
		nptr_parent = null;
		return nptr;
	}
	//This function finds the closest node smaller than the one entered
	public Node predecessor(Node x){
		Node nptr_parent = x.leftChild;
		Node nptr = nptr_parent;
		while(nptr.rightChild != null){
			nptr_parent = nptr;
			nptr = nptr.rightChild;
		}
		nptr_parent = null;
		return nptr;
	}
}
