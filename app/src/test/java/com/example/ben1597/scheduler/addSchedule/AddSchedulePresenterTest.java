package com.example.ben1597.scheduler.addSchedule;

import com.example.ben1597.scheduler.data.source.Task;
import com.example.ben1597.scheduler.data.source.TasksDataSource;
import com.example.ben1597.scheduler.data.source.TasksLocalDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by ben1597 on 2017/3/14.
 */

public class AddSchedulePresenterTest {

    @Mock
    private TasksLocalDataSource mtasksDataSource;

    @Mock
    private AddScheduleContract.View mAddScheduleView;

    private AddSchedulePresenter mAddSchedulePresenter;

    @Before
    public void setupAddSchedulePresenterTest(){

        MockitoAnnotations.initMocks(this);
        mAddSchedulePresenter = new AddSchedulePresenter(mtasksDataSource,mAddScheduleView);
    }

    @Test
    public void saveScheduleToRepository_showsSuccessMessageUi(){
        mAddSchedulePresenter.SaveSchedule("Sport", "good", 0, 0, 3);
        verify(mtasksDataSource).saveTask(any(Task.class)); // saved to the model
        verify(mAddScheduleView).showScheduleList();
    }

    @Test
    public void saveNote_emptyNoteShowsErrorUi() {
        // When the presenter is asked to save an empty note
        mAddSchedulePresenter.SaveSchedule("", "", 0 , 0, 0);

        // Then an empty not error is shown in the UI
        verify(mAddScheduleView).showEmptyScheduleError();
    }
}
