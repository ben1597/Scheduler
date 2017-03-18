package com.example.ben1597.scheduler.showSchedule;

import com.example.ben1597.scheduler.BasePresenter;
import com.example.ben1597.scheduler.BaseView;
import com.example.ben1597.scheduler.data.source.Task;

import java.util.List;

/**
 * Created by ben1597 on 2017/3/6.
 */

public interface ShowScheduleContract {

    interface View {

        void showTasks(List<Task> tasks);

    }

    interface Presenter {

        void loadTasks(boolean forceUpdate);

    }
}
