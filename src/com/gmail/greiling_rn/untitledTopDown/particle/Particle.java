package com.gmail.greiling_rn.untitledTopDown.particle;

import com.gmail.greiling_rn.untitledTopDown.utils.Vector2;

public interface Particle {
    Vector2 getLocation();
    Vector2 getVelocity();
    float getRemainingTimeVisible();
    void lowerRemainingTimeVisibleBy(float time);
    void setLocation(Vector2 location);
}