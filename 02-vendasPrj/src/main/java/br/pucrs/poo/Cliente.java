package br.pucrs.poo;

//import java.util.*;

/**
 * Cliente - This class is a part of my OOP Java documentation!
 *
 * @author Gabriel M. Rostirolla
 */
public abstract class Cliente {
    private String nome;
    private int codigo;
    // private List<Venda> vendas = new ArrayList<Venda>();

    public Cliente(int codigo, String nome) {
        if ((codigo == 0) || (nome == null)) {
            throw new IllegalArgumentException("Parâmetro(s) inválido(s) !");
        }
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public abstract String getID();

    public String toString() {
        return String.format("Nome: %s Código: %s", nome, codigo);
    }
}
