package com.mytrex.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Goodvin1709 on 10.11.2015.
 */
public class Player
{
    TextureRegion texture = new TextureRegion(new Texture("core/assets/wall.jpg"));
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleraion;
    float rotation;
    int width;
    int height;
    SpriteBatch batch = new SpriteBatch();

    public Player(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleraion = new Vector2(0, 460);
    }

    public void draw(){
        batch.begin();
        batch.draw(texture, position.x, position.y, width, height);
        batch.end();
    }

    public void update(float delta){
        velocity.add(acceleraion.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        position.add(velocity.cpy().scl(delta));

    }

    public void onClick() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

}
