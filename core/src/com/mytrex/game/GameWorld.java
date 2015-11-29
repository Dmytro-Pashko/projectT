package com.mytrex.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.Player;

import static com.mytrex.game.Tools.B2DVars.PPM;

import java.util.ArrayList;
import com.badlogic.gdx.utils.Array;


public class GameWorld {
    public Player player;
    private World world;

    public ArrayList<Object> list = new ArrayList<>();

    public World getWorld() {
        return this.world;
    }

    public GameWorld() {
        world = new World(new Vector2(0, -9.8f), true);
        list.add(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void createGroundBlocks(float x, float y) {
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        Body body = world.createBody(def);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.5f);

        FixtureDef def1 = new FixtureDef();
        def1.shape = polygonShape;
        def1.friction = 1;
        def1.density = 1;
        body.createFixture(def1);
        body.setTransform(x + 0.5f, y + 0.5f, 0);

    }

    public void setPlayer(float x, float y) {
        player = new Player(initPlayer(x, y));
    }

    public Body initPlayer(float x, float y) {
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        def.allowSleep = false;
        Body body = world.createBody(def);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.5f);

        MassData data = new MassData();
        data.mass = 10;

        FixtureDef def1 = new FixtureDef();
        def1.shape = polygonShape;
        body.createFixture(def1);
        body.setTransform(x + 0.5f, y + 0.5f, 0);
        body.setMassData(data);
        return body;
    }

    public void update() {
        if (player.getLeftMove()) player.getBody().setTransform(player.getBody().getPosition().x - 0.1f, player.getBody().getPosition().y, 0);
        if (player.getRightMove()) player.getBody().setTransform(player.getBody().getPosition().x + 0.1f, player.getBody().getPosition().y, 0);
        isPlayerGrounded();
    }

    public boolean isPlayerGrounded() {
        Array<Contact> contactList = this.getWorld().getContactList();

        for (int i = 0; i < contactList.size; i++) {
            Contact contact = contactList.get(i);
            if (contact.isTouching() && (contact.getFixtureA() == this.getPlayer().playerSensorFixture ||
                    contact.getFixtureB() == this.getPlayer().playerSensorFixture)) {
                player.setJump(false);
                return true;
            }
        }
        player.setJump(true);
        return false;
    }

}
