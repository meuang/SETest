package Condos.controller;

//package Condos.controller;


import Condos.Config.ComponentConfig;
import Condos.models.Product;
import Condos.services.APIService;
import Condos.services.ProductService;
import com.sun.javafx.logging.JFRPulseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//
public class ProductSearchController implements Initializable {


    private Product selectedProduct;
    private Product productDetail;


    @FXML
    private TableView<Product> productTableView;


    @FXML
    private TableColumn<Product, String> id_ProductTableColumn,name_ProductTableColumn,image_ProductTableColumn;
    @FXML
    private TableColumn<Product, Float> price_ProductTableColumn;
    @FXML
    private TableColumn<Product, Integer> quantity_ProductTableColumn;
    @FXML
    private TextField keywordTextField;
    @FXML
    private TextField editQuantity_Field;
    @FXML
    private Label warning,editWarning;
    @FXML
    private TextField idPro_Field, namePro_Field, pricePro_Field, quantityPro_Field,imagePro_Field;
//    @FXML
//    private Text imagePro_Text;
    @FXML
    private Button inQuantity_Button, addProduct_Button, editQuantityButton;
    @FXML
    private Button clear_Button,handleUpload;

    @FXML
    private ImageView imageView;

    private APIService apiService;
    private ProductService productService;

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        apiService = context.getBean(APIService.class);
        productService = new ProductService();
        inQuantity_Button.setDisable(true);
        editQuantityButton.setDisable(true);
        addProduct_Button.setDisable(false);
        clear_Button.setDisable(true);
        addProduct_Button.setDisable(false);
        updateTable();
        handleUpload.setDisable(false);


    }
////onaction textfield//
    private void showSelectedWork(Product product) {
        selectedProduct = product;//getproduct
        inQuantity_Button.setDisable(false);
        editQuantityButton.setDisable(false);
        addProduct_Button.setDisable(true);
        clear_Button.setDisable(false);
        editWarning.setText("");
        warning.setText("");
        imageView.setVisible(true);


        System.out.println(selectedProduct.getId_P());

        idPro_Field.setText(selectedProduct.getId_P());
//
        namePro_Field.setText(selectedProduct.getName_P());

        pricePro_Field.setText(String.format("%.2f", selectedProduct.getPrice_P()));
        quantityPro_Field.setText(String.format("%d", selectedProduct.getQuantity_P()));
//        editQuantity_Field.setText(String.format("%d", selectedProduct.getQuantity_P()));
        for (Product p : apiService.getP()){
            File brandingFile = new File("image/"+selectedProduct.getImage_P());
            Image brandingImage = new Image(brandingFile.toURI().toString());
            imageView.setImage(brandingImage);
        }


//        imagePro_Text.setText(selectedProduct.getImage_P());
//        imageView.setImage(new Image(getClass().getResource(selectedProduct.getImage_P()).toExternalForm()));
//        DatabaseConnection connectionNow = new DatabaseConnection();
//        Connection connectionDB = connectionNow.getConnection();
//
//        String connectQuery = "SELECT * FROM microchipapp.product";
//
//        try {
//            Statement statement = connectionDB.createStatement();
//            ResultSet queryOutPut = statement.executeQuery(connectQuery);
//            while (queryOutPut.next()){
//                File brandingFile = new File("image/"+selectedProduct.getImage_P());
//                Image brandingImage = new Image(brandingFile.toURI().toString());
//                imageView.setImage(brandingImage);
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }



    }
//
    public void updateTable(){
        handleUpload.setDisable(true);
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        productDetail = new Product();
//        for (Product p : apiService.getP()){
//            String queryID = p.getId_P();
//            String queryImage = p.getImage_P();
//            String queryName = p.getName_P();
//            float queryPrice = p.getPrice_P();
//            int queryQuantity = p.getQuantity_P();
//
//            // Populate the ObservableList
//            productObservableList.add(new Product(queryID, queryName, queryPrice, queryQuantity, queryImage));
//        }

//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectionDB = connectNow.getConnection();
//
//        //SQL Query - Executed in the backed database
//        String productViewQuery = "SELECT * FROM microchipapp.product";
//
        try {
            for (Product p : apiService.getP()){
                String queryID = p.getId_P();
                String queryImage = p.getImage_P();
                String queryName = p.getName_P();
                float queryPrice = p.getPrice_P();
                int queryQuantity = p.getQuantity_P();

                // Populate the ObservableList
                productObservableList.add(new Product(queryID, queryName, queryPrice, queryQuantity, queryImage));
            }

        }catch (IllegalArgumentException e){
            e.getStackTrace();
        }
//        try {
//            Statement statement = connectionDB.createStatement();
//            ResultSet queryOutput = statement.executeQuery(productViewQuery);
//
//            while (queryOutput.next()) {
//                String queryID = queryOutput.getString("id_P");
//                String queryImage = queryOutput.getString("image_P");
//                String queryName = queryOutput.getString("name_P");
//                float queryPrice = queryOutput.getFloat("price_P");
//                int queryQuantity = queryOutput.getInt("all_quantity_P");
//
//                // Populate the ObservableList
//                productObservableList.add(new Product(queryID, queryName, queryPrice, queryQuantity, queryImage));
//
//
//            }

            id_ProductTableColumn.setCellValueFactory(new PropertyValueFactory<>("id_P"));
            name_ProductTableColumn.setCellValueFactory(new PropertyValueFactory<>("name_P"));
            price_ProductTableColumn.setCellValueFactory(new PropertyValueFactory<>("price_P"));
            quantity_ProductTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity_P"));
            image_ProductTableColumn.setCellValueFactory(new PropertyValueFactory<>("image_P"));
            productTableView.setItems(productObservableList);
            productTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    showSelectedWork(newValue);
                }
            });

//            FilteredList<Product> filteredDate = new FilteredList<>(productObservableList, b -> true);
//
//            keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//                filteredDate.setPredicate(product -> {
//
//                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
//                        return true;
//                    }
//                    String searchKeyword = newValue.toLowerCase();
//                    if (product.getId_P().toLowerCase().indexOf(searchKeyword) > -1) {
//                        return true;//found a match in product
//                    } else if (product.getName_P().toLowerCase().indexOf(searchKeyword) > -1) {
//                        return true;
//                    } else if (product.getImage_P().toLowerCase().indexOf(searchKeyword) > -1) {
//                        return true;
//                    } else
//                        return false;//not found
//
//                });
//            });
//            SortedList<Product> sortedData = new SortedList<>(filteredDate);
//
//            //bind sorted result result with table view
//            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());
//
//            //apply filtered and sorted data to the table view
//            productTableView.setItems(sortedData);
//
//
//        } catch (SQLException e) {
//            Logger.getLogger(ProductSearchController.class.getName()).log(Level.SEVERE, null, e);
//            e.printStackTrace();
//        }

    }
//
//    //    public void decreaseQuantityOnAction(ActionEvent event) throws IOException {
////        DatabaseConnection connectionNow = new DatabaseConnection();
////        Connection connectDB = connectionNow.getConnection();
////
////        String idProduct = selectedProduct.getId_P();
//////        String editQuantity =  editQuantity_Field.getText();
////
////        int n = Integer.parseInt(editQuantity_Field.getText());
////        String i = String.valueOf(n - 1);
////
////        String updateField = "UPDATE microchip.product SET quantity_P = + '" + i + "' WHERE id_P = '" + idProduct + "'";
////
////        try {
////            Statement statement = connectDB.createStatement();
////            statement.executeUpdate(updateField);
////            updateTable();
////
//////            Button b = (Button) event.getSource();
//////            Stage stage = (Stage) b.getScene().getWindow();
//////
//////            FXMLLoader loader = new FXMLLoader(getClass().getResource("product-view.fxml"));
//////            stage.setScene(new Scene(loader.load(), 1080, 600));
//////            stage.setTitle("MicrochipStarApp!");
//////            stage.show();
////
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            e.getCause();
////        }
////
////
////    }
    public void increaseQuantityButtonOnAction(ActionEvent event) throws IOException {
//        DatabaseConnection connectionNow = new DatabaseConnection();
//        Connection connectDB = connectionNow.getConnection();
//        if (editQuantity_Field.getText().isEmpty()) {
//            editWarning.setText("กรุณากรอกข้อมูลในช่องว่าง");
//        } else {
//            if(productDetail.isInt(editQuantity_Field.getText())) {
//                if (Integer.parseInt(editQuantity_Field.getText()) > 0 ) {
//                    String idProduct = selectedProduct.getId_P();
//                    int n = Integer.parseInt(editQuantity_Field.getText()) + Integer.parseInt(quantityPro_Field.getText());
//                    String i = String.valueOf(n);
//                    String updateField = "UPDATE microchipapp.product SET all_quantity_P = + '" + i + "' WHERE id_P = '" + idProduct + "'";
//                    try {
//                        Statement statement = connectDB.createStatement();
//                        statement.executeUpdate(updateField);
//                        clearSelectedProduct();
//                        updateTable();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        e.getCause();
//                    }
//                }
//                else{editWarning.setText("กรุณากรอกข้อมูลให้ถูกต้อง");}
//            }else {editWarning.setText("กรุณากรอกตัวเลขให้ถูกต้อง");
//            }
//        }
//
//
//
//
//
//
    }
    public void editQuantityButtonOnAction(ActionEvent event){
//        DatabaseConnection connectionNow = new DatabaseConnection();
//        Connection connectDB = connectionNow.getConnection();
//
//        String idProduct = selectedProduct.getId_P();
//        String editQuantity = editQuantity_Field.getText();
//        if (editQuantity_Field.getText().isEmpty()) {
//            editWarning.setText("กรุณากรอกข้อมูลในช่องว่าง");
//        }
//        else {
//            if(productDetail.isInt(editQuantity_Field.getText())) {
//                if (Integer.parseInt(editQuantity_Field.getText()) >= 0 ) {
//                    String updateField = "UPDATE microchipapp.product SET all_quantity_P = + '" + editQuantity + "' WHERE id_P = '" + idProduct + "'";
//                    try {
//                        Statement statement = connectDB.createStatement();
//                        statement.executeUpdate(updateField);
//                        clearSelectedProduct();
//                        updateTable();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        e.getCause();
//                    }
//                }
//                else{editWarning.setText("กรุณากรอกข้อมูลให้ถูกต้อง");}
//            }else {editWarning.setText("กรุณากรอกตัวเลขให้ถูกต้อง");
//            }
//        }
//
//
//
    }
//
//
//
    public void addProductOnAction(ActionEvent event) throws IOException {
        if (idPro_Field.getText().isEmpty() || namePro_Field.getText().isEmpty()
                || productDetail.getImage_P() == null  || pricePro_Field.getText().isEmpty() || quantityPro_Field.getText().isEmpty()) {
            warning.setText("กรุณากรอกข้อมูลให้ครบ");
        }
        else {
            if(productService.checkProduct(idPro_Field.getText(), namePro_Field.getText(),productDetail.getImage_P())) {
                warning.setText("ข้อมูลนี้ถูกนำไปใช้แล้ว!");
            }
            else {
                    if (productService.isInt(quantityPro_Field.getText()) && productService.isFloat(pricePro_Field.getText())) {
                        if (Integer.parseInt(quantityPro_Field.getText()) >= 0 && Float.parseFloat(pricePro_Field.getText()) > 0) {
//                    System.out.println(Float.parseFloat(pricePro_Field.getText())+1);
//                    System.out.println(Integer.parseInt(quantityPro_Field.getText())+2);

//                    System.out.println("asfasf");
                            productDetail.setId_P(idPro_Field.getText());
                            productDetail.setName_P(namePro_Field.getText());
                            productDetail.setQuantity_P(Integer.parseInt(quantityPro_Field.getText()));
                            productDetail.setPrice_P(Float.parseFloat(pricePro_Field.getText()));
//                            productDetail.setImage_P();
                            apiService.addP(productDetail);
//                            productDetail.addProduct(idPro_Field.getText(), namePro_Field.getText(), Float.parseFloat(pricePro_Field.getText()),
//                                    productDetail.getImage_P(), Integer.parseInt(quantityPro_Field.getText()));

                            Button b = (Button) event.getSource();
                            Stage stage = (Stage) b.getScene().getWindow();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/datasqltest/product-view.fxml"));
                            stage.setScene(new Scene(loader.load(), 1080, 680));
                            stage.setTitle("MicrochipStarApp!");
                            stage.show();
                        }
                        else {
                            warning.setText("กรุณากรอกข้อมูลให้ถูกต้อง");
                        }
                    }
                    else {
                        warning.setText("กรุณากรอกข้อมูลให้ถูกต้อง");
                }
        }
        }





    }
//
    @FXML public void handleUploadButtonOnAction(ActionEvent event){
        FileChooser chooser = new FileChooser();
        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        System.out.println(chooser);
        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File productDir = new File("image");
                System.out.println(productDir);
                if (!productDir.exists()) productDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                System.out.println(filename);
                Path target = FileSystems.getDefault().getPath(
                        productDir.getAbsolutePath()+System.getProperty("file.separator")+filename

                );
                System.out.println(target);
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                imageView.setImage(new Image(target.toUri().toString()));
                productDetail.setImage_P(filename);
//                productDetail.setImage_P(productDir + "/" + filename);
//
//                System.out.println(path);
//                dataSource.writeData(cardList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//
//
//
    private void clearSelectedProduct() {
        Product productDetail = new Product();
        selectedProduct = null;
        idPro_Field.clear();
        namePro_Field.clear();
        pricePro_Field.clear();
        quantityPro_Field.clear();
//        imagePro_Text.setText("");
        editQuantity_Field.clear();
        editWarning.setText("");
        warning.setText("");
        imageView.setVisible(false);

        handleUpload.setDisable(false);
        editQuantityButton.setDisable(true);
        inQuantity_Button.setDisable(true);
        addProduct_Button.setDisable(true);

    }
    public void clearButtonOnAction(ActionEvent event){
        clearSelectedProduct();
        addProduct_Button.setDisable(false);

    }
//
    public void nextButtonOnAction(ActionEvent event)throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/datasqltest/employee/homeEmployee.fxml"));
        stage.setScene(new Scene(loader.load(), 1080, 600));
        stage.setTitle("MicrochipStarApp!");
        stage.show();
    }
//
}