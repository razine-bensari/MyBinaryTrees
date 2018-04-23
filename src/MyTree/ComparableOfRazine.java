package MyTree;

import java.util.Comparator;

public class ComparableOfRazine implements Comparator<Node> {


    @Override
    public int compare(Node a, Node b) {
        if(a.getElem() instanceof Integer && b.getElem() instanceof Integer){ //if my nodes have integers as element.
            return ((Integer)(a.getElem())).compareTo((Integer)(b.getElem()));
        }
        else if(a.getElem() instanceof Double && b.getElem() instanceof Double){

            return ((Double)(a.getElem())).compareTo((Double)(b.getElem()));
        }
        else if(a.getElem() instanceof Float && b.getElem() instanceof Float){
            return ((Float)(a.getElem())).compareTo((Float)(b.getElem()));
        }
        else if(a.getElem() instanceof Character && b.getElem() instanceof Character){
            return ((Character)(a.getElem())).compareTo((Character)(b.getElem()));
        }
        else if(a.getElem() instanceof String && b.getElem() instanceof String){  // compares the string lexicographically
            return ((String)(a.getElem())).compareTo((String)(b.getElem()));
        } else {
            System.out.print("Please provide a different comparable object via the MyTree.A3BSTree constructor. This comparable object can compare only strings and integer objects.");
            System.exit(-1);
            return -1;
        }
    }


}
