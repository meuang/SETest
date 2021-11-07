package Condos.controller;

import Condos.Config.ComponentConfig;
import Condos.models.Employee;
import Condos.services.APIService;
import Condos.services.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class registerEmployeeController {
    @FXML
    private TextField name,username;

    @FXML
    private PasswordField password,ConfirmPassword;

    @FXML
    private Label error;

    @FXML
    private Button closeButton;

    private Employee employees;
    private EmployeeService employeeService;
    private APIService apiService;


    public void initialize(){
        employees = new Employee();
        employeeService =  new EmployeeService();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        apiService = context.getBean(APIService.class);

    }

    public void closeButtonAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        if (username.getText().isEmpty() || password.getText().isEmpty() || name.getText().isEmpty() || ConfirmPassword.getText().isEmpty()){
            error.setText("กรุณากรอกข้อมูลให้ครบ");
            name.clear();
            username.clear();
            password.clear();
            ConfirmPassword.clear();
        }else {
            if (password.getText().equals(ConfirmPassword.getText())){

                if (employeeService.checkUsername(username.getText())){
                    employees.setNameM(name.getText());
                    employees.setUsernameM(username.getText());
                    employees.setPasswordM(password.getText());
                    apiService.addE(employees);

                    error.setText("บันทึกข้อมูลสำเร็จ");
                }else {
                    error.setText("username นี้มีแล้ว");
                    name.clear();
                    username.clear();
                    password.clear();
                    ConfirmPassword.clear();
                }

            }else {
                error.setText("password ไม่ตรงกัน");
                name.clear();
                username.clear();
                password.clear();
                ConfirmPassword.clear();
            }
        }


    }

}
