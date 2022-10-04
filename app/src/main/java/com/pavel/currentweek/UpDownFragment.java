package com.pavel.currentweek;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CalendarView;
import android.widget.Toast;

import com.pavel.currentweek.databinding.FragmentUpDownBinding;
import com.pavel.currentweek.date.UpDownCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpDownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpDownFragment extends Fragment {
    FragmentUpDownBinding binding;
    UpDownCalendar weekCalendar;
    public static UpDownFragment newInstance() {
        UpDownFragment fragment = new UpDownFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpDownBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarInit();
        fabSettingsInit();
        currentWeekIconInit();
    }
    private void calendarInit(){
        weekCalendar = new UpDownCalendar();
        setWeekText(weekCalendar.weekCalc());

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Toast.makeText(getContext(),year + " " + month + " " + day,Toast.LENGTH_LONG).show();
                weekCalendar.setUpDate(year,month,day);
                setWeekText(weekCalendar.weekCalc());
            }
        });
    }
    private void fabSettingsInit(){
        binding.fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Если зайдет добавлю, а пока досвидания", Toast.LENGTH_SHORT).show();
                binding.fabSettings.hide();
            }
        });
    }
    private void currentWeekIconInit(){
        binding.currentWeekIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation = new RotateAnimation(
                        0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                binding.currentWeekIcon.startAnimation(rotateAnimation);
            }
        });
    }
    private void setWeekText(String weekState){
        binding.currentWeekText.setText(weekState);
        if (weekState.equals(UpDownCalendar.UP))
            binding.currentWeekIcon.setImageResource(R.drawable.icon_color_up_round);
        else binding.currentWeekIcon.setImageResource(R.drawable.icon_color_down_round);
    }
}