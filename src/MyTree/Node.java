package MyTree;

public class Node<E> implements TreePrinter.PrintableNode{

    private E elem;
    private int height;
    Node left;
    Node right;

    public Node(E elem){
        this.elem = elem;
        left = null;
        right = null;
    }

    public Node(Node<E> node){
        this.elem = node.getElem();
        this.left = node.left;
        this.right = node.right;
    }

    int height(Node<E> node) {
        if (node == null){
            return 0;
        }
        return node.height;
    }

    public E getElem() {
        return elem;
    }

    public void setElem(E elem) {
        this.elem = elem;
    }

    public int compareTo(Node b){
        if(this.getElem() instanceof Integer && b.getElem() instanceof Integer){ //if my nodes have integers as element.
            return ((Integer)(this.getElem())).compareTo((Integer)(b.getElem()));
        }
        else if(this.getElem() instanceof Double && b.getElem() instanceof Double){

            return ((Double)(this.getElem())).compareTo((Double)(b.getElem()));
        }
        else if(this.getElem() instanceof Float && b.getElem() instanceof Float){
            return ((Float)(this.getElem())).compareTo((Float)(b.getElem()));
        }
        else if(this.getElem() instanceof Character && b.getElem() instanceof Character){
            return ((Character)(this.getElem())).compareTo((Character)(b.getElem()));
        }
        else if(this.getElem() instanceof String && b.getElem() instanceof String){  // compares the string lexicographically
            return ((String)(this.getElem())).compareTo((String)(b.getElem()));

        }
        else{
            System.out.print("Please provide a different comparable object via the MyTree.A3BSTree constructor. This comparable object can compare only strings and integer objects.");
            System.exit(-1);
            return -1;
        }
    }

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return getElem().toString();
    }
}
