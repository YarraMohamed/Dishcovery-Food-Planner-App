package com.example.foodplanner.plannedMeals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;
import com.example.foodplanner.presenter.Presenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class ScheduleFragment extends Fragment {

    private MaterialCalendarView calendarView;
    private TextView loginAlert,plannedMealName;
    private LottieAnimationView loginAnim;
    private View divider;
    private Presenter presenter;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginAnim= view.findViewById(R.id.loginAnim);
        loginAlert = view.findViewById(R.id.loginAlert);
        divider = view.findViewById(R.id.divider3);
        plannedMealName = view.findViewById(R.id.plannedMealName);

        calendarView = view.findViewById(R.id.Calender);
        calendarView.setCurrentDate(CalendarDay.today());
        calendarView.setSelectedDate(CalendarDay.today());

        presenter = new Presenter();
        if(presenter.checkAuth()){
            calendarView.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
            plannedMealName.setVisibility(View.VISIBLE);
            loginAnim.setVisibility(View.GONE);
            loginAlert.setVisibility(View.GONE);
        }else{
            calendarView.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
            plannedMealName.setVisibility(View.GONE);
            loginAnim.setVisibility(View.VISIBLE);
            loginAlert.setVisibility(View.VISIBLE);

        }

    }
}