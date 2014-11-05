import java.util.ArrayList;

/**
 * @author Joseph Cumbo (mooman219)
 */
public class HashTable implements Hash {

    private final Hash.Htype hashType;
    private final double LOAD_FACTOR = 0.75;
    private ArrayList<ArrayList<SackOfShit>> table;
    private int elements = 0;
    // Constructor is not part of the interface, but is given here
    // as guidance. 'size' is the initial size, 'type' is either
    // 'SIMPLE' or CUSTOM', which will determine which hashing
    // function the hash table will use.
    // public HashTable( int size, Htype type ) ;
    // Method to put values into the table. It will
    // create the key if it doesn't already exist and set its
    // value to 1. If the key already exists, it will increment
    // the value.

    public HashTable(Hash.Htype hashType, int size) {
        this.hashType = hashType;
        this.table = new ArrayList<ArrayList<SackOfShit>>(size);
    }

    private int simpleHash(String string) {
        int hash = 0;
        for (char character : string.toCharArray()) {
            hash += character;
        }
        return hash;
    }

    private int customHash(String string) {
        int hash = 7;
        for (char character : string.toCharArray()) {
            hash += 13 * hash + (character - '0');
        }
        return hash;
    }

    // Method to put values into the table. It will
    // create the key if it doesn't already exist and set its
    // value to 1. If the key already exists, it will increment
    // the value.
    public void put(String key) {
        put(key, 1);
    }

    // Additional put method, allows you to set
    // the value associated with a key
    public void put(String key, int count) {
        int hash = hash(key);
        ArrayList<SackOfShit> chain = table.get(hash % table.size());
        if (chain == null) {
            chain = new ArrayList<SackOfShit>();
        }
        for (SackOfShit value : chain) {
            if (value.word.equals(key)) {
                value.occurances += count;
                return;
            }
        }
        elements++;
        rehash();
        chain.add(new SackOfShit(key, 1));
    }

    // Returns the value associated with 'key' from the table
    public int get(String key) {
        int hash = hash(key);
        ArrayList<SackOfShit> base = table.get(hash % table.size());
        if (base != null) {
            for (SackOfShit value : base) {
                if (value.word.equals(key)) {
                    return value.occurances;
                }
            }
        }
        return 0;
    }

    // Returns the imbalance of the current table
    public int imbalance() {
        int nonEmpty = 0;
        for (ArrayList<SackOfShit> chain : table) {
            if (chain != null) {
                nonEmpty++;
            }
        }
        return (elements / nonEmpty) - 1;
    }

    // Creates a new table of 2*size + 1 elements and
    // rehashes the current table entries to the new
    // table. Makes the new table the hash table.
    public void rehash() {
        if (elements >= table.size() * LOAD_FACTOR) {
            elements = 0;
            ArrayList<ArrayList<SackOfShit>> oldTable = table;
            table = new ArrayList<ArrayList<SackOfShit>>(table.size() * 2 + 1);
            for (ArrayList<SackOfShit> chain : oldTable) {
                if (chain != null) {
                    for (SackOfShit value : chain) {
                        put(value.word, value.occurances);
                    }
                }
            }
        }
    }

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
        return hash;
    }

    private class SackOfShit {

        private String word;
        private int occurances;

        public SackOfShit(String word, int occurances) {
            this.word = word;
            this.occurances = occurances;
        }
    }
}
