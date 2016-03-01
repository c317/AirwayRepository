/**
 * 
 * @author 刘挺  2016-3-1 上午9:58:36
 * 航路段，一条航路包括多个航路段
 *
 */
public class AirwaySegment {
	/**
	 * 航路简称
	 */
	private String AirwayName;
	/**
	 * 在所属航路中的序号
	 */
	private int order;
	/**
	 * 起始航路点序号
	 */
	private int startPointID;
	/**
	 * 起始航路点经度
	 */
	private double startLon;
	/**
	 * 起始航路点纬度
	 */
	private double startLat;
	/**
	 * 终止航路点序号
	 */
	private int endPointID;
	/**
	 * 终止航路点经度
	 */
	private double endLon;
	/**
	 * 终止航路点纬度
	 */
	private double endLat;
	/**
	 * 航路宽度
	 */
	private int width;
	/**
	 * 航路最小高程
	 */
	private int minAltitude;
	/**
	 * 航路最大高程
	 */
	private int maxAltitude;
	public AirwaySegment(String airwayName, int order, int startPointID,
			String startLon, String startLat, int endPointID, String endLon,
			String endLat) {
		this.AirwayName = airwayName;
		this.order = order;
		this.startPointID = startPointID;
		this.startLon = getDoubleLon(startLon);
		this.startLat = getDoubleLat(startLat);
		this.endPointID = endPointID;
		this.endLon = getDoubleLon(endLon);
		this.endLat = getDoubleLat(endLat);
	}
	private double getDoubleLon(String lon){
		return Integer.valueOf(lon.substring(1, 4))+Double.valueOf(lon.substring(4, 6))/60+Double.valueOf(lon.substring(6, 8))/3600;
	}
	private double getDoubleLat(String lat){
		return Integer.valueOf(lat.substring(1, 3))+Double.valueOf(lat.substring(3, 5))/60+Double.valueOf(lat.substring(5, 7))/3600;
	}	
	public String getAirwayName() {
		return AirwayName;
	}
	public void setAirwayName(String airwayName) {
		AirwayName = airwayName;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getStartPointID() {
		return startPointID;
	}
	public void setStartPointID(int startPointID) {
		this.startPointID = startPointID;
	}	
	public double getStartLon() {
		return startLon;
	}
	public void setStartLon(double startLon) {
		this.startLon = startLon;
	}
	public double getStartLat() {
		return startLat;
	}
	public void setStartLat(double startLat) {
		this.startLat = startLat;
	}
	public int getEndPointID() {
		return endPointID;
	}
	public void setEndPointID(int endPointID) {
		this.endPointID = endPointID;
	}		
	public double getEndLon() {
		return endLon;
	}
	public void setEndLon(double endLon) {
		this.endLon = endLon;
	}
	public double getEndLat() {
		return endLat;
	}
	public void setEndLat(double endLat) {
		this.endLat = endLat;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getMinAltitude() {
		return minAltitude;
	}
	public void setMinAltitude(int minAltitude) {
		this.minAltitude = minAltitude;
	}
	public int getMaxAltitude() {
		return maxAltitude;
	}
	public void setMaxAltitude(int maxAltitude) {
		this.maxAltitude = maxAltitude;
	}	
}
