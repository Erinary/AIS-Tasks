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

    /**
     * Получение размера списка
     * @return размер списка
     */
    public int getSize() {
        int counter = 0;
        for (ListItem p = head; p != null; p = p.next) {
            ++counter;
        }
        return counter;
    }

    /**
     * Получение узла по индексу
     * @param index индекс узла
     * @return узел
     */
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

    /**
     * Вставка в начало списка
     * @param unit значение нового узла
     */
    public void setFirstUnit(T unit) {
        head = new ListItem(unit, head);
    }

    /**
     * Получение первого узла
     * @return значение узла
     */
    public T getFirstUnit() {
        return getUnit(0);
    }

    /**
     * Получение значения узла по индексу
     * @param index индекс узла
     * @return значение узла
     */
    public T getUnit(int index) {
        ListItem p = this.getListItem(index);
        return p.data;
    }

    /**
     * Изменение значения узла по индексу
     * @param index индекс узла
     * @param newData новое значение
     * @return старое значение
     */
    public T setUnit(int index, T newData) {
        ListItem p = this.getListItem(index);
        T temp = p.data;
        p.data = newData;
        return temp;
    }

    /**
     * Удаление узла по индексу
     * @param index индекс узла
     * @return значение удаленного узла
     */
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

    /**
     * Удаление первого узла
     * @return выдает значение удаленного узла
     */
    public T deleteFirst() {
        return this.deleteUnit(0);
    }

    /**
     * Вставка элемента по индексу
     * @param data значение нового узла
     * @param index индекс вставки
     */
    public void insertUnit(T data, int index) {
        if (index == 0) {
            this.setFirstUnit(data);
        } else {
            ListItem previous = this.getListItem(index - 1);
            ListItem current = previous.next;
            ListItem newUnit = new ListItem(data);
            previous.next = newUnit;
            newUnit.next = current;
        }
    }




}
