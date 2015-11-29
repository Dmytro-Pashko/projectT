package com.mytrex.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import static com.mytrex.game.Tools.B2DVars.PPM;

/**
 * Created by Goodvin1709 on 10.11.2015.
 */
public class Player extends GameObject
{
    private Body body;
    private Sprite spriteStay, spriteJump;
    private Boolean leftMove, rightMove, jump, stay;
    private SpriteBatch batch;
    public com.badlogic.gdx.physics.box2d.Fixture playerSensorFixture;

    public TextureRegion[] walkFrames;
    public Texture walkSheet;
    public Animation walkAnimation;
    public TextureRegion currentFrame;


    public Player(Body body)
    {
        spriteStay = new Sprite(new Texture("core/assets/player_stay.png"));
        spriteJump = new Sprite(new Texture("core/assets/player_jump.png"));
        batch = new SpriteBatch();
        this.body = body;
        leftMove = false;
        rightMove = false;
        stay = true;
        jump=false;
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);
        circle.setPosition(new Vector2(0, -0.05f));
        playerSensorFixture = body.createFixture(circle, 0);

        walkSheet = new Texture("core/assets/player_stay.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 5, walkSheet.getHeight());
        walkFrames = new TextureRegion[5];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.2f, walkFrames);
    }

    public void setSpriteCoord(TextureRegion sprite, float x, float y){
        batch.begin();
        batch.draw(sprite, x * 2 - PPM, y * 2 - PPM, 25, 36);
        batch.end();
    }

    public TextureRegion getCurrentFrame(float stateTime) {
        return walkAnimation.getKeyFrame(stateTime, true);
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
