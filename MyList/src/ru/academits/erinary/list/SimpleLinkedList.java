package ru.academits.erinary.list;

public class SimpleLinkedList<T> {
    private ListItem head;

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

    public int getSize() {
        int counter = 0;
        for (ListItem p = head; p != null; p = p.next) {
            ++counter;
        }
        return counter;
    }

    private ListItem getListItem(int index) {
        if (head == null) {
            throw new RuntimeException("Список пуст");
        }
        if (index < 0) {
            throw new RuntimeException("Отрицательный индекс");
        }
        ListItem p = head;
        for (int i = 0; i < index; ++i) {
            p = p.next;
            if (p == null) {
                throw new RuntimeException("Список кончился.");
            }
        }
        return p;
    }

    public void setFirstUnit(T unit) {
        head = new ListItem(unit, head);
    }

    public T getFirstUnit() {
        return getUnit(0);
    }

    public T getUnit(int index) {
        ListItem p = this.getListItem(index);
        return p.data;
    }

    public T setUnit(int index, T newData) {
        ListItem p = this.getListItem(index);
        T temp = p.data;
        p.data = newData;
        return temp;
    }

    public T deleteUnit(int index) {
        T temp;
        if (index == 0) {
            if (head == null) {
                throw new RuntimeException("Список пуст");
            }
            temp = head.data;
            head = head.next;
        } else {
            ListItem previous = this.getListItem(index - 1);
            ListItem current = previous.next;
            temp = current.data;
            previous.next = current.next;
        }
        return temp;
    }

    public T deleteFirst() {
        return this.deleteUnit(0);
    }

    public void insertUnit(T data, int index) {
        if (index == 0) {
            ListItem newUnit = new ListItem(data);
            newUnit.next = head;
            head = newUnit;
        } else {
            ListItem previous = this.getListItem(index - 1);
            ListItem current = previous.next;
            ListItem newUnit = new ListItem(data);
            previous.next = newUnit;
            newUnit.next = current;
        }
    }

    public void insertFirst(T data) {
        this.insertUnit(data, 0);
    }


}
