package patterns.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import patterns.example.model.Customer;
import patterns.example.service.CustomerService;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final CustomerService customerService;

    @GetMapping({"/", "/login"})
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("customer") Customer customer, Model model) {
        customerService.saveCustomer(customer);
        model.addAttribute("successMsg", true);
        return "login";
    }

}
