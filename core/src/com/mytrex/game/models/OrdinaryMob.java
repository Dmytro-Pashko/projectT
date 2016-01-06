package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

public class OrdinaryMob extends GameCreature
{
    public OrdinaryMob(Body body)
    {
        batch = new SpriteBatch();
        this.body = body;
        walkSheet = new Texture("core/assets/mob_1.png");
        walkFrames = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            walkFrames[i] = new TextureRegion(walkSheet, 16 * i, 0, 16, 16);
        }
        walkAnimation = new Animation(0.25f, walkFrames);
    }

    public void draw(float stateTime, float x, float y)
    {
            batch.begin();
            batch.draw(walkAnimation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 32, 32);
            batch.end();
    }

    public void moving()
    {
        if (!flagMove) body.setLinearVelocity(3,  body.getLinearVelocity().y);
        else body.setLinearVelocity(-3, body.getLinearVelocity().y);

    }



}
