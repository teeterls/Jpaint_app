package JPaint.dominio; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
 
 /**
 * Clase que define el comportamiento de todas las figuras a crear
 *
 * @author Teresa González García
 * @version 1.0 24/05/19
 */

public abstract class Figura implements java.io.Serializable
{
	/** Coordenada x de la figura */
	private int ejex;
	/** Coordenada y de la figura */
	private int ejey;
	/**Booleano relleno figura */
	private boolean relleno; 
	/**Color figura */
	private Color color;
	/**Booleano visibilidad figura */ 
	private boolean visible;

	/**
	*Constructor de la clase: inicializa los atributos de la misma
	*@param ejex coordenada x de la figura en el Canvas
	*@param ejey coordenada y de la figura en el Canvas
	*@param relleno figura pintada transparente u opaca
	*@param color color de la figura
	*@param  visible figura visible o no en el Canvas
	*/
	public Figura( int ejex, int ejey, boolean relleno, Color color, boolean visible)
	{
		this.ejex=ejex;
		this.setY(ejey);
		this.relleno=relleno;
		this.setColor(color);
		this.setVisible(visible);
	}

	/**
	*Setter de la coordenada x de la figura
	* 
	*/
	public void setX (int ejex) 
	{
	this.ejex=ejex;
	}

	/**
	*Getter de la coordenada x de la figura
	* @return ejex: coordenada x de la figura
	*/
	public int getX ()
	{
		return ejex;
	}

	/**
	*Setter de la coordenada y de la figura
	* 
	*/
	public void setY (int ejey) 
	{
	this.ejey=ejey;
	}

	/**
	*Getter de la coordenada y de la figura
	*@return ejey: coordenada y de la figura
	*/
	public int getY ()
	{
	return ejey;
	}

	/**
	*Setter del relleno de la figura
	*
	*/
	public  void setRelleno (boolean relleno)
	{
		this.relleno=relleno;
	}

	/**
	*Getter del relleno de la figura
	*@return relleno: relleno de la figura
	*/
	public  boolean getRelleno()
	{
		return relleno;
	}

	/**
	*Setter del color de la figura
	*
	*/
	public void setColor (Color color)
	{
		this.color=color;
	}

	/**
	*Getter del color de la figura
	*@return color: color de la figura
	*/
	public Color getColor()
	{
		return color;
	}

	/**
	*Setter de la visibilidad de la figura
	*
	*/
	public void setVisible (boolean visible)
	{
		this.visible=visible;
	
	}	

	/**
	*Getter de la visibilidad de la figura
	*@return visible: visibilidad de la figura
	*/
	public boolean isVisible()
	{
		return visible;
	}

	/**
	*Metodo gráfico para pintar la figura en un Canvas, determina el color de la figura
	*@param g elemento gráfico que permite pintar componentes
	*/
	public void pintar (Graphics g)
	{
		
		g.setColor(color); 
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(" - Visible:");
		sb.append(visible);
		sb.append(" - Relleno:");
		sb.append(relleno);
		sb.append(" - Color:");
		sb.append(color);
		sb.append(" - Posición: ");
		sb.append(" (" + ejex + ", " + ejey + ")");
		return sb.toString();
	}
}

