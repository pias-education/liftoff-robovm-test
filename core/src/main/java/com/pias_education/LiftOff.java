package com.pias_education;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class LiftOff extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        viewport = new ScreenViewport();
        angle = 0f;
    }

    Viewport viewport;
    float angle;

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderMovingLogo(batch);
    }

    /** This renders a moving libGDX logo, might get skewed as the viewport
     *  does not properly work when resizing the app
     **/
    private void renderMovingLogo(SpriteBatch batch) {
        batch.begin();
        angle = angle + 2 * Gdx.graphics.getDeltaTime();
        float w = 0.7f * image.getWidth();
        float h = 0.7f * image.getHeight();
        float x = 0.5f * (Gdx.graphics.getWidth() - w + 100f * MathUtils.cos(angle));
        float y = 0.5f * (Gdx.graphics.getHeight() - h + 100f * MathUtils.sin(angle));
        batch.draw(image, x, y, w, h);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("RESIZE", "" + width + ", " + height);
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
