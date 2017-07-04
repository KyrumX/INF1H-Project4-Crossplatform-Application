package com.paladinzzz.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

public class Hat extends Sprite {

    private Mole mole;
    public float x;
    public float y;
    public String path;

    public Hat (Mole mole) {
        this.mole = mole;
        this.path = "None";
        Random randomHat = new Random();
        int h = randomHat.nextInt(10) + 1;

        switch(h) {
            case 1:
                this.path = "Viking.png";
                System.out.println("1");
                break;
            case 2:
                this.path = "Tophat.png";
                System.out.println("2");
                break;
            case 3:
                this.path = "Glasses.png";
                System.out.println("3");
                break;
            case 4:
                this.path = "Rainbow.png";
                System.out.println("4");
                break;
            case 5:
                this.path = "Elvis.png";
                System.out.println("5");
                break;
            case 6:
                this.path = "Ultimate.png";
                System.out.println("6");
                break;
            case 7:
                this.path = "Crown.png";
                System.out.println("7");
                break;
            default:
                this.path = "None";
                break;
        }
    }

    public void update() {
        this.x = mole.body.getPosition().x - getWidth() / 2;
        this.y = (mole.body.getPosition().y - getHeight() / 2) + 0.1f;
    }

    public void draw() {

    }

}
