package asynctaskdata.xiaolian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.tv_data);
		
		tv.setText("load.....");
		
		new AsyncTask<Void, Void, String>(){

			@Override
			protected String doInBackground(Void... params) {
				try {
					InputStream in = new URL("http://jikexueyuan.com").openStream();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
					String line = null ;
					StringBuffer 	content = new StringBuffer();
					while ((line=reader.readLine())!=null) {
						content.append(line);
						
					}
					reader.close();
					return content.toString();
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null ;
			}
			
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				
				if (result != null) {
					tv.setText(result);
				}
			};
			
		}.execute();
	}
}
