package br.com.cadastro.exception.custom;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
        super("Object not found!");
    }
}
