package Backtracking;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Bruno De Luca on 3/15/16.
 */
public class DynamicStack<T> implements Iterable<T> {

    private int n;
    private Node first;

    private class Node{
        private T item;
        private Node next;
    }

    public DynamicStack(){
        first = null;
        n = 0;
    }

    public T getNode(int num){
        if(num < 0 || num > n) throw new IndexOutOfBoundsException();
        T elem = null;
        Iterator it = this.iterator();
        while(it.hasNext())
            elem = (T) it.next();
        return elem;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    public void push(T item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public T pop(){
        if(isEmpty()) throw new RuntimeException("The stack is EMPTY");
        T item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public T peek(){
        if(isEmpty()) throw new RuntimeException("Stack Underflow");
        return first.item;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(T item: this)
            s.append(item + " ");
        return s.toString();
    }

    @Override
    public Iterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

