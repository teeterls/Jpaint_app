package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circulo extends Figura implements java.io.Serializable
{

	private int radio;

	public Circulo (int ejex, int ejey, boolean relleno, Color color, boolean visible, int radio)
	{
		super (ejex, ejey, relleno,color,visible);
		this.setRadio(radio);
	}

//metodos
	public void setRadio (int radio) //metodo escribir radio
	{
	this.radio=radio;
	}

	public int getRadio ()
	{
		return radio;
	}


	//METODO ABSTRACTO IMPLEMENTADO OBLIGATORIAMENTE
	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g); 
		if (this.getRelleno())
					g.fillOval(this.getX(), this.getY(), this.getRadio(), this.getRadio()); 
				else
					g.drawOval(this.getX(), this.getY(),this.getRadio(),this.getRadio()); 

			}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Círculo");
		sb.append(super.toString());
		sb.append(" - Radio: " + radio);
		return sb.toString();
	}
}