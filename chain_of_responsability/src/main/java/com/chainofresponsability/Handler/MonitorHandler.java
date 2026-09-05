package com.chainofresponsability.Handler;

public class MonitorHandler extends BaseHandler {
    public MonitorHandler(SubHandler subHandler) {
        super("Monitor", 1, subHandler);
    }
}
