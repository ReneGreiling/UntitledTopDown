package me.rene.x.utils;

import me.rene.x.Main;
import me.rene.x.entities.Entity;

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

    public boolean isEntityShownByCamera(Entity entity) {
        if (this.getLocationOnPanel(entity.getLocation()).x > Main.instance.jp.getSize().width) return false;
        if (this.getLocationOnPanel(entity.getLocation().add(entity.getSize())).x < 0) return false;
        if (this.getLocationOnPanel(entity.getLocation()).y > Main.instance.jp.getSize().height) return false;
        return !(this.getLocationOnPanel(entity.getLocation().add(entity.getSize())).y < 0);
    }

    public double getSize() {
        return size;
    }
}