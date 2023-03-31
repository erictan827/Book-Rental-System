public class TableDataR extends Book{
    
    //constructor with parameter
    public TableDataR(int id,String name, String date){
        super(id, name, date); 
    }
    
    //get methods
    public int getId(){
        return super.getId();
    }
    public String getName(){
        return super.getName();
    }
    public String getDate(){
        return super.getDate();
    }
    
    @Override
    public String toString() {
    	return super.toString();
    }
    
}