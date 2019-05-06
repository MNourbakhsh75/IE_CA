package JobOonja.Controller;


import JobOonja.Services.SearchUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchUserCtl {

    @RequestMapping(value = "/search/user",method= RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchUserHandler(@RequestParam(value = "name") String name){

        SearchUser searchUser = new SearchUser();
        searchUser.getSearchReasult(name);

        return "khaa";
    }
}
