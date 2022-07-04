package com.example.game;

import javafx.scene.image.Image;

public class LazyTA extends Hero {
    int power = 50;
    ;
    int health=100;
    boolean dead = false;

    public LazyTA(int x , int y) {
        super(50 , 2 , 100 , 0 , x , y);
        Image image=new Image(HelloApplication.class.getResourceAsStream("LazyTA.png"));
        this.setFitHeight(100);
        this.setFitWidth(70);
        this.setImage(image);
        speed=-2;
    }

    public int getHealth() {
        return health;
    }
}
