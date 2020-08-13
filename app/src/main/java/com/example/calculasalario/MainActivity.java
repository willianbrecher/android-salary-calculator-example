package com.example.calculasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    BigDecimal salarioBruto;
    BigDecimal outrosDescontos;
    Long dependentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void calcularSalario(){
        Intent infoIntent = new Intent(this, ResultActivity.class);
        final EditText salarioBruto = findViewById(R.id.edtSalario);
        final EditText dependentes = findViewById(R.id.edtDependentes);
        final EditText outros = findViewById(R.id.edtOutros);

        this.salarioBruto = new BigDecimal(salarioBruto.getText().toString());
        this.dependentes = new Long(dependentes.getText().toString());
        this.outrosDescontos = new BigDecimal(outros.getText().toString());
    }

    private BigDecimal calculaINSS(BigDecimal salario){
        if (salario.compareTo(BigDecimal.valueOf(1045.01)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.075));

        } else if (salario.compareTo(BigDecimal.valueOf(1045.00)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(2089.61)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.09)).subtract(BigDecimal.valueOf(15.67));

        } else if (salario.compareTo(BigDecimal.valueOf(2089.61)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(3134.41)) == -1) {
            return salario.multiply(BigDecimal.valueOf(0.12)).subtract(BigDecimal.valueOf(78.36));

        } else if (salario.compareTo(BigDecimal.valueOf(3134.41)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(6101.07)) == -1) {
            return salario.multiply(BigDecimal.valueOf(0.14)).subtract(BigDecimal.valueOf(141.05));

        } else {
            return salario.subtract(BigDecimal.valueOf(713.10));
        }
    }


//    Até R$ 1.903,98 0 -
//    De R$ 1.903,99 até R$ 2.826,65 7,5% R$ 142,80
//    De R$ 2.826,66 ate R$ 3.751,05 15,0% R$ 354,80
//    De R$ 3.751,06 até R$ 4.664,68 22,5% R$ 636,13
//    Acima de R$ 4.664,68 27,5% R$ 869,36
    private BigDecimal calculaIRRF(BigDecimal salario){
        if (salario.compareTo(BigDecimal.valueOf(1903.98)) == -1){
            return BigDecimal.ZERO;

        } else if (salario.compareTo(BigDecimal.valueOf(1903.99)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(2826.66)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.075)).subtract(BigDecimal.valueOf(142.80));

        } else if (salario.compareTo(BigDecimal.valueOf(2826.66)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(3751.05)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.15)).subtract(BigDecimal.valueOf(142.80));

        } else if (salario.compareTo(BigDecimal.valueOf(3751.05)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(4664.68)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.225)).subtract(BigDecimal.valueOf(142.80));

        } else {
            return salario.multiply(BigDecimal.valueOf(0.275)).subtract(BigDecimal.valueOf(142.80));
        }
    }
}
