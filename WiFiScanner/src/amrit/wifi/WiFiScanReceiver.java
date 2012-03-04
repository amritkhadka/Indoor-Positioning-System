package amrit.wifi;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.util.Log;

public class WiFiScanReceiver extends BroadcastReceiver {
	private static final String TAG = "WiFiScanReceiver";
	WiFiScannerActivity wifiDemo;
	Location location;
	int num_received;

	public WiFiScanReceiver(WiFiScannerActivity wifiDemo) {
		super();
		this.wifiDemo = wifiDemo;
		location = new Location("dummy_loc",20);
		num_received = 0;
	}

	@Override
	public void onReceive(Context c, Intent intent) {
		long time = System.currentTimeMillis();
		List<ScanResult> results = wifiDemo.wifi.getScanResults();
		//wifiDemo.textStatus.append("\n\n___________________________");
		for (ScanResult result : results) {
			//wifiDemo.textStatus.append("\n\n" + result.BSSID +"\n"+ result.level);
			startLogging(result, time);
		}
		num_received++;
		String message = "Scan result received: "+num_received+"\n";
		wifiDemo.textStatus.append(message);

		Log.d(TAG, "onReceive() message: " + message);
		
		if(num_received%100 == 0){
			wifiDemo.textStatus.append(location.toString());
		}
		if(num_received<100){ //this number should match the array size in accesspoint
			wifiDemo.wifi.startScan();
			message = "Scanning the wifi APs: ";
			wifiDemo.textStatus.append(message);
		}
		
	}
	
	private void startLogging(ScanResult result, long time){
		location.logAccessPoint(result.BSSID, result.level, time);
	}

}