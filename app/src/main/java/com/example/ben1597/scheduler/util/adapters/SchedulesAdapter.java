package com.example.ben1597.scheduler.util.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ben1597.scheduler.R;
import com.example.ben1597.scheduler.data.source.Task;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ben1597 on 2017/3/21.
 */


public class SchedulesAdapter extends RecyclerView.Adapter<SchedulesAdapter.ViewHolder> {

    List<Task> mSchedules;
    private ScheduleItemListener mItemListener;

    public SchedulesAdapter(List<Task> tasks, ScheduleItemListener itemListener) {
        setList(tasks);
        mItemListener = itemListener;
    }

    @Override
    public SchedulesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View scheduleView = inflater.inflate(R.layout.textview_schedule, parent, false);

        return new ViewHolder(scheduleView, mItemListener);
    }

    @Override
    public void onBindViewHolder(SchedulesAdapter.ViewHolder viewHolder, int position) {
        Task schedule = mSchedules.get(position);

        viewHolder.time.setText(schedule.getStartTimes());
        viewHolder.title.setText(schedule.getTitle());
        viewHolder.duration.setText(schedule.getDuration());
    }

    public void replaceData(List<Task> schedules) {
        setList(schedules);
        notifyDataSetChanged();
    }

    private void setList(List<Task> schedules) {
        mSchedules = checkNotNull(schedules);
    }

    @Override
    public int getItemCount() {
        return mSchedules.size();
    }

    public Task getItem(int position) {
        return mSchedules.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, description, time , duration;

        private ScheduleItemListener mItemListener;

        public ViewHolder(View itemView, ScheduleItemListener listener) {
            super(itemView);
            mItemListener = listener;
            time = (TextView) itemView.findViewById(R.id.schedule_time);
            duration = (TextView) itemView.findViewById(R.id.schedule_duration);
            title = (TextView) itemView.findViewById(R.id.schedule_title);
//                description = (TextView) itemView.findViewById(R.id.note_detail_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface ScheduleItemListener {

        void onScheduleClick(Task clickedSchedule);
    }
}
