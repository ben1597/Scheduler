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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Schedules.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_SCHEDULE =
            "CREATE TABLE " + TasksPersistenceContract.ScheduleEntry.TABLE_NAME + " (" +
                    TasksPersistenceContract.ScheduleEntry.COLUMN_NAME_TITLE + TEXT_TYPE + " PRIMARY KEY," +
                    TasksPersistenceContract.ScheduleEntry.COLUMN_NAME_TYPE + BOOLEAN_TYPE + COMMA_SEP +
                    TasksPersistenceContract.ScheduleEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    TasksPersistenceContract.ScheduleEntry.COLUMN_NAME_START_TIME + INTEGER_TYPE + COMMA_SEP +
                    TasksPersistenceContract.ScheduleEntry.COLUMN_NAME_DURATION + INTEGER_TYPE + COMMA_SEP +
                    TasksPersistenceContract.ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES + INTEGER_TYPE +
            " )";

    private static final String SQL_CREATE_DAY =
            "CREATE TABLE " + TasksPersistenceContract.DayEntry.TABLE_NAME + " (" +
                    TasksPersistenceContract.DayEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + " PRIMARY KEY," +
                    TasksPersistenceContract.DayEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    TasksPersistenceContract.DayEntry.COLUMN_NAME_SCHEDULE_ID + TEXT_TYPE + COMMA_SEP +
                    TasksPersistenceContract.DayEntry.COLUMN_NAME_DONE + BOOLEAN_TYPE + COMMA_SEP +
                    " )";


    public TasksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SCHEDULE);
        db.execSQL(SQL_CREATE_DAY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
