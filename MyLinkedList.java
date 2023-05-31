public class MyLinkedList {

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head; // головной элемент списка

    public void add(int value) {
        if (head == null) {
            head = new Node(value);
        } else {
            Node last = findLast();
            last.next = new Node(value);
        }
    }

    /**
     * Получить размер списка.
     *
     * @return размер списка
     */
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Проверить наличие элемента в списке.
     *
     * @param value значение элемента
     * @return true, если элемент найден, иначе false
     */
    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Создать новый список с обратным порядком элементов.
     *
     * @return новый список с обратным порядком элементов
     */
    public MyLinkedList reversed() {
        MyLinkedList reversedList = new MyLinkedList();
        Node current = head;
        while (current != null) {
            reversedList.addFirst(current.value);
            current = current.next;
        }
        return reversedList;
    }

    /**
     * Добавить элемент в начало списка.
     *
     * @param value значение элемента
     */
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    private Node findLast() {
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            result.append(current.value).append(" -> ");
            current = current.next;
        }

        int length = result.length();
        if (length > 4) {
            result.delete(length - 4, length);
        }

        result.append("]");
        return result.toString();
    }
}
