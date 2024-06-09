package com.mdsadman.project.expandablerv.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mdsadman.project.expandablerv.components.ExpandableRecyclerAdapter;
import com.mdsadman.project.expandablerv.databinding.ChildItemBinding;
import com.mdsadman.project.expandablerv.databinding.ParentItemBinding;

import java.util.List;
import java.util.Random;

public class Test {

    public static void usual(Context context, RecyclerView recyclerView) {

        // DATA
        List<TestParentModel> parentData = TestDataGenerator.getParentData();
        // DATA

        @SuppressLint("SetTextI18n")
        ExpandableRecyclerAdapter<TestParentModel, ParentItemBinding> parentAdapter =
            new ExpandableRecyclerAdapter<TestParentModel, ParentItemBinding>(context, parentData)
                .setActionOnCreateViewHolder((parentContext) -> {
                    ParentItemBinding binding = ParentItemBinding.inflate(LayoutInflater.from(parentContext), null, false);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    binding.getRoot().setLayoutParams(layoutParams);
                    return binding;
                })
                .setActionOnBindViewHolder(((parentContext, parentHolder, parentPosition, parentPassedData) -> {
                    parentHolder.getFragment().text.setText(parentPassedData.get(parentPosition).value() + " " + (parentPosition + 1));
                    parentHolder.getFragment().getRoot().setOnClickListener(v -> {
                        interface IRotationAnimation {
                            void animate(float fromDeg, float toDeg, String tag);
                        }

                        IRotationAnimation iRotationAnimation = (float fromDeg, float toDeg, String tag) -> {
                            RotateAnimation rotate = new RotateAnimation(
                                    fromDeg, toDeg,
                                    (float) parentHolder.getFragment().arrow.getWidth() / 2,
                                    (float) parentHolder.getFragment().arrow.getHeight() / 2
                            );
                            rotate.setDuration(250);
                            parentHolder.getFragment().arrow.startAnimation(rotate);
                            rotate.setFillAfter(true);
                            parentHolder.getFragment().arrow.setTag(tag);
                        };

                        if (parentHolder.getFragment().arrow.getTag() != null &&
                                parentHolder.getFragment().arrow.getTag().equals("rotateFor90")) { // CLOSE
                            iRotationAnimation.animate(90, 0, "rotateBack90");

                            parentHolder.getFragment().rvChild.clearAnimation();
                            TranslateAnimation animation = new TranslateAnimation(
                                    0, 0,
                                    0, -parentHolder.getFragment().rvChild.getChildCount() * parentHolder.getFragment().rvChild.getChildAt(0).getHeight()
                            );
                            animation.setDuration(250);
                            parentHolder.getFragment().rvChild.startAnimation(animation);
                            animation.setFillAfter(true);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    parentHolder.getFragment().rvChild.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        } else { // OPEN
                            iRotationAnimation.animate(0, 90, "rotateFor90");

                            // DATA
                            List<TestChildModel> childData = TestDataGenerator.getChildData(new Random().nextInt(12));
                            // DATA

                            ExpandableRecyclerAdapter<TestChildModel, ChildItemBinding> childAdapter =
                                    new ExpandableRecyclerAdapter<TestChildModel, ChildItemBinding>(parentContext, childData)
                                            .setActionOnCreateViewHolder((childContext) -> {
                                                ChildItemBinding binding = ChildItemBinding.inflate(LayoutInflater.from(childContext), null, false);
                                                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                                                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                                                );
                                                binding.getRoot().setLayoutParams(layoutParams);
                                                return binding;
                                            })
                                            .setActionOnBindViewHolder((childContext, childHolder, childPosition, childPassedData) -> {
                                                // PASS
                                                childHolder.getFragment().text.setText(childPassedData.get(childPosition).value() + " " + (childPosition + 1));
                                            })
                                            .setActionGetItemCount(childData::size);

                            parentHolder.getFragment().rvChild.setVisibility(View.VISIBLE);
                            parentHolder.getFragment().rvChild.setLayoutManager(new LinearLayoutManager(parentContext));
                            parentHolder.getFragment().rvChild.clearAnimation();
                            if (parentHolder.getFragment().rvChild.getAdapter() == null) {
                                parentHolder.getFragment().rvChild.setAdapter(childAdapter);
                            } else parentHolder.getFragment().rvChild.swapAdapter(childAdapter, true);
                            parentHolder.getFragment().rvChild.scheduleLayoutAnimation();
                            TranslateAnimation animation = new TranslateAnimation(
                                    0, 0,
                                    -1000, 0
                            );
                            animation.setDuration(250);
                            parentHolder.getFragment().rvChild.startAnimation(animation);
                            animation.setFillAfter(true);
                        }
                    });
                }));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(parentAdapter);
    }

}
