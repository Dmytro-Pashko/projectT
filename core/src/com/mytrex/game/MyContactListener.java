package com.mytrex.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.models.Player;

import static com.mytrex.game.Tools.B2DVars.flagMove;

/**
 * Created by Antilamer on 03.12.2015.
 */
public class MyContactListener implements ContactListener {
    World world;

    public MyContactListener(World world){
        super();
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (a == null || b == null) return;
        if (a.getUserData() == null || b.getUserData() == null) return;

        if (a.getUserData().equals("mob") || b.getUserData().equals("mob")){
            if (flagMove) flagMove = false;
            else flagMove = true;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean isPlayerContact(Fixture a, Fixture b ){
        return a.getUserData() instanceof Player || b.getUserData() instanceof Player;
    }
}
