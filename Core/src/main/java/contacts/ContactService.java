package contacts;


/**
 * Created by Admin on 02.07.2015.
 */
import contacts.Contact;

import javax.persistence.*;


import java.util.List;

public class ContactService {

    private EntityManager em = Persistence.createEntityManagerFactory("levelp").createEntityManager();

    public Contact add(Contact obj){
        em.getTransaction().begin();
        Contact obj_db = em.merge(obj);
        em.getTransaction().commit();
        return obj_db;
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Contact get(long id){
        return em.find(Contact.class, id);
    }

    public void update(Contact obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Contact> getAll(){
        TypedQuery<Contact> namedQuery = em.createNamedQuery("Contact.getAll", Contact.class);
        return namedQuery.getResultList();
    }
}