package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mytrex.game.Tools.B2DVars.PPM;

public class Player extends GameCreature {

    public Player(Body body) {
        spriteStay = new Texture("core/assets/player_stay_right.png");
        spriteStayLeft = new Texture("core/assets/player_stay_left.png");
        spriteJumpRigth = new Texture("core/assets/player_jump_right.png");
        spriteJumpLeft = new Texture("core/assets/player_jump_left.png");
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

    public void Draw(float stateTime, float x, float y) {
        batch.begin();
        if ((rightMove) && !(jump)) batch.draw(walkAnimation.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if ((leftMove) && !(jump)) batch.draw(walkAnimationLeft.getKeyFrame(stateTime, true), x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if (jump && rightMove) batch.draw(spriteJumpRigth, x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if (jump && leftMove) batch.draw(spriteJumpLeft, x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if (jump && isLeft) batch.draw(spriteJumpLeft, x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if (jump && !isLeft) batch.draw(spriteJumpRigth, x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if (isLeft) batch.draw(spriteStayLeft, x * 2 - PPM, y * 2 - PPM, 25, 36);
        else if (!isLeft) batch.draw(spriteStay, x * 2 - PPM, y * 2 - PPM, 25, 36);
        batch.end();
    }

    public void moving(){
        if (leftMove)
        {
            Vector2 vector = body.getLinearVelocity();//ѕолучаем вектор линейной скорости.
            body.setLinearVelocity(-5, vector.y);//»змен€ем вектор линейной скорости по X на -5.
        }
        if (rightMove)
        {
            Vector2 vector = body.getLinearVelocity();//ѕолучаем вектор линейной скорости.
            body.setLinearVelocity(5, vector.y);//»змен€ем вектор линейной скорости по X на +5.
        }
        if(!leftMove && !rightMove){
            Vector2 vector = body.getLinearVelocity();
            body.setLinearVelocity(0, vector.y);
        }
    }


}
