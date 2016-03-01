import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.util.StatusBar;
import gov.nasa.worldwindx.examples.LayerPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


public class WWJAnscent extends JFrame{
	protected WorldWindowGLCanvas worldWindGLCanvas;
	protected Model modelEarth;
	protected LayerPanel layerPanel;
	protected StatusBar statusBar;
	public WWJAnscent(){
		Dimension canvasSize = new Dimension(800,600);
		this.worldWindGLCanvas = new WorldWindowGLCanvas();
		this.worldWindGLCanvas.setPreferredSize(canvasSize);
		
		this.modelEarth = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
		this.worldWindGLCanvas.setModel(this.modelEarth);
		this.add(this.worldWindGLCanvas,BorderLayout.CENTER);
		
		this.layerPanel = new LayerPanel(worldWindGLCanvas);
		this.add(this.layerPanel,BorderLayout.WEST);
		
		this.statusBar = new StatusBar();
		this.statusBar.setEventSource(worldWindGLCanvas);
		this.add(this.statusBar,BorderLayout.PAGE_END);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(canvasSize);
	}
	
	public static void main(String args[]){
		String strTitle = "World Wind Java»ù´¡Àà";
		WWJAnscent WWRun = new WWJAnscent();
		WWRun.setTitle(strTitle+":"+WWRun.getClass().getName());
	}
}
