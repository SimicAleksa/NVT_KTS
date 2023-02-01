package com.example.nvt_kts_back.exception;

public class RegisteredUserBlocked extends RuntimeException{
    public RegisteredUserBlocked() { }

    public RegisteredUserBlocked(String message){ super(message);}

}
