package com.kartoffelkopf.crm.controller;

import com.kartoffelkopf.crm.Main;
import com.kartoffelkopf.crm.data.CustomerDao;
import com.kartoffelkopf.crm.model.Customer;
import com.kartoffelkopf.crm.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable{

    public Button addButton;
    public Button deleteButton;
    public TextArea detailsTextArea;
    public Button editButton;

    CustomerService customerService = new CustomerService();

    @FXML
    public ListView customerListView;

    private ObservableList<String> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerListView.setItems(customerService.getListView());
    }

    public void handle_addButton_onMouseReleased(MouseEvent mouseEvent) {

        Stage stage = new Stage();
        Parent subRoot = null;
        try {
            subRoot = FXMLLoader.load(getClass().getResource("/fxml/addCustomer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(subRoot, 600, 100);

        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.showAndWait();
        customerListView.setItems(customerService.getListView());
    }

    public void handle_deleteButton_onMouseReleased(MouseEvent mouseEvent) {
        if (customerListView.getSelectionModel().getSelectedItem() != null) {

            Customer customer = customerService.findByName(customerListView.getSelectionModel().getSelectedItem().toString());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Deleting " + customer.getName());
            alert.setContentText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                customerService.delete(customer);
            } else {

            }
            customerListView.setItems(customerService.getListView());
        }
    }

    public void handle_customerList_onMouseReleased(MouseEvent mouseEvent) {

        Customer customer = customerService.findByName(customerListView.getSelectionModel().getSelectedItem().toString());

        detailsTextArea.setText(customerService.getDetailsView(customer));
    }

    public void handle_editButton_onMouseReleased(MouseEvent mouseEvent) throws IOException {

        Customer customer = customerService.findByName(customerListView.getSelectionModel().getSelectedItem().toString());

        Stage stage = new Stage();
        Parent subRoot = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/editCustomer.fxml"));
        subRoot = fxmlLoader.load();

        EditCustomerController editCustomerController = fxmlLoader.<EditCustomerController>getController();
        editCustomerController.setCustomer(customer);

        Scene scene = new Scene(subRoot, 600, 100);

        stage.setTitle("Edit Customer");
        stage.setScene(scene);
        stage.showAndWait();
        customerListView.setItems(customerService.getListView());
        detailsTextArea.setText(customerService.getDetailsView(customer));

    }
}