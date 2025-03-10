package com.foy.twitter.service;

import com.foy.twitter.entity.User;

public interface AuthService {

    User register(String email, String password);

}
