package com.appsolution.bancoldex;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	ImageView imageSplash;
	
	Thread mSplashThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getActionBar().hide();

		imageSplash = (ImageView) findViewById(R.id.imageView1);
		imageSplash.setBackgroundResource(R.drawable.sprite);
		final AnimationDrawable frameAnimation = (AnimationDrawable) imageSplash.getBackground();
		imageSplash.post(new Runnable() {

			@Override
			public void run() {
				frameAnimation.start();

			}
		});

	}

	
}
