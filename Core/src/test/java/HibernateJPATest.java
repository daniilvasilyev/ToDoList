/**
 * Created by Admin on 02.07.2015.
 */
import contacts.Contact;
import contacts.ContactService;
import tasks.Task;
import tasks.TaskService;

import org.junit.Test;
import java.util.List;

public class HibernateJPATest {

    ContactService contactService = new ContactService();
    TaskService taskService = new TaskService();

    @Test
    public void testSaveRecord() throws Exception {
        //Создаем контакт
        Contact contact = new Contact();
        contact.setName("name1");
        contact.setSurname("surname1");
        //Записали в БД
        Contact contact_db = contactService.add(contact);
        //Вывели записанную в БД запись
        System.out.println(contact_db);

        //Создаем задачу
        Task task = new Task();
        task.setName("task1");
        task.setContact( contact_db );
        task.setPriority(Task.TaskPriority.MEDIUM );
        //Записали в БД
        Task task_db = taskService.add(task);
        //Вывели записанную в БД запись
        System.out.println(task_db);
    }

    @Test
    public void testDeleteRecord() throws Exception {
        //Создаем
        Contact contact = new Contact();
        contact.setName("name2");
        contact.setSurname("surname2");

        //Записуем его в БД
        Contact contact1 = contactService.add(contact);

        System.out.println(contact1);

        //Удвлем его с БД
        contactService.delete(contact1.getId());

    }

    @Test
    public void testSelect() throws Exception {
        //Создаем
        Contact contact = new Contact();
        contact.setName("name3");
        contact.setSurname("surname3");

        //Записываем в БД
       Contact contact1 = contactService.add(contact);

        //Получние с БД
        Contact contact2 = contactService.get(contact1.getId());
        System.out.println(contact2);
    }

    @Test
    public void testUpdate() throws Exception {
        //Создаем
        Contact contact = new Contact();
        contact.setName("name4");
        contact.setSurname("surname4");

        //Записываем в БД
        contact = contactService.add(contact);

        contact.setName("name44");

        //Обновляем
        contactService.update(contact);

        //Получаем обновленую запись
        Contact contact1 = contactService.get(contact.getId());
        System.out.println(contact1);
    }

    @Test
    public void testGetAll(){

        //Получаем все с БД
        List<Contact> contacts = contactService.getAll();
        //Выводим полученый список
        contacts.forEach(System.out::println);

        List<Task> tasks = taskService.getAll();
        tasks.forEach( System.out::println );
    }

    @Test
    public void testGetAllTaskByContact() {

        List<Contact> contacts = contactService.getAll();
        //Выводим полученый список
        contacts.forEach(System.out::println);

        List<Task> tasks = taskService.getAllByContact( contacts.get(0) );
        tasks.forEach(System.out::println);
    }


}
