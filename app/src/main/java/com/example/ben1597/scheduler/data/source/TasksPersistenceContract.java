/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ben1597.scheduler.data.source;

import android.provider.BaseColumns;

/**
 * The contract used for the db to save the tasks locally.
 */
public final class TasksPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private TasksPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ScheduleEntry {
        public static final String TABLE_NAME = "schedule";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";                     //行程名稱
        public static final String COLUMN_NAME_START_TIME = "start_time";           //開始時間
        public static final String COLUMN_NAME_DURATION = "duration";               //持續時間
        public static final String COLUMN_NAME_TYPE = "type";                       //類型
        public static final String COLUMN_NAME_DESCRIPTION = "description";         //描述
        public static final String COLUMN_NAME_COMPLETE_TIMES = "complete_times";   //完成次數
    }

    public static abstract class DayEntry {
        public static final String TABLE_NAME = "day";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_SCHEDULE_ID = "sId";
        public static final String COLUMN_NAME_DONE = "done";
    }
}
