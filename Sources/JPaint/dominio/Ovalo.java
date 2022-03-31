package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import  java.awt.Graphics2D;

public class Ovalo extends Figura implements java.io.Serializable
 {

 		private int base;
 		private int altura;

	public Ovalo (int ejex, int ejey, boolean relleno, Color color, boolean visible, int base, int altura)
	{
		super (ejex, ejey, relleno,color,visible);
		this.setBase(base);
		this.setAltura(altura);
	}

//metodos
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
	this.altura=altura;
	}

	public int getAltura()
	{
		return altura;
	}



	//METODO ABSTRACTO IMPLEMENTADO OBLIGATORIAMENTE
	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g); 
		if (this.getRelleno())
					g.fillOval(this.getX(), this.getY(), this.getBase(), this.getAltura()); 
				else
					g.drawOval(this.getX(), this.getY(),this.getBase(),this.getAltura()); 

			}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Óvalo");
		sb.append(super.toString());
		sb.append("-Anchura: " + base);
		sb.append("-Altura:" + altura);
		return sb.toString();
	}
}