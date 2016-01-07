package com.mytrex.game.Tools;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mytrex.game.models.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antilamer on 24.11.2015.
 */
public class B2DVars {
    //Клас для того, щоб зберігати змінні.

    //pixels per meter
    public static final float PPM = 16f;
    public static int score;
    public static ArrayList<OrdinaryMob> listMobs = new ArrayList<>();
    public static ArrayList<Brick> listBricks = new ArrayList<>();
    public static ArrayList<Coin> listCoins = new ArrayList<>();
    public static ArrayList<SecretBox> listSecretBox = new ArrayList<>();
    public static ArrayList<Animation> listAnimation = new ArrayList<>();
    public static Vector3 cameraPosition;

    //upd
}
