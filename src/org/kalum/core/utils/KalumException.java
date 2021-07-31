package org.kalum.core.utils;

public class KalumException extends Exception{
    private String message;
    private int codigoError;
    public KalumException(int codigoError){
        this.codigoError=codigoError;
    }
    public void setMessage(String message){
        this.message=message;
    }

    @Override
    public String getMessage(){
        if(codigoError==50301){
            this.message="Error al tratar de conectar.";
        }else if(codigoError==50302){
            this.message="Error al obtener informacion.";
        }
        else if(codigoError==50303){
            this.message="Error al guardar informacion.";
        }
        return message;
    }
}
