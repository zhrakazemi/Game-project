package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Master extends Hero {
    public Master(int x , int y) {
        super(150 , 10 , 600 , 0, x , y);
        Image image=new Image(HelloApplication.class.getResourceAsStream("Master.png"));
        this.setFitHeight(100);
        this.setFitWidth(70);
        this.setImage(image);
    }
    public Master copy(){
        return new Master(
                (int)getTranslateX(),
                (int)getTranslateY()
        );
    }
}
