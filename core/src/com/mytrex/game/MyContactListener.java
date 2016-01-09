package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.WorldActions;
import com.mytrex.game.Tools.WorldRunnable;
import com.mytrex.game.models.*;

import static com.mytrex.game.Tools.B2DVars.*;


public class MyContactListener implements ContactListener {
    private Player player;
    private GameWorld gameWorld;


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }


    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        //System.out.println(a.getUserData() + "  "+ b.getUserData());

        //Player all body collision.
        if (a.getUserData() == "player")
        {
            if (b.getUserData() == "flower")
            {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                score += 1000;
            }
            if (b.getUserData() == "coin")
            {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                score += 100;
            }
            if (b.getUserData() == "mashroom")
            {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                score += 1000;
            }
            if (b.getUserData() == "finish")
            {
                complete = true;
            }
        }
        //body
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(0)) {
            if (b.getUserData() == "mob") {
                System.out.println("You Die");
            }
        }
        //Player up sensor collision.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1)) {
            if (b.getUserData() == "brick") {
                listAnimation.add(new Animation(AnimationType.BRICK, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                        b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                score += 50;
            }
         if (b.getUserData() == "coinbox")
         {
                for (CoinBox box : listCoinBoxes) {
                    if ((box.getBody() == b) && (!box.isFlag())) {
                        //Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.FLOWER, b.getPosition().x - 0.5f, b.getPosition().y + 0.5f));
                        listAnimation.add(new Animation(AnimationType.COIN, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                                b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                        box.setFlag(!box.isFlag());
                        score += 100;
                        break;
                    }
                }
            }
        }
        //Player down sensor collision
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2)) {
            //player on ground
            if (b.getUserData() == "ground" || b.getUserData() == "coinbox" || b.getUserData()=="obstacle" || b.getUserData()=="brick") {
                player.setJump(false);
            }
            if (b.getUserData() == "mob") {
                listAnimation.add(new Animation(AnimationType.MOB, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                        b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
                player.setJump(true);
            }
        }
        //Mob collision
        if (a.getUserData() == "mob") {
            if (contact.getFixtureB() == player.getBody().getFixtureList().get(0)) {
                System.out.println("You Die");
            }
            if (contact.getFixtureB() == player.getBody().getFixtureList().get(1)) {
                System.out.println("You Die");
            }
            if (contact.getFixtureB() == player.getBody().getFixtureList().get(2)) {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, a));
                a.setUserData("del");
                player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
                player.setJump(true);
                score += 100;
            }
            if (b.getUserData() == "mob" || b.getUserData() == "obstacle" || b.getUserData() == "mashroom") {
                for (OrdinaryMob mob : listMobs) {
                    if (a == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }
        }
        //Mashroom collision
        if (a.getUserData() == "mashroom") {
            if (b.getUserData() == "mob" || b.getUserData() == "obstacle") {
                for (Mashroom mashroom : listMashrooms) {
                    if (a == mashroom.getBody()) {
                        mashroom.setFlagMove(!mashroom.isFlagMove());
                        break;
                    }
                }
            }
        }
        //CoinBox collision
        if (a.getUserData() == "coinbox") {
            if (contact.getFixtureB() == player.getBody().getFixtureList().get(1)) {
                for (CoinBox box : listCoinBoxes) {
                    if ((box.getBody() == a) && (!box.isFlag())) {
                        //Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.FLOWER, a.getPosition().x - 0.5f, b.getPosition().y + 0.5f));
                        listAnimation.add(new Animation(AnimationType.COIN, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                                a.getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                        box.setFlag(!box.isFlag());
                        score += 100;
                        break;
                    }
                }
            }
        }
        //Obstacle collision
        if (a.getUserData() == "obstacle")
        {
            if (b.getUserData() == "mob")
            {
                for (OrdinaryMob mob : listMobs) {
                    if (b == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }
            if (b.getUserData() == "mashroom")
            {
                for (Mashroom mashroom : listMashrooms) {
                    if (b == mashroom.getBody()) {
                        mashroom.setFlagMove(!mashroom.isFlagMove());
                        break;
                    }
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
