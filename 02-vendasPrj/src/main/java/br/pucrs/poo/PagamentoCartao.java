package br.pucrs.poo;

public class PagamentoCartao extends Pagamento {
    private String numeroMascarado;
    private int parcelas;

    public PagamentoCartao(double valorBruto, String nroCartao, int qtdParcelas) {
        super(valorBruto);
        this.numeroMascarado = ("**** **** **** " + nroCartao.substring(nroCartao.length() - 4));
        if (qtdParcelas <= 0 || qtdParcelas > 12) {
            throw new IllegalArgumentException("Quantidade de parcelas inválida!");
        } else {
            this.parcelas = qtdParcelas;
        }
    }

    public boolean processar() {
        double novoValor;
        if (parcelas <= 3) {
            return true;
        } else {
            novoValor = super.getValor() + (super.getValor() * 0.05);
            super.setValor(novoValor);
            return true;
        }
    }

    public String toString() {
        return String.format("Cartão de Crédito (%dx) - Nº: %s", parcelas, numeroMascarado);
    }
}
