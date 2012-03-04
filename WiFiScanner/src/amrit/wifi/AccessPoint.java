package amrit.wifi;


public class AccessPoint {
	String BSSID;
	int[] RSSIs;
	long[] time_stamps;
	int index;
	
	/** Constructs a new AccessPoint 
	 * @author Amrit Khadka
	 * @param	BSSID
	 * 			BSSID of the AccessPoint
	 * @param	num_signals
	 * 			number of signals that will be logged
	 * @since	2/27/2012*/
	public AccessPoint(String BSSID, int num_signals){
		this.BSSID = BSSID;
		RSSIs = new int[num_signals];
		time_stamps = new long[num_signals];
		index = 0;
	}
	
	/** Constructs a new AccessPoint 
	 * @author Amrit Khadka
	 * @param	RSSI
	 * 			strength of the signal that will be logged
	 * @param	time
	 * 			time of signal received
	 * @since	2/27/2012*/
	public void logRSSI(int RSSI, long time_stamp){
		RSSIs[index] = RSSI;
		time_stamps[index] = time_stamp;
		index++;
	}
}
