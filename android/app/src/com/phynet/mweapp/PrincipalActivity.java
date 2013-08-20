package com.phynet.mweapp;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Intent;



@SuppressLint("SetJavaScriptEnabled")

public class PrincipalActivity extends SherlockActivity implements OnClickListener{
	
	Button buttonStarted;
	Button buttonInfo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		buttonStarted = (Button) findViewById(R.id.buttonStart);
		buttonInfo = (Button) findViewById(R.id.buttonInformation);
		//buttonStarted.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFF3B62));
		buttonStarted.setOnClickListener(this);
		buttonInfo.setOnClickListener(this);
		getSupportActionBar().hide();
	
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

