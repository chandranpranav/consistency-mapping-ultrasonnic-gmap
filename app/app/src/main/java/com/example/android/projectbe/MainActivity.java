package com.example.android.projectbe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
	private DatabaseReference mdatabase;
	private GoogleMap map = null;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mdatabase = FirebaseDatabase.getInstance().getReference().child("locations");

		SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		fragment.getMapAsync(this);

	}

	@Override
	public void onMapReady(final GoogleMap googleMap)
	{
		map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

		mdatabase.addChildEventListener(new ChildEventListener() {
	        @Override
	        public void onChildAdded(DataSnapshot dataSnapshot, String s)
	        {
		        Location location = dataSnapshot.getValue(Location.class);
		        LatLng location2 = new LatLng(Double.parseDouble(location.getLatitude()), Double.parseDouble(location.getLongitude()));
		        Toast.makeText(MainActivity.this, "" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_LONG).show();
		        map.addMarker(new MarkerOptions().position(location2).title("pothole"));
		        map.moveCamera(CameraUpdateFactory.newLatLng(location2));
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
}

