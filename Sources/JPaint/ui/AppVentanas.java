package JPaint.ui;

public class AppVentanas
{
	
	public static void main (String args[])
	{
			
			JVentanaInicio ventana1= new JVentanaInicio();
			JVentanaDibujo ventana2= new JVentanaDibujo();

			ventana1.setVisible(true);
			ventana2.setVisible(false);

			//esperamos a que se cargue el programa del todo
			
			if (ventana1.llegaMaximoBarra())
			{
			ventana1.setVisible(false);
			ventana2.setVisible(true);
			}
	}
}