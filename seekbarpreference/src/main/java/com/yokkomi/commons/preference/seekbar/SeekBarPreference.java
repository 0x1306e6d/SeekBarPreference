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
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarPreference extends DialogPreference {

    private static final String TAG = SeekBarPreference.class.getSimpleName();
    private static final String ANDROID = "http://schemas.android.com/apk/res/android";

    private int padding;
    private String unit;
    private String explain;

    private double currentValue;
    private SeekBar seekBar;
    private TextView valueText;
    private TextView unitText;
    private LinearLayout valueLayout;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SeekBarPreference(Context context) {
        this(context, null);
    }

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        configure(context, attrs);
        init(context, attrs);
    }

    private void configure(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SeekBarPreference);
        try {
            padding = attributes.getInt(R.styleable.SeekBarPreference_padding, 0);
            unit = attributes.getString(R.styleable.SeekBarPreference_unit);
            explain = attributes.getString(R.styleable.SeekBarPreference_explain);
        } finally {
            attributes.recycle();
        }
    }

    private void init(Context context, AttributeSet attrs) {
        seekBar = new SeekBar(context, attrs);
        valueText = new TextView(context);
        valueText.setText(String.valueOf(currentValue));

        unitText = new TextView(context);
        unitText.setText(unit);

        valueLayout = new LinearLayout(context);
        valueLayout.setOrientation(LinearLayout.HORIZONTAL);
        valueLayout.setGravity(Gravity.CENTER);
        valueLayout.addView(valueText);
        valueLayout.addView(unitText);
    }

    @Override
    protected View onCreateDialogView() {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(padding, padding, padding, padding);

        TextView explainText = new TextView(getContext());
        explainText.setText(explain);
        explainText.setGravity(Gravity.CENTER);
        layout.addView(explainText);

        return layout;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        clearParents();

        LinearLayout.LayoutParams params;
        LinearLayout layout = (LinearLayout) view;

        params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layout.addView(valueLayout, params);
        layout.addView(seekBar, params);
    }

    private void clearParents() {
        ViewGroup parent = (ViewGroup) seekBar.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
    }

}
