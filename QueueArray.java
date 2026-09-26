public class QueueArray<T> implements MyQueue<T> {
    private int n;
    private int front, rear, count;
    private T[] qarray;

    public QueueArray(){
        this.n = 4;
        this.qarray = (T[]) new Object[n];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }
    
    public QueueArray(int n){
        this.n = n;
        this.qarray = (T[]) new Object[n];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }
    
    public void enqueue(T x){
        if (isEmpty()){
            qarray[front] = x;
            rear= (rear+1)%qarray.length;
            count++;
            return;
        }
        if (!isFull()){
            qarray[rear] = x;
            rear= (rear+1)%qarray.length;
            count++;
            return;
        }
        if (isFull()){
            n = 2*n;
            T [] newArray = (T[]) new Object[n];
            int i = front;
            for (int j = 0; j <count; j++){
                newArray[j] = qarray[i];
                i = (i+1) % qarray.length;
            }
            qarray = newArray;
            front = 0;
            rear = count;
            qarray[rear] = x;
            rear= (rear+1)%qarray.length;
            count++;
        }
    }

    public T dequeue(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing to delete.");
            return null;
        }

        T item = qarray[front];
        front = (front + 1) % qarray.length;
        count--;
        return item;
    }

    public T front(){
        if (isEmpty()){
            System.out.println("The list is empty. Nothing in front.");
            return null;
        }
        return qarray[front];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return count == n;
    }

    public int size(){
        return count;
    }

    public void delete(T item){
        if (isEmpty()){
            System.out.println("The queue is empty. Nothing to delete.");
            return;
        }
        
        int i = front;

        for (int j = 0; j < count; j++){
            if (qarray[i].equals(item)){
                int current = i;
                int next = (current+1) % qarray.length;

                while (next != rear){
                    qarray[current] = qarray[next];

                    current = next;
                    next = (next + 1) % qarray.length;
                }
                rear = (rear - 1 + qarray.length) % qarray.length;
                count--;
                return;
            }
            i = (i+1)%qarray.length;
        }
        System.out.println("Item not found.");
    }
}
