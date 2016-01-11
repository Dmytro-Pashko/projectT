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


        //Player all body collision.
        if (a.getUserData() == "player") {
            if (b.getUserData() == "flower") {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                score += 1000;
            }
            if (b.getUserData() == "coin") {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                score += 100;
            }
            if (b.getUserData() == "mashroom") {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                player.setFlagFlower(true);
                score += 1000;
            }
            if (b.getUserData() == "finish") {
                complete = true;
            }
        }
        //body
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(0)) {
            if (b.getUserData() == "mob") {
                player.playerDying();
                System.out.println("You Die");
            }
        }
        //Player up sensor collision.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1)) {
            if (b.getUserData() == "brick" && ((int) player.getBody().getPosition().x == (int) b.getPosition().x)) {
                listAnimation.add(new Animation(AnimationType.BRICK, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                        b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                b.setUserData("del");
                score += 50;
            }
            if (b.getUserData() == "coinbox" && (int) player.getBody().getPosition().x == (int) b.getPosition().x) {
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
            if (b.getUserData() == "ground" || b.getUserData() == "coinbox" ||
                    b.getUserData() == "obstacle" || b.getUserData() == "brick" ||
                    b.getUserData() == "secretmashroombox" || b.getUserData() == "secretstarbox" ||
                    b.getUserData() == "secretlifebox" || b.getUserData() == "secretcoinsbox") {
                player.setJump(false);
            }
            for (OrdinaryMob mob : listMobs) {
                if (contact.getFixtureB() == mob.getBody().getFixtureList().get(1)) {
                    System.out.println(contact);
                    listAnimation.add(new Animation(AnimationType.MOB, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                    Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                    b.setUserData("del");
                    player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
                    player.setJump(true);
                    score += 50;
                }
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
            if (contact.getFixtureB() == player.getBody().getFixtureList().get(2) && ((int) a.getPosition().x == (int) b.getPosition().x)) {
                Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, a));
                a.setUserData("del");
                player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
                player.setJump(true);
                score += 100;
            }
            if (b.getUserData() == "mob" || b.getUserData() == "obstacle" || b.getUserData() == "mashroom") {
                for (OrdinaryMob mob : listMobs) {
                    if (b == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }
        }
        //its fix for two mobs hookin
        if (b.getUserData() == "mob") {
            if (a.getUserData() == "mob") {
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
            if (contact.getFixtureB() == player.getBody().getFixtureList().get(1) && (int) player.getBody().getPosition().x == (int) a.getPosition().x) {
                for (CoinBox box : listCoinBoxes) {
                    if ((box.getBody() == a) && (!box.isFlag())) {
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
        if (a.getUserData() == "obstacle") {
            if (b.getUserData() == "mob") {
                for (OrdinaryMob mob : listMobs) {
                    if (b == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }
            if (b.getUserData() == "mashroom") {
                for (Mashroom mashroom : listMashrooms) {
                    if (b == mashroom.getBody()) {
                        mashroom.setFlagMove(!mashroom.isFlagMove());
                        break;
                    }
                }
            }
        }

        //Flowers and mashrooms
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1) && b.getUserData() == "secretmashroombox" && (int) player.getBody().getPosition().x == (int) b.getPosition().x) {
            for (SecretCoinBox box : listSecretBoxes) {
                if ((box.getBody() == b) && (!box.isFlag())) {

                    if (!player.getFlagFlower()) {
                        Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.MASHROOM, b.getPosition().x - 0.5f, b.getPosition().y + 0.5f));
                        Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                    }
                    if (player.getFlagFlower()) {
                        Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.FLOWER, b.getPosition().x - 0.5f, b.getPosition().y + 0.5f));
                        Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                    }

                    box.setFlag(!box.isFlag());
                    player.playerLife();
                    score += 1000;
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
