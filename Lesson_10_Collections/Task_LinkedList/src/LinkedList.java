import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class LinkedList<E> implements MyLinkedList<E> {
    private transient int size = 0;
    private transient Node<E> first;
    private transient Node<E> last;

    private void insertAsFirst(E e) {
        final Node<E> oldFirst = first;
        first = new Node<>(e, null, first);

        if (oldFirst == null) {
            last = first;
        } else {
            oldFirst.previous = first;
        }

        size++;
    }

    private void insertAsLast(E e) {
        final Node<E> oldLast = last;
        last = new Node<>(e, last, null);

        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }

        size++;
    }

    private E extractFirst() {
        E data = first.data;

        if (first.next == null) {
            last = null;
            first.data = null;
            first = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        size--;

        return data;
    }

    private E extractLast() {
        E data = last.data;

        if (last.previous == null) {
            first = null;
            last.data = null;
            last = null;
        } else {
            last = last.previous;
            last.next = null;
        }
        size--;

        return data;
    }

    private E extract(Node<E> node) {
        E data = node.data;
        Node<E> next = node.next;
        Node<E> previous = node.previous;

        if (next == null) {
            last = previous;
        } else {
            next.previous = previous;
        }

        if (previous == null) {
            first = next;
        } else {
            previous.next = next;
        }

        node.data = null;
        node.next = null;
        node.previous = null;
        size--;

        return data;
    }

    private void checkIndex(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index " + i + " is beyond the legal range 0 - " + size);
        }
    }

    private Node<E> getNodeByIndex(int i) {
        int currentIndex;
        Node<E> entryNode;

        if (i > size / 2) {
            entryNode = last;
            currentIndex = size - 1;

            while (entryNode != null) {
                if (currentIndex == i) {
                    break;
                }
                currentIndex--;
                entryNode = entryNode.previous;
            }
        } else {
            entryNode = first;
            currentIndex = 0;

            while (entryNode != null) {
                if (currentIndex == i) {
                    break;
                }
                currentIndex++;
                entryNode = entryNode.next;
            }
        }

        return entryNode;
    }

    private Node<E> getNodeByObject(E e) {
        Node<E> entryNode = first;

        while (entryNode != null) {
            if (e == null) {
                if (entryNode.data == null) {
                    break;
                }
            } else {
                if (e.equals(entryNode.data)) {
                    break;
                }
            }

            entryNode = entryNode.next;
        }

        return entryNode;
    }

    @Override
    public void addAtBegin(E e) {
        insertAsFirst(e);
    }

    @Override
    public void addAtEnd(E e) {
        insertAsLast(e);
    }

    @Override
    public E removeFromBegin() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        return extractFirst();
    }

    @Override
    public E removeFromEnd() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        return extractLast();
    }

    @Override
    public E remove(int i) {
        checkIndex(i);

        return extract(getNodeByIndex(i));
    }

    @Override
    public E remove(E e) {
        Node<E> node = getNodeByObject(e);

        if (node == null) {
            throw new NoSuchElementException();
        }

        return extract(node);
    }

    @Override
    public void clear() {
        Node<E> node = first;

        while (node != null) {
            Node<E> nextNode = node.next;
            node.data = null;
            node.previous = null;
            node.next = null;

            node = nextNode;
        }

        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int i) {
        checkIndex(i);

        return getNodeByIndex(i).data;
    }

    @Override
    public E set(int i, E e) {
        Node<E> node;

        checkIndex(i);
        node = getNodeByIndex(i);

        E data = node.data;
        node.data = e;

        return data;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public E getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        return first.data;
    }

    @Override
    public E getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        return last.data;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        Node<E> i = first;
        StringBuilder result = new StringBuilder("[");

        if (i != null) {
            result.append(i.data.toString());
            i = i.next;
        }

        for (; i != null; i = i.next) {
            result.append(", ").append(i.data.toString());
        }

        return result + "]";
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
}
