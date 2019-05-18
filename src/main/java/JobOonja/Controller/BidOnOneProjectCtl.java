package JobOonja.Controller;

import JobOonja.Services.AddBidingUser;
import JobOonja.itemException.ItemAlreadyExistsException;
import JobOonja.itemException.itemNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static JobOonja.Functions.Functions.createJsonResponse;


@Controller
public class BidOnOneProjectCtl {
    @RequestMapping(value = "/project/{id}/bid",method= RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String bidOnProject(@PathVariable("id") String pid, @RequestParam(value = "amount",required = false) String amount, HttpServletRequest request) {
        String userName = request.getAttribute("userName").toString();
        System.out.println("biddd : "+userName);
        String msg = null;
        Integer code;
        Boolean success;

        if(amount == null){
            msg = "invalid parameter";
            code = 400;
            success = false;
        }else {
            Integer amountInt = Integer.parseInt(amount);

            if (amountInt >= 0) {
                AddBidingUser addBidingUser = new AddBidingUser();
                try {
                    addBidingUser.AddBid(userName, pid, amountInt);
                    msg = "عملیات موفق آمیز بود!";
                    code = 200;
                    success = true;
                } catch (itemNotFoundException | ItemAlreadyExistsException ex) {
                    msg = ex.getMessage();
                    code = 406;
                    success = false;
                }
            } else {
                msg = "invalid input";
                code = 406;
                success = false;
            }
        }
        return createJsonResponse(msg,code,success).toString();
    }
}
