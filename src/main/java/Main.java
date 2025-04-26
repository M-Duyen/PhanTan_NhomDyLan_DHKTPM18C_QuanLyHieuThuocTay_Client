import service.CategoryService;
import service.OrderService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        CategoryService categoryService = (CategoryService) Naming.lookup("rmi://localhost:7281/categoryService");
//        categoryService.getAll().forEach(System.out::println);
        OrderService orderService = (OrderService) Naming.lookup("rmi://localhost:7281/orderService");
        orderService.filterOrderByEmpID("EP1501", "2025-04-25").forEach(System.out::println);


    }
}
