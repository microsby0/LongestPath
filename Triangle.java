import java.io.*;
import java.util.*;

public class Triangle{

    public static void main(String [] args){

        Triangle t = new Triangle();

        BufferedReader in = null;
        ArrayList<ArrayList<Node>> list = null;
        Node head = null;
        Node maxNode = null;


        try{
            in = new BufferedReader(new FileReader("triangle.txt"));
            list = t.createListFromFile(in);
        }catch(FileNotFoundException e){
        	System.out.println("File not found\n");
            e.printStackTrace();
        }catch (IOException e) {
			System.out.println("IO issue:\n");
			e.printStackTrace();
		}


        if(list!=null)
        	head = t.buildTree(list);
        head.setTotal(head.getValue());

        LinkedList<Node> queue = new LinkedList<Node>();
        Node current,leftChild,rightChild;
        queue.add(head);
        maxNode=head;
        while(true){ //while you havent reached the last row, need to improve this later instead of breaking
    		current= queue.pop();
    		leftChild=current.getLChild();
    		rightChild=current.getRChild();
    		if(rightChild == null && leftChild==null) //when you hit the bottom
    			break;
    		if(!queue.contains(leftChild)) //if the node isnt already in the queue
    			queue.add(leftChild);
    		if(!queue.contains(rightChild))
    			queue.add(rightChild);

    		//check if node "path" or total is the greatest possible
    		if((current.getTotal() + leftChild.getValue()) > leftChild.getTotal()){
    			leftChild.setTotal(current.getTotal() + leftChild.getValue());
    			if(leftChild.getTotal() > maxNode.getTotal()){
    				maxNode = leftChild;
    				maxNode.setPathParent(current);
    			}
    		}
    		if((current.getTotal() + rightChild.getValue()) > rightChild.getTotal()){
    			rightChild.setTotal(current.getTotal()+rightChild.getValue());
    			if(rightChild.getTotal() > maxNode.getTotal()){
    				maxNode = rightChild;
    				maxNode.setPathParent(current);
    			}
    		}
        }

        System.out.println("Max total is: " + maxNode.getTotal());
    }

    //attaches nodes to their children
    //stops at bottom row because THEY HAVE NO CHILDREN -_-
    private Node buildTree(ArrayList<ArrayList<Node>> list){
        Node head = list.get(0).get(0);
        int currentRow=0;
        int currentCol=0;
        for(ArrayList<Node> row:list){
        	if(list.size()==currentRow+1)
        		break;
        	for(Node node:row){
        		node.setLChild(list.get(currentRow+1).get(currentCol));
        		node.setRChild(list.get(currentRow+1).get(currentCol+1));
        		currentCol+=1;
        	}
        	currentRow+=1;
        	currentCol=0;
        }

        return head;
    }


    //creates 2d array list of nodes from triangle file
    //each inner arraylist is a row of the triangle
    private ArrayList<ArrayList<Node>> createListFromFile(BufferedReader in) throws IOException{
    	ArrayList<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();
    	ArrayList<Node> temp;
    	String line = in.readLine();
    	while(line!=null){
    		temp = new ArrayList<Node>();
    		for(String s:line.split(" ")){
    			temp.add(new Node(s));
    		}
    		list.add(temp);
    		line=in.readLine();
    	}
    	return list;
    }

}