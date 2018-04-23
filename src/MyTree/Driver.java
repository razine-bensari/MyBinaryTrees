package MyTree;

import java.util.Comparator;

public class Driver {




    public static void main(String[] args){




        Comparator<Node> mycomp = new ComparableOfRazine();
        A3AVLTree<String> myTree2 = new A3AVLTree(mycomp);
        A3BSTree<String> myTree1 = new A3BSTree(mycomp);



        myTree2.add("salut");
        myTree2.add("chams");
        myTree2.add("razine");
        myTree2.add("annes");
        myTree2.add("aaa");
        myTree2.add("zari");
        myTree2.add("Annes");
        myTree2.add("AAA");
        myTree2.add("yo");
        myTree2.add("tcouol ");
        myTree2.add("A llo");



        TreePrinter.print(myTree2.getRoot());





        myTree1.add(1);
        myTree1.add(2);
        myTree1.add(3);
        myTree1.add(3);
        myTree1.add(4);
        myTree1.add(5);
        myTree1.add(6);
        myTree1.add(7);
        myTree1.add(8);
        myTree1.add(9);
        myTree1.add(10);



        TreePrinter.print(myTree1.getRoot());









    }






}
