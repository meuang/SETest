package Condos.services;

import Condos.Config.ComponentConfig;
import Condos.models.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductService {
    private Product product;
    private APIService apiService;

    public ProductService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        apiService = context.getBean(APIService.class);
    }




//    public boolean checkProduct(int quantity_PO , String namePro){
//        for (Product p : apiService.getP()){
//            if (namePro.equals(p.getName_P())){
//                if (quantity_PO < p.getQuantity_P()){
//                    System.out.println("1");
//                    return true;
//                }
//                else {
//                    System.out.println("2");
//                    return false;
//                }
//            }
//        }
//        return false;
//    }

    //    public void addProduct(String idProduct,String nameProduct,float price,String imagePath,int quantity) {
//        DatabaseConnection connectionNows = new DatabaseConnection();
//        Connection connectDB = connectionNows.getConnection();
//
//        String insertField = "INSERT INTO microchipapp.product(id_P, name_P, price_P, all_quantity_P, image_P) VALUES('";
//        String insertValues = idProduct + "','" + nameProduct + "','" + price + "','" + quantity + "','" + imagePath + "')";
//        String insertToProduct = insertField + insertValues;
//
//
//        try {
//            Statement statement = connectDB.createStatement();
//            statement.executeUpdate(insertToProduct);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            e.getCause();
//        }}
    public boolean isInt(String str) {

        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }

    }
    public boolean isFloat(String str) {

        try {
            @SuppressWarnings("unused")
            float x = Float.parseFloat(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }
    }
    public boolean checkProduct(String idProduct, String nameProduct, String imagePath){
        for (Product p : apiService.getP()){
            if (idProduct.equals(p.getId_P())){
//                    System.out.println("1");
                    return true;
                }
                if(nameProduct.equals(p.getName_P())){
//                    System.out.println("2");
                    return true;
                }
                if (imagePath.equals(p.getImage_P())){
//                    System.out.println("3");
                    return true;
                }
        }

//        DatabaseConnection connectionNow = new DatabaseConnection();
//        Connection connectionDB = connectionNow.getConnection();
//        String connectQuery = "SELECT * FROM microchipapp.product";
//
//        try {
//            Statement statement = connectionDB.createStatement();
//            ResultSet queryOutPut = statement.executeQuery(connectQuery);
//            while (queryOutPut.next()){
//                if (idProduct.equals(queryOutPut.getString("id_P"))){
////                    System.out.println("1");
//                    return true;
//                }
//                if(nameProduct.equals(queryOutPut.getString("name_P"))){
////                    System.out.println("2");
//                    return true;
//                }
//                if (imagePath.equals(queryOutPut.getString("image_P"))){
////                    System.out.println("3");
//                    return true;
//                }
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("4");
        return false;
    }

//    public String lastActiveTime(){
//        LocalDateTime lastActiveTime = LocalDateTime.now();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        return lastActiveTime.format(format);
//    }
}
