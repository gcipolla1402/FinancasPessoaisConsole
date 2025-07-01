public class Transacao {
    private String tipo;       // Receita ou Despesa
    private String descricao;
    private double valor;
    private String categoria;  // Usado apenas para despesas

    public Transacao(String tipo, String descricao, double valor, String categoria) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return tipo + " | " + descricao + " | R$" + valor + " | " + (tipo.equals("Despesa") ? categoria : "-");
    }
}