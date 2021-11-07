package Condos.controller;

import Condos.Config.ComponentConfig;
import Condos.models.POModel;
import Condos.models.Product;
import Condos.services.APIService;
import Condos.services.POService;
import Condos.services.ProductService;
import javafx.fxml.Initializable;

//package Condos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class POController implements Initializable {

    @FXML
    private Label total_price_text,quantityText,nameText,evidenceTexT,error,summaryShowNameLabel,summaryShowPhoneLabel,summaryShowMailLabel;

    @FXML
    private Button backButton,addProductButton,cancelButton,list_summary_Button,evidenceButton,confirmButton,summaryB;

    @FXML
    private ChoiceBox<String> ChoiceProductName;

    @FXML
    private TextField quantity,name,phone,email;

    @FXML
    private TextArea address;

    @FXML
    private ImageView productView,evidenceView;

    private POModel poModel;

    private float tps;

    private Product product;

    @FXML
    private Text summaryShowAddressText;

    private APIService apiService;
    private POService poService;
    private ProductService productService;


    public void initialize(URL url, ResourceBundle resourceBundle){
        poModel = new POModel();
        poService = new POService();

        confirmButton.setDisable(true);
        evidenceButton.setDisable(true);
        confirmButton.setDisable(true);
        evidenceButton.setDisable(true);
        poModel.setEvidence_PO("");
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        apiService = context.getBean(APIService.class);

        for (Product p : apiService.getP()){
            ChoiceProductName.getItems().addAll(p.getName_P());
            ChoiceProductName.setOnAction(this::getProductName);
        }


    }

    private void clearDetail(){

        poModel = new POModel();
        confirmButton.setDisable(true);
        evidenceButton.setDisable(true);
        quantity.clear();
        name.clear();
        phone.clear();
        email.clear();
        address.clear();
        total_price_text.setText("");
        quantityText.setText("");
        nameText.setText("");
        error.setText("");
        poModel.setEvidence_PO("");

        summaryShowNameLabel.setText("");
        summaryShowPhoneLabel.setText("");
        summaryShowMailLabel.setText("");
        summaryShowAddressText.setText("");
        productView.setVisible(false);
        evidenceView.setVisible(false);
        evidenceButton.setDisable(true);
        ChoiceProductName.setDisable(false);

    }

    public void getProductName(ActionEvent event){
        product = new Product();
        String productName = ChoiceProductName.getValue();
        poModel.setPn_PO(productName);
        productView.setVisible(true);
        evidenceView.setVisible(false);
        error.setText("");
        for (Product p : apiService.getP()){
            File brandingFile = new File("image/"+p.getImage_P());
            Image brandingImage = new Image(brandingFile.toURI().toString());
            productView.setImage(brandingImage);
        }
    }

    @FXML public void cancelButtonOnAction(ActionEvent event){
        clearDetail();
        error.setText("ยกเลิกการสั่งซื้อ");

    }

    public void nextButtonOnAction(ActionEvent event)throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/employee/homeEmployee.fxml"));
        stage.setScene(new Scene(loader.load(), 1080, 600));
        stage.setTitle("MicrochipStarApp!");
        stage.show();

    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    public void buyProductButtonOnAction(ActionEvent event)throws IOException {
         setDetail();
        if (name.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty()
                 ||poModel.getPn_PO()==null|| poModel.getEvidence_PO().isEmpty()){
            error.setText("กรุณากรอกข้อมูลให้ครบ");
        }else if (!isNumeric(quantity.getText())){
            error.setText("กรุณากรอกข้อมูลให้ถูกต้อง");
        }else {
            if (poService.checkProduct(Integer.parseInt(quantity.getText()),poModel.getPn_PO())){
            Date date = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String time = timeFormat.format(date);
            String dates = dateFormat.format(date);
            String dateAndTime = dates + "-" + time;
            poModel.setName_PO(name.getText());
            poModel.setDate_PO(dateAndTime);
            poModel.setAddress_PO(address.getText());
            poModel.setEmail_PO(email.getText());
            poModel.setPhone_PO(phone.getText());
            poModel.setQuantity_PO(Integer.parseInt(quantity.getText()));
            poModel.setPn_PO(nameText.getText());
            poModel.setStatus_PO("ยืนยันการสั่งซื้อ");
            apiService.addPO(poModel);

            for (Product p : apiService.getP()){
                if (poModel.getPn_PO().equals(p.getName_P())){
                    if (poModel.getQuantity_PO() < p.getQuantity_P()){
                        int q = p.getQuantity_P() - poModel.getQuantity_PO();
                        p.setQuantity_P(q);
                        apiService.updateProduct(p);
                    }
                }

            }
                clearDetail();
                error.setText("ยืนยันการสั่งซื้อ");


        }else {
                error.setText("สินค้าไม่พอ");

            }
        }

    }
    @FXML public void handleUploadButton(ActionEvent event){

        FileChooser chooser = new FileChooser();

        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        System.out.println(chooser);

        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {

                File destDir = new File("evidence");
                System.out.println(destDir);
                if (!destDir.exists()) destDir.mkdirs();

                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                System.out.println(filename);
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename

                );
                System.out.println(target);

                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );

                evidenceView.setImage(new Image(target.toUri().toString()));
                poModel.setEvidence_PO(destDir + "/" + filename);
                confirmButton.setDisable(false);
                productView.setVisible(false);
                evidenceView.setVisible(true);
                ChoiceProductName.setDisable(true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private boolean setDetail() {
        if (name.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty()
                ||quantity.getText().equals("")||poModel.getPn_PO()==null) {
            error.setText("กรุณากรอกข้อมูลให้ครบ");
            return false;
        } else if (!isNumeric(quantity.getText())) {
            error.setText("กรุณากรอกข้อมูลให้ถูกต้อง");
            return false;
        } else {
            if (poService.checkProduct(Integer.parseInt(quantity.getText()),poModel.getPn_PO())){
            nameText.setText(poModel.getPn_PO());
            summaryShowAddressText.setText(address.getText());
            summaryShowMailLabel.setText(email.getText());
            summaryShowNameLabel.setText(name.getText());
            summaryShowPhoneLabel.setText(phone.getText());
            quantityText.setText(quantity.getText()+" ชิ้น");
            for (Product p : apiService.getP()){
                float price = Float.parseFloat(quantity.getText());
                float priceDB = p.getPrice_P();
                float tp = price * priceDB;
                tps = tp;
                poModel.setTotal_price_PO(tp);
                total_price_text.setText(String.valueOf(tp)+" บาท");
                return true;
            }

            }else {
                error.setText("สินค้าไม่พอ");
            }
return false;
        }
    }
    @FXML private void summaryButton(ActionEvent event){
                    if(setDetail()){
                        evidenceButton.setDisable(false);
                        error.setText("");
                    }
                    else {
                        evidenceButton.setDisable(true);
                    }

        }

}
