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

        //������� ������ ���������� �� �����.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2)) {
            player.setJump(false);
        }

        //������� ���� �� ���������� �� ���� �� ������ ��� ����� del,���� ����������
        //������� ��� ���� � ���� �����, �� ������ Exception ��� ��� �������� ��� ������ ������.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2) && contact.getFixtureB().getBody().getUserData() == "mob") {
            contact.getFixtureB().getBody().setUserData("del");
            player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
            for (OrdinaryMob mob : listMobs) {
                if (mob.getBody() == contact.getFixtureB().getBody()){
                    listMobs.remove(mob);
                    break;
                }
            }
        }

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(0) && contact.getFixtureB().getBody().getUserData() == "mob" || player.getBody().getPosition().y < 0) {
            contact.getFixtureA().getBody().setUserData("del");
        }



        //velocity of mob.
        if (contact.getFixtureB().getBody().getUserData() == "mob" && contact.getFixtureA().getBody().getUserData() == "obstacle"){
            for (OrdinaryMob mob : listMobs) {
                if (mob.getBody() == contact.getFixtureB().getBody()){
                    if (mob.getFlagMove()) mob.setFlagMove(false);
                    else mob.setFlagMove(true);
                    break;
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
