/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mfq.foodle.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

import com.mfq.foodle.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for creating content transitions used with {@link android.app.ActivityOptions}.
 */
public class TransitionHelper {

    /**
     * Create the transition participants required during a activity transition while
     * avoiding glitches with the system UI.
     *
     * @param activity The activity used as start for the transition.
     * @param includeStatusBar If false, the status bar will not be added as the transition
     *        participant.
     * @return All transition participants.
     */
    public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
                                                                        boolean includeStatusBar, @Nullable Pair... otherParticipants) {
        // Avoid system UI glitches as described here:
        // https://plus.google.com/+AlexLockwood/posts/RPtwZ5nNebb
        View decor = activity.getWindow().getDecorView();
        View statusBar = null;
        if (includeStatusBar) {
            statusBar = decor.findViewById(android.R.id.statusBarBackground);
        }
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);

        // Create pair of transition participants.
        List<Pair> participants = new ArrayList<>(3);
        addNonNullViewToTransitionParticipants(statusBar, participants);
        addNonNullViewToTransitionParticipants(navBar, participants);
        // only add transition participants if there's at least one none-null element
        if (otherParticipants != null && !(otherParticipants.length == 1
                && otherParticipants[0] == null)) {
            participants.addAll(Arrays.asList(otherParticipants));
        }
        return participants.toArray(new Pair[participants.size()]);
    }

    private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
        if (view == null || Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        participants.add(new Pair<>(view, view.getTransitionName()));
    }

    public static void addSlideEnterTransitionToFragment(Context context, Fragment fragment, int slideEdge, boolean overlap) {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP) return;
        Slide slide = (Slide) TransitionInflater.from(context).inflateTransition(R.transition.slide);
        fragment.setEnterTransition(slide);
        fragment.setExitTransition(TransitionInflater.from(context).inflateTransition(R.transition.fade));
        fragment.setAllowEnterTransitionOverlap(overlap);
        fragment.setAllowReturnTransitionOverlap(overlap);
    }


    public static void addExplodeEnterTransitionToFragment(Context context, Fragment fragment, boolean overlap) {
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP) return;
        Transition transition =  TransitionInflater.from(context).inflateTransition(R.transition.explode);
        fragment.setEnterTransition(transition);
        fragment.setExitTransition(TransitionInflater.from(context).inflateTransition(R.transition.fade));
        fragment.setAllowEnterTransitionOverlap(overlap);
        fragment.setAllowReturnTransitionOverlap(overlap);
    }

}
