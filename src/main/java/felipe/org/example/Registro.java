package felipe.org.example;

public class Registro {

    private String nome;
    private Categoria categoria;
    private int nivel;
    private Tipo tipo;
    private int offset;
    private int numeroParametros;
    private String rotulo;
    private TabelaSimbolos tabelaSimbolos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumeroParametros() {
        return numeroParametros;
    }

    public void setNumeroParametros(int numeroParametros) {
        this.numeroParametros = numeroParametros;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public TabelaSimbolos getTabelaSimbolos() {
        return tabelaSimbolos;
    }

    public void setTabelaSimbolos(TabelaSimbolos tabelaSimbolos) {
        this.tabelaSimbolos = tabelaSimbolos;
    }

    @Override
    public String toString() {
        return "[nome: " + nome + "\ncategoria: " + categoria + "\nnivel: " + nivel + "\ntipo: " + tipo + "\noffset: " + offset + "\nnumeroParametros: " + numeroParametros + "\nrotulo: " + rotulo + "]\n\n";
    }

}