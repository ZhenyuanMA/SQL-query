package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1 = null;
	
	ArrayList<Tuple> tuplelist = null;
	Attribute attribute;
	Tuple tuple = null;
	Tuple tuple1 = null;
	boolean next = true;
	int i = 0;
	int left;
	int right;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
		attribute = new Attribute();
		tuplelist = new ArrayList<Tuple>();
		if(rightChild.getClass().getName().equals(joinPredicate)){
			tuple = leftChild.next();
			tuple1 = rightChild.next();
			for(int j = 0; j < tuple.getAttributeList().size(); j++){
				for(int k = 0; k < tuple1.getAttributeList().size(); k++){
					if(tuple.getAttributeName(j).equals(tuple1.getAttributeName(k))){
						left = j;
						right = k;
						//break;
					}
				}
			}
			while(tuple1 != null){
				tuples1.add(tuple1);
				tuple1 = rightChild.next();
			}				
			while(tuple != null){
				if(next == false){
					tuple = leftChild.next();
				}
				if(tuple == null){
					break;
				}
				for(int a = 0; a < tuples1.size(); a++){
					if(tuple.getAttributeValue(left).equals(tuples1.get(a).getAttributeValue(right))){
						int b = 0;
						while(b < tuples1.get(a).getAttributeList().size()){
							if(b == right){
								b++;
							}
							else{
								attribute = tuples1.get(a).getAttributeList().get(b);
								tuple.getAttributeList().add(attribute);
								b++;
							}							
						}
						tuplelist.add(tuple);
					}
				}
				next = false;				
			}
		}
		else{
			tuple = rightChild.next();
			tuple1 = leftChild.next();
			for(int j = 0; j < tuple.getAttributeList().size(); j++){
				for(int k = 0; k < tuple1.getAttributeList().size(); k++){
					if(tuple.getAttributeName(j).equals(tuple1.getAttributeName(k))){
						right = j;
						left = k;
						//break;
					}
				}
			}
			while(tuple1 != null){
				tuples1.add(tuple1);
				tuple1 = leftChild.next();
			}				
			while(tuple != null){
				if(next == false){
					tuple = rightChild.next();
				}
				if(tuple == null){
					break;
				}
				for(int a = 0; a < tuples1.size(); a++){
					if(tuple.getAttributeValue(right).equals(tuples1.get(a).getAttributeValue(left))){
						int b = 0;
						while(b < tuples1.get(a).getAttributeList().size()){
							if(b == left){
								b++;
							}
							else{
								attribute = tuples1.get(a).getAttributeList().get(b);
								tuple.getAttributeList().add(attribute);
								b++;
							}
						}
						tuplelist.add(tuple);
					}
				}
				next = false;				
			}
		}		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		if(i < tuplelist.size()){			
			tuple = tuplelist.get(i);
			i++;
			return tuple;
		}
		else{
			return null;
		}
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}