package br.pucrs.poo;

import java.util.*;

public class CadastroProdutos {
    private List<Produto> cadastroProdutos = new ArrayList<>();

    public void cadastrarNovoProduto(Produto p1) {
        cadastroProdutos.add(p1);
    }

    public Produto procuraPorCodigo(int codigo) {
        for (Produto produto : cadastroProdutos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public boolean removerPorCodigo(int codigo) {
        for (Produto produto : cadastroProdutos) {
            if (produto.getCodigo() == codigo) {
                cadastroProdutos.remove(produto);
                return true;
            }
        }
        return false;
    }

    public int totalClientes() {
        return cadastroProdutos.size();
    }

}
