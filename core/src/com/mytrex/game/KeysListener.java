package com.mytrex.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Antilamer on 15.11.2015.
 */
public class KeysListener extends InputListener {
    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        float x = event.getListenerActor().getX();
        float y = event.getListenerActor().getY();

        switch (keycode){
            case Input.Keys.UP:
                y += 5;
                break;
            case Input.Keys.DOWN:
                y -= 5;
                break;
            case Input.Keys.LEFT:
                x -= 5;
                break;
            case Input.Keys.RIGHT:
                x += 5;
                break;
        }
        event.getListenerActor().setPosition(x, y);
        return true;
    }
}
