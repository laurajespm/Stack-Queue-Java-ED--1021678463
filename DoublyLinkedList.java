public class DoublyLinkedList{
    DLLNode head;
    DLLNode tail;
    int count;

    public DoublyLinkedList(){
        this.tail = null;
        this.head = null;
    }

    public void pushBack(int data){
        DLLNode n = new DLLNode(data);

        if (isEmpty()){
            head = n;
            tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }

        count++;
    }

    public void pushFront(int data){
        DLLNode n = new DLLNode(data);
        if (isEmpty()){
            head = n;
            tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
        count++;
    }

    public void AddNodeAfter(DLLNode node, int key){
        if (node == null){
            System.out.println("Invalid node.");
            return;
        }

        DLLNode n = new DLLNode(key);
        n.next = node.next;
        n.prev = node;

        if (node.next != null){
            node.next.prev = n;
        } else {
            tail = n;
        }
        node.next = n;
        count++;
    }

    public void AddNodeBefore(DLLNode node, int key){
        if (node == null){
            System.out.println("Invalid node.");
            return;
        }

        DLLNode n = new  DLLNode(key);
        n.prev = node.prev;
        n.next = node;

        if (node.prev != null){
            node.prev.next = n;
        } else {
            head = n;
        }

        node.prev= n;
        count++;
    }

    public void popBack(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to delete.");
            return;
        } else if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        tail = tail.prev;
        tail.next = null;
        count--;
    }

    public void popFront(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to delete.");
            return;
        } else if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        head = head.next;
        head.prev = null;
        count--;
    }
    

    public void DeleteNodeAfter(DLLNode node){
        if (isEmpty()){
            System.out.println("The list is empty, nothing to delete.");
            return;
        } 

        if (node == null || node.next == null){
            System.out.println("There is no node after this node.");
            return;
        }

        DLLNode nodeToDelete = node.next;
        
        if (nodeToDelete.next != null){
                node.next = nodeToDelete.next;
                nodeToDelete.next.prev = node; 
            } else {
                node.next = null;
                tail = node;
            }
        }        


    public void DeleteNodeBefore(DLLNode node){
        if (isEmpty()){
            System.out.println("The list is empty, nothing to delete.");
            return;
        } 
        if (node.prev == null || node == null){
            System.out.println("There is no node before this node.");
            return;
        }

        DLLNode nodeToDelete = node.prev;
        
        if (nodeToDelete.prev != null){
                node.prev = nodeToDelete.prev;
                nodeToDelete.prev.next = node; 
        } else {
                node.prev = null;
                head = node;
        }
        count--;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public DLLNode Find(int item){
        DLLNode temp = head;

        if (!isEmpty()){
            while (temp != null){
                if (temp.data == item){
                    return temp;
                }
                temp = temp.next;
            }
        }
        System.out.println("The element was not found");
        return null;
    }

    public void Erase(int n){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        if (n<=0){
            System.out.println("Invalid position.");
            return;
        }
        if (n==1){
            popFront();
            return;
        }
        
        int counter = 1;
        DLLNode pointer = head;

        while (counter < (n-1) && pointer.next != null){
            pointer = pointer.next;
            counter++;
        }
        if (pointer == null || pointer.next == null){
            System.out.println("Position out of bounds.");
            return;
        }

        DLLNode temp = pointer.next;

        pointer.next = temp.next;

        if (temp.next != null){
            temp.next.prev = pointer;
        } else {
            tail = pointer;
        }
        
        temp.next = null;
        temp.prev = null;
        count--;
    }

}