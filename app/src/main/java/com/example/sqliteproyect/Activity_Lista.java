package com.example.sqliteproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteproyect.db.DbPersona;

public class Activity_Lista extends AppCompatActivity {

    EditText txtnombre, txtapellido,txttelefono,txtemail;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        txtnombre=findViewById(R.id.txtNombre);
        txtapellido=findViewById(R.id.txtApellido);
        txttelefono=findViewById(R.id.txtTelefono);
        txtemail=findViewById(R.id.txtEmail);
        btn_guardar=findViewById(R.id.btnGuarda);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbPersona dbPersona = new DbPersona(Activity_Lista.this);
                long id=dbPersona.insertarPersona(txtnombre.getText().toString(), txtapellido.getText().toString(),
                        txttelefono.getText().toString(),txtemail.getText().toString());

                if(id>0){
                    Toast.makeText(Activity_Lista.this, "Datos Insertados", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else{
                    Toast.makeText(Activity_Lista.this, "Error al Insertar Datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void limpiar(){
        txtnombre.setText("");
        txtapellido.setText("");
        txttelefono.setText("");
        txtemail.setText("");
    }
}