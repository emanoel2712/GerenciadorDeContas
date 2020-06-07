package br.com.mobills.gerenciadordecontas.control;

import android.app.Activity;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.mobills.gerenciadordecontas.R;
import br.com.mobills.gerenciadordecontas.model.Despesa;
import br.com.mobills.gerenciadordecontas.utils.MaskEditUtil;

public class CadastrarDespesaControl {
    private Activity activity;
    private TextInputEditText editValor;
    private TextInputEditText editDescricao;
    private TextInputEditText editData;
    private MaterialCheckBox checkBoxPago;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private Despesa despesa;

    public CadastrarDespesaControl(Activity activity) {
        this.activity = activity;

        initComponents();
    }

    private void initComponents() {
        editValor = activity.findViewById(R.id.editValor);
        editDescricao = activity.findViewById(R.id.editDesc);
        editData = activity.findViewById(R.id.editData);
        editData.addTextChangedListener(MaskEditUtil.mask(editData, MaskEditUtil.FORMAT_DATE_HOUR));
        checkBoxPago = activity.findViewById(R.id.checkboxPago);

        despesa = new Despesa();

        inicializarFirebase();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(activity);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    public void salvarAction() {
        limparListaAntiga();

        despesa.setId(UUID.randomUUID().toString());
        despesa.setValor(Double.valueOf(editValor.getText().toString()));
        despesa.setDescricao(editDescricao.getText().toString());
        despesa.setData(editData.getText().toString());
        despesa.setPago(verificaCheckBox());

        databaseReference.child("Despesa").child(despesa.getId()).setValue(despesa);

        System.out.println("Despesa" + despesa);
        activity.finish();


    }

    private boolean verificaCheckBox() {
        if (checkBoxPago.isChecked()) {
            despesa.setPago(Boolean.valueOf("Sim"));

        } else {
            despesa.setPago(Boolean.valueOf("Não"));
        }
        return despesa.isPago();

    }

    private void limparListaAntiga() {
        PesquisarDespesaControl.listDespesa.clear();

    }


}
