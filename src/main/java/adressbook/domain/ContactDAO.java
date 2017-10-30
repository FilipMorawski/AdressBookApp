package adressbook.domain;

import adressbook.entities.Contact;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

@Component
public class ContactDAO {

    private Contact contact;
    private EntityManagerFactory factory;
    private EntityManager em;

    public void addContact(EntityManagerFactory entityManagerFactory, HashMap<String, String> entry) {
        factory = entityManagerFactory;
        em = factory.createEntityManager();
        contact = new Contact();
        contact.setFirstName(entry.get("firstName"));
        contact.setLastName(entry.get("lastName"));
        contact.setNickname(entry.get("nickname"));
        contact.setEmail(entry.get("email"));
        contact.setPhoneNumber(entry.get("phoneNumber"));
        contact.setStreet(entry.get("street"));
        contact.setStreetNumber(entry.get("streetNumber"));
        contact.setZipCode(entry.get("zipcode"));
        contact.setCity(entry.get("city"));
        contact.setAdditionalInfo(entry.get("info"));

        em.getTransaction().begin();
        em.persist(contact);
        em.getTransaction().commit();
        em.close();
    }

    public void removeContact(EntityManagerFactory entityManagerFactory, long id) {
        factory = entityManagerFactory;
        em = factory.createEntityManager();
        contact = em.find(adressbook.entities.Contact.class, id);

        em.getTransaction().begin();
        em.remove(contact);
        em.getTransaction().commit();
        em.close();
    }


    public List getContactList(EntityManagerFactory entityManagerFactory) {
        factory = entityManagerFactory;
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query selectAll = em.createQuery("SELECT e FROM Contact e");
        List resultList = selectAll.getResultList();
        em.getTransaction().commit();
        em.close();
        return resultList;
    }

    public void updateContact(EntityManagerFactory entityManagerFactory, long id, HashMap<String, String> textFieldsData) {
        factory = entityManagerFactory;
        em = factory.createEntityManager();

        contact = em.find(adressbook.entities.Contact.class, id);
        em.getTransaction().begin();
        contact.setFirstName(textFieldsData.get("firstName"));
        contact.setLastName(textFieldsData.get("lastName"));
        contact.setNickname(textFieldsData.get("nickname"));
        contact.setPhoneNumber(textFieldsData.get("phoneNumber"));
        contact.setEmail(textFieldsData.get("email"));
        contact.setStreet(textFieldsData.get("street"));
        contact.setStreetNumber(textFieldsData.get("streetNumber"));
        contact.setZipCode(textFieldsData.get("zipcode"));
        contact.setCity(textFieldsData.get("city"));
        contact.setAdditionalInfo(textFieldsData.get("info"));
        em.getTransaction().commit();
        em.close();

    }
}
