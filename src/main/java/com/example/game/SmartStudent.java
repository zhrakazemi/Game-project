package com.example.game;

import javafx.scene.image.Image;

public class SmartStudent extends Hero{
    int power = 100;
    int speed = 5;
    int health = 100;
    boolean isDead = false;

    public SmartStudent(int x  , int y)  {
        super(100, 5, 100, 150 , x , y);
        Image image=new Image(HelloApplication.class.getResourceAsStream("SmartStudent.png"));
        this.setFitHeight(100);
        this.setFitWidth(70);
        this.setImage(image);
    }
    public SmartStudent copy(){
        return new SmartStudent(
                (int)getTranslateX(),
                (int)getTranslateY()
        );
    }

    public int getHealth() {
        return health;
    }
}
