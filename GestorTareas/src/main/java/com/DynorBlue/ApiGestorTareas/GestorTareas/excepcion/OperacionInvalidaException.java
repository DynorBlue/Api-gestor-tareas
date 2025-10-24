package com.DynorBlue.ApiGestorTareas.GestorTareas.excepcion;

public class OperacionInvalidaException extends RuntimeException {
    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
