package classes;

public class Produto {
    private int codigo, quantidade;
    private String nome;
    private double valor;
    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("Código: " + getCodigo() + " - Nome: " + getNome() + 
        " - Quantidade: " + getQuantidade() + " - Valor: " + getValor());  
        }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Produto)) {
            return false;
        }
        final Produto other = (Produto) obj;
        return this.getNome().equals(other.getNome());
    }
}
