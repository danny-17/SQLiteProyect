package com.example.sqliteproyect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqliteproyect.db.DbPersona;
import com.example.sqliteproyect.entidades.Persona;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtTelefono, txtEmail;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;
    Persona persona;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ver);
        txtNombre=findViewById(R.id.txtNombre);
        txtApellido=findViewById(R.id.txtApellido);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtEmail=findViewById(R.id.txtEmail);

        btnGuardar=findViewById(R.id.btnGuarda);
        fabEditar=findViewById(R.id.fabEditar);
        fabEliminar=findViewById(R.id.fabEliminar);

        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                id= Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");
            }
        }else{
            id=(int)savedInstanceState.getSerializable("ID");
        }

        DbPersona dbPersona = new DbPersona(VerActivity.this);
        persona=dbPersona.verPersona(id);

        if(persona != null){
            txtNombre.setText(persona.getNombre());
            txtApellido.setText(persona.getApellido());
            txtTelefono.setText(persona.getTelefono());
            txtEmail.setText(persona.getEmail());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtApellido.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtEmail.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Seguro desea Eliminar?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (dbPersona.eliminarPersona(id)){
                            lista();
                        }

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

   private void lista(){
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
   }
}