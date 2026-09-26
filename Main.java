import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random(42);
        long size = 10;

        // 1. LINKED LIST CON TAIL
        System.out.println("Tiempos para linked list: ---------------------");
        for (int exp = 1; exp <= 7; exp++, size *= 10) {
            LinkedList list = new LinkedList();
            for (long i = 0; i < size; i++) {
                list.pushFront(random.nextInt(1000000));
            }

            int reps = (size >= 100000) ? 1000 : 100000;
            int[] values = new int[reps];
            for (int i = 0; i < reps; i++) {
                values[i] = random.nextInt(1000000);
            }

            long time0, time1;

            // pushFront
            time0 = System.nanoTime();
            for (int i = 0; i < reps; i++) {
                list.pushFront(values[i]);
            }
            time1 = System.nanoTime();
            double pushFrontTime = (time1 - time0) / (double) reps;

            for (int i = 0; i < reps; i++) {
                list.popFront();
            }
            System.out.println("Tiempo de pushFront: " + pushFrontTime + " ns | Tamaño: " + size);

            // pushBack
            time0 = System.nanoTime();
            for (int i = 0; i < reps; i++) {
                list.pushBack(values[i]);
            }
            time1 = System.nanoTime();
            double pushBackTime = (time1 - time0) / (double) reps;

            for (int i = 0; i < reps; i++) {
                list.popBack();
            }
            System.out.println("Tiempo de pushBack: " + pushBackTime + " ns | Tamaño: " + size);

            // popBack
            int repsPopBack = (size >= 100000) ? 10 : reps;
            time0 = System.nanoTime();
            for (int i = 0; i < repsPopBack; i++) {
                list.popBack();
                list.pushBack(values[i]);
            }
            time1 = System.nanoTime();
            double popBackTime = (time1 - time0) / (double) repsPopBack;
            System.out.println("Tiempo de popBack: " + popBackTime + " ns | Tamaño: " + size);

            // popFront
            time0 = System.nanoTime();
            for (int i = 0; i < reps; i++) {
                list.popFront();
                list.pushFront(values[i]);
            }
            time1 = System.nanoTime();
            double popFrontTime = (time1 - time0) / (double) reps;
            System.out.println("Tiempo de popFront: " + popFrontTime + " ns | Tamaño: " + size);

            // Find y nodos referencia
            int knownValue = -1;
            list.pushFront(knownValue);

            time0 = System.nanoTime();
            list.Find(-99999999);
            time1 = System.nanoTime();
            System.out.println("Tiempo Find: " + (time1 - time0) + " ns | Tamaño: " + size);

            Node targetNode = list.Find(knownValue);
            if (targetNode != null) {
                time0 = System.nanoTime();
                list.addBefore(targetNode, 999);
                time1 = System.nanoTime();
                System.out.println("Tiempo addBefore: " + (time1 - time0) + " ns | Tamaño: " + size);

                time0 = System.nanoTime();
                list.addAfter(targetNode, 999);
                time1 = System.nanoTime();
                System.out.println("Tiempo addAfter: " + (time1 - time0) + " ns | Tamaño: " + size);
            }

            // Erase
            time0 = System.nanoTime();
            list.Erase((int) Math.min(size / 2, Integer.MAX_VALUE));
            time1 = System.nanoTime();
            System.out.println("Tiempo Erase: " + (time1 - time0) + " ns | Tamaño: " + size);
            System.out.println("--------------------------------------------------");
        }

        // 2. LINKED LIST NO TAIL
        size = 10;
        System.out.println("Tiempos para linked list sin cola: ---------------------");
        for (int exp = 1; exp <= 7; exp++, size *= 10) {
            LinkedListNoTail list = new LinkedListNoTail();
            for (long i = 0; i < size; i++) {
                list.pushFront(random.nextInt(1000000));
            }

            int val = random.nextInt(1000000);
            list.pushFront(val);
            int reps01 = (size >= 100000) ? 1000 : 100000;
            int repsOn = (size >= 10000) ? 10 : 1000;

            long time0, time1;

            // pushFront
            time0 = System.nanoTime();
            for (int i = 0; i < reps01; i++) {
                list.pushFront(val);
            }
            time1 = System.nanoTime();
            for (int i = 0; i < reps01; i++) {
                list.popFront();
            }
            System.out.println("Tiempo de pushFront: " + ((time1 - time0) / (double) reps01) + " ns | Tamaño: " + size);

            // pushBack O(N)
            time0 = System.nanoTime();
            for (int i = 0; i < repsOn; i++) {
                list.pushBack(val);
            }
            time1 = System.nanoTime();
            for (int i = 0; i < repsOn; i++) {
                list.popBack();
            }
            System.out.println("Tiempo de pushBack: " + ((time1 - time0) / (double) repsOn) + " ns | Tamaño: " + size);

            // popBack O(N)
            time0 = System.nanoTime();
            for (int i = 0; i < repsOn; i++) {
                list.popBack();
                list.pushBack(val);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo de popBack: " + ((time1 - time0) / (double) repsOn) + " ns | Tamaño: " + size);

            // popFront O(1)
            time0 = System.nanoTime();
            for (int i = 0; i < reps01; i++) {
                list.popFront();
                list.pushFront(val);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo de popFront: " + ((time1 - time0) / (double) reps01) + " ns | Tamaño: " + size);
    
            Node targetNode = list.Find(val);

            if (targetNode != null) {
                // addAfter O(1)
                time0 = System.nanoTime();
                list.addAfter(targetNode, 99999);
                time1 = System.nanoTime();
                System.out.println("Tiempo addAfter:   " + (time1 - time0) + " ns | Tamaño: " + size);

                // addBefore O(N)
                time0 = System.nanoTime();
                list.addBefore(targetNode, 88888);
                time1 = System.nanoTime();
                System.out.println("Tiempo addBefore:  " + (time1 - time0) + " ns | Tamaño: " + size);
            }

            // Find
            time0 = System.nanoTime();
            list.Find(val);
            time1 = System.nanoTime();
            System.out.println("Tiempo Find: " + (time1 - time0) + " ns | Tamaño: " + size);

            // Erase
            time0 = System.nanoTime();
            list.Erase((int) Math.min(size / 2, Integer.MAX_VALUE));
            time1 = System.nanoTime();
            System.out.println("Tiempo Erase: " + (time1 - time0) + " ns | Tamaño: " + size);
            System.out.println("--------------------------------------------------");
        }

        // 3. DOUBLY LINKED LIST CON TAIL
        size = 10;
        System.out.println("Tiempos para DL.list con cola: ---------------------");
        for (int exp = 1; exp <= 7; exp++, size *= 10) {
            DoublyLinkedList list = new DoublyLinkedList();
            for (long i = 0; i < size; i++) {
                list.pushFront(random.nextInt(1000000));
            }

            int repsDLL = (size >= 100000) ? 1000 : 100000;
            int[] valuesDLL = new int[repsDLL];
            for (int i = 0; i < repsDLL; i++) {
                valuesDLL[i] = random.nextInt(1000000);
            }

            long time0, time1;

            // pushFront
            time0 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) list.pushFront(valuesDLL[i]);
            time1 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) list.popFront();
            System.out.println("Tiempo pushFront O(1): " + ((time1 - time0) / (double) repsDLL) + " ns | Tamaño: " + size);

            // pushBack
            time0 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) list.pushBack(valuesDLL[i]);
            time1 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) list.popBack();
            System.out.println("Tiempo pushBack O(1):  " + ((time1 - time0) / (double) repsDLL) + " ns | Tamaño: " + size);

            // popFront
            time0 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) {
                list.popFront();
                list.pushFront(valuesDLL[i]);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo popFront O(1):  " + ((time1 - time0) / (double) repsDLL) + " ns | Tamaño: " + size);

            // popBack
            time0 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) {
                list.popBack();
                list.pushBack(valuesDLL[i]);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo popBack O(1):   " + ((time1 - time0) / (double) repsDLL) + " ns | Tamaño: " + size);

            // Find y Erase
            time0 = System.nanoTime();
            list.Find(valuesDLL[0]);
            time1 = System.nanoTime();
            System.out.println("Tiempo Find O(N):      " + (time1 - time0) + " ns | Tamaño: " + size);

            time0 = System.nanoTime();
            list.Erase((int) Math.min(size / 2, Integer.MAX_VALUE));
            time1 = System.nanoTime();
            System.out.println("Tiempo Erase O(N):     " + (time1 - time0) + " ns | Tamaño: " + size);

            DLLNode targetNode = list.head;
            if (targetNode != null) {
                time0 = System.nanoTime();
                list.AddNodeAfter(targetNode, 99999);
                time1 = System.nanoTime();
                System.out.println("Tiempo AddNodeAfter:  " + (time1 - time0) + " ns");

                time0 = System.nanoTime();
                list.AddNodeBefore(targetNode, 88888);
                time1 = System.nanoTime();
                System.out.println("Tiempo AddNodeBefore: " + (time1 - time0) + " ns");

                time0 = System.nanoTime();
                list.DeleteNodeAfter(targetNode);
                time1 = System.nanoTime();
                System.out.println("Tiempo DeleteNodeAfter:" + (time1 - time0) + " ns");
            }
            System.out.println("--------------------------------------------------");
        }

        // 4. DOUBLY LINKED LIST SIN TAIL
        size = 10;
        System.out.println("Tiempos para DL.list sin cola: ---------------------");
        for (int exp = 1; exp <= 7; exp++, size *= 10) {
            DoublyLinkedListNoTail list = new DoublyLinkedListNoTail();
            for (long i = 0; i < size; i++) {
                list.pushFront(random.nextInt(1000000));
            }

            int repsDLL = (size >= 100000) ? 1000 : 100000;
            int repsON = (size >= 10000) ? 10 : 1000;

            int[] valuesDLL = new int[repsDLL];
            for (int i = 0; i < repsDLL; i++) {
                valuesDLL[i] = random.nextInt(1000000);
            }

            long time0, time1;

            // pushFront
            time0 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) list.pushFront(valuesDLL[i]);
            time1 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) list.popFront();
            System.out.println("Tiempo pushFront O(1): " + ((time1 - time0) / (double) repsDLL) + " ns | Tamaño: " + size);

            // pushBack
            time0 = System.nanoTime();
            for (int i = 0; i < repsON; i++) list.pushBack(valuesDLL[i]);
            time1 = System.nanoTime();
            for (int i = 0; i < repsON; i++) list.popBack();
            System.out.println("Tiempo pushBack O(N):  " + ((time1 - time0) / (double) repsON) + " ns | Tamaño: " + size);

            // popFront
            time0 = System.nanoTime();
            for (int i = 0; i < repsDLL; i++) {
                list.popFront();
                list.pushFront(valuesDLL[i]);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo popFront O(1):  " + ((time1 - time0) / (double) repsDLL) + " ns | Tamaño: " + size);

            // popBack
            time0 = System.nanoTime();
            for (int i = 0; i < repsON; i++) {
                list.popBack();
                list.pushBack(valuesDLL[0]);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo popBack O(N):   " + ((time1 - time0) / (double) repsON) + " ns | Tamaño: " + size);

            // Find y Erase
            time0 = System.nanoTime();
            list.Find(valuesDLL[0]);
            time1 = System.nanoTime();
            System.out.println("Tiempo Find O(N):      " + (time1 - time0) + " ns | Tamaño: " + size);

            time0 = System.nanoTime();
            list.Erase((int) Math.min(size / 2, Integer.MAX_VALUE));
            time1 = System.nanoTime();
            System.out.println("Tiempo Erase O(N):     " + (time1 - time0) + " ns | Tamaño: " + size);

            DLLNode targetNode = list.head;
            if (targetNode != null) {
                time0 = System.nanoTime();
                list.AddNodeAfter(targetNode, 99999);
                time1 = System.nanoTime();
                System.out.println("Tiempo AddNodeAfter:  " + (time1 - time0) + " ns");

                time0 = System.nanoTime();
                list.AddNodeBefore(targetNode, 88888);
                time1 = System.nanoTime();
                System.out.println("Tiempo AddNodeBefore: " + (time1 - time0) + " ns");

                time0 = System.nanoTime();
                list.DeleteNodeAfter(targetNode);
                time1 = System.nanoTime();
                System.out.println("Tiempo DeleteNodeAfter:" + (time1 - time0) + " ns");

                time0 = System.nanoTime();
                list.DeleteNodeBefore(targetNode);
                time1 = System.nanoTime();
                System.out.println("Tiempo DeleteNodeBefore:" + (time1 - time0) + " ns");
            }
            System.out.println("--------------------------------------------------");
        }

        // 5. QUEUE ARRAY
        size = 10;
        System.out.println("Tiempos para queue: ---------------------");
        for (int exp = 1; exp <= 7; exp++, size *= 10) {
            int repsQueue = (size >= 100000) ? 1000 : 100000;
            QueueArray<Integer> queue = new QueueArray<>((int) Math.min(size + repsQueue, Integer.MAX_VALUE));

            for (long i = 0; i < size; i++) {
                queue.enqueue(random.nextInt(1000000));
            }

            int[] valuesQueue = new int[repsQueue];
            for (int i = 0; i < repsQueue; i++) {
                valuesQueue[i] = random.nextInt(1000000);
            }

            long time0, time1;

            // enqueue
            time0 = System.nanoTime();
            for (int i = 0; i < repsQueue; i++) queue.enqueue(valuesQueue[i]);
            time1 = System.nanoTime();
            for (int i = 0; i < repsQueue; i++) queue.dequeue();
            System.out.println("Tiempo enqueue O(1) amortiguado: " + ((time1 - time0) / (double) repsQueue) + " ns | Tamaño: " + size);

            // dequeue
            time0 = System.nanoTime();
            for (int i = 0; i < repsQueue; i++) {
                queue.dequeue();
                queue.enqueue(valuesQueue[i]);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo dequeue O(1):" + ((time1 - time0) / (double) repsQueue) + " ns | Tamaño: " + size);

            // front
            time0 = System.nanoTime();
            for (int i = 0; i < repsQueue; i++) queue.front();
            time1 = System.nanoTime();
            System.out.println("Tiempo front O(1):" + ((time1 - time0) / (double) repsQueue) + " ns | Tamaño: " + size);

            // delete
            time0 = System.nanoTime();
            queue.delete(valuesQueue[0]);
            time1 = System.nanoTime();
            System.out.println("Tiempo delete O(N):              " + (time1 - time0) + " ns | Tamaño: " + size);
            System.out.println("--------------------------------------------------");
        }

        // 6. STACK ARRAY
        size = 10;
        System.out.println("Tiempos para Stack: ---------------------");
        for (int exp = 1; exp <= 7; exp++, size *= 10) {
            int repsStack = (size >= 100000) ? 1000 : 100000;
            StackArray<Integer> stack = new StackArray<>((int) Math.min(size + repsStack, Integer.MAX_VALUE));

            for (long i = 0; i < size; i++) {
                stack.push(random.nextInt(1000000));
            }

            int[] valuesStack = new int[repsStack];
            for (int i = 0; i < repsStack; i++) {
                valuesStack[i] = random.nextInt(1000000);
            }

            long time0, time1;

            // push
            time0 = System.nanoTime();
            for (int i = 0; i < repsStack; i++) stack.push(valuesStack[i]);
            time1 = System.nanoTime();
            for (int i = 0; i < repsStack; i++) stack.pop();
            System.out.println("Tiempo push O(1) amortiguado: " + ((time1 - time0) / (double) repsStack) + " ns | Tamaño: " + size);

            // pop
            time0 = System.nanoTime();
            for (int i = 0; i < repsStack; i++) {
                stack.pop();
                stack.push(valuesStack[i]);
            }
            time1 = System.nanoTime();
            System.out.println("Tiempo pop O(1):              " + ((time1 - time0) / (double) repsStack) + " ns | Tamaño: " + size);

            // peek
            time0 = System.nanoTime();
            for (int i = 0; i < repsStack; i++) stack.peek();
            time1 = System.nanoTime();
            System.out.println("Tiempo peek O(1):             " + ((time1 - time0) / (double) repsStack) + " ns | Tamaño: " + size);

            // delete
            time0 = System.nanoTime();
            stack.delete(valuesStack[0]);
            time1 = System.nanoTime();
            System.out.println("Tiempo delete O(N):           " + (time1 - time0) + " ns | Tamaño: " + size);
            System.out.println("--------------------------------------------------");
        }
    }
}

