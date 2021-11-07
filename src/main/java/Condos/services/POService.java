package Condos.services;

import Condos.Config.ComponentConfig;
import Condos.models.POModel;
import Condos.models.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class POService {
    private POModel poModel;
    private APIService apiService;

    public POService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        apiService = context.getBean(APIService.class);
    }

    public void buyProduce(String name, String phone, String email, String address, String pn, int quantity, float tp, String evidence, String date){
        poModel = new POModel(name, phone, email, address, pn, quantity, tp, evidence, date,"ยืนยันการสั่งซื้อ");
        apiService.addPO(poModel);

    }

    public boolean checkProduct(int quantity_PO , String namePro){
        for (Product p : apiService.getP()){
            if (namePro.equals(p.getName_P())){
                if (quantity_PO < p.getQuantity_P()){
                    System.out.println("1");
                    return true;
                }
                else {
                    System.out.println("2");
                    return false;
                }
            }
        }
        return false;
    }
}
