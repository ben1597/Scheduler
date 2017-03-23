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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.ben1597.scheduler.data.source.TasksPersistenceContract.ScheduleEntry;
import com.example.ben1597.scheduler.util.schedulers.BaseSchedulerProvider;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class TasksLocalDataSource implements TasksDataSource {

    @Nullable
    private static TasksLocalDataSource INSTANCE;

    @NonNull
    private final BriteDatabase mDatabaseHelper;

    @NonNull
    private Func1<Cursor, Task> mTaskMapperFunction;

    // Prevent direct instantiation.
    private TasksLocalDataSource(@NonNull Context context,
                                 @NonNull BaseSchedulerProvider schedulerProvider) {
        checkNotNull(context, "context cannot be null");
        checkNotNull(schedulerProvider, "scheduleProvider cannot be null");
        TasksDbHelper dbHelper = new TasksDbHelper(context);
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, schedulerProvider.io());
        mTaskMapperFunction = new Func1<Cursor, Task>() {
            @Override
            public Task call(Cursor c) {
                String itemId = c.getString(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_ENTRY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_TITLE));
                Integer type = c.getInt(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_TYPE));
                String description =
                        c.getString(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_DESCRIPTION));
                String startTimes =
                        c.getString(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_START_TIME));
                String duration =
                        c.getString(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_DURATION));
                Integer completeTimes =
                        c.getInt(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES));
//                boolean completed =
//                        c.getInt(c.getColumnIndexOrThrow(ScheduleEntry.COLUMN_NAME_COMPLETED)) == 1;
                return new Task(title,type, description, itemId, startTimes, duration, completeTimes);
            }
        };
    }

    public static TasksLocalDataSource getInstance(
            @NonNull Context context,
            @NonNull BaseSchedulerProvider schedulerProvider) {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource(context, schedulerProvider);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Task>> getTasks() {
        String[] projection = {
                ScheduleEntry.COLUMN_NAME_ENTRY_ID,
                ScheduleEntry.COLUMN_NAME_TITLE,
                ScheduleEntry.COLUMN_NAME_TYPE,
                ScheduleEntry.COLUMN_NAME_START_TIME,
                ScheduleEntry.COLUMN_NAME_DURATION,
                ScheduleEntry.COLUMN_NAME_DESCRIPTION,
                ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES
        };
        String sql = String.format("SELECT %s FROM %s", TextUtils.join(",", projection), ScheduleEntry.TABLE_NAME);
        return mDatabaseHelper.createQuery(ScheduleEntry.TABLE_NAME, sql)
                .mapToList(mTaskMapperFunction).first();
    }

    @Override
    public Observable<Task> getTask(@NonNull String taskId) {
        String[] projection = {
                ScheduleEntry.COLUMN_NAME_ENTRY_ID,
                ScheduleEntry.COLUMN_NAME_TITLE,
                ScheduleEntry.COLUMN_NAME_TYPE,
                ScheduleEntry.COLUMN_NAME_DESCRIPTION,
                ScheduleEntry.COLUMN_NAME_START_TIME,
                ScheduleEntry.COLUMN_NAME_DURATION,
                ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES
        };
        String sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection), ScheduleEntry.TABLE_NAME, ScheduleEntry.COLUMN_NAME_ENTRY_ID);
        return mDatabaseHelper.createQuery(ScheduleEntry.TABLE_NAME, sql, taskId)
                .mapToOneOrDefault(mTaskMapperFunction, null);
    }

    @Override
    public void saveTask(@NonNull Task task) {
        checkNotNull(task);
        ContentValues values = new ContentValues();
        values.put(ScheduleEntry.COLUMN_NAME_ENTRY_ID, task.getId());
        values.put(ScheduleEntry.COLUMN_NAME_TITLE, task.getTitle());
        values.put(ScheduleEntry.COLUMN_NAME_TYPE, task.getType());
        values.put(ScheduleEntry.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(ScheduleEntry.COLUMN_NAME_START_TIME, task.getStartTimes());
        values.put(ScheduleEntry.COLUMN_NAME_DURATION, task.getDuration());
        values.put(ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES, task.getmCompleteTimes());
        mDatabaseHelper.insert(ScheduleEntry.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void completeTask(@NonNull Task task) {
        ContentValues values = new ContentValues();
        values.put(ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES, task.getmCompleteTimes()+1);

        String selection = ScheduleEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {task.getId()};
        mDatabaseHelper.update(ScheduleEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    @Override
    public void activateTask(@NonNull Task task) {
        activateTask(task.getId());
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        ContentValues values = new ContentValues();
        values.put(ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES, false);

        String selection = ScheduleEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {taskId};
        mDatabaseHelper.update(ScheduleEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    @Override
    public void clearCompletedTasks() {
        String selection = ScheduleEntry.COLUMN_NAME_COMPLETE_TIMES + " LIKE ?";
        String[] selectionArgs = {"1"};
        mDatabaseHelper.delete(ScheduleEntry.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public void refreshTasks() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllTasks() {
        mDatabaseHelper.delete(ScheduleEntry.TABLE_NAME, null);
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        String selection = ScheduleEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {taskId};
        mDatabaseHelper.delete(ScheduleEntry.TABLE_NAME, selection, selectionArgs);
    }
}
