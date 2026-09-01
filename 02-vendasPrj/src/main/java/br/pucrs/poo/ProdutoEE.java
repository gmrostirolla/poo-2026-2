package br.pucrs.poo;

import java.time.LocalDate;

/**
 * Prod. Elétro-Eletrônico - This class is a part of my OOP Java documentation
 * (practicing inheritance & polymorphism)
 *
 * @author Gabriel M. Rostirolla
 */
public class ProdutoEE extends Produto {
    private int diasGarantia;

    public ProdutoEE(int codigo, String nome, double preco, int diasGarantia) {
        super(codigo, nome, preco);

        if (diasGarantia <= 0) {
            throw new IllegalArgumentException("Prazo de garantia inválido!");
        } else {
            this.diasGarantia = diasGarantia;
        }
    }

    public String getGarantia(LocalDate dataCompra) {
        if (dataCompra == null) {
            throw new IllegalArgumentException("A data de compra não pode ser nula!");
        }

        LocalDate dataVencimento = getValidade(dataCompra);

        return String.format("%s - %d Dias de Garantia - Data Compra: %s - Vencimento: %s",
                getNome(), this.diasGarantia, dataCompra, dataVencimento);
    }

    /**
     * Retorna se o produto em questão está ou não válido.
     *
     * @param ProdutoEE - Recebe um Produto Elétro-Eletrônico como parâmetro.
     * @return Validade do protudo.
     * @throws IllegalArgumentException - Se o produto informado for inválido.
     */
    public String statusGarantia(LocalDate dataVenda) {
        if (dataVenda == null) {
            throw new IllegalArgumentException("Data inválida!");
        }

        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isAfter(getValidade(dataVenda))) {
            return "EXPIRADO";

        } else {
            return "VÁLIDO";
        }
    }

    /**
     * Retorna a data de validade da garantia.
     *
     * @param dataVenda - Recebe a data da venda como parâmetro.
     * @return Data de validade.
     * @throws IllegalArgumentException - Se a data informada for inválida.
     */
    public LocalDate getValidade(LocalDate dataVenda) {
        if (dataVenda == null) {
            throw new IllegalArgumentException("Data inválida!");
        } else {
            return dataVenda.plusDays(diasGarantia);
        }
    }
}
