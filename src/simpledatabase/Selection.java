package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;
	
	ArrayList<Tuple> tuplelist = null;
	Tuple tuple = null;
	boolean next = true;
	int i = 0;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		
		
		tuplelist = new ArrayList<Tuple>();
		if(!child.from.equals(whereTablePredicate)){
			tuple = child.next();
			while(tuple != null){			
				tuplelist.add(tuple);
				tuple = child.next();
			}
		}
		else{
			tuple = child.next();
			while(tuple != null){			
				if(next == false){
					tuple = child.next();
				}
				if(tuple == null){
					break;
				}
				for(int j = 0; j < tuple.getAttributeList().size(); j++){
					if(tuple.getAttributeName(j).equals(whereAttributePredicate)){
						if(tuple.getAttributeValue(j).equals(whereValuePredicate)){
							tuplelist.add(tuple);
						}
					}
				}
				next = false;
			}
		}
	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
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
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}