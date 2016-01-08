package com.mytrex.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.Tools.WorldActions;
import com.mytrex.game.Tools.WorldRunnable;
import com.mytrex.game.models.*;

import static com.mytrex.game.Tools.B2DVars.*;

/**
 * Created by Antilamer on 03.12.2015.
 */
public class MyContactListener implements ContactListener {
    private Player player;
    private GameWorld gameWorld;
    private WorldRunnable runnable;


    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {

        //System.out.println(contact.getFixtureA().getBody().getUserData() + "    " + contact.getFixtureB().getBody().getUserData());

        //������� ������ ���������� �� �����.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2)) {
            player.setJump(false);
        }

        //������� ���� �� ���������� �� ���� �� ������ ��� ����� del,���� ����������
        //������� ��� ���� � ���� �����, �� ������ Exception ��� ��� �������� ��� ������ ������.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2) && contact.getFixtureB().getBody().getUserData() == "mob") {
            Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, contact.getFixtureB().getBody()));
            player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
            player.setJump(true);
            for (OrdinaryMob mob : listMobs) {
                if (mob.getBody() == contact.getFixtureB().getBody()) {
                    listAnimation.add(new Animation(AnimationType.MOB, contact.getFixtureB().getBody().getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            contact.getFixtureB().getBody().getPosition().y * PPM - (cameraPosition.y - 8) * PPM));
                    listMobs.remove(mob);
                    System.out.println(score += 50);
                    break;
                }
            }
        }

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(0) && contact.getFixtureB().getBody().getUserData() == "mob" || player.getBody().getPosition().y < 0) {
            System.out.println("You loose");
        }

        //bloks destroing

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1) && contact.getFixtureB().getBody().getUserData() == "brick") {
            Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, contact.getFixtureB().getBody()));
            for (Brick brick : listBricks) {
                if (brick.getBody() == contact.getFixtureB().getBody()) {
                    listAnimation.add(new Animation(AnimationType.BRICK, contact.getFixtureB().getBody().getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            contact.getFixtureB().getBody().getPosition().y * PPM - (cameraPosition.y - 8) * PPM));
                    brick.setFlag();
                    //listBricks.remove(brick);
                    break;
                }
            }
        }

        //coins

        if (contact.getFixtureA().getBody() == player.getBody() && contact.getFixtureB().getBody().getUserData() == "coin") {
            Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, contact.getFixtureB().getBody()));
            for (Coin coin : listCoins) {
                if (coin.getBody() == contact.getFixtureB().getBody()) {
                    listCoins.remove(coin);
                    System.out.println(score += 100);
                    break;
                }
            }
        }

        //secretBox

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1) && contact.getFixtureB().getBody().getUserData() == "secretBox") {
            for (SecretBox box : listSecretBox) {
                if (box.getBody() == contact.getFixtureB().getBody() && !box.isFlag()) {
                    listAnimation.add(new Animation(AnimationType.COIN, contact.getFixtureB().getBody().getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            contact.getFixtureB().getBody().getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                    box.setFlag(!box.isFlag());
                    System.out.println(score += 100);
                    Fixture fb = contact.getFixtureB();
                    Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.FLOWER, contact.getFixtureB().getBody().getPosition().x-0.5f, contact.getFixtureB().getBody().getPosition().y+0.5f));
                    break;
                }
            }
        }


        //velocity of mob.
        if (contact.getFixtureB().getBody().getUserData() == "mob") {
            if (contact.getFixtureA().getBody().getUserData() == "obstacle") {
                for (OrdinaryMob mob : listMobs) {
                    if (mob.getBody() == contact.getFixtureB().getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }
            if (contact.getFixtureA().getBody().getUserData() == "mob") {
                for (OrdinaryMob mob : listMobs) {
                    if (contact.getFixtureB().getBody() == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                    }

                    if (contact.getFixtureA().getBody() == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                    }
                }
            }
        }

        //velocity of mashroom
        if (contact.getFixtureA().getBody().getUserData() == "mashroom")
        {
            if (contact.getFixtureB().getBody().getUserData()== "obstacle") {
                for (Mashroom mashroom : listMashrooms) {
                    if (mashroom.getBody() == contact.getFixtureA().getBody()) {
                        mashroom.setFlagMove(!mashroom.isFlagMove());
                        break;
                    }
                }
            }

            if (contact.getFixtureB().getBody().getUserData() == "mob") {
                for (Mashroom mashroom : listMashrooms) {
                    if (mashroom.getBody() == contact.getFixtureA().getBody()) {
                        mashroom.setFlagMove(!mashroom.isFlagMove());
                        break;
                    }

                }
                for (OrdinaryMob mob : listMobs) {
                    if (mob.getBody() == contact.getFixtureB().getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }

            if (contact.getFixtureB().getBody().getUserData() == "player") {
                for (Mashroom mashroom : listMashrooms) {
                    if (mashroom.getBody() == contact.getFixtureA().getBody()) {
                        Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, contact.getFixtureA().getBody()));
                        listMashrooms.remove(mashroom);
                        //mashroom.getBody().setUserData("del");
                        System.out.println(score += 1000);
                        break;
                    }
                }
            }

        }

        //flowers

        if (contact.getFixtureB().getBody() == player.getBody() && contact.getFixtureA().getBody().getUserData() == "flower") {
            for (Flower flower : listFlowers) {
                if (flower.getBody() == contact.getFixtureA().getBody()) {
                    Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, contact.getFixtureA().getBody()));
                    listFlowers.remove(flower);
                    System.out.println(score += 100);
                    break;
                }
            }
        }
        //flowersG
        if (contact.getFixtureA().getBody() == player.getBody() && contact.getFixtureB().getBody().getUserData() == "flower") {
            for (Flower flower : listFlowers) {
                if (flower.getBody() == contact.getFixtureB().getBody()) {
                    Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, contact.getFixtureB().getBody()));
                    listFlowers.remove(flower);
                    System.out.println(score += 1000);
                    break;
                }
            }
        }




        if (contact.getFixtureA().getBody() == player.getBody() && contact.getFixtureB().getBody().getUserData() == "finish") {
            System.out.println("Level Complete!");
            complete = true;
        }

    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
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
