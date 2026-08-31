package br.pucrs.poo;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("=== SIMULAÇÃO DE SISTEMA DE VENDAS INICIADA ===\n");

        // 1. Instanciando os objetos básicos (Cliente e Produtos)
        Cliente cliente = new ClientePF("123", "Gabriel Rostirolla", "06156205071");

        // Criando um Eletrônico com 90 dias de garantia
        ProdutoEE smartphone = new ProdutoEE(101, "Smartphone", 3000.0, 90);

        // Criando um Produto Genérico / Alcoólico para compor o carrinho
        // (Ajuste o construtor conforme a assinatura real da sua classe Produto)
        Produto cerveja = new Produto(202, "Cerveja", 15.0) {
        };

        // 2. Criando a Venda (Fluxo Realista: começa apenas com o cliente)
        Venda venda = new Venda(cliente);

        // 3. Inserindo os itens no carrinho
        venda.inserirProduto(1, smartphone); // R$ 3000.0
        venda.inserirProduto(2, cerveja); // R$ 30.0 (15.0 * 2)

        System.out.println("-> Produtos inseridos com sucesso.");
        System.out.println("-> Valor Bruto do Carrinho: R$ " + venda.valorTotal() + "\n");

        // 4. Testando a Lógica de Garantia do Eletrônico
        System.out.println("=== TESTE DE GARANTIA ===");
        System.out.println("Data da Compra: " + venda.dataCriacao());
        System.out.println("Vencimento da Garantia: " + smartphone.getValidade(venda.dataCriacao()));
        System.out.println("Status atual da garantia: " + venda.verificarGarantia(smartphone));
        System.out.println("-------------------------------------------------\n");

        // 5. Testando os Fluxos de Pagamento (Escolha UM cenário descomentando as
        // linhas)

        System.out.println("=== PROCESSANDO O PAGAMENTO ===");

        // CENÁRIO A: Testando pagamento via PIX (Espera-se 10% de desconto)
        double valorBruto = venda.valorTotal();
        Pagamento formaPagamento = new PagamentoCartao(valorBruto, "1234123412341234", 3);

        // CENÁRIO B: Testando pagamento via CARTÃO (Descomente para testar)
        // 4 parcelas aplica 5% de juros. 16 dígitos simulando o mascaramento.
        // Pagamento formaPagamento = new PagamentoCartao(valorBruto,
        // "1234567890123456", 4);

        // CENÁRIO C: Testando pagamento via BOLETO (Descomente para testar)
        // Pagamento formaPagamento = new PagamentoBoleto(valorBruto, "34191.79001
        // 01043.513184 91020.150008 7 900000000000");

        // Executando o processamento do pagamento
        if (formaPagamento.processar()) {
            System.out.println("-> Pagamento PROCESSADO com sucesso!");

            // Injetando o pagamento já processado de volta na venda
            venda.definirMetodoPagamento(formaPagamento);

            // 6. Emitindo a Nota Fiscal Final
            System.out.println("\n=== EMISSÃO DE NOTA FISCAL ===");
            System.out.println(venda.getNotaFiscal());

        } else {
            System.out.println("⚠️ Falha grave: O pagamento foi recusado!");
        }
    }
}
