package com.appsolution.bancoldex;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, ConnectionCallbacks, OnConnectionFailedListener,
		LocationListener, OnMyLocationButtonClickListener {

	private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragmentLogin mNavigationDrawerFragment;

	LinearLayout buttonNecesitoUnCredito;
	LinearLayout buttonQueOportunidadesHay;
	LinearLayout buttonConQuienHablo;
	LinearLayout buttonComoMePuedeAyudarBancoldex;
	LinearLayout buttonInteligenciaFinanciera;

	TableLayout layoutMainNotLogged;
	TableLayout layoutQueOportunidadesHay;
	TableLayout layoutNecesitoUnCredito;
	TableLayout layoutConQuienHablo;
	TableLayout layoutComoMePuedeAyudarBancoldex;
	TableLayout layoutInteligenciaFinanciera;

	FrameLayout contentMap;
	
	FrameLayout contentOne;
	LinearLayout contentTwo;

	ImageView imagetViewAnimate;

	private GoogleMap mMap;
	private LocationClient mLocationClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mNavigationDrawerFragment = (NavigationDrawerFragmentLogin) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

		
		// Set up the drawer.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setTitle("");
		actionBar.setIcon(null);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.topbarview, null);

		actionBar.setCustomView(v);
		// showBottomMenuFiveButtons();

		buttonNecesitoUnCredito = (LinearLayout) findViewById(R.id.buttonNecesitoUnCredito);
		buttonNecesitoUnCredito.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loadNecesitoUnCredito();
			}
		});

		buttonQueOportunidadesHay = (LinearLayout) findViewById(R.id.buttonQueOportunidadesHay);
		buttonQueOportunidadesHay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loadQueOportunidadesHay();
			}
		});

		buttonConQuienHablo = (LinearLayout) findViewById(R.id.buttonConQuienHablo);
		buttonConQuienHablo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loadConQuienHablo();
			}
		});

		buttonComoMePuedeAyudarBancoldex = (LinearLayout) findViewById(R.id.buttonComoMePuedeAyudarBancoldex);
		buttonComoMePuedeAyudarBancoldex.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loadComoMePuedeAyudarBancoldex();
			}
		});

		buttonInteligenciaFinanciera = (LinearLayout) findViewById(R.id.buttonInteligenciaFinanciera);
		buttonInteligenciaFinanciera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loadInteligenciaFinanciera();
			}
		});
		
		LinearLayout buttonShowMap = (LinearLayout) findViewById(R.id.buttonShowMap);
		buttonShowMap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				contentMap.setVisibility(FrameLayout.VISIBLE);
				setUpMapIfNeeded();
				contentTwo.setVisibility(TableLayout.GONE);
			}
		});
		
		
		
		contentMap = (FrameLayout)findViewById(R.id.containerMap);

		layoutMainNotLogged = (TableLayout) findViewById(R.id.tableMainNotLogged);
		layoutNecesitoUnCredito = (TableLayout) findViewById(R.id.tableNecesitoUnCredito);
		layoutQueOportunidadesHay = (TableLayout) findViewById(R.id.tableQueOportunidadesHay);
		layoutConQuienHablo = (TableLayout) findViewById(R.id.tableConQuienHablo);
		layoutComoMePuedeAyudarBancoldex = (TableLayout) findViewById(R.id.tableComoMePuedeAyudarBancoldex);
		layoutInteligenciaFinanciera = (TableLayout) findViewById(R.id.tableInteligenciaFinanciera);

		layoutNecesitoUnCredito.animate().translationX(-800).setDuration(0);
		layoutQueOportunidadesHay.animate().translationX(-800).setDuration(0);
		layoutConQuienHablo.animate().translationX(-800).setDuration(0);
		layoutComoMePuedeAyudarBancoldex.animate().translationX(-800).setDuration(0);
		layoutInteligenciaFinanciera.animate().translationX(-800).setDuration(0);

		contentOne = (FrameLayout) findViewById(R.id.containerContentOne);
		contentTwo = (LinearLayout) findViewById(R.id.containerContentTwo);

		contentTwo.animate().translationX(-800).setDuration(0);

		imagetViewAnimate = (ImageView) findViewById(R.id.imageAnimation);

		imagetViewAnimate.animate().scaleX(1.3f).setDuration(0);
		imagetViewAnimate.animate().scaleY(1.3f).setDuration(0);

		imagetViewAnimate.animate().translationX(-50).setDuration(0);
		imagetViewAnimate.animate().translationY(-50).setDuration(0);

		animateImageContentOne();

		setUpMapIfNeeded();

	}

	public void animateImageContentOne() {
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime) * 25;

		imagetViewAnimate.animate().translationX(50).translationY(50).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				imagetViewAnimate.animate().translationX(-50).translationY(50).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						imagetViewAnimate.animate().translationX(50).translationY(-50).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								imagetViewAnimate.animate().translationX(-50).translationY(-50).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
									@Override
									public void onAnimationEnd(Animator animation) {
										animateImageContentOne();
									}
								});
							}
						});
					}
				});
			}
		});
	}

	@Override
	public void onBackPressed() {
		loadMainNotLogged();

	}

	public void loadMainNotLogged() {
		
		
		
		int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

		layoutMainNotLogged.animate().translationX(0).setDuration(mShortAnimationDuration);
		layoutNecesitoUnCredito.animate().translationX(-800).setDuration(mShortAnimationDuration);
		layoutComoMePuedeAyudarBancoldex.animate().translationX(-800).setDuration(mShortAnimationDuration);
		layoutConQuienHablo.animate().translationX(-800).setDuration(mShortAnimationDuration);
		layoutInteligenciaFinanciera.animate().translationX(-800).setDuration(mShortAnimationDuration);
		layoutQueOportunidadesHay.animate().translationX(-800).setDuration(mShortAnimationDuration);
		contentMap.animate().translationX(-800).setDuration(mShortAnimationDuration);

		contentOne.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(-800).setDuration(mShortAnimationDuration);

	}

	public void loadNecesitoUnCredito() {
//		contentMap.setVisibility(FrameLayout.GONE);
		contentTwo.setVisibility(TableLayout.VISIBLE);
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

		layoutMainNotLogged.animate().translationX(800).setDuration(mShortAnimationDuration);
		layoutNecesitoUnCredito.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentOne.animate().translationX(800).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(0).setDuration(mShortAnimationDuration);

	}

	public void loadQueOportunidadesHay() {
//		contentMap.setVisibility(FrameLayout.GONE);
		contentTwo.setVisibility(TableLayout.VISIBLE);
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

		layoutMainNotLogged.animate().translationX(800).setDuration(mShortAnimationDuration);
		layoutQueOportunidadesHay.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentOne.animate().translationX(800).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(0).setDuration(mShortAnimationDuration);
		
		contentMap.animate().translationX(0).setDuration(mShortAnimationDuration);

	}

	public void loadConQuienHablo() {
//		contentMap.setVisibility(FrameLayout.GONE);
		contentTwo.setVisibility(TableLayout.VISIBLE);
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

		layoutMainNotLogged.animate().translationX(800).setDuration(mShortAnimationDuration);
		layoutConQuienHablo.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentOne.animate().translationX(800).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(0).setDuration(mShortAnimationDuration);

	}

	public void loadComoMePuedeAyudarBancoldex() {
//		contentMap.setVisibility(FrameLayout.GONE);
		contentTwo.setVisibility(TableLayout.VISIBLE);
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

		layoutMainNotLogged.animate().translationX(800).setDuration(mShortAnimationDuration);
		layoutComoMePuedeAyudarBancoldex.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentOne.animate().translationX(800).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(0).setDuration(mShortAnimationDuration);

	}

	public void loadInteligenciaFinanciera() {
//		contentMap.setVisibility(FrameLayout.GONE);
		contentTwo.setVisibility(TableLayout.VISIBLE);
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

		layoutMainNotLogged.animate().translationX(800).setDuration(mShortAnimationDuration);
		layoutInteligenciaFinanciera.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentOne.animate().translationX(800).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(0).setDuration(mShortAnimationDuration);

	}

	// public void showBottomMenuFiveButtons(){
	// rowFirstConfig_1.setVisibility(View.VISIBLE);
	// rowFirstConfig_2.setVisibility(View.VISIBLE);
	// rowSecondtConfig_1.setVisibility(View.GONE);
	// }
	//
	// public void showBottomMenuFourButtons(){
	// rowFirstConfig_1.setVisibility(View.GONE);
	// rowFirstConfig_2.setVisibility(View.GONE);
	// rowSecondtConfig_1.setVisibility(View.VISIBLE);
	// }

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.home, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
		setUpLocationClientIfNeeded();
        mLocationClient.connect();
	}

	private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
//            	setUpMap();
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
            }
        }
    }

	/**
	 * This is where we can add markers or lines, add listeners or move the
	 * camera. In this case, we just add a marker near Africa.
	 * <p>
	 * This should only be called once and when we are sure that {@link #mMap}
	 * is not null.
	 */
	private void setUpMap() {
		mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
	}
	
	private void setUpLocationClientIfNeeded() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(
                    getApplicationContext(),
                    this,  // ConnectionCallbacks
                    this); // OnConnectionFailedListener
        }
    }

    /**
     * Button to get current Location. This demonstrates how to get the current Location as required
     * without needing to register a LocationListener.
     */
    public void showMyLocation(View view) {
        if (mLocationClient != null && mLocationClient.isConnected()) {
            String msg = "Location = " + mLocationClient.getLastLocation();
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Implementation of {@link LocationListener}.
     */
    @Override
    public void onLocationChanged(Location location) {
       Log.v("BANCOLDEX","Location = " + location);
       LatLng latlong = new LatLng(location.getLatitude(),
               location.getLongitude());
       
       mMap.animateCamera(CameraUpdateFactory.newLatLng(latlong));
       mMap.animateCamera(CameraUpdateFactory.zoomBy(15));
    }

    /**
     * Callback called when connected to GCore. Implementation of {@link ConnectionCallbacks}.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        mLocationClient.requestLocationUpdates(
                REQUEST,
                this);  // LocationListener
    }

    /**
     * Callback called when disconnected from GCore. Implementation of {@link ConnectionCallbacks}.
     */
    @Override
    public void onDisconnected() {
        // Do nothing
    }

    /**
     * Implementation of {@link OnConnectionFailedListener}.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Do nothing
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

}
