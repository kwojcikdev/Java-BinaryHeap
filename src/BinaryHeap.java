import java.util.Scanner;

import java.util.Arrays;

import java.util.NoSuchElementException;

class BinaryHeap {

    private final int c = 2;
    private int heapSize;
    private int[] heap;

    public BinaryHeap(int size){

        heapSize = 0;
        heap = new int[size];
    }

    public boolean isEmpty() { return heapSize == 0;};
    public boolean isFull() { return heapSize == heap.length;};

    private int parentOf(int i){ return (i-1)/c; }
    private int kChild(int i, int k) { return c * i +k; }


    public void insert(int ele)
    {
        if(!isFull())
        {
            heap[heapSize++] = ele;
            heapifyUp(heapSize - 1);
        }
        else { System.out.print("no space");}
    }

    private void heapifyUp(int child)
    {
        int tmp = heap[child];

        while( child > 0 && tmp < heap[parentOf(child)])
        {
            heap[child] = heap[ parentOf(child)];
            child = parentOf(child);
        }
        heap[child] = tmp;
    }

    private void heapifyDown(int inx)
    {
        int child;
        int tmp = heap[inx];

        while( kChild(inx, 1) < heapSize)
        {
            child = minChild(inx);
            if(heap[child] < tmp) heap[inx] = heap[child];
            else break;

            inx = child;
        }
        heap[inx] = tmp;

    }

    private int minChild(int inx)
    {

        int hChild = kChild(inx, 1);
        int k = 2;
        int pos = kChild(inx, k);

        while((k <= c) && (pos < heapSize))
        {
            if(heap[pos] < heap[hChild]) hChild = pos;
            pos = kChild(inx, k++);
        }

        return hChild;
    }

    public void printHeap()
    {
        System.out.print("\nHeap = ");

        for (int i = 0; i < heapSize; i++)

            System.out.print(heap[i] +" ");

        System.out.println();

    }


    public int findMin( )

    {

        if (isEmpty() )

            throw new NoSuchElementException("Underflow Exception");

        return heap[0];

    }



    public int deleteMin()
    {
        int keyItem = heap[0];
        delete(0);
        return keyItem;
    }

    public int delete(int ind)

    {

        if (isEmpty() )

            throw new NoSuchElementException("Underflow Exception");

        int keyItem = heap[ind];

        heap[ind] = heap[heapSize - 1];

        heapSize--;

        heapifyDown(ind);

        return keyItem;

    }

    public void makeEmpty()
    {
        heapSize = 0;
    }
}
