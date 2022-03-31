package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import  java.awt.Graphics2D;

public class LineaRecta extends Figura implements java.io.Serializable
{
	//segundas coordenadas (las primeras son las que obtenemos de Figura)
	private int x2;
	private int y2;

	public LineaRecta (int ejex, int ejey,boolean relleno, Color color, boolean visible, int x2, int y2)
	{
		super (ejex, ejey, relleno,color,visible);
		this.setX2(x2);
		this.setY2(y2);
	}

	public void setX2(int x2)
	{
		this.x2=x2;
	}

	public int getX2()
	{
		return x2;
	}


	public void setY2(int y2)
	{
		this.y2=y2;
	}


	public int getY2()
	{
		return y2;
	}

	//OJO RELLENO CAMBIAR PORQUE VOY A TENER UN BOTON PARA RELLENO O NO 
	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g);
		g.drawLine(this.getX(),this.getY(),this.getX2(),this.getY2());
		} 

	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Linea Recta");
		sb.append(super.toString());
		sb.append("Posición final:");
		sb.append(" (" + x2 + ", " + y2 + ")");
		return sb.toString();
	}
}