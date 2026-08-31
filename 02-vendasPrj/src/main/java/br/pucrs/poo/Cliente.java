package br.pucrs.poo;

//import java.util.*;

/**
 * Cliente - This class is a part of my OOP Java documentation!
 *
 * @author Gabriel M. Rostirolla
 */
public abstract class Cliente {
    private String nome;
    private String codigo;
    // private List<Venda> vendas = new ArrayList<Venda>();

    public Cliente(String nome, String codigo) {
        if ((codigo == null) && (nome == null)) {
            throw new NullPointerException("Parâmetros nulos!");
        } else if ((codigo == null) || (nome == null)) {
            throw new NullPointerException("Parâmetro(s) nulo(s) !");
        }
        this.nome = nome;
        this.codigo = codigo;
    }

    public Cliente() {
        this.codigo = "-1";
        this.nome = "Sem nome";

    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String toString() {
        return String.format("Nome: %s Código: %s", nome, codigo);
    }
}
