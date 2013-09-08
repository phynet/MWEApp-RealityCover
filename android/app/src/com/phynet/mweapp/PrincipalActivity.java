package com.phynet.mweapp;

import java.io.IOException;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.System;
import android.widget.Button;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Intent;


import com.metaio.tools.io.AssetsManager;
import com.metaio.sdk.MetaioDebug;


@SuppressLint("SetJavaScriptEnabled")

public class PrincipalActivity extends SherlockActivity implements OnClickListener{
	
	private Button buttonStarted;
	private Button buttonInfo;
	
	AssetsExtracter mTask;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		buttonStarted = (Button) findViewById(R.id.buttonStart);
		buttonInfo = (Button) findViewById(R.id.buttonInformation);
		
		buttonStarted.setOnClickListener(this);
		buttonInfo.setOnClickListener(this);
		
		mTask = new AssetsExtracter();
		//mTask.execute(0);


	}
	private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean>
	{

		
		@Override
		protected Boolean doInBackground(Integer... params) 
		{
			try 
			{
				AssetsManager.extractAllAssets(getApplicationContext(), true);
		    	java.lang.System.out.print("EXTRACTING ASSESTS HEREEEEEE!!! ");

			} 
			catch (IOException e) 
			{
				MetaioDebug.printStackTrace(Log.ERROR, e);
				return false;
			}
			
			return true;
		}
		
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {        
            startActivity(new Intent(this, InfoActivty.class));
        } 
        return true;
    }

	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
        	case R.id.buttonStart:
        		buttonStarted.setBackgroundResource(R.drawable.button_state);
        		
        		Toast toast = Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
        		
        		startActivity(new Intent(this, AugmentedRealityActivity.class));
        	break;
        	
        	case R.id.buttonInformation:
        		buttonInfo.setBackgroundResource(R.drawable.button_state_info);
        		startActivity(new Intent(this, InfoActivty.class));
            break;
        
		
		}

	}
}

