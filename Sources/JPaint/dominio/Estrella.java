package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Estrella extends Figura implements java.io.Serializable
{
	private int x[];
	private int y[];

	public Estrella (int ejex, int ejey, boolean relleno, Color color, boolean visible, int x[], int y[])
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
					g.fillPolygon(x,y,10);
				else
					g.drawPolygon(x,y,10);
		} 
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Estrella");
		sb.append(super.toString());
		sb.append(" -Coordenadas x:");
		sb.append(x[0]);
		sb.append(",");
		sb.append(x[1]);
		sb.append(",");
		sb.append(x[2]);
		sb.append(",");
		sb.append(x[3]);
		sb.append(",");
		sb.append(x[4]);
		sb.append(",");
		sb.append(x[5]);
		sb.append(",");
		sb.append(x[6]);
		sb.append(",");
		sb.append(x[7]);
		sb.append(",");
		sb.append(x[8]);
		sb.append(",");
		sb.append(x[9]);
		sb.append("-Coordenadas y:");
		sb.append(y[0]);
		sb.append(",");
		sb.append(y[1]);
		sb.append(",");
		sb.append(y[2]);
		sb.append(",");
		sb.append(y[3]);
		sb.append(",");
		sb.append(y[4]);
		sb.append(",");
		sb.append(y[5]);
		sb.append(";");
		sb.append(y[6]);
		sb.append(",");
		sb.append(y[7]);
		sb.append(",");
		sb.append(y[8]);
		sb.append(",");
		sb.append(y[9]);
		return sb.toString();
	}
}
