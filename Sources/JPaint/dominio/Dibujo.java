package JPaint.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import java.awt.Color;


/*seleccionar JOPTIONPANE INPUT DIALOG
selected solo quiero mostrar un grupo de figuras jcombobox con las figuras hacer dos vectores uno con los mostrados y otros con los no mostrados
jseleccionfiguras
LUEGO TAMBIEN PODEMOS CAMBIAR EL COLOR (o incluso el relleno si esta seleccionado y alguna figura no esta rellena)

deselected mostrarocultartodo true porque vuelvo a mostrar todo*/
public class Dibujo implements java.io.Serializable
{
	//clave grupo String no se puede repetir: Set
	//valores Figuras si se pueden repetir: Collection

	//private String tipofigura
	private HashMap grupos= new HashMap(); //grupo clave-valor: hashCode() interno en String

	 public void pintar(String grupo, Figura f) //coge una figura
	{
		Collection figurasGrupo= (Collection) grupos.get(grupo); //devuelve una coleccion!!!
		if (figurasGrupo!=null) //el grupo existe en el mapa, ya ha sido creado
		
			figurasGrupo.add(f); 
		else{
		 //todavia no se ha creado el grupo

		ArrayList figuras= new ArrayList(); //construimos un arraylist
		figuras.add(f);//vamos añadiendo lo que va llegando al arraylist
		grupos.put(grupo,figuras); //lo vamos escribiendo en el mapa
		}	

	}

	public void mostrarOcultarTodo (boolean visible)
	{	
			//utilizamos metodo VALUES ya que nos devuelve todas las claves, pero en COLLECTION!!
			Iterator it= grupos.values().iterator();
			while (it.hasNext())
			{
				Iterator it1= ((Collection) it.next()).iterator();
				while (it1.hasNext())
				{
						Figura f= (Figura) it1.next();
						f.setVisible(visible);
					}
			}
	}

	public void mostrarOcultarGrupo (String grupo, boolean visible)
	{
		Collection ocultados= (Collection) grupos.get(grupo);
		if (ocultados!=null) //existe el grupo
		{
			Iterator it= ocultados.iterator();
			while (it.hasNext())
			{
				Figura f= (Figura) it.next();
				f.setVisible(visible);
			
			}
		}
	}

	public void borrarGrupo (String grupo)
	{
		//ver si el grupo se encuentra en el mapa y borrarlo
		if (grupos.containsKey(grupo))
			grupos.remove(grupo); //borra la coleccion 

	}

	public void mostrarSeleccion (String tipofigura, Color color, boolean relleno)
	{
		Iterator it= grupos.values().iterator();
			while (it.hasNext())
			{
				Iterator it1= ((Collection) it.next()).iterator();
				while (it1.hasNext())
				{
						Figura f= (Figura) it1.next();
						if (tipofigura.equals(f.getClass().getSimpleName()))
						{
						f.setVisible(true);
						f.setColor(color);
						f.setRelleno(relleno);
						}
						else
						f.setVisible(false);
						
				}

			}	
	}

	
	//ver de que tipo es la figura y ocultar ese elemento 
	public void mostrarOcultarElemento(String tipofigura, boolean visible)
	{
		Iterator it= grupos.values().iterator();
			while (it.hasNext())
			{
				Iterator it1= ((Collection) it.next()).iterator();
				while (it1.hasNext())
				{
						Figura f= (Figura) it1.next();
						if (tipofigura.equals(f.getClass().getSimpleName()))
						f.setVisible(visible);
						
				}

			}	
	}	

	public void borrarElemento (String tipofigura)
	{
		Iterator it= grupos.values().iterator();
			while (it.hasNext())
			{
				Iterator it1= ((Collection) it.next()).iterator();
				while (it1.hasNext())
				{
					Figura f= (Figura) it1.next();
						if (tipofigura.equals(f.getClass().getSimpleName()))
							it1.remove();
				}
			}

	}


	public void borrarTodo()
	{
		grupos.clear();
		//grupo vacio
	}

	public ArrayList getFiguras()
	{
		ArrayList figuras= new ArrayList();
		Iterator it= grupos.values().iterator();
		while (it.hasNext())
		{
			Iterator it1= ((Collection) it.next()).iterator();
			while (it1.hasNext())
			{
					Figura f= (Figura) it1.next();
					figuras.add(f);
				}
			}
	return figuras;
	}


	public String toHTML()
	{
		StringBuilder sb= new StringBuilder();
		sb.append("<h3><b> Dibujo JPaint 2019 v1.0 </h3> </b></br>");
		sb.append("<h4><b> Figuras pintadas:</h4></b></br>");
		Collection figuras=this.getFiguras(); //todas las figuras pintadas
		//for (Figura figura:figuras)
		Iterator it= figuras.iterator();
		while (it.hasNext())
		{
			sb.append(((Figura) it.next()).toString() + "</br>"); //downcasting de figura necesario
		}
		return sb.toString();
	}
}


	
		