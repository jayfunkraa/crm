package com.kartoffelkopf.crm.service;

import com.kartoffelkopf.crm.data.CustomerDao;
import com.kartoffelkopf.crm.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    CustomerDao customerDao = new CustomerDao();

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public Customer findById(long id) {
        return customerDao.findById(id);
    }

    public void save(Customer customer) {
        customerDao.save(customer);
    }

    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    public ObservableList<String> getListView() {

        ObservableList<String> customers = FXCollections.observableArrayList();

        for (Customer c : findAll()) {
            customers.add(c.getName());
        }

        return customers;
    }

    public Customer findByName(String name) {

        List<Customer> customers = new ArrayList<>();

        for (Customer c : findAll()) {
            if (c.getName() == name) {
                customers.add(c);
            }
        }
        return customers.get(0);
    }

    public String getDetailsView(Customer customer) {
        String detailsView = "Name: " + customer.getName();
        return detailsView;
    }
}
