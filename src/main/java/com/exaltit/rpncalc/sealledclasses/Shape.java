package com.exaltit.rpncalc.sealledclasses;

public sealed class Shape permits Circle, Square {
    private Point center;

    private short s;

    public Point getCenter() {
        return center;
    }
}
