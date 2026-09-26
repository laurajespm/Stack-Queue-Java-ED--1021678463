public class StackArray<T> implements MyStack<T> {
    private int n;
    private int top;
    private T[] sarray;

    public StackArray(){
        this.n = 4;
        this.top = 0;
        this.sarray = (T[]) new Object[n];
    }

    public StackArray(int n){
        this.n = n;
        this.top = 0;
        this.sarray = (T[]) new Object[n];
    }

    public void push (T x){
        if (!isFull()){
            sarray[top] = x;
            top++;
            return;
        } else {
            n = 2*n;
            T[] newArray = (T[])new Object[n];
            for (int j = 0; j <top; j++){
                newArray[j] = sarray[j];
            }
            sarray = newArray;
            sarray[top] = x;
            top++;
        }
    }

    public T pop (){
        if (isEmpty()){
            System.out.println("The list is empty, nothing to pop.");
            return null;
        } else {
            top --;
            return sarray[top];
        }
    }

    public T peek(){
        if (isEmpty()){
            System.out.println("Nothing to peek, the stack is empty");
            return  null;
        }
        return (sarray[top-1]);
    }

    public boolean isEmpty(){
        return top == 0;
    }

    public boolean isFull(){
        return top == sarray.length;
    }

    public int size(){
        return top;
    }

    public void delete (T n){
        if (isEmpty()){
            System.out.println("The list is empty. No item to delete.");
            return;
        }
        for (int i=0; i < top; i++){
            if (sarray[i].equals(n)){
                for(int j = i; j < top - 1; j++){
                    sarray[j] = sarray [j+1];
                }
                top--;
                return;
            }
        }
        System.out.println("Item not found");
    }
}
