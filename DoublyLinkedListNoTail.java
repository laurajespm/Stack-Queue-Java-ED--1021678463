public class DoublyLinkedListNoTail{
    DLLNode head;
    int count;

    public DoublyLinkedListNoTail(){
        this.head = null;
    }

    public void pushBack(int data){
        DLLNode n = new DLLNode(data);
        if (head == null){
            head = n;
        } else {
            DLLNode curr = head;
            while (curr.next != null){
                curr = curr.next;
            }
            curr.next = n;
            n.prev = curr;
        }
        count++;
    }

    public void pushFront(int data){
        DLLNode n = new DLLNode(data);
        if (isEmpty()){
            head = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
        count++;
    }

    public void AddNodeAfter(DLLNode node, int key){
        if (node == null){
            System.out.println("The node does not exist or it is null.");
            return;
        } 
        
        DLLNode n = new DLLNode(key);
        n.next = node.next;
        n.prev = node;

        if (node.next != null){
            node.next.prev = n;
        }

        node.next = n;
        count++;
    }

    public void AddNodeBefore(DLLNode node, int key){
        if (node==null){
            System.out.println("The node does not exist pr it is null.");
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
        }
        if (head.next ==null){
            head = null;
            count--;
            return;
        }
        
        DLLNode curr = head;

        while (curr.next !=null){
            curr = curr.next;
        }

        curr.prev.next = null;
        curr.prev = null;
        count--;
    }

    public void popFront(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        DLLNode temp = head;

        if (head.next == null){
            head = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        count--;
    }
    

    public void DeleteNodeAfter(DLLNode node){
        if (isEmpty()){
            System.out.println("The list is empty, nothing to delete.");
            return;
        } 
        if (node == null || node.next == null){
            System.out.println("There is no node after this node, nothing to delete.");
            return;
        }

        DLLNode nodeToDelete = node.next;
        node.next = nodeToDelete.next;
        
        if (nodeToDelete.next != null){
                nodeToDelete.next.prev = node; 
        }

        nodeToDelete.next = null;
        nodeToDelete.prev = null;

        count--;
    }        


    public void DeleteNodeBefore(DLLNode node){
        if (isEmpty()){
            System.out.println("The list is empty, nothing to delete.");
            return;
        }
        if (node == null || node.prev == null){
            System.out.println("There is no node before this node, nothing to delete.");
            return;
        }

        DLLNode nodeToDelete = node.prev;
        node.prev = nodeToDelete.prev;
        
        if (nodeToDelete.prev != null){
            nodeToDelete.prev.next = node; 
        } else {
            head = node;
        }
        
        nodeToDelete.prev = null;
        nodeToDelete.next = null;

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
        }
        
        temp.next = null;
        temp.prev = null;
        count--;
    }

}