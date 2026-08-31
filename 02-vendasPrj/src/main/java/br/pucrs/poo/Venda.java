package br.pucrs.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Venda - This class is a part of my OOP Java documentation!
 *
 * @author Gabriel M. Rostirolla
 */
public class Venda {
    private Cliente cliente;
    private Pagamento pagamento;
    private List<ItemVenda> itens = new ArrayList<>();
    private int nroNF;
    private LocalDate dataCriacao;

    public static int proxNroNF = 1000;

    /**
     * Cria uma Venda.
     *
     * @param x - Cliente
     * @throws NullPointerException Se o Cliente não for informado.
     */
    public Venda(Cliente x) {
        if (x == null) {
            throw new NullPointerException("Cliente não informado!");
        }
        this.cliente = x;
        this.dataCriacao = LocalDate.now();
        Venda.proxNroNF = Venda.proxNroNF + 1;
        this.nroNF = proxNroNF;
    }

    /**
     * Adiciona um ItemProduto à uma Venda.
     *
     * @param prod - ItemProduto
     */
    public void inserirProduto(ItemVenda prod) {
        if (prod == null) {
            throw new NullPointerException("Item não informado!");
        }
        itens.add(prod);
    }

    // Versão alternativa para adicionar produtos:

    /**
     * Adiciona um ItemProduto e uma quantidade à uma Venda.
     *
     * @param prod - ItemProduto
     * @param qtd  - Quantidade
     * @throws IllegalArgumentException Se os parâmetros forem inválidos.
     */
    public void inserirProduto(int qtd, Produto prod) {
        if ((prod == null) && (qtd <= 0)) {
            throw new IllegalArgumentException("Parâmetros inválidos!");
        } else if ((prod == null) || (qtd <= 0)) {
            throw new IllegalArgumentException("Parâmetro(s) inválido(s)!");
        }
        itens.add(new ItemVenda(prod, qtd));
    }

    /**
     * Adiciona uma 1 unidade de um Produto à uma Venda.
     *
     * @param prod - Produto (1x)
     * @throws NullPointerException Se o Produto não for informado.
     */
    public void inserirProduto(Produto prod) {
        if (prod == null) {
            throw new NullPointerException("Produto não informado!");
        }
        this.inserirProduto(1, prod);
    }

    public void definirMetodoPagamento(Pagamento metodo) {
        if (metodo == null) {
            throw new IllegalArgumentException("Método de pagamento não informado");
        } else {
            this.pagamento = metodo;
        }
    }

    /**
     * Retorna a data em que o produto foi comprado.
     *
     * @return Data de Criação.
     */
    public LocalDate dataCriacao() {
        return dataCriacao;
    }

    public String verificarGarantia(ProdutoEE prod) {
        return prod.statusGarantia(this.dataCriacao);
    }

    /**
     * Retorna o valor total de uma Venda (Produtos * N).
     *
     * @return Valor total de uma Venda.
     */
    public double valorTotal() {
        double total = 0;

        for (ItemVenda item : itens) {
            total += item.ValorTotal();
        }
        return total;
    }

    /**
     * Retorna uma Nota Fiscal com todas as informações de uma Venda (Cliente,
     * Produtos, Valor total, Código da NF e Horário).
     *
     * @return Nota Fiscal
     */
    public String getNotaFiscal() {
        if (pagamento == null) {
            throw new IllegalStateException("Método de pagamento não informado!");
        } else {
            StringBuilder notaFiscal = new StringBuilder();
            notaFiscal.append(
                    "Nota Fiscal de Exemplo - POO \t\t\t" + LocalDateTime.now().toLocalTime() + "\t" + nroNF + "\n");
            notaFiscal.append(
                    "------------------------------------------------------------------------------------------\n");
            notaFiscal.append(String.format("CLIENTE: %s \t\t\t\t\t\t QTD PRODS.:", cliente.getCodigo()) + "\n\n");

            for (ItemVenda item : itens) {
                notaFiscal.append(item.getDetalhes() + "\n");
            }
            notaFiscal.append(
                    "------------------------------------------------------------------------------------------\n");
            notaFiscal.append("PAGAMENTO: " + pagamento + "\t\t\t\t VALOR TOTAL: R$" + valorTotal() + "\n\n");
            notaFiscal.append("\t\t\t\t TOTAL À PAGAR: R$" + pagamento.getValor() + "\n");

            return notaFiscal.toString();
        }
    }
}
