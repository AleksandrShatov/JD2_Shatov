package com.noirix.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateRequest {

    public String name;

    public String surname;

    public String login;

    public float weight;
}
