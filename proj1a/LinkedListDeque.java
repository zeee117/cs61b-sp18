public class LinkedListDeque<T>{
    private class Node{
        T item;
        Node front;
        Node next;

        public Node(T i, Node f, Node n){
            item = i;
            front = f;
            next = n;
        }

        public Node(){
            front = null;
            next = null;
        }
    }

    private int size;
    private Node sentinel;

    /* create an empty LinkedListDeque with sentinel node
        sentinel.item = 63 */
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node();
        sentinel.front = sentinel;
        sentinel.next = sentinel;
    }

    /* add i to the first place */
    public void addFirst(T i){
        Node n = new Node(i, sentinel, sentinel.next);
        sentinel.next.front = n;
        sentinel.next = n;
        size += 1;
    }

    /* add i to the last place */
    public void addLast(T i){
        Node n = new Node(i, sentinel.front, sentinel);
        sentinel.front.next = n;
        sentinel.front = n;
        size += 1;
    }

    /* check if the list is empty */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    /* return the size of list */
    public int size(){
        return size;
    }

    /* the helper function to printDeque */
    private void printHelper(Node n){
        if(n == sentinel){
            return;
        }
        System.out.print(n.item + " ");
        printHelper(n.next);
    }

    /* print the list's items */
    public void printDeque(){
        printHelper(sentinel.next);
    }

    /* remove the first Node from the list */
    public T removeFirst(){
        size -= 1;
        Node helper = sentinel.next;
        T ans = helper.item;
        sentinel.next.next.front = sentinel;
        sentinel.next = sentinel.next.next;
        helper.front = null;
        helper.next = null;
        return ans;
    }

    /* remove the last Node from the list */
    public T removeLast(){
        size -= 1;
        Node helper = sentinel.front;
        T ans = helper.item;
        sentinel.front.front.next = sentinel;
        sentinel.front = sentinel.front.front;
        helper.front = null;
        helper.next = null;
        return ans;
    }

    /* print the ith item using iteration */
    public T get(int i){
        Node n = sentinel.next;
        while(n != sentinel && i != 0){
            i -= 1;
            n = n.next;
        }
        if(n == sentinel){
            return null;
        }
        return n.item;
    }

    /* the helper function of getRecursive */
    private T getHelper(int i, Node n){
        if(n == sentinel){
            return null;
        }else if(i == 0){
            return n.item;
        }
        return getHelper(i - 1, n.next);
    }

    /* get the ith item using recursion */
    public T getRecursive(int i){
        return getHelper(i, sentinel.next);
    }

    public static void main(String[] args){
        LinkedListDeque L = new LinkedListDeque();
        System.out.println(L.isEmpty());
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(3);
        System.out.println(L.get(0));
        System.out.println(L.getRecursive(0));
        System.out.println(L.isEmpty());
        System.out.println(L.size());
        L.printDeque();
        System.out.println(L.removeFirst());
        System.out.println(L.removeLast());
    }
}