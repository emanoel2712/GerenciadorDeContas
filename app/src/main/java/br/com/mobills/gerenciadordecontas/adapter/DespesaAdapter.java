package br.com.mobills.gerenciadordecontas.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.mobills.gerenciadordecontas.R;
import br.com.mobills.gerenciadordecontas.model.Despesa;
import br.com.mobills.gerenciadordecontas.view.CadastrarDespesaActivity;

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.ViewHolder> {

    private List<Despesa> listDespesa;
    private Context context;

    public DespesaAdapter(Context context, List<Despesa> listDespesa) {
        this.listDespesa = listDespesa;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_despesa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvValor.setText(String.valueOf(listDespesa.get(position).getValor()));
        holder.tvDesc.setText(listDespesa.get(position).getDescricao());
        holder.tvData.setText(String.valueOf(listDespesa.get(position).getData()));
        holder.tvPago.setText(String.valueOf(listDespesa.get(position).isPago()));
    }

    @Override
    public int getItemCount() {
        return listDespesa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvValor;
        public TextView tvDesc;
        public TextView tvData;
        public TextView tvPago;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvValor = itemView.findViewById(R.id.tvValor);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvData = itemView.findViewById(R.id.tvData);
            tvPago = itemView.findViewById(R.id.tvPago);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {


                        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                        alerta.setTitle("Visualizando despesa");
                        alerta.setMessage(listDespesa.get(pos).toString());

                        alerta.setNeutralButton("Fechar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//

                                Intent it = new Intent(context, CadastrarDespesaActivity.class);
                                Bundle b = new Bundle();
                                b.putString("despesaId", listDespesa.get(pos).toString());
                                it.putExtras(b);

                                itemView.getContext().startActivity(it);


                            }
                        });
                        alerta.show();


                    }

                }
            });
        }
    }
}

