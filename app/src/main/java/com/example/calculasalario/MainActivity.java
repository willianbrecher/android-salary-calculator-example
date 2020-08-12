package com.example.calculasalario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    BigDecimal salarioBruto;
    BigDecimal salarioLiquido;
    BigDecimal outrosDescontos;
    BigDecimal descontos;
    BigDecimal INSS;
    BigDecimal IRRF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void calcularSalario(){

    }
}
