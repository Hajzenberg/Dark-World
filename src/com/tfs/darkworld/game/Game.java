package com.tfs.darkworld.game;

import com.tfs.darkworld.loading.AsyncLoader;
import com.tfs.darkworld.loading.tasks.BackgroundGeneratingTask;
import com.tfs.darkworld.loading.tasks.LoadingResurcesTask;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.states.GameplayState;
import com.tfs.darkworld.states.LoadingState;
import com.tfs.darkworld.states.MenuState;
import com.tfs.darkworld.states.PauseState;
import com.tfs.darkworld.states.Transition;

import rafgfxlib.GameHost;

public class Game {
	
	private GameHost mGameHost;
	private AsyncLoader asyncLoader;
	
	public Game() {
		super();
		asyncLoader = new AsyncLoader();
		asyncLoader.addTask(new LoadingResurcesTask());
		asyncLoader.addTask(new BackgroundGeneratingTask());
		
		mGameHost = new GameHost("Dark World", 800, 600, false);
		mGameHost.setUpdateRate(60);
		
		new Transition(mGameHost);
		new LoadingState(mGameHost,asyncLoader);
		new MenuState(mGameHost);
		new GameplayState(mGameHost);
		new PauseState(mGameHost);
	}
	
	public void start(){
		System.out.println(" start() "+Thread.currentThread().getName());
		mGameHost.setState(Strings.LOADING_SATE);
	}
	
	
	
}
