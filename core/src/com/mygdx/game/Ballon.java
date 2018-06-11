package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;

import java.util.Timer;
import java.util.TimerTask;

import static com.mygdx.game.MyGdxGame.score;

public class Ballon extends Actor{

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("balloonImgInPNG/balloon1.png")));

    SpriteBatch batch;
    TextureAtlas textureAtlasMove;
    TextureAtlas textureAtlasPopped;
    Animation<TextureRegion> moveAnimation;
    Animation<TextureRegion> poppedAnimation;
    float timeForMove = 0.0f;
    float timeForPop = 0.0f;

    public static boolean popped = false;

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public Ballon(){

        batch = new SpriteBatch();
        textureAtlasMove = new TextureAtlas(Gdx.files.internal("SpriteSheetBallon/BalloonAtlas.atlas"));
        textureAtlasPopped = new TextureAtlas(Gdx.files.internal("ExplosionSpriteSheet/ExplosionAtlas.atlas")); //Does not show up or shows up too fast to be seen
        moveAnimation = new Animation(1/5f,textureAtlasMove.getRegions());
        poppedAnimation = new Animation(1/2f,textureAtlasPopped.getRegions());

        setX((float)(Math.random()*Gdx.graphics.getWidth()-300)+200);
        setY((float)(Math.random()*300)-500);

        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

        ScaleByAction scaleByAction = new ScaleByAction();
        scaleByAction.setAmount(10f,10f);
        scaleByAction.setDuration(.1f);

        //addAction(scaleByAction);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //sprite.draw(batch);

        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(getX(),getY(),getWidth(),getHeight());
        shapeRenderer.end();*/

        timeForMove += Gdx.graphics.getDeltaTime();
        timeForPop += Gdx.graphics.getDeltaTime();

        if (popped) {
            batch.draw(poppedAnimation.getKeyFrame(timeForPop,false), getX(), getY());
            timeForMove = 0;
            Gdx.app.log("asdfg", "DONE");
            addAction(Actions.removeActor());
            popped = false;


        }else{
            batch.draw(moveAnimation.getKeyFrame(timeForMove,true), getX(), getY());
            timeForPop = 0;
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        sprite.setPosition(getX(),getY());

        MoveByAction moveByAction = new MoveByAction();
        moveByAction.setAmountY(100f);
        moveByAction.setDuration(10f);
        addAction(moveByAction);

        if (getY() >= 1500) {
            addAction(Actions.removeActor());
            score--;
        }

    }
}
