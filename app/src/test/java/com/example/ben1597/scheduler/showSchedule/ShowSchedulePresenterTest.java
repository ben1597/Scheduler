package com.example.ben1597.scheduler.showSchedule;

import com.example.ben1597.scheduler.data.source.Task;
import com.example.ben1597.scheduler.data.source.TasksLocalDataSource;
import com.example.ben1597.scheduler.util.schedulers.BaseSchedulerProvider;
import com.example.ben1597.scheduler.util.schedulers.ImmediateSchedulerProvider;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ben1597 on 2017/3/20.
 */

public class ShowSchedulePresenterTest {


    public static List<Task> SchedulesForTest = Lists.newArrayList(new Task("Title1", 0, "Description1", "10:00", "2"),
            new Task("Title2", 0, "Description2", "15:00", "3"));

    @Mock
    private TasksLocalDataSource mtasksDataSource;
    @Mock
    private ShowScheduleContract.View mShowScheduleView;

    private ShowSchedulePresenter mShowSchedulePresenter;

    private BaseSchedulerProvider mSchedulerProvider;

    @Before
    public void setupAddSchedulePresenterTest() {

        // Make the sure that all schedulers are immediate.
        mSchedulerProvider = new ImmediateSchedulerProvider();

        MockitoAnnotations.initMocks(this);
        mShowSchedulePresenter = new ShowSchedulePresenter(mtasksDataSource, mShowScheduleView, mSchedulerProvider);

    }

    @Test
    public void loadTasks() {

        //給getTask假資料
        when(mtasksDataSource.getTasks()).thenReturn(Observable.just(SchedulesForTest));

        //執行loadTask跟驗證
        mShowSchedulePresenter.loadTasks(false);
        verify(mShowScheduleView).showTasks(SchedulesForTest);


    }


}
