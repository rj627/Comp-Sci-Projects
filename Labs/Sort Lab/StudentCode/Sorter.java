/**
* Class Sorter 
*/
public class Sorter
{

	private SortDisplay display;
	/**
	* main method instantiates a sorter instance
	* Usuage: called directly by the IDE or when Java is launched 
	* @params args an array of arguments for legacy command line
	*              the values are not used
	*/
	
	public static void main(String[] args)
	{
		Sorter sorter = new Sorter();
	}
	/**
	* Sorter()
	* Useage:  Sorter aSorter = new Sorter()
	* ________________________________________
	* constructor for Sorter objects.  Creates a new display, which controls
	* all of the sorting by means of call-backs to this class.
	*/
	public Sorter()
	{
		display = new SortDisplay(this);
	}

	

	public void selectionSort(Comparable[] a)
	{
		/* INSERT CODE HERE */
	}

	public void insertionSort(Comparable[] a)
	{
		/* INSERT CODE HERE */
	}

	public void mergesort(Comparable[] a)
	{
		/* INSERT CALL TO mergesortHelp HERE */
	}

	//Postcondition: a[lowIndex] to a[highIndex] are in increasing order
	private void mergesortHelp(Comparable[] a, int lowIndex, int highIndex)
	{
		/* INSERT RECURSIVE CODE HERE */
	}
	/*
	* method merge
	* Useage: merge(inputArray, lowIndex, midIndex, highIndex)
	*_______________________________________________
	* Merges the two halves of the input array into one.  The method assumes
	* that each half of the input array is sorted as follows:
	* 
	*                a[lowIndex] to a[midIndex] are in increasing order.
	*                a[midIndex + 1] to a[highIndex] are in increasing order.
	* The method creates an array to hold the output.  It then establishes 
	* two pointers into the two halves of the input array.  The values at the
	* pointer locations are compared, and the smallest is added to the output
	* array.  The corresponding pointer is then increased by one.  In the event
	* either half becomes empty, the remaining values are copied to the output
	* array.
	* Postcondition: a[lowIndex] to a[highIndex] are in increasing order.
	*
	* @param a is the input array of Comparable values
	* @param lowIndex is the index into the array a corresponding to the beginning
	*        of the first half of the array to merge
	* @param midIndex is the index of the last value in the first half of the array
	* @param highIndex is the index of the last value in the second half of the array
	*/
	private void merge(Comparable[] a, int lowIndex, int midIndex, int highIndex)
	{
		Comparable[] copy = new Comparable[a.length];
		for (int i = lowIndex; i <= highIndex; i++)
			copy[i] = a[i];
		int left = lowIndex;
		int right = midIndex + 1;
		for (int i = lowIndex; i <= highIndex; i++)
		{
			if (right > highIndex ||
				(left <= midIndex && copy[left].compareTo(copy[right]) < 0))
			{
				a[i] = copy[left];
				left++;
			}
			else
			{
				a[i] = copy[right];
				right++;
			}
			display.update();
		}
	}

	//Postcondition: a[lowIndex] to a[highIndex] are in increasing order
	public void quicksort(Comparable[] a)
	{
		/* INSERT CALL TO quicksortHelp HERE */
	}

	//performs quicksort on portion of array from lowIndex to highIndex
	private void quicksortHelp(Comparable[] a, int lowIndex, int highIndex)
	{
		/* INSERT RECURSIVE CODE HERE */
	}
	/*
	* Method partition
	* Usuage: int pivotIndex = partition(a, lowIndex, highIndex)
	*___________________________________________________________
	*
	*Returns the index of the pivot element defined as follows:
	*                All elements on the left side of the pivot (from lowIndex)
	*                are less than or equal to the pivot.
	*                All elements on the right side of the pivot (through highIndex)
	*                are greater than or equal to the pivot.
	* The computation is performed in place.
	* @param a the array to partion
	* @param lowIndex is the index of the start of the part of array a to consider
	* @param highIndex is the index of the end of the part of array a to consider
	* @return the index of the pivot element in array a
	*/
	
	private int partition(Comparable[] a, int lowIndex, int highIndex)
	{
		int pivot = lowIndex;
		for (int unsorted = lowIndex + 1; unsorted <= highIndex; unsorted++)
		{
			if (a[unsorted].compareTo(a[pivot]) < 0)
			{
				Comparable temp = a[pivot];
				a[pivot] = a[unsorted];
				display.update();
				a[unsorted] = a[pivot + 1];
				display.update();
				a[pivot + 1] = temp;
				display.update();
				pivot++;
			}
		}
		return pivot;
	}
}