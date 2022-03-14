package Pokemoni;

public class Abilitate {
    private int damage;
    private boolean stun;
    private boolean dodge;
    private int cooldown;
    private int cooldownStatus = 0;

    public Abilitate(int damage, boolean stun, boolean dodge, int cooldown) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cooldown = cooldown;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isStun() {
        return stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCooldownStatus() {
        return cooldownStatus;
    }

    public void setCooldownStatus(int cooldownStatus) {
        this.cooldownStatus = cooldownStatus;
    }
}
