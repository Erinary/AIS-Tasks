package ru.academits.erinary.list;

import java.util.Collection;

public class SimpleLinkedList<T> {
    private ListItem head;
    private int size = 0;

    public SimpleLinkedList() {
        this.head = null;
    }

    private class ListItem {
        private T data;
        private ListItem next;

        ListItem(T data) {
            this.data = data;
            this.next = null;
        }

        ListItem(T data, ListItem next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Получение размера списка
     *
     * @return размер списка
     */
    public int getSize() {
        return size;
    }

    /**
     * Получение узла по индексу
     *
     * @param index индекс узла
     * @return узел
     */
    private ListItem getNode(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("Список пуст");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Недопустимое значение индекса");
        }
        ListItem p = head;
        ListItem temp = null;
        for (int i = 0; i <= index; ++i) {
            if (p == null) {
                break;
            }
            temp = p;
            p = p.next;
        }
        return temp;
    }

    /**
     * Вставка в начало списка
     *
     * @param node значение нового узла
     */
    public void insertHead(T node) {
        head = new ListItem(node, head);
        ++this.size;
    }

    /**
     * Получение значения первого узла
     *
     * @return значение узла
     */
    public T getHead() {
        if (head == null){
            throw new IndexOutOfBoundsException("Список пуст!");
        }
        return this.head.data;
    }

    /**
     * Получение значения узла по индексу
     *
     * @param index индекс узла
     * @return значение узла
     */
    public T getNodeData(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Недопустимое значение индекса");
        }
        ListItem p = this.getNode(index);
        return p.data;
    }

    /**
     * Изменение значения узла по индексу
     *
     * @param index   индекс узла
     * @param newData новое значение
     * @return старое значение
     */
    public T setNodeData(int index, T newData) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Недопустимое значение индекса");
        }
        ListItem p = this.getNode(index);
        T temp = p.data;
        p.data = newData;
        return temp;
    }

    /**
     * Удаление узла по индексу
     *
     * @param index индекс узла
     * @return значение удаленного узла
     */
    public T deleteNode(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Недопустимое значение индекса");
        }
        T temp;
        if (index == 0) {
            temp = this.deleteHead();
        } else {
            ListItem previous = this.getNode(index - 1);
            ListItem current = previous.next;
            temp = current.data;
            previous.next = current.next;
            --this.size;
        }
        return temp;
    }

    /**
     * Удаление первого узла
     *
     * @return выдает значение удаленного узла
     */
    public T deleteHead() {
        T temp;
        if (head == null) {
            throw new IndexOutOfBoundsException("Список пуст");
        }
        temp = head.data;
        head = head.next;
        --this.size;
        return temp;
    }

    /**
     * Вставка элемента по индексу
     *
     * @param data  значение нового узла
     * @param index индекс вставки
     */
    public void insertNode(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Недопустимое значение индекса");
        }
        if (index == 0) {
            this.insertHead(data);
        } else {
            ListItem previous = this.getNode(index - 1);
            ListItem current = previous.next;
            ListItem newUnit = new ListItem(data);
            previous.next = newUnit;
            newUnit.next = current;
            ++this.size;
        }
    }

    /**
     * Удаление узла по значению
     *
     * @param data передаваемое значение
     */
    public void deleteNodeByData(T data) {
        if (head == null) {
            throw new IndexOutOfBoundsException("Список пуст");
        }
        for (ListItem p = head, previous = null; true; previous = p, p = p.next) {
            if (p.data.equals(data) && previous != null) {
                previous.next = p.next;
                --this.size;
                break;
            } else if (p.data.equals(data) && previous == null) {
                head = p.next;
                --this.size;
                break;
            } else if (p.next == null) {
                throw new RuntimeException("Такого значения в списке нет");
            }
        }
    }

    /**
     * Вставка узла после указанного узла
     *
     * @param data          значение нового узла
     * @param specifiedUnit указанный узел
     */
    public void insertNodeAfterSpecifiedOne(T data, ListItem specifiedUnit) {
        specifiedUnit.next = new ListItem(data, specifiedUnit.next);
        ++this.size;
    }

    /**
     * Удаление узла после указанного узла
     *
     * @param specifiedUnit указанный узел
     */
    public void deleteNodeAfterSpecifiedOne(ListItem specifiedUnit) {
        if (specifiedUnit.next == null) {
            throw new IndexOutOfBoundsException("Конец списка, следующий элемент удалить невозможно");
        }
        ListItem deletedNode = specifiedUnit.next;
        specifiedUnit.next = specifiedUnit.next.next;
        deletedNode.data = null;
        deletedNode.next = null;
        --this.size;
    }

    /**
     * Разворот списка
     */
    public void inverseList() {
        ListItem prev = null;
        ListItem current = head;
        while (current != null) {
            ListItem next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    /**
     * Преобразует список в массив
     *
     * @return массив, содержащий элементы списка
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (ListItem p = head; p != null; p = p.next) {
            result[i++] = p.data;
        }
        return result;
    }

    /**
     * Добавление в конец списка передаваемой коллекции
     *
     * @param c коллекция
     * @return true, если операция прошла успешно
     */
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            System.out.println("Передаваемая коллекция пуста");
            return true;
        }
        for (T o : c) {
            this.insertNode(o, size);
        }
        return true;
    }

    /**
     * Копирование текущего списка
     * @return копию списка
     */
    public SimpleLinkedList<T> copyList() {
        SimpleLinkedList<T> result = new SimpleLinkedList<>();
        if (this.head == null){
            return result;
        }
        result.head = result.new ListItem(head.data);
        ListItem temp = result.head;
        for (ListItem p = head.next; p != null; p = p.next) {
            ListItem node = result.new ListItem(p.data);
            temp.next = node;
            temp = node;
        }
        result.size = this.size;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (ListItem p = head; p != null; p = p.next){
            sb.append(p.data);
            if (p.next != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

