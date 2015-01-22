package com.estudioskelon.columnas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameActivity extends Activity implements OnTouchListener {

	static Field field;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		field = (Field) findViewById(R.id.field);
		field.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getX() < view.getWidth() * 0.50) {
			System.out.println("A la izquierda");
			field.left();
		} else if (event.getX() > view.getWidth() * 0.50) {
			System.out.println("A la derecha");
			field.right();
		}
		return false;
	}

}
