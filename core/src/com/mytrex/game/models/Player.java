package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;

/**
 * Created by Goodvin1709 on 10.11.2015.
 */
public class Player extends GameObject
{
    private Body body;
    private Sprite sprite;
    private Boolean leftMove,rightMove,jump;
    public com.badlogic.gdx.physics.box2d.Fixture playerPhysicsFixture;
    public com.badlogic.gdx.physics.box2d.Fixture playerSensorFixture;

    public Player(Body body)
    {
        sprite = new Sprite(new Texture("core/assets/badlogic.jpg"));
        this.body = body;
        leftMove = false;
        rightMove = false;
        jump=false;
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);
        circle.setPosition(new Vector2(0, -0.05f));
        playerSensorFixture = body.createFixture(circle, 0);
    }


    public Sprite getSprite(){
        return sprite;
    }

    public Boolean getJump() {
        return jump;
    }

    public void setJump(Boolean jump) {this.jump = jump;}

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
