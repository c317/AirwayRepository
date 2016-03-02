package com.adcc.airspace.app;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.util.StatusBar;
import gov.nasa.worldwindx.examples.LayerPanel;
import gov.nasa.worldwindx.examples.util.HighlightController;
import gov.nasa.worldwindx.examples.util.ToolTipController;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


public class WWJAnscent extends JFrame{
	protected WorldWindowGLCanvas worldWindGLCanvas;
	protected Model modelEarth;
	protected LayerPanel layerPanel;
	protected StatusBar statusBar;
	protected ToolTipController toolTipController;
    protected HighlightController highlightController;
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
		
		this.toolTipController = new ToolTipController(this.worldWindGLCanvas, AVKey.DISPLAY_NAME, null);
        this.highlightController = new HighlightController(this.worldWindGLCanvas, SelectEvent.ROLLOVER);
        
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(canvasSize);
	}
}
