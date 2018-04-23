package MyTree;

import java.util.*;

public class TreeSort {
    /**
     * Sorts an array using MyTree.TreeSort with a balanced BST implementation
     *
     * @param a an array to sort
     */


    private static Tree tree;

    static ArrayList temparray = new ArrayList();

    public static <E> E[] sort(E[] a) {
        Comparator<Node> mycomp = new ComparableOfRazine();
        tree = new A3AVLTree(mycomp);

       return (E[]) sort(tree, a);
    }

    /**
     * Sorts an array using MyTree.TreeSort with a specified BST
     *
     * @param tree tree to use
     * @param a    an array to sort
     */
    public static <E> E[] sort(Tree<E> tree, E[] a) {


        for (E anA : a) {
            tree.add((E) anA);
        }

        Node<E> current = tree.getRoot();

        inOrderTraversal(current);

        for(int k=0;k<a.length;k++){
            a[k] = (E) temparray.get(k);
        }
        return a;

    /*
       MyTree.Node current = tree.getRoot();  // start point is the root



        if (current == null) {
            return;
        }

        Stack<MyTree.Node> mystack = new Stack<MyTree.Node>();

        //first node to visit is the leftmost one
        while (current != null) {
            mystack.push(current);
            current = current.left;
        }

        // traverse the tree
        while (mystack.size() > 0) {

            // visit the top node
            temparray.add((E) mystack.peek().getElem());
            current = mystack.pop();
            if (current.right != null) {
                current = current.right;

                // the next node to be visited is the leftmost
                while (current != null) {
                    mystack.push(current);
                    current = current.left;
                }
            }
        }

        //I copy my array list to array a so i can print a in main()
        for(int k=0;k<a.length;k++){
            a[k] = temparray.get(k);
        }*/
    }

    public static <E> void inOrderTraversal(Node<E> current){
        if(current == null){
            return;
        }
        inOrderTraversal(current.left);

        temparray.add(current.getElem());

        inOrderTraversal(current.right);
    }
}