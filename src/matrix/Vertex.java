package matrix;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Vertex {
	private int				x;
	private int				y;
	private String			text	= "Vert";
	private Color			color;
	private List<Vertex>	connects;

	private final int		w		= 100;
	private final int		h		= 30;

	public Vertex() {
	}

	public Vertex(int x, int y, Color c, String text) {
		this.x = x;
		this.y = y;
		color = c;
		if (text != null&&text!="") {
			this.text = text;
		}
		connects = new ArrayList<Vertex>();
	}

	public void addConnection(Vertex v) {
		connects.add(v);
		v.connects.add(this);
	}

	public void deleteConnection(Vertex v) {
		v.connects.remove(this);
		connects.remove(v);
	}

	public void deleteAllConnections() {
		for (Vertex v : connects) {
			v.connects.remove(this);
		}
//		connects.remove(v);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public boolean shot(MouseEvent e) {
		int dx = (int) e.getX();
		int dy = (int) e.getY();
		boolean result = false;

		if (dx > (x - w / 2) && dx < (x + w / 2) && dy > (y - h / 2) && dy < (y + h / 2))
			result = true;

		return result;
	}

	public void draw(GraphicsContext gc) {
		for (Vertex item : connects) {
			if(item.x>x) {
				
			}
			item.drawVertex(gc);
		}
		drawVertex(gc);
	}

	private void drawVertex(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(x - w / 2, y - h / 2, w, h);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(x - w / 2, y - h / 2, w, h);
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font(20));
		gc.fillText(text, x - w / 2 + 2, y + 2);
	}

}
