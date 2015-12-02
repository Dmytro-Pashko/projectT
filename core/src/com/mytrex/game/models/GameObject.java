package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Antilamer on 19.11.2015.
 */
public class GameObject {
    Body body;
    Boolean leftMove = false;
    Boolean rightMove = false;
    Boolean jump = false;
    Boolean isLeft = false;
    SpriteBatch batch;
    TextureRegion[] walkFramesRight, walkFramesLeft;
    Texture spriteStay, spriteStayLeft, spriteJumpRigth, spriteJumpLeft, walkSheet;
    Animation walkAnimation, walkAnimationLeft;

    public Body getBody() {
        return body;
    }

    public void setIsLeft(Boolean isLeft) {
        this.isLeft = isLeft;
    }

    public void setJump(Boolean jump) {
        this.jump = jump;
    }

    public void setLeftMove(Boolean leftMove) {
        this.leftMove = leftMove;
    }

    public void setRightMove(Boolean rightMove) {
        this.rightMove = rightMove;
    }
}
