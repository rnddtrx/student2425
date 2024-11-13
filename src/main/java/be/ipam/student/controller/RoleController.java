package be.ipam.student.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @GetMapping("/admin")
    @Secured({ "ADMIN" })
    public String isAdmin() {
        return "{\"message\":\"You are an admin\"}";
    }

    @GetMapping("/alumni")
    @Secured({ "ALUMNI" })
    public String isAlumni() {
        return "{\"message\":\"You are an alumni\"}";
    }

    @GetMapping("/user")
    @Secured({ "USER" })
    public String isUser() {
        return "{\"message\":\"You are a user\"}";
    }

    @GetMapping("/hasRole")
    @Secured({ "USER", "ADMIN", "ALUMNI" })
    public String hasRole() {
        return "{\"message\":\"You have a role\"}";
    }

}
