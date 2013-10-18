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
		TextView result1 = (TextView) findViewById(R.id.depositado1);
		TextView ingreso = (TextView) findViewById(R.id.ingreso);
		TextView ingresotext = (TextView) findViewById(R.id.ingresotext);
		TextView previo = (TextView) findViewById(R.id.previo);
		TextView previotext = (TextView) findViewById(R.id.previotext);
		TextView total = (TextView) findViewById(R.id.total);
		TextView totaltext = (TextView) findViewById(R.id.totaltext);
		Bundle bundle = getIntent().getExtras();
		int valorBanco = bundle.getInt("Banco");
		String valor = bundle.getString("Boton");
		boolean toggle = bundle.getBoolean("Toggle");
		Integer results = Integer.parseInt(valor);
		DDBB handler = new DDBB(this);
		if(toggle){
			handler.abrir();
			previotext.setText("Total Previo");
			previo.setText(""+handler.getMoneyByBankId(valorBanco));
			handler.ActualizarRetirar(results, "Retiro", valorBanco);
			ingresotext.setText("Importe");
			ingreso.setText("-"+results);
			result.setText(""+handler.getNombreBanco(valorBanco));
			result1.setText("Extracción Finalizada!");			
			total.setText(""+handler.getMoneyByBankId(valorBanco));
			totaltext.setText("Total Actual");
			handler.cerrar();
			
		}else{
				
			handler.abrir();			
			previo.setText(""+handler.getMoneyByBankId(valorBanco));
			previotext.setText("Total Previo");		
			ingresotext.setText("Importe");
			ingreso.setText(""+results);
			
			handler.Actualizar(results, "Deposito", valorBanco);
			total.setText(""+handler.getMoneyByBankId(valorBanco));
			totaltext.setText("Total Actual");
			result.setText(""+handler.getNombreBanco(valorBanco));
			result1.setText("Depósito Correcto!");
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
