package JobOonja.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import JobOonja.Services.*;
import JobOonja.commands.Instruction;
import JobOonja.commands.Register;

import static JobOonja.Functions.Functions.*;

@WebListener
public class  Listener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        Instruction instruction;
        instruction = new Register(addStaticUser1());
        instruction.run();
        instruction = new Register(addStaticUser2());
        instruction.run();
        instruction = new Register(addStaticUser3());
        instruction.run();
        GetAllDataFromServer getAllDataFromServer = new GetAllDataFromServer();
        getAllDataFromServer.getAllProjectsMethod();
        getAllDataFromServer.getAllSkillsMethod();
    }
}
