package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;
	
	Attribute attribute;
	Tuple tuple = null;
	Tuple newtuple = null;
	ArrayList<Tuple> tuplelist = null;
	boolean next = true;
	int i = 0;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		
		attribute = new Attribute();
		tuplelist = new ArrayList<Tuple>();
		tuple = child.next();
		while(tuple != null){			
			if(next == false){
				tuple = child.next();
			}
			if(tuple == null){
				break;
			}
			for(int j = 0; j < tuple.getAttributeList().size(); j++){
				if(tuple.getAttributeName(j).equals(attributePredicate)){
					attribute = tuple.getAttributeList().get(j);
					newAttributeList = new ArrayList<Attribute>();
					newAttributeList.add(attribute);
					newtuple = new Tuple(newAttributeList);
					tuplelist.add(newtuple);
				}
			}
			next = false;
		}	
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
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
		return child.getAttributeList();
	}

}