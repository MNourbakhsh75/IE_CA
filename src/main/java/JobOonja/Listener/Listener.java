package JobOonja.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import JobOonja.DataLayer.DataMapper.UserMapper;
import JobOonja.Entities.User;
import JobOonja.Services.*;
import JobOonja.commands.Instruction;
import JobOonja.commands.Register;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static JobOonja.DataLayer.DataMapper.UserMapper.insertUserToDB;
import static JobOonja.Functions.Functions.*;

@WebListener
public class  Listener implements ServletContextListener{
    private ScheduledExecutorService scheduler;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting up!");
        GetAllDataFromServer getAllDataFromServer = new GetAllDataFromServer();
        getAllDataFromServer.getAllSkillsMethod();

        Register instruction = new Register(addStaticUser1());
        User u = instruction.run();
        Register instruction2 = new Register(addStaticUser2());
        User u2 = instruction2.run();
        Register instruction3 = new Register(addStaticUser3());
        User u3 = instruction3.run();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        long seconds = 300L;
        TimeUnit t = TimeUnit.HOURS;

        scheduler.scheduleAtFixedRate(new ProjectRunnable(), 0, 1, t);
        try{
            UserMapper userMapper = new UserMapper();
            insertUserToDB(u);
            insertUserToDB(u2);
            insertUserToDB(u3);
        }catch (SQLException s){
            System.out.println(s);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }


}
