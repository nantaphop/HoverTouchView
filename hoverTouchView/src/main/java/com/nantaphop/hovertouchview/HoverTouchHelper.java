package com.nantaphop.hovertouchview;

import android.animation.Animator;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import jp.wasabeef.blurry.Blurry;

/**
 * Created by nantaphop on 01-Jan-16.
 */
public class HoverTouchHelper {
    private static final String TAG = "HoverTouchHelper";

    public static void make(final FrameLayout root, final HoverTouchAble hoverTouchAble) {
        if (hoverTouchAble instanceof View) {
            final View v = (View) hoverTouchAble.getHoverView();
            final View src = (View) hoverTouchAble;
            root.addView(v);
            v.setScaleX(0.3f);
            v.setScaleY(0.3f);
            root.removeView(v);
            final int duration = hoverTouchAble.getHoverAnimateDuration();

            src.setOnTouchListener(new View.OnTouchListener() {

                View hoverView = v;
                View sourceView = src;

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            hoverTouchAble.onStartHover();
                            Blurry.with(root.getContext()).radius(8).sampling(2).capture(root).into(HoverTouchHelper.addBlurView(root));
                            if (hoverView.getParent() == null) {
                                root.addView(hoverView);
                            }
                            hoverView.animate()
                                    .scaleX(1)
                                    .scaleY(1)
                                    .alpha(1)
                                    .setListener(null)
                                    .setDuration(duration)
                                    .start();
                            break;
                        case MotionEvent.ACTION_UP:
                            HoverTouchHelper.removeBlurView(root, duration);
                            hoverView.animate().cancel();
                            hoverView.animate()
                                    .scaleX(0)
                                    .scaleY(0)
                                    .alpha(0)
                                    .setDuration(duration)
                                    .setListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animator) {
                                            root.removeView(hoverView);
                                            hoverTouchAble.onStopHover();
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animator) {

                                        }
                                    })
                                    .start();
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

        } else {
            throw new IllegalArgumentException("HoverTouchAble must be a View");
        }
    }

    private static ImageView addBlurView(ViewGroup root){
        ImageView blurView = (ImageView) root.findViewWithTag(TAG);
        if (blurView == null) {
            blurView = new ImageView(root.getContext());
            blurView.setTag(TAG);
            blurView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            root.addView(blurView);
        }
        return blurView;

    }



    private static void removeBlurView(final ViewGroup root, int duration) {
        final View blurView = root.findViewWithTag(TAG);
        if (blurView != null) {
            blurView.animate().alpha(0).setDuration(duration).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    root.removeView(blurView);

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            }).start();

        }
    }


}
