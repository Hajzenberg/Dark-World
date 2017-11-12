package com.tfs.darkworld.loading.tasks;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.states.GameToPauseTransitionState;
import com.tfs.darkworld.states.GameplayState;
import com.tfs.darkworld.states.MenuState;
import com.tfs.darkworld.states.PauseState;
import com.tfs.darkworld.states.AboutState;
import com.tfs.darkworld.states.DiedState;
import com.tfs.darkworld.states.Transition;

import rafgfxlib.GameHost;

public class StateLoading implements ITask {

	private GameHost gameHost;
	
	public StateLoading(GameHost gameHost) {
		super();
		if (gameHost == null) {
			throw new IllegalArgumentException();
		}
		this.gameHost = gameHost;
	}

	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {
		
		progressListener.updateProgress(0);
		taskListener.updateTaskName("Loading transition state...");
		new Transition(gameHost);
		progressListener.updateProgress(20);
		taskListener.updateTaskName("Loading menu state...");
		new MenuState(gameHost);
		progressListener.updateProgress(20);
		taskListener.updateTaskName("Loading gameplay state...");
		new GameplayState(gameHost);
		progressListener.updateProgress(20);
		taskListener.updateTaskName("Loading pause state...");
		new PauseState(gameHost);
		progressListener.updateProgress(20);
		taskListener.updateTaskName("Loading game to pause state...");
		new GameToPauseTransitionState(gameHost);
		progressListener.updateProgress(10);
		taskListener.updateTaskName("Loading died state...");
		new DiedState(gameHost);
		taskListener.updateTaskName("Loading about state...");
		new AboutState(gameHost);
		progressListener.updateProgress(10);
	}

}
