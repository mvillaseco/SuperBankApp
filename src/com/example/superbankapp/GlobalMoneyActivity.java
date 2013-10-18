package com.example.superbankapp;



import java.text.DecimalFormat;

import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class GlobalMoneyActivity extends Activity {

	JSONObject json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_global_money);
		DDBB handler = new DDBB(this);
		TextView text = (TextView) findViewById(R.id.globalmoneytxt);
		//new Read().execute("blue");
		
		int valores = handler.getGlobalMoney();
				text.setText(""+Integer.toString(valores));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.global_money, menu);
		return true;
	}
	
	public void onToggleChanged(View view)
	{
		DDBB handler = new DDBB(getApplicationContext());	
		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.onOffBlue);
		TextView texto2 = (TextView) findViewById(R.id.signoPesos);
		boolean on = toggleButton.isChecked();
		TextView mainText = (TextView) findViewById(R.id.globalmoneytxt);
		if(on)
		{	
			mainText.setText("Cargando...");
			new Read().execute("blue");
			texto2.setText("USD$ ");
		}else{
			mainText.setText(""+handler.getGlobalMoney());
			texto2.setText("$ ");
		}
		
	}
	public class Read extends AsyncTask<String, Integer, String>{
		
		

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		try {
			DDBB handler = new DDBB(null);
			json = handler.dolarValue();
			return json.getString(params[0]);
		} catch (Exception e) {
			// TODO: handle exception
			return "invalid";
		}
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			DDBB handler = new DDBB(getApplicationContext());			
			TextView texto = (TextView) findViewById(R.id.globalmoneytxt);
			if(result == "invalid"){
				texto.setText("Sin conexion");
			}else{
				double global = handler.getGlobalMoney();
				double dolar = Double.parseDouble(result);
				double resultado = global / dolar;
				texto.setText(""+new DecimalFormat("#.##").format(resultado));
			}
		}	
	}

}
