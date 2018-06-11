package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

import javax.xml.soap.Text;

import sun.rmi.runtime.Log;

import static com.mygdx.game.Ballon.popped;


public class MyGdxGame extends ApplicationAdapter implements GestureDetector.GestureListener{

	Stage stage;
	Texture backTexture;

	public static int score;

	BitmapFont bitmapFont;
	SpriteBatch batch;

	@Override
	public void create () {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		backTexture = new Texture(Gdx.files.internal("skyback.jpg"));

		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();
		bitmapFont.getData().setScale(6f,6f);

		Ballon ballon1 = new Ballon();
		stage.addActor(ballon1);

		GestureDetector gestureDetector = new GestureDetector(this);
		Gdx.input.setInputProcessor(gestureDetector);

	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(backTexture, 0,0);
		bitmapFont.draw(batch, "Score: "+score, 10,Gdx.graphics.getHeight()-25);
		batch.end();

		if (stage.getActors().size < 5){
			Ballon tempBall = new Ballon();
			stage.addActor(tempBall);
		}

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void dispose () {
		stage.dispose();
	}


	@Override
	public boolean touchDown(float screenX, float screenY, int pointer, int button) {
		Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX,screenY));
		Actor hitActor = stage.hit(coord.x,coord.y,false);

		if (hitActor != null) {
			Gdx.app.log("asdf","HITYES");
			popped = true;
			score+=3;
		}else{
			Gdx.app.log("asdf","NONONO");
		}

		return true;
	}

	@Override
	public boolean tap(float screenX, float screenY, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}
}
