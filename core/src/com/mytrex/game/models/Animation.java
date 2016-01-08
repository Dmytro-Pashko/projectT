package com.mytrex.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;


/**
 * Created by Antilamer on 07.01.2016.
 */
public class Animation {
    private ParticleEffect effect;

    public Animation(AnimationType type, float x, float y) {
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal(defineType(type)), Gdx.files.internal("core/assets/"));
        effect.setPosition(x * 2, y * 2);
        effect.scaleEffect(1f);
        effect.start();
    }

    private String defineType(AnimationType brick) {
        switch (brick) {
            case BRICK: {
                return "core/assets/brick.ptf";
            }
            case MOB: {
                return "core/assets/ordinarymob.ptf";
            }
            case COIN: {
                return "core/assets/coin.ptf";
            }
        }
        return "";
    }

    public ParticleEffect getEffect() {
        return effect;
    }
}
