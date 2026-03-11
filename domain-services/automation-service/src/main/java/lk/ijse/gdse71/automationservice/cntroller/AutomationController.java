package lk.ijse.gdse71.automationservice.cntroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/automation")
public class AutomationController {

    @GetMapping("/check-auth")
    public String checkAuthentication() {
        return "ok";
    }

    @GetMapping("/msg")
    public String getMessage() {
        return "message.";
    }
}