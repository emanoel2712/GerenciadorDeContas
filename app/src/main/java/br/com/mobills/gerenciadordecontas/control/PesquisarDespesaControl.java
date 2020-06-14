package br.com.mobills.gerenciadordecontas.control;

import android.content.Intent;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;

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

public class PesquisarDespesaControl {
    private View root;
    private Fragment fragment;

    public static List<Despesa> listDespesa = new ArrayList<>();

    private RecyclerView recyclerViewDespesa;

    private Button btAddDespesa;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public PesquisarDespesaControl(View root, Fragment fragment) {
        this.root = root;
        this.fragment = fragment;

        initComponents();
    }


    private void initComponents() {
        btAddDespesa = root.findViewById(R.id.btAddDespesa);

        inicializarFirebase();

        recyclerViewDespesa = root.findViewById(R.id.recyclerViewDespesas);

        recuperarDespesasBanco();

        setupRecyclerView(recyclerViewDespesa);

        btAddDespesa();

    }

    public void btAddDespesa() {
        btAddDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(root.getContext(), CadastrarDespesaActivity.class);
                fragment.getActivity().startActivity(it);


            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(root.getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            recyclerView.setAdapter(new DespesaAdapter(root.getContext(), listDespesa));
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

                            listDespesa.add(despesa);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void limparLista(){
    listDespesa.clear();

    }

    //    private void clickItemLista() {
//        despesa = new Despesa();
//
//
//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tecnicoId = "";
//              final int pos = recyclerView.getRootView();
//
//                if (pos != RecyclerView.NO_POSITION) {
//
////                  tecnicoId = listDespesa.get(pos).getId();
//                    //System.out.println(tecnicoId);
//
////                    Intent intent = new Intent(v.getContext(), TelaExibeTecnico.class);
////                    Bundle b = new Bundle();
////                    b.putString("tecnicoId", tecnicoId.toString());
////                    intent.putExtras(b);
////                    v.getContext().startActivity(intent);
//
//                    AlertDialog.Builder alerta = new AlertDialog.Builder(fragment.getContext());
//                    alerta.setTitle("Visualizando Despesa");
//                    alerta.setMessage(listDespesa.get(pos).toString());
//                    alerta.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            despesa = null;
//                        }
//                    });
//                    alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                        }
//                    });
//                    alerta.show();
//                }
//            }
//        });
//
//    }

//        Query query;
//        query = databaseReference.child("Despesa").orderByChild("valor");


    public void onResume() {
        setupRecyclerView(recyclerViewDespesa);
    }


}

