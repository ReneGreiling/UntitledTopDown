package com.gmail.greiling_rn.untitledTopDown.events;

import com.gmail.greiling_rn.untitledTopDown.entities.Player;
import com.gmail.greiling_rn.untitledTopDown.utils.Vector2;

public class PlayerMoveEvent extends Event {
    private Player player;
    private Vector2 to;

    public PlayerMoveEvent(Player player, Vector2 to) {
        this.player = player;
        this.to = to;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Vector2 getFrom() {
        return player.getLocation();
    }

    public Vector2 getTo() {
        return to;
    }

    public void setTo(Vector2 to) {
        this.to = to;
    }
}