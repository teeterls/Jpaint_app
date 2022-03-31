package JPaint.dominio;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

public class Texto extends Figura implements java.io.Serializable
{
	private String s;
	private Font font;

public Texto (int ejex, int ejey, boolean relleno,Color color, boolean visible, String s, Font font)
{
	super(ejex, ejey, relleno,color,visible);
	this.s=s; //toString de String lo hace el mismo
	this.setFuente(font);
}

	public void setFuente(Font font)
	{
		this.font=font;
	}

	public Font getFuente()
	{
		return font;
	}


	@Override
	public void pintar(Graphics g)
	{
		if (this.isVisible())
		{
		super.pintar(g);
		g.drawString(s.toString(), this.getX(), this.getY());
		g.setFont(font);
		} 

	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Tipo: Texto");
		sb.append(super.toString());
		sb.append("Texto:"+ s.toString());
		sb.append("Fuente:" + "(" +font.getFontName() + font.getStyle() + font.getSize() + ")");
		return sb.toString();
	}
}