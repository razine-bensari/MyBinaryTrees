package MyTree;

import java.util.Collection;
import java.util.Iterator;

public interface Tree<E> {
    public void add(E e);
    public void addAll (Collection<? extends E> c);
    public boolean remove(E e);
    public Iterator<E> iterator();
    public int height();
    public int size();
    Node<E> getRoot(); //I added this method.
}
