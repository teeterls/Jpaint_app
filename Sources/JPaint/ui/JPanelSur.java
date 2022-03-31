package JPaint.ui;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.FlowLayout;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;


//zoom
public class JPanelSur extends JPanel
{
	private JVentanaDibujo ventana;
	private JLabel cursor=new JLabel("Cursor:");
	private JLabel x=new JLabel();
	private JLabel y= new JLabel();

 public JPanelSur(JVentanaDibujo ventana)
 {
 	this.ventana=ventana;
 	Lienzo lienzo=ventana.getLienzo();
 	this.initCoord(lienzo);
 	
 	this.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
 	x.setText("0");
 	y.setText("0");
 	this.add(cursor);
 	this.add(x);
 	this.add(y);
 
}

private void initCoord(Lienzo lienzo)
{
	lienzo.addMouseMotionListener(new MouseMotionAdapter()
	{
		@Override
		public void mouseMoved(MouseEvent e)
		{
			
			x.setText(Integer.toString(e.getX()));
			y.setText(Integer.toString(e.getY()));
			
		}
	});

	lienzo.addMouseListener(new MouseAdapter()
	{
		@Override
		public void mouseExited(MouseEvent e)
		{
			x.setText("0");
			y.setText("0");
		}
	});
}

}