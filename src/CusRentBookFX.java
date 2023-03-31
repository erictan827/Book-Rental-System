import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;

public class CusRentBookFX extends Application{
    
    PreparedStatement preparedStatement;
    Statement statement ;
    ResultSet result;
    Connection con;
    
    //Login scene
    Image img1L = new Image("image/image1L.PNG",160,120,false,false);
    Label lblImage1L = new Label();
    TextField name1L = new TextField();
    PasswordField password1L = new PasswordField();
    Button btnLogin1L = new Button("Login");
    Button btnRegister1L = new Button("Register");
    
    //Register scene
    Image img1R = new Image("image/image1R.PNG",160,120,false,false);
    Label lblImage1R = new Label();
    TextField name1R = new TextField();
    PasswordField password1R = new PasswordField();
    PasswordField password2R = new PasswordField();
    Button btnRegister1R = new Button("Register");
    Button btnClear1R = new Button("Clear");
    Button btnBack1R = new Button("Back");
    
    //Admin scene
    Button addBtnA = new Button("Add");
    Button findBtnA = new Button("Find");
    Button updateBtnA = new Button("Update");
    Button clearBtnA = new Button("Clear");
    Button deleteBtnA = new Button("Delete");
    Button LogoutCBtn = new Button("Logout");    
    TextField tfBookNameA = new TextField();
    TextField tfBookYearA = new TextField();
    TextField tfBookAuthorA = new TextField();
    TextField tfAvailable = new TextField();
    TextField tfSearchA = new TextField();
    TextArea printdetailsA = new TextArea();
    
    //Radio Button
    RadioButton rbYesA = new RadioButton("Yes");
    RadioButton rbNoA = new RadioButton("No");
    
    TableView <TableDataA> tableA = new TableView <TableDataA>();
    
    //Customer scene
    Button logOutButtonC = new Button("Logout");
    TextField bookIdFieldC = new TextField("");
    TextField titleTextFieldC = new TextField("");
    
    TableView <TableDataC> tableC = new TableView <TableDataC>();
    
    //Record scene
    TableView <TableDataR> tableR = new TableView <TableDataR>();
    
    @Override
    public void start(Stage primaryStage) {
        
        //Start Connect to the database
        initializeDB();
        checkDuration();
        
        //Creating a graphic (image)
        Image img = new Image("image/quit.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(80);
        
        view.setPreserveRatio(true);
      //Creating a Button
        Button quitbutton = new Button();
      //Setting the location of the button
      //Setting the size of the button
        quitbutton.setPrefSize(80, 80);
        //Setting a graphic to the button
        quitbutton.setGraphic(view);
        
        //Login scene ---------------------------------------------------------------------------------------------------------------------  
        Text welcomeL = new Text("Welcome To Book Rental System");
        welcomeL.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        GridPane root1L = new GridPane();
        root1L.setHgap(5);
        root1L.setVgap(15);
        root1L.setPadding(new Insets(20, 10, 0, 10)); 
        root1L.add(welcomeL,0,0);
        root1L.setAlignment(Pos.CENTER);
        //gridpane for image
        GridPane root2L = new GridPane();
        root2L.setHgap(5);
        root2L.setVgap(15);
        root2L.add(lblImage1L,0,0);
        lblImage1L.setGraphic(new ImageView(img1L));
        //gridpane for username and password
        GridPane root3L = new GridPane();
        root3L.setHgap(5);
        root3L.setVgap(15);
        root3L.add(new Label("Username:"),0,0);
        root3L.add(name1L,1,0);
        root3L.add(new Label("Password:"),0,1);
        root3L.add(password1L,1,1);
        root3L.setAlignment(Pos.CENTER);
        //gridpane for button
        btnLogin1L.setPrefWidth(80);
        btnRegister1L.setPrefWidth(80);
        GridPane root4L = new GridPane();
        root4L.setHgap(15);
        root4L.setVgap(15);
        root4L.add(btnLogin1L,0,0);
        root4L.add(btnRegister1L,1,0);
        root4L.setAlignment(Pos.CENTER);
        //VBox for root3L and root4L
        VBox vbox1L = new VBox(20, root3L, root4L);
        //final ui
        root2L.add(vbox1L,1,0);
        VBox vbox2L = new VBox(20, root1L, root2L);
        Scene sceneL = new Scene(vbox2L, 400, 220);
        
     // Search Page 

        GridPane Searchgrid = new GridPane();
        Searchgrid.setAlignment(Pos.CENTER);
        Searchgrid.setHgap(10);
        Searchgrid.setVgap(10);
        Searchgrid.setPadding(new Insets(25, 25, 25, 25));

        VBox TS = new VBox();
        TS.getChildren().addAll(Searchgrid, tableR);
        Scene HistorytableScene = new Scene(TS, 500, 275);

        Text Searchgridtitle = new Text("Your Rent History");
        Searchgridtitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Searchgrid.add(Searchgridtitle, 1, 0);
        
      //Creating a graphic (image)
        Image img2 = new Image("image/quit2.png");
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(20);
        
        view2.setPreserveRatio(true);
      //Creating a Button
        Button CanceltrackButton = new Button();
      //Setting the location of the button
      //Setting the size of the button
        CanceltrackButton.setPrefSize(20, 20);
        //Setting a graphic to the button
        CanceltrackButton.setGraphic(view2);

        Searchgrid.add(CanceltrackButton, 0, 0);

        

        // Search Page END //
        
        //Register scene ---------------------------------------------------------------------------------------------------------------------  
        //gridpane for image
        GridPane root1R = new GridPane();
        root1R.setHgap(5);
        root1R.setVgap(10);
        root1R.setPadding(new Insets(20, 10, 10, 10)); 
        root1R.add(lblImage1R,0,0);
        lblImage1R.setGraphic(new ImageView(img1R));
        //gridpane for username and password
        GridPane root2R = new GridPane();
        root2R.setHgap(5);
        root2R.setVgap(15);
        root2R.add(new Label("Username:"),0,0);
        root2R.add(name1R,1,0);
        root2R.add(new Label("Password:"),0,1);
        root2R.add(password1R,1,1);
        root2R.add(new Label("Confirm Password:"),0,2);
        root2R.add(password2R,1,2);
        root2R.setAlignment(Pos.CENTER);
        //gridpane for button
        btnClear1R.setPrefWidth(66);
        btnRegister1R.setPrefWidth(66);
        btnBack1R.setPrefWidth(66);
        GridPane root3R = new GridPane();
        root3R.setHgap(10);
        root3R.setVgap(15);
        root3R.add(btnRegister1R,0,0);
        root3R.add(btnClear1R,1,0);
        root3R.add(btnBack1R,2,0);
        root3R.setAlignment(Pos.CENTER);
        //VBox for root2R and root3R
        VBox vbox1R = new VBox(20, root2R, root3R);
        //Title
        Text welcomeR = new Text("Register For An Account");
        welcomeR.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        GridPane root4R = new GridPane();
        root4R.setHgap(5);
        root4R.setVgap(15);
        root4R.setPadding(new Insets(20, 10, 0, 10)); 
        root4R.add(welcomeR,0,0);
        root4R.setAlignment(Pos.CENTER);
        //final ui
        root1R.add(vbox1R,1,0);
        VBox vbox2R = new VBox(root4R, root1R);
        Scene sceneR = new Scene(vbox2R, 450, 250);
        
        //customer scene ---------------------------------------------------------------------------------------------------------------------  
        Scene sceneC = new Scene(new Group());
        TableColumn idCol = new TableColumn("ID");
       idCol.setCellValueFactory(
               new PropertyValueFactory<TableDataC, Integer>("id"));

       TableColumn nameCol = new TableColumn("Title");
       nameCol.setCellValueFactory(
               new PropertyValueFactory<TableDataC, String>("name"));

       TableColumn yearCol = new TableColumn("Year");
       yearCol.setCellValueFactory(
               new PropertyValueFactory<TableDataC, String>("year"));

       TableColumn authorCol = new TableColumn("Author");
       authorCol.setCellValueFactory(
               new PropertyValueFactory<TableDataC, String>("author"));

       TableColumn statusCol = new TableColumn("Available");
       statusCol.setCellValueFactory(
               new PropertyValueFactory<TableDataC, String>("available"));

       
       tableC.getColumns().addAll(idCol,nameCol,yearCol,authorCol,statusCol);
       tableC.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
       Image imageC = new Image("image/rentBook.PNG");  
       
       
       //Setting the image view 
       ImageView imageViewC = new ImageView(imageC); 
       
       //Setting the position of the image 
       imageViewC.setX(100); 
       imageViewC.setY(200); 
       
       //setting the fit height and width of the image view 
       imageViewC.setFitHeight(200); 
       imageViewC.setFitWidth(200); 
       
       //Setting the preserve ratio of the image view 
       imageViewC.setPreserveRatio(true);  
       
       //Creating a Group object  
       Group forimage = new Group(imageViewC);  
       
       GridPane rootC = new GridPane();

       rootC.setPadding(new Insets(10));
       rootC.setHgap(10);
       rootC.setVgap(15);

       Label labelTitleC = new Label("Rent Book Now!");

       // Put on cell (0,0), span 2 column, 1 row.
       rootC.add(imageViewC, 0, 0, 2, 1);

       
      
       
       titleTextFieldC.setPromptText("Book Title");

       
       bookIdFieldC.setPromptText("Book ID");

       
       Button searchButtonC = new Button("Search");
       Button RentButtonC = new Button("  Rent ");
       Button checkHistoryButtonC = new Button("Check History");

       rootC.setHalignment(titleTextFieldC, HPos.RIGHT);

       // Put on cell (0,1)

       rootC.setHalignment(titleTextFieldC, HPos.RIGHT);
       rootC.add(titleTextFieldC, 0, 1);
       
       rootC.setHalignment(bookIdFieldC, HPos.RIGHT);
       rootC.add(bookIdFieldC, 0, 2);

       // Horizontal alignment for User Name field.
       rootC.setHalignment(searchButtonC, HPos.LEFT);
       rootC.add(searchButtonC, 1, 1);

       // Horizontal alignment for Password field.
       rootC.setHalignment(RentButtonC, HPos.LEFT);
       rootC.add(RentButtonC, 1, 2);
       
       rootC.setHalignment(checkHistoryButtonC, HPos.CENTER);
       rootC.add(checkHistoryButtonC, 0, 3);
       
       rootC.setHalignment(logOutButtonC, HPos.CENTER);
       rootC.add(logOutButtonC, 1, 3);
       
       HBox vboxC = new HBox();
       vboxC.getChildren().addAll(rootC,tableC);
       ((Group) sceneC.getRoot()).getChildren().addAll(vboxC);
       
       //admin scene -----------------------------------------------------------------------------------------------------------------------  
       // Add Book
       GridPane pA = new GridPane();
       pA.setHgap(5);
       pA.setVgap(10);
       pA.setPadding(new Insets(5, 10, 10, 10));
       pA.setAlignment(Pos.CENTER);
         
        //1st row
        pA.add(new Label ("Enter Book Name: "),0,1);
        pA.add(tfBookNameA,1,1);
        //2st row
        pA.add(new Label ("Enter Book Year: "),0,2);
        pA.add(tfBookYearA,1,2);
        //3rd row
        pA.add(new Label ("Enter Book Author: "),0,3);
        pA.add(tfBookAuthorA,1,3);
        //4rd row
        pA.add(new Label ("Available: "),0,4);
        HBox hb1 = new HBox(20, rbYesA, rbNoA);
        pA.add(hb1,1,4);
        
        HBox vboxA = new HBox(15);
       
        vboxA.getChildren().addAll(addBtnA, clearBtnA);
        vboxA.setAlignment(Pos.CENTER);
        vboxA.setPadding(new Insets(15,15,15,15));
       
        VBox VboxA = new VBox(0); 
        VboxA.getChildren().addAll(pA, vboxA);
       
        
        //Toggle Group
        
        ToggleGroup b1A = new ToggleGroup();
        rbYesA.setToggleGroup(b1A);
        rbNoA.setToggleGroup(b1A);
        
       //Search Column
        GridPane aaA = new GridPane();
        aaA.setHgap(5);
        aaA.setVgap(10);
        aaA.setPadding(new Insets(5, 10, 10, 10));
        aaA.setAlignment(Pos.CENTER);
       
        aaA.add(new Label ("Search Book:"),0,0);
        aaA.add(tfSearchA,1,0);
        tfSearchA.setPromptText("Book ID");
        
        HBox FIND = new HBox(10);
        FIND.getChildren().addAll(findBtnA,updateBtnA,deleteBtnA);
        FIND.setAlignment(Pos.CENTER);
        FIND.setPadding(new Insets(15,15,15,15));
        
        HBox LogoutA = new HBox(30);
        LogoutA.getChildren().addAll(LogoutCBtn);
        LogoutA.setAlignment(Pos.CENTER);
        
        GridPane logoutGA = new GridPane();
        logoutGA.setHgap(5);
        logoutGA.setVgap(10);
        logoutGA.setPadding(new Insets(5, 10, 10, 10));
        logoutGA.setAlignment(Pos.CENTER);
        logoutGA.add(quitbutton,0,0);
        
        VBox CFIND = new VBox(10); 
        CFIND.getChildren().addAll(aaA, FIND, logoutGA);
        
        
        VBox VboxB = new VBox(30); 
        VboxB.getChildren().addAll(VboxA, CFIND);
       
       
       
       
       // Show Column 
                TableColumn bookIDCol = new TableColumn("Book ID");
		bookIDCol.setCellValueFactory(new PropertyValueFactory <TableDataA, Integer>("id"));
                
                TableColumn booknameCol = new TableColumn ("Book Name");
		booknameCol.setCellValueFactory(new PropertyValueFactory <TableDataA, String>("name"));

		TableColumn bookyearCol = new TableColumn ("Book Year");
		bookyearCol.setCellValueFactory(new PropertyValueFactory <TableDataA, String>("year"));
		
		TableColumn bookauthorCol = new TableColumn("Book Author");
		bookauthorCol.setCellValueFactory(new PropertyValueFactory <TableDataA, String>("author"));
		            
                TableColumn availableACol = new TableColumn("Available");
		availableACol.setCellValueFactory(new PropertyValueFactory <TableDataA, String>("available"));
       
                tableA.getColumns().add(bookIDCol);
                tableA.getColumns().add(booknameCol);
                tableA.getColumns().add(bookyearCol);
                tableA.getColumns().add(bookauthorCol);
                tableA.getColumns().add(availableACol);
                tableA.setMaxWidth(Region.USE_PREF_SIZE);
                tableA.setMaxHeight(480);
                tableA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
         // CSS
         
         addBtnA.setStyle(/*"-fx-background-color: GREEN;" +
                        "-fx-border-color:  #a1aeb8;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-color: BLUE;"*/
                        "-fx-pref-height: 40px;" +
                        "-fx-pref-width: 120px;"
                        );   
         updateBtnA.setStyle(/*"-fx-background-color: GREEN;" +
                        "-fx-border-color:  #a1aeb8;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-color: BLUE;"*/
                        "-fx-pref-height: 40px;" +
                        "-fx-pref-width: 90px;"
                        );  
         clearBtnA.setStyle(/*"-fx-background-color: GREEN;" +
                        "-fx-border-color:  #a1aeb8;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-color: BLUE;"*/
                        "-fx-pref-height: 40px;" +
                        "-fx-pref-width: 120px;"
                        );  
         findBtnA.setStyle(/*"-fx-background-color: GREEN;" +
                        "-fx-border-color:  #a1aeb8;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-color: BLUE;"*/
                        "-fx-pref-height: 40px;" +
                        "-fx-pref-width: 90px;"
                        );
         deleteBtnA.setStyle(/*"-fx-background-color: GREEN;" +
		                 "-fx-border-color:  #a1aeb8;" +
		                 "-fx-border-width: 2px;" +
		                 "-fx-border-style: solid;" +
		                 "-fx-color: BLUE;"*/
		                 "-fx-pref-height: 40px;" +
		                 "-fx-pref-width: 90px;"
		                 );
         LogoutCBtn.setStyle(/*"-fx-background-color: GREEN;" +
                        "-fx-border-color:  #a1aeb8;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-style: solid;" +
                        "-fx-color: BLUE;"*/
                        "-fx-pref-height: 50px;" +
                        "-fx-pref-width: 200px;"
                        );
         
         CanceltrackButton.setStyle(/*"-fx-background-color: GREEN;" +
                 "-fx-border-color:  #a1aeb8;" +
                 "-fx-border-width: 2px;" +
                 "-fx-border-style: solid;" +
                 "-fx-color: BLUE;"*/
                 "-fx-background-color: transparent;" +
                 "outline: none;" +
                 "background: none;"
                 );
                
         quitbutton.setStyle(/*"-fx-background-color: GREEN;" +
                 "-fx-border-color:  #a1aeb8;" +
                 "-fx-border-width: 2px;" +
                 "-fx-border-style: solid;" +
                 "-fx-color: BLUE;"*/
                 "-fx-background-color: transparent;" +
                 "outline: none;" +
                 "background: none;"
                 );
         
                
        HBox vbA = new HBox();
        vbA.getChildren().addAll(VboxB,tableA);
        Scene sceneA = new Scene(vbA, 750, 500);
        
        //check record ---------------------------------------------------------------------------------------------------------------------------
       TableColumn rentIDCol = new TableColumn("Book ID");
       rentIDCol.setCellValueFactory(
               new PropertyValueFactory<TableDataR, Integer>("id"));

       TableColumn rentTitleCol = new TableColumn("Book Title");
       rentTitleCol.setCellValueFactory(
               new PropertyValueFactory<TableDataR, String>("name"));

       TableColumn rentDateCol = new TableColumn("Rent Date");
       rentDateCol.setCellValueFactory(
               new PropertyValueFactory<TableDataR, String>("date"));

       
       tableR.getColumns().addAll(rentIDCol,rentTitleCol,rentDateCol);
       tableR.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //navigate function
        btnRegister1L.setOnAction(e->primaryStage.setScene(sceneR));
        
        
        btnBack1R.setOnAction(event ->{
        	primaryStage.setScene(sceneL);
        	clearText();
        });
        
        btnLogin1L.setOnAction(event -> {
            String enterID = name1L.getText();
            String enterPassword = password1L.getText();
            String password = "";
            
            if (enterID.equals("admin")&&enterPassword.equals("admin")) {
    		    	  Information("Admin Login Successful!");
                              PrintA();
  		              primaryStage.setScene(sceneA);
            } else {
            
            try{

                    String searchSQL = "SELECT * from user WHERE UserName = ?";
                    PreparedStatement statement = con.prepareStatement(searchSQL);
                    statement.setString(1,enterID);
                    result = statement.executeQuery();
                    if(result.next()){
                        password = result.getString("Password");
                        if (enterPassword.equalsIgnoreCase(password)){
                            Print();
                            Information("User Login Successful!");
                            primaryStage.setScene(sceneC);
                        }else {
                            Error("Wrong Password"); 
                        }
                    }
                    else {
                      Error("Cannot find the username."); 
                    }
                    
                }catch(Exception e){
                    Error("Got some error, please check again!");
                }
            
            }
                    
                });
       
        
        logOutButtonC.setOnAction(event -> {
        	primaryStage.setScene(sceneL);
        	clearText();
        });
        
        quitbutton.setOnAction(e->{primaryStage.setScene(sceneL); clearText();});
        
        checkHistoryButtonC.setOnAction(e->primaryStage.setScene(HistorytableScene));
       
        
        CanceltrackButton.setOnAction(event -> {
        	primaryStage.setScene(sceneC);
        	Print();
        });

        
        checkHistoryButtonC.setOnAction(event -> {
        	primaryStage.setScene(HistorytableScene);
        	String selectedName = name1L.getText();
        	clear();
        	clearText();
        	
        	 try{
                 String sql3 = "SELECT * FROM record where username = ?";
                 PreparedStatement statement3 = con.prepareStatement(sql3);
                 statement3.setString(1,selectedName);
                 result = statement3.executeQuery();
                 
//                 ObservableList < TableDataR > dataR;
//                 dataR = FXCollections.observableArrayList();
       
                 while(result.next()){
                     int id = result.getInt("bookid");
                     String name = result.getString("booktitle");
                     String date = result.getString("date");
                     

//                     dataR.add(new TableDataR(id,name,date));
//                     tableR.setItems(dataR);
                     
                     tableR.getItems().add(new TableDataR(id,name,date));
                 }
             }catch(SQLException e){
                 e.printStackTrace();
             }
        });
        

        
        //method
        //register page
        btnRegister1R.setOnAction(event -> {
            String enterName = name1R.getText();
            String enterPassword = password1R.getText();
            String comfirmPassword = password2R.getText();
            
            try{
            	if(enterName.isEmpty() || enterPassword.isEmpty() || comfirmPassword.isEmpty()) {
            		throw null;
            	}
                
                if(enterName.equals("admin")){
            		 Error("Username Existed!");
            		 clearText();
            	}
            	else {
                
                   if(enterPassword.equals(comfirmPassword)){
                       String searchSQL = "SELECT * from user WHERE UserName = ?";
                       PreparedStatement statement = con.prepareStatement(searchSQL);
                       statement.setString(1,enterName);
                       result = statement.executeQuery();
                       if(result.next()){                           
                            Error("This User Name is Existed, Pls Try another Name.");
                        }else {
                            
                            String searchSQL1 = "INSERT INTO user(UserName,Password) VALUES (?,?)";
                            PreparedStatement statement1 = con.prepareStatement(searchSQL1);
                            statement1.setString(1,enterName);
                            statement1.setString(2, enterPassword);                    
                            int rows = statement1.executeUpdate();
                    
                            if(rows>0){
                                Information("Add Successful");
                            }
                        }     
                    }else{
                        Error("Password Not Match");
                    }                   
                }      
            }catch(Exception e){
                Error("Please make sure you have entered all the details!");
            }  
        });
        //admin page
        addBtnA.setOnAction(e->addBookA());
        updateBtnA.setOnAction(e->updateA());
        findBtnA.setOnAction(e->FindBookA());
        deleteBtnA.setOnAction(event -> DeleteA());
       
        //customer page
        searchButtonC.setOnAction(event ->{
        	SearchBookC();
        });
        
        //Rent Book 
        RentButtonC.setOnAction(event ->{
        	RentBookC();
        });
        
        clearBtnA.setOnAction(event ->{
        	clearText();
        });
        
        btnClear1R.setOnAction(event ->{
        	clearText();
        });
        
        //show scene
        primaryStage.setTitle("Book Rental System");
        primaryStage.setScene(sceneL);
        primaryStage.show();
    }
    
    private void initializeDB(){
    String url = "jdbc:mysql://localhost:3306/bookrental";
       String username="root";
       String password="";


       try{
            con = DriverManager.getConnection(url, username, password);
            if(con!=null){
                System.out.println("Connection to database");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //admin
    //add book
    public void addBookA(){
    	
        String name = tfBookNameA.getText();
        String author = tfBookAuthorA.getText();
        String year = tfBookYearA.getText();
        String available = getAvailableA();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String date = dtf.format(now);
        
        try{
            
        	if(name.isEmpty() || author.isEmpty() || year.isEmpty() || available.isEmpty()) {
        		throw null;
        	}
        	
            String searchSQL = "INSERT INTO book(BookName,BookAuthor,BookYear,Available,Date) VALUES (?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(searchSQL);
            statement.setString(1,name);
            statement.setString(2, author);
            statement.setString(3,year);
            statement.setString(4, available); 
            statement.setString(5, date); 
            int rows = statement.executeUpdate();

            if(rows>0){
                Information("Add Successful");
                PrintA();
            }
        
        }catch(Exception e){
        	Error("Please make sure you have entered all the details!");
        }
        
    }
    
    public String getAvailableA(){
        String available = "";
        if(rbYesA.isSelected())
            available = "Yes";
        else if(rbNoA.isSelected())
            available = "No";
        return available;
    }
    
    //update book
    public void updateA(){
        
        String id = tfSearchA.getText();
        String name = tfBookNameA.getText();
        String author = tfBookAuthorA.getText();
        String year = tfBookYearA.getText();
        String available = getAvailableA();
        
        try{
            
            if(name.isEmpty() || author.isEmpty() || year.isEmpty() || available.isEmpty()) {
        		throw null;
            }
            
            
            if(!(findID(id))){
                Error("The ID has not exists");
            }else{
                
                String sql = "UPDATE book SET BookName =?, BookAuthor =? , BookYear=? , Available = ? WHERE BookID =?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, author);
                statement.setString(3, year);
                statement.setString(4, available);
                statement.setString(5, id);
                
                int rows = statement.executeUpdate();
                if(rows>0){
                    Information("Update Successful");
                    PrintA();
                }else{
                    Error("Update Failed");
                }
                
            }
            
        }catch(Exception e){
            Error("Please make sure you have entered all the details!");
        } 
    }
    
    public boolean findID(String id){
        boolean gotIt=false;
        
        try{
            String searchSQL = "SELECT * FROM book WHERE BookID=?";
            PreparedStatement statement = con.prepareStatement(searchSQL);
            statement.setString(1, id);
            result = statement.executeQuery();
            gotIt = result.next();
        }catch(SQLException e){
            e.printStackTrace();
        } 
        return gotIt;
    }
    
    //find
    public void FindBookA() {
    try{
            String searchtitleA = tfSearchA.getText();
            if(String.valueOf(searchtitleA).length()<1) {
            	 String searchSQL1 = "SELECT * FROM book";
                 PreparedStatement statement1 = con.prepareStatement(searchSQL1);
                 result = statement1.executeQuery();
                 
                 clear();
                 
                 while(result.next()){
                     int id = result.getInt("BookID");
                     String name = result.getString("BookName");
                     String year = result.getString("BookYear");
                     String author = result.getString("BookAuthor");                     
                     String available = result.getString("Available");   
                     tableA.getItems().add(new TableDataA(id,name, year,author,available));
                 }
            }else{
               String searchSQL = "SELECT * FROM book WHERE BookID = ?";
               PreparedStatement statement = con.prepareStatement(searchSQL);
               statement.setString(1, searchtitleA);
               result = statement.executeQuery();

                clear();

                if(result.isBeforeFirst()){
                    while(result.next()){
                        int id = result.getInt("BookID");
                        String name = result.getString("BookName");
                        String author = result.getString("BookAuthor");
                        String year = result.getString("BookYear");
                        String available = result.getString("Available");
                        tfBookNameA.setText(name);
                        tfBookYearA.setText(year);
                        tfBookAuthorA.setText(author);
                        if(available.equals("No"))
                        	rbNoA.setSelected(true);
                        else
                        	rbYesA.setSelected(true);
                        tableA.getItems().add(new TableDataA(id,name, year,author,available));
                    }
                    Information("Find Successful!!!");
                }else {
                    Error("Cannot found the result!");
                	PrintA();
            
                }

            }            
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            Error("Please make sure you have entered digit in the id before press the find button!");
        }

    } 
    
   //Delete Book
   public void DeleteA() {
   
            try{
                   int idType = Integer.parseInt(tfSearchA.getText());
     
                   try{
                        String sql = "DELETE FROM book WHERE BookID=?";
                        PreparedStatement statement = con.prepareStatement(sql);
                        statement.setInt(1,idType);
                                    
                        int rows = statement.executeUpdate();
                        if(rows>0){
                             Information("The Book has been deleted");
                             PrintA();
                             clearText();
                        }else{
                             Error(idType + " not found!");
                        }
  
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    
            }catch(Exception e){
                   Error("You should enter all digits to use the delete function!");
            }
   }
    
    //print
    public void PrintA(){
        try{
            String sql = "SELECT * FROM book";
            statement = con.createStatement();
            result = statement.executeQuery(sql);
            
            ObservableList < TableDataA > dataA;
            dataA = FXCollections.observableArrayList();
            
            if(result.getRow() <= 0){
                    clear();
            }
  
            while(result.next()){
                 
                int id = result.getInt("BookID");
                String name = result.getString("BookName");
                String author = result.getString("BookAuthor");
                String year = result.getString("BookYear");
                String available = result.getString("Available");
                
                dataA.add(new TableDataA(id,name,year,author,available));
                tableA.setItems(dataA);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //user
    //rent book
    public void RentBookC(){
    	try{
            String username = name1L.getText();
            String rentBookTitle = "";
            int rentBookID = Integer.parseInt(bookIdFieldC.getText());
            String availablestatus ="";
            String BookID = "";
            String availablestatus1 = "";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            String date = dtf.format(now);

            
             String searchSQL = "SELECT * FROM book WHERE BookID = ?";
             PreparedStatement statement = con.prepareStatement(searchSQL);
             statement.setInt(1, rentBookID);
             result = statement.executeQuery();
             
             if(result.next()) {
             
              availablestatus = result.getString("Available");
              BookID = result.getString("BookID");
              rentBookTitle = result.getString("BookName");
             
              availablestatus1 = "No";
              if(availablestatus.equals("No")) {
            	 Error("Book Not Available Now!");
              }
              else {
            	 String sql = "UPDATE book SET Available = ?, Date = ? WHERE BookID = ?";
                 PreparedStatement statement1 = con.prepareStatement(sql);
                 statement1.setString(1, availablestatus1);
                 statement1.setString(2, date);
                 statement1.setString(3, BookID);
                 
                 String sql2 = "INSERT INTO record (username, bookid, booktitle, date) VALUES (?,?,?,?)";
                 PreparedStatement statement2 = con.prepareStatement(sql2);
                 statement2.setString(1, username);
                 statement2.setString(2, BookID);
                 statement2.setString(3, rentBookTitle);
                 statement2.setString(4, date);
                 
                 int rows2 = statement2.executeUpdate();
                 
                 int rows = statement1.executeUpdate();
                 if(rows>0){
                     Information("Rent Successful");
                     Print();
                 }else{
                     Error("Rent Failed");
                 
                }
               }
             }
             else{
                 Error("Not Have This Book");
             }                                                  
         }catch(SQLException e){
             e.printStackTrace();
         }catch(Exception e){
             Error("ID not found!");
         }
    }
    
    //search
    public void SearchBookC(){
    	try{
            String searchtitleC = titleTextFieldC.getText();
            if(String.valueOf(searchtitleC).length()<1) {
            	 String searchSQL1 = "SELECT * FROM book";
                 PreparedStatement statement1 = con.prepareStatement(searchSQL1);
                 result = statement1.executeQuery();
                 
                 clear();
                 
                 while(result.next()){
                	 int id = result.getInt("BookID");
                     String name = result.getString("BookName");
                     String author = result.getString("BookAuthor");
                     String year = result.getString("BookYear");
                     String available = result.getString("Available");       
                     tableC.getItems().add(new TableDataC(id,name, author,year,available));
                 }
            }
            else {
             String searchSQL = "SELECT * FROM book WHERE BookName LIKE ?";
             PreparedStatement statement = con.prepareStatement(searchSQL);
             statement.setString(1, "%" + searchtitleC + "%");
             result = statement.executeQuery();
             

             clear();
             
             if(result.isBeforeFirst()){
             while(result.next()){
            	 int id = result.getInt("BookID");
                 String name = result.getString("BookName");
                 String author = result.getString("BookAuthor");
                 String year = result.getString("BookYear");
                 String available = result.getString("Available");       
                 tableC.getItems().add(new TableDataC(id,name, author,year,available));
             }
             Information("Find Successful!!!");
             }else{
            	 
                 Error("Didnt find the book name!");
                 Print();
             }
         }
    	}catch(SQLException e){
             e.printStackTrace();
         }catch(Exception e){
             Error("Please make sure you have entered digit in the id before press the find button!");
         }
    }
    
    //print
    public void Print(){
        try{
            String sql = "SELECT * FROM book";
            statement = con.createStatement();
            result = statement.executeQuery(sql);
            
            ObservableList < TableDataC > dataC;
            dataC = FXCollections.observableArrayList();
  
            while(result.next()){
                int id = result.getInt("BookID");
                String name = result.getString("BookName");
                String author = result.getString("BookAuthor");
                String year = result.getString("BookYear");
                String available = result.getString("Available");
                

                dataC.add(new TableDataC(id,name, author,year,available));
                tableC.setItems(dataC);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //calculate duration at first
    public void checkDuration(){
        
        try{

            String searchSQL = "SELECT * from book";
            PreparedStatement statement = con.prepareStatement(searchSQL);
            result = statement.executeQuery();
            while(result.next()){
                
                int bookID = result.getInt("BookID");
                String date = result.getString("Date");
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();  
                String dateNow = dtf.format(now);
                
                long duration = find(date,dateNow);
                
                String available = "Yes";
                
                
                if(calculate(duration)){
                    String sql = "UPDATE book SET Available =? WHERE BookID =?";
                    PreparedStatement statement1 = con.prepareStatement(sql);
                    statement1.setString(1, available);
                    statement1.setInt(2, bookID);
                    
                    int rows = statement1.executeUpdate();
  
                }
            }


        }catch(Exception e){

        }
            
    }
    
    public boolean calculate(long duration){
        boolean change = true;
        
        if(duration > 15)
            change = true;
        else
            change = false;
            
        return change;
    }
    
    long find(String join_date, String leave_date)   
    {   
        // Create an instance of the SimpleDateFormat class  
        SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");   
        // In the try block, we will try to find the difference  
        try {   
            // Use parse method to get date object of both dates  
            Date date1 = obj.parse(join_date);   
            Date date2 = obj.parse(leave_date);   
            // Calucalte time difference in milliseconds   
            long time_difference = date2.getTime() - date1.getTime();  
            // Calucalte time difference in days  
            long days_difference = (time_difference / (1000*60*60*24)) % 365;   
            // Calucalte time difference in years  
            long years_difference = (time_difference / (1000l*60*60*24*365));   
            // Calucalte time difference in seconds  
            long seconds_difference = (time_difference / 1000)% 60;   
            // Calucalte time difference in minutes  
            long minutes_difference = (time_difference / (1000*60)) % 60;   
              
            // Calucalte time difference in hours  
            long hours_difference = (time_difference / (1000*60*60)) % 24;   
            // Show difference in years, in days, hours, minutes, and seconds      
               return
                days_difference;  
                  
        }   
        // Catch parse exception   
        catch (ParseException excep) {   
            excep.printStackTrace();   
        }
        return
                0; 
    }
    
  

    // Error
    public void Error(String i) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText(i);
      alert.show();
    }

    // Information
    public void Information(String i) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Information");
      alert.setHeaderText(null);
      alert.setContentText(i);
      alert.show();
    }
    
    //clear
    public void clear(){
        tableA.getItems().clear();
        tableC.getItems().clear();
        tableR.getItems().clear();
    }
    
    public void clearText(){
    	name1L.setText(null);
    	name1R.setText(null);
    	password1R.setText(null);
    	password2R.setText(null);
    	tfBookNameA.setText(null);
    	tfBookYearA.setText(null);
    	tfBookAuthorA.setText(null);
    	tfAvailable.setText(null);
    	tfSearchA.setText(null);
    	bookIdFieldC.setText(null);
    	titleTextFieldC.setText(null);
    	rbYesA.setSelected(false);
    	rbNoA.setSelected(false);
    	password1L.setText(null);
    }

    public static void main(String[] args) {
        launch(args);
    }

    
    
}
