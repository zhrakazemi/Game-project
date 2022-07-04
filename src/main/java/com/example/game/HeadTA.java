package com.example.game;

import javafx.scene.image.Image;

public class HeadTA extends Hero{
    int power = 100;
    int speed = 10;
    int health=150;
    boolean dead = false;
    public HeadTA(int x , int y) {

        super(100, 10, 150 , 0 , x , y) ;
        Image image=new Image(HelloApplication.class.getResourceAsStream("HeadTA.png"));
        this.setFitHeight(180);
        this.setFitWidth(260);
        this.setImage(image);
    }
}
