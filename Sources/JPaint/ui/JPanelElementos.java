package JPaint.ui;
import JPaint.dominio.Dibujo;
import JPaint.dominio.Texto;
import JPaint.dominio.TrazadoLibre;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class JPanelElementos extends JPanel
{
private String[] fuentes=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //fuentes disponibles
private String[] tamanos={"10","12","14","16","20"};
private String[] grosores={"10","20","30","40","50"};
private JVentanaDibujo ventana;
private Lienzo lienzo;
private Dibujo dibujo;
private JRadioButton lapiz;
private JRadioButton pincel;
private JButton texto;
private ButtonGroup grupo= new ButtonGroup();
private JComboBox selectort= new JComboBox(fuentes);
private JComboBox tamano=new JComboBox(tamanos);
private JComboBox grosor=new JComboBox(grosores);
private Font fuente;
private JRadioButton negrita=new JRadioButton("Negrita");
private JRadioButton cursiva= new JRadioButton("Cursiva");
private JRadioButton normal= new JRadioButton ("Normal");
private String s;
private String tipo;
private int estilo;
private int tam;
private int gros;
private Color color;

private int x=-1;
private int y=-1;
private int xInicial=0;
private int yInicial=0;


private JColorChooser colores=new JColorChooser();


 public JPanelElementos(JVentanaDibujo ventana)
 {	
 	this.ventana=ventana;
    estilo=Font.PLAIN;
 	lienzo=ventana.getLienzo();
 	this.setLayout(new FlowLayout());

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

 	lapiz=new JRadioButton(new ImageIcon(new ImageIcon("iconos/lapiz.png").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
     lapiz.addItemListener (new ItemListener()
        {
            @Override
            public void itemStateChanged (ItemEvent e)
            {
                if(e.getStateChange()==ItemEvent.SELECTED)
                {
                    xInicial=0;
                    yInicial=0;
                    lapiz.setBackground(Color.YELLOW);  
                    Cursor clapiz = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("iconos/lapiz.png").getImage(), new Point(0,0), "clapiz");
                    lienzo.setCursor(clapiz);
                     JPanelElementos.this.setGrosor(5);
        
                    lienzo.addMouseMotionListener(new MouseMotionAdapter()
                    {
                        @Override
                         public void mouseDragged (MouseEvent me)
                        {
                            JPanelElementos.this.FormaMouseDragged(me, xInicial, yInicial);
                            if (x>=0 && y>=0)
                            dibujo.pintar(JPanelElementos.this.getColor().toString(), new TrazadoLibre(x,y,true,JPanelElementos.this.getColor(),true,JPanelElementos.this.getGrosor()));
                            
                       }

                    });
                }else if (e.getStateChange()== ItemEvent.DESELECTED)
                {
                    lapiz.setBackground(null);  
                    xInicial=-1;
                    yInicial=-1;
                    lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
    
            }
            });
 	this.add(lapiz);

 	pincel=new JRadioButton(new ImageIcon(new ImageIcon("iconos/pincel.png").getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
    pincel.addItemListener (new ItemListener()
        {
            @Override
            public void itemStateChanged (ItemEvent e)
            {
                if(e.getStateChange()==ItemEvent.SELECTED)
                {
                    xInicial=0;
                    yInicial=0;
                    pincel.setBackground(Color.YELLOW); 
                    Cursor cpincel = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("iconos/pincel.png").getImage(), new Point(0,0), "cpincel");
                    lienzo.setCursor(cpincel);
    
                     lienzo.addMouseMotionListener(new MouseMotionAdapter()
                    {
                        @Override
                                        public void mouseDragged (MouseEvent me)
                                        {
                                            JPanelElementos.this.FormaMouseDragged(me, xInicial, yInicial);
                                            if (x>=0 && y>=0)
                                             dibujo.pintar(JPanelElementos.this.getColor().toString(), new TrazadoLibre(x,y,true,JPanelElementos.this.getColor(),true,JPanelElementos.this.getGrosor()));
                                        }
                    });
                     }else if (e.getStateChange()== ItemEvent.DESELECTED)
                {
                    pincel.setBackground(null);  
                    xInicial=-1;
                    yInicial=-1;
                    lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
    
            }
            
        });
	this.add(pincel);

	grosor.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed (ActionEvent e)
							{
								gros=Integer.parseInt(grosor.getSelectedItem().toString());
							}
						});
	grosor.setMaximumSize(new Dimension(50,30));
	grosor.setPreferredSize(new Dimension(50,20));
	this.add(grosor);

    negrita.addItemListener (new ItemListener()
        {
            @Override
            public void itemStateChanged (ItemEvent e)
            {
                if (e.getStateChange()==ItemEvent.SELECTED)
                                                           
                     JPanelElementos.this.setEstilo(Font.BOLD);
                               
                 }

         });
    this.add(negrita);

     cursiva.addItemListener (new ItemListener()
        {
            @Override
            public void itemStateChanged (ItemEvent e)
            {
                if (e.getStateChange()==ItemEvent.SELECTED)                                        
                     JPanelElementos.this.setEstilo(Font.ITALIC);
                               
                 }

         });
    this.add(cursiva);

	//boton habilitador escribir texto
 	texto=new JButton(new ImageIcon(new ImageIcon("iconos/texto.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
    texto.addActionListener (new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                dibujo=ventana.getDibujo();
                s=JOptionPane.showInputDialog(null,"Escriba texto que quiera dibujar:","Texto", JOptionPane.PLAIN_MESSAGE);
                //fuente=new Font(tipo,estilo,tam); 
                fuente=new Font (JPanelElementos.this.getTipo(), JPanelElementos.this.getEstilo(), JPanelElementos.this.getTamano());
                lienzo.setCursor(new Cursor(Cursor.TEXT_CURSOR));
                lienzo.addMouseListener(new MouseAdapter()
                    {
                        @Override
                         public void mouseClicked(MouseEvent e)
                        {
                        int x=e.getX();
                        int y=e.getY();
                        dibujo.pintar(JPanelElementos.this.getColor().toString(), new Texto(x,y,false,JPanelElementos.this.getColor(),true,JPanelElementos.this.getTexto(),JPanelElementos.this.getFuente()));
                        lienzo.repaint();
                        lienzo.removeMouseListener(this);
                        lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        x=0;
                        y=0;
                        }
                                   
                    });
                } 
        
            });

	this.add(texto);

	selectort.addActionListener(new ActionListener()
					{
						@Override
								public void actionPerformed (ActionEvent e)
								{
									JPanelElementos.this.setTipo(selectort.getSelectedItem().toString());
								}
					});
	selectort.setMaximumSize(new Dimension(100,40));
	selectort.setPreferredSize(new Dimension(110,40));
	this.add(selectort);

	tamano.addActionListener(new ActionListener()
					{
						@Override
								public void actionPerformed (ActionEvent e)
								{
									JPanelElementos.this.setTamano(Integer.parseInt(tamano.getSelectedItem().toString()));
								}
					});
	tamano.setMaximumSize(new Dimension(50,30));
	tamano.setPreferredSize(new Dimension(50,20));
	this.add(tamano);

}

 	public void setFuente(Font fuente)
 	{
 		this.fuente=fuente;
 	}


 	public Font getFuente()
 	{
 		return fuente;
 	}

 	public void setGrosor (int gros)
 	{
 		this.gros=gros;
 	}

 	public int getGrosor()
 	{
 		return gros;
 	}

    public void setEstilo (int estilo)
    {
        this.estilo=estilo;
    }

 	public int getEstilo()
 	{
 		return estilo;
 	}

     public void setTamano (int tam)
    {
        this.tam=tam;
    }

 	public int getTamano()
 	{
 		return tam;
 	}

     public void setTipo (String tipo)
    {
        this.tipo=tipo;
    }

 	public String getTipo()
 	{
 		return tipo;
 	}

 	public String getTexto()
 	{
 		return s;
 	}

 	//metodo del mouse dragged

 	private void FormaMouseDragged (MouseEvent me, int xInicial, int yInicial)
 	{
        
        if (xInicial==0 && yInicial==0)
        {
        dibujo=ventana.getDibujo();
 	  x=me.getX();
 	   y=me.getY();
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
