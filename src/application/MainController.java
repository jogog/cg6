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
	@FXML
	Button connectVertex;
	
	
	public void initialize() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Draw draw = new Draw(gc);
		
		canvas.setCursor(Cursor.HAND);
		
		
		createVertex.setOnMouseClicked(e->{
			switchMode(draw);
			canvas.setCursor(Cursor.CROSSHAIR);
			canvas.setOnMouseClicked(ev->{
				draw.addVertex(ev, colorPicker.getValue(), text.getText());
			});
		});
		
		hand.setOnMouseClicked(e->{
			switchMode(draw);
			canvas.setCursor(Cursor.OPEN_HAND);
			canvas.setOnMousePressed(ev->{
				canvas.setCursor(Cursor.CLOSED_HAND);
				draw.focus(ev);
			});
			canvas.setOnMouseDragged(ev->{
				draw.moveFocused(ev);
			});
			canvas.setOnMouseReleased(ev->{
				canvas.setCursor(Cursor.HAND);
				draw.clearFocused();
			});
		});
		
		deleteVertex.setOnMouseClicked(e->{
			switchMode(draw);
			canvas.setCursor(Cursor.CROSSHAIR);
			canvas.setOnMouseClicked(ev->{
				draw.deleteVertex(ev);
			});
		});
	
		connectVertex.setOnMouseClicked(e->switchToConnect(draw));
	
	}
	private void switchToConnect(Draw draw){
		switchMode(draw);
		canvas.setCursor(Cursor.CROSSHAIR);
		canvas.setOnMouseClicked(ev->{
			draw.focus(ev);
			canvas.setOnMouseClicked(event ->{
				draw.connectVertex(event);
				switchToConnect(draw);
			});
		});
	}
	
	private void switchMode(Draw draw) {
		draw.clearFocused();
		canvas.setOnMouseClicked(null);
		canvas.setOnMouseDragged(null);
		canvas.setOnMousePressed(null);
		canvas.setOnMouseReleased(null);
	}

}
