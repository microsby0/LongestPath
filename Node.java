

public class Node{

    private Node lChild;
    private Node rChild;
    private int value;
    private int total;
    private Node pathParent;

	public Node(String value){
        this.lChild=null;
        this.rChild=null;
        this.total=0;
        if(value!=null){
            this.value=Integer.parseInt(value);
        }
    }

    public void setLChild(Node lChild){
        this.lChild=lChild;
    }

    public void setRChild(Node rChild){
        this.rChild=rChild;
    }

    public void setValue(int value){
        this.value=value;
    }

    public void setTotal(int total){
        this.total=total;
    }

    public Node getLChild(){
        return this.lChild;
    }

    public Node getRChild(){
        return this.rChild;
    }

    public int getValue(){
        return this.value;
    }

    public int getTotal(){
        return this.total;
    }

    public Node getPathParent() {
		return pathParent;
	}

	public void setPathParent(Node pathParent) {
		this.pathParent = pathParent;
	}

}