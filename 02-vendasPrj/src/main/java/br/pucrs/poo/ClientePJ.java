package br.pucrs.poo;

public class ClientePJ extends Cliente {
    private String cnpj;

    public ClientePJ(String codigo, String nome, String cnpj) {
        super(codigo, nome);
        this.cnpj = cnpj;
    }

    public String getCNPJ() {
        return cnpj;
    }

    @Override
    public String toString() {
        return super.toString() + "[CNPJ " + cnpj + "]";

    }
}
