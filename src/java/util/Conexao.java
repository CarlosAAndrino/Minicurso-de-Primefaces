/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.hibernate.SessionFactory;

/**
 *
 * @author site2019
 */
public class Conexao {
    private static SessionFactory emf;
    
    public static EntityManager getEntityManager(){
        if(emf == null){
            emf = (SessionFactory) Persistence.createEntityManagerFactory("minicurso");
        }
        return emf.createEntityManager();
    }
}
