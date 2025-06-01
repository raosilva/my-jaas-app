package com.example.security;

import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.LoginModule;
import java.util.Map;

public class SimpleLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private String username;
    private String password;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("No CallbackHandler available");
        }

        NameCallback nameCb = new NameCallback("Username: ");
        PasswordCallback passCb = new PasswordCallback("Password: ", false);
        Callback[] callbacks = new Callback[]{nameCb, passCb};

        try {
            callbackHandler.handle(callbacks);
            username = nameCb.getName();
            password = new String(passCb.getPassword());
            
            if ("admin".equals(username) && "admin123".equals(password)) {
                return true;
            } else {
                throw new FailedLoginException("Invalid credentials");
            }
        } catch (Exception e) {
            throw new LoginException("Error during login: " + e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
