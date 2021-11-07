package Condos;

import Condos.Config.ComponentConfig;
import Condos.models.Employee;
import Condos.models.POModel;
import Condos.models.Product;
import Condos.services.APIService;
import Condos.services.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class testText {
    private static APIService service;
    private static EmployeeService employeeService;
    private static Employee employee;
    private static RestTemplate restTemplate;
    private static Product product;
    private static POModel poModel;


    public static void main(String[] args) {
        poModel = new POModel();
//        restTemplate = new RestTemplate();
        employeeService = new EmployeeService();
        product = new Product();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(APIService.class);
//        int empNo = 3;
//        String url = "http://localhost:8091/employee/" + empNo;
//
//
//
//        Employee updateInfo = new Employee(empNo, "admin4", "Cleck");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Data attached to the request.
//        HttpEntity<Employee> requestBody = new HttpEntity<>(updateInfo, headers);
//
//        // Send request with PUT method.
//        restTemplate.exchange(url, HttpMethod.PUT, requestBody, Void.class);

        int empNo = 1;
        String url = "http://localhost:8091/Product/" + empNo;



        Product updateInfo = new Product(empNo,1000);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<Product> requestBody = new HttpEntity<>(updateInfo, headers);

        // Send request with PUT method.
        restTemplate.exchange(url, HttpMethod.PUT, requestBody, Void.class);

//        String resourceUrl = url + "/" + empNo;
//
//        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);
//
//        if (e != null) {
//            System.out.println("(Client side) Employee after update: ");
//            System.out.println("Employee: " + e.getUsernameM() + " - " + e.getPasswordM());
//        }
    }


    }

//        System.out.println(employeeService.getE().get(0).getUsernameM());
//        if (employeeService.check("admin","12345")){
//            System.out.println("มี");
//        }else {
//            System.out.println("ไม่มี");
//        }
//        System.out.println(employeeService.getDatazz());
////        employeeService = new EmployeeService();
////        employee = new Employee();
////        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
////        service = context.getBean(APIService.class);
////        for (int i = 0; i < service.getE().size() ;i++){
////            System.out.println(service.getE().get(i).getIdM());
////        }
////        System.out.println(service.getE().get(0).getIdM());
//        restTemplate = new RestTemplate();
//        String url = "http://localhost:8091/PO";
//
////        ResponseEntity<POModel[]> response =
////                restTemplate.getForEntity(url, POModel[].class);
////        POModel[] carts = response.getBody();
//
////        System.out.println(carts);
//////        System.out.println(carts);
//////        for (int i = 0; i < carts.length;i++){
//////            System.out.println(i);
//////        }
////        for (POModel c: carts) {
////            System.out.println(c.getEmail_PO());
////        }
//////
////        String responseEntity = restTemplate.getForObject(url,String.class);
//        for (POModel p : service.getAll()){
////            if (p.getId_P().equals("16B")){
////                restTemplate.put(url,product.getQuantity_P(),Product.class);
////                System.out.println("มี");
////            }
//            if (p.getId_PO() == 1){
//                restTemplate.put(url,p,POModel.class);
//                System.out.println("มี");
//            }
//
//            System.out.println(p.getId_PO());
//        }

//            System.out.println(responseEntity);


//        if (employeeService.check("admin","12345")){
//            System.out.println("มี");
//        }else {
//            System.out.println("ไม่มี");
//        }

//        System.out.println(service.getAll().get(0).getAddress_PO());
//        System.out.println(service.getE().get(0).getUsernameM());
//        System.out.println(employeeService.check("admin","12345"));
//        if (employeeService.check("admin","12345")){
//            System.out.println("มี");
//        }else {
//            System.out.println("ไม่มี");
//        }
//    }

