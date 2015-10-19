/*
 * HeapUtils is a class that allows the user to perform operations on heaps - heapify an input array,
 * build a heap from an array, and insert and remove from a heap. A heap is a complete binary tree
 * whose maximum value is at the root, and both left and right subtrees are heaps (a recursive structure).
 * An array represents a heap, and manipulations to a "heap" are manipulations of this array. 
 * @author Rahul Jayaraman
 * @version 041514
 */

public class HeapUtils 
{
	/**
	 * This method heapifies an input array (i.e. makes its binary tree representation satisfy the heap
	 * property). If the root (specified by index) is a leaf, then it is trivially a heap. Otherwise,
	 * determine the maximum value of the root and the children. If the root contains the maximum value,
	 * then the heap condition is satisfied. Otherwise, swap the root value with the maximum value, which
	 * may violate the heap condition for the left and right subtree. We then recursively heapify the
	 * subtree involved in the swap. This method runs in O(log n) time, where n is the number of items
	 * in the array, because of the need to only deal with potentially half of the nodes.
	 * @param heap is the array representation of a heap that we want to heapify
	 * @param index is the root of the "subtree" that we would like to heapify
	 * @param heapSize is the size of the heap, which may not be heap.length - 1
	 * @pre both left and right subtrees are heaps
	 * @post the tree with root at index is now a heap
	 */
	public static void heapify(Comparable[] heap, int index, int heapSize)
	{
		if (index <= heapSize/2) //non-child nodes
		{
			//store variables to the largest value and its index, assume it's the root
			Comparable largest = heap[index];
			int largestIndex = index;
			
			//left child is larger than root, must check for existence!
			if (heap[2*index].compareTo(largest) > 0 && 2*index <= heapSize)
			{
				largest = heap[2*index];
				largestIndex = 2*index;
			} //close if
			
			//right child is larger than the largest value, must check for existence!
			if (heap[2*index + 1].compareTo(largest) > 0 && 2*index + 1 <= heapSize)
			{
				largest = heap[2*index + 1];
				largestIndex = 2*index + 1;
			} //close if
			
			//swap and heapify the index involved as long as it's not the root
			if (!largest.equals(heap[index]))
			{
				swapValues(heap, index, largestIndex);
				heapify(heap, largestIndex, heapSize);
			} //close else
			
		} //close if
				
	} //close heapify()
	
	/**
	 * This method swaps two values in an array. One is stored in a temporary variable, one of the values
	 * is switched, and then the value in the temporary variable is assigned to the other index.
	 * @param array is the array in which we would like to swap two values
	 * @param index1 is one of the indices that we want to swap the value with
	 * @param index2 is the other index that we would like to swap the value with
	 * @post the values are now swapped in the array
	 */
	private static void swapValues(Comparable[] array, int index1, int index2)
	{
		Comparable temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	} //close swapValues()
	
	/**
	 * This method builds a heap given an array of random values of size heapSize. We start with the
	 * rightmost interior node at the next-to-last level, heapify the node, and then work our way
	 * left and upward, heapifying each node we visit until we finally have finished heapifying the root.
	 * This method runs in O(n log n) time, as we potentially have to visit n nodes and perform n
	 * heapifying operations, each of which takes log n time. This uses a for loop for each index
	 * from heapSize/2 all the way back (heapSize/2 is the rightmost interior node - with children). 
	 * @param heap is the data which we wish to convert into a heap
	 * @param heapSize is the size of the heap that we will build and is a param for heapify()
	 * @post the array heap's binary tree representation now satisfies the heap property
	 */
	public static void buildHeap(Comparable[] heap, int heapSize)
	{
		//go left and upward in the numbering system for the indices of the binary tree, heapifying each
		for (int i = heapSize/2; i > 0; i--)
			heapify(heap, i, heapSize);
		
	} //close buildHeap()
	
	/**
	 * This method removes and returns the root value, leaving a complete binary tree that is one 
	 * element smaller and meets the heap condition. Remove() runs in O(log n) time, as we swap
	 * and heapify the root; heapify runs in O(log n) time, and this is basically heapify(). We first
	 * extract the data from the root, replace it with the rightmost leaf at the lowest level, and then
	 * heapify the root. 
	 * @param heap is the array representation of the binary tree
	 * @param heapSize is the size of the heap that we wish to remove from; this is a param of heapify()
	 * @return the value that previously used to be at the root.
	 * @post the root is now swapped with a different value but still has the heap property
	 */
	public static Comparable remove(Comparable[] heap, int heapSize)
	{
		Comparable root = heap[1]; //store the values
		swapValues(heap, 1, heapSize);
		 
		heap[heapSize] = null; //remove that value, it's stored at root
		heapSize--; 
		
		heapify(heap, 1, heapSize);
		
		return root;
	} //close remove()
	
	/**
	 * This method inserts item into the heap array, maintaining the heap property, and returns the new
	 * heap with the element added. This places the data in the next available location in the array
	 * heap, maintaining a complete binary tree. We locate the parent node, heapify that, and continue
	 * up to the root, finally heapifying that. This method runs in O(log n) time because we'll need
	 * at most one swap on each level on the path to the root, halving the problem size each time.
	 * @param heap is the array that we want to insert
	 * @param item is what we want to insert
	 * @param heapSize is the size of the heap for heapify()
	 * @return the array with the item inserted
	 * @post the item is inserted and the heap property is maintained
	 */
	public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize)
	{
		//if the array can't hold the values, double the size
		if (heap.length <= heapSize + 1)
		{
			Comparable[] newHeap = new Integer[heap.length*2];
			for (int i = 1; i < heap.length; i++)
				newHeap[i] = heap[i];
			
			heap = newHeap;
		} //close if
		
		//this common code increments the heap size, inserts the item into the array, and heapifies
		//all of the parent nodes up the tree (even if the array can hold the values)
		heapSize++;
		heap[heapSize] = item;
		
		for (int i = heapSize; i > 0; i = i/2)
			heapify(heap, i, heapSize);

		return heap;
		
	} //close insert()
	
	/**
	 * heapSort is a O(nlogn) sort algorithm that begins with the data ordered as a heap.
	 * The root node is swapped with the node n, and the new tree having one less element 
	 * is then heapified. The process repeats until all items in the heap are processed.
	 * @param heap is the array representation of a complete binary tree meeting the
	 * heap condition
	 * @param heapSize is the size of the heap which may differ from the size of the array 
	 * precondition: heap is an array representation of a binary tree that satisfies the 
	 * heap condition
	 * postcondition: the array heap contains the data sorted from smallest to largest
	 * beginning at index 1. The original heap is destroyed.
	 */
	public static void heapSort(Comparable[] heap, int heapSize) 
	 { 
		for(int i = 1; i <= heapSize; i++) 
		{ 
			int index = heapSize +1 - i; // last index is heapSize
			int length = heapSize - i; 
			swapValues(heap, 1, index); 
			heapify(heap, 1, length); 
		} 
	 } 
	
} //close HeapUtils class
