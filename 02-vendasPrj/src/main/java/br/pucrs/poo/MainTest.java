package br.pucrs.poo;

import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        ClientePF clientePF = new ClientePF(1, "Gabriel Rostirolla", "06156205071");
        ClientePJ clientePJ = new ClientePJ(2, "Loja Exemplo LTDA", "12345678000199");

        System.out.println(clientePF);
        System.out.println(clientePF.getNome());
        System.out.println(clientePF.getCodigo());
        System.out.println(clientePF.getCPF());
        System.out.println(clientePF.getID());
        System.out.println(clientePJ);
        System.out.println(clientePJ.getNome());
        System.out.println(clientePJ.getCodigo());
        System.out.println(clientePJ.getCNPJ());
        System.out.println(clientePJ.getID());

        CadastroCliente cadastroCliente = new CadastroCliente();
        cadastroCliente.cadastrarNovoCliente(clientePF);
        cadastroCliente.cadastrarNovoCliente(clientePJ);
        System.out.println(cadastroCliente.totalClientes());
        System.out.println(cadastroCliente.procuraPorCodigo(1));
        System.out.println(cadastroCliente.procuraPorCodigo(2));
        System.out.println(cadastroCliente.procurarPorID("06156205071"));
        System.out.println(cadastroCliente.procurarPorID("12345678000199"));
        System.out.println(cadastroCliente.removerPorCodigo(2));
        System.out.println(cadastroCliente.totalClientes());
        System.out.println(cadastroCliente.procuraPorCodigo(2));

        ProdutoEE smartphone = new ProdutoEE(101, "Smartphone", 3000.0, 90);
        ProdutoEE notebook = new ProdutoEE(102, "Notebook", 5200.0, 180);
        ProdutoAlcoolico cerveja = new ProdutoAlcoolico(201, "Cerveja", 15.0);
        ProdutoAlcoolico vinho = new ProdutoAlcoolico(202, "Vinho", 42.0);

        System.out.println(smartphone);
        System.out.println(smartphone.getNome());
        System.out.println(smartphone.getCodigo());
        System.out.println(smartphone.getPreco());
        System.out.println(smartphone.getGarantia(LocalDate.now()));
        System.out.println(smartphone.getValidade(LocalDate.now()));
        System.out.println(smartphone.statusGarantia(LocalDate.now()));
        System.out.println(notebook.getGarantia(LocalDate.now().minusDays(120)));
        System.out.println(cerveja);
        System.out.println(cerveja.getNome());
        System.out.println(cerveja.getCodigo());
        System.out.println(cerveja.getPreco());
        System.out.println(cerveja.getTarifa());
        System.out.println(vinho.getPreco());
        cerveja.setTarifa(0.5);
        vinho.setTarifa(0.2);
        System.out.println(cerveja.getTarifa());
        System.out.println(vinho.getTarifa());
        System.out.println(cerveja.getPreco());
        System.out.println(vinho.getPreco());

        CadastroProdutos cadastroProdutos = new CadastroProdutos();
        cadastroProdutos.cadastrarNovoProduto(smartphone);
        cadastroProdutos.cadastrarNovoProduto(notebook);
        cadastroProdutos.cadastrarNovoProduto(cerveja);
        cadastroProdutos.cadastrarNovoProduto(vinho);
        System.out.println(cadastroProdutos.totalClientes());
        System.out.println(cadastroProdutos.procuraPorCodigo(101));
        System.out.println(cadastroProdutos.procuraPorCodigo(202));
        System.out.println(cadastroProdutos.removerPorCodigo(102));
        System.out.println(cadastroProdutos.totalClientes());
        System.out.println(cadastroProdutos.procuraPorCodigo(102));

        ItemVenda itemSmartphone = new ItemVenda(smartphone, 1);
        ItemVenda itemCerveja = new ItemVenda(cerveja, 2);
        ItemVenda itemVinho = new ItemVenda(vinho, 3);
        System.out.println(itemSmartphone.valorUnitario());
        System.out.println(itemSmartphone.valorTotal());
        System.out.println(itemSmartphone.getDetalhes());
        System.out.println(itemCerveja.valorUnitario());
        System.out.println(itemCerveja.valorTotal());
        System.out.println(itemCerveja.getDetalhes());
        System.out.println(itemVinho.valorUnitario());
        System.out.println(itemVinho.valorTotal());
        System.out.println(itemVinho.getDetalhes());

        Venda vendaCartao = new Venda(clientePF);
        vendaCartao.inserirProduto(itemSmartphone);
        vendaCartao.inserirProduto(2, cerveja);
        vendaCartao.inserirProduto(vinho);
        System.out.println(vendaCartao.dataCriacao());
        System.out.println(vendaCartao.verificarGarantia(smartphone));
        System.out.println(vendaCartao.verificarGarantia(notebook));
        System.out.println(vendaCartao.valorTotal());

        Pagamento pagamentoCartao = new PagamentoCartao(vendaCartao.valorTotal(), "1234123412341234", 4);
        System.out.println(pagamentoCartao.getValor());
        System.out.println(pagamentoCartao.processar());
        System.out.println(pagamentoCartao.getValor());
        System.out.println(pagamentoCartao.toString());
        vendaCartao.definirMetodoPagamento(pagamentoCartao);
        System.out.println(vendaCartao.emitirNotaFiscal().montarNotaFiscal());

        Venda vendaPix = new Venda(clientePJ);
        vendaPix.inserirProduto(1, notebook);
        vendaPix.inserirProduto(2, vinho);
        System.out.println(vendaPix.valorTotal());

        Pagamento pagamentoPix = new PagamentoPIX(vendaPix.valorTotal(), "chave-pix-exemplo@teste.com");
        System.out.println(pagamentoPix.getValor());
        System.out.println(pagamentoPix.processar());
        System.out.println(pagamentoPix.getValor());
        System.out.println(pagamentoPix.toString());
        vendaPix.definirMetodoPagamento(pagamentoPix);
        System.out.println(vendaPix.emitirNotaFiscal().montarNotaFiscal());

        Venda vendaBoleto = new Venda(clientePF);
        vendaBoleto.inserirProduto(3, smartphone);
        vendaBoleto.inserirProduto(itemCerveja);
        System.out.println(vendaBoleto.valorTotal());

        Pagamento pagamentoBoleto = new PagamentoBoleto(vendaBoleto.valorTotal(),
                "34191.79001 01043.513184 91020.150008 7 900000000000");
        System.out.println(pagamentoBoleto.getValor());
        System.out.println(pagamentoBoleto.processar());
        System.out.println(pagamentoBoleto.getValor());
        System.out.println(pagamentoBoleto.toString());
        vendaBoleto.definirMetodoPagamento(pagamentoBoleto);
        System.out.println(vendaBoleto.emitirNotaFiscal().montarNotaFiscal());

        try {
            new ClientePF(0, null, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            new ProdutoEE(0, null, -1.0, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            new ItemVenda(null, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            new PagamentoCartao(0.0, "1234", 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            new PagamentoBoleto(10.0, "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            vendaCartao.definirMetodoPagamento(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            new Venda(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
