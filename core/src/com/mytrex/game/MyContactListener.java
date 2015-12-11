package com.mytrex.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.models.Player;

/**
 * Created by Antilamer on 03.12.2015.
 */
public class MyContactListener implements ContactListener
{
    private Player player;


    public void setPlayer(Player player)
    {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact)
    {

        //Смотрим сенсор нахождения на земле.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2))
        {
            player.setJump(false);
        }

        //Смотрим если мы напрыгнули на моба то ставим ему метку del,если попытаемся
        //удалить его тело в этом цикле, то выдаст Exception так как работают два разных потока.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2) && contact.getFixtureB().getUserData()=="mob")
        {
          contact.getFixtureB().getBody().setUserData("del");
            //Делаем прижок как в марио.
          player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
        }

    }

    @Override
    public void endContact(Contact contact)
    {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
