package JPaint.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JOptionPane;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Dimension;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;


public class JVentanaInicio extends JFrame 

{
	private JLabel titulo;
	private JLabel icono;
	private JProgressBar barra;	

	public JVentanaInicio()
	{
		
		this.initComponents();
		this.initFrame();
		this.iterate(barra);
		

	}

	public void initComponents()
	{
		
		titulo= new JLabel("J P A I N T");
		icono= new JLabel (new ImageIcon ("iconos/inicio.jpg"));
		barra=new JProgressBar(0,100);
		barra.setVisible(true);
		barra.setValue(0);
		barra.setBorderPainted(true);
		barra.setStringPainted(true);
		
		
		titulo.setFont(new Font("Ar Hermann",Font.BOLD,40));
		titulo.setForeground(Color.PINK);
		
		this.setLayout(new BorderLayout());


		JPanel panelC = new JPanel(new GridLayout(1,2));
        panelC.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        panelC.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
         panelC.add(icono);
         panelC.add(titulo);
         panelC.setBackground(Color.BLACK);
         this.add(panelC, BorderLayout.CENTER);
        
       JPanel panelS= new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
         panelS.add(barra);
         panelS.setBackground(Color.GRAY);
		this.add(panelS,BorderLayout.SOUTH);

 		this.setVisible(true);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         this.addWindowListener(new WindowAdapter()
         {
         	public void windowClosing (WindowEvent e)
         	{

         		if (barra.getValue()<barra.getMaximum())
         		{
         			barra.setVisible(false);
         			JOptionPane.showMessageDialog(null,"El programa no ha llegado a cargarse. Saliendo...");
         			JVentanaInicio.this.dispose();
         			System.exit(0);
         		}

         	}
         });


	}

	public void initFrame()
	{
		
		 JPanel panelN= new JPanel(new GridLayout(1,3));
         JLabel mensaje1= new JLabel("Version 1.0");
         JLabel mensaje2= new JLabel ("por Teresa Gonzalez Garcia");
         JLabel mensaje3= new JLabel ("2-GITT ICAI");
                    
         
         mensaje1.setFont(new Font("Arial",Font.BOLD,15));
         mensaje2.setFont(new Font("Arial",Font.ITALIC,15));
         mensaje3.setFont(new Font("Arial",Font.ITALIC,15));

         panelN.add(mensaje1);
         panelN.add(mensaje2);
         panelN.add(mensaje3);
         
        this.add(panelN,BorderLayout.NORTH);
        ImageIcon icon= new ImageIcon("iconos/logo.jpg");
		this.setIconImage(icon.getImage()); 

		
		this.setMaximumSize(new Dimension(800,300));
		this.setMinimumSize(new Dimension(250,250));
		this.setPreferredSize(new Dimension(700,450));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Inicio JPaint");
		this.setResizable(true);
	}

	private void iterate(JProgressBar barra)
	{
		int i=0;
		while(i<=barra.getMaximum())
		{    
  		barra.setValue(i);    
  		i=i+5;    
  		try{
  			Thread.sleep(150);
  			}catch(InterruptedException e)
  			{
  				e.printStackTrace();
  			}
		}

	}

	public boolean llegaMaximoBarra()
	{
		if (barra.getValue()==barra.getMaximum())
			return true;
		else
			return false;
	}
}




                    