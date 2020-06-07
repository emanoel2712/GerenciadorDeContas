package br.com.mobills.gerenciadordecontas.model;

import java.sql.Timestamp;
import java.sql.Date;


public class Despesa {
    private String id;
    private double valor;
    private String descricao;
    private String data;
    private boolean pago;

    public Despesa() {
    }

    public Despesa(String id, double valor, String descricao, String data, boolean pago) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.pago = pago;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return
                "Valor: " + valor + "\nPago: " + pago + "\nData: " + data +
                "\nDescricao: " + descricao;

    }
}
