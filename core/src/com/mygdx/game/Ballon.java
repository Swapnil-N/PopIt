package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

import static com.mygdx.game.MyGdxGame.score;

public class Ballon extends Actor{

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));

    public Ballon(){

        setX((float)(Math.random()*Gdx.graphics.getWidth()-150)+100);
        setY((float)(Math.random()*300)-500);

        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        MoveByAction moveByAction = new MoveByAction();
        moveByAction.setAmountY(100f);
        moveByAction.setDuration(10f);
        addAction(moveByAction);

        if (getY() >= 1500) {
            addAction(Actions.removeActor());
            score--;
        }

        sprite.setPosition(getX(),getY());
        sprite.setRotation(getRotation());
        sprite.setScale(getScaleX(),getScaleY());
    }
}
