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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

/**
 * Immutable model class for a Task.
 */
public final class Task {

    @NonNull
    private final String mId;

    @Nullable
    private final String mTitle;

    @Nullable
    private final Integer mType;

    @Nullable
    private final String mDescription;

    @Nullable
    private final String mStartTimes;

    @Nullable
    private final String mDuration;

    @Nullable
    private final int mCompleteTimes;

    /**
     * Use this constructor to specify a completed Task if the Task already has an id (copy of
     * another Task).
     *
     * @param title          title of the task
     * @param description    description of the task
     * @param id             id of the task
     * @param complete_times true if the task is completed, false if it's active
     */

    public Task(@Nullable String title, @Nullable Integer type, @Nullable String description,
                @Nullable String startTimes, @Nullable String duration) {
        this(title, type, description, UUID.randomUUID().toString(), startTimes, duration, 0);
    }


    public Task(@Nullable String title, @Nullable Integer type, @Nullable String description,
                @NonNull String id, @Nullable String startTimes, @Nullable String duration, @Nullable int completeTimes) {
        mId = id;
        mTitle = title;
        mType = type;
        mDescription = description;
        mStartTimes = startTimes;
        mDuration = duration;
        mCompleteTimes = completeTimes;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public Integer getType() {
        return mType;
    }

    @Nullable
    public String getStartTimes() {
        return mStartTimes;
    }

    @Nullable
    public String getDuration() {
        return mDuration;
    }

    @Nullable
    public int getmCompleteTimes(){
        return mCompleteTimes;
    }

    @Nullable
    public String getTitleForList() {
        if (!Strings.isNullOrEmpty(mTitle)) {
            return mTitle;
        } else {
            return mDescription;
        }
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mTitle) &&
                Strings.isNullOrEmpty(mDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equal(mId, task.mId) &&
                Objects.equal(mTitle, task.mTitle) &&
                Objects.equal(mDescription, task.mDescription);
    }


//    public static Integer compare(Task task1, Task task2) {
//        return task1.getStartTimes().compareTo(task2.getStartTimes());
//    }


    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mTitle, mDescription);
    }

    @Override
    public String toString() {
        return "Task with title " + mTitle;
    }
}
