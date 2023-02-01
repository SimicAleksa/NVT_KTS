package com.example.nvt_kts_back.exception;

public class RideNotFoundException extends RuntimeException{
    public RideNotFoundException() { }

    public RideNotFoundException(String message){ super(message);}
}
