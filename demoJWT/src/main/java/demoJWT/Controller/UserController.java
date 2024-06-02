package demoJWT.Controller;

import demoJWT.Entity.User;
import demoJWT.Form.FormLogin;
import demoJWT.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody FormLogin form,  HttpServletResponse response) {
        return new ResponseEntity<>(userService.Login(form, response), HttpStatus.OK);
    }
}
