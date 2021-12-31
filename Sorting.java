import java.util.Scanner;
import java.util.Random;
import java.util.Timer;

/* Fouzan Abdullah
 * 6840797                */

public class Sorting {
  
  public static Scanner scan = new Scanner(System.in);
  public static int size = scan.nextInt();  //Setting a user selected size for all arrays.
  public static int[] randomNumbers = new int[size];  
  public static int[] heapSortedNumbers = new int[randomNumbers.length];
  public static int[] mergeSortedNumbers = new int[randomNumbers.length];
  public static int[] radixSortedNumbers = new int[randomNumbers.length];
  public static int[] quickSortedNumbers = new int[randomNumbers.length];
  public static int[] bubbleSortedNumbers = new int[randomNumbers.length];
  public static int computationTime  = 0;
  public static Timer timer;
  public static int interval;
  
  public static void generateRandom(){
    Random rd = new Random();
    int min = 0;
    int max = 10000000;
    int count;
    int temp;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter how many values you want in the range: ");
    count = scanner.nextInt();
    int[] randomvalue = new int[count];
    
    for(int i=0; i < count; i++)
    {
    randomvalue[i] = rd.nextInt(max-min) + min;
    System.out.println(randomvalue[i]);
    }
    
    for (int i = 0; i < count; i++)
    {
      for (int j = i + 1; j < count; j++) { 
        if (randomvalue[i] > randomvalue[j]) {
          temp = randomvalue[i];
          randomvalue[i] = randomvalue[j];
          randomvalue[j] = temp;
        }
      }
    }
    System.out.print("Elements in ascending order: ");
    for (int i = 0; i < count - 1; i++)
    {
      System.out.print(randomvalue[i] + ", ");
    }
    System.out.println(randomvalue[count - 1]);
  }

  
  // POPULATE ARRAY 
  
  public static int[] PopulateArray(int upper, int lower, int length)
  { 
   int [] newArr = new int[length];  
   Random rd = new Random();
   
   for (int i = 0; i<newArr.length; i++)
   { 
     int val = rd.nextInt(upper-lower) + lower;
     newArr[i] = val;
   }
   return newArr; 
  }

  
 // HEAP SORT ALGORITHN
  
 public static void HeapSort()  
  {
   int startingIndex = 0;
   for (int i = (heapSortedNumbers.length / 2) - 0; i >= 0; i--)
   {
     createHeap(heapSortedNumbers, heapSortedNumbers.length, i);  
   }
   
   for (int j=heapSortedNumbers.length-1; j>=0; j--)  
   {  
     int tempVal = heapSortedNumbers[0];  
     heapSortedNumbers[0]= heapSortedNumbers[j];   
     heapSortedNumbers[j] = tempVal;  
     createHeap(heapSortedNumbers, j, startingIndex);  
   }  
  }
 
 // BUILDING THE HEAP (HEAPIFY)
 
 public static void createHeap (int[] numbers,int lngth, int idx)
 {
   int max = idx;    
   int lft = 2*idx + 1;    
   int rght = 2*idx + 2;    
   
   if ( (lft < lngth) && (numbers[lft] > numbers[max]) )  
   {
    max = lft;
   }
   if ( (rght < lngth) && (numbers[rght] > numbers[max]) ) 
   {
     max = rght;
   }
   if (max != idx)  
   {  
     int tempVal = numbers[idx];  
     numbers[idx]= numbers[max];   
     numbers[max] = tempVal;  
     createHeap(numbers, lngth, max);  
   }
   
 }
  
 
 // MERGE SORT
 
public static void MergeArrays(int current_numbers[], int start_point, int mid_point, int end_point)  
 {  
   int lft = mid_point - start_point + 1;  
   int rght = end_point - mid_point;  
   
   int left_side [] = new int [lft];  
   int right_side[] = new int [rght];  
   
   for (int i=0; i<lft; ++i) 
   {
     left_side[i] = current_numbers[start_point + i];  
   }
   
   for (int j=0; j<rght; ++j)  
   {
     right_side[j] = current_numbers[mid_point + 1+ j];  
   }
   
   int a = 0, b = 0;  
   int c = start_point;  
   while (a<lft && b<rght)  
   {  
     if (left_side[a] <= right_side[b])  
     {  
       current_numbers[c] = left_side[a];  
       a++;  
     }  
     else  
     {  
       current_numbers[c] = right_side[b];  
       b++;  
     }  
     c++;  
   }  
   while (a<lft)  
   {  
     current_numbers[c] = left_side[a];  
     a++;  
     c++;  
   }  
   
   while (b<rght)  
   {  
     current_numbers[c] = right_side[b];  
     b++;  
     c++;  
   }  
 }  
 
 public static void MergeSort(int start_point, int end_point)  
 {  
   if (start_point<end_point)  
   {  
     int mid_point = (start_point+end_point)/2;  
     MergeSort(start_point, mid_point);  
     MergeSort(mid_point+1, end_point);  
     MergeArrays(mergeSortedNumbers, start_point, mid_point,end_point);  
   }
   
 }  

 
 // RADIX SORT
 
 public static void RadixSort()
 {
   int space[][]=new int[radixSortedNumbers.length][radixSortedNumbers.length];  
   int space_length[]=new int[radixSortedNumbers.length];  
   int rem;
   int n=0;
   int div=1;
   int max;
   max = 0;  
   for(int i=1;i<radixSortedNumbers.length;i++)
   {
     if(radixSortedNumbers[i]>max)
     {
       max = radixSortedNumbers[i]; 
     }
   }
   while(max>0)
   {
     n++;  
     max = max/radixSortedNumbers.length;
   }
   for(int skip=0;skip<n;skip++)
   {
     for(int i=0;i<radixSortedNumbers.length;i++)
       space_length[i]=0;
     for(int i=0;i<radixSortedNumbers.length;i++)
     {
       rem = (radixSortedNumbers[i]/div)%radixSortedNumbers.length;  
       space[rem][space_length[rem]] = radixSortedNumbers[i];  
       space_length[rem] += 1;
     }
     int i=0;
     for(int k=0;k<radixSortedNumbers.length;k++)
     {
       for(int j=0;j<space_length[k];j++)
       {
         radixSortedNumbers[i] = space[k][j];  
         i++;
       }
     }
     div = div * radixSortedNumbers.length;
   }  
 }
 
  // BUBBLE SORT
 
 public static void BubbleSort() 
 {
   int n = bubbleSortedNumbers.length;  
   int temp = 0;
   for(int i=0; i < n; i++)
   {
     for(int j=1; j < (n-i); j++)
     {
       if(bubbleSortedNumbers[j-1] > bubbleSortedNumbers[j])
       {
        temp = bubbleSortedNumbers[j-1];  
        bubbleSortedNumbers[j-1] = bubbleSortedNumbers[j];  
        bubbleSortedNumbers[j] = temp;
       }
     }
   }
  }
 
  // QUICK SORT
 
 public static void QuickSort(int current_numbers[], int start_point, int end_point)  
    {  
        if(start_point<end_point)  
        {  
            int tmp;
            int loc = start_point;
            int lft =  start_point;
            int rght = end_point;  
            int signal = 0;  
            while(signal != 1)  
            {  
              while((current_numbers[loc] <= current_numbers[rght]) && (loc!=rght)){  
                rght--;  
              }
              if(loc==rght){  
                signal =1;  
              }
              else if(current_numbers[loc]>current_numbers[rght])  
              {  
                tmp = current_numbers[loc];  
                current_numbers[loc] = current_numbers[rght];  
                current_numbers[rght] = tmp;  
                loc = rght;  
              }  
              if(signal!=1)  
              {  
                while((current_numbers[loc] >= current_numbers[lft]) && (loc!=lft)){  
                  lft++;  
                }
                if(loc==lft){  
                  signal =1;  
                }
                else if(current_numbers[loc] <current_numbers[lft])  
                {  
                  tmp = current_numbers[loc];  
                  current_numbers[loc] = current_numbers[lft];  
                  current_numbers[lft] = tmp;  
                  loc = lft;  
                }  
              }  
            }  
            QuickSort(current_numbers, start_point, loc-1);  
            QuickSort(current_numbers, loc+1, end_point);  
        }  
    }  
 
 public static void printNumbers(int[] numbers, String message)
 {
 System.out.println("Printing Sorted Elements Using "+message);  
    for (int i=0; i<numbers.length; ++i) 
    {
      System.out.print(numbers[i]+" ");  
    }
 }

  public static void main(String[] args)
  {

    //The following code is for the range 1000-10000
    
    long t1 = System.nanoTime();
    
    Scanner scan = new Scanner(System.in);
    
    System.out.print("\nEnter lower bound of first array: ");
    
    int lowerBound = scan.nextInt();
    
    System.out.print("\nEnter upper bound of first array: : ");
    
    int upperBound = scan.nextInt();
    
    System.out.print("\nEnter array size of first array: : ");
    
    int size = scan.nextInt();
    
    System.out.print("\nPress Y to insert values and otherwise to automatically generate random values within upper and lower bounds: ");
    
    char signal = scan.next().charAt(0);
    
    int[] randomNumbers = new int[size];
    
    if(signal == 'Y')
    {
      System.out.print("\nEnter integer elements separated by comma of first array: : ");
      String elements = scan.next();
      String split[] = elements.split(",");
      
      for (int i = 0; i<randomNumbers.length; i++) 
      {
        randomNumbers[i] = Integer.parseInt(split[i]);
      }
    }
    else
    { 
       System.out.print("\nPopulating first array with random numbers: \n");
       randomNumbers = PopulateArray(upperBound,lowerBound, size);
       System.out.println(randomNumbers);
    }
    System.arraycopy(randomNumbers, 0, heapSortedNumbers,0,randomNumbers.length);
    
    HeapSort();
  
    printNumbers(heapSortedNumbers,"Heap Sort");
    
    System.out.print("\n\n");
    
    System.arraycopy(randomNumbers, 0, mergeSortedNumbers,0,randomNumbers.length);
    
    MergeSort(0, mergeSortedNumbers.length-1);
  
    printNumbers(mergeSortedNumbers,"Merge Sort");
    
    System.out.print("\n\n");

    System.arraycopy(randomNumbers, 0, radixSortedNumbers,0,randomNumbers.length);
    
    RadixSort();
  
    printNumbers(radixSortedNumbers,"Radix Sort");
    
    System.out.print("\n\n");
    
    System.arraycopy(randomNumbers, 0, quickSortedNumbers,0,randomNumbers.length);
    
    QuickSort(quickSortedNumbers, 0, quickSortedNumbers.length-1);  
  
    printNumbers(quickSortedNumbers,"Quick Sort");
    
    System.out.print("\n\n");
    
    System.arraycopy(randomNumbers, 0, bubbleSortedNumbers,0,randomNumbers.length);
    
    BubbleSort();  
  
    printNumbers(bubbleSortedNumbers,"Bubble Sort");
    
    System.out.print("\n\n");
    
    long t = System.nanoTime() - t1;
    System.out.println("Time taken in nano seconds for the range 1000-10000: " + t);
    
    //1000-10000 range code ends here
    
    //The following code is for range 5000-10000
    
    long l1 = System.nanoTime();
    
     System.out.print("\n\n\n\n\n");
     System.out.print("\nEnter lower bound of second array: ");
     lowerBound = scan.nextInt();

    System.out.print("\nEnter upper bound of second array: : ");
    upperBound = scan.nextInt();
    
    System.out.print("\nEnter array size of second array: : ");
    size = scan.nextInt();
    
    System.out.print("\nPress Y to insert values and otherwise to automatically generate random values within upper and lower bounds: ");
    signal = scan.next().charAt(0);
    
    int[] randomArray2 = new int[size];
    
    if(signal == 'Y')
    {
      System.out.print("\nEnter integer elements separated by comma of second array: : ");
      String elements = scan.next();
      String split[] = elements.split(",");
      
      for (int i = 0; i<randomArray2.length; i++) 
      {
        randomArray2[i] = Integer.parseInt(split[i]);
      }
    }
    else
    {
       System.out.print("\nPopulating second array with random numbers: \n");
       randomArray2 = PopulateArray(upperBound,lowerBound, size);  
       System.out.println(randomArray2);
    }
     scan.close();
     
    System.arraycopy(randomNumbers, 0, heapSortedNumbers,0,randomNumbers.length);
    
    HeapSort();
  
    printNumbers(heapSortedNumbers,"Heap Sort");
    
    System.out.print("\n\n");
    
    System.arraycopy(randomNumbers, 0, mergeSortedNumbers,0,randomNumbers.length);
    
    MergeSort(0, mergeSortedNumbers.length-1);
  
    printNumbers(mergeSortedNumbers,"Merge Sort");
    
    System.out.print("\n\n");

    System.arraycopy(randomNumbers, 0, radixSortedNumbers,0,randomNumbers.length);
    
    RadixSort();
  
    printNumbers(radixSortedNumbers,"Radix Sort");
    
    System.out.print("\n\n");
    
    System.arraycopy(randomNumbers, 0, quickSortedNumbers,0,randomNumbers.length);
    
    QuickSort(quickSortedNumbers, 0, quickSortedNumbers.length-1);  
  
    printNumbers(quickSortedNumbers,"Quick Sort");
    
    System.out.print("\n\n");
    
    System.arraycopy(randomNumbers, 0, bubbleSortedNumbers,0,randomNumbers.length);
    
    BubbleSort();  
  
    printNumbers(bubbleSortedNumbers,"Bubble Sort");
    
    System.out.print("\n\n");
    
    long l = System.nanoTime() - l1;
    System.out.println("Time taken in nano seconds: " + l);
  } 
}