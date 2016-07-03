package hu.gabornovak.movieapp.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.GenericDraweeView;

/**
 *  Shared transition fix: https://gist.github.com/burzumrus/a589aa7e36ca003ddaf2334218c50ad0
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class DraweeTransform extends Transition {

    private static final String PROPNAME_BOUNDS = "draweeTransform:bounds";
    private final ScalingUtils.ScaleType fromScale;
    private final ScalingUtils.ScaleType toScale;

    public DraweeTransform(ScalingUtils.ScaleType fromScale, ScalingUtils.ScaleType toScale) {
        this.fromScale = fromScale;
        this.toScale = toScale;
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof GenericDraweeView) {
            transitionValues.values.put(PROPNAME_BOUNDS, new Rect(0, 0, transitionValues.view.getWidth(), transitionValues.view.getHeight()));
        }
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null || !(startValues.view instanceof GenericDraweeView)) {
            return null;
        }

        final GenericDraweeView draweeView = (GenericDraweeView) startValues.view;
        Rect startBounds = (Rect) startValues.values.get(PROPNAME_BOUNDS);
        Rect endBounds = (Rect) endValues.values.get(PROPNAME_BOUNDS);

        final InterpolatingScaleType scaleType = new InterpolatingScaleType(fromScale, toScale, startBounds, endBounds);
        draweeView.getHierarchy().setActualImageScaleType(scaleType);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = (float) animation.getAnimatedValue();
                scaleType.setValue(fraction);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                draweeView.getHierarchy().setActualImageScaleType(toScale);
            }
        });

        return animator;
    }

    public static class InterpolatingScaleType implements ScalingUtils.ScaleType {

        private final ScalingUtils.ScaleType mScaleTypeFrom;
        private final ScalingUtils.ScaleType mScaleTypeTo;
        private final Rect mStartBounds;
        private final Rect mEndBounds;
        private final float[] mMatrixValuesFrom = new float[9];
        private final float[] mMatrixValuesTo = new float[9];
        private final float[] mMatrixValuesInterpolated = new float[9];

        private float mInterpolatingValue;

        public InterpolatingScaleType(ScalingUtils.ScaleType scaleTypeFrom, ScalingUtils.ScaleType scaleTypeTo, Rect startBounds, Rect endBounds) {
            mScaleTypeFrom = scaleTypeFrom;
            mScaleTypeTo = scaleTypeTo;
            mStartBounds = startBounds;
            mEndBounds = endBounds;
        }

        public void setValue(float value) {
            mInterpolatingValue = value;
        }

        @Override
        public Matrix getTransform(
                Matrix transform,
                Rect parentBounds,
                int childWidth,
                int childHeight,
                float focusX,
                float focusY) {
            mScaleTypeFrom.getTransform(transform, mStartBounds, childWidth, childHeight, focusX, focusY);
            transform.getValues(mMatrixValuesFrom);
            mScaleTypeTo.getTransform(transform, mEndBounds, childWidth, childHeight, focusX, focusY);
            transform.getValues(mMatrixValuesTo);

            for (int i = 0; i < 9; i++) {
                mMatrixValuesInterpolated[i] = mMatrixValuesFrom[i] * (1 - mInterpolatingValue) +
                        mMatrixValuesTo[i] * mInterpolatingValue;
            }

            transform.setValues(mMatrixValuesInterpolated);
            Log.d("MATRIX", transform.toString());
            return transform;
        }
    }
}
