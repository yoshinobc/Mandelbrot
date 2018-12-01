import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mandelbrot extends JPanel
{
  int n = 1;
  int mode = 0;

  private static final Dimension DEFAULT_SIZE = new Dimension(480, 480);

	public Mandelbrot()
	{
		setPreferredSize(DEFAULT_SIZE);

    //Thread th = new AnimeThread();
    //th.start();
    addMouseListener(new OnClick());
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		draw(g2, (double)getWidth(), (double)getHeight());
	}

	private void draw(Graphics2D g2, double x0, double y0){
/*
    double a_min=-0.7;
    double a_max=1.5;
		double b_min=-1.5;
		double b_max=1.5;
    */

    double a_min=0.5;
    double a_max=1;
    double b_min=0;
    double b_max=0.5;

		double a_step=(a_max-a_min)/x0;
		double b_step=(b_max-b_min)/y0;
		int wx,wy;
		wx=0;
		for(double a=a_min;a<=a_max;a+=a_step/2){
			wy=0;
			for(double b=b_min;b<=b_max;b+=b_step/2){
				double x=0;
				double y=0;
				int c=0;
        long startTime = System.nanoTime();
				for(c=0;c<=n;c++){
					double x2=x*x;
					double y2=y*y;
					double zx=x2-y2-a;
					double zy=2*x*y-b;
					x=zx;
					y=zy;
					if(x2+y2>=4)
						break;
				}
				if(c==n+1){
          long estimatedTime = System.nanoTime() - startTime;
					c=0;
          //g2.setColor(new Color(0xff0000 / (int)estimatedTime));
					g2.setColor(Color.BLACK);
					g2.drawLine(wx, wy, wx, wy);
				}else{
            //g2.setColor(new Color(c));

          g2.setColor(new Color(0xffffff / c));
					//g2.setColor(new Color(0xffffff / 512 * c));
					g2.drawLine(wx, wy, wx, wy);
				}
				++wy;
			}
			++wx;
		}
	}

  class AnimeThread extends Thread{
    public void run(){
      while(true){
        n++;
        repaint();

        try{
          Thread.sleep(100);
        } catch(Exception e){
        }
      }
    }
  }

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

	public static void main(String[] args)
	{
		try { UIManager.setLookAndFeel(
			UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) { e.printStackTrace(); }

		Mandelbrot mandelbrot = new Mandelbrot();

		JFrame frame = new JFrame(mandelbrot.getClass().getName());
		frame.getContentPane().add(mandelbrot);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,400);
		frame.pack();
		frame.setLocationRelativeTo(null); // center
		frame.setVisible(true);


	}

}
