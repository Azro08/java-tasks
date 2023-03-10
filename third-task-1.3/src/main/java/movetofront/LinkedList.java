package movetofront;

public class LinkedList<T> {
    Node<T> head;

    public LinkedList(){
        //конструктор пустой т.к список мб пустым
    }

    public void add(Node<T> newNode){
        newNode.next = head;
        head = newNode;
    }

    public void moveToFront(LinkedList<T> linkedList){
        /*
        чтобы выполнить задачу, сначала мы переворачиваем связанный список,
        а затем удаляем повторяющиеся символы. таким образом, когда мы вводим символ,
        он идет вперед, и если тот же символ добавляется, он идет первым, а другой будет удален.
         */
        reverse(linkedList);
        removeDuplicates(linkedList);
    }

    public void reverse (LinkedList<T> ls)
    {
        Node<T> previous = null;
        Node<T> current = ls.head;
        Node<T> next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        ls.head = previous;
    }

    public void removeDuplicates(LinkedList<T> ls){
        Node<T> ptr1 = null;
        Node <T> ptr2 = null;
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
