
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.airspaces.Airspace;
import gov.nasa.worldwind.render.airspaces.Cake;
import gov.nasa.worldwind.render.airspaces.Cake.Layer;
import gov.nasa.worldwind.render.airspaces.CappedCylinder;
import gov.nasa.worldwind.render.airspaces.Curtain;
import gov.nasa.worldwind.render.airspaces.Orbit;
import gov.nasa.worldwind.render.airspaces.PartialCappedCylinder;
import gov.nasa.worldwind.render.airspaces.Polygon;
import gov.nasa.worldwind.render.airspaces.Route;
import gov.nasa.worldwind.render.airspaces.SphereAirspace;

public class WWJAirSpace extends WWJAnscent {
	protected AirspaceLayer airspaceLayer;

	public WWJAirSpace(){
		addAirSpaceLayer("º½Â·");
		
		LatLon[] curtainNodes = new LatLon[5];
		curtainNodes[0] = LatLon.fromDegrees(34.0,120.0);
		curtainNodes[1] = LatLon.fromDegrees(34.0,122.0);
		curtainNodes[2] = LatLon.fromDegrees(35.0,122.0);
		curtainNodes[3] = LatLon.fromDegrees(35.0,120.0);
		curtainNodes[4] = LatLon.fromDegrees(34.0,120.0);
		addPolygon(curtainNodes, 15000.0, 20000.0, Color.gray);
	}

	public void addAirSpaceLayer( String Title ){
		airspaceLayer = new AirspaceLayer();
		airspaceLayer.setName(Title);
		this.worldWindGLCanvas.getModel().getLayers().add(airspaceLayer);
		this.layerPanel.update(worldWindGLCanvas);
	}

	
	public void addPolygon(LatLon[] latlonArray, Double minAltitude, Double maxAltitude,Color color){
		Polygon polygon = new Polygon();
		Iterable<LatLon>LatLons;
		LatLons = Arrays.asList(latlonArray);
		polygon.setLocations(LatLons);
		polygon.setAltitudes(minAltitude, maxAltitude);
		polygon.setTerrainConforming(true, true);
		this.setupDefaultMaterial(polygon, color);
		airspaceLayer.addAirspace(polygon);		
	}
	
	protected void setupDefaultMaterial(Airspace a, Color color)
	{
		Color outlineColor = makeBrighter(color);
		a.getAttributes().setDrawOutline(true);
		a.getAttributes().setMaterial(new Material(color));
		a.getAttributes().setOutlineMaterial(new Material(outlineColor));
		a.getAttributes().setOpacity(0.7);
		a.getAttributes().setOutlineOpacity(0.9);
		a.getAttributes().setOutlineWidth(3.0);
	}

	protected static Color makeBrighter(Color color)
	{
		float[] hsbComponents = new float[3];
		Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbComponents);
		float hue = hsbComponents[0];
		float saturation = hsbComponents[1];
		float brightness = hsbComponents[2];
		saturation /= 3f;
		brightness *= 3f;
		if (saturation < 0f) saturation = 0f;
		if (brightness > 1f) brightness = 1f;
		int rgbInt = Color.HSBtoRGB(hue, saturation, brightness);
		return new Color(rgbInt);
	}

	public static void main(String[] args){
		String strTitle = "º½Â·";
		WWJAirSpace WWRun = new WWJAirSpace();
		WWRun.setTitle( strTitle + ":" + WWRun.getClass().getName());
	}
}
