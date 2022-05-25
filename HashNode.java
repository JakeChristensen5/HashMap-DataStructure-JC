import com.sun.jdi.Value;

/**
 * Helper class to help with chaining and managing for collisions
 */
public class HashNode <KeyType, ValueType> {

        //data fields
        KeyType key;
        ValueType value;
        HashNode<KeyType, ValueType> next; //next node in the list
        final int hashCode;

        /**
         * Constructor
         */
        public HashNode(KeyType key, ValueType value, int hashCode) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }

        public KeyType getKey() {
            return key;
        }

        public ValueType getValue() {
            return value;
        }
        
        public Object getNext(KeyType key, ValueType value) {
            return next;
        }

        public Object setNext(KeyType key, ValueType value, HashNode newNext){
            return this.next = newNext;
        }


        public ValueType makeValue(ValueType value) {
            ValueType oValue = value;
            this.value = value;
            return oValue;
        }

}

