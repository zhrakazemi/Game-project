package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Master extends ImageView {
    public Master() {
        Image image=new Image(HelloApplication.class.getResourceAsStream("Master.png"));
        this.setFitHeight(180);
        this.setFitWidth(260);
        this.setImage(image);
        this.setVisible(false);

    }
}
