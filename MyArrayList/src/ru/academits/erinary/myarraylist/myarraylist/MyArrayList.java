package ru.academits.erinary.myarraylist.myarraylist;

import java.util.*;


public class MyArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        this.items = (T[]) new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private class MyIterator implements Iterator<T> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            T result;
            if (hasNext()) {
                result = get(current);
                current += 1;
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

//    Методы

    /**
     * Возвращает размер списка.
     *
     * @return размер списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true, если список пуст
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет наличие эелмента в списке
     *
     * @param o искомый элемент
     * @return true, если элемент о есть в списке
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; ++i) {
            if (Objects.equals(this.get(i), o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MyIterator iterator() {
        return new MyIterator();
    }

    /**
     * Преобразует список в массив
     *
     * @return массив, содержащий элементы списка
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.items, this.size);
    }

    /**
     * Добавляет элемент в конец списка
     *
     * @param element добавляемый элемент
     * @return true, если текущая коллекция была изменена
     */
    @Override
    public boolean add(T element) {
        if (size >= this.items.length) {
            this.increaseCapacity();
        }
        this.items[size] = element;
        ++size;
        return true;
    }

    /**
     * Метод увеличивает вместимость списка два раза
     */
    @SuppressWarnings("unchecked")
    private void increaseCapacity() {
        T[] old = this.items;
        this.items = Arrays.copyOf(old, old.length * 2);
    }

    /**
     * Удаление узла по значению
     *
     * @param o переданное значение
     * @return true, если список был изменен
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(this.items[i], o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Добавляет в конец списка переданную коллекцию
     *
     * @param c передаваемая коллекция
     * @return true, если список был изменен
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return this.addAll(this.size, c);
    }

    /**
     * Добавляет переданную коллекцию в список по индексу
     *
     * @param index позиция, куда нужно вставить коллекцию
     * @param c     передаваемая коллекция
     * @return true, если список был изменен
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }
        // проверка, влезет ли коллекция при данной вместимости
        if (c.size() + size >= this.items.length) {
            this.ensureCapacity(c.size() - size);
        }
        T[] collectionToCopy = (T[]) c.toArray();
        int oldSize = this.size;
        if (size - index > 0) {
            System.arraycopy(this.items, index, this.items, index + collectionToCopy.length, size - index);
        }
        System.arraycopy(collectionToCopy, 0, this.items, index, collectionToCopy.length);
        this.size += c.size();
        return oldSize != this.size;
    }

    /**
     * Метод проверяет, что вместимость списка не менее переданного минимального значения; если меньше, то
     * то увеличивает вместимость, если нет - ничего не делает
     *
     * @param minCapacity минимальное значение вместимости
     */
    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minCapacity) {
        if (this.items.length < minCapacity) {
            T[] old = this.items;
            this.items = (T[]) new Object[this.items.length + minCapacity];
            System.arraycopy(old, 0, this.items, 0, old.length);
        }
    }

    /**
     * Удаляет все элементы списка
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            this.items[i] = null;
        }
        size = 0;
    }

    /**
     * Получение элемента списка по индексу
     *
     * @param index индекс элемента
     * @return требуемый элемент
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        return this.items[index];
    }

    /**
     * Устанавливет значение элемента по индексу
     *
     * @param index   индекс элемента
     * @param element новое значение
     * @return старое значение элемента
     */
    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        T oldElement = this.items[index];
        this.items[index] = element;
        return oldElement;
    }

    /**
     * Вставка нового элемента по индексу
     *
     * @param index   индекс
     * @param element новый элемент
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        if (size >= this.items.length) {
            this.increaseCapacity();
        }
        if (size - index > 0) {
            System.arraycopy(this.items, index, this.items, index + 1, size - index);
        }
        this.items[index] = element;
        ++this.size;
    }

    /**
     * Удаление элемента по индексу
     *
     * @param index индекс
     * @return значение удаленного элемента
     */
    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        T removedElement = this.items[index];
        if (index < size - 1) {
            System.arraycopy(this.items, index + 1, this.items, index, size - index - 1);
        }
        --size;
        return removedElement;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        MyIterator itr = this.iterator();
        while (itr.hasNext()) {
            T e = itr.next();
            sb.append(e);
            if (itr.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Возвращает индекс первого вхождения переданного элемента в список, или -1, если такого элемента в списке нет.
     *
     * @param o переданный элемент
     * @return индекс первого вхождения, или -1
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(this.items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Возвращает индекс последнего вхождения переданного элемента в список, или -1б если такого элемента в списке нет.
     *
     * @param o переданный элемент
     * @return индекс последнего вхождения, или -1
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i > 0; --i) {
            if (Objects.equals(this.items[i], o)) {
                return i;
            }
        }
        return -1;
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
        throw new UnsupportedOperationException();
    }

    /**
     * Убирает из списка все элементы, не содержащиеся в переданной коллекции
     *
     * @param c переданная коллекция
     * @return true, если текущий список был изменен
     */
    @Override
    public boolean retainAll(Collection c) {
        try {
            @SuppressWarnings("unchecked")
            T[] coincidentItems = (T[]) new Object[this.size];
            int i = 0;
            for(T listElement: this) {
                if (c.contains(listElement)) {
                    coincidentItems[i] = listElement;
                    ++i;
                }
            }
            this.items = coincidentItems;
            this.size = i;
         } catch (ClassCastException e) {
            System.out.println("Типы коллекций не совпадают!");
            return false;
        }
        return true;
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
