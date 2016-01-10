package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.mytrex.game.models.*;

import static com.mytrex.game.Tools.B2DVars.*;


public class GameWorld {
    private Player player;
    private World world;
    private MyContactListener listner;


    public GameWorld() {
        world = new World(new Vector2(0, -20f), true);
        listner = new MyContactListener();
        listner.setGameWorld(this);
        world.setContactListener(listner);
    }

    public void setPlayer(float x, float y) {

        player = new Player(initPlayer(x, y));
        listner.setPlayer(player);
    }

    public void setFlower(float x, float y,float height,float width) {
        listFlowers.add(new Flower(initBody(x, y, width, height, BodyType.StaticBody, "flower")));
    }

    public void setMasroom(float x, float y,float height,float width) {
        listMashrooms.add(new Mashroom(initBody(x, y, width, height, BodyType.DynamicBody, "mashroom")));
    }

    public void setMob(float x, float y,float height,float width) {
        listMobs.add(new OrdinaryMob(initBody(x, y,  width, height,BodyType.DynamicBody, "mob")));
    }

    public void setBrick(float x, float y,float height,float width) {
        listBricks.add(new Brick(initBody(x, y, width, height, BodyType.StaticBody, "brick")));
    }

    public void setCoin(float x, float y,float height,float width) {
        listCoins.add(new Coin(initBody(x, y, width, height, BodyType.StaticBody, "coin")));
    }

    public void setCoinBox(float x, float y,float height,float width) {
        listCoinBoxes.add(new CoinBox(initBody(x, y,  width, height,BodyType.StaticBody, "coinbox")));
    }

    public void setGround(float x, float y,float height,float width) {
        initBody(x, y, width, height, BodyType.StaticBody, "ground");
    }

    public void setObstacle(float x, float y,float height,float width) {
        initBody(x, y, width, height, BodyType.StaticBody, "obstacle");
    }
    public void setFinish(float x, float y,float height,float width) {
        initBody(x, y, width, height, BodyType.StaticBody, "finish");
    }

    //spawn mashroom or flower
    public void setSecretMashroomBox(float x, float y,float height,float width) {
        initBody(x, y, width, height, BodyType.StaticBody, "secretmashroombox");
    }
    //spawn star
    public void setSecretStarBox(float x, float y,float height,float width) {
        initBody(x, y, width, height, BodyType.StaticBody, "secretstarbox");
    }
    //spawn life mashroom
    public void setSecretLifeBox(float x, float y,float height,float width) {
        initBody(x, y, width, height, BodyType.StaticBody, "secretlifebox");
    }
    //spawn many coin.
    public void setSecretCoinsBox(float x, float y,float height,float width) {
        initBody(x, y, width, height, BodyType.StaticBody, "secretcoinsbox");
    }

    private Body initBody(float x, float y,float height,float width,BodyType type, String userData) {
        BodyDef BodyDef = new BodyDef();
        BodyDef.type = type;
        BodyDef.allowSleep = false;
        Body body = world.createBody(BodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(height/2, width/2);
        fixtureDef.shape = shape;
        MassData data = new MassData();
        data.mass = 7.5f;
        body.setMassData(data);
        body.setUserData(userData);
        body.setTransform(x + height/2, y + width/2, 0);//Через те що TiledMap виставляє центр.
        body.createFixture(fixtureDef);
        return body;
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
        loader.attachFixture(body, "PlayerBodyUpSensor", def1, 1);
        loader.attachFixture(body, "PlayerBodyDownSensor", def1, 1);
        loader.attachFixture(body, "PlayerBodyEmpties", def1, 1);
        return body;
    }

    public World getWorld() {
        return this.world;
    }

    public Player getPlayer() {
        return player;
    }
}
