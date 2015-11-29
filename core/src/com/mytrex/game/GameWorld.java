package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.GroundBlock;
import com.mytrex.game.models.Player;

import static com.mytrex.game.Tools.B2DVars.PPM;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;


public class GameWorld {
    public Player player;
    private World world;
    private TreeMap<Integer, Integer> map;

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
        //System.out.println("Player X="+player.getBody().getPosition().x+" Player Y="+player.getBody().getPosition().y);
        if (player.getLeftMove()) {
            player.getBody().setTransform(player.getBody().getPosition().x - 0.1f, player.getBody().getPosition().y, 0);
        }

        if (player.getRightMove()) {
            player.getBody().setTransform(player.getBody().getPosition().x + 0.1f, player.getBody().getPosition().y, 0);
        }

    }

}
