package com.supmti.covoiturage.exception;

public class InternauteNotFoundException extends RuntimeException {
    public InternauteNotFoundException(String message){
        super(message);
    }
}
