package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import  java.awt.Graphics2D;

public class RoundRectangulo extends Figura implements java.io.Serializable
{

	private int base;
	private int altura;
	private int arcbase;
	private int arcaltura;


public RoundRectangulo (int ejex, int ejey,boolean relleno, Color color, boolean visible, int base, int altura, int arcbase, int arcaltura)
	{
		
		super (ejex, ejey, relleno,color,visible);
		this.setBase(base);
		this.setAltura(altura);
		this.setArcbase(arcbase);
		this.setArcaltura(arcaltura);
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

	public void setArcbase (int arcbase) 
	{
	this.arcbase=arcbase;	
	}

	public int getArcbase()
	{
		return arcbase;
	}

	public void setArcaltura (int arcaltura) 
	{
	this.arcaltura =arcaltura;	
	}

	public int getArcaltura()
	{
		return arcaltura;
	}

//OJO RELLENO CAMBIAR PORQUE VOY A TENER UN BOTON PARA RELLENO O NO 
	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g);
	if(this.getRelleno())
					g.fillRoundRect(this.getX(), this.getY(), this.getBase(), this.getAltura(), this.getArcbase(), this.getArcaltura());
				else
					g.drawRoundRect(this.getX(), this.getY(), this.getBase(), this.getAltura(), this.getArcbase(), this.getArcaltura());
		} 
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Round Rectángulo");
		sb.append(super.toString());
		sb.append("- Anchura: " + base);
		sb.append("- Altura: " + altura);
		sb.append("- Arco horizontal: " + arcbase);
		sb.append("- Arco vertical: " + arcaltura);
		return sb.toString();
	}
}