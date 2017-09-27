/**
 * 
 * @author Pei-li Kuo
 * @since Sept. 27 2017
 * @param <Key>
 * @param <Value>
 * 
 *            Using only primitive types, implement a fixed-size hash map that
 *            associates string keys with arbitrary data object references. Data
 *            structure is optimized for algorithmic runtime and memory usage.
 *            Do not import any external libraries, and may not use primitive
 *            hash map.
 */

public class HashMap2<Key, Value> {

	// private instance
	private int size; // total size of hash map
	private int count; // current number of elements
	private Key[] keys; // arrays to store keys
	private Value[] map; // arrays to store mapping value with same index in
							// keys
	private final int INDEX_NOTFOUND = -1; // index value for not found related
											// key
	private final int DEFAULT_SIZE = 10000;

	/**
	 * return an instance of the class with pre-allocated space for a default
	 * number of objects.
	 */
	public HashMap2() {
		count = 0;
		this.size = DEFAULT_SIZE;
		keys = (Key[]) new Object[DEFAULT_SIZE];
		map = (Value[]) new Object[DEFAULT_SIZE];
	}

	/**
	 * return an instance of the class with pre-allocated space for the given
	 * number of objects.
	 */
	public HashMap2(int size) {
		count = 0;
		this.size = size;
		keys = (Key[]) new Object[size];
		map = (Value[]) new Object[size];
	}

	/**
	 * stores the given key/value pair in the hash map.
	 * 
	 * @param key
	 * @param value
	 * @return a boolean value indicating success / failure of the operation.
	 */
	public boolean set(Key key, Value value) {
		int index = findIndexByKey(key);
		if (index == INDEX_NOTFOUND && count == size)
			return false;
		if (index == INDEX_NOTFOUND) {
			index = key.hashCode() % size;
			while (keys[index] != null && map[index] != null) {
				index++;
				if (index == size)
					index = 0;
			}
			keys[index] = key;

			count++;
		}
		map[index] = value;
		return true;
	}

	/**
	 * derive value with the given key
	 * 
	 * @param key
	 * @return the value associated with the given key, or null if no value is
	 *         set.
	 */
	public Value get(Key key) {
		int index = findIndexByKey(key);
		if (index == INDEX_NOTFOUND)
			return null;
		return map[index];

	}

	/**
	 * delete the value associated with the given key
	 * 
	 * @param key
	 * @return the value on success or null if the key has no value.
	 */
	public Value delete(Key key) {
		int index = findIndexByKey(key);
		if (index >= 0 && map[index] != null) {
			count--;
			Value val = map[index];
			map[index] = null;
			return val;
		}
		return null;

	}

	/**
	 * find load factor of the data structure. Since the size of the dat
	 * structure is fixed, this should never be greater than 1.
	 * 
	 * @return value representing the load factor ((items in hash map)/(size of
	 *         hash map)
	 */
	public float load() {
		return (float) count / size;
	}

	/**
	 * find the index in the keys array with a given key
	 * 
	 * @param key
	 * @return index of the given key, if not exist, return INDEX_NOTFOUN
	 */
	private int findIndexByKey(Key key) {
		int index = key.hashCode() % size;
		while (keys[index] != null) {
			if (keys[index].equals(key))
				return index;
			index++;
			if (index == size)
				index = 0;
		}
		return INDEX_NOTFOUND;
	}
}
