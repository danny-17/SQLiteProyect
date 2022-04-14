package com.example.sqliteproyect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqliteproyect.adptadores.ListaPersona_Adaprter;
import com.example.sqliteproyect.db.DbHelper;
import com.example.sqliteproyect.db.DbPersona;
import com.example.sqliteproyect.entidades.Persona;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaPersonas;
    ArrayList<Persona> listaArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPersonas=findViewById(R.id.listaPersona);
        listaPersonas.setLayoutManager(new LinearLayoutManager(this));
        DbPersona dbPersona = new DbPersona(MainActivity.this);
        listaArray = new ArrayList<>();

        ListaPersona_Adaprter adaprter = new ListaPersona_Adaprter(dbPersona.mostrarPersona());
        listaPersonas.setAdapter(adaprter);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this,Activity_Lista.class);
        startActivity(intent);
    }
}