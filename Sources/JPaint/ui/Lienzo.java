package JPaint.ui;
import JPaint.dominio.Figura;
import JPaint.dominio.Dibujo;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Cursor;

//CANVAS: LIENZO PARA DIBUJAR
public class Lienzo extends Canvas
{

	private JVentanaDibujo ventana;
	//referencias para que puedan ejecutar sus metodos 
	public Lienzo (JVentanaDibujo ventana)
	{
		this.ventana=ventana;
	}


	//metodo que permite pintar en el lienzo!!
	//tenemos que obtener una ocurrencia de dibujo para poder operar con los elementos:get Dibujo()
	//entonces podremos obtener figuras
	public void paint (Graphics g)
	{

 //ya tenemos la referencia de un dibujo
		ArrayList figuras= ventana.getDibujo().getFiguras(); //ya tenemos las figuras en un array
		
		for (int i=0; i<figuras.size();i++)
		{
			if (figuras.get(i) instanceof Figura)
			{
				Figura f= (Figura) figuras.get(i);
				f.pintar(g);
			}
		}
	}
}
