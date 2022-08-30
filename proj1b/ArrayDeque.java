public class ArrayDeque<T> implements Deque<T>{
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

    @Override
    /* add item in the front place */
    public void addFirst(T item){
        if(size == length - 1){
            grow();
        }
        items[front] = item;
        front = minusOne(front);
        size += 1;
    }

    @Override
    /* add item in the next place */
    public void addLast(T item){
        if(size == length - 1){
            grow();
        }
        items[next] = item;
        next = plusOne(next);
        size += 1;
    }

    @Override
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

    @Override
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

    @Override
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
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

    @Override
    public T get(int i){
        int tmpInd = plusOne(front);
        for(int j = 0; j < i; j += 1){
            tmpInd = plusOne(tmpInd);
        }
        return items[tmpInd];
    }
}
