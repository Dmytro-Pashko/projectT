package com.mytrex.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.models.OrdinaryMob;
import com.mytrex.game.models.Player;
import static com.mytrex.game.Tools.B2DVars.*;

/**
 * Created by Antilamer on 03.12.2015.
 */
public class MyContactListener implements ContactListener {
    private Player player;


    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {

        //—мотрим сенсор нахождени€ на земле.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2)) {
            player.setJump(false);
        }

        //—мотрим если мы напрыгнули на моба то ставим ему метку del,если попытаемс€
        //удалить его тело в этом цикле, то выдаст Exception так как работают два разных потока.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2) && contact.getFixtureB().getBody().getUserData() == "mob") {
            contact.getFixtureB().getBody().setUserData("del");
            player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
        }

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(0) && contact.getFixtureB().getBody().getUserData() == "mob" || player.getBody().getPosition().y < 0) {
            contact.getFixtureA().getBody().setUserData("del");
        }



        //÷ей код ще не робить, ≥де€ пол€гаЇ в тому, щоб в≥др≥зн€ти моба по хеш-коду ≥ м≥н€ти конкретного моба.
        if (contact.getFixtureA().getBody().getUserData() == "mob"){
            int hashMob, hashObj;
            for (OrdinaryMob mob : listMobs) {
                hashMob = mob.getBody().hashCode();
                hashObj = contact.getFixtureA().getBody().hashCode();
                if (hashMob == hashObj){
                    if (mob.getFlagMove()) mob.setFlagMove(false);
                    else mob.setFlagMove(true);
                }
            }
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

}
