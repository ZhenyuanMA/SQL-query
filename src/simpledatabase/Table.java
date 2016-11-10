package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	
	Tuple tuple = null;
	ArrayList<Tuple> tuplelist = null;
	int i = 0;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
			
			String attributeline = br.readLine();
			String datatypeline = br.readLine();
			tuplelist = new ArrayList<Tuple>();
			String thisline = null;			
			while(true){
				thisline = br.readLine();
				if(thisline != null){
					tuple = new Tuple(attributeline, datatypeline, thisline);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					tuplelist.add(tuple);
				}
				else{
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}				
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
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
		return tuple.getAttributeList();
	}
	
}