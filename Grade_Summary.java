package javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Grade_Summary extends Application{
	
	int highest;
	int lowest;
	int num_marks=0;
	int total_marks=0;
	int avg_marks=0;	
	
	
	int[] num_list=new int[5];
	int i=0;
	
	Scene scene1,scene2;

	TextField txt1=new TextField();
	
	Label lb1=new Label("Enter Grade"); 
	
	Button btnAdd=new Button("Add Mark");
	
	
	Button btnShowSummary=new Button("Show Summary");
	
	Button btnClear=new Button("Clear");

	
	Text markCount=new Text("Number Of Marks ");
	Text total=new Text("Total Marks ");
	Text avg=new Text("Average Mark ");
	Text high=new Text("Highest Mark ");
	Text low=new Text("Lowest Marks ");
	
	Label lblCount=new Label("");
	Label lblTotal=new Label("");
	Label lblAvg=new Label("");
	Label lblHigh=new Label("");
	Label lblLow=new Label("");
	
	Button btnMain=new Button("Back To Main");	
	
	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		VBox main_page=new VBox();
		main_page.setSpacing(20); 
		main_page.setBackground(Background.fill(Color.LIGHTBLUE)); 
		main_page.setStyle("-fx-font:13 arial;");		
		
		// input enter grade
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(lb1);
		hb2.setAlignment(Pos.TOP_LEFT);
		hb2.setPadding(new Insets(15, 12, 15, 12));
		hb2.setStyle("-fx-font: 24 arial;"); 
		
		HBox hb1=new HBox();		
		hb1.getChildren().addAll(txt1,btnAdd);
		hb1.setSpacing(30); 
		hb1.setAlignment(Pos.CENTER);
		hb1.setPadding(new Insets(15, 12, 15, 12)); 
		txt1.setPromptText("Enter Your Mark ");		
		
		HBox btnPane=new HBox();
		btnShowSummary.setDisable(true);
		btnPane.getChildren().addAll(btnClear,btnShowSummary);
		btnPane.setAlignment(Pos.CENTER); 
		btnPane.setSpacing(15); 
		
		main_page.getChildren().addAll(hb2,hb1,btnPane);
		scene1=new Scene(main_page,350,200);		
		
		GridPane summaryPane=new GridPane();
		summaryPane.add(markCount, 0, 0);
		summaryPane.add(lblCount, 1, 0);
		summaryPane.add(total, 0, 1);
		summaryPane.add(lblTotal, 1, 1);
		summaryPane.add(avg, 0, 2);
		summaryPane.add(lblAvg, 1, 2);
		summaryPane.add(high, 0, 3);
		summaryPane.add(lblHigh, 1, 3);
		summaryPane.add(low, 0, 4);
		summaryPane.add(lblLow, 1, 4);
		summaryPane.add(btnMain, 1, 5);
		summaryPane.setVgap(10); 
		summaryPane.setPadding(new Insets(10, 10, 10, 10)); 
		summaryPane.setBackground(Background.fill(Color.LIGHTBLUE)); 
		
		scene2=new Scene(summaryPane,350,200); 
		summaryPane.setStyle("-fx-font:13 arial;");
		
		stage.setTitle("Main Page");
		stage.setScene(scene1);
		stage.show();
		
		btnShowSummary.setOnAction(e->{
			totalMark();
			averageMark();
			CalcLowest();
			CalcHighest();
			
			lblCount.setText(num_marks+"");
			lblTotal.setText(total_marks+"");
			lblAvg.setText(avg_marks+"");
			lblHigh.setText(highest+"");
			lblLow.setText(lowest+"");			
			
			stage.setTitle("Summary Page");
			stage.setScene(scene2);
			stage.show();
		});
		
		btnMain.setOnAction(e->{
			txt1.setEditable(true);
			i = 0;
			num_marks = 0;
			btnShowSummary.setDisable(true);
			stage.setTitle("Main Page");
			stage.setScene(scene1);
			stage.show();
		});		
		
		btnAdd.setOnAction(e->{
			if (i==5) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Input Error");
				alert.setContentText("The number of marks must be 5 marks");
				alert.showAndWait();
				txt1.clear();
				txt1.setEditable(false);
			}
			else {
				if (i==4)
				btnShowSummary.setDisable(false);
				num_list[i]=Integer.parseInt(txt1.getText());
				num_marks++;
				txt1.clear(); 
				i++;
				
			}
		});
		
		// To Clear input
		btnClear.setOnAction(e->
		{
			txt1.clear();
		});
	}
	
	// Calculate the total mark 
	public void totalMark()
	{
		for(int i=0;i<num_list.length;i++)
			total_marks+=num_list[i];		
	}
	
	// Calculate the average mark
	public void averageMark()
	{
		int total_mark=0;
		for(int i=0;i<num_list.length;i++)
			total_mark+=num_list[i];		
		avg_marks=total_mark/5;
	}
	
	// Calculate the lowest mark
	public void CalcLowest()
	{
		lowest=num_list[0];
		for(int i=1;i<num_list.length;i++)
			if (num_list[i]<lowest)
				lowest=num_list[i];
	}
	
	// Calculate the highest mark
	public void CalcHighest() 
	{
		highest=num_list[0];
		for(int i=1; i<num_list.length; i++)
			if(num_list[i]>highest)
				highest=num_list[i];
	}
}
