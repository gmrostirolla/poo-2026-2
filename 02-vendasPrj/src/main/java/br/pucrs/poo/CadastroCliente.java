package br.pucrs.poo;

import java.util.*;

public class CadastroCliente {
    private List<Cliente> cadastroCliente = new ArrayList<>();

    public void cadastrarNovoCliente(Cliente c1){
        cadastroCliente.add(c1);
    }

    public int totalClientes(){
        return cadastroCliente.size();
    }
}
