package com.example.calculasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    public final static String SALARIO_BRUTO = "com.example.calculasalario.SALARIO_BRUTO";
    public final static String DEPENDENTES = "com.example.calculasalario.DEPENDENTES";
    public final static String OUTROS_DESCONTOS = "com.example.calculasalario.OUTROS_DESCONTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcularSalario(View view){
        Intent infoIntent = new Intent(this, ResultActivity.class);
        final EditText salarioBruto = findViewById(R.id.edtSalario);
        final EditText dependentes = findViewById(R.id.edtDependentes);
        final EditText outros = findViewById(R.id.edtOutros);

        infoIntent.putExtra(SALARIO_BRUTO, new BigDecimal(salarioBruto.getText().toString()));
        infoIntent.putExtra(DEPENDENTES, new Long(dependentes.getText().toString()));
        infoIntent.putExtra(OUTROS_DESCONTOS, new BigDecimal(outros.getText().toString()));

        startActivity(infoIntent);
    }
}
