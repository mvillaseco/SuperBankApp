package com.example.superbankapp;

import static android.provider.BaseColumns._ID;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DDBB extends SQLiteOpenHelper {

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
    	String columnas[] = {_ID,"valor","datos","banco"};
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
    	String columnas[] = {"valor",_ID,"datos"};
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
    	 String columnas[]= {_ID,"valor","datos","banco"};
    	// Cursor c = this.getReadableDatabase().query("bank", columnas,null,null,null,null,null);
    	 String sql = "SELECT valor,banco from bank WHERE bank.banco = "+bancoSelec+"";

    	 Cursor c = this.getReadableDatabase().rawQuery(sql,null);	
    	 if( c != null ){
    			if( c.moveToFirst() ){
    	   	     c.moveToFirst();
    	    	 int id,iu,ip,it;
    	    	 id = c.getColumnIndex(_ID);
    	    	 iu = c.getColumnIndex("valor");
    	    	 ip = c.getColumnIndex("datos");
    	    	 it = c.getColumnIndex("banco");
    	    	 
    	    	 c.moveToLast();
    	    		 result = "Actualmente tienes $"+c.getInt(iu)+" en el banco: "+c.getString(it);//+"en el banco: "+c.getString(it);  
        	    	 //+" Transacciòn Nùmero: "+c.getInt(id)
        	    	 return result; 	    	     	    	 
    		} else{
    			result = "No tiene ningun deposito en este banco";
    			
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
 	    	 int id,iu;
 	    	 id = cur.getColumnIndex(_ID);
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
	   
		
	
	//String sql = "INSERT INTO createdbank (nombre) VALUES ('BBVA')";
	//String sql2 = "INSERT INTO createdbank (nombre) VALUES ('Santander')";
	//String sql3 = "INSERT INTO createdbank (nombre) VALUES ('Galicia')";
	//db.execSQL(sql);
	//db.execSQL(sql2);
	//db.execSQL(sql3);
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
    
}