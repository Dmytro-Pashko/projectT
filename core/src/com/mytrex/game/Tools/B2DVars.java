package com.mytrex.game.Tools;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antilamer on 24.11.2015.
 */
public class B2DVars {
    //Клас для того, щоб зберігати змінні.

    //pixels per meter
    public static final float PPM = 16f;
    public static boolean flagMove = true;
    public static final HashMap<String, Vector2> map = new HashMap<>();
    static {
        map.put("mob1", new Vector2(1, 1));
        map.put("mob1", new Vector2(4, 1));
    }

}
