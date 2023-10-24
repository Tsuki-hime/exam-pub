package cz.tsuki.pubsimulator.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // userService or create auth service?
/*
    @PostMapping("/register")
    public ResponseEntity<?> register(){



    }

    @PostMapping("/login")
    public ResponseEntity<?> login(){



    }
*/
}
