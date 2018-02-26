package br.com.testetecnospeed.adicionavaga.entidade;

import java.io.Serializable;

/**
 * Created by popovicz on 26/02/2018.
 */

public class Vaga implements Serializable {

    private int id;
    private String titulo;
    private String setor;
    private String cargo;
    private String atividade;
    private String requisito;
    private String escolaridade;
    private boolean statusRegistro;

    public Vaga() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public boolean isStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(boolean statusRegistro) {
        this.statusRegistro = statusRegistro;
    }
}
