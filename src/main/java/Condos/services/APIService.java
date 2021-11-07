package Condos.services;

import Condos.models.Employee;
import Condos.models.POModel;
import Condos.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import th.ac.ku.MicrochipsStarApp.model.Customer;

@Service
public class APIService {



    @Autowired
    private RestTemplate restTemplate;

    public APIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//------------------------------------------PO-------------------------------------------------------------
    public List<POModel> getAll() {
        String url = "http://localhost:8091/PO";
        ResponseEntity<POModel[]> response =
                restTemplate.getForEntity(url, POModel[].class);
        POModel[] carts = response.getBody();
        return Arrays.asList(carts);
    }

    public void addPO(POModel cart) {
        String url = "http://localhost:8091/PO";

        restTemplate.postForObject(url, cart, POModel.class);
    }

    public void updatePO(POModel order){
        String url = "http://localhost:8091/PO";
        restTemplate.put(url,order,POModel.class);
    }
    //----------------------------------Employee---------------------------------------------------
    public List<Employee> getE() {
        String url = "http://localhost:8091/employee";
        ResponseEntity<Employee[]> response =
                restTemplate.getForEntity(url, Employee[].class);
        Employee[] carts = response.getBody();
        return Arrays.asList(carts);
    }


    public void addE(Employee cart) {
        String url = "http://localhost:8091/employee";

        restTemplate.postForObject(url, cart, Employee.class);
    }

    public void updateE(Employee order){
        String url = "http://localhost:8091/employee";
        restTemplate.put(url,order,Employee.class);
    }
    //----------------------------------Product----------------------------------------------------
    public List<Product> getP() {
        String url = "http://localhost:8091/Product";
        ResponseEntity<Product[]> response =
                restTemplate.getForEntity(url, Product[].class);
        Product[] carts = response.getBody();
        return Arrays.asList(carts);
    }

    public void addP(Product cart) {
        String url = "http://localhost:8091/Product";

        restTemplate.postForObject(url, cart, Product.class);
    }

//    public void updateP(Product order){
//        String url = "http://localhost:8091/Product";
//        restTemplate.put(url,order,Product.class);
//    }


    public void updateP(Product order) {
        String url = "http://localhost:8091/Product";
        restTemplate.put(url,order,Product.class);
    }

    public Product getProductByPName(String pName){
        String url = "http://localhost:8091/Product";
        ResponseEntity<Product> response = restTemplate.getForEntity(url+"/"+pName, Product.class);
        Product product = response.getBody();
        return product;
    }

    public void updateProduct(Product product){
        String url = "http://localhost:8091/Product";
        restTemplate.put(url+"/"+ product.getNo_P(), product, Product.class);
    }

//    int empNo = 1;
//    String url = "http://localhost:8091/Product/" + empNo;
//
//
//
//    Product updateInfo = new Product(empNo,1000);
//
//    HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//
//    RestTemplate restTemplate = new RestTemplate();
//
//    // Data attached to the request.
//    HttpEntity<Product> requestBody = new HttpEntity<>(updateInfo, headers);
//
//    // Send request with PUT method.
//        restTemplate.exchange(url, HttpMethod.PUT, requestBody, Void.class);
}
