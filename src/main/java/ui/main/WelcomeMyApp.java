package ui.main;


import model.Employee;
import service.EmployeeService;


import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static staticProcess.StaticProcess.*;

public class WelcomeMyApp {
    public static boolean doneLoading = false;
    public static void main(String[] args) throws IOException, NotBoundException {
        FileInputStream fin = new FileInputStream(new File("lib/config.properties"));
        properties.load(fin);

        Loading load = new Loading();
        login = new Login_GUI();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                Loading.main(load);
                while (!doneLoading) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {

                        Login_GUI.main(login);
                        while (!loginSuccess) {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        if (loginSuccess) {
                            HomePage.setRole(userlogin);
                            HomePage.main(null);
                        }
                    }
                };
                worker.execute();

            }
        };
        worker.execute();

    }

    public static void hideLogin(){
        login.setVisible(false);
    }

    public static Employee getEmployeeLogin() throws MalformedURLException, NotBoundException, RemoteException {
        return ((EmployeeService)Naming.lookup("rmi://" + staticProcess.StaticProcess.properties.get("ServerName") + ":" + staticProcess.StaticProcess.properties.get("Port") + "/employeeService")).findById(userlogin);
    }

}