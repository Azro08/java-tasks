package movetofront;

public class LinkedList<T> {
    Node<T> head;

    public LinkedList(){
        //конструктор пустой т.к список мб пустым
    }

    public void addAtStart(Node<T> newNode) {
        if(head == null) {
            head = newNode;
        }
        else {
            Node<T> temp = head;
            head = newNode;
            head.next = temp;
        }
    }

    public void moveToFront(LinkedList<T> linkedList){
        removeDuplicates(linkedList);
    }

    public void removeDuplicates(LinkedList<T> ls){
        Node<T> ptr1;
        Node <T> ptr2;
        ptr1 = ls.head;
        while (ptr1 != null && ptr1.next != null) {
            ptr2 = ptr1;
            while (ptr2.next != null) {
                if (ptr1.value == ptr2.next.value) {
                    ptr2.next = ptr2.next.next;
                }
                else {
                    ptr2 = ptr2.next;
                }
            }
            ptr1 = ptr1.next;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder("{");
        Node<T> n = head;
        if (n == null)
            return s + "}";
        while (n.next != null) {
            s.append(n.value).append("->");
            n = n.next;
        }
        s.append(n.value);
        return s + "}";
    }
}
