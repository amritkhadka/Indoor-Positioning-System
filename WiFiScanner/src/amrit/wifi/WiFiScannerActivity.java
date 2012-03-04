package amrit.wifi;


import android.app.Activity;
import android.content.BroadcastReceiver; // classes to recieve everything
import android.content.Context;//
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WiFiScannerActivity extends Activity implements OnClickListener {
	private static final String TAG = "WiFiDemo";
	WifiManager wifi; // to manage the wifi signals
	BroadcastReceiver receiver;// listens to the broadcasted wifi signals

	TextView textStatus;// view the text 
	Button buttonScan; // a variable for the button

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { // intializes the object 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); // gives content view an object that points to main.xml

		// Setup UI
		textStatus = (TextView) findViewById(R.id.textStatus); // points to 
		buttonScan = (Button) findViewById(R.id.buttonScan);
		buttonScan.setOnClickListener(this);

		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);		

		// Register Broadcast Receiver
		if (receiver == null)
			receiver = new WiFiScanReceiver(this);

		registerReceiver(receiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		Log.d(TAG, "onCreate()");

	}

	@Override
	public void onStop() {
		unregisterReceiver(receiver);
	}

	public void onClick(View view) {
		Toast.makeText(this, "Scanning the wifi APs",
				Toast.LENGTH_SHORT).show();

		if (view.getId() == R.id.buttonScan) {
			Log.d(TAG, "onClick() wifi.startScan()");
			wifi.startScan();
		}
	}
}