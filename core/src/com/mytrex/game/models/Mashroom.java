package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

public class Mashroom {
    private SpriteBatch batch;
    private Texture sprite;
    private Body body;
    private boolean flagMove = false;

    public Mashroom(Body body) {
        batch = new SpriteBatch();
        this.body = body;
        sprite = new Texture("core/assets/mashroom.png");
    }

    public void draw(float x, float y) {
        batch.begin();
        moving();
        batch.draw(sprite, x * 2 - PPM, y * 2 - PPM, 32, 32);
        batch.end();
    }

    public void moving() {
        if (!flagMove) body.setLinearVelocity(3, body.getLinearVelocity().y);
        else body.setLinearVelocity(-3, body.getLinearVelocity().y);

    }

    public Body getBody() {
        return body;
    }

    public void setFlagMove(boolean flagMove) {
        this.flagMove = flagMove;
    }

    public boolean isFlagMove() {
        return flagMove;
    }
}
