//package JobOonja.Controller;
//
//import JobOonja.Services.AddBidingUser;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/bidcheckamount")
//public class CheckBidAmountCtl extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Integer bidAmount = Integer.parseInt(request.getParameter("bidAmount"));
//        String pid = request.getParameter("projectId");
//        String res;
//        if(bidAmount >= 0){
//            AddBidingUser addBidingUser = new AddBidingUser();
//            if(addBidingUser.AddBid("1",pid,bidAmount)) {
//                res = "Done :)";
//            }else {
//                res = "bid amount should be less than project budget";
//            }
//        }else{
//            res = "invalid input";
//        }
//        request.setAttribute("res",res);
//        request.getRequestDispatcher("/Response.jsp").forward(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//    }
//}
