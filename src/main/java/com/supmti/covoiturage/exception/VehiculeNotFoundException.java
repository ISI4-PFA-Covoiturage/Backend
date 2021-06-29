package com.supmti.covoiturage.exception;

public class VehiculeNotFoundException extends RuntimeException {
    public VehiculeNotFoundException(String message){
        super(message);
    }
}
