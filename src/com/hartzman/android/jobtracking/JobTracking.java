package com.hartzman.android.jobtracking;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class JobTracking extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job_tracking);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.job_tracking, menu);
		return true;
	}
	
	public void showDatePickerDialog(View view)
	{
		DialogFragment fragment = new DatePickerFragment();
		fragment.show(getFragmentManager(), "datePicker");
	}
	
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
	{

		private static int selectedYear;
		private static int selectedMonth;
		private static int selectedDay;
		
		@Override 
		public Dialog onCreateDialog(Bundle savedInstanceData)
		{
		
			final Calendar cal = Calendar.getInstance(); 
			final int year = selectedYear == 0 ? cal.get(Calendar.YEAR) : selectedYear;
			final int day = selectedDay == 0 ? cal.get(Calendar.DAY_OF_MONTH) : selectedDay;
			final int month = selectedMonth == 0 ? cal.get(Calendar.MONTH) : selectedMonth;
			final DatePickerDialog dpd = new DatePickerDialog(getActivity(), null, year, month, day);
			dpd.setCancelable(true);
			dpd.setButton(DialogInterface.BUTTON_POSITIVE, "Set", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "got the DONE button", Toast.LENGTH_LONG).show();
					DatePicker dp = dpd.getDatePicker();
					onDateSet(dp, dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
					System.out.println("in onClick");
				}
			});
			dpd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					// do nothing - it's a 'cancel' action
					
				}
			});
			return dpd; 
		}

		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			view.setSpinnersShown(true);
			Calendar cal = Calendar.getInstance();
			cal.set(year, monthOfYear, dayOfMonth, 0, 0);
			TextView dateText = (TextView)getActivity().findViewById(R.id.dateField);
			String text = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()).format(cal.getTime());
			dateText.setText(text);
			selectedYear = year;
			selectedMonth = monthOfYear;
			selectedDay = dayOfMonth;
		}
		
	}

}
