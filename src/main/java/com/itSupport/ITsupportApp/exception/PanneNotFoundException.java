package com.itSupport.ITsupportApp.exception;

public class PanneNotFoundException  extends RuntimeException{
    public PanneNotFoundException(){
        super(("Panne not found !"));
    }
}
