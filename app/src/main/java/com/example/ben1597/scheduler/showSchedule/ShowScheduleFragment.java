package com.example.ben1597.scheduler.showSchedule;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ben1597.scheduler.MainActivity;
import com.example.ben1597.scheduler.R;
import com.example.ben1597.scheduler.addSchedule.AddScheduleFragment;
import com.example.ben1597.scheduler.data.source.Task;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowScheduleFragment extends Fragment implements ShowScheduleContract.View{

    private OnFragmentInteractionListener mListener;
    private FloatingActionButton floatingActionButton;

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

        View root = inflater.inflate(R.layout.fragment_show_schedule, container, false);
        floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_schedules);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(new AddScheduleFragment());
            }
        });
        // Inflate the layout for this fragment
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void showTasks(List<Task> tasks) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
