import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() throws Exception {
        if (first == null) {
            throw new Exception("Cannot pop from an empty stack.");
        }
        Item res = first.item;
        first = first.next;
        return res;
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
     try {
         System.out.println(s.pop());
     } catch (Exception e) {
         System.out.println(e.getMessage());
     }
  }
}
