package model;

public class Compromisso {
    private String titulo;
    private String data;
    private String descricao;

    public Compromisso(String titulo, String data, String descricao) {
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
    }

    public String toFileString() {
        return titulo + ";" + data + ";" + descricao;
    }

    public static Compromisso fromFileString(String linha) {
        String[] partes = linha.split(";");
        return new Compromisso(partes[0], partes[1], partes[2]);
    }

    // getters
    public String getTitulo() { return titulo; }
    public String getData() { return data; }
    public String getDescricao() { return descricao; }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}