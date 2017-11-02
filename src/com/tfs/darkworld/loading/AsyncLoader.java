package com.tfs.darkworld.loading;

import java.util.ArrayList;
import java.util.List;

import com.tfs.darkworld.loading.tasks.ITask;

public class AsyncLoader extends Thread {
	private List<ITask> tasks;
	
	private IProgressListener progressListener;
	private ITaskListener taskListener;
	
	private IProgressListener internalProgress;
	private ITaskListener internalTask;
	
	private float currentProgress = 0;
	
	public void setProgressListener(IProgressListener progressListener) {
		this.progressListener = progressListener;
	}
	
	public void setTaskListener(ITaskListener taskListener) {
		this.taskListener = taskListener;
	}
	
	public void addTask(ITask task) {
		tasks.add(task);
	}
	
	public void removeTaks(ITask task) {
		tasks.remove(task);
	}
	
	public AsyncLoader() {
		tasks = new ArrayList<>();
		internalProgress = new IProgressListener() {
			
			@Override
			public void updateProgress(int progress) {
				if (progressListener != null) {
					currentProgress += (float)progress/tasks.size();
					progressListener.updateProgress((int)currentProgress);
				}
			}
		};
		internalTask = new ITaskListener() {
			
			@Override
			public void updateTaskName(String taskName) {
				if (taskListener != null) {
					taskListener.updateTaskName(taskName);
				}
			}
		};
	}
	
	
	
	
	
	public void run() {
		System.out.println("Async Thread started");
		currentProgress = 0;
		if (progressListener != null) {
			progressListener.updateProgress(0);
		}
		for (ITask task : tasks) {
			task.doTask(internalProgress,internalTask);
		}
	}
	
	
}
