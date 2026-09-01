package br.pucrs.poo;

/**
 * Prod. Alcóolico - This class is a part of my OOP Java documentation
 * (practicing inheritance & polymorphism)
 *
 * @author Gabriel M. Rostirolla
 */
public class ProdutoAlcoolico extends Produto {
    private double impostoAlcool;

    public ProdutoAlcoolico(int codigoProduto, String nomeProduto, double precoProduto) {
        super(codigoProduto, nomeProduto, precoProduto);
        this.impostoAlcool = 1.0;
    }

    public double getTarifa() {
        return impostoAlcool;
    }

    public void setTarifa(double tarifa) {
        if (tarifa > 5.0 || tarifa < 0.0) {
            throw new ExceptionInInitializerError("Valor de tarifa inválido");
        } else {
            impostoAlcool = tarifa;
        }
    }

    @Override
    public double getPreco() {
        return super.getPreco() + (impostoAlcool * super.getPreco());
    }
}
