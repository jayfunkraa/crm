package com.kartoffelkopf.crm.controller;

import com.kartoffelkopf.crm.model.Customer;
import com.kartoffelkopf.crm.service.CustomerService;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerController implements EditWindowController {

    public TextField nameTextField;
    public Button saveButton;
    public Button cancelButton;

    private Customer selectedCustomer;

    private CustomerService customerService = new CustomerService();

    public void setObject(Object customer) {
        this.selectedCustomer = (Customer) customer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            this.nameTextField.setText(selectedCustomer.getName());
        });

    }

    public void handle_saveButton_onMouseReleased(MouseEvent mouseEvent) {

        selectedCustomer.setName(nameTextField.getText());

        customerService.save(selectedCustomer);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

    public void handle_cancelButton_onMouseReleased(MouseEvent mouseEvent) {

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }
}
