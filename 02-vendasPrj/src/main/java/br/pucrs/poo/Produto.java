package br.pucrs.poo;

/**
 * Produto - This class is a part of my OOP Java documentation!
 *
 * @author Gabriel M. Rostirolla
 */
public class Produto {

    private int codigo;
    private String nome;
    private double preco;

    public Produto(int codigo, String nome, double preco) {
        if ((codigo <= 0) && (nome == null) && (preco <= 0)) {
            throw new IllegalArgumentException("Parâmetros inválidos!");
        } else if ((codigo <= 0) || (nome == null) || (preco <= 0)) {
            throw new IllegalArgumentException("Parâmetro(s) inválido(s) !");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String toString() {
        return String.format("Produto: %d, Nome: %s, Preço: %f", codigo, nome, preco);
    }
}
