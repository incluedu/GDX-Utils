package net.lustenauer.gdx.util.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @since Version 1.1.0
 */
public abstract class Box2DEntity {

    /* CONSTANTS */

    /* ATTRIBUTES */
    protected final World world;

    protected Body body;
    protected Animation<TextureRegion> animation;
    protected float animationStateTime;
    protected float width, height;
    protected float x, y;
    protected float ppm = 100;

    /* CONSTRUCTORS */
    public Box2DEntity(World world, float x, float y, float width, float height) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        createBody();
    }

    /* PUBLIC METHODS */
    public void initAnimation(TextureRegion[] textures, float frameDuration) {
        initAnimation(textures, frameDuration, Animation.PlayMode.LOOP);
    }

    public void initAnimation(TextureRegion[] textures, float frameDuration, Animation.PlayMode playMode) {
        animation = new Animation<TextureRegion>(
                frameDuration,
                textures
        );
        animation.setPlayMode(playMode);
    }

    public void update(float delta) {
        // update position from body
        x = body.getPosition().x * ppm;
        y = body.getPosition().y * ppm;

        // increment animation state time
        animationStateTime += delta;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                animation.getKeyFrame(animationStateTime),
                getX() - getWidth() / 2, getY() - getHeight() / 2,
                getWidth(), getHeight()
        );
    }


    public Body getBody() {
        return body;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    /**
     * Set the pixels per meter to the given value.<p><p>
     * <b>Note:</b><p>
     * The default value off this is 100, if you ok with 100 you
     * not have to use this method.
     *
     * @param ppm as a float value
     */
    public void setPpm(float ppm) {
        this.ppm = ppm;
    }

    /* PRIVATE METHODS */
    protected abstract void createBody();

}
