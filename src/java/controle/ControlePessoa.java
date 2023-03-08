/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import modelo.Pessoa;
import util.Conexao;

/**
 *
 * @author site2019
 */
@ManagedBean
@ViewScoped
public class ControlePessoa {
    private Pessoa pessoa;
    private List<Pessoa> listagem;
    
    public ControlePessoa(){
        pessoa = new Pessoa();
        listagem = new ArrayList<Pessoa>();
        buscarTodos();
    }
    
    

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public List<Pessoa> getListagem() {
        return listagem;
    }

    public void setListagem(List<Pessoa> listagem) {
        this.listagem = listagem;
    }
    
    public void buscarTodos(){
        EntityManager em = Conexao.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        //Query query = em.createQuery("");
        String consulta = "SELECT p from Pessoa p";
        TypedQuery<Pessoa> queryTipada = em.createQuery(consulta,Pessoa.class);
        this.listagem = queryTipada.getResultList();
        //NamedQuery queryNomeada = (NamedQuery) em.createNamedQuery("");
    }
    
    public void adicionar(){

        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage("Sucesso",this.pessoa.getNome()+ " cadastrado com sucesso");
        contexto.addMessage(null, mensagem);
        
        
        EntityManager em = Conexao.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(pessoa);
            tx.commit();
        }
        catch(Throwable t){
            tx.rollback();
            t.printStackTrace();
        }
        pessoa = new Pessoa();
        buscarTodos();

    }
    public void excluir(int id){
        EntityManager em = Conexao.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        pessoa = em.find(Pessoa.class, id);
        try{
            tx.begin();
            em.remove(pessoa);
            tx.commit();
        }
        catch(Throwable t){
            tx.rollback();
            t.printStackTrace();
        }
        pessoa = new Pessoa();
        buscarTodos();

    }
    public void carregar(int id){
    EntityManager em = Conexao.getEntityManager();
    pessoa = em.find(Pessoa.class, id);
    }
}
