
public class Book {
    public int id;
    public String name;
    public String year;
    public String author;
    public String available;
    public String date;

    public Book (int id, String name, String year, String author, String available, String date) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.available = available;
        this.date = date;
    }
    
    //table data C and A
    public Book(int id,String name, String year,String author, String available){
    	this.id = id;
    	this.name = name;
        this.year = year;
        this.author = author;
        this.available = available;
    }
    //table data r
    public Book(int id,String name, String date){
        this.id = id;
        this.name= name;
        this.date = date; 
    }
    
    public int getId(){
        return id;
    }
 
    public String getName() {
        return name;
    }
        
    public String getYear() {
        return year;
    }
    
    public String getAuthor() {
        return author;
    }
    public String getAvailable(){
        return available;
    }
    public String getDate(){
        return date;
    }
    
    public String toString(){
    	return getId()+getName()+getYear()+getAuthor()+getAvailable()+getDate();
    }
}