package JobOonja.Controller;

import JobOonja.Services.AddBidingUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static JobOonja.Functions.Functions.createJsonResponse;


@Controller
public class BidOnOneProjectCtl {
    @RequestMapping(value = "/project/{id}/bid",method= RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String bidOnProject(@PathVariable("id") String pid, @RequestParam("amount") String amount) {

        System.out.println("amount : "+amount);
        Integer amountInt = Integer.parseInt(amount);
        String msg = null;
        Integer code;
        Boolean success;
        if(amountInt >= 0){
            AddBidingUser addBidingUser = new AddBidingUser();
            if(addBidingUser.AddBid("1",pid,amountInt)) {
                msg = "Done";
                code = 200;
                success = true;
            }else {
                msg = "bid amount should be less than project budget";
                code = 406;
                success = false;
            }
        }else{
            msg = "invalid input";
            code = 406;
            success = false;
        }
        return createJsonResponse(msg,code,success).toString();
    }
}
