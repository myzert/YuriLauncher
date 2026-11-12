package net.kdt.pojavlaunch.customcontrols.mouse;

import static net.kdt.pojavlaunch.Tools.currentDisplayMetrics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.movtery.zalithlauncher.setting.AllSettings;
import com.movtery.zalithlauncher.setting.AllStaticSettings;
import com.movtery.zalithlauncher.utils.ZHTools;
import com.movtery.zalithlauncher.utils.image.Dimension;
import com.movtery.zalithlauncher.utils.image.ImageUtils;

import net.kdt.pojavlaunch.GrabListener;

import org.lwjgl.glfw.CallbackBridge;

/**
 * Class dealing with the virtual mouse
 */
public class Touchpad extends View implements GrabListener, AbstractTouchpad {
    /* Whether the Touchpad should be displayed */
    private boolean mDisplayState;
    /* Mouse pointer icon used by the touchpad */
    private Drawable mMousePointerDrawable;
    private float mMouseX, mMouseY;

    public Touchpad(@NonNull Context context) {
        this(context, null);
    }

    public Touchpad(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /** Enable the touchpad */
    private void _enable(){
        setVisibility(VISIBLE);
        placeMouseAt(currentDisplayMetrics.widthPixels / 2f, currentDisplayMetrics.heightPixels / 2f);
        if (mMousePointerDrawable instanceof Animatable) {
            ((Animatable) mMousePointerDrawable).start();
        }
    }

    /** Disable the touchpad and hides the mouse */
    private void _disable(){
        setVisibility(GONE);
        if (mMousePointerDrawable instanceof Animatable) {
            ((Animatable) mMousePointerDrawable).stop();
        }
    }

    /** @return The new state, enabled or disabled */
    public boolean switchState(){
        mDisplayState = !mDisplayState;
        if(!CallbackBridge.isGrabbing()) {
            if(mDisplayState) _enable();
            else _disable();
        }
        return mDisplayState;
    }

    public void placeMouseAt(float x, float y) {
        mMouseX = x;
        mMouseY = y;
        updateMousePosition();
    }

    private void sendMousePosition() {
        CallbackBridge.sendCursorPos((mMouseX * AllStaticSettings.scaleFactor), (mMouseY * AllStaticSettings.scaleFactor));
    }

    private void updateMousePosition() {
        sendMousePosition();
        // I wanted to implement a dirty rect for this, but it is ignored since API level 21
        // (which is our min API)
        // Let's hope the "internally calculated area" is good enough.
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mMousePointerDrawable == null) return;
        canvas.save();
        canvas.translate(mMouseX, mMouseY);
        mMousePointerDrawable.draw(canvas);
        canvas.restore();
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable who) {
        return who == mMousePointerDrawable || super.verifyDrawable(who);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mMousePointerDrawable instanceof Animatable && getVisibility() == VISIBLE) {
            ((Animatable) mMousePointerDrawable).start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mMousePointerDrawable instanceof Animatable) {
            ((Animatable) mMousePointerDrawable).stop();
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (mMousePointerDrawable instanceof Animatable) {
            if (visibility == VISIBLE && getVisibility() == VISIBLE) {
                ((Animatable) mMousePointerDrawable).start();
            } else {
                ((Animatable) mMousePointerDrawable).stop();
            }
        }
    }

    private void init(){
        // Setup mouse pointer
        updateMouseDrawable();
        // For some reason it's annotated as Nullable even though it doesn't seem to actually
        // ever return null
        assert mMousePointerDrawable != null;

        updateMouseScale();

        setFocusable(false);
        setDefaultFocusHighlightEnabled(false);

        // When the game is grabbing, we should not display the mouse
        disable();
        mDisplayState = false;
    }

    public void updateMouseScale() {
        if (mMousePointerDrawable == null) return;
        int intrinsicWidth = mMousePointerDrawable.getIntrinsicWidth();
        int intrinsicHeight = mMousePointerDrawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0) intrinsicWidth = 48;
        if (intrinsicHeight <= 0) intrinsicHeight = 48;
        Dimension mousescale = ImageUtils.resizeWithRatio(intrinsicWidth, intrinsicHeight,
                AllSettings.getMouseScale().getValue());
        mMousePointerDrawable.setBounds(0, 0, (int) (mousescale.width * 0.5), (int) (mousescale.height * 0.5));
    }

    public void updateMouseDrawable() {
        if (mMousePointerDrawable instanceof Animatable) {
            ((Animatable) mMousePointerDrawable).stop();
        }
        if (mMousePointerDrawable != null) {
            mMousePointerDrawable.setCallback(null);
        }
        mMousePointerDrawable = ZHTools.customMouse(getContext());
        if (mMousePointerDrawable != null) {
            mMousePointerDrawable.setCallback(this);
            if (mMousePointerDrawable instanceof Animatable && getVisibility() == VISIBLE) {
                ((Animatable) mMousePointerDrawable).start();
            }
        }
        updateMouseScale();
        invalidate();
    }

    @Override
    public void onGrabState(boolean isGrabbing) {
        post(()->updateGrabState(isGrabbing));
    }
    private void updateGrabState(boolean isGrabbing) {
        if(!isGrabbing) {
            if(mDisplayState && getVisibility() != VISIBLE) _enable();
            if(!mDisplayState && getVisibility() == VISIBLE) _disable();
        }else{
            if(getVisibility() != View.GONE) _disable();
        }
    }

    @Override
    public boolean getDisplayState() {
        return mDisplayState;
    }

    @Override
    public void applyMotionVector(float x, float y) {
        mMouseX = Math.max(0, Math.min(currentDisplayMetrics.widthPixels, mMouseX + x * (AllSettings.getMouseSpeed().getValue() / 100f)));
        mMouseY = Math.max(0, Math.min(currentDisplayMetrics.heightPixels, mMouseY + y * (AllSettings.getMouseSpeed().getValue() / 100f)));
        updateMousePosition();
    }

    @Override
    public void enable(boolean supposed) {
        if(mDisplayState) return;
        mDisplayState = true;
        if(supposed && CallbackBridge.isGrabbing()) return;
        _enable();
    }

    @Override
    public void disable() {
        if(!mDisplayState) return;
        mDisplayState = false;
        _disable();
    }
}
