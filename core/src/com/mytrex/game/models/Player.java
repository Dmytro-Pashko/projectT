package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;

import static com.mytrex.game.Tools.B2DVars.PPM;

public class Player extends GameObject {
    private Body body;
    private Boolean leftMove = false;
    private Boolean rightMove = false;
    private Boolean jump = false;
    private SpriteBatch batch;
    public Fixture playerSensorFixture;

    private TextureRegion[] walkFramesRight, walkFramesLeft;
    private Texture spriteStay, spriteJump, walkSheet;
    private Animation walkAnimation, walkAnimationLeft;


    public Player(Body body) {
        spriteStay = new Texture("core/assets/player_stay.png");
        spriteJump = new Texture("core/assets/player_jump.png");
        batch = new SpriteBatch();
        this.body = body;
        //CircleShape circle = new CircleShape();
        //circle.setRadius(0.5f);
        //circle.setPosition(new Vector2(0, -0.05f));
        //playerSensorFixture = body.createFixture(circle, 0);

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

    public void Draw(float stateTime, float x, float y) {
        batch.begin();
        if ((rightMove) && !(jump))
            batch.draw(walkAnimation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if ((leftMove) && !(jump))
            batch.draw(walkAnimationLeft.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if (jump) batch.draw(spriteJump, x * 2 - PPM, y * 2 - PPM, 25, 36);
        else batch.draw(spriteStay, x * 2 - PPM, y * 2 - PPM, 25, 36);
        batch.end();
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
