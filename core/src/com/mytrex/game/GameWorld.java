package com.mytrex.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.Player;

public class GameWorld {
    private Player player;
    private World world;

    public World getWorld() {
        return this.world;
    }

    public GameWorld() {
        world = new World(new Vector2(0, -20f), true);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(float x, float y) {
        player = new Player(initPlayer(x, y));
    }

    private Body initPlayer(float x, float y) {
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        def.allowSleep = false;
        Body body = world.createBody(def);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.5f);
        FixtureDef def1 = new FixtureDef();
        def1.shape = polygonShape;
        def1.friction = 0;
        body.createFixture(def1);
        body.setTransform(x+0.5f, y+0.5f, 0);
        MassData data = new MassData();
        data.mass = 7.5f;
        body.setMassData(data);
        body.setBullet(true);
        return body;

    }

    public void update()
    {
        if (player.getLeftMove())
        {
            Vector2 vector = getPlayer().getBody().getLinearVelocity();//ѕолучаем вектор линейной скорости.
            player.getBody().setLinearVelocity(-5, vector.y);//»змен€ем вектор линейной скорости по X на -5.
        }
        if (player.getRightMove())
        {
            Vector2 vector = getPlayer().getBody().getLinearVelocity();//ѕолучаем вектор линейной скорости.
            player.getBody().setLinearVelocity(5, vector.y);//»змен€ем вектор линейной скорости по X на +5.

        }
        if(!player.getJump() && !player.getLeftMove() && !player.getRightMove()){
            Vector2 vector = getPlayer().getBody().getLinearVelocity();
            player.getBody().setLinearVelocity(0, vector.y);
        }
        isPlayerGrounded();

    }

    public boolean isPlayerGrounded() {
        for (int i = 0; i < getWorld().getContactList().size; i++)
            if (getWorld().getContactList().get(i).isTouching() && (getWorld().getContactList().get(i).getFixtureA() == this.getPlayer().getBody().getFixtureList().get(0))) {
                player.setJump(false);
                return true;
            }
        player.setJump(true);
        return false;
    }

}
