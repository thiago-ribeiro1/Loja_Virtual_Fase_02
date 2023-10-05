package application;

public class Vendedor {
   private String nome;
   private String Cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public Vendedor(String nome, String cpf) {
        this.nome = nome;
        Cpf = cpf;
    }

}
