package com.example.sts_admin.svg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;

public class SVGConstraintLayout extends ConstraintLayout {
    private PictureDrawable pictureDrawable;

    public SVGConstraintLayout(@NonNull Context context) {
        super(context);

        init();
    }

    public SVGConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SVGConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        try {
            InputStream inputStream = getContext().getAssets().open("screen_bg_sts.svg");
            SVG svg = SVG.getFromInputStream(inputStream);
            Picture picture = svg.renderToPicture();
            pictureDrawable = new PictureDrawable(picture);
            inputStream.close();
        } catch (IOException | SVGParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        if (pictureDrawable != null) {
            pictureDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            pictureDrawable.draw(canvas);
        }

        super.dispatchDraw(canvas);
    }
}
