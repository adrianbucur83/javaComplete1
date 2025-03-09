package ro.scoala_informala.javaComplete1.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.scoala_informala.javaComplete1.model.Customer;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/info")
    public String getLoginInfo(){
        return "login/info";
    }

    @GetMapping("/form")
    public String getLoginForm() {
        return "login/form";
    }

    @PostMapping("/access")
    @ResponseBody
    public String access(@RequestParam String username, @RequestParam String password) {
        return "You are logged in as: " + username + "password:" + password;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/login/deleteUser")
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam String username, @RequestParam String password) {
        return "You are logged in as: " + username + "password:" + password;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login/updateUser")
    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody Customer customer) {
        return "You have updated customer " + customer.getName();
    }

}

