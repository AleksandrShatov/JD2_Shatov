package com.noirix.controller.rest;

import com.noirix.beans.GitSecretConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GitSecretRestController {

    private final GitSecretConfig gitSecretConfig;

    @GetMapping("/gitsecret")
    public String get() {
        return gitSecretConfig.toString();
    }
}
