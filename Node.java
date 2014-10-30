// Alex von Hafften
// Fall Semester 2014
// As of October 30, 2014

/**
* This class is the node object used in HashTable.java. This
* class was produced using the pseudo code from Introduction to Algorithms
* [Third Edition] by Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest,
* and Clifford Stein. Published by the MIT Press, Copyrighted in 2009.
*
* @author Alex von Hafften
*/
public class Node {

	private int key;
	private Node next;

	/**
	 * Constructs a simple Node with a key and a next.
	 * Next is default null.
	 */
	public Node(int key){
		this.key = key;
		next = null;
	}

	/**
	 * Constructs a simple Node with a key and a next.
	 */
	public Node(int key, Node next) {
		this.key = key;
		this.next = next;
	}

	/**
	 * Getter for key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Setter for key
	 */
	public void setKey(int key) {
		this.key = key;
	}

	/**
	 * Getter for next field.
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Setter for next field.
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * Returns true if a Node doesn't point to another Node.
	 * Returns false if a Node points to another Node.
	 */
	public boolean hasNext() {
		return getNext() != null;
	}

	/**
	 * Returns String representation of a Node.
	 * A Node with a key of 4 that points to another non-null node, prints "4 -> "
	 * A Node with a key of 4 that doesn't point to a Node, prints "4 -> null".
	 */
	public String toString() {
		String result = key + " -> ";
		if (next == null) {
			result += "null";
		}
		return result;
	}
}
