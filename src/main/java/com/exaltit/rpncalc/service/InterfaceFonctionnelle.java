package com.exaltit.rpncalc.service;

@FunctionalInterface
public interface InterfaceFonctionnelle {
    void show();

    default int addition(int a, int b) {
        return a + b;
    }

    static int subscription(int a, int b) {
        return a - b;
    }
}
