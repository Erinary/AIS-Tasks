package ru.academits.erinary.myarraylist.myarraylist;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements List {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] items;
    private int size = 0;

    public MyArrayList(int initialCapacity) {
        this.items = new Object[initialCapacity];
    }

    public MyArrayList() {
        this.items = new Object[DEFAULT_CAPACITY];
    }

//    Методы

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (Object e : items) {
                if (e == null) {
                    return true;
                }
            }
        } else {
            for (Object e : items) {
                if (e.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[this.items.length];
        System.arraycopy(this.items, 0, newArray, 0, this.items.length);
        return newArray;
    }

    @Override
    public boolean add(Object o) {
        if (size >= this.items.length) {
            this.increaseCapacity();
        }
        this.items[size] = o;
        ++size;
        return true;
    }

    private void increaseCapacity() {
        Object[] old = this.items;
        this.items = new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; ++i) {
                if (this.items[i] == null) {
                    this.remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (this.items[i].equals(o)) {
                    this.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if (c.isEmpty()) {
            throw new NullPointerException("Передаваемая коллекция пуста!");
        }
        if (c.size() + size >= this.items.length) {
            this.ensureCapacity(c.size() - size);
        }
        Object[] collectionToCopy = c.toArray();
        int oldSize = this.size;
        System.arraycopy(collectionToCopy, 0, this.items, size, collectionToCopy.length);
        return oldSize != this.size;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (c.isEmpty()) {
            throw new NullPointerException("Передаваемая коллекция пуста!");
        }
        if (c.size() + size >= this.items.length) {
            this.ensureCapacity(c.size() - size);
        }
        Object[] collectionToCopy = c.toArray();
        int oldSize = this.size;
        if (size - index > 0) {
            System.arraycopy(this.items, index, this.items, index + collectionToCopy.length, size - index);
        }
        System.arraycopy(collectionToCopy, 0, this.items, index, collectionToCopy.length);
        return oldSize != this.size;
    }

    public void ensureCapacity(int minCapacity) {
        if (this.items.length < minCapacity) {
            Object[] old = this.items;
            this.items = new Object[this.items.length + minCapacity];
            System.arraycopy(old, 0, this.items, 0, old.length);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            this.items[i] = null;
        }
        size = 0;
    }

    @Override
    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        return this.items[index];
    }

    @Override
    public Object set(int index, Object element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        Object oldElement = this.items[index];
        this.items[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, Object element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        if (size >= this.items.length) {
            this.increaseCapacity();
        }
        if (size - index > 0) {
            System.arraycopy(this.items, index, this.items, index + 1, size - index);
        }
        this.items[index] = element;
    }

    @Override
    public Object remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        Object removedElement = this.items[index];
        if (index < size - 1) {
            System.arraycopy(this.items, index + 1, this.items, index, size - index - 1);
        }
        --size;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
