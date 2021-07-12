package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {
    private String  nomeVenda;
    private int quantidadeVenda;
    private double valorUnitario, valorTotal;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public Date getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    private Date dataVenda;
    
    public double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public String getNomeVenda() {
        return nomeVenda;
    }
    public void setNomeVenda(String nomeVenda) {
        this.nomeVenda = nomeVenda;
    }
    public int getQuantidadeVenda() {
        return quantidadeVenda;
    }
    public void setQuantidadeVenda(int quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    @Override
    public String toString() {
        return String.format("Data: " + formato.format(getDataVenda()) + " - Produto: " + getNomeVenda() + 
        " - Quantidade: " + getQuantidadeVenda() + " - Valor unitário R$: " + getValorUnitario() + 
        " - Valor total R$: " + getValorTotal() + "\n");
    }
}
