package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import  java.awt.Graphics2D;

//

public class TrazadoLibre extends Figura implements java.io.Serializable
{
	private int grosor; //para pincel y goma seran mayor que para lapiz


	public TrazadoLibre (int ejex, int ejey, boolean relleno, Color color, boolean visible,int grosor)
	{
		super(ejex,ejey,relleno,color,visible);
		this.setGrosor(grosor);
	}

	public void setGrosor (int grosor)
	{
		this.grosor=grosor;
	}

	public int getGrosor()
	{
		return grosor;
	}


	@Override
	public void pintar (Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g); 
		g.fillOval(this.getX(), this.getY(), this.getGrosor(), this.getGrosor()); 
		}
	}

@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Trazado Libre");
		sb.append(super.toString());
		sb.append(" -Grosor: " + grosor);
		return sb.toString();
	}

}