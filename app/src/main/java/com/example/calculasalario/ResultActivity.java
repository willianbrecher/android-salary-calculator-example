package com.example.calculasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

/**
 * @author Willian
 */
public class ResultActivity extends AppCompatActivity {

    private int dependentes;

    private double salarioBruto;
    private double outrosDescontos;
    
    private TextView txtSalarioBruto;
    private TextView txtINSS;
    private TextView txtIRRF;
    private TextView txtOutrosDescontos;
    private TextView txtLiquido;
    private TextView txtDescontos;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        this.salarioBruto = intent.getDoubleExtra(MainActivity.SALARIO_BRUTO,0);
        this.dependentes = intent.getIntExtra(MainActivity.DEPENDENTES,0);
        this.outrosDescontos = intent.getDoubleExtra(MainActivity.OUTROS_DESCONTOS,0);

        this.txtSalarioBruto = (TextView)findViewById(R.id.txtSalarioBruto);
        this.txtINSS = (TextView)findViewById(R.id.txtINSS);
        this.txtIRRF = (TextView)findViewById(R.id.txtIRRF);
        this.txtOutrosDescontos = (TextView)findViewById(R.id.txtOutrosDescontos);
        this.txtLiquido = (TextView)findViewById(R.id.txtLiquido);
        this.txtDescontos = (TextView)findViewById(R.id.txtDescontos);

        this.calculaSalario();
    }

    /**
     *
     */
    private void calculaSalario(){
        double valorINSS = calculaINSS(this.salarioBruto);
        double baseCalculoIRRF = this.salarioBruto - valorINSS - (this.dependentes * 189.59);
        double valorIRRF = calculaIRRF(baseCalculoIRRF);
        double salarioLiquido = this.salarioBruto - valorINSS - valorIRRF - this.outrosDescontos;
        double percentualDescontos = (this.salarioBruto > 0 ? (1-salarioLiquido/this.salarioBruto)*100 : 0.0);

        txtSalarioBruto.setText(String.valueOf(this.formatDouble(salarioBruto)));
        txtINSS.setText(String.valueOf(this.formatDouble(valorINSS)));
        txtIRRF.setText(String.valueOf(this.formatDouble(valorIRRF)));
        txtOutrosDescontos.setText(String.valueOf(this.formatDouble(outrosDescontos)));
        txtLiquido.setText(String.valueOf(this.formatDouble(salarioLiquido)));
        txtDescontos.setText(String.valueOf(formatDouble(percentualDescontos))+"%");
    }

    /**
     *
     * @param salario
     * @return
     */
    private double calculaINSS(double salario){
        if(salario <= 1045){
            return salario*0.075;
        }
        if(salario <= 2089.60){
            return (salario*0.09) - 15.67;
        }
        if(salario <= 3134.40){
            return (salario*0.12) -78.36;
        }
        if(salario <= 6101.06){
            return (salario*0.14) -141.05;
        }
        return 713.10;
    }

    /**
     *
     * @param salario
     * @return
     */
    private double calculaIRRF(double salario){
        if (salario <= 1903.98){
            return 0;
        }
        if (salario <= 2826.65){
            return (salario*0.075) - 142.80;
        }
        if (salario <= 3751.05){
            return (salario*0.15) - 354.80;
        }
        if (salario <= 4664.68){
            return (salario*0.225) - 636.13;
        }

        return (salario*0.275) - 868.36;
    }

    /**
     *
     * @param value
     * @return
     */
    private double formatDouble(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format(value));
    }
}
