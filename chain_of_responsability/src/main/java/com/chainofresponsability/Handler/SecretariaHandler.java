package com.chainofresponsability.Handler;
public class SecretariaHandler extends BaseHandler {
    public SecretariaHandler(SubHandler subHandler) {
        super("Secretaria", 4, subHandler);
    }
}
