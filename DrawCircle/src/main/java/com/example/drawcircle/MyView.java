package com.example.drawcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	public float X = 50;
	public float Y = 50;

	Paint paint = new Paint();

	public MyView(Context context, AttributeSet set) {
		super(context,set);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.RED);
		canvas.drawCircle(X,Y,30,paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.X = event.getX();
		this.Y = event.getY();

		this.invalidate();
		return true;
	}
}
