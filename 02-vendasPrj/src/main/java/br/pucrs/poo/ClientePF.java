package br.pucrs.poo;

public class ClientePF extends Cliente {
    private String cpf;

    public ClientePF(int codigo, String nome, String cpf) {
        super(codigo, nome);
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }

    public String getID() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return super.toString() + "[CPF " + cpf + "]";
    }
}
