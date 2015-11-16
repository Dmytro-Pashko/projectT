package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;


public class GameWorld
{
    private World world;

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
    }

    public Body createGroundBlocks(float x,float y)
    {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("ground.json"));
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"wall",new FixtureDef(),1.0f);
        body.setTransform(x-5f,y-5f,0);
        return body;
    }
}
