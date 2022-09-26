public class ArrayDeque<T>{

    private int size;
    private int length;
    private T[] items;
    private int front;
    private int next;

    public ArrayDeque(){
        size = 0;
        length = 8;
        items = (T []) new Object[8];
        front = 3;
        next = 4;
    }

    /* return the index before i using circle */
    private int minusOne(int i){
        if(i == 0){
            return length - 1;
        }
        return i - 1;
    }

    /* return the index after i using circle */
    private int plusOne(int i){
        if(i == length - 1){
            return 0;
        }
        return i + 1;
    }

    private void grow(){
        T[] tmp = (T[]) new Object[length * 2];
        int tmpInd = length;
        int tmpArrayInd = plusOne(front);
        int i = 0;
        while(i != size){
            tmp[tmpInd] = items[tmpArrayInd];
            tmpInd += 1;
            tmpArrayInd = plusOne(tmpArrayInd);
            i += 1;
        }
        front = length - 1;
        length *= 2;
        next = length - 1;
        items = tmp;
    }

    private void shrink(){
        T[] tmp = (T[]) new Object[length / 2];
        int tmpInd = length / 4;
        int tmpArrayInd = plusOne(front);
        int i = 0;
        while(i != size){
            tmp[tmpInd] = items[tmpArrayInd];
            tmpInd += 1;
            tmpArrayInd = plusOne(tmpArrayInd);
            i += 1;
        }
        length /= 2;
        front = length / 2 - 1;
        next = 0;
        items = tmp;
    }

    /* add item in the front place */
    public void addFirst(T item){
        if(size == length - 1){
            grow();
        }
        items[front] = item;
        front = minusOne(front);
        size += 1;
    }

    /* add item in the next place */
    public void addLast(T item){
        if(size == length - 1){
            grow();
        }
        items[next] = item;
        next = plusOne(next);
        size += 1;
    }

    /* remove the front item in plusOne(front) */
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        if(length >= 16 && size * 4 == length){
            shrink();
        }
        front = plusOne(front);
        T ans = items[front];
        items[front] = items[minusOne(front)];
        size -= 1;
        return ans;
    }

    /* remove the item in minusOne(next) */
    public T removeLast(){
        if(size == 0){
            return null;
        }
        if(length >= 16 && size * 4 == length){
            shrink();
        }
        next = minusOne(next);
        T ans = items[next];
        items[next] = items[plusOne(next)];
        size -= 1;
        return ans;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int tmpInd = front + 1;
        while(tmpInd != next){
            if(plusOne(tmpInd) == next){
                System.out.print(items[tmpInd]);
            }else{
                System.out.print(items[tmpInd] + " ");
            }
            tmpInd = plusOne(tmpInd);
        }
    }

    public T get(int i){
        int tmpInd = plusOne(front);
        for(int j = 0; j < i; j += 1){
            tmpInd = plusOne(tmpInd);
        }
        return items[tmpInd];
    }
}

1.
a.
"carol dan"
"carol eve"
"dan carol"
"alice bob"
"carol eve"
b.
"alice bob"
"fritz bob"
"fritz gritz"
2.
a.
if x > 1
    f will return the 2th numbre which is less
    than or equal x
else if x < 1 and x >=0
    f will in an infinite loop

4.
a.
x[i] != y
xclean[c] = x[i];
c++;
new int[c];
xclean, 0, r, 0, c

b.
x == null
null;
x.first == y
ilsans(x.rest, y);
IntList(x.first, ilsans(x.rest, y));

c.

d.
new int[]{4, 5, 5, 6};
5;
new int[]{4, 6};
Sans.sans(x, y);
assertEqual(expected, actual);
4, 5, 5, 6
5;
4,6
Sans.sans(x, y);
assertEqual(expected, actual);

5.
a.
private int length;
length = 8;
//resize()
Item tmp = new Item[capacity];
System.arraycopy(items, 0, tmp, 0, capacity);
items.length = capacity;
items = tmp;
//push()
resize(items.length * 2);
items[items.size()] = x;
items.size++;
//pop
items.size() == 0
resize(items.length / 2);
items[items.size() - 1] = 0;
items.size--;
//size()
items.size();
b.
public default void purge(Item x){
    Item[] ans = (Item[]) new Object[8];
    Item[] p = this;
    for(int i=0; i<this.length; i++){
        if(p[i] != x){
            ans.push(x);
        }
    }
    return ans;
}

6.
a.
x.length == 0
0
x.length == 1
x[0]
return helperCombine(f, x, 0);
private static int helperCombine(ComFunc f, int[]x, int ind){
    if(ind == x.lnegth - 2){
        return f.apply((x[ind], x[ind + 1]));
    }
    return f.apply((x[ind], helperCombine(f, x, ind + 1)));
}

b.
Combine.combine(new Add(), x);

7.
a.
default public void plusEquals(ListOfItns x){
    if(x.size() != this.size()){return;}
    for(int i=0; i<this.size; i++){
        this.set(i, x[i] + this[i]);
    }
}

b.
public void plusEquals(DLListOfInts x){
    if(x.size() != this.size()){
        return;
    }
    x = x.sentinel.next;
    for(IntNode p = sentinel.next; p!=sentinel; p = p.next){
        p.item = p.item + x.item;
        x = x.next;
    }
}

pubilc static V get(Map61B m, K[] key){
    int ind = getIndex(key);
    if(ind == -1){
        return null;
    }
    return m[ind];
}

public class ArraySet<T> {
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for(int i=0; i<size; i++){
            if(items[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. 
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if(contains(x)){
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");        
        System.out.println(s.contains("horse"));        
        System.out.println(s.size());       
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}

map
priority queue
deque

public boolean equalTwo(int[] a, int k){
    Set<integer> s = new HashSet<>();
    for(int item : a){
        if(s.contains(k - item)){
            return true;
        }
        s.add(item);
    }
    return false;
}

public class Queue<T>{
    private Stack<T> s1;
    private Stack<T> s2;

    public Queue(){
        s1 = new stack<>();
        s2 = new stack<>();
    }

    public void push(T item){
        s1.push(item);
    }

    public T poll(){
        while(s1.size > 0){
            s2.push(s1.poll);
        }
        T ans = s2.poll;
        while(s2.size > 0){
            s1.push(s2.poll);
        }
        return ans;
    }
}

public class SortStack{
    private Stack<integer> s1;
    private Stack<integer> s2;

    public void SortStack(){
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int item){
        while(!s1.isEmpty() && s1.peck() > item){
            s2.push(s1.poll());
        }
        s1.push(item);
        while(!s2.isEmpty()){
            s1.push(s2.poll());
        }
    }

    public int pop(){
        return s1.poll();
    }
}

@Override
public String toString(){
    String ans = "{";
    for(T item : items){
        ans += item.toString();
        ans += ",";
    }

}

public boolean equals(Object other){
    if(other == this){
        return true;
    }
    if(other == null){
        return false;
    }
    if(other.getClass() != this.getClass()){
        return false;
    }
    if(other.size() != this.size()){
        return false;
    }
    ArraySet<T> a = (ArraySet<T>) other;
    for(T item : items){
        if(!a.contains(item)){
            return false;
        }
    }
    return ture;
}

public static void main(String[] args){
    try{
        bis.pop();
    }catch(NullPointerException e){
        System.out.println("Success");
    }
}

public static void main(String[] args){
    BadIntegerStack bis = new BadIntegerStack();
    bis.push(1);
    bis.top.prev = top;
    while(!bis.isEmpty()){
        bis.pop();
    }
}

mystery of 1 is 1
3
counter is 1
1
counter is 2
mystery of 6 is 2

public AltList<Y, X> pairSwapped(){
    if(this.next.next == null){
        return new AltList<Y, X>(this.next.item, 
            new AltList<X, Y>(this.item, null));
    }
    AltList<Y, X> al = this.next.next.pairSwapped();
    return new AltList<Y, X>(this.next.item, 
        new AltList<X, Y>(this.item, al));
}

public AltList<Y, X> pariSwapped(){
    AltList<Y, X> al = new AltList<Y, X>(this.next.item
        new AltList<X, Y>(this.item, null));
    if(this.next.next != null){
        al.next.next = this.next.next.pairSwapped()
    }
    return al;
}

public Integer next(){
    if(curList == null){
        throw new NosuchElementException();
    }
    Integer ans = curList.head;
    for(int i=0; i<k; i++){
        curList = curList.tail;
    }
    hasNext = (curLsit != null);
    return ans;
}

first sort the array
then check out if every value in array can find the same value in it
map 

public int[] combine(int[] a, int[]b){
    int i = 0;
    int j = 0;
    int ind = 0;
    int[] ans = new int(a.length + b.length);
    while(i < a.length() && j < b.length()){
        if(a[i] == a[j]){
            ans[ind] = a[i];
            i++;
            j++;
        }else if(a[i] < b[j]){
            ans[ind] = ans[i];
            i++;
        }else{
            ans[ind] = b[j];
            j++;
        }
        ind++;
    }
    while(i < a.length()){
        ans[ind] = ans[i];
        ind++;
        i++;
    }
    while(j < b.length()){
        ans[ind] = b[j];
        ind++;
        j++;
    }
    return ans;
}

list of sets
quick find
quick union

static BST find(BDT T, Key key){
    if(T == null){
        return null;
    }
    if(T.key == key){
        return T.key;
    }else if(T.key < key){
        return find(T.right, key);
    }else{
        return find(T.left, key);
    }
}

static BST insert(BST T, Key ik){
    if(T == null){
        return new BST(ik);
    }
    if(T.key < ik){
        T.right = insert(T.right, ik);
    }else if(T.key > ik){
        T.left = insert(T.left, ik);
    }
    return T;
}

public static boolean isBSTGood(TreeNode T){
    return isBSTHelper(T, Integer.MIN_VAUE, Interger.MAX_VALUE);
}

public static boolean isBSTHelper(TreeNode T, int min, int max){
    if(T == null){
        return true;
    }else if(T.value < min || T.value > max){
        return false;
    }
    return isBSTHelper(T.left, min, T.value) && isBSTHelper(T.right, T.value, max);
}

2. the hashCode function return a very large number
3. no
4. very large and the people equals does't have the same hashCode
5. large

public int preOrder(tree T){
    if(T == null){
        return null;
    }
    visit(T.lable);
    preOrder(T.left);
    preOrder(T.right);
}

public int inOrder(tree T){
    if(T == null){
        return null;
    }
    inOrder(T.left);
    visit(T.lable);
    inOrder(T.right);
}

public static boolean isAHeap(IntTree xt){
    if(xt == null){
        return true;
    }
    if(getItem(xt) > getItem(xt.left) || getItem(xt) > getItem(xt.right)){
        return false;
    }
    return isAHeap(xt.left) && isAHeap(xt.right);
}

public static void getTreeValues(IntTree xt, List<Integer> treeValues){
    if(xt == null){
        return;
    }
    getTreeValues(xt.left, treeValues);
    treeValues.add(getItem(xt));
    getTreeValues(xt.right, treeValues);
}

public static boolean vaildXelhaTree(IntTree xt, List<Integer> vals){
    List<Integer> treeValues = new ArrayList<Integer>();
    getTreeValues(xt, treeValues);
    return isAHeap(xt) && treeValues.equals(vals);
}

public IntTree constructTree(int[] preorder, int[] inorder){
    if(preorder.legth == 0 || inorder.lenght == 0){
        return null;
    }
    IntTree it = new IntTree();
    it.item = preorder[0];
    it.left = constructTree(preleft, inleft);
    it.right = constrcutTree(preright, inright);
    return it;
}

public static void twocolor(Grah G, int v, Set<Integer> a, Set<Integer> b){
    a.add(v);
    for(int vInd : adj(v)){
        if(a.contains(vInd)){
            throw new IllegalArgumentException();
        }
        if(!b.contains(vInd)){
            twoColor(G, vInd, b, a);
        }
    }
}