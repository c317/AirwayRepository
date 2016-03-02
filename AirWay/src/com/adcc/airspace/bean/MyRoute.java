package com.adcc.airspace.bean;

import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.airspaces.Route;

import java.util.ArrayList;

public class MyRoute extends Route {
	/**
	 * º½Â·¼ò³Æ
	 */
	private String routeName;
	/**
	 * º½Â·µãÐòºÅ
	 */
	private int[] routePointsID;
	
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public int[] getRoutePointsID() {
		return routePointsID;
	}
	public void setRoutePointsID(int[] routePointsID) {
		this.routePointsID = routePointsID;
	}
}
