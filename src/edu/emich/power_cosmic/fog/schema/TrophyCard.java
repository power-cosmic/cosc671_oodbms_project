package edu.emich.power_cosmic.fog.schema;

public class TrophyCard {

    private String title;
    private Game gameFrom;

    private int manaCost;
    private int power;
    private int toughness;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Game getGameFrom() {
        return gameFrom;
    }

    public void setGameFrom(Game gameFrom) {
        this.gameFrom = gameFrom;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

}
