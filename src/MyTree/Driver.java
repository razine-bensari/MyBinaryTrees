package MyTree;

import java.util.Comparator;

public class Driver {




    public static void main(String[] args){




        Comparator<Node> mycomp = new ComparableOfRazine();
        A3AVLTree<String> myTree2 = new A3AVLTree(mycomp);
        A3BSTree<Integer> myTree1 = new A3BSTree(mycomp);



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
        myTree1.add(456);
        myTree1.add(645);
        myTree1.add(6345);
        myTree1.add(3456);
        myTree1.add(5456);
        myTree1.add(1554);
        myTree1.add(134);
        myTree1.add(2345);
        myTree1.add(23454);
        myTree1.add(3653);



        TreePrinter.print(myTree1.getRoot());









    }






}
