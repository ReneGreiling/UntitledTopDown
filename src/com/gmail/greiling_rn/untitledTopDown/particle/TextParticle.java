package com.gmail.greiling_rn.untitledTopDown.particle;

import com.gmail.greiling_rn.untitledTopDown.utils.Vector2;

import java.awt.*;

public class TextParticle implements Particle {
    private String text;
    private Vector2 location;
    private Color color;
    private Vector2 velocity;
    private float remainingTimeVisible;

    public TextParticle(String text, Vector2 location, Color color, Vector2 velocity, float remainingTimeVisible) {
        this.text = text;
        this.location = location;
        this.color = color;
        this.velocity = velocity;
        this.remainingTimeVisible = remainingTimeVisible;
    }

    @Override
    public Vector2 getLocation() {
        return location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public float getRemainingTimeVisible() {
        return remainingTimeVisible;
    }

    @Override
    public void lowerRemainingTimeVisibleBy(float time) {
        remainingTimeVisible -= time;
    }

    @Override
    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
