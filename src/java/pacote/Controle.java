package pacote;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Controle {
    private String nome;
    
    public Controle(){
        System.out.println("Entrou no Construtor");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
