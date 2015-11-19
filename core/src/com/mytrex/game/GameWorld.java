package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.GroundBlock;
import com.mytrex.game.models.Player;

import java.util.ArrayList;


public class GameWorld
{
    public Player player;
    private World world;

    public ArrayList<Object> list = new ArrayList<>();

    public World getWorld()
    {
        return this.world;
    }

    public GameWorld()
    {
        world = new World(new Vector2(0, -9.8f), true);
        for (int i = 0; i <10 ; i++)
        {
            list.add(new GroundBlock(createGroundBlocks(i, 0.0f)));
        }
        player = new Player(initPlayer(2,2));
        list.add(player);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Body createGroundBlocks(float x,float y)
    {
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/ground.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"wall",new FixtureDef(),1.0f);
        body.setTransform(x, y, 0);
        return body;
    }
    public Body initPlayer(float x, float y){
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/box.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"box",new FixtureDef(),1.0f);
        body.setTransform(x, y, 0);
        return body;
    }
    public void update()
    {
        //System.out.println("Player X="+player.getBody().getPosition().x+" Player Y="+player.getBody().getPosition().y);
        if (player.getLeftMove()) player.getBody().setTransform(player.getBody().getPosition().x - 0.1f, player.getBody().getPosition().y, 0);

        if (player.getRightMove()) player.getBody().setTransform(player.getBody().getPosition().x + 0.1f, player.getBody().getPosition().y, 0);

    }
}
