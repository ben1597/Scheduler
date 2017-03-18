package com.example.ben1597.scheduler.addSchedule;

import android.app.TimePickerDialog;
import java.util.GregorianCalendar;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ben1597.scheduler.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddScheduleFragment extends Fragment implements AddScheduleContract.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner add_schedule_types,add_schedule_duration;
    private TimePickerDialog timePickerDialog;
    private TextView textViewTime;
    private FloatingActionButton fab;

    private OnFragmentInteractionListener mListener;

    public AddScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddScheduleFragment newInstance(String param1, String param2) {
        AddScheduleFragment fragment = new AddScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_add_schedule, container, false);

        initView(root);
        initListener();

        return root;
    }

    private void initView(View root) {
        add_schedule_types = (Spinner)root.findViewById(R.id.add_schedule_types);
        add_schedule_duration = (Spinner) root.findViewById(R.id.add_schedule_duration);
        textViewTime = (TextView)root.findViewById(R.id.add_schedule_startTimes);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_schedules);
        fab.setImageResource(R.drawable.ic_done);
    }

    private void initListener() {

        ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(this.getActivity() ,
                R.array.schedule_types,
                android.R.layout.simple_spinner_dropdown_item);
        typeList.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        add_schedule_types.setAdapter(typeList);

        typeList = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.schedule_duration,
                android.R.layout.simple_spinner_dropdown_item);
        add_schedule_duration.setAdapter(typeList);

        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        GregorianCalendar calendar = new GregorianCalendar();
        timePickerDialog = new TimePickerDialog(getActivity() , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textViewTime.setText(hourOfDay + ":" + minute + "PM");
            }
        },calendar.get(calendar.HOUR_OF_DAY),calendar.get(calendar.MINUTE),false);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mActionListener.saveNote(mTitle.getText().toString(),
//                        mDescription.getText().toString());
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void showEmptyScheduleError() {

    }

    @Override
    public void showScheduleList() {

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
