package com.mytrex.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.OrdinaryMob;
import com.mytrex.game.models.Player;

import java.util.HashMap;

import static com.mytrex.game.Tools.B2DVars.map;

public class GameWorld {
    private Player player;
    private OrdinaryMob mob;
    private World world;

    public GameWorld() {
        world = new World(new Vector2(0, -20f), true);
        world.setContactListener(new MyContactListener(world));
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
        polygonShape.setAsBox(0.3f, 0.5f);
        FixtureDef def1 = new FixtureDef();
        def1.shape = polygonShape;
        def1.friction = 0;
        body.createFixture(def1);
        body.setTransform(x+0.5f, y+0.5f, 0);
        MassData data = new MassData();
        data.mass = 7.5f;
        body.setMassData(data);
        body.setBullet(true);
        body.createFixture(def1).setUserData("player");
        return body;

    }

    public void setMob(float x, float y) {
        for (HashMap.Entry<String, Vector2> pair : map.entrySet()){
            mob = new OrdinaryMob(initMob(pair.getValue().x, pair.getValue().y));
        }
    }

    private Body initMob(float x, float y) {
        BodyDef BodyDef = new BodyDef();
        BodyDef.type = BodyType.DynamicBody;
        BodyDef.allowSleep = false;
        Body body = world.createBody(BodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = 1;
        body.createFixture(fixtureDef);
        body.setTransform(x+0.5f, y+0.5f, 0);
        MassData data = new MassData();
        data.mass = 7.5f;
        body.setMassData(data);
        body.setBullet(true);
        body.createFixture(fixtureDef).setUserData("mob");
        return body;
    }

    public void update()
    {
        player.moving();
        mob.moving();
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

    public World getWorld() {
        return this.world;
    }
    public Player getPlayer() {
        return player;
    }

    public OrdinaryMob getMob() {
        return mob;
    }
}
