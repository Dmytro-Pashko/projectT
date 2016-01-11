package com.mytrex.game.Tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mytrex.game.GameWorld;

/**
 * Created by Antilamer on 08.01.2016.
 */
public class WorldRunnable implements Runnable
{
    private GameWorld world;
    private float x,y;
    private Body body;
    private WorldActions action;

    public WorldRunnable(GameWorld world, WorldActions action, float x,float y)
    {
        this.world = world;
        this.action = action;
        this.x = x;
        this.y = y;
    }

    public WorldRunnable(GameWorld world, WorldActions action,Body body)
    {
        this.world = world;
        this.action = action;
        this.body = body;
    }

    @Override
    public void run()
    {
        switch (action){
            case DESTROY: {
                world.getWorld().destroyBody(body);
                break;
            }
            case FLOWER:  {
                world.setFlower(x, y,1.0f,1.0f);
                break;
            }
            case MASHROOM: {
                world.setMasroom(x, y,1.0f,1.0f);
                break;
            }
            case GROOVING: {
                world.setPlayer(x, y);
                break;
            }
        }

    }

}
