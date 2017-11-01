package com.tfs.darkworld.game;

import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.GameplayState;
import com.tfs.darkworld.states.LoadingState;
import com.tfs.darkworld.states.MenuState;
import com.tfs.darkworld.states.PauseState;

import rafgfxlib.GameHost;

public class Game {
	
	private GameHost mGameHost;

	public Game() {
		super();
		mGameHost = new GameHost("Dark World", 800, 600, false);
		mGameHost.setUpdateRate(60);
		
		new LoadingState(mGameHost);
		new MenuState(mGameHost);
		new GameplayState(mGameHost);
		new PauseState(mGameHost);
	}
	
	public void start(){
		mGameHost.setState(Strings.LOADING_SATE);
	}
	
	
	
}
