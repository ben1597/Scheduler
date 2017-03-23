package com.example.ben1597.scheduler.showSchedule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ben1597.scheduler.MainActivity;
import com.example.ben1597.scheduler.R;
import com.example.ben1597.scheduler.addSchedule.AddScheduleFragment;
import com.example.ben1597.scheduler.data.source.Task;
import com.example.ben1597.scheduler.data.source.TasksLocalDataSource;
import com.example.ben1597.scheduler.util.adapters.SchedulesAdapter;
import com.example.ben1597.scheduler.util.schedulers.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ShowScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowScheduleFragment extends Fragment implements ShowScheduleContract.View{

    private FloatingActionButton floatingActionButton;
    private ShowScheduleContract.Presenter mPresenter;
    private SchedulesAdapter schedulesAdapter;
    private RecyclerView recyclerView;

    public ShowScheduleFragment() {
        // Required empty public constructor
    }

    public static ShowScheduleFragment newInstance() {
        return new ShowScheduleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPresenter = new ShowSchedulePresenter(TasksLocalDataSource.getInstance(getActivity().getApplicationContext(), SchedulerProvider.getInstance())
                ,this
                ,SchedulerProvider.getInstance());

        View root = inflater.inflate(R.layout.fragment_show_schedule, container, false);

        // Inflate the layout for this fragment
        initView(root);
        initListener();


        mPresenter.loadTasks(false);

        return root;
    }

    private void initListener() {

        SchedulesAdapter.ScheduleItemListener mItemListener = new SchedulesAdapter.ScheduleItemListener() {
            @Override
            public void onScheduleClick(Task clickedSchedule) {

            }
        };

        schedulesAdapter = new SchedulesAdapter(new ArrayList<Task>(0), mItemListener);
        recyclerView.setAdapter(schedulesAdapter);

        int numColumns = 1;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
    }

    private void initView(View root) {

        floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_schedules);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new AddScheduleFragment());
            }
        });
        recyclerView = (RecyclerView) root.findViewById(R.id.show_schedules);

    }

    @Override
    public void showTasks(List<Task> tasks) {

        schedulesAdapter.replaceData(tasks);

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

}
