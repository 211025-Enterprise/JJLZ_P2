package com.revature.JJLZ.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthenToken {
    private String auth_token; // token used to authenticate logged in users
    private int exp; // amount of time the token will expire in
}
