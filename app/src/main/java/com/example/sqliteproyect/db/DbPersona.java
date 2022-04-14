package com.example.sqliteproyect.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sqliteproyect.entidades.Persona;

import java.util.ArrayList;

public class DbPersona extends DbHelper{

    Context context;

    public DbPersona(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long insertarPersona(String nombre, String apellido, String telefono, String email){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("telefono", telefono);
            values.put("email", email);
             id = db.insert(TABLE_PERSONAS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Persona> mostrarPersona(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Persona> listaPersona = new ArrayList<>();
        Persona persona = null;
        Cursor cursorPersona = null;

        cursorPersona = db.rawQuery("SELECT * FROM " + TABLE_PERSONAS, null);
        if(cursorPersona.moveToFirst()){
            do {
                persona = new Persona();
                persona.setId(cursorPersona.getInt(0));
                persona.setNombre(cursorPersona.getString(1));
                persona.setApellido(cursorPersona.getString(2));
                persona.setTelefono(cursorPersona.getString(3));
                persona.setEmail(cursorPersona.getString(4));
                listaPersona.add(persona);
            }while(cursorPersona.moveToNext());
        }
        cursorPersona.close();
        return listaPersona;
    }

    public Persona verPersona(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Persona persona = null;
        Cursor cursorPersona;

        cursorPersona = db.rawQuery("SELECT * FROM " + TABLE_PERSONAS + " WHERE id=" + id + " LIMIT 1 ", null);

        if(cursorPersona.moveToFirst()){
            persona = new Persona();
            persona.setId(cursorPersona.getInt(0));
            persona.setNombre(cursorPersona.getString(1));
            persona.setApellido(cursorPersona.getString(2));
            persona.setTelefono(cursorPersona.getString(3));
            persona.setEmail(cursorPersona.getString(4));
        }
        cursorPersona.close();

        return persona;
    }

    public boolean editarPersona(int id, String nombre, String apellido, String telefono, String email){

        boolean correcto=false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE " + TABLE_PERSONAS + " SET nombre = '" +nombre+ "', apellido='" +apellido+ "', telefono= '" +telefono+ "', email= '" +email+ "' WHERE id='"+id+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarPersona(int id){

        boolean correcto=false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE from " + TABLE_PERSONAS + " WHERE id = '" + id + "'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }
        return correcto;
    }
}
