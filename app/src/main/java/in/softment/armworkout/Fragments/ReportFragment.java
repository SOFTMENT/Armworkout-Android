package in.softment.armworkout.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import in.softment.armworkout.Adapter.WeekDayReportAdapter;
import in.softment.armworkout.R;
import in.softment.armworkout.Utils.Services;

public class ReportFragment extends Fragment {

    private int count = 0;
    ArrayList<String> daysText = new ArrayList<String>();
    ArrayList<String> daysYearText  = new ArrayList<String>();
    private Context context;
    private CombinedChart chartWeight;
    public ReportFragment(Context context) {
        // Required empty public constructor
        this.context = context;


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        chartWeight = view.findViewById(R.id.chartWeight);
        RecyclerView recyclerView = view.findViewById(R.id.rcyHistoryWeek);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        WeekDayReportAdapter weekDayReportAdapter = new WeekDayReportAdapter(context);
        recyclerView.setAdapter(weekDayReportAdapter);
        setupGraph();
        return view;
    }

    private void setupGraph() {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM", Locale.getDefault());
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        try {
            calendar.setTime(formatDate.parse("$year-01-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        count = isLeapYear(year) + 1;
        daysText = new ArrayList();
        daysYearText = new ArrayList();

        for (int i = 0; i < count; i++) {
            daysText.add(format.format(calendar.getTime()));
            daysYearText.add(formatDate.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        chartWeight.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.LINE});

        chartWeight.getDescription().setEnabled(false);
        Description description = new Description();
        description.setText("Date");
        chartWeight.setDescription(description);
        chartWeight.setNoDataText(getResources().getString(R.string.app_name));
        chartWeight.setBackgroundColor(Color.WHITE);
        chartWeight.setDrawGridBackground(false);
        chartWeight.setDrawBarShadow(false);
        chartWeight.setHighlightFullBarEnabled(false);

        Legend l = chartWeight.getLegend();
        l.setWordWrapEnabled(false);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis leftAxis = chartWeight.getAxisLeft();
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = chartWeight.getAxisRight();

        rightAxis.setEnabled(false);

        XAxis xAxis = chartWeight.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) (count));
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(30);

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {

                if (value < daysText.size() && value > 0) {
                    return daysText.get((int) value);
                }
                else {
                    return  "";
                }
            }
        });


        CombinedData data = new CombinedData();
        data.setData(generateLineData());

        data.setValueTypeface(Typeface.DEFAULT);
        chartWeight.setData(data);

        chartWeight.setVisibleXRange(5f, 8f);

        String strDate = Services.convertFullDateToDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()).toString());
        int position = daysYearText.indexOf(strDate);
        chartWeight.centerViewTo((float) (position), 50f, YAxis.AxisDependency.LEFT);


        chartWeight.invalidate();
    }

    private LineData generateLineData() {

        //    val yAxisData = dbHelper.getUserWeightData()
        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();
//        if (yAxisData.size > 0) {
//            for (index in 0 until yAxisData.size) {
////            yAxisData[index]["KG"]
//                val strDate = yAxisData[index]["DT"]
//                val position = daysYearText.indexOf(strDate)
//                if (LocalDB.getWeightUnit(context) == ConstantString.DEF_KG) {
//                    entries.add(Entry(position.toFloat(), yAxisData[index]["KG"]!!.toFloat()))
//                } else {
//                    entries.add(
//                            Entry(
//                                    position.toFloat(),
//                                    CommonUtility.KgToLb(yAxisData[index]["KG"]!!.toDouble()).toFloat()
//                            )
//                    )
//                }
//            }
//        } else {
//            if (LocalDB.getLastInputWeight(context) > 0) {
//                val strDate = CommonUtility.convertFullDateToDate(CommonUtility.getCurrentTimeStamp())
//                val position = daysYearText.indexOf(strDate)
//                if (LocalDB.getWeightUnit(context) == ConstantString.DEF_KG) {
//                    entries.add(Entry(position.toFloat(), LocalDB.getLastInputWeight(context)))
//                } else {
//                    entries.add(
//                            Entry(
//                                    position.toFloat(),
//                                    CommonUtility.KgToLb(LocalDB.getLastInputWeight(context).toDouble()).toFloat()
//                            )
//                    )
//                }
//            }
//        }

        LineDataSet set = new LineDataSet(entries, "Date");
        set.setColor(Color.rgb(130, 87, 242));
        set.setLineWidth(1.5f);
        set.setCircleColor(Color.rgb(130, 130, 130));
        set.setCircleRadius(5f);

//        set.fillColor = Color.rgb(130, 130, 130)

        set.setFillColor(Color.rgb(130, 130, 130));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(130, 87, 242));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    private int isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }
}