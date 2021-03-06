package com.gmail.greiling_rn.untitledTopDown.utils;

import java.awt.*;

public class Vector2 {

    public double x, y;

    public static final Vector2 UP = new Vector2(0, -1),
            LEFT = new Vector2(-1, 0), DOWN = new Vector2(0, 1), RIGHT = new Vector2(1, 0), NULL = new Vector2();

    public Vector2() {
        this(0, 0);
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 fromPoint(Point point) {
        if (point != null)
            return new Vector2(point.x, point.y);
        return new Vector2();
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 multiply(double factor) {
        return new Vector2(this.x * factor, this.y * factor);
    }

    public Vector2 divide(double divisor) {
        return new Vector2(this.x / divisor, this.y / divisor);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2 normalise() {
        return magnitude() == 0 ? new Vector2() : this.divide(magnitude());
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
}