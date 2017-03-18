package com.example.ben1597.scheduler.addSchedule;

import android.support.annotation.NonNull;

import com.example.ben1597.scheduler.data.source.Task;
import com.example.ben1597.scheduler.data.source.TasksLocalDataSource;

import java.util.Date;

/**
 * Created by ben1597 on 2017/3/8.
 */

public class AddSchedulePresenter implements AddScheduleContract.Presenter {

    @NonNull
    private final AddScheduleContract.View mShowScheduleView;

    @NonNull
    private final TasksLocalDataSource mTasksRepository;

    public AddSchedulePresenter(@NonNull TasksLocalDataSource tasksRepository,
                                @NonNull AddScheduleContract.View view
    ) {
        mShowScheduleView = view;
        mTasksRepository = tasksRepository;
    }

    @Override
    public void SaveSchedule(String title, String description, Integer type, int startTime, int duration) {
        Task task = new Task(title, type, description, startTime, duration);
        if (task.isEmpty()) {
            mShowScheduleView.showEmptyScheduleError();
        } else {
            mTasksRepository.saveTask(task);
            mShowScheduleView.showScheduleList();
        }
    }
}
