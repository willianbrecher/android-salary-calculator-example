package com.example.calculasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        BigDecimal salarioBruto = new BigDecimal(intent.getStringExtra(MainActivity.SALARIO_BRUTO));
        BigDecimal dependentes = new BigDecimal(intent.getStringExtra(MainActivity.DEPENDENTES));
        BigDecimal outrosDescontos = new BigDecimal(intent.getStringExtra(MainActivity.OUTROS_DESCONTOS));

        BigDecimal descontoINSS = this.calculaINSS(salarioBruto);
        BigDecimal salarioLiquido = salarioBruto.subtract(descontoINSS);
        BigDecimal descontoIRRF = this.calculaIRRF(salarioLiquido.subtract(dependentes.multiply(BigDecimal.valueOf(189.59))));

        salarioLiquido = salarioLiquido.subtract(descontoIRRF).subtract(outrosDescontos);
        BigDecimal descontos = descontoINSS.add(descontoIRRF).add(outrosDescontos);
        BigDecimal porcentagemDesconto = salarioBruto.multiply(descontos.divide(BigDecimal.valueOf(100)));

        TextView txtSalarioBruto = (TextView)findViewById(R.id.txtSalarioBruto);
        txtSalarioBruto.setText(salarioBruto.toString());

        TextView txtINSS = (TextView)findViewById(R.id.txtINSS);
        txtINSS.setText(descontoINSS.toString());

        TextView txtIRRF = (TextView)findViewById(R.id.txtIRRF);
        txtIRRF.setText(descontoIRRF.toString());

        TextView txtOutrosDescontos = (TextView)findViewById(R.id.txtOutrosDescontos);
        txtOutrosDescontos.setText(outrosDescontos.toString());

        TextView txtLiquido = (TextView)findViewById(R.id.txtLiquido);
        txtLiquido.setText(salarioLiquido.toString());

        TextView txtDescontos = (TextView)findViewById(R.id.txtDescontos);
        txtDescontos.setText(porcentagemDesconto.toString()+"%");

    }

    private BigDecimal calculaINSS(BigDecimal salario){
        if (salario.compareTo(BigDecimal.valueOf(1045.01)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.075));

        } else if (salario.compareTo(BigDecimal.valueOf(1045.00)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(2089.61)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.09)).subtract(BigDecimal.valueOf(15.67));

        } else if (salario.compareTo(BigDecimal.valueOf(2089.60)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(3134.41)) == -1) {
            return salario.multiply(BigDecimal.valueOf(0.12)).subtract(BigDecimal.valueOf(78.36));

        } else if (salario.compareTo(BigDecimal.valueOf(3134.40)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(6101.07)) == -1) {
            return salario.multiply(BigDecimal.valueOf(0.14)).subtract(BigDecimal.valueOf(141.05));

        } else {
            return BigDecimal.valueOf(713.10);
        }
    }

    private BigDecimal calculaIRRF(BigDecimal salario){
        if (salario.compareTo(BigDecimal.valueOf(1903.99)) == -1){
            return BigDecimal.ZERO;

        } else if (salario.compareTo(BigDecimal.valueOf(1903.98)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(2826.66)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.075)).subtract(BigDecimal.valueOf(142.80));

        } else if (salario.compareTo(BigDecimal.valueOf(2826.65)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(3751.06)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.15)).subtract(BigDecimal.valueOf(142.80));

        } else if (salario.compareTo(BigDecimal.valueOf(3751.05)) == 1 &&
                salario.compareTo(BigDecimal.valueOf(4664.69)) == -1){
            return salario.multiply(BigDecimal.valueOf(0.225)).subtract(BigDecimal.valueOf(636.13));

        } else {
            return salario.multiply(BigDecimal.valueOf(0.275)).subtract(BigDecimal.valueOf(869.36));
        }
    }
}
