package br.com.mobills.gerenciadordecontas.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import br.com.mobills.gerenciadordecontas.R;
import br.com.mobills.gerenciadordecontas.control.MainControl;

public class MainActivity extends AppCompatActivity {
    private MainControl mainControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainControl = new MainControl(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainControl.onActivityResult(requestCode, resultCode, data);
    }
}
