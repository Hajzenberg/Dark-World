package com.tfs.darkworld.loading.tasks;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;

public interface ITask {
	void doTask(IProgressListener progressListener, ITaskListener taskListener);
}
