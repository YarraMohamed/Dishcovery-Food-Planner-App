package com.example.foodplanner.plannedMeals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;
import com.example.foodplanner.data.Repository;
import com.example.foodplanner.data.local.MealLocalDataSource;
import com.example.foodplanner.data.remote.MealRemoteDataSource;
import com.example.foodplanner.model.PlanMeals;
import com.example.foodplanner.plannedMeals.presenter.PlansPresenter;
import com.example.foodplanner.presenter.Presenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.List;

public class ScheduleFragment extends Fragment implements OnRemoveClick, PlansViewInterface {

    private MaterialCalendarView calendarView;
    private TextView loginAlert;
    private LottieAnimationView loginAnim,emptyAnim;
    private View divider;
    private Presenter presenter;
    private RecyclerView PlannedList;
    private PlansAdapter plansAdapter;
    private PlansPresenter plansPresenter;
    private String selectedDate;

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
        emptyAnim = view.findViewById(R.id.emptyAnim);
        PlannedList = view.findViewById(R.id.PlannedList);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        PlannedList.setHasFixedSize(true);
        PlannedList.setLayoutManager(manager);

        plansAdapter = new PlansAdapter(requireContext(),this);
        PlannedList.setAdapter(plansAdapter);

        plansPresenter = new PlansPresenter(this,
                Repository.getRepoInstance(new MealRemoteDataSource(),
                        new MealLocalDataSource(requireContext())));

        calendarView = view.findViewById(R.id.Calender);
        calendarView.setCurrentDate(CalendarDay.today());
        calendarView.setSelectedDate(CalendarDay.today());

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            if(selected){
                selectedDate =  String.format("%04d-%02d-%02d", date.getYear(), date.getMonth() + 1, date.getDay());
                plansPresenter.getStoredPlanForDay(selectedDate);
            }
        });

        presenter = new Presenter();
        if(presenter.checkAuth()){
            calendarView.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
            loginAnim.setVisibility(View.GONE);
            loginAlert.setVisibility(View.GONE);
            CalendarDay date = calendarView.getSelectedDate();
            if(date!=null){
                selectedDate = String.format("%04d-%02d-%02d", date.getYear(), date.getMonth() + 1, date.getDay());
                plansPresenter.getStoredPlanForDay(selectedDate);
            }
        }else{
            calendarView.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
            loginAnim.setVisibility(View.VISIBLE);
            loginAlert.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onRemoveItemClick(PlanMeals planMeal) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Remove Plan");
        builder.setMessage("Are you sure you want to remove this meal from planned meals?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            plansPresenter.removePlan(planMeal);
            Toast.makeText(getContext(),"Deleted from palns",Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showPlannedMeals(List<PlanMeals> data) {
        plansAdapter.setPlanMeals(data);
        plansAdapter.notifyDataSetChanged();
        if(!data.isEmpty()){
            PlannedList.setVisibility(View.VISIBLE);
            emptyAnim.setVisibility(View.GONE);
        }else{
            PlannedList.setVisibility(View.GONE);
            emptyAnim.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String errMsg) {
        Toast.makeText(requireContext(),"Something went Wrong",Toast.LENGTH_SHORT).show();
    }
}