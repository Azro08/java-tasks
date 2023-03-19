package movetofront;
public class Node<T> {
    T value;
    Node<T> next;
    public Node(T value, Node<T> next){
        this.value = value;
        this.next = next;
    }
}
