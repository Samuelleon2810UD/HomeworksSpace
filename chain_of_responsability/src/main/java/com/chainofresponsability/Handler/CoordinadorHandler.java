package com.chainofresponsability.Handler;

public class CoordinadorHandler extends BaseHandler {
    public CoordinadorHandler(SubHandler subHandler) {
        super("Coordinador", 3, subHandler);
    }
}
