import java.awt.Color;
import java.util.Arrays;

import sun.awt.DesktopBrowse;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.DBaseRecord;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.airspaces.Airspace;
import gov.nasa.worldwind.render.airspaces.Route;
import gov.nasa.worldwind.render.airspaces.Orbit.OrbitType;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;
/**
 * 解析航路的shp文件
 * @author 刘挺  2016-3-1 下午2:37:49
 *
 */
public class ShpAnalyze extends WWJAnscent{	
	private static String shpFilePath = "http://127.0.0.1/Data/Airway_Segment_Project.shp";
	private Shapefile shpFile;
	private ShapefileRecord shpFileRecord;
	private static double routeWidth=1000d;
	private static double minAltitude = 6000d;
	private static double maxAltitude = 8400d;
	private AirwaySegment airwaySegment;
	private AirspaceLayer airspaceLayer;
	public ShpAnalyze(){
		addAirSpaceLayer("航路");
		AddRouteData(shpFilePath,"航路" );
	}

	public boolean AddRouteData( String shpFilePath , String LayerTitle){
		boolean rtnResult = false;
		Route route;
		String airwayName;
		int order;
		int startPointID;
		String startLon;
		String startLat;
		int endPointID;
		String endLon;
		String endLat;
		try	{
			shpFile = new Shapefile(shpFilePath);
			while(shpFile.hasNext()){
				shpFileRecord = shpFile.nextRecord();
				airwayName = (String) shpFileRecord.getAttributes().getValue("AirwayName");
				order = (int)((long) shpFileRecord.getAttributes().getValue("segOrder"));
				startPointID = (int)((long) shpFileRecord.getAttributes().getValue("startPoint"));
				startLon = (String) shpFileRecord.getAttributes().getValue("startLon");
				startLat = (String) shpFileRecord.getAttributes().getValue("startLat");
				endPointID = (int)((long) shpFileRecord.getAttributes().getValue("endPoint"));
				endLon = (String) shpFileRecord.getAttributes().getValue("endLon");
				endLat = (String) shpFileRecord.getAttributes().getValue("endLat");
				airwaySegment = new AirwaySegment(airwayName, order, startPointID, startLon, startLat, endPointID, endLon, endLat);
				route = new Route();
	            route.setAltitudes(minAltitude, maxAltitude);
	            route.setWidth(routeWidth);
	            route.setLocations(Arrays.asList(
	                LatLon.fromDegrees(airwaySegment.getStartLat(), airwaySegment.getStartLon()),
	                LatLon.fromDegrees(airwaySegment.getEndLat(), airwaySegment.getEndLon())));
	            route.setTerrainConforming(false, false);
	            setupDefaultMaterial(route, Color.GREEN);
	            airspaceLayer.addAirspace(route);
			}
			rtnResult = true;
		}
		catch ( Exception ex){
			System.out.println ( ex.getMessage());
		}
		return rtnResult;
	}
	public void addAirSpaceLayer(String title){
		airspaceLayer = new AirspaceLayer();
		airspaceLayer.setName(title);
		this.worldWindGLCanvas.getModel().getLayers().add(airspaceLayer);
		this.layerPanel.update(worldWindGLCanvas);
	}
	protected void setupDefaultMaterial(Airspace a, Color color){
         a.getAttributes().setDrawOutline(true);
         a.getAttributes().setMaterial(new Material(color));
         a.getAttributes().setOutlineMaterial(new Material(WWUtil.makeColorBrighter(color)));
         a.getAttributes().setOpacity(0.8);
         a.getAttributes().setOutlineOpacity(0.9);
         a.getAttributes().setOutlineWidth(3.0);
    }
	public static void main(String[] args){
		String strTitle = "航路";
		ShpAnalyze WWRun = new ShpAnalyze();  
		WWRun.setTitle( strTitle + ":" + WWRun.getClass().getName());
	}
}
