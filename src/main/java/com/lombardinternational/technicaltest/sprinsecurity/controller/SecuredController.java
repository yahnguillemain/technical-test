package com.lombardinternational.technicaltest.sprinsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class SecuredController {

    @GetMapping("/public")
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("No need to be authenticated to read that");
    }

    @PostMapping("/public")
    public ResponseEntity<String> postPublic() {
        return ResponseEntity.ok("Need to be authenticated to write that");
    }

    @GetMapping("/private")
    public ResponseEntity<String> getPrivate() {
        return ResponseEntity.ok("Must be authenticated to read that");
    }

    @PostMapping("/private")
    public ResponseEntity<String> post() {
        return ResponseEntity.ok("Must be authenticated and has CAN_WRITE or ADMIN permission to do that");
    }

    @GetMapping("/private/adult_only")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Must be at least 18 years old to see that");
    }
}
