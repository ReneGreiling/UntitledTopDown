package me.rene.x.entities;

import me.rene.x.utils.Vector2;

public abstract class Entity {

    private Vector2 location;
    private Vector2 size;

    public Entity(Vector2 location, Vector2 size) {
        this.location = location;
        this.size = size;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}