package com.example.superbankapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void OnClick (View view)
	{
		Bundle bundle = getIntent().getExtras();
		int valorBanco = bundle.getInt("Banco");
		
		DDBB handler = new DDBB(this);
		Object context = getSystemService(Context.VIBRATOR_SERVICE);
		handler.vibrar(context);
		
		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);		
		boolean on = toggleButton.isChecked();
		Intent intent = new Intent(this, Depositado.class);			
		intent.putExtra("Boton", (String) view.getTag());
		intent.putExtra("Toggle", (boolean) on);
		intent.putExtra("Banco", valorBanco);
		startActivity(intent);
	}
	
	public void ToggleChanged (View view)
	{
		DDBB handler = new DDBB(this);
		Object context = getSystemService(Context.VIBRATOR_SERVICE);
		handler.vibrar(context);
		
		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);		
		boolean on = toggleButton.isChecked();
		TextView mainText = (TextView) findViewById(R.id.textView1);
		if(on)
		{			
			mainText.setText("Cuanto vas a Retirar?");			
		}else{
			mainText.setText("Cuanto vas a Depositar?");
		}
		
	
	}
	}
