package br.com.mobills.gerenciadordecontas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import br.com.mobills.gerenciadordecontas.R;
import br.com.mobills.gerenciadordecontas.control.CadastrarDespesaControl;

public class CadastrarDespesaActivity extends AppCompatActivity {
      private CadastrarDespesaControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_despesa);

        control = new CadastrarDespesaControl(this);
    }

    public void btCadastrar(View v){
        control.salvarAction();
    }
}
