package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;
import static com.mytrex.game.Tools.B2DVars.complete;

public class Player {

    private int life = 0;
    private Body body;
    private SpriteBatch batch;
    private Texture walkSheet, spriteStayRight, spriteStayLeft, spriteJumpRigth, spriteJumpLeft;
    private TextureRegion[] walkFramesRight, walkFramesLeft;
    private Animation walkAnimationRight, walkAnimationLeft;
    private boolean rightMove;
    private boolean leftMove;
    private boolean jump;
    private boolean isLeft;

    private boolean flagFlower;

    public Player(Body body) {
        spriteStayRight = new Texture("core/assets/player_stay_right.png");
        spriteStayLeft = new Texture("core/assets/player_stay_left.png");
        spriteJumpRigth = new Texture("core/assets/player_jump_right.png");
        spriteJumpLeft = new Texture("core/assets/player_jump_left.png");
        walkSheet = new Texture("core/assets/player_walk.png");
        batch = new SpriteBatch();
        this.body = body;

        walkFramesRight = new TextureRegion[5];
        walkFramesLeft = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            walkFramesRight[i] = new TextureRegion(walkSheet, 25 * i, 0, 25, 36);
            walkFramesLeft[i] = new TextureRegion(walkSheet, 25 * i, 0, 25, 36);
            walkFramesLeft[i].flip(true, false);
        }
        walkAnimationRight = new Animation(0.1f, walkFramesRight);
        walkAnimationLeft = new Animation(0.1f, walkFramesLeft);
    }

    public void Draw(float stateTime, float x, float y) {
        moving();
        batch.begin();
        if ((rightMove) && !(jump))
            batch.draw(walkAnimationRight.getKeyFrame(stateTime, true), x * 2 - PPM + 3, y * 2 - PPM, 25, 36);
        else if ((leftMove) && !(jump))
            batch.draw(walkAnimationLeft.getKeyFrame(stateTime, true), x * 2 - PPM + 5, y * 2 - PPM, 25, 36);
        else if (jump && rightMove) batch.draw(spriteJumpRigth, x * 2 - PPM + 2, y * 2 - PPM, 25, 36);
        else if (jump && leftMove) batch.draw(spriteJumpLeft, x * 2 - PPM + 2, y * 2 - PPM, 25, 36);
        else if (jump && isLeft) batch.draw(spriteJumpLeft, x * 2 - PPM + 2, y * 2 - PPM, 25, 36);
        else if (jump && !isLeft) batch.draw(spriteJumpRigth, x * 2 - PPM + 2, y * 2 - PPM, 25, 36);
        else if (isLeft) batch.draw(spriteStayLeft, x * 2 - PPM + 3, y * 2 - PPM, 25, 36);
        else if (!isLeft) batch.draw(spriteStayRight, x * 2 - PPM + 3, y * 2 - PPM, 25, 36);
        batch.end();
    }

    public void moving() {
        if (body.getLinearVelocity().y > 13) body.setLinearVelocity(body.getLinearVelocity().x, 13); // fix, більше не взлітає
        if (leftMove) {
            Vector2 vector = body.getLinearVelocity();
            body.setLinearVelocity(-5, vector.y);
        }
        if (rightMove) {
            Vector2 vector = body.getLinearVelocity();
            body.setLinearVelocity(5, vector.y);
        }
        if (!leftMove && !rightMove) {
            Vector2 vector = body.getLinearVelocity();
            body.setLinearVelocity(0, vector.y);
        }
    }

    public boolean getFlagFlower() {
        return flagFlower;
    }

    public void playerDying(){
        if (life > 0) life--;
        else complete = true;
    }

    public void playerLife(){
        life++;
    }

    public void setFlagFlower(boolean flagFlower) {
        this.flagFlower = flagFlower;
    }

    public Body getBody() {
        return body;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isJump() {
        return jump;
    }

    public void setLeftMove(boolean leftMove) {
        this.leftMove = leftMove;
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public void setRightMove(boolean rightMove) {
        this.rightMove = rightMove;
    }

    public float distToBody(Body body)
    {
        return this.body.getPosition().dst(body.getPosition().cpy());
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
