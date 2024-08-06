package com.itSupport.ITsupportApp.exception;

public class EquipementNotFoundException extends RuntimeException{
    public EquipementNotFoundException(){
        super(("Equipement not found !"));
    }
}
