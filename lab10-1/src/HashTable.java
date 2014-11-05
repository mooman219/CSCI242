import java.util.ArrayList;

/**
 * Simple hash-table implementation that uses Strings as keys and their
 * occurrences as the value.
 *
 * @author Joseph Cumbo (mooman219)
 */
public class HashTable implements Hash {

    private final Hash.Htype hashType;
    private final double LOAD_FACTOR = 0.75;
    private ArrayList<ArrayList<WorldHolder>> table;
    private int size = 0;
    private int elements = 0;

    /**
     * Initializes a new HashTable that hashes based on the given hashType and
     * has initial size.
     *
     * @param hashType which hash function this table will use.
     * @param size the initial size of this table.
     */
    public HashTable(Hash.Htype hashType, int size) {
        this.hashType = hashType;
        populate(size);
        this.size = size;
    }

    /**
     * Adds the given word to the table with a count of 1. If the word is
     * already in the table, increment its count by 1.
     *
     * @param key the word to add.
     */
    public void put(String key) {
        put(key, 1);
    }

    /**
     * Adds a given word to the table with a given count. If the word is already
     * in the table, increment its count by the given count.
     *
     * @param key the word to add.
     * @param count the count to set.
     */
    public void put(String key, int count) {
        int hash = hash(key);
        ArrayList<WorldHolder> chain = table.get(hash % size);
        for (WorldHolder value : chain) {
            if (value.word.equals(key)) {
                value.occurrences += count;
                return;
            }
        }
        elements++;
        chain.add(new WorldHolder(key, 1));
        rehash();
    }

    /**
     * Gets the count for the given key.
     *
     * @param key the key that will be tested against the HashTable.
     * @return the occurrences for the given key.
     */
    public int get(String key) {
        int hash = hash(key);
        ArrayList<WorldHolder> base = table.get(hash % size);
        if (!base.isEmpty()) {
            for (WorldHolder value : base) {
                if (value.word.equals(key)) {
                    return value.occurrences;
                }
            }
        }
        return 0;
    }

    /**
     * Calculates the imbalance in the table by dividing the total elements by
     * the total non-empty chains then subtracting 1.
     *
     * @return (Total Elements) / (Total non-empty chains) - 1.
     */
    public int imbalance() {
        int nonEmpty = 0;
        for (ArrayList<WorldHolder> chain : table) {
            if (!chain.isEmpty()) {
                nonEmpty++;
            }
        }
        System.out.println(elements + " " + nonEmpty);
        return (elements / nonEmpty) - 1;
    }

    // Creates a new table of 2*size + 1 elements and
    // rehashes the current table entries to the new
    // table. Makes the new table the hash table.'
    /**
     * Doubles the size of the has table and rehashes all the elements.
     */
    public void rehash() {
        if (elements >= size * LOAD_FACTOR) {
            elements = 0;
            size = (size * 2) + 1;
            ArrayList<ArrayList<WorldHolder>> oldTable = table;
            populate(size);
            for (ArrayList<WorldHolder> chain : oldTable) {
                if (!chain.isEmpty()) {
                    for (WorldHolder value : chain) {
                        put(value.word, value.occurrences);
                    }
                }
            }
        }
    }

    /**
     * Sets the current hash table to a new hash table with the given size.
     *
     * @param size the size of the new hash table.
     */
    private void populate(int size) {
        table = new ArrayList<ArrayList<WorldHolder>>(size);
        for (int i = 0; i < size; i++) {
            table.add(new ArrayList<WorldHolder>());
        }
    }

    /**
     * Hashes the key based on the hash-type assigned in the constructor.
     *
     * @param key the key to hash.
     * @return the hash-value for the key.
     */
    public int hash(String key) {
        int hash = 0;
        switch (hashType) {
            case CUSTOM:
                hash = customHash(key);
                break;
            default:
                hash = simpleHash(key);
                break;
        }
        return hash & Integer.MAX_VALUE; // Hack for no negative values
    }

    /**
     * Hashes the string based on the simple hash implementation.
     *
     * @param string the string to hash.
     * @return the hash value of the string.
     */
    private int simpleHash(String string) {
        int hash = 0;
        for (char character : string.toCharArray()) {
            hash += character;
        }
        return hash;
    }

    /**
     * Hashes the string based on the simple custom implementation.
     *
     * @param string the string to hash.
     * @return the hash value of the string.
     */
    private int customHash(String string) {
        int hash = 7;
        for (char character : string.toCharArray()) {
            hash += 17 * hash + (character - '0');
        }
        return hash;
    }

    /**
     * Holder bean for the word and its occurrences.
     */
    private class WorldHolder {

        private String word;
        private int occurrences;

        /**
         * Initializes a new WordHolder object with given word and occurrences.
         *
         * @param word
         * @param occurrences
         */
        public WorldHolder(String word, int occurrences) {
            this.word = word;
            this.occurrences = occurrences;
        }
    }
}
