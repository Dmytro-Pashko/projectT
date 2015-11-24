package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.BodyEditorLoader;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mytrex.game.models.GroundBlock;
import com.mytrex.game.models.Player;
import static com.mytrex.game.Tools.B2DVars.PPM;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;


public class GameWorld
{
    public Player player;
    private World world;
    private TreeMap<Integer, Integer> map;

    public ArrayList<Object> list = new ArrayList<>();

    public World getWorld()
    {
        return this.world;
    }

    public GameWorld(TreeMap<Integer, Integer> map)
    {
        this.map = map;
        world = new World(new Vector2(0, -9.8f), true);
        createGroundBlocks();
        player = new Player(initPlayer(2 , 20 ));
        list.add(player);
    }

    public Player getPlayer()
    {
        return player;
    }

    public void createGroundBlocks()
    {
        for (Map.Entry<Integer, Integer> pair : map.entrySet()){
            BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/ground.json"));
            BodyDef def = new BodyDef();
            def.type = BodyType.StaticBody;
            Body body = world.createBody(def);
            loader.attachFixture(body,"wall",new FixtureDef(),1.0f );
            body.setTransform(pair.getKey() * 16 , pair.getValue() * 16, 0);
        }
    }
    public Body initPlayer(float x, float y){
        BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("core/assets/box.json"));
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        Body body = world.createBody(def);
        loader.attachFixture(body,"box",new FixtureDef(),1.0f );
        body.setTransform(x , y, 0);
        return body;
    }
    public void update()
    {
        //System.out.println("Player X="+player.getBody().getPosition().x+" Player Y="+player.getBody().getPosition().y);
        if (player.getLeftMove()) player.getBody().setTransform(player.getBody().getPosition().x - 1f, player.getBody().getPosition().y, 0);

        if (player.getRightMove()) player.getBody().setTransform(player.getBody().getPosition().x + 1f, player.getBody().getPosition().y, 0);

    }
}
