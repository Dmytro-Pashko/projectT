package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.mytrex.game.models.Player;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public class GameWorld
{
    private World world;
    private Player player;

    public World getWorld()
    {
        return world;
    }

    public GameWorld()
    {
        world = new World(new Vector2(0, -10), true);
        for (int i = 0; i <10 ; i++)
        {
            createGroundBlocks((float)i,(float)0.0f);
        }
        initPlayer(5, 5);
    }

    public void initPlayer(float x, float y){
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("box.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"box",new FixtureDef(),1.0f);
        body.setTransform(x , y, 0);
        MassData data = new MassData();
        data.I = 3f;
        data.mass = 5f;
        data.center.set(new Vector2(0.25f, 0.25f));
        body.resetMassData();
        body.setMassData(data);
    }

    public void createGroundBlocks(float x,float y)
    {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("ground.json"));
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"wall",new FixtureDef(),1.0f);
        body.setTransform(x , y, 0);
    }
}
