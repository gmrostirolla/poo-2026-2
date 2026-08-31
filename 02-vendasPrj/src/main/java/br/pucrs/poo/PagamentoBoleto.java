package br.pucrs.poo;

public class PagamentoBoleto extends Pagamento {
    private String codigoBarras;

    public PagamentoBoleto(double valorBruto, String codigo) {
        super(valorBruto);
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código de barras inválido!");
        } else {
            this.codigoBarras = codigo;
        }
    }

    public boolean processar() {
        return true;
    }

    public String toString() {
        return "Boleto";
    }
}
