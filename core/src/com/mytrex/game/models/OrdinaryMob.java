package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;
import static com.mytrex.game.Tools.B2DVars.flagMove;

public class OrdinaryMob extends GameCreature
{
    public OrdinaryMob(Body body)
    {
        batch = new SpriteBatch();
        this.body = body;
        walkSheet = new Texture("core/assets/player_walk.png");
        walkFramesRight = new TextureRegion[5];
        walkFramesLeft = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            walkFramesRight[i] = new TextureRegion(walkSheet, 25 * i, 0, 25, 36);
            walkFramesLeft[i] = new TextureRegion(walkSheet, 25 * i, 0, 25, 36);
            walkFramesLeft[i].flip(true, false);
        }
        walkAnimation = new Animation(0.1f, walkFramesRight);
        walkAnimationLeft = new Animation(0.1f, walkFramesLeft);
    }

    public void Draw(float stateTime, float x, float y)
    {
        batch.begin();
        batch.draw(walkAnimation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 25, 36);
        batch.end();
    }

    public void moving()
    {
        if (flagMove) body.setLinearVelocity(3, 0);
        else body.setLinearVelocity(-3, 0);
    }

}
