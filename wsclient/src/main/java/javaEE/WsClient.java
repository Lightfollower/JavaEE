package javaEE;



import adminconsole.service.AdminService;
import adminconsole.service.AdminkaServiceWS;
import adminconsole.service.Category;

import javax.xml.datatype.DatatypeConfigurationException;
import java.net.MalformedURLException;
import java.net.URL;

public class WsClient {
    public static void main(String[] args) throws MalformedURLException, DatatypeConfigurationException {
        URL url = new URL("http://localhost:8080/lesson_4/AdminService/AdminkaService?wsdl");
        AdminService adminService = new AdminService(url);

        AdminkaServiceWS adminkaServicePort = adminService.getAdminkaServicePort();

        adminkaServicePort.findAll().forEach(t -> System.out.println(t.getName()));

        Category category = new Category();
        category.setName("first cat");
        adminkaServicePort.insert(category);
    }
}
