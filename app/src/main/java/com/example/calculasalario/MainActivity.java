package com.example.calculasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Willian
 */
public class MainActivity extends AppCompatActivity {

    public final static String SALARIO_BRUTO = "com.example.calculasalario.SALARIO_BRUTO";
    public final static String DEPENDENTES = "com.example.calculasalario.DEPENDENTES";
    public final static String OUTROS_DESCONTOS = "com.example.calculasalario.OUTROS_DESCONTOS";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText edtSalario = findViewById(R.id.edtSalario);
        final EditText edtDependentes = findViewById(R.id.edtDependentes);
        final EditText edtOutros = findViewById(R.id.edtOutros);

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

                intent.putExtra(SALARIO_BRUTO, Double.parseDouble(edtSalario.getText().toString()));
                intent.putExtra(DEPENDENTES, Double.parseDouble(edtDependentes.getText().toString()));
                intent.putExtra(OUTROS_DESCONTOS, Double.parseDouble(edtOutros.getText().toString()));

                startActivity(intent);
            }
        });
    }
}
