package com.mytrex.game.Tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Antilamer on 08.01.2016.
 */
public class BodyT extends Body {

    private boolean flag = false;
    /**
     * Constructs a new body with the given address
     *
     * @param world the world
     * @param addr  the address
     */

    protected BodyT(World world, long addr) {
        super(world, addr);
    }

}
