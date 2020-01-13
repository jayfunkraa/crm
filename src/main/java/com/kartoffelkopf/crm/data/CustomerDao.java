package com.kartoffelkopf.crm.data;

import com.kartoffelkopf.crm.config.HibernateUtil;
import com.kartoffelkopf.crm.model.Customer;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerDao {

    public List<Customer> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("name")));
        List<Customer> customers = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return customers;
    }

    public Customer findById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    public void save(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(customer);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }


}
