package com.zk.quickindex.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndexBar extends View{
	public static final String[] LETTERS = new String[]{
		"A", "B", "C", "D",
		"E", "F", "G", "H", 
		"I", "J", "K", "L", 
		"M", "N", "O", "P", 
		"Q", "R", "S", "T", 
		"U", "V", "W", "X", 
		"Y", "Z" };
	private Paint paint;
	private int mHeight;//控件高度
	private int cellWidth;//单元格宽度
	private float cellHeight;//单元格高度
	private OnLetterUpdateListener onLetterUpdateListener;
	
	public void setOnLetterUpdateListener(
			OnLetterUpdateListener onLetterUpdateListener) {
		this.onLetterUpdateListener = onLetterUpdateListener;
	}
	public OnLetterUpdateListener getOnLetterUpdateListener() {
		return onLetterUpdateListener;
	}
	public interface OnLetterUpdateListener{
		void onLetterUpdate(String letter);
	}

	public QuickIndexBar(Context context) {
		this(context,null);
	}
	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);//设置白色画笔
		paint.setTypeface(Typeface.DEFAULT_BOLD);//设置为粗体

	}

	@Override
	protected void onDraw(Canvas canvas) {
		for(int i=0; i<LETTERS.length;i++){
			String letter = LETTERS[i];
			//计算每一个字母的横竖坐标
			float x = cellWidth/2.0f -paint.measureText(letter)/2.0f;

			//获取文本所占的矩形区域
			Rect bounds = new Rect();
			paint.getTextBounds(letter, 0, letter.length(), bounds);
			float y = cellHeight/2.0f +bounds.height()/2.0f+i*cellHeight;
			
			paint.setColor(i == touchIndex ? Color.GRAY:Color.WHITE);
			
			//绘制字母A-Z
			canvas.drawText(letter, x, y, paint);
			
		}
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		cellWidth = getMeasuredWidth();//单元格宽度
		mHeight = getMeasuredHeight();
		cellHeight = mHeight*1.0f/LETTERS.length;
	}
	int touchIndex = -1;//被按下字母
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int index;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			index = (int) (event.getY()/cellHeight);
			if (index != touchIndex) {
				if (index >= 0 && index<LETTERS.length) {
					if (onLetterUpdateListener != null) {
						onLetterUpdateListener.onLetterUpdate(LETTERS[index]);
					}
					touchIndex = index;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			index = (int) (event.getY()/cellHeight);
			if (index!=touchIndex) {
				if (index>=0&&index<LETTERS.length) {
					if (onLetterUpdateListener!=null) {
						onLetterUpdateListener.onLetterUpdate(LETTERS[index]);
					}
					touchIndex = index;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			touchIndex = -1;
			break;

		default:
			break;
		}
		invalidate();
		return true;//消费事件
	}

}
