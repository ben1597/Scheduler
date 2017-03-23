package com.example.ben1597.scheduler.showSchedule;

import android.support.annotation.NonNull;

import com.example.ben1597.scheduler.data.source.Task;
import com.example.ben1597.scheduler.data.source.TasksLocalDataSource;
import com.example.ben1597.scheduler.util.schedulers.BaseSchedulerProvider;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ben1597 on 2017/3/6.
 */

public class ShowSchedulePresenter implements ShowScheduleContract.Presenter {

    @NonNull
    private final ShowScheduleContract.View mShowScheduleView;

    @NonNull
    private final TasksLocalDataSource mTasksRepository;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeSubscription mSubscriptions;


    ShowSchedulePresenter(@NonNull TasksLocalDataSource tasksRepository,
                          @NonNull ShowScheduleContract.View view,
                          @NonNull BaseSchedulerProvider schedulerProvider) {
        mTasksRepository = tasksRepository;
        mShowScheduleView = checkNotNull(view);
        mSchedulerProvider = schedulerProvider;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void loadTasks(boolean forceUpdate) {

        mSubscriptions.clear();
        Subscription subscription = mTasksRepository.getTasks()
                .flatMap(new Func1<List<Task>, Observable<Task>>() {
                    @Override
                    public Observable<Task> call(List<Task> tasks) {
                        return Observable.from(tasks);
                    }
                })
                .toSortedList(new Func2<Task, Task, Integer>() {
                    @Override
                    public Integer call(Task task, Task task2) {
                        return compareTaskStartTime(task.getStartTimes(),task2.getStartTimes());
                    }
                })
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Observer<List<Task>>() {
                    @Override
                    public void onCompleted() {
                        mShowScheduleView.setLoadingIndicator(false);
                    }

                    @Override
                    public void onError(Throwable e) {
//                        mTasksView.showLoadingTasksError();
                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        mShowScheduleView.showTasks(tasks);
                        // processTasks(tasks);
                    }
                });
        mSubscriptions.add(subscription);
//        mShowScheduleView.showTasks(task);
    }

    private int compareTaskStartTime(String startTime, String startTime2) {

        String[] arrStartTime = startTime.split(":");
        String[] arrStartTime2 = startTime2.split(":");
        int intStartTime = Integer.parseInt(arrStartTime[0])*60+Integer.parseInt(arrStartTime[1]);
        int intStartTime2 = Integer.parseInt(arrStartTime2[0])*60+Integer.parseInt(arrStartTime2[1]);

        return intStartTime - intStartTime2;
    }
}
