package com.example.superbankapp;


import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DolarBlueActivity extends Activity {

	JSONObject json;
	JSONObject jsonOficial;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		
		
		
		setContentView(R.layout.activity_dolar_blue);
		
		//dolarBlue.loadUrl("http://www.eldolarblue.net/mobile2/");
		
		new Read().execute("blue","libre");
		

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dolar_blue, menu);
		return true;
	}
	

		
		

	public class Read extends AsyncTask<String, Integer, String[]>{
		
	

		@Override
		protected String[] doInBackground(String... params) {
			// TODO Auto-generated method stub
			String[] parametros = new String[3];
				try {
					DDBB handler = new DDBB(null);
					json = handler.dolarValue();
					parametros[0] = json.getString(params[0]);
					jsonOficial = handler.dolarValue();
					parametros[1] = jsonOficial.getString(params[1]);
					parametros[2]="OK";
					return parametros;
					 
				} catch (Exception e) {
					// TODO: handle exception
					parametros[2] = "error";
					return parametros;
				} 
			
		
		}
		@Override
		protected void onPostExecute(String[] result) {
			TextView texto = (TextView) findViewById(R.id.valor);			
			TextView texto2 = (TextView) findViewById(R.id.valor2);
			if(result[2] == "OK")
			{
				texto.setText(result[0]);
				texto2.setText(result[1]);				
			}else{
				Context context = getApplicationContext();
				CharSequence text = "Sin conexión!";
				int duration = Toast.LENGTH_SHORT;

				Toast.makeText(context, text, duration).show();
			}
		}
			
	}

}



