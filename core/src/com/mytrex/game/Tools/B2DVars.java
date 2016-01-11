package com.mytrex.game.Tools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.mytrex.game.models.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antilamer on 24.11.2015.
 */
public class B2DVars {

    public static final float PPM = 16f;
    public static int score;
    public static boolean complete, bigPlayer;
    public static ArrayList<OrdinaryMob> listMobs = new ArrayList<>();
    public static ArrayList<Brick> listBricks = new ArrayList<>();
    public static ArrayList<Coin> listCoins = new ArrayList<>();
    public static ArrayList<CoinBox> listCoinBoxes = new ArrayList<>();
    public static ArrayList<Animation> listAnimation = new ArrayList<>();
    public static ArrayList<Flower> listFlowers = new ArrayList<>();
    public static ArrayList<Mashroom> listMashrooms = new ArrayList<>();
    public static ArrayList<SecretCoinBox> listSecretBoxes = new ArrayList<>();
    public static Vector3 cameraPosition;

    public static void clearLists(){
        listMobs = new ArrayList<>();
        listBricks = new ArrayList<>();
        listCoins = new ArrayList<>();
        listCoinBoxes = new ArrayList<>();
        listAnimation = new ArrayList<>();
        listFlowers = new ArrayList<>();
        listMashrooms = new ArrayList<>();
        listSecretBoxes = new ArrayList<>();
        score = 0;
    }
}
