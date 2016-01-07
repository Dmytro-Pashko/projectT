package com.mytrex.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mytrex.game.models.*;

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

        //Смотрим сенсор нахождения на земле.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2)) {
            player.setJump(false);
        }

        //Смотрим если мы напрыгнули на моба то ставим ему метку del,если попытаемся
        //удалить его тело в этом цикле, то выдаст Exception так как работают два разных потока.
        if (contact.getFixtureA() == player.getBody().getFixtureList().get(2) && contact.getFixtureB().getBody().getUserData() == "mob") {
            contact.getFixtureB().getBody().setUserData("del");
            player.getBody().applyLinearImpulse(0f, 150f, 0.0f, 0.0f, true);
            player.setJump(true);
            for (OrdinaryMob mob : listMobs) {
                if (mob.getBody() == contact.getFixtureB().getBody()){
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
            contact.getFixtureB().getBody().setUserData("del");
            for (Brick brick : listBricks) {
                if (brick.getBody() == contact.getFixtureB().getBody())
                {
                    listAnimation.add(new Animation(AnimationType.BRICK, contact.getFixtureB().getBody().getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            contact.getFixtureB().getBody().getPosition().y * PPM - (cameraPosition.y - 8) * PPM));
                    brick.setFlag();
                    listBricks.remove(brick);
                    break;
                }
            }
        }

        //coins

        if (contact.getFixtureA().getBody() == player.getBody() && contact.getFixtureB().getBody().getUserData() == "coin") {
            contact.getFixtureB().getBody().setUserData("del");
            for (Coin coin : listCoins) {
                if (coin.getBody() == contact.getFixtureB().getBody()) {
                    listCoins.remove(coin);
                    System.out.println(score += 100);
                    break;
                }
            }
        }

        //secretBox

        if (contact.getFixtureA() == player.getBody().getFixtureList().get(1) && contact.getFixtureB().getBody().getUserData() == "secretBox")
        {
            for (SecretBox box : listSecretBox) {
                if (box.getBody() == contact.getFixtureB().getBody() && !box.isFlag())
                {
                    listAnimation.add(new Animation(AnimationType.COIN, contact.getFixtureB().getBody().getPosition().x * PPM - (cameraPosition.x - 8) * PPM,
                            contact.getFixtureB().getBody().getPosition().y * PPM - (cameraPosition.y - 8) * PPM + 16));
                    box.setFlag(!box.isFlag());
                    System.out.println(score += 100);
                    break;
                }
            }
        }

        //velocity of mob.
        if (contact.getFixtureB().getBody().getUserData() == "mob" && contact.getFixtureA().getBody().getUserData() == "obstacle"){
            for (OrdinaryMob mob : listMobs) {
                if (mob.getBody() == contact.getFixtureB().getBody()){
                    mob.setFlagMove(!mob.getFlagMove());
                    break;
                }
            }
        }

        if (contact.getFixtureA().getBody().getUserData() == "mob" && contact.getFixtureB().getBody().getUserData() == "mob")
        {
            for (OrdinaryMob mob:listMobs)
            {
                if (contact.getFixtureB().getBody() == mob.getBody())
                {
                    mob.setFlagMove(!mob.getFlagMove());
                }

                if (contact.getFixtureA().getBody() == mob.getBody())
                {
                    mob.setFlagMove(!mob.getFlagMove());
                }
            }
        }

        if (contact.getFixtureA().getBody() == player.getBody() && contact.getFixtureB().getBody().getUserData() == "finish") {
            System.out.println("Level Complete!");
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
