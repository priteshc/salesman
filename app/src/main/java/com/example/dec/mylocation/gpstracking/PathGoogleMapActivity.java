package com.example.dec.mylocation.gpstracking;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dec.mylocation.BackTask.RetrofitBuild;
import com.example.dec.mylocation.R;
import com.example.dec.mylocation.custom.SweetAlertDialog;
import com.example.dec.mylocation.pojo.AllData;
import com.example.dec.mylocation.pojo.MapPojo;
import com.example.dec.mylocation.pojo.MyMap;
import com.example.dec.mylocation.rximpl.ALlMapDetailInterface;
import com.example.dec.mylocation.rximpl.AllMapDetailsPresenter;
import com.example.dec.mylocation.rximpl.AllVisitDetailsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;

public class PathGoogleMapActivity extends FragmentActivity implements OnMapReadyCallback,ALlMapDetailInterface {

	private static final LatLng DADAR = new LatLng(19.021324,
			72.842418);
	private static final LatLng ANDHERI = new LatLng(19.113645, 72.869734);
	private static final LatLng VIRAR = new LatLng(19.456360, 72.792461);

	GoogleMap mMap;
	final String TAG = "PathGoogleMapActivity";
	private SweetAlertDialog progressDialog;

	private RetrofitBuild retrofitBuild;
	private AllMapDetailsPresenter detailsPresenter;

	List<MyMap> mapList = new ArrayList<>();

    LatLng firstpos = new LatLng(0,0);
	LatLng lastpos = new LatLng(0,0);
	LatLng multiple = new LatLng(0,0);

	String user;
	private String cdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_google_map);
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		 fm.getMapAsync(this);


		if(getIntent().getExtras()!= null){

			user= getIntent().getExtras().getString("Name");

			cdate= getIntent().getExtras().getString("Date");


		}


		progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
		progressDialog.getProgressHelper().setBarColor(Color.parseColor("#2289DC"));
		progressDialog.setTitleText("Please  wait...");
		progressDialog.setCancelable(false);



		retrofitBuild = new RetrofitBuild();

		detailsPresenter = new AllMapDetailsPresenter(this);




	}

	private String getMapsApiDirectionsUrl() {
		String waypoints = "waypoints=optimize:true|" + ANDHERI.latitude + ","
				+ ANDHERI.longitude ;

		/*String origin = "Chicago";
		String destination= "Los";*/

        String origin ="origin=" + DADAR.latitude + "," + DADAR.longitude;

        String destination = "destination=" + VIRAR.latitude + "," + VIRAR.longitude;

		String sensor = "sensor=false";
		String params =  origin + "&" + destination + "&" + waypoints + "&" + sensor;
		String output = "json";
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + params;
		return url;
	}

	private void addMarkers() {
		if (mMap != null) {
			mMap.addMarker(new MarkerOptions().position(DADAR)
					.title("First Point"));
			mMap.addMarker(new MarkerOptions().position(ANDHERI)
					.title("Second Point"));
			mMap.addMarker(new MarkerOptions().position(VIRAR)
					.title("Third Point"));
		}
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {


		mMap = googleMap;

	/*	MarkerOptions options = new MarkerOptions();
		options.position(DADAR);
		options.position(ANDHERI);
		options.position(VIRAR);
		googleMap.addMarker(options);
		String url = getMapsApiDirectionsUrl();
		ReadTask downloadTask = new ReadTask();
		downloadTask.execute(url);

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ANDHERI,
				10));
		addMarkers();*/

	detailsPresenter.getData(user,cdate);

		progressDialog.show();

	}

	@Override
	public void onMapComplete() {


		if(mapList != null) {


			StringBuffer sb = new StringBuffer("waypoints=optimize:true|");


			firstpos = new LatLng(Double.valueOf(mapList.get(0).getLatitude()), Double.valueOf(mapList.get(0).getLongitude()));

			lastpos = new LatLng(Double.valueOf(mapList.get(mapList.size()-1).getLatitude()), Double.valueOf(mapList.get(mapList.size()-1).getLongitude()));


			for (int i = 1; i < mapList.size() - 1; i++) {

				if (i == mapList.size() - 2) {

					sb.append(mapList.get(i).getLatitude());
					sb.append(",");
					sb.append(mapList.get(i).getLongitude());
				} else {

					sb.append(mapList.get(i).getLatitude());
					sb.append(",");
					sb.append(mapList.get(i).getLongitude());
					sb.append("|");
				}

				multiple = new LatLng(Double.valueOf(mapList.get(i).getLatitude()), Double.valueOf(mapList.get(i).getLongitude()));

				mMap.addMarker(new MarkerOptions().position(multiple)
						.title(mapList.get(i).getvTime()));
			}


			mMap.addMarker(new MarkerOptions().position(firstpos)
					.title(mapList.get(0).getvTime()));
			mMap.addMarker(new MarkerOptions().position(lastpos)
					.title(mapList.get(mapList.size()-1).getvTime()));

			String waypoints = sb.toString();

			System.out.println("Pat:" + waypoints);

			DIrectionurl(firstpos, lastpos, waypoints);


		}
		else {

			Toast.makeText(PathGoogleMapActivity.this,"No data found",Toast.LENGTH_SHORT).show();

		}

		progressDialog.dismiss();
	}

	@Override
	public void onMapError(String s) {

		progressDialog.dismiss();

		Toast.makeText(PathGoogleMapActivity.this,"server error",Toast.LENGTH_SHORT).show();


	}

	@Override
	public void onMap(MapPojo mapPojo) {

		mapList = mapPojo.getData();


	}

	@Override
	public Observable<MapPojo> getAllMapData(String user,String cdate) {
		return retrofitBuild.allApi().getMapdata(user,cdate);
	}



	private void DIrectionurl(LatLng firstpos , LatLng lastpos,String way){


		String waypoints = "waypoints=optimize:true|" + ANDHERI.latitude + ","
				+ ANDHERI.longitude ;

		/*String origin = "Chicago";
		String destination= "Los";*/

		String origin ="origin=" + firstpos.latitude + "," + firstpos.longitude;

		String destination = "destination=" + lastpos.latitude + "," + lastpos.longitude;

		String sensor = "sensor=false";
		String params =  origin + "&" + destination + "&" + way + "&" + sensor;
		String output = "json";
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + params;

		ReadTask downloadTask = new ReadTask();
		downloadTask.execute(url);

		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastpos,
				10));



	}


	private class ReadTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... url) {
			String data = "";
			try {
				HttpConnection http = new HttpConnection();
				data = http.readUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
            new ParserTask().execute(result);

			System.out.println("Result:" + result);
		}
	}

	private class ParserTask extends
			AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

		@Override
		protected List<List<HashMap<String, String>>> doInBackground(
				String... jsonData) {

			JSONObject jObject;
			List<List<HashMap<String, String>>> routes = null;

			try {
				jObject = new JSONObject(jsonData[0]);
				PathJSONParser parser = new PathJSONParser();
				routes = parser.parse(jObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return routes;
		}

		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
			ArrayList<LatLng> points = null;
			PolylineOptions polyLineOptions = null;

			// traversing through routes
			for (int i = 0; i < routes.size(); i++) {
				points = new ArrayList<LatLng>();
				polyLineOptions = new PolylineOptions();
				List<HashMap<String, String>> path = routes.get(i);

				for (int j = 0; j < path.size(); j++) {
					HashMap<String, String> point = path.get(j);

					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);

					points.add(position);
				}

				polyLineOptions.addAll(points);
				polyLineOptions.width(4);
				polyLineOptions.color(Color.BLUE);
			}

			mMap.addPolyline(polyLineOptions);
		}
	}
}
