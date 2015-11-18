package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.Player;


public class GameWorld
{
    private World world;
    public Player player;


    public World getWorld()
    {
        return this.world;
    }

    public GameWorld()
    {
        world = new World(new Vector2(0, -9.8f), true);
        for (int i = 0; i <10 ; i++)
        {
            createGroundBlocks((float)i,0.0f);
        }
    player = new Player(initPlayer(1,1));

    }

    public Player getPlayer()
    {
        return player;
    }

    public Body initPlayer(float x,float y)
    {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/box.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;

        Body body = world.createBody(def);
        body.setSleepingAllowed(false);
        loader.attachFixture(body,"box",new FixtureDef(),1.0f);
        body.setTransform(x, y,0);

        MassData data = new MassData();
        data.I = 1f;
        data.mass = 2f;

        body.setMassData(data);
        return body;
    }
    public void initBlock(float x, float y){
        //BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("box.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        Body body = world.createBody(def);
        //loader.attachFixture(body,"box",new FixtureDef(),1.0f);

        //PolygonShape poly = new PolygonShape();
        //poly.setAsBox(0.50f, 0.50f);

        CircleShape poly = new CircleShape();

        FixtureDef fd= new FixtureDef();
        fd.density = 1.0f; //Плотность.
        fd.friction = 0.7f;//Трение.
        fd.restitution = 0f;//Еластичность.
        fd.shape = poly;//Форма.
        body.createFixture(fd);

        MassData data = new MassData();
        data.I = 1f;
        data.mass = 2f;
        //data.center.add(body.getFixtureList().size / 2, body.getFixtureList().size / 2);
        body.resetMassData();
        body.setMassData(data);
        body.setTransform(x, y, (float)Math.random()*100);
    }

    public void createGroundBlocks(float x,float y)
    {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/ground.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"wall",new FixtureDef(),1.0f);
        body.setTransform(x , y, 0);

    }
}
