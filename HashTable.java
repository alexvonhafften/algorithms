// Alex von Hafften
// Fall Semester 2014
// As of October 30, 2014

/**
* This class is an implementation of an hash table. This
* class was produced using the pseudo code from Introduction to Algorithms
* [Third Edition] by Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest,
* and Clifford Stein. Published by the MIT Press, Copyrighted in 2009.
*
* @author Alex von Hafften
*/
public class HashTable {

	private Node[] table;

	/**
	 * Constructs new HashTable of the specified size.
	 */
	public HashTable(int size) {
		table = new Node[size];
		for (int i = 0; i < size; i++) {
			// The first Node at each indices of the HashTable is the value of the index.
			table[i] = new Node(i);
		}
	}

	/**
	 * Array version of addsKeyModSize(). Takes array and adds in order.
	 */
	public void addsKeyModSize(int[] keys) {
		for (int i = 0; i < keys.length; i++) {
			addsKeyModSize(keys[i]);
		}
	}

	/**
	 * Creates a Node with a specified key to the HashTable.
	 * Adds Node using a hash function of h(key) = key mod sizeOfHashTable.
	 * Ex. A new Node with a key = 25 is added to a HashTable of size 9 at 25 mod 9 = 7.
	 */
	public void addsKeyModSize(int key) {
		addsKeyAtI(key, key % table.length);
	}

	/**
	 * Adds a Node with a specified key to the end of the list of Nodes at i in the HashTable.
	 */
	public void addsKeyAtI(int key, int i) {
		// Prints error message if i is outside of the size of the HashTable
		if ((i >= table.length)||(i < 0)) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("ERROR: tried to add an element outside of the size of the table.");
			System.out.println("----------------------------------------------------------------");
			return;
		}
		Node a = new Node(key);
		Node b = table[i];
		// Finds the last Node stored at index i
		while (b.hasNext() == true) {
			b = b.getNext();
		}
		b.setNext(a);
	}

	/**
	 * Searches for a node with the specified key in the HashTable.
	 * Return true if key is found. False if otherwise.
	 */
	public boolean isKeyHere(int key) {
		boolean result = false;
		int i = key % table.length;
		Node a = table[i];
		while (a.hasNext() == true) {
			if (a.getNext().getKey() == key) {
				result = true;
			}
			a = a.getNext();
		}
		return result;
	}

	/**
	 * Deletes first Node added with specified key.
	 * If Node with specified key is not found, prints error message.
	 */
	public void delete(int key) {
		if (isKeyHere(key) == false) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("ERROR: The node to be deleted doesn't exist in the HashTable.");
			System.out.println("-------------------------------------------------------------");
			return;
		}
		int i = key % table.length;
		Node a = table[i];
		while (a.hasNext() == true) {
			if (a.getNext().getKey() == key) {
				a.setNext(a.getNext().getNext());
				return;
			}
			a = a.getNext();
		}
	}

	/**
	 * Returns String representation of a HashTable.
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < table.length; i++) {
			result += table[i].toString();
			Node a = table[i];
			while (a.hasNext() == true) {
				result += a.getNext().toString();
				a = a.getNext();
			}
			result += "\n";
		}
		return result;
	}
}
