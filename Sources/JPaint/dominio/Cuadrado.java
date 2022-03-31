package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cuadrado extends Figura implements java.io.Serializable
{


	private int lado;

public Cuadrado(int ejex, int ejey,boolean relleno, Color color, boolean visible, int lado)
	{
		
		super (ejex, ejey, relleno,color,visible);
		this.setLado(lado);
	}

	public void setLado (int lado) 
	{
	this.lado=lado;	
	}

	public int getLado ()
	{
		return lado;
	}

//OJO RELLENO CAMBIAR PORQUE VOY A TENER UN BOTON PARA RELLENO O NO 
	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g);
	if(this.getRelleno())
					g.fillRect(this.getX(),this.getY(),this.getLado(), this.getLado()); 
				else
					g.drawRect(this.getX(),this.getY(),this.getLado(),this.getLado());
		} 
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Cuadrado");
		sb.append(super.toString());
		sb.append(" - Lado: " + lado);
		return sb.toString();
	}
}

	   

