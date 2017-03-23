package com.example.ben1597.scheduler.addSchedule;

/**
 * Created by ben1597 on 2017/3/8.
 */

public interface AddScheduleContract {

    interface View{

        void showEmptyScheduleError();

        void showScheduleList();
    }

    interface Presenter{

        void SaveSchedule(String title, String description, int type, String startTime, String duration);

    }

}
