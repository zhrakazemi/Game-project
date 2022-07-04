package com.example.game;

import javafx.scene.image.Image;

public class HeadTA extends Hero {
    int power = 100;
    int health = 150;
    boolean dead = false;

    public HeadTA(int x, int y) {

        super(100, 10, 150, 0, x, y);
        Image image = new Image(HelloApplication.class.getResourceAsStream("HeadTA.png"));
        this.setFitHeight(100);
        this.setFitWidth(70);
        speed = -10;
        this.setImage(image);
    }


    public int getHealth() {
        return health;

    }
}
