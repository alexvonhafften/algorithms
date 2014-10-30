// Alex von Hafften
// Fall Semester 2014
// As of October 30, 2014

/**
* This class is an implementation of an open-address hash table. This
* class was produced using the pseudo code from Introduction to Algorithms
* [Third Edition] by Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest,
* and Clifford Stein. Published by the MIT Press, Copyrighted in 2009.
*
* @author Alex von Hafften
*/
public class HashTableOA {

	private Integer[] table;

	// the specified values for quadratic probing
	private final int C1 = 1;
	private final int C2 = 3;

	private final int DELETED_VALUE = Integer.MIN_VALUE;

	/**
	 *  creates an Open Address Hash Table of a specified size
	 */
	public HashTableOA(int size) {
		table = new Integer[size];
	}

	/**
	 * Array version of insert()
	 * @param type of probing: 'l' for linear probing, 'q' for quadratic probing, 'd' for double hashing
	 */
	public void insert(int[] keys, char type) {
		for (int i = 0; i < keys.length; i++) {
			insert(keys[i], type);
		}
	}

	/**
	 * inserts key into hash table using a specified type of probing
	 * @param type of probing: 'l' for linear probing, 'q' for quadratic probing, 'd' for double hashing
	 */
	public void insert(int key, char type) {
		int i = 0;
		int j;
		while (true) {
			j = chooseJ(key, i, type);
			if ((table[j] == null)||(table[j] == DELETED_VALUE)) {
				table[j] = key;
				return;
			}
			i++;
			if (i==table.length) {
				System.out.println("---------------------------");
				System.out.println("ERROR: Hash Table overflow.");
				System.out.println("---------------------------");
				return;
			}
		}
	}

	/**
	 * Searches for a key in the hash table
	 * @param type of probing: 'l' for linear probing, 'q' for quadratic probing, 'd' for double hashing
	 * @return the index of where the key is found and -1 if it isn't found
	 */
	public int search(int key, char type) {
		int i = 0;
		int j;
		while (true) {
			j = chooseJ(key, i, type);
			i++;
			if ((table[j]==null)||(i==table.length)) {
				return -1;
			}
			if (table[j] == key) {
				return j;
			}
		}
	}

	/**
	 * Deletes key from the hash table.
	 * Doesn't actually set the key to null, but to the hard coded DELETED_VALUE.
	 * insert() will insert a key over the deleted value.
	 * @param key
	 * @param type
	 */
	public void delete(int key, char type) {
		table[search(key, type)] = DELETED_VALUE;
	}

	/**
	 * This function takes a key, an index, and a char which determine the probing to use
	 * @param key to be inputed in the hash table
	 * @param i is an index
	 * @param type of probing: 'l' for linear probing, 'q' for quadratic probing, 'd' for double hashing
	 * @return
	 */
	public int chooseJ(int key, int i, char type) {
		int j = -1;
		if (type == 'l') {
			j = linearProbing(key, i);
		} else if (type == 'q') {
			j = quadraticProbing(key, i);
		} else if (type == 'd') {
			j = doubleHash(key, i);
		} else {
			System.out.println("-------------------------------------------");
			System.out.println("ERROR: Incorrect specified type of probing.");
			System.out.println("-------------------------------------------");
		}
		return j;
	}

	/**
	 * implements linear probing
	 */
	public int linearProbing(int key, int i) {
		return (auxHash(key) + i) % table.length;
	}

	/**
	 * implements quadratic probing
	 */
	public int quadraticProbing(int key, int i) {
		return (auxHash(key) + C1*i + C2*i*i) % table.length;
	}

	/**
	 * implements double hashing
	 */
	public int doubleHash(int key, int i) {
		return (h1(key) + i*h2(key)) % table.length;
	}

	/**
	 * the specified auxiliary hashing function
	 * h'(k) = k
	 */
	public int auxHash(int key) {
		return key;
	}

	/**
	 * first of the specified double hashing functions
	 * h1(k) = k
	 */
	public int h1(int key) {
		return key;
	}

	/**
	 * first of the specified double hashing functions
	 * h2(k) = 1 + (k mod (m - 1))
	 */
	public int h2(int key) {
		return 1 + (key % (table.length - 1));
	}

	/**
	 * Return string representation of an open address hash table
	 */
	public String toString() {
		String result = "";
		for(int i = 0; i < table.length; i++) {
			result += table[i] + "\n";
		}
		return result;
	}
}
