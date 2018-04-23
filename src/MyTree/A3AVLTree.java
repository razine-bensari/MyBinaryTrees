package MyTree;/*
* The purpose of the programming questions in this assignment is to evaluate two implementations of
binary search trees in terms of their performance for different insertion and deletion operations. The
trees will then be tested to implement a MyTree.TreeSort sorting algorithm.
Binary Search Trees (BST) are data structures that store “items” (such as numbers, names etc.) in
memory. They allow fast lookup, addition and removal of items, and can be used to implement
either dynamic sets of items, or lookup tables that allow finding an item by its key.
The MyTree.Tree interface provides three methods to add and remove elements to and from the tree. It
also provides an iterator that visits the elements in-order, as well as a function height that simply
returns the height of the tree. Note that the speed of these operations may strongly depend on the
implementation.
In this assignment, you will have to write two implementations of MyTree.Tree interface (provided), one
that uses regular (possibly unbalanced) Binary Search Trees1
, and one that uses balanced
AVL Trees2
. After that, you will have to test the performance of TreeSort3 when using your
implementations. For your convenience, some starting code is provided. Note that it either does not
implement some features or implements them improperly.
Question 1:
Implement the necessary methods in the two implementations (called MyTree.A3BSTree and
MyTree.A3AVLTree) of MyTree.Tree interface:
public void add(E e);
public void addAll (Collection<? extends E> c);
public boolean remove(Object o);
public Iterator<E> iterator();
public int height();
public int size();
The classes should use generics. The AVL add and remove operation should keep the tree
balanced. It should also be possible to have duplicate items in the trees (something that binary search
trees normally do not allow); think about how you can work around it.
You are free to implement any private or protected methods and classes as you see needed.
However, you may not have any public methods other than the ones mentioned (or the ones present
in the interface or its super classes).

* */


import java.util.*;
import java.util.function.Consumer;

public class A3AVLTree <E> implements Tree<E>{

        private Node<E> root;
        private Comparator<Node<E>> comparator;

        public A3AVLTree(){

            root = null;
            Comparator comparator = null;
        }
        public A3AVLTree(Comparator<Node<E>> comparator){
            // the user input its own comparable method if the BST is not of numbers or strings (it is of object).
            this.comparator = comparator;
        }
        @Override
        public Node<E> getRoot(){
            return root;
        }



        private Node<E> rotateRight(Node<E> node) {
            Node<E> temp = node.left;
            Node<E> subtree = temp.right;

            temp.right = node;
            node.left = subtree;

            return temp;
        }
        private Node<E> rotateLeft(Node<E> node) {
            Node<E> temp = node.right;
            Node<E> subtree = temp.left;


            temp.left = node;
            node.right = subtree;

            return temp;
         }

        private int getDeltaHeight(Node<E> node) {
            if (node == null)
             return 0;

            return heightRecursive(node.left) - heightRecursive(node.right);
        }


        @Override
        public void add(E e) {
            Node<E> nodeToInsert = new Node<E>(e);
            root = add(root, nodeToInsert);
        }
        private Node<E> add(Node<E> current, Node<E> nodeToInsert){  //this private methods is used so i can traverse the tree recursively.
            if(current == null){
                return new Node<E>(nodeToInsert);
            }
            if(comparator.compare(nodeToInsert, current) >= 0) {
                current.right = add(current.right, nodeToInsert);
            }
            else if(comparator.compare(nodeToInsert, current) < 0) {
                current.left = add(current.left, nodeToInsert);
            } else {
                return current;
            }


            //Now that it has been inserted, give it its height
            int delta = getDeltaHeight(current);

            //Delta is bigger than 2 then this means this node has become unbalanced due to the add method.
            //Fom now on, 4 case move can occur depending on 2 conditions each. a left left move, right right move, left right move and right left move

            //Double rotation to the left
            if(delta > 1 && (comparator.compare(nodeToInsert, current.left) < 0)){
                    return rotateRight(current);
            }
            //Double rotation to the right
            if(delta < -1 && (comparator.compare(nodeToInsert, current.right) >= 0)){
                return rotateLeft(current);
            }
            //rotation right then left
            if(delta < -1 && (comparator.compare(nodeToInsert, current.right) < 0)){
                current.right = rotateRight(current.right);
                return rotateLeft(current);
            }
            //rotation left then right
            if(delta > 1 && (comparator.compare(nodeToInsert, current.left) >= 0)){
                current.left = rotateLeft(current.left);
                return rotateRight(current);
            }

            return current;
        }

        @Override
        public void addAll(Collection<? extends E> c) {
            for(E e : c){
                add(e);
            }
        }

        @Override
        public boolean remove(E e) {
            boolean success = false;
            Node<E> copyOfNodeToDelete = new Node<E>(e);
            root = removeRecursively(root, copyOfNodeToDelete, success);
            return success;
        }

    private Node<E> removeRecursively(Node<E> current, Node<E> copyOfNodeToDelete, boolean success){

        //If there is no root
        if(current == null){
            throw new RuntimeException("There is no Root node.");
        }
        else if(comparator.compare(copyOfNodeToDelete, current) > 0){
            current.right = removeRecursively(current.right, copyOfNodeToDelete, success);
        }
        else if(comparator.compare(copyOfNodeToDelete, current) < 0){
            current.left = removeRecursively(current.left, copyOfNodeToDelete, success);
        } else { //If I enter this statement I have found my node that is equal
            if (current.right == null && current.left != null) {
                current.setElem((E) current.left.getElem());  //replacing my node value with the left
                current.left = current.left.left;               //changing my pointer to current.left's subtree (null if last value)
                success = true;
            } else if (current.right != null && current.left == null) {  //right
                current.setElem((E) current.right.getElem());       //replacing my node value with the right
                current.right = current.right.right;                //changing my pointer to current.right's subtree (null if last value)
                success = true;
            } else if (current.right == null && current.left == null) {
                current = null;
                success = true;
            } else {    //if both children are non null leafs
                current.setElem((E) inOrderValue(current.right));     //then give me the outmost left leafs data in the subtree (using inorder pattern)
                success = true;
            }
        }


            if(root == null){
                return root;
            }

            //Now that it has been deleted, give it its height
            int delta = getDeltaHeight(current);

            //Delta is bigger than 2 then this means this node has become unbalanced due to the add method.
            //Fom now on, 4 case move can occur depending on 2 conditions each. a left left move, right right move, left right move and right left move

            //Double rotation to the Left
            if(delta > 1 && (getDeltaHeight(current.left)) >=0){
                return rotateRight(current);
            }
            //toation left then right
            if(delta > 1 && (getDeltaHeight(current.left)) < 0){
                current.left = rotateLeft(current.left);
                return rotateRight(current);
            }
            //Double rotation right
            if(delta < -1 && getDeltaHeight(current.right) <= 0){
                return rotateLeft(current);
            }
            //rotation left then right
            if(delta < -1 && (getDeltaHeight(current.right) >= 0)){
                current.right = rotateRight(current.right);
                return rotateLeft(current);
            }

            return current;
    }




        private E inOrderValue(Node<E> current) {
            E temp;
            while (current.left != null){
                current = current.left;
            }
            temp = current.getElem();      //saves the element to be returned
            current = null;              // deletes that node

            return temp;           //return the element of the outmost left node in the right subtree (in order fashion)
        }


        @Override
        public Iterator<E> iterator() {
            return new A3AVLTreeIterator<>();
        }
        private class A3AVLTreeIterator<E> implements Iterator<E> {      //inner class iterator

            public Stack<Node<E>> mystack = new Stack<>();
            Node<E> current;


            public A3AVLTreeIterator(){
                current = (Node<E>) root;
            }

            @Override
            public boolean hasNext() {
                return (!mystack.isEmpty() || current != null);
            }

            //InOrder iteration
            @Override
            public E next() {
                while (current != null) {
                    mystack.push(current);
                    current = current.left;
                }

                current = mystack.pop();
                Node node = current;
                current = current.right;

                return (E) node.getElem();
            }



            //InOrder Iteration
            @Override
            public void forEachRemaining(Consumer action) { // I dont need it for the purpose of this assignment

            }
            @Override
            public void remove() {     // I dont need it for the purpose of this assignment

            }
        }

        public int height()
        {
            return heightRecursive(root);
        }

        private int heightRecursive(Node<E> current)
        {
            if(current == null) {
                return -1;
            } else {
                // recursively returns the height of the tree. method max return the maximum between the two values
                return (1 + Math.max( heightRecursive(current.left), heightRecursive(current.right)));
            }

        }

        @Override
        public int size()
        {
            return sizeRecursive(root);
        }
        private int sizeRecursive(Node<E> current)
        {
            if(current == null) return 0;
            else
            if(current.left == null && current.right == null){
                return 1;
            }
            else {
                return (sizeRecursive(current.left) + sizeRecursive(current.right));
            }
        }

}


