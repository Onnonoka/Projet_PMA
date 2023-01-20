package fr.univ.projet_pma;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends View implements View.OnTouchListener {

    private static final String TAG = PieChart.class.getName();

    private Context _ctx;

    private Paint _paint;

    private List<Integer> _pieElementValue;
    private int _selectedElement;

    private int _strokeWidth;
    private int _selectedStrokeWidth;
    private RectF _rect;

    public PieChart(Context context) {
        super(context);
        _ctx = context;
        _paint = new Paint();
        _pieElementValue = new ArrayList<>();
        _selectedElement = -1;
        computeSize();
    }

    public PieChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        _ctx = context;
        _paint = new Paint();
        _pieElementValue = new ArrayList<>();
        _selectedElement = -1;
        computeSize();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        computeSize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (_pieElementValue != null) {
            final int sum = _pieElementValue.stream().mapToInt(Integer::intValue).sum();
            float startAngle = 0;
            for (int i = 0; i < _pieElementValue.size(); i++) {
                float angle = _pieElementValue.get(i) * (360 - (_pieElementValue.size() * 5)) / (float) sum;

                /*Resources resources = _ctx.getResources();

                int colorPrimaryId = resources.getIdentifier("colorPrimary", "color", _ctx.getPackageName());
                int colorPrimary = ResourcesCompat.getColor(resources, colorPrimaryId, null);*/

                _paint.setColor(getResources().getColor(R.color.md_theme_light_primary));
                _paint.setStyle(Paint.Style.STROKE);
                if (i == _selectedElement) {
                    Log.i(TAG, "Selected");
                    _paint.setStrokeWidth(_selectedStrokeWidth);
                    canvas.drawArc(_rect, startAngle - 5, angle + 10, false, _paint);
                } else {
                    _paint.setStrokeWidth(_strokeWidth);
                    canvas.drawArc(_rect, startAngle, angle, false, _paint);
                }

                Log.i(TAG, "startAngle = " + startAngle + ", angle = " + angle);

                startAngle += angle + 5;
            }
        }
    }

    private void computeSize() {
        int minBound = Math.min(getWidth(), getHeight());
        _strokeWidth = (int) Math.round(minBound * 0.15);
        _selectedStrokeWidth = (int) Math.round(minBound * 0.18);
        int startBound = (getWidth() / 2) - (minBound / 2) + getPaddingStart() + _selectedStrokeWidth;
        int endBound = (getWidth() / 2) + (minBound / 2) - getPaddingEnd() - _selectedStrokeWidth;
        int topBound = (getHeight() / 2) - (minBound / 2) + getPaddingTop() + _selectedStrokeWidth;
        int bottomBound = (getHeight() / 2) + (minBound / 2) - getPaddingBottom() - _selectedStrokeWidth;
        _rect = new RectF(startBound, topBound, endBound, bottomBound );
    }

    public void addElement(Integer value) {
        _pieElementValue.add(value);
        invalidate(); // To draw with the updated version
    }

    public void addElement(List<Integer> values) {
        _pieElementValue.addAll(values);
        invalidate(); // To draw with the updated version
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
