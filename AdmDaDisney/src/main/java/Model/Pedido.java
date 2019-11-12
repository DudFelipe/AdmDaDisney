/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author eduar
 */
public class Pedido {
    private int id;
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    private String departamento;
    private String descricao;
    private String justificativa;
    private int aprovacao;
    
    public Pedido(){
        
    }
    
    public Pedido(int id, String titulo, String departamento, String descricao, String justificativa, int aprovacao){
        this.id = id;
        this.departamento = departamento;
        this.descricao = descricao;
        this.justificativa = justificativa;
        this.aprovacao = aprovacao;
        this.titulo = titulo;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public int getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(int aprovacao) {
        this.aprovacao = aprovacao;
    }
    
    
}
