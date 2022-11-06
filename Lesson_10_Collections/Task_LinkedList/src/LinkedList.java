public class LinkedList implements MyLinkedList {
    private Object data;
    private int size = 0;
    private LinkedList first;
    private LinkedList last;
    private LinkedList previous;
    private LinkedList next;

    //region Constructors
    public LinkedList() {
    }

    public LinkedList(Object data) {
        this.data = data;
    }
    //endregion

    @Override
    public boolean addFirst(Object o) {
        if (first != null && last != null) {
            includeBefore(first, new LinkedList(o));
        } else {
            first = new LinkedList(o);
            last = first;
            size++;
        }
        return true;
    }

    private void includeBefore(LinkedList source, LinkedList newUnit) {
        if (source.previous == null) {
            source.previous = newUnit;
            newUnit.next = source;
            first = newUnit;
        } else {
            LinkedList previousUnit = source.previous;
            source.previous = newUnit;
            previousUnit.next = newUnit;
            newUnit.next = source;
            newUnit.previous = previousUnit;
        }
        size++;
    }

    @Override
    public boolean isContain(Object o) {
        for (LinkedList l = first; l != null; ) {
            if (l.data.equals(o)) {
                return true;
            }

            l = l.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(Object o) {
        if (first != null && last != null) {
            includeAfter(last, new LinkedList(o));
        } else {
            last = new LinkedList(o);
            first = last;
            size++;
        }
        return true;
    }

    private void includeAfter(LinkedList source, LinkedList newUnit) {
        if (source.next == null) {
            source.next = newUnit;
            newUnit.previous = source;
            last = newUnit;
        } else {
            LinkedList nextUnit = source.next;
            source.next = newUnit;
            nextUnit.previous = newUnit;
            newUnit.next = nextUnit;
            newUnit.previous = source;
        }
        size++;
    }

    @Override
    public boolean remove(Object o) {
        for (LinkedList l = first; l != null; ) {
            if (l.data.equals(o)) {
                exclude(l);
                return true;
            }
            l = l.next;
        }

        return false;
    }

    private void exclude(LinkedList listUnit) {
        LinkedList previousUnit = listUnit.previous;
        LinkedList nextUnit = listUnit.next;

        if (previousUnit != null && nextUnit != null) {
            previousUnit.next = nextUnit;
            nextUnit.previous = previousUnit;
            listUnit.data = null;
            size--;
        } else if (previousUnit == null && nextUnit != null) {
            nextUnit.previous = null;
            first = nextUnit;
            listUnit.data = null;
            size--;
        } else if (previousUnit != null) {
            previousUnit.next = null;
            listUnit.data = null;
            last = previousUnit;
            size--;
        } else {
            first.data = null;
            first = last = null;
            size = 0;
        }
    }

    @Override
    public void clear() {
        for (LinkedList l = first; l != null; ) {
            l.previous = null;
            l.data = null;
            l = l.next;
        }

        if (first != null) {
            first.data = null;
            last.data = null;
            first = last = null;
        }

        size = 0;
    }

    @Override
    public Object get(int i) {
        LinkedList l = getListUnit(i);

        if (l != null) {
            return l.data;
        }

        return null;
    }

    @Override
    public boolean set(int i, Object o) {
        LinkedList l = getListUnit(i);

        if (l != null) {
            l.data = o;
            return true;
        }

        return false;
    }

    private LinkedList getListUnit(int i) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        if (i >= size) {
            throw new IndexOutOfBoundsException("Index " + i + " is beyond range " + (size - 1));
        }

        int counter;

        if (i > size / 2) {
            counter = size - 1;
            for (LinkedList l = last; l != null; ) {
                if (counter == i) {
                    return l;
                }
                l = l.previous;
                counter--;
            }
        } else {
            counter = 0;
            for (LinkedList l = first; l != null; ) {
                if (counter == i) {
                    return l;
                }
                l = l.next;
                counter++;
            }
        }

        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        int counter = 0;

        for (LinkedList l = first; l != null; ) {
            newArray[counter++] = l.data;
            l = l.next;
        }

        return newArray;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public LinkedList getFirst() {
        return first;
    }

    @Override
    public LinkedList getLast() {
        return last;
    }

    @Override
    public String toString() {
        String returnString = "Values in the list = [";

        for (LinkedList l = first; l != null; ) {
            returnString += l.data;

            if (l.next != null) {
                returnString += ",";
            }

            l = l.next;
        }
        returnString += "]";

        return returnString;
    }

    //region Getters/Setters
    public Object getData() {
        return data;
    }

    public LinkedList getPrevious() {
        return previous;
    }

    public LinkedList getNext() {
        return next;
    }
    //endregion
}
