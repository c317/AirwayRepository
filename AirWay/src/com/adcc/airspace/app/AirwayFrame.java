package com.adcc.airspace.app;

import java.awt.Color;
import java.util.ArrayList;
import com.adcc.airspace.bean.MyRoute;

import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.airspaces.Airspace;
import gov.nasa.worldwind.util.WWUtil;

public class AirwayFrame extends WWJAnscent{	
	private static final long serialVersionUID = 1L;
	private AirspaceLayer airspaceLayer;
	public AirwayFrame(){
		this.setTitle("º½Â·");
		addAirSpaceLayer("º½Â·");
	}
	public void addAirSpaceLayer(String title){
		airspaceLayer = new AirspaceLayer();
		airspaceLayer.setName(title);
		addRoutesOnAirspaceLayer(airspaceLayer);
		this.worldWindGLCanvas.getModel().getLayers().add(airspaceLayer);
		this.layerPanel.update(worldWindGLCanvas);
	}
	protected void addRoutesOnAirspaceLayer(AirspaceLayer airspaceLayer){
		ShpAnalyze dataSource = new ShpAnalyze();
		ArrayList<MyRoute> routes = dataSource.getRoutes();
		MyRoute route;
		for(int i=0;i<routes.size();i++){
			route = routes.get(i);
			route.setTerrainConforming(false, false);
	        setupDefaultMaterial(route, Color.GREEN);
	        airspaceLayer.addAirspace(route);
		}
	}
	protected void setupDefaultMaterial(Airspace a, Color color){
        a.getAttributes().setDrawOutline(true);
        a.getAttributes().setMaterial(new Material(color));
        a.getAttributes().setOutlineMaterial(new Material(WWUtil.makeColorBrighter(color)));
        a.getAttributes().setOpacity(0.8);
        a.getAttributes().setOutlineOpacity(0.9);
        a.getAttributes().setOutlineWidth(3.0);
   }
	public static void main(String args[]){
		AirwayFrame appFrame = new AirwayFrame();
	}
}
