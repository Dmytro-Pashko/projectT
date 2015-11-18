package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.Player;



public class GameWorld
{
    public Player player;
    private World world;
    private Rectangle rectangle = new Rectangle(3, 8 ,1.7f , 1.2f);


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

    }


    public Rectangle getRect(){
        return rectangle;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void update(float delta){
        Gdx.app.log("GameWorld", "update");
        rectangle.x+= 0.2;
        if (rectangle.x > 137){
            rectangle.x = 0;
        }
    }


    public void createGroundBlocks(float x,float y)
    {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/ground.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"wall",new FixtureDef(),1.0f);
        body.setTransform(x, y, 0);

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
        fd.density = 1.0f; //���������.
        fd.friction = 0.7f;//������.
        fd.restitution = 0f;//������������.
        fd.shape = poly;//�����.
        body.createFixture(fd);

        MassData data = new MassData();
        data.I = 1f;
        data.mass = 2f;
        //data.center.add(body.getFixtureList().size / 2, body.getFixtureList().size / 2);
        body.resetMassData();
        body.setMassData(data);
        body.setTransform(x, y, (float)Math.random()*100);
    }
}
