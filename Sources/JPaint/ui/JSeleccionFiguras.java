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

//pintar lo delegamos a la ventana principal, donde encontramos los metodos de pintar de cada tipo de figura. mouselistener
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JColorChooser;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.JOptionPane;

public class JSeleccionFiguras extends JPanel
{
    private JVentanaDibujo ventana;
    private Lienzo lienzo;
    private Dibujo dibujo;
    private int xInicial = 0;
    private int yInicial = 0;
    private boolean fill;
    private boolean seleccionado;
    private Color color;
    private JColorChooser colores= new JColorChooser();
    private JCheckBox relleno;
    private JRadioButton seleccionar;
    private JRadioButton arco;
    private JRadioButton lrecta;
    private JRadioButton circulo;
    private JRadioButton ovalo;
    private JRadioButton cuadrado;
    private JRadioButton rectangulo;
    private JRadioButton roundrect;
    private JRadioButton triangulo;
    private JRadioButton estrella;
    private JRadioButton flecha;
    private String figuras[]= {"Ninguno","Arco","LineaRecta","Circulo","Ovalo","Cuadrado","Rectangulo","RoundRectangulo","Triangulo","Estrella","Flecha","Texto","TrazadoLibre"};
    private JComboBox todasfiguras=new JComboBox(figuras);
    private String figurasel;

    public JSeleccionFiguras( JVentanaDibujo ventana)
    {   
        this.ventana=ventana;
       // pherramientas=new JPanelHerramientas(ventana);
        lienzo= ventana.getLienzo();
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        todasfiguras.setEditable(true);

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

        relleno=new JCheckBox(new ImageIcon(new ImageIcon("iconos/relleno.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
        relleno.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged (ChangeEvent e)
            {
                if (relleno.isSelected())
                {
                    relleno.setBackground(Color.GREEN);
                    JSeleccionFiguras.this.setRelleno(true);
                }
                else if (!relleno.isSelected())
                {
                    JSeleccionFiguras.this.setRelleno(false);
                    relleno.setBackground(null);
                }
            }
        });


        this.add(relleno);

        seleccionar=new JRadioButton(new ImageIcon(new ImageIcon("iconos/seleccionar.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
         seleccionar.addItemListener (new ItemListener()
        {
            @Override
            public void itemStateChanged (ItemEvent e)
            {
                if (e.getStateChange()==ItemEvent.SELECTED)
                {
                    
                    JSeleccionFiguras.this.setSeleccion(true);
                    JOptionPane.showMessageDialog( null, todasfiguras, "Escoja grupo de figuras a seleccionar", JOptionPane.QUESTION_MESSAGE);
                    figurasel=todasfiguras.getSelectedItem().toString();
                    if (figurasel=="Ninguno")
        			{
        			dibujo=ventana.getDibujo();
                    dibujo.mostrarOcultarTodo(true);
                    lienzo.repaint();
               		 }else
               		 {
                    dibujo=ventana.getDibujo();
                    dibujo.mostrarSeleccion(figurasel,JSeleccionFiguras.this.getColor(),JSeleccionFiguras.this.getRelleno());
                    lienzo.repaint();
               		 }

                }
              }
               
        });

        this.add(seleccionar);

        cuadrado= new JRadioButton (new ImageIcon(new ImageIcon("iconos/cuadrado.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
        cuadrado.addItemListener (new ItemListener()
        {
            @Override
            public void itemStateChanged (ItemEvent e)
            {
                if (e.getStateChange()==ItemEvent.SELECTED)
                {
                                    cuadrado.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        
    
                    lienzo.addMouseListener(new MouseAdapter()
                    {
                        public void mousePressed(MouseEvent me)
                        {
                            if(xInicial==0 && yInicial ==0)
                            {
                                JSeleccionFiguras.this.inicializarCreacionFigura(me);
                            }
                            else
                            {
                                int x = me.getX();
                                int y = me.getY();
                                int lado = x - xInicial;
                                dibujo=ventana.getDibujo();
                                dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Cuadrado(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,lado));
                                lienzo.repaint(); 
                                lienzo.removeMouseListener(this); 
                                lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                xInicial=0;
                                yInicial=0;   
                                }
                    }
                            
                               
                    });      

                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     cuadrado.setBackground(null);  
                                     
                                 }
                
            }
        });

        this.add(cuadrado);

        arco= new JRadioButton (new ImageIcon(new ImageIcon("iconos/arco.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
         arco.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    arco.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int x = me.getX();
                                            int y = me.getY();
                                            //System.out.println("x:" + xInicial + "y:" + yInicial + "dif:" + (x - xInicial) );
                                            int angulo1 = x- xInicial;
                                            int angulo2= y -yInicial;
                                            //fill=JSeleccionFiguras.this.getColor();
                                            //color= pherramientas.getColorSel();
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Arco(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,x - xInicial,y -yInicial,angulo1, angulo2));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this); 
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     arco.setBackground(null);   
                                     
                                     
                                    }
                                         
                                }
                          });
    this.add(arco);
        
        circulo= new JRadioButton (new ImageIcon(new ImageIcon("iconos/circulo.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
        circulo.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    circulo.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int x = me.getX();
                                            int y = me.getY();
                                            int radio = x - xInicial;
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Circulo(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,radio));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this); 
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     circulo.setBackground(null);   
                                     
                                     
                                    }
                                         
                                }
                          });
        this.add(circulo);
        
         ovalo= new JRadioButton (new ImageIcon(new ImageIcon("iconos/ovalo.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
         ovalo.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    ovalo.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int x = me.getX();
                                            int y = me.getY();
                                            int medida1 = x - xInicial;
                                            int medida2 = y - yInicial;
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Ovalo(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,medida1,medida2));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this);
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     ovalo.setBackground(null);   
                                     
                                     
                                    }
                                         
                                }
                          });
        this.add(ovalo);
         
         rectangulo= new JRadioButton (new ImageIcon(new ImageIcon("iconos/rectangulo.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
        rectangulo.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    rectangulo.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int x = me.getX();
                                            int y = me.getY();
                                            int medida1 = x - xInicial;
                                            int medida2 = y - yInicial;
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Rectangulo(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,medida1, medida2));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this);
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     rectangulo.setBackground(null);   
                                     
                                     
                                    }
                                         
                                }
                          });
        this.add(rectangulo);

       
        roundrect=new JRadioButton (new ImageIcon(new ImageIcon("iconos/roundrect.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
         roundrect.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    roundrect.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int x = me.getX();
                                            int y = me.getY();
                                            int medida1 = x - xInicial;
                                            int medida2 = y - yInicial;
                                            int angulo1=xInicial;
                                            int angulo2=yInicial;
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new RoundRectangulo(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,medida1, medida2, angulo1, angulo2));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this); 
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     roundrect.setBackground(null);       
                                    }
                                         
                                }
                          });
        this.add(roundrect);
       
        triangulo=new JRadioButton (new ImageIcon(new ImageIcon("iconos/triangulo.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
        triangulo.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    triangulo.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int puntosx[] = {xInicial,me.getX(),me.getX()+20};
                                            int puntosy[]= {yInicial,me.getY(),yInicial};
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Triangulo(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,puntosx,puntosy));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this); 
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     triangulo.setBackground(null);       
                                    }
                                         
                                }
                          });
        this.add(triangulo);
        
         lrecta=new JRadioButton (new ImageIcon(new ImageIcon("iconos/lrecta.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
        lrecta.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    lrecta.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int medida1 = me.getX();
                                            int medida2 = me.getY();
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new LineaRecta(xInicial,yInicial,false,JSeleccionFiguras.this.getColor(),true,medida1,medida2));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this); 
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     lrecta.setBackground(null);   
                                     
                                    }
                                         
                                }
                          });
        this.add(lrecta);
        
        
         
         estrella= new JRadioButton (new ImageIcon(new ImageIcon("iconos/estrella.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
        estrella.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    estrella.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int puntosx[] = {xInicial,xInicial+50,xInicial+100,xInicial+150, xInicial+200, xInicial+150,xInicial+170,xInicial+100,xInicial+20,xInicial+50,xInicial};
                                            int puntosy[] = {yInicial,yInicial,yInicial-50,yInicial,yInicial,yInicial+50,yInicial+100,yInicial+70,yInicial+100,yInicial+50,yInicial}; 
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Estrella(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,puntosx,puntosy));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this);  
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     estrella.setBackground(null);    
                                     
                                    }
                                         
                                }
                          });
        this.add(estrella);
        
         flecha= new JRadioButton (new ImageIcon(new ImageIcon("iconos/flecha.png").getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT)));
        flecha.addItemListener(new ItemListener()
                        {   

                                @Override
                                public void itemStateChanged (ItemEvent e)
                                {
                                    if (e.getStateChange()==ItemEvent.SELECTED)
                                    {
                                    flecha.setBackground(Color.YELLOW);  
                                    lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                                    lienzo.addMouseListener(new MouseAdapter()
                                    {
                                        @Override
                                         public void mousePressed(MouseEvent me)
                                    {
                                        if(xInicial==0 && yInicial ==0)
                                        {
                                            JSeleccionFiguras.this.inicializarCreacionFigura(me);
                                        }
                                        else
                                        {
                                            int puntosx[] = {xInicial,xInicial,xInicial+40,xInicial+40,me.getX(),xInicial+40,xInicial+40};
                                            int puntosy[]= {yInicial ,yInicial+40,yInicial+40,yInicial+60,me.getY(),yInicial-60,yInicial};
                                            dibujo=ventana.getDibujo();
                                            dibujo.pintar(JSeleccionFiguras.this.getColor().toString(),new Flecha(xInicial,yInicial,fill,JSeleccionFiguras.this.getColor(),true,puntosx,puntosy));
                                            lienzo.repaint(); 
                                            lienzo.removeMouseListener(this); 
                                            lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                            xInicial=0;
                                            yInicial=0;   
                                            }
                                      }
                                                  
                                    });

                                    }else if (e.getStateChange() == ItemEvent.DESELECTED)
                                    {
                                     flecha.setBackground(null);    
                                    }
                                         
                                }
                          });
        this.add(flecha);

    }

        public void inicializarCreacionFigura(MouseEvent me)
    {
        xInicial = me.getX();
        yInicial= me.getY();
        lienzo.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
    }

    public void setRelleno (boolean fill)
    {
        this.fill=fill;
    } 

    public boolean getRelleno()
    {
        return fill;
    }

    public void setSeleccion (boolean seleccionado)
    {
        this.seleccionado=seleccionado;
    } 

    public boolean getSeleccion()
    {
        return seleccionado;
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