package br.pucrs.poo;

/**
 * Prod. Alcóolico - This class is a part of my OOP Java documentation
 * (practicing inheritance & polymorphism)
 *
 * @author Gabriel M. Rostirolla
 */
public class ProdutoAlcoolico extends Produto {
    // (indo de 0.0 - 1.0)
    private static double tarifaAlcool;

    public ProdutoAlcoolico(int codigoProduto, String nomeProduto, double precoProduto) {
        super(codigoProduto, nomeProduto, precoProduto);
        if (getNome().toLowerCase().contains("corote")) {
            tarifaAlcool = 4;
        }
    }

    public double getTarifa() {
        return tarifaAlcool;
    }

    public void serTarifa(double tarifa) {
        if (tarifa > 1.0 || tarifa < 0.0) {
            throw new ExceptionInInitializerError("Valor de tarifa inválido");
        } else {
            tarifaAlcool = tarifa;
        }
    }

    @Override
    public double getPreco() {
        return (tarifaAlcool * super.getPreco());
    }
}
