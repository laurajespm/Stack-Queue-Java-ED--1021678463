public class LinkedList{
    private Node head;
    private Node tail;

    public LinkedList(){
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void pushFront(int item){
        Node newNode = new Node(item);
        if (isEmpty()){
            head= newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head= newNode;
        }
    }

    public void pushBack(int item){
        Node newNode = new Node(item);
        if (isEmpty()){
            head= newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void addBetween(int n, int item){
        int counter = 1;
        Node newNode = new Node(item);
        Node pointer = head;

        if (isEmpty()){
            head = newNode;
        } else {
            while (counter < n && pointer.next != null){
                pointer = pointer.next;
                counter++;
            }

            newNode.next = pointer.next;
            pointer.next = newNode;

            if (pointer == tail){
                tail = newNode;
            }
        }
    }

    public void addAfter(Node node, int item){
        if (isEmpty() || node == null){
            return;
        }

        Node newNode = new Node(item);

        newNode.next = node.next;
        node.next = newNode;

        if (node == tail){
            tail = newNode;
        }
    }

    public void addBefore(Node node, int item){
        if (isEmpty() || node == null){
            return;
        }

        Node newNode = new Node(item);
        if (head == node){
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp != null && temp.next != node){
            temp = temp.next;
        }

        if (temp == null){
            System.out.println("The target node was not found in the list.");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void popFront(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to delete");
        } else {
            Node pointer = head;
            head = pointer.next;
            pointer.next = null;

            if (head == null){
                tail = null;
            }
        }
    }

    public void popBack(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to delete");
            return;
        } else if(head == tail) {
            head = null;
            tail = null;
        } else {
            Node pointer = head;

            while (pointer.next != tail){
                pointer = pointer.next;
            }

            pointer.next = null;
            tail = pointer;
        }
    }

    public void printList(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to show.");
        } else {
            Node pointer = head;
            while (pointer != null){
                System.out.println(pointer.data);
                pointer = pointer.next;
            }
        }
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
        Node pointer = head;

        while (counter < (n-1) && pointer.next != null){
            pointer = pointer.next;
            counter++;
        }
        if (pointer == null || pointer.next == null){
            System.out.println("Position out of bounds.");
            return;
        }

        Node temp = pointer.next;

        pointer.next = temp.next;
        temp.next = null; 

        if (pointer.next == null){
            tail = pointer;
        }
    }

    public Node Find(int item){
        Node temp = head;

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
}