package tasks;

/**
 * Created by Admin on 02.07.2015.
 */
import contacts.Contact;
import tasks.Task;

import javax.persistence.*;


import java.util.List;

//@Repository
//@Transactional(readOnly = true)
public class TaskService {

    //@PersistenceContext
    private EntityManager em = Persistence.createEntityManagerFactory("levelp").createEntityManager();

    //@Transactional
    public Task add(Task obj){
        em.getTransaction().begin();
        Task obj_db = em.merge(obj);
        em.getTransaction().commit();
        return obj_db;
    }

    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Task get(long id){
        return em.find(Task.class, id);
    }

    public void update(Task obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Task> getAll(){
        TypedQuery<Task> namedQuery = em.createNamedQuery("Task.getAll", Task.class);
        return namedQuery.getResultList();
    }

    public  List<Task> getAllByContact( Contact contact ) {
        TypedQuery<Task> namedQuery = em.createNamedQuery("Task.getAllByContact", Task.class);
        namedQuery.setParameter( "contact_id", contact );
        return namedQuery.getResultList();
    }
}