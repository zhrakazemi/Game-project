package com.example.game;

import javafx.scene.image.Image;

public class FighterStudent extends Hero {
    int power = 100;
    int speed = 5;
    int health = 100;
    boolean isDead = false;

    public FighterStudent(int x  , int y)  {
        super(100, 5, 100, 150 , x , y);
        Image image=new Image(HelloApplication.class.getResourceAsStream("FighterStudent.png"));
        this.setFitHeight(180);
        this.setFitWidth(260);
        this.setImage(image);
    }
    public FighterStudent copy(){
        return new FighterStudent(
                (int)getTranslateX(),
                (int)getTranslateY()
        );
    }
}
