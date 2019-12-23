package application;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	Canvas canvas;
	@FXML
	ColorPicker colorPicker;
	@FXML
	TextField text;
	@FXML
	Button createVertex;
	@FXML
	Button deleteVertex;
	@FXML
	Button hand;
	
	public void initialize() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Draw draw = new Draw(gc);
		
		canvas.setCursor(Cursor.HAND);
		
		
		createVertex.setOnMouseClicked(e->{
			canvas.setCursor(Cursor.CROSSHAIR);
			canvas.setOnMouseClicked(ev->{
				draw.addVertex(ev, colorPicker.getValue(), text.getText());
			});
		});
		
		hand.setOnMouseClicked(e->{
			canvas.set
		});
	}
	
	

}
