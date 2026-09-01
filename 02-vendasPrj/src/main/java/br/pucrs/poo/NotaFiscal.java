package br.pucrs.poo;

import java.time.LocalDateTime;
import java.util.List;

public class NotaFiscal {
    private int numero;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private double valorTotal;
    private double valorFinal;
    private String metodoPagamento;

    public NotaFiscal(int nroNF, Cliente cliente, List<ItemVenda> itens, double valorTotal, double valorFinal,
            String pagamento) {
        this.numero = nroNF;
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.valorFinal = valorFinal;
        this.metodoPagamento = pagamento;
    }

    /**
     * Retorna uma Nota Fiscal com todas as informações de uma Venda (Cliente,
     * Produtos, Valor total, Código da NF e Horário).
     *
     * @return Nota Fiscal
     */
    public String montarNotaFiscal() {
        String separador = "------------------------------------------------------------------------------------------\n";
        StringBuilder notaFiscal = new StringBuilder();
        notaFiscal.append("Nota Fiscal de Exemplo - POO\t\t\t")
                .append(LocalDateTime.now().toLocalTime())
                .append('\t')
                .append(numero)
                .append('\n');
        notaFiscal.append(separador);
        notaFiscal.append(String.format("CLIENTE: %s \t\t\t\t\t\t QTD PRODS.:%n%n", cliente.getCodigo()));

        for (ItemVenda item : itens) {
            notaFiscal.append(item.getDetalhes()).append('\n');
        }
        notaFiscal.append(separador);
        notaFiscal.append("PAGAMENTO: ")
                .append(metodoPagamento)
                .append("\t\t\t\t VALOR TOTAL: R$")
                .append(valorTotal)
                .append("\n\n");
        notaFiscal.append("\t\t\t\t TOTAL À PAGAR: R$")
                .append(valorFinal)
                .append('\n');

        return notaFiscal.toString();
    }
}
