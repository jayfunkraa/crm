package com.kartoffelkopf.crm.controller;

import com.kartoffelkopf.crm.util.WindowFactory;
import com.kartoffelkopf.crm.model.Customer;
import com.kartoffelkopf.crm.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable{

    final static Logger log = LogManager.getLogger(HomeController.class);

    public Button addButton;
    public Button deleteButton;
    public TextArea detailsTextArea;
    public Button editButton;

    CustomerService customerService = new CustomerService();
    WindowFactory windowFactory = new WindowFactory();

    @FXML
    public ListView customerListView;

    private ObservableList<String> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerListView.setItems(customerService.getListView());
    }

    public void handle_addButton_onMouseReleased(MouseEvent mouseEvent) {

        windowFactory.getAddWindow("/fxml/addCustomer.fxml", 600, 100, "Add New Customer").showAndWait();

        customerListView.setItems(customerService.getListView());
    }

    public void handle_editButton_onMouseReleased(MouseEvent mouseEvent) {

        Customer customer = customerService.findByName(customerListView.getSelectionModel().getSelectedItem().toString());

        windowFactory.getEditWindow("/fxml/editCustomer.fxml", 600, 100, "Edit Customer", customer).showAndWait();

        customerListView.setItems(customerService.getListView());
        detailsTextArea.setText(customerService.getDetailsView(customer));
    }

    public void handle_deleteButton_onMouseReleased(MouseEvent mouseEvent) {
        if (customerListView.getSelectionModel().getSelectedItem() != null) {

            Customer customer = customerService.findByName(customerListView.getSelectionModel().getSelectedItem().toString());

            if (windowFactory.getDeleteWindow(customer, customer.getName())) {
                customerService.delete(customer);
                customerListView.setItems(customerService.getListView());
            }

            customerListView.setItems(customerService.getListView());
        }
    }

    public void handle_customerList_onMouseReleased(MouseEvent mouseEvent) {

        Customer customer = customerService.findByName(customerListView.getSelectionModel().getSelectedItem().toString());

        detailsTextArea.setText(customerService.getDetailsView(customer));
    }

}
