package com.example.superbankapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Depositado extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_depositado);		
		TextView result = (TextView) findViewById(R.id.depositado);
		Bundle bundle = getIntent().getExtras();
		int valorBanco = bundle.getInt("Banco");
		String valor = bundle.getString("Boton");
		boolean toggle = bundle.getBoolean("Toggle");
		Integer results = Integer.parseInt(valor);
		DDBB handler = new DDBB(this);
		if(toggle){
			handler.abrir();
			handler.ActualizarRetirar(results, "Retiro", valorBanco);
			
			result.setText(handler.leer(valorBanco));
			handler.cerrar();
			
		}else{
				
			handler.abrir();
			handler.Actualizar(results, "Deposito", valorBanco);
	
			result.setText(handler.leer(valorBanco));
			handler.cerrar();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.depositado, menu);
		return true;
	}

}
