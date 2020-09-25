
import java.util.*;


public class KLists 
{


    //checks to see if the input has a length or not. If it does, it continues to merge. Otherwise it returns null and 
    //a blank array is outputted
    public double[] mergeKLists(double[][] outerArray)
    {
        int inBounds = outerArray.length-1;
        if (outerArray.length == 0)
            return null;
        if (outerArray == null) 
        {
            return null;
        }
        else
        {
            double[] splitArray = split(outerArray, 0, inBounds); //runs through split array, setting the starting and ending points
            return splitArray; 
        }
    }

    //splits array
    public double[] split(double[][] arr, int start, int fin)
    {
        int first = start+1;
        if (first < fin) //checks to ensure firt elements is less than the last
        {
            int mid = (start + fin)/2; //splits array into 2 at the middle
            double[] top = split(arr, start, mid); //left side goes from start to middle of parent array
            double[] bot = split(arr, mid+1, fin); //bot starts from middle to end of parent array, essentially splitting them
            return merge(top, bot); //runs each side through the merge method
        } else if (first == fin) 
        {
            return merge(arr[start], arr[fin]);
        }
        return arr[start];
    }
    // merges the different sides into one large array
    public double[] merge(double[] top, double[] bot) 
    {
        //uses a merge sort
       int totalSize = top.length + bot.length;
       double[] mergedArr = new double[totalSize];
       int topIndex = 0; int botIndex = 0; int mergedArrIndex = 0;


       while (topIndex < top.length && botIndex < bot.length) 
       {
           if (top[topIndex] < bot[botIndex]) //merges them together in sorted fashion
           {                                        //if top side is smaller, it gets copied to the mergedArr
               mergedArr[mergedArrIndex] = top[topIndex];
               mergedArrIndex++;
               topIndex++;
           } 
           else                                     //otherwise, the bottom side gets copied into the mergedArr
           {
               mergedArr[mergedArrIndex] = bot[botIndex]; 
               mergedArrIndex++;
               botIndex++;
           }
       }

       ////if anything is left over, it copies everything 
        while (topIndex < top.length)           //if we already copied everything on the top array, then skip this while loop
        {
            mergedArr[mergedArrIndex] = top[topIndex];
            mergedArrIndex++;
            topIndex++;
        }
        while (botIndex < bot.length) 
        {
            mergedArr[mergedArrIndex] = bot[botIndex];
            mergedArrIndex++;
            botIndex++;
        }
        return mergedArr;
    }

    //toString method prints the final sorted/merged array
    public void toString(double[] sortedArray) 
    {
        //if the array was empty, it will return an empty array as well
        if(sortedArray == null) 
        {
            System.out.println("[]\n");
            return;
        }
        if(sortedArray.length == 0)
        {
            System.out.println("[]\n");
            return;
        }
        //otherwise, it will print out the merged array in ascending sorted order
        else
        {
            for (int i = 0; i < sortedArray.length; i++) 
            {
                System.out.print(sortedArray[i] + ", ");
            }
      }
        System.out.println("\n"); //adds new line for readibility
    }

    public static void main(String[] args) 
    {


       System.out.print("How many arrays would you like?\n");   //user inputs how many arrays they'd like to input
       Scanner sc=new Scanner(System.in);
       int rows=sc.nextInt();
       System.out.println("How many elements would you like in the arrays?");   //user indicates how many elements they want in the arrays
       int columns=sc.nextInt();
       
       System.out.println("Please be aware that this program assumes your elements for each array are already in sorted order.\n" +  
       "Enter your elements one at a time for array #1 : ");   //user enters the elements for each array
       
       double userArrays[][]=new double[rows][columns];
         
        for(int i=0; i<rows;i++) //it loops through, asks user to input arrays for each index of each array
         { 

            for(int j=0; j<columns;j++)
            {
                userArrays[i][j]=sc.nextDouble();
            }
            int checker = 0;
            
            if (i < (rows-1))

            System.out.println("Enter your elements for array #" + (i+2));       
         }

        KLists sorted = new KLists();
        double[] solution = sorted.mergeKLists(userArrays);

        System.out.println("Your sorted list:");
        sorted.toString(solution);

//*************************************************************************************************************************//
//I understand there are limitations for when the user inputs the data (can't do empty arrays, different sized arrays, etc.//
//so I made this block of code to test any example:)                                                                       //
//*************************************************************************************************************************//
        double[][] hardCodedEx = {{1.1, 4.4, 5.5}, {1.1, 3.3, 4.4}, {2.2, 6.6}};
        solution = sorted.mergeKLists(hardCodedEx);

        System.out.println("Sorted List from the hard-coded array:");
        sorted.toString(solution);


    }

}