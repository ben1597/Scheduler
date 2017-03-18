package com.example.ben1597.scheduler.addSchedule;

import java.util.Date;

/**
 * Created by ben1597 on 2017/3/8.
 */

public interface AddScheduleContract {

    interface View{

        void showEmptyScheduleError();

        void showScheduleList();
    }

    interface Presenter{

        void SaveSchedule(String title, String description, Integer type, int startTime, int duration);

    }

}
