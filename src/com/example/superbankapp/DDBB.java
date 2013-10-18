package com.example.superbankapp;

import static android.provider.BaseColumns._ID;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Vibrator;

public class DDBB extends SQLiteOpenHelper {

	HttpClient client = new DefaultHttpClient();
	final static String URL = "http://ws.geeklab.com.ar/dolar/get-dolar-json.php";
    public DDBB(Context ctx) {
        super(ctx, "BankDB",null ,10);
    }	

    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL("create table createdbank(bank_ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL)");
    	db.execSQL("create table bank("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, valor INTEGER NOT NULL, datos TEXT, banco REFERENCES createdbank (bank_ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists bank");
        db.execSQL("drop table if exists createdbank");
        onCreate(db);
    }    
    
    public void insertar(Integer valor, String datos, String banco){
    	ContentValues values = new ContentValues();
    	values.put("valor", valor);
    	values.put("datos", datos);
    	values.put("banco", banco);
    	this.getWritableDatabase().insert("bank", null, values);
    	}
    
    public void Actualizar(Integer valor, String datos, int banco){
    	ContentValues values = new ContentValues();
    	int resultado = 0;
    	//Cursor c = this.getWritableDatabase().query("bank", columnas, null,null,null,null,null); 
    	String sql = "SELECT valor from bank WHERE bank.banco = "+banco+"";

    	Cursor c = this.getWritableDatabase().rawQuery(sql,null);	

	   	if( c != null ){
	   	    if( c.moveToFirst() ){
	   		   	c.moveToLast();
	   		   //int id = c.getInt(c.getColumnIndex("_ID"));
	   	       int num = c.getInt(c.getColumnIndex("valor"));
	   	       //String datosDB = c.getString(c.getColumnIndex("datos")); 
	   	       //int ban = c.getInt(c.getColumnIndex("banco"));    

	   	      
	   

    	resultado = num + valor;
    		
    	values.put("valor", resultado);
    	//values.put("datos", datosDB);
    	values.put("banco", banco);
    	this.getWritableDatabase().insert("bank", null, values);
		    }else{
			   	values.put("valor", valor);
		    	values.put("datos", datos);
		    	values.put("banco", banco);
		    	this.getWritableDatabase().insert("bank", null, values);		    	
		    }
		  }
    	}
    public void ActualizarRetirar(Integer valor, String datos, int banco){
    	ContentValues values = new ContentValues();
    	int resultado = 0;
    	//Cursor c = this.getWritableDatabase().query("bank", columnas, null,null,null,null,null); 	  	
    	String sql = "SELECT valor from bank WHERE bank.banco = "+banco+"";

    	Cursor c = this.getWritableDatabase().rawQuery(sql,null);
	   	if( c != null ){
	   	    if( c.moveToFirst() ){
	   		   	c.moveToLast();
	   	       int num = c.getInt(c.getColumnIndex("valor")); 
	   	       //int id = c.getInt(c.getColumnIndex(_ID));  
	   	       //int ban = c.getInt(c.getColumnIndex("banco"));
	   	       //String datosDB = c.getString(c.getColumnIndex("datos")); 
	   

    	resultado = num - valor;
    		
    	values.put("valor", resultado);
    	//values.put("datos", datos);
    	values.put("banco", banco);
    	this.getWritableDatabase().insert("bank", null, values);
		    }else{
			   	values.put("Valor", valor);
		    	values.put("datos", datos);
		    	values.put("banco", banco);
		    	this.getWritableDatabase().insert("bank", null, values);		    	
		    }
		  }
    
    }
    
    public String leer(int bancoSelec){
    	 String result = "";
    	// Cursor c = this.getReadableDatabase().query("bank", columnas,null,null,null,null,null);
    	 String sql = "SELECT valor,banco from bank WHERE bank.banco = "+bancoSelec+"";

    	 Cursor c = this.getReadableDatabase().rawQuery(sql,null);	
    	 if( c != null ){
    			if( c.moveToFirst() ){
    	   	     c.moveToFirst();
    	    	 int iu;
    	    	 iu = c.getColumnIndex("valor");
    	    	 
    	    	 c.moveToLast();
    	    		 result = "Actualmente tienes $"+c.getInt(iu)+" en este Banco";  
        	    	 //+" Transacciòn Nùmero: "+c.getInt(id)
        	    	 return result; 	    	     	    	 
    		} else{
    			result = "No tiene ningun deposito en este Banco";
    			
    		}  		
    		}
    		return result;
    	}
    public String getNombreBanco(int valorBanco)
    {
    	String textoBanco ="";
    	 String sql = "SELECT nombre from createdbank WHERE createdbank.bank_ID = "+valorBanco+" LIMIT 1";
    	 
    	 Cursor cur = this.getReadableDatabase().rawQuery(sql,null);
    	 if( cur != null ){
 			if( cur.moveToFirst() ){
 	   	     cur.moveToFirst();
 	    	 int iu;
 	    	 iu = cur.getColumnIndex("nombre");
 	    	 
 	    	 cur.moveToLast();
 	    	 textoBanco = cur.getString(iu);  
     	     return textoBanco; 	    	     	    	 
 			}    		
 		}
    	 grabarpordefecto();
    	return textoBanco;
    }
    public void abrir()
    {
    	this.getWritableDatabase();
    }
    public void cerrar()
    {
    	this.close();
    }
    public void grabarpordefecto()
    {   
    
   	 	String sql = "SELECT nombre from createdbank";
   	 
   	 	Cursor cur = this.getReadableDatabase().rawQuery(sql,null);
    	
   	 if( cur != null ){
			if( cur.moveToFirst() ){
				
			}else{
				ContentValues values = new ContentValues();
				values.put("nombre", "BBVA");    	
				this.getWritableDatabase().insert("createdbank", null, values);
				
				ContentValues values1 = new ContentValues();
				values1.put("nombre", "Santander");
				this.getWritableDatabase().insert("createdbank", null, values1);
				
				ContentValues values2 = new ContentValues();
				values2.put("nombre", "Galicia");
				this.getWritableDatabase().insert("createdbank", null, values2);
			}
			}
    }
    public int getMoneyByBankId(int bankId)
    {
    	 int valor = 0;
	   	
	   	 String sql = "SELECT valor from bank WHERE bank.banco = "+bankId+"";
	
	   	 Cursor c = this.getReadableDatabase().rawQuery(sql,null);	
	   	 if( c != null ){
	   			if( c.moveToFirst() ){
	   	   	     c.moveToFirst();
	   	    	 int iu;
	   	    	 iu = c.getColumnIndex("valor");
	   	    	
	   	    	 
	   	    	 c.moveToLast();
	   	    	 valor = c.getInt(iu);
	       	    	
	   			}
	   	 }
	    	return valor;
    }
    public int getGlobalMoney()
    {
    	int valor = getMoneyByBankId(1) + getMoneyByBankId(2) + getMoneyByBankId(3);
    	return valor;
    }
    public void vibrar(Object context)
    {    	
    	Vibrator v = (Vibrator) context;
    	v.vibrate(20);
    }
	public JSONObject dolarValue() throws ClientProtocolException, IOException, JSONException
	{


		
		StringBuilder url = new StringBuilder(URL);
		
		HttpGet get = new HttpGet(url.toString());
		HttpResponse resp = client.execute(get);
		int status = resp.getStatusLine().getStatusCode();
		if(status == 200){
			HttpEntity e = resp.getEntity();
			String data = EntityUtils.toString(e);
			JSONObject timeline = new JSONObject(data);
			
			
			
			//JSONObject last = timeline.getJSONObject("rhs");

			
			return timeline;
		}else{
			
			return null;
		}
	}
	
}