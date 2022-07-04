package com.example.game;

import javafx.scene.image.Image;

public class NormalStudent extends Hero{
    int power = 100;
    int speed = 5;
    int health = 100;
    boolean isDead = false;

    public NormalStudent(int x  , int y)  {
        super(100, 5, 100, 150 , x , y);
        Image image=new Image(HelloApplication.class.getResourceAsStream("NormalStudent.png"));
        this.setFitHeight(180);
        this.setFitWidth(260);
        this.setImage(image);
    }
    public NormalStudent copy(){
        return new NormalStudent(
                (int)getTranslateX(),
                (int)getTranslateY()
        );
    }
}
