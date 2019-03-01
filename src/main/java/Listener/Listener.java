package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import Services.*;
@WebListener
public class Listener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        GetAllDataFromServer getAllDataFromServer = new GetAllDataFromServer();
        getAllDataFromServer.getAllProjectsMethod();
        getAllDataFromServer.getAllSkillsMethod();
    }
}
