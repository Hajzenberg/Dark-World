package com.tfs.darkworld;

import com.tfs.darkworld.game.Game;

public class Main {

	public static void main(String[] args) {
		System.out.println(" Main "+Thread.currentThread().getName());
		new Game().start();
		

	}

}
