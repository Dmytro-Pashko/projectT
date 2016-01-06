package com.mytrex.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mytrex.game.Tools.B2DVars.PPM;

/**
 * Created by Antilamer on 07.01.2016.
 */
public class Animation {
    public ParticleEffect effect;
    boolean start = false;
    public float x;
    public float y;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public Animation(){
        effect  = new ParticleEffect();
        effect.load(Gdx.files.internal("core/assets/test.ptf"), Gdx.files.internal("core/assets/"));
    }

    public void startAnimation(float x, float y)
    {
        effect.setPosition(x,y);
        effect.start();
    }
    public void setPosition(float camposX,float camposY)
    {
        effect.setPosition(250,250);
        effect.scaleEffect(0.5f);
    }
}
