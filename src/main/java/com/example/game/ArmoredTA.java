package com.example.game;

import javafx.scene.image.Image;

public class ArmoredTA extends Hero{
    int power =75;
    int health=100;
    boolean dead = false;

    public ArmoredTA(int x , int y) {
        super(75 , 5 , 100 , 0 , x , y);
        Image image=new Image(HelloApplication.class.getResourceAsStream("ArmoredTA.png"));
        this.setFitHeight(100);
        this.setFitWidth(70);
        this.setImage(image);
        speed=-5;

    }

    public int getHealth() {
        return health;
    }
}
