package com.adcc.airspace.app;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.adcc.airspace.bean.MyRoute;
import com.adcc.airspace.bean.RouteSegmentDAO;
import com.adcc.airspace.util.SortListUtil;

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
public class ShpAnalyze{	
	private static String shpFilePath = "http://127.0.0.1/Data/Airway_Segment_Project.shp";
	private Shapefile shpFile;
	private ShapefileRecord shpFileRecord;
	private ArrayList<RouteSegmentDAO> routeSegments;
	private static double routeWidth=2000d;
	private static double minAltitude = 6000d;
	private static double maxAltitude = 8400d;
	
	
	public ShpAnalyze(){
		routeSegments = AddRouteData(shpFilePath,"航路" );
	}
	
	/**
	 * 创建航路,航路段数据使用了二次排序
	 */
	public ArrayList<MyRoute> getRoutes(){
		MyRoute route = null;
		ArrayList<MyRoute> routes = new ArrayList<MyRoute>();
		List<LatLon> locations = null;
		String routeName="";
		if(routeSegments.size()>0){
			route = new MyRoute();
			route.setAltitudes(minAltitude, maxAltitude);
			route.setWidth(routeWidth);
			routeName = routeSegments.get(0).getRouteName();
			route.setValue(AVKey.DISPLAY_NAME, routeName);
			route.setValue(AVKey.NAME, routeName);
			route.setTerrainConforming(false, false);
			locations = new ArrayList<LatLon>();
			locations.add(LatLon.fromDegrees(routeSegments.get(0).getStartLat(), routeSegments.get(0).getStartLon()));
			locations.add(LatLon.fromDegrees(routeSegments.get(0).getEndLat(), routeSegments.get(0).getEndLon()));
		}
		for(int i=1;i<routeSegments.size();i++){
			if(routeName.equals(routeSegments.get(i).getRouteName())){
				locations.add(LatLon.fromDegrees(routeSegments.get(i).getEndLat(), routeSegments.get(i).getEndLon()));
			}else{
				//先构建出上一条航路
				LatLon[] lts = locations.toArray(new LatLon[locations.size()]);
				route.setLocations(Arrays.asList(lts));
				routes.add(route);
				//再进行下一条航路的构建
				route = new MyRoute();
				route.setAltitudes(minAltitude, maxAltitude);
				route.setWidth(routeWidth);
				route.setValue(AVKey.DISPLAY_NAME, routeSegments.get(i).getRouteName());
				route.setValue(AVKey.NAME, routeSegments.get(i).getRouteName());
				route.setTerrainConforming(false, false);
				locations = new ArrayList<LatLon>();
				locations.add(LatLon.fromDegrees(routeSegments.get(i).getStartLat(), routeSegments.get(i).getStartLon()));
				locations.add(LatLon.fromDegrees(routeSegments.get(i).getEndLat(), routeSegments.get(i).getEndLon()));
			}
			routeName = routeSegments.get(i).getRouteName();
		}
		return routes;
	}
	
	public ArrayList<RouteSegmentDAO> AddRouteData( String shpFilePath , String LayerTitle){
		RouteSegmentDAO routeSegment;
		ArrayList<RouteSegmentDAO> routeSegments = null;
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
			routeSegments = new ArrayList<RouteSegmentDAO>();
			while(shpFile.hasNext()){
				shpFileRecord = shpFile.nextRecord();
				airwayName = (String) shpFileRecord.getAttributes().getValue("AirwayName");
				order = ((Long) shpFileRecord.getAttributes().getValue("segOrder")).intValue();
				startPointID = ((Long) shpFileRecord.getAttributes().getValue("startPoint")).intValue();
				startLon = (String) shpFileRecord.getAttributes().getValue("startLon");
				startLat = (String) shpFileRecord.getAttributes().getValue("startLat");
				endPointID = ((Long) shpFileRecord.getAttributes().getValue("endPoint")).intValue();
				endLon = (String) shpFileRecord.getAttributes().getValue("endLon");
				endLat = (String) shpFileRecord.getAttributes().getValue("endLat");
				routeSegment = new RouteSegmentDAO(airwayName, order, startPointID, startLon, startLat, endPointID, endLon, endLat);
				routeSegments.add(routeSegment);
			}
			SortListUtil.sort(routeSegments, new String[] { "routeName", "order" }, new String[] {SortListUtil.ASC,SortListUtil.ASC});
		}
		catch ( Exception ex){
			System.out.println ( ex.getMessage());
		}
		return routeSegments;
	}
	
}
