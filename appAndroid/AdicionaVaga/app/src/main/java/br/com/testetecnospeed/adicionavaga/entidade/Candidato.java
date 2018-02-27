package br.com.testetecnospeed.adicionavaga.entidade;

import java.io.Serializable;

/**
 * Created by popovicz on 26/02/2018.
 */

public class Candidato implements Serializable {

    private int id;
    private int idVaga;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String objetivo;
    private String formacaoAcademica;
    private String experienciaProfissional;
    private String informacaoAdicional;
    private boolean status_registro;

    public Candidato() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(String formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public String getExperienciaProfissional() {
        return experienciaProfissional;
    }

    public void setExperienciaProfissional(String experienciaProfissional) {
        this.experienciaProfissional = experienciaProfissional;
    }

    public String getInformacaoAdicional() {
        return informacaoAdicional;
    }

    public void setInformacaoAdicional(String informacaoAdicional) {
        this.informacaoAdicional = informacaoAdicional;
    }

    public boolean isStatus_registro() {
        return status_registro;
    }

    public void setStatus_registro(boolean status_registro) {
        this.status_registro = status_registro;
    }
}
