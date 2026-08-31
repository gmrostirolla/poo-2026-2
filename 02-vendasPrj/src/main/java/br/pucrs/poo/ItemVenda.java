package br.pucrs.poo;

/**
 * ItemProduto - This class is a part of my OOP Java documentation!
 *
 * @author Gabriel M. Rostirolla
 */
public class ItemVenda {
    private Produto produto;
    private int quantidade;

    // Método p/ criar ItemProdutos passando o produto e a quantidade desejada:
    public ItemVenda(Produto prod, int qtde) {
        if ((prod == null) && (qtde <= 0)) {
            throw new IllegalArgumentException("Parâmetros inválidos!");
        } else if ((prod == null) || (qtde <= 0)) {
            throw new IllegalArgumentException("Parâmetro(s) inválido(s)!");
        }
        this.produto = prod;
        this.quantidade = qtde;
    }

    /**
     * Retorna o valor UNITÁRIO do Produto.
     *
     * @return Preço de um Produto.
     */
    public double ValorUnitario() {
        return (produto.getPreco());
    }

    /**
     * Retorna o valor total de um ItemProduto (Quantidade X Preço Unitário).
     *
     * @return Valor total de um ItemProduto.
     */
    public double ValorTotal() {
        return (quantidade * produto.getPreco());
    }

    /**
     * Retorna o todas as informações de um Produto (Código, Nome, Quantidade e
     * Valor Unitário).
     *
     * @return Informações de Produto.
     */
    public String getDetalhes() {
        return String.format("(%d) %s \t\t\t\t\t\t\t %d x %.2f", produto.getCodigo(), produto.getNome(), quantidade,
                ValorUnitario());
    }
}
