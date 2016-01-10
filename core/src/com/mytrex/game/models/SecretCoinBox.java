package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

public class SecretCoinBox extends Box
{

    public SecretCoinBox(Body body) {
        batch = new SpriteBatch();
        this.body = body;
        waitSheet = new Texture("core/assets/SecretBox_1.png");
        openTexture = new Texture("core/assets/SecretBox_2.png");

        waitFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            waitFrames[i] = new TextureRegion(waitSheet, 16 * i, 0, 16, 16);
        }
        waitAnimation = new Animation(0.2f, waitFrames);
    }
}
