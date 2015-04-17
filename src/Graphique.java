import java.awt.*;

import javax.swing.*;


public class Graphique extends JPanel {
	/**
	 * correspondance entre une unite de mesure et un pixel sur X
	 */
	private float echelleX;
	
	/**
	 * correspondance entre une unite de mesure et un pixel sur Y
	 */
	private float echelleY;
	
	/**
	 * valeur max du graphique sur X
	 */
	private int maxx;
	
	/**
	 * valeur max du graphique sur Y
	 */
	private int maxy;
	
	/**
	 * tableau de donnees
	 */
	private Tableau donnees;
	
	/**
	 * marge sur Y
	 */
	private int margeY=50;
	
	/**
	 * marge sur X
	 */
	private int margeX=50;
	
	/**
	 * label texte a placer au bout de l'axe Y
	 */
	private String labelY;
	
	/**
	 * constructeur d'un graphique
	 * @param d tableau de donnees
	 * @param dx hauteur du JPanel en pixels
	 * @param dy largeur du JPanel en pixels
	 * @param fitOnX si a true, l'echelle du graphique sera de 1/1. Sinon, elle sera de maxY/maxX
	 * @param labelY label texte a afficher au bout de l'axe Y
	 */
	public Graphique(Tableau t, int dx, int dy, boolean fitOnX, String labelY)
	{
		super();
		this.donnees = t;
		if(!fitOnX)
		{
			this.maxx=(int) donnees.data.length-1;
			this.maxy = (int) donnees.data[donnees.data.length-1];
		}
		else
		{
			this.maxx=donnees.data.length-1;
			//this.maxy=Math.round(d[d.length-1]+1);
			this.maxy=donnees.data.length-1;
		}
		
		this.echelleX = (float) maxx / ((float)(dx-this.margeX*2));
		this.echelleY = ((float) maxy) / ((float) (dy-this.margeY*2));
		this.setSize(dx, dy);
		this.setBackground(Color.white);
		this.labelY = labelY;
	}
	
	 public void paintComponent(Graphics g)
	 {
		 g.setColor(Color.BLACK);
		  //axes
		    g.drawLine(margeX, this.getHeight()-margeY, this.getWidth()-margeX, this.getHeight()-margeY);
		    g.drawLine(margeY, this.getHeight()-margeY, margeX, margeY);
		    
		    
		    
		    //courbe des donnees
		    g.setColor(Color.RED);
		    g.drawString("T(n)", margeX, this.getHeight()-margeY+35);
		    
		    int lastX1=margeX;
		    int lastY1=this.getHeight()-margeY;
		    
		    for(int i = 0; i < donnees.data.length; i+=donnees.pas)
		    {
		    	
		    	//float x1 = ((float) i-1) / echelleX;
		    	//float y1 = ((float) donnees.data[i-1]) / echelleY;
		    	float x2 = ((float) i) / echelleX;
		    	float y2 = ((float) donnees.data[i]) / echelleY;
		    	
		    	//int ix1 = Math.round(x1)+margeX;
		    	//int iy1 = -Math.round(y1)+this.getHeight()-margeY;
		    	int ix1=lastX1;
		    	int iy1=lastY1;
		    	int ix2 = Math.round(x2)+margeX;
		    	int iy2 = -Math.round(y2)+this.getHeight()-margeY;
		    	
		    	lastX1=ix2;
		    	lastY1=iy2;
		    	
		    	g.drawLine(ix1,iy1,ix2,iy2);
		    }
		    
		    //courbe de f(x)=x.log(x)
		    g.setColor(Color.BLUE);
		    g.drawString("n.log2(n)", margeX+39, this.getHeight()-margeY+35);
		    for(int i = 1; i < donnees.data.length; i++)
		    {
		    	
		    	float x1 = ((float) i-1) / echelleX;
		    	float y1 = ((float) Math.log(i-1) / (float) Math.log(2))*(i-1) / echelleY;
		    	float x2 = ((float) i) / echelleX;
		    	float y2 = ((float) Math.log(i) / (float) Math.log(2))*(i) / echelleY;
		    	
		    	int ix1 = Math.round(x1)+margeX;
		    	int iy1 = -Math.round(y1)+this.getHeight()-margeY;
		    	int ix2 = Math.round(x2)+margeX;
		    	int iy2 = -Math.round(y2)+this.getHeight()-margeY;
		    	/*
		    	if(iy2 < margeY)
		    	{
		    		break;
		    	}*/
		    	g.drawLine(ix1,iy1,ix2,iy2);
		    }
		    
		    //courbe de f(x)=x
		    g.setColor(Color.gray);
		    g.drawString("f(n)=n", margeX+100, this.getHeight()-margeY+35);
		    for(int i = 1; i < donnees.data.length; i++)
		    {
		    	
		    	float x1 = ((float) i-1) / echelleX;
		    	float y1 = ((float) i-1) / echelleY;
		    	float x2 = ((float) i) / echelleX;
		    	float y2 = ((float) i) / echelleY;
		    	
		    	int ix1 = Math.round(x1)+margeX;
		    	int iy1 = -Math.round(y1)+this.getHeight()-margeY;
		    	int ix2 = Math.round(x2)+margeX;
		    	int iy2 = -Math.round(y2)+this.getHeight()-margeY;
		    	/*
		    	if(iy2 < margeY)
		    	{
		    		break;
		    	}*/
		    	g.drawLine(ix1,iy1,ix2,iy2);
		    }
		    
		  //courbe de f(x)=x.Vx
		    g.setColor(Color.orange);
		    g.drawString("n.Vn", margeX+150, this.getHeight()-margeY+35);
		    for(int i = 1; i < donnees.data.length; i++)
		    {
		    	
		    	float x1 = ((float) i-1) / echelleX;
		    	float y1 = ((float) i-1) * (float) Math.sqrt(i-1) / echelleY;
		    	float x2 = ((float) i) / echelleX;
		    	float y2 = ((float) i) * (float) Math.sqrt(i)/ echelleY;
		    	
		    	int ix1 = Math.round(x1)+margeX;
		    	int iy1 = -Math.round(y1)+this.getHeight()-margeY;
		    	int ix2 = Math.round(x2)+margeX;
		    	int iy2 = -Math.round(y2)+this.getHeight()-margeY;
		    	/*
		    	if(iy2 < margeY)
		    	{
		    		break;
		    	}*/
		    	g.drawLine(ix1,iy1,ix2,iy2);
		    }
		    
		  
		  //courbe de f(x)=x.x
		    g.setColor(Color.MAGENTA);
		    g.drawString("n²", margeX+200, this.getHeight()-margeY+35);
		    for(int i = 1; i < donnees.data.length; i++)
		    {
		    	
		    	float x1 = ((float) i-1) / echelleX;
		    	float y1 = ((float) Math.pow(i-1,2)) / echelleY;
		    	float x2 = ((float) i) / echelleX;
		    	float y2 = ((float) Math.pow(i,2)) / echelleY;
		    	
		    	int ix1 = Math.round(x1)+margeX;
		    	int iy1 = -Math.round(y1)+this.getHeight()-margeY;
		    	int ix2 = Math.round(x2)+margeX;
		    	int iy2 = -Math.round(y2)+this.getHeight()-margeY;
		    	/*
		    	if(iy2 < margeY)
		    	{
		    		break;
		    	}*/
		    	g.drawLine(ix1,iy1,ix2,iy2);
		    	//System.out.println(x1+" "+y1+" "+x2+" "+y2);
		    }
		    
		    
		  //courbe de f(x)=(x.(x-1))/2
		    g.setColor(new Color(0,147,3));
		    g.drawString("n.(n-1)/2", margeX+250, this.getHeight()-margeY+35);
		    for(int i = 1; i < donnees.data.length; i++)
		    {
		    	
		    	float x1 = ((float) i-1) / echelleX;
		    	float y1 = ((float) ((i-1)*(i-2))/2) / echelleY;
		    	float x2 = ((float) i) / echelleX;
		    	float y2 = ((float) ((i)*(i-1))/2) / echelleY;
		    	
		    	int ix1 = Math.round(x1)+margeX;
		    	int iy1 = -Math.round(y1)+this.getHeight()-margeY;
		    	int ix2 = Math.round(x2)+margeX;
		    	int iy2 = -Math.round(y2)+this.getHeight()-margeY;
		    	/*
		    	if(iy2 < margeY)
		    	{
		    		break;
		    	}*/
		    	g.drawLine(ix1,iy1,ix2,iy2);
		    	//System.out.println(x1+" "+y1+" "+x2+" "+y2);
		    }
		    
		  //courbe de f(x)=(x.(x-1))/4
		    /*
		    g.setColor(Color.darkGray);
		    g.drawString("n(n-1)/4", margeX+300, this.getHeight()-margeY+35);
		    for(int i = 1; i < donnees.data.length; i++)
		    {
		    	
		    	float x1 = ((float) i-1) / echelleX;
		    	float y1 = ((float) ((i-1)*(i-2))/4) / echelleY;
		    	float x2 = ((float) i) / echelleX;
		    	float y2 = ((float) ((i)*(i-1))/4) / echelleY;
		    	
		    	int ix1 = Math.round(x1)+margeX;
		    	int iy1 = -Math.round(y1)+this.getHeight()-margeY;
		    	int ix2 = Math.round(x2)+margeX;
		    	int iy2 = -Math.round(y2)+this.getHeight()-margeY;
		    	
		    	g.drawLine(ix1,iy1,ix2,iy2);//System.out.println(x1+" "+y1+" "+x2+" "+y2);
		    }
		    */
		    g.setColor(Color.BLACK);
			  
			    //origine 0,0
			    g.drawString("0", margeX, this.getHeight()-margeY+20);
			    g.drawString("0", margeX-20, this.getHeight()-margeY);
			    
			    //n sur axe x et y
			    g.drawString(""+maxx,this.getWidth()-margeX, this.getHeight()-margeY+20);
			    g.drawString(""+maxy, margeX-35, margeY+10);
			    
			    g.drawString(labelY, margeX, margeY-2);
			    g.drawString("n", this.getWidth()-margeX+5, this.getHeight()-margeY);
		   
	 }   
}
