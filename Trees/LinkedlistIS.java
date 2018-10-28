package Trees;

import Entidades.Dragon;
import Logica.Game;
import disparos.ControlDisparo;

public class LinkedlistIS<T> {
	public Dragon head, sorted, root;
	int size;
	private Game game;
	ServerDragons server = new ServerDragons(game);
	ControlDisparo cd = new ControlDisparo(game);

	public LinkedlistIS() {
		this.head = null;
		this.size = 0;
	}

	public void validate_parent(LinkedlistIS dragons) {
		for (int i = 0; i < dragons.getSize(); i++) {
			if (dragons.getNode(i).parent == null) {
				dragons.getNode(i).parent = server.setparent();
			}
		}
	}

	public Dragon getNode(int index) {
		Dragon current = head;
		if (index < size) {
			for (int j = 0; j < size; j++) {
				if (index == j) {
					return current;
				} else {
					current = current.next;
				}
			}
		}
		return null;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	void push(Dragon newnode) {
		newnode.next = head;
		head = newnode;
		size++;
	}

	// function to sort a singly linked list using insertion sort
	public void insertionSort(Dragon headref) {
		// Initialize sorted linked list
		sorted = null;
		Dragon current = headref;
		// Traverse the given linked list and insert every
		// node to sorted
		while (current != null) {
			// Store next for next iteration
			Dragon next = current.next;
			// insert current in sorted linked list
			sortedInsert(current);
			// Update current
			current = next;
		}
		// Update head_ref to point to sorted linked list
		head = sorted;
	}

	void sortedInsert(Dragon current2) {
		/* Special case for the head end */
		if (sorted == null || (int) sorted.getVelocidad() >= (int) current2.getVelocidad()) {
			current2.next = sorted;
			sorted = current2;
		} else {
			Dragon current = sorted;
			/* Locate the node before the point of insertion */
			while (current.next != null && (int) current.next.getVelocidad() < (int) current2.getVelocidad()) {
				current = current.next;
			}
			current2.next = current.next;
			current.next = current2;
		}
	}

	void printlist(Dragon head) {
		while (head != null) {
			System.out.print(head.getEdad() + " ");
			head = head.next;
		}
	}

	Dragon lastNode(Dragon node) {
		while (node.next != null)
			node = node.next;
		return node;
	}

	Dragon partition(Dragon l, Dragon h) {
		// set pivot as h element
		int x = (int) h.getEdad();

		// similar to i = l-1 for array implementation
		Dragon i = l.prev;

		// Similar to "for (int j = l; j <= h- 1; j++)"
		for (Dragon j = l; j != h; j = j.next) {
			if ((int) j.getEdad() <= x) {
				// Similar to i++ for array
				i = (i == null) ? l : i.next;
				int temp = (int) i.getEdad();
				i.setEdad(j.getEdad());
				j.setEdad(temp);
			}
		}
		i = (i == null) ? l : i.next; // Similar to i++
		int temp = (int) i.getEdad();
		i.setEdad(h.getEdad());
		h.setEdad(temp);
		return i;
	}

	void _quickSort(Dragon l, Dragon h) {
		if (h != null && l != h && l != h.next) {
			Dragon temp = partition(l, h);
			_quickSort(l, temp.prev);
			_quickSort(temp.next, h);
		}
	}

	public void quickSort(Dragon node) {
		// Find last node
		Dragon head = lastNode(node);

		// Call the recursive QuickSort
		_quickSort(node, head);
	}

	public void selectionSort(Dragon head) {
		for (Dragon node1 = head; node1 != null; node1 = node1.getNext()) {
			Dragon min = node1;
			for (Dragon node2 = node1; node2 != null; node2 = node2.getNext()) {
				if (min.getEdad() > node2.getEdad()) {
					min = node2;
				}

			}
			ControlDisparo cd = new ControlDisparo(game);
			Dragon temp = new Dragon(game, node1.getID(), node1.getClase(), cd, node1.getParent(), node1.getX(),
					node1.getY(), node1.getEdad());
			node1.setEdad(min.getEdad());
			min.setEdad(temp.getEdad());
		}
	}

	int height(Dragon N) {
		if (N == null)
			return 0;

		return (int) N.height;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// A utility function to right rotate subtree rooted with y
	// See the diagram given above.
	Dragon rightRotate(Dragon y) {
		Dragon x = y.left;
		Dragon T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		// Return new root
		return x;
	}

	// A utility function to left rotate subtree rooted with x
	// See the diagram given above.
	Dragon leftRotate(Dragon x) {
		Dragon y = x.right;
		Dragon T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		// Return new root
		return y;
	}

	// Get Balance factor of node N
	int getBalance(Dragon N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	public void insertarAux(LinkedlistIS dragon) {
		for (int i = 0; i == dragon.size; i++) {
			insert(dragon.root, dragon.getNode(i).getVelocidad(), dragon.getNode(i).getEdad(),
					dragon.getNode(i).getSalud(), dragon.getNode(i).getID(), dragon.getNode(i).getParent(),
					dragon.getNode(i).getX(), dragon.getNode(i).getY(), dragon.getNode(i).getClase());
		}
	}

	public Dragon insert(Dragon node, float velocidad, int edad, int vida, String id, Dragon parent, float x, float y,
			String clase) {

		/* 1. Perform the normal BST insertion */
		if (node == null)
			return (new Dragon(game, id, clase, cd, parent, x, y, edad));

		if (edad < node.getEdad())
			node.left = insert(node.left, velocidad, edad, vida, id, parent, x, y, clase);
		else if (edad > node.getEdad())
			node.right = insert(node.right, velocidad, edad, vida, id, parent, x, y, clase);
		else // Duplicate keys not allowed
			return node;

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left), height(node.right));

		/*
		 * 3. Get the balance factor of this ancestor node to check whether this
		 * node became unbalanced
		 */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && edad < node.left.getEdad())
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && edad > node.right.getEdad())
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && edad > node.left.getEdad()) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && edad < node.right.getEdad()) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	// A utility function to print preorder traversal
	// of the tree.
	// The function also prints height of every node
	void preOrder(Dragon node) {
		if (node != null) {
			System.out.print(node.getEdad() + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	// Driver program to test above functions
	public static void main(String[] args) {
		LinkedlistIS tree = new LinkedlistIS();
		/**
		 * Node node1= new Node(10,69,8,"com","a1",tree.); //Node node2= new
		 * Node(30,2,8,"com","a2"); //Node node3= new Node(1,1,8,"com","a3");
		 * tree.root = tree.insert(tree.root,
		 * node1.speed,node1.age,node1.resistance,node1.classe,node1.name,
		 * node2); tree.root = tree.insert(tree.root,
		 * node2.speed,node2.age,node2.resistance,node2.classe,node2.name,
		 * node3); tree.root = tree.insert(tree.root,
		 * node3.speed,node3.age,node3.resistance,node3.classe,node3.name,
		 * node1); System.out.println("Preorder traversal" + " of constructed
		 * tree is : "); tree.preOrder(tree.root);
		 **/

	}
}

// This code is contributed by Rishabh Mahrsee
