package br.com.appsociotorcedor;

public class Socio {

    private int matricula;
    private String nome;
    private String email;
    private String dataNasc;
    private Modalidade modalidade;

    public Socio() {
    }

    public Socio(String nome, String email, String dataNasc, Modalidade modalidade) {
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
        this.modalidade = modalidade;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public String toString() {
        return  nome + " \n " +
                email + " \n " +
                dataNasc + " \n " +
                modalidade ;
    }
}
