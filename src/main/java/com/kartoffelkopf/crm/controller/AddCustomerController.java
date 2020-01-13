package com.kartoffelkopf.crm.controller;

import com.kartoffelkopf.crm.model.Customer;
import com.kartoffelkopf.crm.service.CustomerService;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddCustomerController {

    public Button addButton;
    public Button cancelButton;
    public TextField nameTextField;

    private CustomerService customerService = new CustomerService();


    public void handle_addButton_onMouseReleased(MouseEvent mouseEvent) {

        Customer customer = new Customer();
        customer.setName(nameTextField.getText());

        customerService.save(customer);

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    public void handle_cancelButton_onMouseReleased(MouseEvent mouseEvent) {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }
}
