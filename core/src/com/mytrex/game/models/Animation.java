package com.mytrex.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import static com.mytrex.game.Tools.B2DVars.PPM;

/**
 * Created by Antilamer on 07.01.2016.
 */
public class Animation {
    public ParticleEffect effect;
    public float x;
    public float y;


    public Animation(AnimationType type, float x, float y){
        effect  = new ParticleEffect();
        effect.load(Gdx.files.internal(defineType(type)), Gdx.files.internal("core/assets/"));
        effect.setPosition(x * 2 - PPM, y * 2 - PPM);
        effect.scaleEffect(0.9f);
        effect.start();
    }

    private String defineType(AnimationType brick){
        switch (brick){
            case Brick:{
                return "core/assets/brick.ptf";
            }
        }
        return "";
    }
}
