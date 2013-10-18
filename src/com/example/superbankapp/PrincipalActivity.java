package com.example.superbankapp;

import java.io.IOException;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	public void selectBank(View view)
	{
		DDBB handler = new DDBB(this);
		Object context = getSystemService(Context.VIBRATOR_SERVICE);
		handler.vibrar(context);
		Intent intent = new Intent(this, BancosActivity.class);	
		
		startActivity(intent);
	}
	
	public void getGlobalMoney(View view)
	{
		DDBB handler = new DDBB(this);
		Object context = getSystemService(Context.VIBRATOR_SERVICE);
		handler.vibrar(context);
		Intent intent = new Intent(this, GlobalMoneyActivity.class);
		
		startActivity(intent);		
	}
	public void dolarBlue(View view) throws IOException, JSONException
	{
		DDBB handler = new DDBB(this);
		Object context = getSystemService(Context.VIBRATOR_SERVICE);
		handler.vibrar(context);
		Intent intent = new Intent(this, DolarBlueActivity.class);
		
		startActivity(intent);		
	}
}
