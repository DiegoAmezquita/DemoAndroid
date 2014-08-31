package com.appsolution.bancoldex;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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

public class HomeActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	//
	// private TableRow rowFirstConfig_1;
	// private TableRow rowFirstConfig_2;
	// private TableRow rowSecondtConfig_1;

	LinearLayout linearLayoutTestFive;
	LinearLayout linearLayoutTestFour;

	TableLayout layoutFiveButtons;
	TableLayout layoutFourButtons;

	
	FrameLayout contentOne;
	LinearLayout contentTwo;
	
	
	ImageView imagetViewAnimate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// rowFirstConfig_1=(TableRow)findViewById(R.id.firstConfig_1);
		// rowFirstConfig_2=(TableRow)findViewById(R.id.firstConfig_2);
		//
		// rowSecondtConfig_1=(TableRow)findViewById(R.id.secondConfig_1);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

		// Set up the drawer.
		ActionBar actionBar = getSupportActionBar();
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

		linearLayoutTestFive = (LinearLayout) findViewById(R.id.buttonNeedCreditFour);
		linearLayoutTestFive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loadFourItems();

			}
		});

		linearLayoutTestFour = (LinearLayout) findViewById(R.id.buttonNeedCredit);
		linearLayoutTestFour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loadFiveItems();

			}
		});

		layoutFiveButtons = (TableLayout) findViewById(R.id.tableMenuDown);
		layoutFourButtons = (TableLayout) findViewById(R.id.tableMenuFourButtons);

		layoutFourButtons.animate().translationX(-800).setDuration(0);

		
		contentOne = (FrameLayout) findViewById(R.id.containerContentOne);
		contentTwo = (LinearLayout) findViewById(R.id.containerContentTwo);
		
		contentTwo.animate().translationX(-800).setDuration(0);
		
		
		
		imagetViewAnimate = (ImageView)findViewById(R.id.imageAnimation);
		
		
		imagetViewAnimate.animate().scaleX(1.3f).setDuration(0);
		imagetViewAnimate.animate().scaleY(1.3f).setDuration(0);
		
		imagetViewAnimate.animate().translationX(-50).setDuration(0);
		imagetViewAnimate.animate().translationY(-50).setDuration(0);

		animateImageContentOne();
		
	}

	public void animateImageContentOne(){
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime)*25;
		
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
		loadFiveItems();

	}
	
	public void loadFourItems() {
		final int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
		
		

		layoutFiveButtons.animate().translationX(800).setDuration(mShortAnimationDuration);
		layoutFourButtons.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentOne.animate().translationX(800).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(0).setDuration(mShortAnimationDuration);

		
		
	}

	public void loadFiveItems() {
		int mShortAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

		layoutFiveButtons.animate().translationX(0).setDuration(mShortAnimationDuration);

		layoutFourButtons.animate().translationX(-800).setDuration(mShortAnimationDuration);
		
		contentOne.animate().translationX(0).setDuration(mShortAnimationDuration);
		contentTwo.animate().translationX(-800).setDuration(mShortAnimationDuration);
		
		
		
		

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

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
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
			((HomeActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}

}
