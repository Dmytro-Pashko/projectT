package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Goodvin1709 on 10.11.2015.
 */
public class Player
{
    private Body body;
    private Sprite sprite;
    private Boolean leftMove,rightMove,jump;

    public Player(Body body)
    {
        this.body = body;
        leftMove = false;
        rightMove = false;
        jump=false;
    }

    public Boolean getJump() {
        return jump;
    }

    public void setJump(Boolean jump) {
        this.jump = jump;
    }

    public Body getBody() {
        return body;
    }

    public void setLeftMove(Boolean leftMove) {
        this.leftMove = leftMove;
    }

    public void setRightMove(Boolean rightMove) {
        this.rightMove = rightMove;
    }

    public Boolean getLeftMove() {
        return leftMove;
    }

    public Boolean getRightMove() {
        return rightMove;
    }
}
