/*
 *    Copyright 2016 com.yokkomi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.yokkomi.commons.preference.seekbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class FollowSeekBarPreference extends SeekBarPreference {

    private static final String TAG = FollowSeekBarPreference.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FollowSeekBarPreference(Context context) {
        super(context);
    }

    public FollowSeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FollowSeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FollowSeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        Log.d(TAG, "onBindDialogView");

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) valueLayout.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        valueLayout.setLayoutParams(params);

        updateView();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        setCurrentValue(progress);
        valueLayout.setX(calculateThumbX());
    }

    private float calculateThumbX() {
        float x;
        Drawable thumb = seekBar.getThumb();
        float centerX = thumb.getBounds().exactCenterX();
        x = centerX - (thumb.getBounds().width() / 2.0f);
        return x;
    }
}
