package com.example.android.projectbe;

import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener
{
	Marker marker;
	private GoogleMap mMap;
	private DatabaseReference mloc;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		mloc = FirebaseDatabase.getInstance().getReference().child("locations");
		mloc.push().setValue(marker);
	}

	@Override
	public void onMapReady(GoogleMap googleMap)
	{
		mMap = googleMap;
		googleMap.setOnMarkerClickListener(this);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mloc.addChildEventListener(new ChildEventListener()
		{
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s)
			{
				Location location = dataSnapshot.getValue(Location.class);
				LatLng location2 = new LatLng(Double.parseDouble(location.getLatitude()), Double.parseDouble(location.getLongitude()));
				Toast.makeText(MapsActivity.this, "" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_LONG).show();
				mMap.addMarker(new MarkerOptions().position(location2).title("pothole"));
				mMap.moveCamera(CameraUpdateFactory.newLatLng(location2));
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s)
			{

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot)
			{

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s)
			{

			}

			@Override
			public void onCancelled(DatabaseError databaseError)
			{

			}
		});
	}

	@Override
	public void onLocationChanged(android.location.Location location)
	{

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{

	}

	@Override
	public void onProviderEnabled(String provider)
	{

	}

	@Override
	public void onProviderDisabled(String provider)
	{

	}

	@Override
	public boolean onMarkerClick(Marker marker)
	{
		return false;
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture)
	{

	}
}

