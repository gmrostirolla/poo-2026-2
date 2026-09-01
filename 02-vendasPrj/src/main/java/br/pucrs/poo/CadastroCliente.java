package br.pucrs.poo;

import java.util.*;

public class CadastroCliente {
    private List<Cliente> cadastroCliente = new ArrayList<>();

    public void cadastrarNovoCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente inválido");
        } else {
            cadastroCliente.add(cliente);
        }
    }

    public Cliente procurarPorID(String id) {
        for (Cliente cliente : cadastroCliente) {
            if (cliente.getID() == id) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente procuraPorCodigo(int codigo) {
        for (Cliente cliente : cadastroCliente) {
            if (cliente.getCodigo() == codigo) {
                return cliente;
            }
        }
        return null;
    }

    public boolean removerPorCodigo(int codigo) {
        for (Cliente cliente : cadastroCliente) {
            if (cliente.getCodigo() == codigo) {
                cadastroCliente.remove(cliente);
                return true;
            }
        }
        return false;
    }

    public int totalClientes() {
        return cadastroCliente.size();
    }
}
