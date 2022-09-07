import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
    private Node first;
    private int sz;
    private class Node {
        Item item;
        Node next;
    }

    public Stack() {
        first = null;
        sz = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return sz;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        sz++;
    }

    public Item pop() {
        if (first == null) {
            throw new NoSuchElementException("Stack Underflow");
        }
        Item res = first.item;
        first = first.next;
        sz--;
        return res;
    }

    public Item top() {
        if (first == null) {
            throw new NoSuchElementException("Stack Underflow");
        }
        return first.item;
    }
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node cur = first;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (cur == null) {
                throw new NoSuchElementException();
            }
            Item item = cur.item;
            cur = cur.next;
            return item;
        }
    }

  public static void main(String[] args) {
     Stack<String> s = new Stack<String>();
     if (s.isEmpty()) {
         System.out.println("Empty");
     }
     s.push("Va");
     s.push("vanhg");
     s.push("vjp");
     Iterator<String> it = s.iterator();
     while (it.hasNext()) {
        String tmp = it.next();
        System.out.println(tmp);
     }

  }
}
