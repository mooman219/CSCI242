import java.util.*;

/**
   This class is an array-based heap   

   NOTE: Items placed in the heap MUST implement compareTo()
*/

public class ArrayHeap<E extends Comparable<E>> 
{
    private ArrayList<E> arrayHeap ;
    
    public ArrayHeap( )
    {
        arrayHeap = new ArrayList<E>() ;
    }

    /**
       The siftUp method sifts up the element at arrayHeap[size()-1].
    */
    private void siftUp()
    {
        int p = arrayHeap.size()-1;  // Position to sift up
        while (p != 0)
        {
           int parent = (p-1) / 2;           
           E t1 = arrayHeap.get(p) ;
           E t2 = arrayHeap.get(parent) ;
           if ( t1.compareTo(t2) > 0 )
           {
               // We are done
               break;
           }
           else
           {
              // Do a swap
              E temp = arrayHeap.get(parent);
              arrayHeap.set(parent, arrayHeap.get(p));
              arrayHeap.set(p, temp);
              
              // Move up
              p = parent;
           }            
        }
    }
    
    /**
       The siftDown method sifts down the element at arrayHeap[0].
    */
    
    private void siftDown()
    {
        int p = 0; // Position to sift down
        int size = arrayHeap.size();
        while (2*p + 1 < size)
        {
           int leftChildPos = 2*p + 1;
           int rightChildPos = leftChildPos + 1;
           int minChildPos = leftChildPos;
           
           // Is there a right child?
           if (rightChildPos < size)
           {
               // Which child is smaller
               E t1 = arrayHeap.get(rightChildPos) ;
               E t2 = arrayHeap.get(leftChildPos) ;
               if ( t1.compareTo(t2) < 0 )
               {
                   minChildPos = rightChildPos;
               }
           }           
           // If less than children we are done, 
			  // otherwise swap node with smaller child
           E t1 = arrayHeap.get(p) ;
           E t2 = arrayHeap.get(minChildPos) ;
           if ( t1.compareTo(t2) <= 0 )
               break;
           else
           {
               // Do the swap
               E temp = arrayHeap.get(p);
               arrayHeap.set(p, arrayHeap.get(minChildPos));
               arrayHeap.set(minChildPos, temp);               
           }           
           // Go down to the child position
           p = minChildPos;            
        }
    }
    
    /**
       The add method adds a value to the heap.
       @param x The value to add.
	   @return true.
    */
    
    boolean add(E x)
    {
       // Add x at the end of the array list
       arrayHeap.add(x);
       
       // Sift up
       siftUp();
       return true;
    }
    
    /**
      The removeMin method removes an item from the heap.
      @return The minimum element in the heap.
	  @exception RuntimeException When heap is empty.
    */
    
    public E removeMin()
    {
       if (isEmpty())
		 { 
           String message = "Heap is empty.";
			  throw new RuntimeException(message);
		 }
       else
       {
           E val = arrayHeap.get(0);
           
           // Replace root by last leaf
		   int lastPos = arrayHeap.size()-1;
           arrayHeap.set(0, arrayHeap.get(lastPos));
           
           // Remove the last leaf
           arrayHeap.remove(arrayHeap.size()-1);
           siftDown();
           return val;
       }
    }
    
    /** 
       The isEmpty method checks if the heap is empty.
       @return true if the heap is empty and false otherwise. 
    */

    public boolean isEmpty()
    {
        return arrayHeap.size() == 0;
    }
    
    /** 
       The valueAt method is a convenience method, 
       it makes the code more readable.
       @return value stored at given position in the heap.
    */
	 
    private E valueAt(int pos)
    {
        return arrayHeap.get(pos);
    }    
}
