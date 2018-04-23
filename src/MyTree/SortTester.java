package MyTree;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortTester {

    static PrintWriter pw = null;

   static  Comparator<Node> mycomp; //= new MyTree.ComparableOfRazine();  //using my comparator method
    static Tree<Integer> BSTree; // = new MyTree.A3BSTree(mycomp);        // creating my two different trees
   static  Tree<Integer> AVLTree; // = new MyTree.A3AVLTree(mycomp);



    public static void main(String[] args) {


        try{
            pw = new PrintWriter(new FileOutputStream(new File("testrun.txt")));


            pw.println("Razine Ahmed Bensari     40029076");





            for (int i = 10; i < 10001; i = i * 10) {
                mycomp = new ComparableOfRazine();  //using my comparator method
                BSTree = new A3BSTree(mycomp);        // creating my two different trees
                AVLTree = new A3AVLTree(mycomp);
                long start=0;
                long stop=0;
                long time=0;
                Integer a[] = new Integer[i];




                for (int j = 0; j < a.length; j++) {// I fill out my array (initialization)
                    a[j] = j;
                }

                Integer reversesorted[]  = new Integer[i];
                for(int k=0; k<a.length;k++){        //reversing the array and storing it.
                    reversesorted[k] = a[a.length-1-k];
                }

                pw.printf("Initial%n%s%n", Arrays.toString(a)); //print my array

                Collections.shuffle(Arrays.asList(a));
                pw.printf("Shuffled(Unsorted)%n%s%n", Arrays.toString(a)); // print shuffled array


                start = System.nanoTime();
                TreeSort.sort(BSTree, a);
                stop = System.nanoTime();
                time = stop - start;
                pw.println("For N = " + i);
                pw.println("BST: \t\t\t" + time + " ns");
                pw.printf("Sorted%n%s%n", Arrays.toString(TreeSort.sort(BSTree, a)));


                start = System.nanoTime();
                TreeSort.sort(a);
                stop = System.nanoTime();
                time = stop - start;
                pw.println("For N = " + i);
                pw.println("AVL: \t\t\t" + time + " ns");
                pw.printf("Sorted%n%s%n", Arrays.toString(TreeSort.sort(a)));


                start = System.nanoTime();
                TreeSort.sort(BSTree, reversesorted);
                stop = System.nanoTime();
                time = stop - start;
                pw.println("BST (ReverSorted): \t\t\t" + time + " ns");
                pw.printf("Sorted%n%s%n", Arrays.toString(TreeSort.sort(BSTree, reversesorted)));


                start = System.nanoTime();
                TreeSort.sort(reversesorted);
                stop = System.nanoTime();
                time = stop - start;
                pw.println("AVL (ReverSorted): \t\t\t" + time + " ns");
                pw.printf("ReverSorted%n%s%n", Arrays.toString(TreeSort.sort(reversesorted)));
                pw.println("\n\n\n");

            }
        }catch (Exception e){
            System.out.print(e.getMessage());
        } finally {
            pw.close();
        }
    }

}