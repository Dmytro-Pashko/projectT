package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.mytrex.game.models.OrdinaryMob;
import com.mytrex.game.models.Player;

import java.util.ArrayList;


public class GameWorld {
    private Player player;
    private World world;
    private ArrayList<OrdinaryMob> mobs = new ArrayList<>();
    private MyContactListener listner;


    public GameWorld()
    {
        world = new World(new Vector2(0, -20f), true);
        listner = new MyContactListener();
        world.setContactListener(listner);
    }

    public void setPlayer(float x, float y)
    {

        player = new Player(initPlayer(x, y));
        listner.setPlayer(player);
    }

    private Body initPlayer(float x, float y) {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/PlayerBody.ptb"));
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        def.allowSleep = false;
        Body body = world.createBody(def);
        FixtureDef def1 = new FixtureDef();
        def1.friction = 0;
        MassData data = new MassData();
        data.mass = 7.5f;
        body.setMassData(data);
        body.setBullet(true);
        body.setUserData("player");
        body.setTransform(x, y, 0);
        loader.attachFixture(body, "PlayerBodyBox", def1, 1);
        loader.attachFixture(body, "PlayerBodyUpSensor", new FixtureDef(), 1);
        loader.attachFixture(body, "PlayerBodyDownSensor", new FixtureDef(), 1);
        return body;

    }

    public void setMob(float x, float y)
    {
        mobs.add(new OrdinaryMob(initMob(x,y)));
    }

    private Body initMob(float x, float y)
    {
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

       for (int i = 0; i < world.getBodyCount(); i++)
        {
            Array<Body> bodys =  new Array<>();
            world.getBodies(bodys);

            for (Body b:bodys)
                if (b.getUserData() == "del") world.destroyBody(b);
        }
    }

    public World getWorld()
    {
        return this.world;
    }
    public Player getPlayer()
    {
        return player;
    }
}
