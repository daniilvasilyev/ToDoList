package tasks;

import contacts.Contact;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Задача: выполяется за один раз
 */
@Entity
@Table(name = "Task")
@NamedQueries(
        {@NamedQuery(name="Task.getAll", query = "SELECT task from Task task"),
        @NamedQuery(name="Task.getAllByContact", query="SELECT OBJECT(task) FROM Task task WHERE task.contact = :contact_id")})
public class Task {

    public enum TaskPriority {
        HIGH("Высокий"),
        MEDIUM("Нормальный"),
        LOW("Низкий");
        private String title;
        TaskPriority(String title) {
            this.title = title;
        }
        @Override
        public String toString() { return title; }
    };


    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    /**
     * id задачи
     */
    @Column(name = "id")
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Название задачи
     */
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    public Task() {}

    public Long getId() { return id; }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return name; }
    public void setContact( Contact contact )  { this.contact = contact; }
    public Contact getContact() { return contact; }
    public TaskPriority getPriority()   { return priority; }
    public void setPriority( TaskPriority priority ) { this.priority = priority; }

    @Override
    public String toString() {
        return "Task{ id = " + id + ", name = " + name
                + ", contact = " + contact.getName() + ", contact_id = " + contact.getId() +
                ", priority = " + priority + "}";
    }
}
