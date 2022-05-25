import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashtableMap <K, V> implements MapADT{

    //data fields ~

    private K key;
    private V value;
    private int arraySize;//Size of the array
    private int capacity;
    private double loadFactor = 0.8; //load factor of HashTable
    protected HashNode<K, V>[] theHash;

    //protected HashNode<K, V>[] theArray;
    //protected ArrayList<HashNode<K, V> > theArray; //ArrayList for Keys and Values to be paired


    /**
     * Constructor that sets the size and makes the table
     * @param capacity
     */
    public HashtableMap(int capacity, double loadFactor) throws IllegalArgumentException{
       if(capacity <= 0){
           throw new IllegalArgumentException("The capacity cannot be less than 0!");
       }
        this.arraySize = 0;
        this.capacity = capacity;
        this.loadFactor = arraySize/capacity;
        theHash = (HashNode<K, V>[]) new HashNode[capacity]; //creating an array list to store values
    }

    /**
     * Constructor similar to the other constructor declared
     */
    public HashtableMap() {
        this(20, 0.8);
    }


    public boolean isEmpty(){
        return size() == 0;
    }

    public final int hashCode(K key){
        int hashCode = key.hashCode(); //Finds the key within the has
        return hashCode;
    }


    /**
     * Doubles table size and creates a new hash for resizing purposes
     */
    private void refactorHash(){

        loadFactor = (arraySize/capacity);

        while(loadFactor > this.loadFactor) { //might change this to an if
            int updateSize = capacity * 2;
            capacity = updateSize;
            HashNode[] newHash = new HashNode[capacity];


            for (int n = 0; n < capacity; n++) {
                newHash[n] = theHash[n];
                theHash = newHash;

            }
        }
        }

    @Override
    public boolean put(Object key, Object value) {

        if (key == null || get(key) != null) {
            throw new IllegalArgumentException("The key is invalid!");
        } else {

            int hashVal = (int) get(key);
            if (theHash[hashVal] == null) {
                theHash[hashVal] = new HashNode<>((K) key, (V) value, hashCode());
            } else {
                HashNode temp = theHash[hashVal];
                temp.next = new HashNode((K) key, (V) value, hashCode());
                theHash[hashVal] = temp;
            }
            arraySize++;
            refactorHash();
        }
        return true;
    }

    @Override
    public Object get(Object key) throws NoSuchElementException {
        if (!containsKey(key)) throw new NoSuchElementException();

        int hc = key.hashCode();
        int hashIndex = Math.abs(hc) % this.capacity;

        if (theHash[hashIndex].getKey().equals(key)) {
            return theHash[hashIndex].getValue();
        } else {
            HashNode<K, V> node = theHash[hashIndex];
            while (!node.getKey().equals(key)) {
                node = (HashNode<K, V>) node.getNext((K) key, value);
            }
            return node.getValue();
        }
    }



    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean containsKey(Object key) {
        int hc = key.hashCode();
        int hashIndex = Math.abs(hc) % this.capacity;

        if (theHash[hashIndex] == null)
            return false;

        if (theHash[hashIndex].getKey().equals(key))
            return true;
        else if (theHash[hashIndex].next == null) return false;

        else {
            HashNode<K, V> node = theHash[hashIndex];

            if(node.next != null){
                node = node.next;
                if(node.getKey().equals(key)) return true;
            }
            }
        return false;
        }




    @Override
    public Object remove(Object key) {
        if (!containsKey(key)) return null;

        int hc = key.hashCode();
        int hashIndex = Math.abs(hc) % this.capacity;

        HashNode<K, V> cur = theHash[hashIndex];
        HashNode<K, V> prev = theHash[hashIndex];

        if(cur != null){
            if(sameKeyHelper(cur.getKey(), (K) key)){
                if(prev == null){
                    theHash[hashIndex] = cur.next;
                } else {
                    prev.next = cur.next;
                }
                return cur;
            }
        }
     return null;
    }

    @Override
    public void clear() {
        arraySize = 0;
        theHash = new HashNode[arraySize];

    }


    private boolean sameKeyHelper(K key, K key2){
        return Objects.equals(key, key2);
    }

    private boolean sameValueHelper(V value, V value2){
        return Objects.equals(value, value2);
    }



}
