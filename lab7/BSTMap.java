import java.util.Set;
import java.util.Iterator;
import java.util.Comparator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K k, V v){
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    private Node node;
    private int size;

    public BSTMap(){
        node = null;
        size = 0;
    }

    @Override
    public void clear(){
        node = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key){
        return get(key) != null;
    }

    @Override
    public V get(K key){
        return getHelper(node, key);
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void put(K key, V value){
        node = putHelper(node, key, value);
    }

    public void printInOrder(){

    }

    private V getHelper(Node n, K key){
        if(n == null){
            return null;
        }
        if(key.compareTo(n.key) == 0){
            return n.value;
        }else if(key.compareTo(n.key) < 0){
            return getHelper(n.left, key);
        }else{
            return getHelper(n.right, key);
        }
    }

    private Node putHelper(Node n, K key, V value){
        if(n == null){
            size += 1;
            return new Node(key, value);
        }
        if(key.compareTo(node.key) == 0){
            n.value = value;
        }else if(key.compareTo(n.key) < 0){
            n.left = putHelper(n.left, key, value);
        }else{
            n.right = putHelper(n.right, key, value);
        }
        return n;
    }

    /** below is the functions we don't need */
    @Override
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException();
    }

    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
}
