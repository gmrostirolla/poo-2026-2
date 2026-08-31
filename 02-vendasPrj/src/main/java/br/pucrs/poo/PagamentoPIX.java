package br.pucrs.poo;

public class PagamentoPIX extends Pagamento {
    private String chaveDestino;

    public PagamentoPIX(double valorBruto, String chavePIX) {
        super(valorBruto);
        this.chaveDestino = chavePIX;
    }

    public boolean processar() {
        if (chaveDestino != null && !chaveDestino.isBlank()) {
            double novoValor = super.getValor() - (super.getValor() * 0.1);
            super.setValor(novoValor);
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return String.format("PIX - Chave: %s", chaveDestino);
    }
}
