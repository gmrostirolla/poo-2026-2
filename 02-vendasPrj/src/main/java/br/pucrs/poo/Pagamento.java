package br.pucrs.poo;

public abstract class Pagamento {
    private double valor;

    public Pagamento(double valorBruto) {
        if (valorBruto <= 0.0) {
            throw new IllegalArgumentException("O valor indicado está inválido!");
        } else {
            this.valor = valorBruto;
        }
    }

    public void setValor(double novoValor) {
        if (novoValor <= 0.0) {
            throw new IllegalArgumentException("o valor indicado está inválido!");
        } else {
            this.valor = novoValor;
        }
    }

    public double getValor() {
        return this.valor;
    }

    public abstract boolean processar();

    public abstract String toString();
}
