import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Mandelbrot2 extends JPanel
{
  int n = 1;
  int mode = 0;

  private static final Dimension DEFAULT_SIZE = new Dimension(960, 960);

	public Mandelbrot2()
	{
		setPreferredSize(DEFAULT_SIZE);

    //Thread th = new AnimeThread();
    //th.start();
    //addMouseListener(new OnClick());
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		draw(g2, (double)getWidth(), (double)getHeight());
	}

	private void draw(Graphics2D g2, double x0, double y0){
	
    double a_min=-0.5;
    double a_max=1.5;
	double b_min=-1;
	double b_max=1;
	
	/*
	double a_min=-0.2;
    double a_max=-0.1;
	double b_min=-0.05;
	double b_max=0.05;
	*/
    /*
    double a_min=0.650000004497600;
    double a_max=0.650000004497650;
    double b_min=0.450000003797608;
    double b_max=0.450000003797658;
*/
		double a_step=(a_max-a_min)/x0;
		double b_step=(b_max-b_min)/y0;
		int wx,wy;
		wx=0;
		for(double a=a_min;a<=a_max;a+=a_step){
			wy=0;
			for(double b=b_min;b<=b_max;b+=b_step){
				double x=0;
				double y=0;
				int c=0;
        		long startTime = System.nanoTime();
				for(c=0;c<=255;c++){
					double x2=x*x;
					double y2=y*y;
					double zx=x2-y2-a;
					double zy=2*x*y-b;
					x=zx;
					y=zy;
					if(x2+y2>=25)
						break;
				}
				if(c==256){
          			long estimatedTime = System.nanoTime() - startTime;
					c=0;
					//System.out.println(estimatedTime);
          			//g2.setColor(255,255,(int)Math.sqrt(estimatedTime));
          			double x2 = x * x;
          			double y2 = y * y;
          			double z = x2 + y2;
          			double _z = Math.abs(Math.tan(1/z))*130;
          			double _x = Math.abs(Math.tan(1/x2))*70;
          			double _y = Math.abs(Math.tan(1/y2))*70;

          			if(_z >= 255) _z = 255;
          			if(_x >= 255) _x = 255;
          			if(_y >= 255) _y = 255;
          			//System.out.println(_z);
          			g2.setColor(new Color(0,(int)_x,255));
          			//g2.setColor(new Color((int)_x,(int)_y,(int)_z));

					//g2.setColor(Color.BLACK);
					//g2.setColor(new Color())
					g2.drawLine(wx, wy, wx, wy);
				}else{
            //g2.setColor(new Color(c));

          			//g2.setColor(new Color(0xffffff / c));
          			//System.out.println((int)x);
          			//double x2 = Math.sqrt(x*x);
          			//double y2 = Math.sqrt(y*y);
          			double x2 = x * x;
          			double y2 = y * y;
          			double z = x2 + y2;
          			double _z = Math.abs(Math.tan(1/z))*130;
          			double _x = Math.abs(Math.tan(1/x2))*70;
          			double _y = Math.abs(Math.tan(1/y2))*70;

          			if(_z >= 255) _z = 255;
          			if(_x >= 255) _x = 255;
          			if(_y >= 255) _y = 255;
          			if(x2 >= 255) x2 = 255;
          			if(y2 >= 255) y2 = 255;
					g2.setColor(new Color(255 - c,(int)x2,(int)y2));
					g2.setColor(new Color((int)x2,255 - c,(int)y2));
          			g2.setColor(new Color((int)_x,0,(int)_y));

					//g2.setColor(new Color(0xffffff / c));
					g2.drawLine(wx, wy, wx, wy);
				}
				++wy;
			}
			++wx;
		}
	}
/*
  class AnimeThread extends Thread{
    public void run(){

      while(mode == 1){
        n++;
        repaint();

        try{
          Thread.sleep(100);
        } catch(Exception e){
        }
      }
    }
  }
  */
/*
  class OnClick extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
      if(mode == 0){
        mode = 1;
        Thread th = new AnimeThread();
        th.start();
      } else {
        mode = 0;
      }
    }
  }
  */

	public static void main(String[] args)
	{
		try { UIManager.setLookAndFeel(
			UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) { e.printStackTrace(); }

		Mandelbrot2 mandelbrot = new Mandelbrot2();

		JFrame frame = new JFrame(mandelbrot.getClass().getName());
		frame.getContentPane().add(mandelbrot);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,400);
		frame.pack();
		frame.setLocationRelativeTo(null); // center
		frame.setVisible(true);


	}

}
