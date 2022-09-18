import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize;
    private double loadFactor;
    private HashNode<K, V>[] hashArray;
    private int size;

    private static class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode next;

        public HashNode(){
            key = null;
            value = null;
            next = null;
        }

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
            next = null;
        }

        private HashNode<K, V> find(K key, HashNode<K, V> hn){
            if(hn == null){
                return null;
            }else if(hn.key == key){
                return hn;
            }
            return find(key, hn.next);
        }

        /** the code outside will make sure key is in the HashNode list*/
        private void changeValue(K key, V value, HashNode hn){
            if(hn.key == key){
                hn.value = value;
                return;
            }
            changeValue(key, value, hn.next);
        }

        /** put the key value pair in the HashNode list
         * the code outside will make sure key is't in the HashNode list
         */
        private void put(K key, V value, HashNode hn){
            while(hn.next != null){
                hn = hn.next;
            }
            hn.next = new HashNode<>(key, value);
        }
    }

    public MyHashMap(){
        initialSize = 16;
        loadFactor = 0.75;
        hashArray = new HashNode[initialSize];
        size = 0;
    }

    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
        loadFactor = 0.75;
        hashArray = new HashNode[initialSize];
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        hashArray = new HashNode[initialSize];
        size = 0;
    }

    /** transform key's value to hashCode, then return the index according to the hashCode */
    private int transToHash(K key){
        int hashCode = key.hashCode();
        return hashCode % initialSize;
    }

    /** return the HashNode in the hashArray according to key */
    private HashNode<K, V> findHashNode(K key){
        int index = transToHash(key);
        return hashArray[index];
    }

    @Override
    public void clear(){
        hashArray = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        HashNode hashNode = findHashNode(key);
        if(hashNode.find(key, hashNode) == null){
            return false;
        }
        return true;
    }

    @Override
    public V get(K key) {
        if(!containsKey(key)){
            return null;
        }
        HashNode<K, V> hashNode = findHashNode(key);
        hashNode = hashNode.find(key, hashNode);
        return hashNode.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        HashNode hashNode = findHashNode(key);
        if(containsKey(key)){
            hashNode.changeValue(key, value, hashNode);
        }
        if(size / initialSize >= loadFactor){
            resize();
        }
        size += 1;
        hashNode.put(key, value, hashNode);
    }

    /** when size / initialSize >= loadFactor resize hashArray to it's twice space */
    private void resize(){
        HashNode[] appended = (HashNode[]) new Object[initialSize * 2];
        for(int i=0; i<initialSize; i++){
            appended[i] = hashArray[i];
        }
        initialSize *= 2;
        hashArray = appended;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        for(int i=0; i<initialSize; i++){
            HashNode hn = hashArray[i];
            while(hn != null){
                keySet.add(hn.key);
                hn = hn.next;
            }
        }
        return keySet;
    }

    @Override
    public Iterator<K> iterator(){
        return keySet().iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
}
