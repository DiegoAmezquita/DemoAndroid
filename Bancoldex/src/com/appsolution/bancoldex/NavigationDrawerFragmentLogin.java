package com.appsolution.bancoldex;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 */
public class NavigationDrawerFragmentLogin extends Fragment {

	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private View mFragmentContainerView;

	private Button buttonLogin;
	private EditText editTextUser;
	private EditText editTextPassword;

	String userEmpresario = "empresario";
	String passwordEmpresario = "123";

	String userIntermediario = "intermediario";
	String passwordIntermediario = "123";

	String userEjecutivoCuenta = "ecuenta";
	String passwordEjecutivoCuenta = "123";

	public NavigationDrawerFragmentLogin() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicate that this fragment would like to influence the set of
		// actions in the action bar.
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout mainLayout = (LinearLayout) inflater.inflate(R.layout.fragment_navigation_drawer_login, container, false);

		buttonLogin = (Button) mainLayout.findViewById(R.id.buttonLogin);
		editTextUser = (EditText) mainLayout.findViewById(R.id.editTextUser);
		editTextPassword = (EditText) mainLayout.findViewById(R.id.editTextPassword);

		buttonLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.v("BANCOLDEX", "CLIC EN EL BOTON");
				tryLogin();
			}
		});

		return mainLayout;

	}

	public void tryLogin() {
		editTextUser.setError(null);
		editTextPassword.setError(null);

		String textUserEditText = editTextUser.getText().toString();
		String textPasswordEditText = editTextPassword.getText().toString();

		boolean dataValid = true;

		if (textUserEditText.isEmpty()) {
			editTextUser.setError("El campo no puede estar vacio");
			dataValid = false;
		} else if (textPasswordEditText.isEmpty()) {
			editTextPassword.setError("El campo no puede estar vacio");
			dataValid = false;
		}

		if (dataValid) {
			if (textUserEditText.equals(userEmpresario) && textPasswordEditText.equals(passwordEmpresario)) {
				Toast.makeText(getActivity(), "Empresario", Toast.LENGTH_SHORT).show();
			} else if (textUserEditText.equals(userIntermediario) && textPasswordEditText.equals(passwordIntermediario)) {
				Toast.makeText(getActivity(), "Intermediario", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(), LoggedIntermediarioActivity.class);
				startActivity(intent);
			} else if (textUserEditText.equals(userEjecutivoCuenta) && textPasswordEditText.equals(passwordEjecutivoCuenta)) {
				Toast.makeText(getActivity(), "Ejecutivo Cuenta", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getActivity(), LoggedEjecutivoCuentaActivity.class);
				startActivity(intent);
			}
		}
		
		
		InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    

	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	/**
	 * Users of this fragment must call this method to set up the navigation
	 * drawer interactions.
	 * 
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the navigation drawer and the action bar app icon.
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, /*
										 * "open drawer" description for
										 * accessibility
										 */
		R.string.navigation_drawer_close /*
										 * "close drawer" description for
										 * accessibility
										 */
		) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}

				getActivity().supportInvalidateOptionsMenu(); // calls

			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}

				getActivity().supportInvalidateOptionsMenu(); // calls
																// onPrepareOptionsMenu()
			}
		};

		// If the user hasn't 'learned' about the drawer, open it to introduce
		// them to the drawer,
		// per the navigation drawer design guidelines.

		// Defer code dependent on restoration of previous instance state.
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// If the drawer is open, show the global app actions in the action bar.
		// See also
		// showGlobalContextActionBar, which controls the top-left area of the
		// action bar.
		if (mDrawerLayout != null && isDrawerOpen()) {
			inflater.inflate(R.menu.global, menu);
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Callbacks interface that all activities using this fragment must
	 * implement.
	 */
	public static interface NavigationDrawerCallbacks {
		/**
		 * Called when an item in the navigation drawer is selected.
		 */
		void onNavigationDrawerItemSelected(int position);
	}

}
