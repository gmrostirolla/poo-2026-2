package br.pucrs.poo;

import java.time.LocalDate;

/**
 * Prod. Elétro-Eletrônico - This class is a part of my OOP Java documentation
 * (practicing inheritance & polymorphism)
 *
 * @author Gabriel M. Rostirolla
 */
public class ProdutoEE extends Produto {
    private int diaGarantia;

    public ProdutoEE(int codigo, String nome, double preco, int diaGarantia) {
        super(codigo, nome, preco);

        if (diaGarantia <= 0) {
            throw new IllegalArgumentException("Prazo de garantia inválido!");
        } else {
            this.diaGarantia = diaGarantia;
        }
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
            return dataVenda.plusDays(diaGarantia);
        }
    }
}
