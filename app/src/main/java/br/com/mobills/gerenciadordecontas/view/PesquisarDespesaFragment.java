package br.com.mobills.gerenciadordecontas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.mobills.gerenciadordecontas.R;
import br.com.mobills.gerenciadordecontas.control.PesquisarDespesaControl;


public class PesquisarDespesaFragment extends Fragment {

    private PesquisarDespesaControl control;
    public static View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       root = inflater.inflate(R.layout.fragment_pesquisar_despesa, container, false);


        control = new PesquisarDespesaControl(this);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        control.onResume();
    }
}
