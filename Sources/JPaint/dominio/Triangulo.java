package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangulo extends Figura implements java.io.Serializable
{
	//2 vectores de 3 elementos porque tenemos 3 puntos
	private int x[];
	private int y[];

	public Triangulo (int ejex, int ejey, boolean relleno, Color color, boolean visible, int x[], int y[])
	{
		super (ejex, ejey, relleno,color,visible);
		this.setVectorX(x);
		this.setVectorY(y);
		
	}

	public void setVectorX( int[] x)
	{
		this.x=x;
	}

	public int[] getVectorX()
	{
 		return x;
	}

	public void setVectorY( int[] y)
	{
		this.y=y;
	}

	public int[] getVectorY()
	{
 		return y;
	}

	@Override
	public void pintar (Graphics g)
	{

		if (this.isVisible())
		{
		super.pintar(g);
	if(this.getRelleno())
					g.fillPolygon(x,y,3);
				else
					g.drawPolygon(x,y,3);
		} 
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Triángulo");
		sb.append(super.toString());
		sb.append(" -Coordenadas x:");
		sb.append(x[0]);
		sb.append(",");
		sb.append(x[1]);
		sb.append(",");
		sb.append(x[2]);
		sb.append("-Coordenadas y:");
		sb.append(y[0]);
		sb.append(",");
		sb.append(y[1]);
		sb.append(",");
		sb.append(y[2]);
		return sb.toString();
	}
}