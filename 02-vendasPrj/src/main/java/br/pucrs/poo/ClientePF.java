package br.pucrs.poo;

public class ClientePF extends Cliente {
    private String cpf;

    public ClientePF(String codigo, String nome, String cpf) {
        super(codigo, nome);
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }

    public ClientePF() {
        this.cpf = "-1";
    }

    @Override
    public String toString() {
        return super.toString() + "[CPF " + cpf + "]";
    }
}
