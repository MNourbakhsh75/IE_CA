package JobOonja.Listener;

import JobOonja.Services.GetAllDataFromServer;

public class ProjectRunnable implements Runnable{


    @Override
    public void run() {
//        System.out.println("runable");
        GetAllDataFromServer getAllDataFromServer = new GetAllDataFromServer();
        getAllDataFromServer.getAllProjectsMethodRunable();

    }
}
