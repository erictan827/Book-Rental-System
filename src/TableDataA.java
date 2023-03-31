import java.sql.Date;


//use for the table view
public class TableDataA extends Book{

    public TableDataA(int id,String name, String year, String author, String available) {
    	super(id,name,year,author,available);
    }
    
    public int getId() {
        return super.getId();
    }
 
    public String getName() {
        return super.getName();
    }
        
    public String getYear() {
        return super.getYear();
    }
    
    public String getAuthor() {
        return super.getAuthor();
    }
    public String getAvailable(){
        return super.getAvailable();
    }
    
    @Override
    public String toString() {
    	return super.toString() + " . This is table data A";
    }
    
    
}