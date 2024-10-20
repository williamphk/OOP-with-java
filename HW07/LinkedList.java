public class LinkedList<E> {
    private class Node<E> { // inner class
        private E data;
        private Node<E> next;

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public void add(int index, E newData) {
        Node<E> node = new Node<E>(newData, null);
        Node<E> current = head;
        int count = 0;

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else if (isEmpty()) {
            head = node;
            size++;
        } else if (index == 0) {
            node.next = current;
            head = node;
            size++;
        } else {
            while (current.next != null && count < index - 1) {
                count++;
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
            size++;
        }
    }

    public void add(E newData) {
        add(size, newData);
    }

    public boolean contains(Object target) {
        if (isEmpty())
            return false;

        boolean found = false;
        Node<E> current = head;

        while ((current != null) && (!found)) {
            if (target == null ? current.data == null : target.equals(current.data)) {
                found = true;
            } else {
                current = current.next;
            }
        }
        return found;
    }

    public E get(int index) {
        Node<E> current = head;
        int count = 0;
        E data;

        if (isEmpty())
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        else if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else {
            while (current.next != null && count < index) {
                count++;
                current = current.next;
            }
            data = current.data;
        }
        return data;
    }

    public int indexOf(Object target) {
        int index = -1;
        int count = 0;
        Node<E> current = head;

        if (isEmpty()) {
            return index;
        }

        while ((current != null) && (index == -1)) {
            if (target == null ? current.data == null : target.equals(current.data)) {
                index = count;
            } else {
                count++;
                current = current.next;
            }
        }
        return index;
    }

    public E remove(int index) {
        E removedData;
        Node<E> current = head;
        int count = 0;

        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else if (isEmpty()) {
            removedData = null;
            size--;
        } else if (index == 0) {
            removedData = head.data;
            head = current.next;
            size--;
        } else {
            while (current.next != null && count < index - 1) {
                count++;
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
            size--;
        }

        return removedData;
    }

    public boolean remove(Object target) {
        if (isEmpty())
            return false;
        else if (!contains(target))
            return false;
        else {
            remove(indexOf(target));
            return true;
        }
    }

    public E set(int index, E data) {
        Node<E> current = head;
        int count = 0;
        E removedData;

        if (isEmpty())
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        else if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else {
            while (current.next != null && count < index) {
                count++;
                current = current.next;
            }
            removedData = current.data;
            current.data = data;
        }
        return removedData;
    }

    public String toString() {
        Node<E> current = head;
        String result = "";

        if (isEmpty())
            return "[" + "]";

        while (current != null) {
            result += current.data == null ? "null, " : current.data.toString() + ", ";
            current = current.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof LinkedList))
            return false;

        LinkedList<?> other = (LinkedList<?>) obj;

        if (this.size != other.size)
            return false;

        Node<E> thisNode = this.head;
        LinkedList<?>.Node<?> otherNode = other.head;

        while (thisNode != null && otherNode != null) {
            if (thisNode.data == null) {
                if (otherNode.data != null)
                    return false;
            } else if (!thisNode.data.equals(otherNode.data)) {
                return false;
            }
            thisNode = thisNode.next;
            otherNode = otherNode.next;
        }

        return true;
    }
}