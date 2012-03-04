package amrit.wifi;

import android.util.Log;

public class Location {
	String location_id;
	private AccessPoint[] accesspoints;
	private int index;

	/** Constructs a new location 
	 * @author Amrit Khadka
	 * @param	location_id
	 * 			id of the location
	 * @param	num_aps
	 * 			maximum number of access points that will be logged
	 * @since	2/27/2012*/
	public Location(String location_id, int num_aps){
		this.location_id = location_id;
		accesspoints = new AccessPoint[num_aps];
		index = 0;
	}

	/** Constructs a new AccessPoint(AP) or logs signals to existing AP
	 * @author Amrit Khadka
	 * @param	bssid
	 * 			BSSID of the AP
	 * @param	rssi
	 * 			RSSI of the signal that will be logged
	 * @param	time
	 * 			time of signal received
	 * @since	2/27/2012*/
	public void logAccessPoint(String bssid, int rssi, long time){
		int i = Search(bssid);
		if(i<0){
			accesspoints[index] = new AccessPoint(bssid, 101);
			i = index;
			index++;
		}
		if(i<index)
			accesspoints[i].logRSSI(rssi, time);
		else
			Log.d("Location", "more access point than allowed are received");
	}

	/** Search for an access point in the array of accesspoint
	 * @author Amrit Khadka
	 * @param	bssid
	 * 			BSSID of the AP
	 * @param	rssi
	 * 			RSSI of the signal that will be logged
	 * @param	time
	 * 			time of signal received
	 * @return	index of the accesspoint if it exists, or
	 * 			-1 if it doesnt exist
	 * @since	2/27/2012*/
	private int Search(String BSSID){
		for(int i=0; i<index; i++){
			if(accesspoints[i].BSSID.equals(BSSID)){
				return i;
			}
		}
		return -1;
	}
	
	/** Formats the BSSID's and their signal and timestamp into a csv format string
	 * @author Amrit Khadka, Andy Pape, Aabristi Khadka
	 * @return	Formatted string, including signal and timestamp for every
	 			BSSID
	 * @since	2/27/2012*/
	public String toString(){
		String str = new String();
		str = str+"\n"+location_id+":\n\n";
		for(int i=0; i<index; i++){
			str = str+accesspoints[i].BSSID.substring(12, 17);
			if(i<index-1) {str = str+", ";} //last string in the line should not have ,
		}
		str = str+"\n";
		int line_counter = 0;
		boolean exit = false;
		while (!exit){
			exit = true;
			for(int i=0; i<index; i++){//every access point
				if(line_counter<accesspoints[i].index){// every signal
					str = str+accesspoints[i].RSSIs[line_counter];
					if(i<index-1) {str = str+", ";} //last string in the line should not have ,
					exit = (exit && false);
				}
				else{
					Log.d("Location", "no more signals available");
					str = str+" NA";
					if(i<index-1) {str = str+", ";} //last string in the line should not have ,
					exit = (exit && true);
				}
			}
			str = str+"\n\n";
			
			/*for(int i=0; i<index; i++){//every access point
				if(line_counter<accesspoints[i].index){// every signal
					str = str+accesspoints[i].time_stamps[line_counter]+"   ";
				}
				else{
					Log.d("Location", "no more signals available");
					str = str+" NA"+"   ";
					exit = true;
				}
			}
			str = str+"\n\n";*/
			
			line_counter++;
		}
		
		return str;
	}
}
