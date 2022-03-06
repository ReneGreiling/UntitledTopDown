package me.rene.x.utils;

public class Camera {
    Vector2 location;
    double size;

    public Camera(Vector2 location, double size) {
        this.location = location;
        this.size = size;
    }

    public Vector2 getLocationOnPanel(Vector2 v) {
        return v.subtract(location).multiply(size);
    }

    public Vector2 getInWorldLocation(Vector2 v) {
        return v.add(location).divide(size);
    }

    public double getSize() {
        return size;
    }
}