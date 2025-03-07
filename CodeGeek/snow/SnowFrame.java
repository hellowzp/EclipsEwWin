package com.snow;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnowFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	BufferedImage img = null ;
	Robot robot = null ;
	private Dimension d  = Toolkit.getDefaultToolkit().getScreenSize();
	int sx ; 
	int sy ; 
	int sspeed ;
	int scr ; 
	int scg ; 
	int scb ; 
	int ssize ;
	private Random r = new Random(System.currentTimeMillis());
	private List<Snow> snows = new ArrayList<Snow>();
	
	public SnowFrame() {
		super();
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
//		this.getContentPane().setBackground(Color.BLACK);
		GraphicsDevice myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment() .getDefaultScreenDevice(); 
		     //不显示任务栏
//		myDevice.setFullScreenWindow(this);
		     //下面可以显示任务栏
		DisplayMode dm=myDevice.getDisplayMode();
		//
//		this.setBounds(0,0,dm.getWidth(),dm.getHeight());
		this.setSize(d);
		SnowPanel sp = new SnowPanel();
		Thread th = new Thread(sp);
		th.start();
		this.add(sp);
		this.setVisible(true);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {if(e.getKeyCode()==KeyEvent.VK_SPACE){System.exit(0);}}
		});
	}
	class SnowPanel extends JPanel implements Runnable{
		private static final long serialVersionUID = 1L;

		public SnowPanel() {
			super();
			this.setBackground(null);
			this.getSnows();
			this.getScreenImage();
		}
		@Override
		public void run() {
			for(;;){
				for (int i = 0; i < snows.size(); i++) {
					snows.get(i).setSy(snows.get(i).getSy()+snows.get(i).getSspeed());
					if(snows.get(i).getSy()>=d.height){
						snows.get(i).setSy(0);
					}
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.repaint();
			}
		}
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img, 0, 0, d.width, d.height, this);
			for (int i = 0; i < snows.size(); i++) {
				g.setColor(new Color(snows.get(i).getScr(),snows.get(i).getScg(),snows.get(i).getScb()));
				g.setFont(new Font("Microsoft yahei",Font.BOLD , snows.get(i).getSsize()));
				g.drawString("*", snows.get(i).getSx(), snows.get(i).getSy());
			}
		}
		
		public void getScreenImage(){
			try {
				robot = new Robot();
				img = robot.createScreenCapture(new Rectangle(d));
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		public void getSnows (){
			for (int i = 0; i < 200; i++) {
				this.getSnow();
			}
		}
		public void getSnow(){
			this.getSnowData();
			snows.add(new Snow(sx,sy,sspeed,scr,scg,scb,ssize));
		}
		public void getSnowData(){
			sx = r.nextInt(d.width);
			sy = r.nextInt(d.height);
			sspeed = r.nextInt(3)+3 ;
			scr = r.nextInt(255);
			scg = r.nextInt(255);
			scb = r.nextInt(255);
			for(;;){
				ssize = r.nextInt(70);
				if(ssize>20){
					break ;
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		new SnowFrame();
	}

	class Snow{
		int sx ; 
		int sy ; 
		int sspeed ;
		int scr ; 
		int scg ; 
		int scb ; 
		int ssize ;
		public Snow() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Snow(int sx, int sy, int sspeed, int scr, int scg, int scb,
				int ssize) {
			super();
			this.sx = sx;
			this.sy = sy;
			this.sspeed = sspeed;
			this.scr = scr;
			this.scg = scg;
			this.scb = scb;
			this.ssize = ssize;
		}
		public int getSx() {
			return sx;
		}
		public void setSx(int sx) {
			this.sx = sx;
		}
		public int getSy() {
			return sy;
		}
		public void setSy(int sy) {
			this.sy = sy;
		}
		public int getSspeed() {
			return sspeed;
		}
		public void setSspeed(int sspeed) {
			this.sspeed = sspeed;
		}
		public int getScr() {
			return scr;
		}
		public void setScr(int scr) {
			this.scr = scr;
		}
		public int getScg() {
			return scg;
		}
		public void setScg(int scg) {
			this.scg = scg;
		}
		public int getScb() {
			return scb;
		}
		public void setScb(int scb) {
			this.scb = scb;
		}
		public int getSsize() {
			return ssize;
		}
		public void setSsize(int ssize) {
			this.ssize = ssize;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + scb;
			result = prime * result + scg;
			result = prime * result + scr;
			result = prime * result + ssize;
			result = prime * result + sspeed;
			result = prime * result + sx;
			result = prime * result + sy;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Snow other = (Snow) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (scb != other.scb)
				return false;
			if (scg != other.scg)
				return false;
			if (scr != other.scr)
				return false;
			if (ssize != other.ssize)
				return false;
			if (sspeed != other.sspeed)
				return false;
			if (sx != other.sx)
				return false;
			if (sy != other.sy)
				return false;
			return true;
		}
		private SnowFrame getOuterType() {
			return SnowFrame.this;
		}
		@Override
		public String toString() {
			return "Snow [sx=" + sx + ", sy=" + sy + ", sspeed=" + sspeed
					+ ", scr=" + scr + ", scg=" + scg + ", scb=" + scb
					+ ", ssize=" + ssize + "]";
		}
		
	}
}
