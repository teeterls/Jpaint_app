package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import  java.awt.Graphics2D;

public class Rectangulo extends Figura implements java.io.Serializable
{
		private int base;
		private int altura;

public Rectangulo (int ejex, int ejey,boolean relleno, Color color, boolean visible, int base, int altura)
	{
		
		super (ejex, ejey, relleno,color,visible);
		this.setBase(base);
		this.setAltura(altura);
	}

	public void setBase (int base) 
	{
	this.base=base;	
	}

	public int getBase()
	{
		return base;
	}

	public void setAltura (int altura) 
	{
	this.altura =altura;	
	}

	public int getAltura()
	{
		return altura;
	}

//OJO RELLENO CAMBIAR PORQUE VOY A TENER UN BOTON PARA RELLENO O NO 
	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g);
	if(this.getRelleno())
					g.fillRect(this.getX(),this.getY(),this.getBase(), this.getAltura()); 
				else
					g.drawRect(this.getX(),this.getY(),this.getBase(),this.getAltura());
		} 
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Rectángulo");
		sb.append(super.toString());
		sb.append("-Anchura: " + base);
		sb.append("-Altura:" + altura);
		return sb.toString();
	}
}

