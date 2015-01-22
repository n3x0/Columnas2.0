package com.estudioskelon.columnas;

import java.util.Random;

import android.graphics.Color;

public class Colorator {
	int set;
	static int[] colours = {
			Color.rgb(41, 128, 185),
			Color.rgb(46, 204, 113),
			Color.rgb(241, 196, 15),
			Color.rgb(192, 57, 43),
			//purple
			Color.rgb(142, 68, 173)
			//orange
			Color.rgb(211, 84, 0)}
	
	public Colorator(int set){
		this.set = set;
	}
	
	public static int getColor(){
		//some day we will add the set
		return colours[new Random().nextInt(colours.length)];
	}
}
