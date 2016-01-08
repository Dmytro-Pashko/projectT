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

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        //System.out.println(contact.getFixtureA().getBody().getUserData() + "    " + contact.getFixtureB().getBody().getUserData());

        //������� ������ ���������� �� �����.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2)) {
            player.setJump(false);
        }

        //������� ���� �� ���������� �� ���� �� ������ ��� ����� del,���� ����������
        //������� ��� ���� � ���� �����, �� ������ Exception ��� ��� �������� ��� ������ ������.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2) && b.getUserData() == "mob") {
            Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
            player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
            player.setJump(true);
            for (OrdinaryMob mob : listMobs) {
                if (mob.getBody() ==b) {
                    listAnimation.add(new Animation(AnimationType.MOB, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM));
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

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1) && b.getUserData() == "brick") {
            Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
            b.setUserData("del");
            listAnimation.add(new Animation(AnimationType.BRICK, b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                    b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM));
        }

        //coins

        if (contact.getFixtureA().getBody() == player.getBody() && b.getUserData() == "coin") {
            Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
            for (Coin coin : listCoins) {
                if (coin.getBody() == b) {
                    listCoins.remove(coin);
                    System.out.println(score += 100);
                    break;
                }
            }
        }

        //secretBox

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1) && b.getUserData() == "secretBox") {
            for (SecretBox box : listSecretBox) {
                if (box.getBody() == b && !box.isFlag()) {
                    listAnimation.add(new Animation(AnimationType.COIN,b.getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            b.getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                    box.setFlag(!box.isFlag());
                    System.out.println(score += 100);
                    Fixture fb = contact.getFixtureB();
                    Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.FLOWER, b.getPosition().x-0.5f, b.getPosition().y+0.5f));
                    break;
                }
            }
        }


        //velocity of mob.
        if (b.getUserData() == "mob") {
            if (a.getUserData() == "obstacle") {
                for (OrdinaryMob mob : listMobs) {
                    if (mob.getBody() == b) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }
            if (a.getUserData() == "mob") {
                for (OrdinaryMob mob : listMobs) {
                    if( b == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                    }

                    if (a == mob.getBody()) {
                        mob.setFlagMove(!mob.isFlagMove());
                    }
                }
            }
        }

        //velocity of mashroom
        if (a.getUserData() == "mashroom")
        {
            if (b.getUserData()== "obstacle") {
                for (Mashroom mashroom : listMashrooms) {
                    if (mashroom.getBody() == a) {
                        mashroom.setFlagMove(!mashroom.isFlagMove());
                        break;
                    }
                }
            }

            if (b.getUserData() == "mob") {
                for (Mashroom mashroom : listMashrooms) {
                    if (mashroom.getBody() == a) {
                        mashroom.setFlagMove(!mashroom.isFlagMove());
                        break;
                    }

                }
                for (OrdinaryMob mob : listMobs) {
                    if (mob.getBody() == b) {
                        mob.setFlagMove(!mob.isFlagMove());
                        break;
                    }
                }
            }

            if (b.getUserData() == "player") {
                for (Mashroom mashroom : listMashrooms) {
                    if (mashroom.getBody() == a) {
                        Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, a));
                        listMashrooms.remove(mashroom);
                        //mashroom.getBody().setUserData("del");
                        System.out.println(score += 1000);
                        break;
                    }
                }
            }

        }

        //flowers

        if (b == player.getBody() && a.getUserData() == "flower") {
            for (Flower flower : listFlowers) {
                if (flower.getBody() == a) {
                    Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, a));
                    listFlowers.remove(flower);
                    System.out.println(score += 100);
                    break;
                }
            }
        }
        //flowersG
        if (a == player.getBody() && b.getUserData() == "flower") {
            for (Flower flower : listFlowers) {
                if (flower.getBody() == b) {
                    Gdx.app.postRunnable(new WorldRunnable(gameWorld, WorldActions.DESTROY, b));
                    listFlowers.remove(flower);
                    System.out.println(score += 1000);
                    break;
                }
            }
        }




        if (a == player.getBody() && b.getUserData() == "finish") {
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
