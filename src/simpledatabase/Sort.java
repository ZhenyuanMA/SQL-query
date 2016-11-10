package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	Attribute attribute;
	Tuple tuple = null;
	ArrayList<Tuple> tuplelist = null;
	int order = 0;
	int i = 0;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
		tuplelist = new ArrayList<Tuple>();
		tuple = child.next();
		for(int a = 0; a < tuple.getAttributeList().size(); a++){
			if(tuple.getAttributeList().get(a).getAttributeName().equals(orderPredicate)){
				order = a;
			}
		}
		while(tuple != null){			
			tuplesResult.add(tuple);
			tuple = child.next();
		}
		while(!tuplesResult.isEmpty()){
			tuple = tuplesResult.get(0);
			for(int j = 0; j < tuplesResult.size(); j++){
				if(tuple.getAttributeList().get(order).getAttributeValue().toString().compareTo(tuplesResult.get(j).getAttributeList().get(order).getAttributeValue().toString()) > 0){
					tuple = tuplesResult.get(j);
				}
			}
			tuplelist.add(tuple);
			tuplesResult.remove(tuplesResult.get(tuplesResult.indexOf(tuple)));
		}
	}
	
	
	/**
     * The function is used to return the sorted tuple
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