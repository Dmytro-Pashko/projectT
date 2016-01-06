package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.mytrex.game.models.*;

import static com.mytrex.game.Tools.B2DVars.*;


public class GameWorld {
    private Player player;
    private World world;
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

    public void setMob(float x, float y) {
        listMobs.add(new OrdinaryMob(initMob(x,y)));
    }

    private Body initMob(float x, float y)
    {
        BodyDef BodyDef = new BodyDef();
        BodyDef.type = BodyType.DynamicBody;
        BodyDef.allowSleep = false;
        Body body = world.createBody(BodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f,0.5f);
        fixtureDef.shape = shape;
        MassData data = new MassData();
        data.mass = 7.5f;
        body.setMassData(data);
        body.setUserData("mob");
        body.setTransform(x, y, 0);
        body.createFixture(fixtureDef);
        return body;
    }

    public void setBrick(float x, float y) {
        listBricks.add(new Brick(initBrick(x, y)));
    }

    private Body initBrick(float x, float y)
    {
        BodyDef BodyDef = new BodyDef();
        BodyDef.type = BodyType.StaticBody;
        Body body = world.createBody(BodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f,0.5f);
        fixtureDef.shape = shape;
        body.setUserData("brick");
        body.setTransform(x + 0.5f, y + 0.5f, 0);
        body.createFixture(fixtureDef);
        return body;
    }

    public void setCoin(float x, float y) {
        listCoins.add(new Coin(initCoin(x, y)));
    }

    private Body initCoin(float x, float y)
    {
        BodyDef BodyDef = new BodyDef();
        BodyDef.type = BodyType.StaticBody;
        Body body = world.createBody(BodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f,0.5f);
        fixtureDef.shape = shape;
        body.setUserData("coin");
        body.setTransform(x + 0.5f, y + 0.5f, 0);
        body.createFixture(fixtureDef);
        return body;
    }
    public void setBox(float x, float y) {
        listSecretBox.add(new SecretBox(initSecretBox(x, y)));
    }

    private Body initSecretBox(float x, float y)
    {
        BodyDef BodyDef = new BodyDef();
        BodyDef.type = BodyType.StaticBody;
        Body body = world.createBody(BodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f,0.5f);
        fixtureDef.shape = shape;
        body.setUserData("secretBox");
        body.setTransform(x + 0.5f, y + 0.5f, 0);
        body.createFixture(fixtureDef);
        return body;
    }

    public void update()
    {
        player.moving();//Ету хуету нужно сделать в PlayerInputProcessor

       for (int i = 0; i < world.getBodyCount(); i++)
        {
            Array<Body> bodies =  new Array<>();
            world.getBodies(bodies);
            for (Body b:bodies)
            {

                if (b.getUserData() == "del") world.destroyBody(b); //Если стоит метка на удаление.
                if (b.getPosition().y < -2) world.destroyBody(b); //Если тело упало под карту.

            }
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
