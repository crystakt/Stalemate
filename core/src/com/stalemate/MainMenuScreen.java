package com.stalemate;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen implements Screen
{
    final Stalemate game;

    OrthographicCamera camera;

//    Stage stage;
//    TextButton playButton;
//    TextButton optionButton;
//    TextButton exitButton;
//    TextButton.TextButtonStyle textButtonStyle;


    public MainMenuScreen(final Stalemate gam)
    {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);

//        stage = new Stage();

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Stalemate", 180, 420);
        game.font.draw(game.batch, "Tap anywhere to begin!", 180, 380);
        game.batch.end();

        if(Gdx.input.isTouched())
        {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume(){}

    @Override
    public void dispose() {}
}
