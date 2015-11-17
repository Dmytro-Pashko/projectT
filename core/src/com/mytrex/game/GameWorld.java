package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public class GameWorld
{
    private World world;

    public World getWorld()
    {
        return this.world;
    }

    public GameWorld()
    {
        world = new World(new Vector2(0, -20), true);
        for (int i = 0; i <10 ; i++)
        {
            createGroundBlocks((float)i,0.0f);
        }

        int endindex = 10;
        int startindex = 1;
        for (int i = 0; i <=10 ; i+=2)
        {
            System.out.println("");
            for (int j = startindex; j <=endindex; j+=2)
            {
                initPlayer(j+(float)Math.random(),startindex);
            }
            startindex +=2;
            endindex -=2;

        }

    }
    public void initPlayer(float x, float y){
        //BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("box.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        Body body = world.createBody(def);
        //loader.attachFixture(body,"box",new FixtureDef(),1.0f);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.50f, 0.50f);


        FixtureDef fd= new FixtureDef();
        fd.density = 1.0f; //���������.
        fd.friction = 0.7f;//������.
        fd.restitution = 0f;//������������.
        fd.shape = poly;//�����.

        body.createFixture(fd);

        MassData data = new MassData();
        data.I = 3f;
        data.mass = 10f;
        data.center.add(body.getFixtureList().size / 2, body.getFixtureList().size / 2);
        body.resetMassData();
        body.setMassData(data);
        body.setTransform(x, y, (float)Math.random()*100);
    }

    public void createGroundBlocks(float x,float y)
    {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("ground.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"wall",new FixtureDef(),1.0f);
        body.setTransform(x , y, 0);
    }
}
