package JPaint.ui;
import JPaint.dominio.Dibujo;
/*EXCEPCIONES:
no insertar color antes de hacer alguna opcion*/

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.util.ArrayList;
import java.util.Collection;

public class JPanelOpciones extends JPanel implements ChangeListener
{
	//private JPanelHerramientas pherramientas;
	private JVentanaDibujo ventana;
	private Lienzo lienzo;
	private Dibujo dibujo;
	private String figuras[]= {"Arco","LineaRecta","Circulo","Ovalo","Cuadrado","Rectangulo","RoundRectangulo","Triangulo","Estrella","Flecha","Texto","TrazadoLibre"};
	private String opgrupos[]={"Ocultar grupo","Mostrar grupo", "Borrar grupo"};
	private JCheckBox ocultarf= new JCheckBox("Ocultar");
	private JCheckBox mostrarf=new JCheckBox("Mostrar");
	private JCheckBox borrarf=new JCheckBox("Borrar");
	private JComboBox todasfiguras=new JComboBox(figuras);
	private JComboBox opcionesgrupos=new JComboBox(opgrupos);
	private String figurasel;
	private String acciongrupo;
	private JColorChooser colores=new JColorChooser();
	private Color color;
	

	public JPanelOpciones (JVentanaDibujo ventana) 
	{
		this.ventana=ventana;
		lienzo=ventana.getLienzo();
		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

		colores.setPreviewPanel(new JPanel());
        colores.setPreferredSize(new Dimension(490,150));

        colores.getSelectionModel().addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged (ChangeEvent e)
            {
                color=colores.getColor();
            }

        });
        this.add(colores);
        
		opcionesgrupos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dibujo=ventana.getDibujo();
				acciongrupo=opcionesgrupos.getSelectedItem().toString();

				if (acciongrupo.equals("Ocultar grupo"))
				{
				dibujo.mostrarOcultarGrupo(JPanelOpciones.this.getColor().toString(),false);
				lienzo.repaint();
				}

				else if (acciongrupo.equals("Mostrar grupo"))
				{
				dibujo.mostrarOcultarGrupo(JPanelOpciones.this.getColor().toString(),true);
				lienzo.repaint();
				}

				else
				{
				dibujo.borrarGrupo(JPanelOpciones.this.getColor().toString());
				lienzo.repaint();
				}
			}

		});

		ocultarf.addChangeListener(this);
		mostrarf.addChangeListener(this);
		borrarf.addChangeListener(this);

		//desplegable 
		todasfiguras.addActionListener(new ActionListener()
         {

         	@Override
         	public void actionPerformed(ActionEvent e)
         	{
         		figurasel=todasfiguras.getSelectedItem().toString();     		
         		
         	}


		});
		opcionesgrupos.setMaximumSize(new Dimension(150,60));
		opcionesgrupos.setPreferredSize(new Dimension(140,50));
		this.add(opcionesgrupos);
		this.add(ocultarf);
		this.add(mostrarf);
		this.add(borrarf);
		todasfiguras.setMaximumSize(new Dimension(150,60));
		todasfiguras.setPreferredSize(new Dimension(140,50));
		this.add(todasfiguras);
		}
 

		@Override
		public void stateChanged (ChangeEvent e)
		{
			dibujo=ventana.getDibujo();
			if (ocultarf.isSelected())
			{
				//dibujo.mostrarOcultarTodo(false);
				dibujo.mostrarOcultarElemento(figurasel,false);
				lienzo.repaint();

			}

			else if (mostrarf.isSelected())
			{
				dibujo.mostrarOcultarElemento(figurasel,true);
				//dibujo.mostrarOcultarTodo(true);
				lienzo.repaint();
			}

			else if (borrarf.isSelected())
			{
				dibujo.borrarElemento(figurasel);
				lienzo.repaint();
			}
		}

	   public void setColor (Color color)
    {
        this.color=color;
    }

    public Color getColor()
    {
        return color;
    }
	
}