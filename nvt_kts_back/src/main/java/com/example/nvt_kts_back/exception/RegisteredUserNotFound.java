package com.example.nvt_kts_back.exception;

public class RegisteredUserNotFound extends RuntimeException{
    public RegisteredUserNotFound() { }

    public RegisteredUserNotFound(String message){ super(message);}

}
