package Condos.controller;

import Condos.Config.ComponentConfig;
import Condos.models.Employee;
import Condos.models.POModel;
import Condos.services.APIService;
import Condos.services.DataListSourceBox;
import Condos.services.EmployeeList;
import Condos.services.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class homeEmployeeController implements Initializable {

    @FXML
    private Button addEmployee,confirmState;

    @FXML
    private TextField serach;

    @FXML
    private TableView<POModel> POTable,testTable,table;

    @FXML
    private TableColumn<POModel, Integer> id_po,quantity,tp;

    @FXML private TableColumn<POModel, String> name,pn,phone,address,status,date;

    @FXML private Label error,test,nameTestText,addressTest,phoneText,dateText,statesText;

    @FXML private ChoiceBox<String> choiceBoxState;

    private String[] state = {"กำลังผลิต", "กำลังจัดส่ง","ส่งสินค้าแล้ว"};


    private POModel poModel;


    private ObservableList<POModel> dataObservableList;
    private DataListSourceBox dataSource;
    private EmployeeService employeeService;
    private EmployeeList BoxLists;
    private Employee employee;
    private APIService apiService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        choiceBoxState.getItems().addAll(state);
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        apiService = context.getBean(APIService.class);
        updateTable();


    }

    public void updateTable(){
        error.setText("");
        ObservableList<POModel> poModelObservableList = FXCollections.observableArrayList();
        poModel = new POModel();
        nameTestText.setText("");
        addressTest.setText("");
        phoneText.setText("");
        dateText.setText("");
        statesText.setText("");
        choiceBoxState.setValue("กำลังผลิต");

        try {

            for (POModel p: apiService.getAll()) {
                Integer queryPOID = p.getId_PO();
                String queryPOName = p.getName_PO();
                String queryPOPN = p.getPn_PO();
                Integer queryPOQuantity = p.getQuantity_PO();
                float queryPOTP = p.getTotal_price_PO();
                String queryPOPhone = p.getPhone_PO();
                String queryPOAddress = p.getAddress_PO();
                String queryPODate = p.getDate_PO();
                String queryPOStatus = p.getStatus_PO();
                String queryPOEmail = p.getEmail_PO();
                String queryPOEvidence = p.getEvidence_PO();

                poModelObservableList.add(new POModel(queryPOID,queryPOName,queryPOPhone,queryPOEmail,queryPOAddress,queryPOPN,queryPOQuantity,queryPOTP,queryPOEvidence,queryPODate,queryPOStatus));
            }

            }catch (IllegalArgumentException e){
            e.getStackTrace();
        }

            id_po.setCellValueFactory(new PropertyValueFactory<>("id_PO"));
            name.setCellValueFactory(new PropertyValueFactory<>("name_PO"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone_PO"));
            address.setCellValueFactory(new PropertyValueFactory<>("address_PO"));
            pn.setCellValueFactory(new PropertyValueFactory<>("pn_PO"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity_PO"));
            tp.setCellValueFactory(new PropertyValueFactory<>("total_price_PO"));
            date.setCellValueFactory(new PropertyValueFactory<>("date_PO"));
            status.setCellValueFactory(new PropertyValueFactory<>("status_PO"));

            POTable.setItems(poModelObservableList);

            POTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    showSelectedMemberCard(newValue);
                }
            });


    }

    private void showSelectedMemberCard(POModel card) {

        error.setText("");
        nameTestText.setText(card.getName_PO());
        addressTest.setText(card.getAddress_PO());
        phoneText.setText(card.getPhone_PO());

        dateText.setText(card.getDate_PO());
        statesText.setText(card.getStatus_PO());

        if (card.getStatus_PO().equals("ส่งสินค้าแล้ว")){
            choiceBoxState.setDisable(true);
            confirmState.setDisable(true);
        }else {
            choiceBoxState.setDisable(false);
            confirmState.setDisable(false);
        }
        confirmState.setOnAction(e -> getChoices(choiceBoxState,card.getId_PO(),card.getStatus_PO()));


    }


        public void getChoices(ChoiceBox<String> choiceBox,int id,String statusText){
            String po = poModel.getStatus_PO();
        String status = choiceBox.getValue();
        if (statusText.equals("ยืนยันการสั่งซื้อ")){
            if (status.equals("กำลังจัดส่ง") || status.equals("ส่งสินค้าแล้ว")){
                error.setText("สถานนะไม่ถูกต้อง");

            }else {

                poModel.setStatus_PO(status);
                apiService.updatePO(id,poModel);

                updateTable();
            }
        }
        else if (statusText.equals("กำลังจัดส่ง")){
            if (status.equals("กำลังผลิต")){

                error.setText("สถานนะไม่ถูกต้อง");
            }else {
                poModel.setStatus_PO(status);
                apiService.updatePO(id,poModel);
                updateTable();
            }
        }
        else if(statusText.equals("กำลังผลิต")){

            if (status.equals("ส่งสินค้าแล้ว")){
                error.setText("สถานนะไม่ถูกต้อง");
            }
            else{
                poModel.setStatus_PO(status);
                apiService.updatePO(id,poModel);
                updateTable();
            }


        }
        }

    @FXML
    public void goPOButton(ActionEvent event) throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/POpage.fxml"));
        stage.setScene(new Scene(loader.load(),1080,600));

        stage.show();
    }

    @FXML
    public void addProductButtonOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/product-view.fxml"));
        stage.setScene(new Scene(loader.load(),1080,680));

        stage.show();
    }

    @FXML
    public void addEmployeeButtonOnAction() {
        createAccountForm();
    }

    public void createAccountForm(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ui/registerEmployee.fxml"));
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root,650,650));
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();;
            e.getCause();
        }
    }
    public void logOutButtonOnAction(ActionEvent event) throws IOException {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        stage.show();

    }
}
