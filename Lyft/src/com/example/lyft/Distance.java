package com.example.lyft;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Distance extends Activity implements LocationListener{

	Location pointA = new Location("pointA");
	Location pointB = new Location("pointB");
	Location pointC = new Location("pointC");
	Location pointD = new Location("pointD");

	private TextView aLat;
	private TextView aLon;
	private TextView bLat;
	private TextView bLon;
	private TextView cLat;
	private TextView cLon;
	private TextView dLat;
	private TextView dLon;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distance);
		
		aLat = (TextView) findViewById(R.id.editText1);
		aLon = (TextView) findViewById(R.id.editText2);
		bLat = (TextView) findViewById(R.id.editText3);
		bLon = (TextView) findViewById(R.id.editText4);
		cLat = (TextView) findViewById(R.id.editText5);
		cLon = (TextView) findViewById(R.id.editText6);
		dLat = (TextView) findViewById(R.id.editText7);
		dLon = (TextView) findViewById(R.id.editText8);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_distance, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	public double calculateDistance(Location start, Location end)
	{
		return start.distanceTo(end);
	}

	public double calculateShorterOne(Location a, Location b, Location c, Location d)
	{
		return calculateDistance(a,c) + calculateDistance(c,d) + calculateDistance(d,b);
		//double rideB = calculateDistance(c,a) + calculateDistance(a,b) + calculateDistance(b,d);
	}

	public double calculateShorterTwo(Location a, Location b, Location c, Location d)
	{
		//double rideA = calculateDistance(a,c) + calculateDistance(c,d) + calculateDistance(d,b);
		return calculateDistance(c,a) + calculateDistance(a,b) + calculateDistance(b,d);
	}


	public void distanceButton(View view)
	{
		if(aLat.getText().length() == 0 ||
				aLon.getText().length() == 0 ||
				bLat.getText().length() == 0 ||
				bLon.getText().length() == 0 ||
				cLat.getText().length() == 0 ||
				cLon.getText().length() == 0 ||
				dLat.getText().length() == 0 ||
				dLon.getText().length() == 0 )
		{
			Toast.makeText(this, "Please enter all values", Toast.LENGTH_LONG).show();
		}
		else
		{
			pointA.setLatitude((double) (Integer.parseInt(aLat.getText().toString())));
			pointA.setLongitude((double) (Integer.parseInt(aLon.getText().toString())));		
			pointB.setLatitude((double) (Integer.parseInt(bLat.getText().toString())));
			pointB.setLongitude((double) (Integer.parseInt(bLon.getText().toString())));
			pointC.setLatitude((double) (Integer.parseInt(cLat.getText().toString())));
			pointC.setLongitude((double) (Integer.parseInt(cLon.getText().toString())));
			pointD.setLatitude((double) (Integer.parseInt(dLat.getText().toString())));
			pointD.setLongitude((double) (Integer.parseInt(dLon.getText().toString())));

			double returnValueOne = calculateShorterOne(pointA, pointB, pointC, pointD);
			double returnValueTwo = calculateShorterTwo(pointA, pointB, pointC, pointD);

			if(returnValueOne < returnValueTwo)
			{
				Toast.makeText(this, "Driver One (A->C->D->B): " + returnValueOne +
						"m\nDriver Two (C->A->B->D): " + returnValueTwo + 
						"m\nShorter Route: A->C->D->B ", Toast.LENGTH_LONG).show();
			}
			else
				if(returnValueOne > returnValueTwo)
				{
					Toast.makeText(this, "Driver One (A->C->D->B): " + returnValueOne +
							"m\nDriver Two (C->A->B->D): " + returnValueTwo + 
							"m\nShorter Route: C->A->B->D", Toast.LENGTH_LONG).show();
				}
				else
					if(returnValueOne == returnValueTwo)
					{
						Toast.makeText(this, "Driver One (A->C->D->B): " + returnValueOne +
								"m\nDriver Two (C->A->B->D): " + returnValueTwo + 
								"m\nBoth routes are equal", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(this, "Error. Could not calculate values", Toast.LENGTH_LONG).show();
					}
		}

	}

}
