package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import matrix.Vertex;

public class Draw {
	private GraphicsContext		gc;
	private ArrayList<Vertex>	figures;
	private Vertex				focused;

	private int					dx;
	private int					dy;

	public Draw(GraphicsContext gc) {
		this.gc = gc;
		figures = new ArrayList<Vertex>();
	}

	public void addVertex(MouseEvent e, Color c, String text) {
		Vertex v = new Vertex((int) e.getX(), (int) e.getY(), c, text);
		figures.add(v);
		render();
	}

	public void focus(MouseEvent e) {
		Vertex v = getVertex(e);
		if (v != null) {
			focused = v;
			dx = (int) e.getX();
			dy = (int) e.getY();
		}
	}

	public void moveFocused(MouseEvent e) {
		if (focused == null)
			return;
		focused.setPosition(focused.getX() + (int) (e.getX() - dx), focused.getY() + (int) (e.getY() - dy));
		dx = (int) e.getX();
		dy = (int) e.getY();
		render();
	}

	public void clearFocused() {
		focused = null;
	}

	public void connectVertex(MouseEvent e) {
		Vertex v = getVertex(e);
		if (focused == null || v == focused||v==null) {
			clearFocused();
			render();
			return;
		}
		focused.addConnection(v);
		clearFocused();
		render();
	}

	public void deleteVertex(MouseEvent e) {
		Vertex v = getVertex(e);
		if (v != null) {
			v.deleteAllConnections();
			figures.remove(v);
		}
		render();
	}

	private Vertex getVertex(MouseEvent e) {
		Vertex v = null;
		for (Vertex item : figures) {
			if (item.shot(e)) {
				v = item;
				return v;
			}
		}
		return v;
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
