package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import matrix.Vertex;

public class Draw {
	private GraphicsContext		gc;
	private ArrayList<Vertex>	figures;

	public Draw(GraphicsContext gc) {
		this.gc = gc;
		figures = new ArrayList<Vertex>();
	}

	public void addVertex(MouseEvent e, Color c, String text) {
		Vertex v = new Vertex((int) e.getX(), (int) e.getY(), c, text);
		figures.add(v);
		render();
	}

	private void render() {
		clear();
		for (Vertex v : figures) {
			v.draw(gc);
		}
	}
	private void clear() {
		gc.setFill(Color.WHITESMOKE);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	}
}
