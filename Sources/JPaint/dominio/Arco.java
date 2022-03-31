package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import  java.awt.Graphics2D;

public class Arco extends Figura implements java.io.Serializable
{
	private int base;
 	private int altura;
 	private int initang;
 	private int ang;

	public Arco (int ejex, int ejey, boolean relleno, Color color, boolean visible, int base, int altura, int initang, int ang)
	{
		super (ejex, ejey, relleno,color,visible);
		this.setBase(base);
		this.setAltura(altura);
		this.setInitang(initang);
		this.setAng(ang);
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
	this.altura=altura;
	}

	public int getAltura()
	{
		return altura;
	}

	public void setInitang (int initang) 
	{
	this.initang=initang;
	}

	public int getInitang()
	{
		return initang;
	}

	public void setAng (int ang) 
	{
	this.ang=ang;
	}

	public int getAng()
	{
		return ang;
	}


	//METODO ABSTRACTO IMPLEMENTADO OBLIGATORIAMENTE
	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g); 
		if (this.getRelleno())
					g.fillArc(this.getX(), this.getY(), this.getBase(), this.getAltura(), this.getInitang(), this.getAng()); 
				else
					g.drawArc(this.getX(), this.getY(),this.getBase(),this.getAltura(), this.getInitang(), this.getAng()); 

			}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Arco");
		sb.append(super.toString());
		sb.append(" - Anchura:" + base);
		sb.append(" -Altura:" + altura);
		sb.append("-Angulo inicial:" + initang);
		sb.append("-Angulo abarcado:" + ang);	
		return sb.toString();
	}
}