package ferreira;
import java.lang.reflect.Constructor;
import java.util.*;

//Implement a linked list
public class LinkedList {
    /**
     * Node to be used on the linked List
     */
    static class Node {
        private int data;
        private Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        public int getData() {
            return this.data;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public Node getNext() {
            return this.next;
        }
        
    }

    /**
     * LinkedList Custom Implementation
     */
    static class CustomLinkedList {
        private Node head;
        private Node tail;
        private int length;

        CustomLinkedList() {
            this.head = null;
            this.tail = null;
            this.length = 0;
        }

        public void add(int data) {
            Node newNode = new Node(data);
            if (this.head == null) {
                this.head = newNode;
                this.tail = this.head;
            } else {
                Node tempNode = this.tail;
                tempNode.setNext(newNode);
                this.tail = newNode;
            }
            this.length++;
        }

        public int get(int index) {
            if (index < 0 || index >= this.length){
                System.out.println("Index out of range!");
                return -1;
            }

            if (index == 0) {
                return this.head.data;
            } else if (index == this.length - 1) {
                return this.tail.data;
            } 

            Node iteratorNode = this.head;
            int iteratorIndex = 0;
            while(iteratorIndex != index) {
                iteratorNode = iteratorNode.next;
                iteratorIndex++;
            }

            return iteratorNode.data;
        }

        public int indexOf(int data) {
            Node iterator = this.head;
            int index = 0;
            while(iterator.data != data) {
                if (iterator.next == null) {
                    return -1;
                }
                iterator = iterator.next;
                index++;
            }
            return index;
        }

        public void add(int index, int data) {
            if (index < 0 || index > this.length) {
                System.out.println("Index out of range!");
                return;
            }
            Node newNode = new Node(data);

            if (index == 0) {
                newNode.next = this.head;
                this.head = newNode;
            } else if (index == this.length) {
                this.tail.next = newNode;
                this.tail = newNode;
            } else {
                Node prev = this.head;
                Node next = this.head;
                int iteratorIndex = 0;
                while(iteratorIndex != index) {
                    prev = next;
                    next = next.next;
                    iteratorIndex++;
                }
                prev.next = newNode;
                newNode.next = next;
            }
            this.length++;
        }

        public int remove(int index) {
            if (index < 0 || index > this.length) {
                System.out.println("Index out of range!");
                return - 1;
            }

            Node returnNode = null;
            if (index == 0) {
                returnNode = this.head;
                this.head = this.head.next;
            } else {
                int indexTrack = 0;
                Node next = this.head;
                Node prev = this.head;
                while(indexTrack != index) {
                    prev = next;
                    next = next.next;
                    indexTrack++;
                }
                returnNode = next;
                prev.next = next.next;

            }
            this.length--;
            return returnNode.data;
        }

        public boolean removeData(int data) {
            if (this.head.data == data) {
                this.head = this.head.next;
                this.length--;
                return true;
            }
            Node prev = this.head;
            Node next = this.head;
            while(next != null) {
                if (next.data == data) {
                    prev.next = next.next;
                    this.length--;
                    return true;
                }
                prev = next;
                next = next.next;
            }
            System.out.print("Error: Data Not Found!");
            return false;
        }

        public int size() {
            return this.length;
        }

        public void clear() {
            if (this.head == null) {
                System.out.println("Empty list!");
            } else {
                this.head = null;
                this.length = 0;
            }
        }
        
        public boolean isEmpty() {
            return this.head == null;
        }
        public void display() {
            System.out.println("Calling Display");
            System.out.println("----------------------");
            Node iteratorNode = this.head;
            while (iteratorNode != null) {
                System.out.print(iteratorNode.getData() + " -> ");
                iteratorNode = iteratorNode.getNext();
            }
            System.out.println("\n----------------------");
        }

    }

    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();
        System.out.println("Adding from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        list.display();

        System.out.println("Getting Size: " + list.size());

        System.out.println("Adding at index 0 -> 0");
        list.add(0, 0);
        list.display();

        System.out.println("Adding at last index -> 6");
        list.add(list.size(), 6);
        list.display();

        System.out.println("Adding at index 1 -> 7");
        list.add(1, 7);
        list.display();

        System.out.println("Adding at index 5 -> 9");
        list.add(5, 9);
        list.display();

        System.out.println("Get at index 1 -> expected 7 " + "result: " + list.get(1));
        System.out.println("Get at index 5 -> expected 9 " + "result: " + list.get(5));
        System.out.println("Get at index 0 -> expected 0 " + "result: " + list.get(0));
        System.out.println("Get at index (size() - 1) -> expected 6 " + "result: " + list.get(list.size() - 1));

        System.out.println("Get index of int 0 -> expected index 0 " + "result: " + list.indexOf(0));
        System.out.println("Get index of int 6 -> expected index 8 " + "result: " + list.indexOf(6));
        System.out.println("Get index of int 9 -> expected index 5 " + "result: " + list.indexOf(9));
        System.out.println("Get index of int 2 -> expected index 3 " + "result: " + list.indexOf(2));
        System.out.println("Get size -> expected 9 " + "result: " + list.size());

        list.display();
        System.out.println("Remove at index 0 -> expected 0 " + "result: " + list.remove(0));
        list.display();
        System.out.println("Remove at index 6 -> expected 5 " + "result: " + list.remove(6));
        list.display();
        System.out.println("Remove at index 3 -> expected 3 " + "result: " + list.remove(3));
        list.display();
        System.out.println("Remove at index (size() - 1) -> expected 6 " + "result: " + list.remove(list.size() - 1));
        list.display();
        System.out.println("Size() ? " + list.size());
        System.out.println("IsEmpty() ? " + list.isEmpty());
        System.out.println("Remove int data 1 expected: true result " + list.removeData(1));
        System.out.println("Remove int data 4 expected: true result " + list.removeData(4));
        System.out.println("Remove int data 7 expected: true result " + list.removeData(7));
        System.out.println("Remove int data 7 expected: false result " + list.removeData(7));
        list.display();
        System.out.println("clear()");
        list.clear();
        list.display();
        System.out.println("IsEmpty() ? " + list.isEmpty());
        System.out.println("Size() ? " + list.size());

    }
}


