package com.example.sqliteproyect;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteproyect.db.DbPersona;
import com.example.sqliteproyect.entidades.Persona;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtTelefono, txtEmail;
    Button btnGuardar;

    boolean correcto= false;

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

        DbPersona dbPersona = new DbPersona(EditarActivity.this);
        persona=dbPersona.verPersona(id);

        if(persona != null){
            txtNombre.setText(persona.getNombre());
            txtApellido.setText(persona.getApellido());
            txtTelefono.setText(persona.getTelefono());
            txtEmail.setText(persona.getEmail());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("")&&!txtApellido.getText().toString().equals("")){
                    correcto = dbPersona.editarPersona(id, txtNombre.getText().toString(), txtApellido.getText().toString(),txtTelefono.getText().toString(),txtEmail.getText().toString());
                    if(correcto){
                        Toast.makeText(EditarActivity.this, "Persona editada correctamente", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "Error al editar a la persona", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EditarActivity.this, "Llene todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void verRegistro(){
        Intent intent = new Intent(this,VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
