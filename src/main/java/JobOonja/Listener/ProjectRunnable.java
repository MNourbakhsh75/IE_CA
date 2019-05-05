package JobOonja.Listener;

import JobOonja.Services.GetAllDataFromServer;

public class ProjectRunnable implements Runnable{


    @Override
    public void run() {
        GetAllDataFromServer getAllDataFromServer = new GetAllDataFromServer();
        getAllDataFromServer.getAllProjectsMethod();

    }
}
