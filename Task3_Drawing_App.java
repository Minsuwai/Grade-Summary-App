package javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Task3_Drawing_App extends Application{

	public static void main(String[] args) {
	
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Button btn_Clear = new Button("Clear");
		btn_Clear.setMaxSize(50, 50);	
		
		GridPane gp=new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(20);
		
		// Create pen width
		Integer [] cbo_Value= {1,2,3,4,5,6,7};
		ObservableList<Integer> cbo=FXCollections.observableArrayList(cbo_Value);
		
		ComboBox<Integer> pen_Width=new ComboBox<Integer>();
		pen_Width.setPrefWidth(7);
		pen_Width.setValue(1);
		pen_Width.getItems().addAll(cbo);
		
		pen_Width.setOnAction(e->{
			cbo.indexOf(pen_Width.getValue());
		});
		
		// Display the choose color
		Rectangle rec_Color=new Rectangle(75,50);
		rec_Color.setFill(Color.BLACK);
		
		Slider rslide=new Slider(0.0,1.0,0.0);
		rslide.setOrientation(Orientation.HORIZONTAL);
		
		Slider gslide=new Slider(0.0,1.0,0.0);
		gslide.setOrientation(Orientation.HORIZONTAL);
		
		Slider bslide=new Slider(0.0,1.0,0.0);
		bslide.setOrientation(Orientation.HORIZONTAL);
		
		gp.add(new Label("Red"), 0, 0);
		gp.add(new Label("Green"), 1, 0);
		gp.add(new Label("Blue"), 2, 0);
		
		gp.add(rslide,0,1);
		gp.add(gslide,1,1);
		gp.add(bslide,2,1);
		
		rslide.valueProperty().addListener(ov->{
			rec_Color.setFill(Color.color(rslide.getValue(),gslide.getValue(),bslide.getValue()));
		});
		
		gslide.valueProperty().addListener(ov->{
			rec_Color.setFill(Color.color(rslide.getValue(),gslide.getValue(),bslide.getValue()));
		});
		
		bslide.valueProperty().addListener(ov->{
			rec_Color.setFill(Color.color(rslide.getValue(),gslide.getValue(),bslide.getValue()));
		});
		
		// Create the canvas for drawing
		Canvas cv=new Canvas(500,500);
		GraphicsContext gc=cv.getGraphicsContext2D();
		gc.strokeRect(0, 0, cv.getWidth(), cv.getHeight());
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		
		cv.setOnMousePressed(e->{
			gc.beginPath();
			gc.moveTo(e.getX(), e.getY());
			gc.stroke();
			gc.setLineWidth(pen_Width.getValue());
			gc.setStroke(Color.color(rslide.getValue(),gslide.getValue(),bslide.getValue()));
		});
		
		cv.setOnMouseDragged(e->{
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();
			gc.setLineWidth(pen_Width.getValue());
			gc.setStroke(Color.color(rslide.getValue(),gslide.getValue(),bslide.getValue()));
		});
		
		HBox hbox=new HBox(15);
		hbox.getChildren().addAll(gp,rec_Color,pen_Width);
		hbox.setPadding(new Insets(10));
		
		// Create stackpane to assign the clear button 
		StackPane Clear = new StackPane();
		Clear.getChildren().add(btn_Clear);
		Clear.setPadding(new Insets(25,0,25,0));
		
		// Create borderpage for hbox, cv and Clear
		BorderPane bp=new BorderPane();
		bp.setTop(hbox);
		bp.setCenter(cv);
		bp.setBottom(Clear);
		bp.setBackground(Background.fill(Color.LIGHTBLUE));
		
		Scene scene=new Scene(bp,600,700);
		stage.setTitle("Drawing Application");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
		// Action for clear button
		btn_Clear.setOnAction(e->
		{
			gc.clearRect(0, 0, cv.getWidth(), cv.getHeight());
			gc.strokeRect(0, 0, cv.getWidth(), cv.getHeight());
			gc.setFill(Color.WHITE);
			gc.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		});
	}

}
