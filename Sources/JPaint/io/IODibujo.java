package JPaint.io;

import JPaint.dominio.Dibujo;

//import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.EOFException;

import javax.swing.JOptionPane;


//en esta práctica no tiene sentido leer un fichero de texto con figuras
public class IODibujo
{
	//metodos estaticos (de clase): solo importa funcionalidad
	//OJO COLLECTION TIENE OBJECTS, NO NECESARIO HACER DOWNCASTING!

	//ficheros de objetos
	public static void guardarDibujo (Dibujo dibujo)
	//fichero nuevo
	{
		try{
			File fichero= new File("ficheros/dibujoJPaint.obj");
			if (fichero.exists())
				JOptionPane.showMessageDialog(null, "El fichero de figuras existente se sobrescribira","Aviso", JOptionPane.WARNING_MESSAGE);

			ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream("ficheros/dibujoJPaint.obj"));
			oos.writeObject(dibujo); //todos los objetos heredan de Object, no hace falta hacer upcasting!!
			/*Iterator it= figuras.iterator();
			while (it.hasNext())
				oos.writeObject(it.next());*/
			oos.close();
		}
		catch(FileNotFoundException fnfe){}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error en la escritura del archivo","Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static Dibujo leerDibujo()
	//no fichero nuevo
	{
		Dibujo dibujo=null; //inicializamos
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try{
			 fis= new FileInputStream("ficheros/dibujoJPaint.obj");
			ois= new ObjectInputStream(fis);
			dibujo= (Dibujo) ois.readObject(); //downcasting a dibujo, readObject devuelve Object!
			
		}
		catch(EOFException eof)
		{
			try{

			ois.close();
			fis.close();
			//eof.printStackTrace();

			}catch(IOException ioe)
			{
				JOptionPane.showMessageDialog(null, "Error al finalizar la lectura del archivo","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(FileNotFoundException fnfe)
		{
			//JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo","Error", JOptionPane.ERROR_MESSAGE);
		}
		

		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error en la lectura del archivo","Error", JOptionPane.ERROR_MESSAGE);
		}

		catch (ClassNotFoundException cne)
		{
			JOptionPane.showMessageDialog(null, "No se ha encontrado la clase","Error", JOptionPane.ERROR_MESSAGE);
		}
		return dibujo;
	}

	//ficheros de texto
	public static void exportarDibujoTexto (Dibujo dibujo)
	{

		try
		{

			FileWriter fw= new FileWriter ("ficheros/dibujoJPaint.html",true); //ir añadiendo al final
			PrintWriter pw= new PrintWriter (fw); //nivel de linea
			pw.println(dibujo.toHTML());
			pw.close();
			fw.close();
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error en la escritura del fichero de texto","Error", JOptionPane.ERROR_MESSAGE);
		}
	}


}

