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
                    return true;
                }
                if(nameProduct.equals(p.getName_P())){
                    return true;
                }
                if (imagePath.equals(p.getImage_P())){
                    return true;
                }
        }

        return false;
    }

}
