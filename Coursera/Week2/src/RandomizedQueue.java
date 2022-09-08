import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] a;
  private int sz;

  public RandomizedQueue() {
    a = (Item[]) new Object[1];
    sz = 0;
  }

  public boolean isEmpty() {
    return (sz == 0);
  }

  public int size() {
    return sz;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    if (sz == a.length) {
      resize(2 * sz);
    }
    a[sz++] = item;
    swapItem();
  }

  public Item dequeue() {
    if (sz == 0) {
      throw new NoSuchElementException();
    }
    Item item = a[sz - 1];
    a[sz - 1] = null;
    sz--;
    if (sz != 0 && sz == a.length / 4) {
      resize(a.length / 2);
    }
    return item;
  }

  public Item sample() {
    if (sz == 0) {
      throw new NoSuchElementException();
    }
    int i = StdRandom.uniformInt(sz);
    return a[i];
  }

  private void swapItem() {
    int i = StdRandom.uniformInt(sz);
    Item tmp = a[i];
    a[i] = a[sz - 1];
    a[sz - 1] = tmp;
  }

  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < sz; i++) copy[i] = a[i];
    a = copy;
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private int i = 0;
    private Item[] RandomItem;

    public ListIterator() {
      RandomItem = (Item[]) new Object[sz];
      for (int i = 0; i < sz; i++) RandomItem[i] = a[i];
      for (int i = 0; i < sz - 1; i++) {
        int j = StdRandom.uniformInt(i + 1, sz);
        Item tmp = RandomItem[j];
        RandomItem[j] = RandomItem[i];
        RandomItem[i] = tmp;
      }
    }

    public boolean hasNext() {
      return (i != sz);
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (i == sz) {
        throw new NoSuchElementException("No more items to return");
      }
      Item item = RandomItem[i++];
      return item;
    }
  }

  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    if (rq.isEmpty()) System.out.println("Empty!");
    Iterator<Integer> it = rq.iterator();
    rq.enqueue(3);
    rq.enqueue(4);
    rq.enqueue(5);
    it = rq.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
    Iterator<Integer> it2 = rq.iterator();
    while (it2.hasNext()) {
      System.out.println(it2.next());
    }
    System.out.println(rq.dequeue());
    System.out.println(rq.sample());
    System.out.println(rq.sz);
    it.remove();
  }
}
