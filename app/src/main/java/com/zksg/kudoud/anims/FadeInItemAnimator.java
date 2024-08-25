package com.zksg.kudoud.anims;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class FadeInItemAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        view.setAlpha(0f);
        view.animate()
                .alpha(1f)
                .setDuration(500)
                .setListener(new SimpleAnimatorListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dispatchAddFinished(holder);
                    }
                });
        return true;
    }

    @Override
    public void runPendingAnimations() {
        super.runPendingAnimations();
    }

    // 自定义的简单 AnimatorListener
    private static class SimpleAnimatorListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) { }
        @Override
        public void onAnimationEnd(Animator animation) { }
        @Override
        public void onAnimationCancel(Animator animation) { }
        @Override
        public void onAnimationRepeat(Animator animation) { }
    }
}
