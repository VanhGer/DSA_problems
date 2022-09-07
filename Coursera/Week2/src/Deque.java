import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int sz;

  private class Node {
    Item item;
    Node next;
    Node pre;
  }

  public Deque() {
    first = null;
    last = null;
    sz = 0;
  }

  public boolean isEmpty() {
    return (first == null);
  }

  public int size() {
    return sz;
  }

  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException("null argument");
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
    if (oldfirst == null) {
      last = first;
    } else {
      oldfirst.pre = first;
    }
    sz++;
  }

  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException("null argument");
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.pre = oldlast;
    if (oldlast == null) {
      first = last;
    } else {
      oldlast.next = last;
    }
    sz++;
  }

  public Item removeFirst() {
    if (this.isEmpty()) throw new NoSuchElementException();
    Item res = first.item;
    first = first.next;
    if (first != null)
      first.pre = null;
    else last = null;
    return res;
  }

  public Item removeLast() {
    if (this.isEmpty()) throw new NoSuchElementException();
    Item res = last.item;
    last = last.pre;
    if (last != null)
      last.next = null;
    else first = null;
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

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (cur == null) {
        throw new NoSuchElementException();
      }
      Item res = cur.item;
      cur = cur.next;
      return res;
    }
  }

  public static void main(String[] args) {
    Deque<Integer> dq = new Deque<Integer>();
    Iterator<Integer> it = dq.iterator();
    if (dq.isEmpty()) {
      System.out.println("Empty");
    }
    dq.addFirst(3);
    dq.addFirst(5);
    dq.addLast(2);
    dq.addLast(6);
    it = dq.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
    dq.removeFirst();
    dq.removeLast();
    dq.removeLast();
    dq.removeLast();
    it = dq.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
