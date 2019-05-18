package JobOonja.Listener;

import JobOonja.Services.AuctionProjects;
import JobOonja.Services.GetAllDataFromServer;

public class ActionRunnable implements Runnable{


    @Override
    public void run() {
        System.out.println("auctionrunable");
        AuctionProjects auctionProjects = new AuctionProjects();
        auctionProjects.actionProjects();

    }
}
