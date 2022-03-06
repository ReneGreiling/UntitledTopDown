package me.rene.x.events;

public abstract class Event implements NoneCancelableEvent {
    private boolean canceled = false;

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}