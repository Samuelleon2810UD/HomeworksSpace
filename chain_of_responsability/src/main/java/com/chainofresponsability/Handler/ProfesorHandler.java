package com.chainofresponsability.Handler;
public class ProfesorHandler extends BaseHandler {
    public ProfesorHandler(SubHandler subHandler) {
        super("Profesor", 2, subHandler);
    }
}
