package com.supmti.covoiturage.exception;

public class AnnonceNotFoundException extends  RuntimeException {
    public AnnonceNotFoundException(String message){
        super(message);
    }
}
