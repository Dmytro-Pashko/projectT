package com.mytrex.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Antilamer on 15.11.2015.
 */
public class MouseListener extends InputListener{
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        event.getListenerActor().setSize(100, 100);
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        event.getListenerActor().setSize(87, 94);
    }
}
