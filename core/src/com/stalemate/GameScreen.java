package com.stalemate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by suchi on 4/21/16.
 */
public class GameScreen implements Screen
{
    final Stalemate game;

    public PerspectiveCamera camera;
    public ModelBatch modelBatch;
    public ModelBuilder modelBuilder;
    public Model model;
    public ModelInstance modelInstance;
    public Environment environment;
    public CameraInputController camController;

    public GameScreen(final Stalemate gam)
    {
        this.game = gam;

        camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 0f, 3f);
        camera.lookAt(0f, 0f, 0f);
        camera.near = .1f;
        camera.far  = 100f;

        modelBatch = new ModelBatch();
        modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(1f, 1f, 1f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position  | VertexAttributes.Usage.Normal);
        modelInstance = new ModelInstance(model, 0, 0, 0);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);
    }

    @Override
    public void render(float delta)
    {
//        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

//        camera.rotateAround(Vector3.Zero, new Vector3(0,1,0), 1f);
        camera.update();

        camController.update();

        modelBatch.begin(camera);
        modelBatch.render(modelInstance, environment);
        modelBatch.end();
    }


    @Override
    public void dispose()
    {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

}
