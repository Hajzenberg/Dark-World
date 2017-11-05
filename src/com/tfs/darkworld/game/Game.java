package com.tfs.darkworld.game;

import com.tfs.darkworld.loading.AsyncLoader;
import com.tfs.darkworld.loading.tasks.BackgroundGeneratingTask;
import com.tfs.darkworld.loading.tasks.LoadingResurcesTask;
import com.tfs.darkworld.loading.tasks.StateLoading;
import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.states.LoadingState;

import rafgfxlib.GameHost;

public class Game {
	
	private GameHost mGameHost;
	private AsyncLoader asyncLoader;
	
	public Game() {
		super();
		mGameHost = new GameHost(GameConstants.GAME_NAME, GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH, false);
		
		asyncLoader = new AsyncLoader();
		asyncLoader.addTask(new LoadingResurcesTask());
		asyncLoader.addTask(new StateLoading(mGameHost));
		asyncLoader.addTask(new BackgroundGeneratingTask());
		
		mGameHost.setUpdateRate(60);
		new LoadingState(mGameHost,asyncLoader);
	}
	
	public void start(){
		mGameHost.setState(Strings.LOADING_SATE);
	}
}
