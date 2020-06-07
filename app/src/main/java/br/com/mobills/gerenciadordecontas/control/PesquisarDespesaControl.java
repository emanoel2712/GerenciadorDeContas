package br.com.mobills.gerenciadordecontas.control;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.mobills.gerenciadordecontas.R;
import br.com.mobills.gerenciadordecontas.adapter.DespesaAdapter;
import br.com.mobills.gerenciadordecontas.model.Despesa;
import br.com.mobills.gerenciadordecontas.view.CadastrarDespesaActivity;
import br.com.mobills.gerenciadordecontas.view.PesquisarDespesaFragment;

import static java.security.AccessController.getContext;

public class PesquisarDespesaControl {
    private Fragment fragment;
    public static List<Despesa> listDespesa = new ArrayList<>();

    private RecyclerView recyclerViewDespesa;

    private Button btAddDespesa;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    Despesa despesa;


    public PesquisarDespesaControl() {
    }

    public PesquisarDespesaControl(Fragment fragment) {
        this.fragment = fragment;

        initComponents();
    }


    private void initComponents() {
        btAddDespesa = PesquisarDespesaFragment.root.findViewById(R.id.btAddDespesa);

        inicializarFirebase();

        recyclerViewDespesa = PesquisarDespesaFragment.root.findViewById(R.id.recyclerViewDespesas);

        recuperarDespesasBanco();

        setupRecyclerView(recyclerViewDespesa);

        btAddDespesa();

    }

    public void btAddDespesa() {
        btAddDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(fragment.getActivity(), CadastrarDespesaActivity.class);
                fragment.getActivity().startActivity(it);


            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(fragment.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
            recyclerView.setAdapter(new DespesaAdapter(fragment.getContext(), listDespesa));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void recuperarDespesasBanco() {
        Query query = databaseReference.child("Despesa");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Despesa despesa = snapshot.getValue(Despesa.class);

//
                        listDespesa.add(despesa);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public void onResume() {
        setupRecyclerView(recyclerViewDespesa);
    }


}

