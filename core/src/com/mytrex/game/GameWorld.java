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
    private Rectangle rectangle = new Rectangle(3, 7.5f ,0.5f , 0.5f);


    public World getWorld()
    {
        return this.world;
    }

    public GameWorld()
    {
        player = new Player(1, 1, 1, 1);
        world = new World(new Vector2(0, -9.8f), true);
        for (int i = 0; i <10 ; i++)
        {
            createGroundBlocks((float)i,8.0f);
        }

    }


    public Rectangle getRect(){
        return rectangle;
    }
    public Player getPlayer(){
        return player;
    }


    public void update(float delta){
        Gdx.app.log("GameWorld", "update");
        rectangle.x+= 0.2;
        if (rectangle.x > 50){
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
}
