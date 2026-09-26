public class DLLNode {
    int data;
    DLLNode prev;
    DLLNode next;

    public DLLNode(int data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}