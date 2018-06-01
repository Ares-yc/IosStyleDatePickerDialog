package com.ares.iosstyledatepickerdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ymdBtn,ymdhmBtn;
    private String dateYear = getYmdFormat(new Date());
    private String dateMonth = getYmdFormat(new Date());
    private String dateDay = getYmdFormat(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ymdBtn = findViewById(R.id.button1);
        ymdhmBtn = findViewById(R.id.button2);

        ymdBtn.setOnClickListener(this);
        ymdhmBtn.setOnClickListener(this);
        Log.e("*****","dateYear : " + dateYear);
        Log.e("*****","dateMonth : " + dateMonth);
        Log.e("*****","dateDay : " + dateDay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                showDateDialog(DateUtilPicker.getDateForString(dateDay), false, false, new DateCallback() {
                    @Override
                    public void getDate(int[] dates) {
                        dateDay = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                                + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                        Log.e("*****","dateYear : "+dateDay);
                    }
                });
                break;
            case R.id.button2:

                break;
        }
    }

    public void showDateDialog(List<Integer> date, boolean flag, boolean flagyear, final DateCallback callback) {
        DatePickerDialog dateDialog;
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this, flag, flagyear,Integer.valueOf(splitYearDate(getYmdhmFormat(new Date()))), 2015);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                callback.getDate(dates);
            }

            @Override
            public void onCancel() {

            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        builder.setMaxYear(DateUtilPicker.getYear());
        builder.setMaxMonth(DateUtilPicker.getDateForString(DateUtilPicker.getToday()).get(1));
        builder.setMaxDay(DateUtilPicker.getDateForString(DateUtilPicker.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();

    }

    public static String splitYearDate(String date) {
        String[] split = date.split("-");
        return split[0];
    }

    public String getYmdFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }


    public String getYmdhmFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }
}
