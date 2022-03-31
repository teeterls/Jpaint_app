package JPaint.ui;
import JPaint.dominio.Dibujo;
import JPaint.dominio.Figura;
import JPaint.dominio.Arco;
import JPaint.dominio.Circulo;
import JPaint.dominio.Cuadrado;
import JPaint.dominio.Estrella;
import JPaint.dominio.Flecha;
import JPaint.dominio.Hexagono;
import JPaint.dominio.LineaRecta;
import JPaint.dominio.Ovalo;
import JPaint.dominio.Pentagono;
import JPaint.dominio.Rectangulo;
import JPaint.dominio.RoundRectangulo;
import JPaint.dominio.Texto;
import JPaint.dominio.TrazadoLibre;
import JPaint.dominio.Triangulo;

import JPaint.io.IODibujo;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Image;
//layouts
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

//color
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
//eventos
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseMotionAdapter;

import java.io.IOException;

public class JVentanaDibujo extends JFrame 
{
	
		private Dibujo dibujo;
		private Lienzo lienzo= new Lienzo(this);
		private JMenuBar mb=new JMenuBar();
		private JMenu archivo= new JMenu("Archivo");
		private JMenu figuras=new JMenu("Figuras");
		private JMenu dib=new JMenu ("Dibujo");
		private JMenu opciones=new JMenu ("Opciones");
		private JPanelElementos pelementos= new JPanelElementos(this);
		private JSeleccionFiguras pfiguras= new JSeleccionFiguras(this);
		private JPanelOpciones popciones=new JPanelOpciones(this);
		private JScrollPane scroll;
		private JPanelSur psur= new JPanelSur(this);
   	
   		
	public JVentanaDibujo()
	{
		this.dibujo=IODibujo.leerDibujo();
					if (this.dibujo==null)//todavia no se ha creado
					{
				this.dibujo=new Dibujo(); //nueva instancia
				//JOptionPane.showMessageDialog(null, "Todavía no se ha creado ningún fichero","Empiece a pintar...", JOptionPane.INFORMATION_MESSAGE);
				lienzo.repaint();
					}
		 
		this.initMenu();//modularizacion
		this.initComponents();
		this.initFrame();
		
	}

		//componentes
		public void initComponents()
		{
			lienzo.setSize(1000,400);
			
			   this.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        int result=JOptionPane.showConfirmDialog(null, "Va a salir de la aplicacion. �Desea guardar su dibujo?","Saliendo...",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                        switch(result)
                        {
                        	case 0: //yes
                        	IODibujo.guardarDibujo(dibujo);
							IODibujo.exportarDibujoTexto(dibujo);
							try
							{
								Runtime.getRuntime().exec("cmd /c " + "ficheros/dibujoJPaint.html");
							}
							catch(IOException exc)
							{
								JOptionPane.showMessageDialog(null, "No se pudo iniciar el navegador","Error", JOptionPane.ERROR_MESSAGE);
							}	
			
                        	JOptionPane.showMessageDialog(null, "Dibujo guardado con exito", "Guardado",JOptionPane.INFORMATION_MESSAGE);

                        	JVentanaDibujo.this.dispose(); //se liberan recursos
                       		 System.exit(0);
                        	break;

                        	case 1: //no
                      		 JVentanaDibujo.this.dispose(); //se liberan recursos
                       		 System.exit(0);
                        	break;

                        	case 2: //cancel. se cierra el panel y se queda igual 

                        	break;

                        	case -1: //close. lo mismo que cancel

                        	break;

                        	default:
                        	JOptionPane.showMessageDialog(null,"Error","Error",JOptionPane.ERROR_MESSAGE);
                        	JVentanaDibujo.this.dispose(); //se liberan recursos
                       		 System.exit(0);
                        	break;

                        }

      
                    }
                 });
		}

		//frame:Lienzo (canvas)
		public void initFrame()
		{
		this.setLayout(new BorderLayout());
		this.setIconImage(new ImageIcon("iconos/logo.jpg").getImage());
		pelementos.setVisible(false);
		pfiguras.setVisible(false);
		popciones.setVisible(false);
		lienzo.setBackground(Color.WHITE); 
		scroll=new JScrollPane(lienzo);
		this.add(scroll, BorderLayout.CENTER);
		this.add(psur, BorderLayout.SOUTH);
		
		this.pack();
		this.setTitle("JPaint 2019 1.0");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		}	

		public void initMenu()
		{
			
 			JMenuItem nuevo= new JMenuItem("Nuevo",new ImageIcon(new ImageIcon("iconos/nuevo.jpg").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
			JMenuItem abrir= new JMenuItem("Abrir",new ImageIcon(new ImageIcon("iconos/abrir.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
			JMenuItem guardar= new JMenuItem("Guardar",new ImageIcon(new ImageIcon("iconos/guardar.jpg").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT)));

			//nuevo:lo borra todo
			nuevo.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed (ActionEvent e)
				{
					//borrar todo el hashmap de figuras insertadas
					dibujo.borrarTodo();
					lienzo.repaint();
				}
				
			});

			abrir.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed (ActionEvent e)
				{
					//abrimos el fichero de objetos
					//inicialmente leemos si el fichero .obj ya esta creado
					JVentanaDibujo.this.dibujo=IODibujo.leerDibujo();
					if (JVentanaDibujo.this.dibujo==null)//todavia no se ha creado
					{
					JVentanaDibujo.this.dibujo=new Dibujo(); //nueva instancia
					JOptionPane.showMessageDialog(null, "Todavia no se ha creado ningún fichero","Empiece a pintar...", JOptionPane.INFORMATION_MESSAGE);
					}
					lienzo.repaint();
				}
			});


			guardar.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed (ActionEvent e)
				{
					IODibujo.guardarDibujo(dibujo);
					IODibujo.exportarDibujoTexto(dibujo);
				try
				{
					Runtime.getRuntime().exec("cmd /c " + "dibujoJPaint.html");
				}
				catch(IOException exc)
				{
					JOptionPane.showMessageDialog(null, "No se pudo iniciar el navegador","Error", JOptionPane.ERROR_MESSAGE);
				}	
				}
			});

			archivo.add(nuevo);
			archivo.add(abrir);
			archivo.addSeparator();
			archivo.add(guardar);


			figuras.addMenuListener(new MenuListener()
		{
			@Override
			public void menuSelected(MenuEvent e)
			{
				pelementos.setVisible(false);
				popciones.setVisible(false);
				pfiguras.setVisible(true);
				JVentanaDibujo.this.add(pfiguras, BorderLayout.NORTH);
			}

			public void menuDeselected(MenuEvent e){};

			
			public void menuCanceled(MenuEvent e){};
		});

			dib.addMenuListener(new MenuListener()
		{
			@Override
			public void menuSelected(MenuEvent e)
			{
				pelementos.setVisible(true);
				popciones.setVisible(false);
				pfiguras.setVisible(false);
				JVentanaDibujo.this.add(pelementos, BorderLayout.NORTH);
			}

			public void menuDeselected(MenuEvent e){};

			
			public void menuCanceled(MenuEvent e){};
		});

		opciones.addMenuListener(new MenuListener()
		{
			@Override
			public void menuSelected(MenuEvent e)
			{
				pelementos.setVisible(false);
				popciones.setVisible(true);
				pfiguras.setVisible(false);
				JVentanaDibujo.this.add(popciones, BorderLayout.NORTH);
			}

			public void menuDeselected(MenuEvent e){};

			
			public void menuCanceled(MenuEvent e){};
		});
			
			mb.add(archivo);
			mb.add(figuras);
			mb.add(dib);
			//mb.add(herramientas);
			mb.add(opciones);
		
			this.setJMenuBar(mb);
		}
		
		public Dibujo getDibujo()
		{
			return dibujo;
		}

		public Lienzo getLienzo()
		{
			return lienzo;
		} 

	}




