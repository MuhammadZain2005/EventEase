package com.example.eventease;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class CreateEventActivity extends AppCompatActivity {

    EditText eventNameEditText, descriptionEditText, startDateEditText, endDateEditText;
    ImageView calendarStart, calendarEnd;
    Switch geolocationSwitch, qrSwitch;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Initialize views
        eventNameEditText = findViewById(R.id.input_event_name);
        descriptionEditText = findViewById(R.id.input_description);
        startDateEditText = findViewById(R.id.start_date);
        endDateEditText = findViewById(R.id.end_date);
        calendarStart = findViewById(R.id.icon_calendar_start);
        calendarEnd = findViewById(R.id.icon_calendar_end);
        geolocationSwitch = findViewById(R.id.switch_geolocation);
        qrSwitch = findViewById(R.id.switch_qr);
        saveButton = findViewById(R.id.btn_save_changes);

        // Event name popup
        eventNameEditText.setOnClickListener(v -> showTextInputDialog(eventNameEditText, "Enter Event Name"));

        // Description popup
        descriptionEditText.setOnClickListener(v -> showTextInputDialog(descriptionEditText, "Enter Description"));

        // Start date picker
        startDateEditText.setOnClickListener(v -> showDatePicker(startDateEditText));
        calendarStart.setOnClickListener(v -> showDatePicker(startDateEditText));

        // End date picker
        endDateEditText.setOnClickListener(v -> showDatePicker(endDateEditText));
        calendarEnd.setOnClickListener(v -> showDatePicker(endDateEditText));

        // Save button
        saveButton.setOnClickListener(v -> Toast.makeText(this, "Save functionality coming soon!", Toast.LENGTH_SHORT).show());
    }

    // Popup text input
    private void showTextInputDialog(EditText targetEditText, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText input = new EditText(this);
        input.setText(targetEditText.getText().toString());
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> targetEditText.setText(input.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Date picker
    private void showDatePicker(EditText targetEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    targetEditText.setText(date);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}