package com.example.ben1597.scheduler.util.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.ben1597.scheduler.showSchedule.ShowSchedulePresenterTest.SchedulesForTest;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by ben1597 on 2017/3/22.
 */
public class SchedulesAdapterTest {

    @Mock
    private SchedulesAdapter schedulesAdapter;
    @Mock
    private SchedulesAdapter.ScheduleItemListener scheduleItemListener;
    @Mock
    private RecyclerView recyclerView;
    @Mock
    private Context context;

    @Before
    public void initSchedulesAdapter(){
        MockitoAnnotations.initMocks(this);
        schedulesAdapter = new SchedulesAdapter(SchedulesForTest,scheduleItemListener);
    }

    @Test
    public void setSchedulesAdapter(){
        recyclerView.setAdapter(schedulesAdapter);
        int numColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(context, numColumns));

    }


}