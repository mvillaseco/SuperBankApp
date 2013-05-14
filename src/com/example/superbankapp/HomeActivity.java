package com.example.superbankapp;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		DDBB handler = new DDBB(this);
		
		TextView monto = (TextView) findViewById(R.id.Monto);
		TextView banco = (TextView) findViewById(R.id.BancoSeleccionado);
		Bundle bundle = getIntent().getExtras();
		int valorBanco = bundle.getInt("Boton");
			handler.abrir();		
			monto.setText(handler.leer(valorBanco));
			handler.cerrar();
			
		TextView Banco = (TextView) findViewById(R.id.BancoSeleccionado);
		
		
		DDBB db = new DDBB(this);
		db.abrir();
		String mauro = db.getNombreBanco(valorBanco);
		Banco.setText(db.getNombreBanco(valorBanco));
		
	}
	
	protected void onResume()
	{
	   super.onResume();


		DDBB handler = new DDBB(this);
		TextView monto = (TextView) findViewById(R.id.Monto);
		Bundle bundle = getIntent().getExtras();
		int valorBanco = bundle.getInt("Boton");
			handler.abrir();		
			monto.setText(handler.leer(valorBanco));
			handler.cerrar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void onClick(View view) 
	{
		Intent intent = new Intent(this, MainActivity.class);	
		TextView banco = (TextView) findViewById(R.id.BancoSeleccionado);
		String nombreBanco = (String) banco.getText();
		int valBank = 0;
		if(nombreBanco.equals("BBVA"))
		{
			valBank = 1;
		}
		if(nombreBanco.equals("Santander"))
		{
			valBank = 2;		
		}
		if(nombreBanco.equals("Galicia"))
		{
			valBank = 3;			
		}
		intent.putExtra("Banco", valBank);
		startActivity(intent);
	}

}
