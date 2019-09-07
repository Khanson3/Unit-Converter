package edu.binghamton.khanson3.unitconverter;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        String[] lengthUnits = res.getStringArray(R.array.length_units);
        String[] weightUnits = res.getStringArray(R.array.weight_units);
        String[] temperatureUnits = res.getStringArray(R.array.temperature_units);
        String[] speedUnits = res.getStringArray(R.array.speed_units);
        String[] timeUnits = res.getStringArray(R.array.time_units);
        String[] volumeUnits = res.getStringArray(R.array.volume_units);
        final String[][] allUnits = {lengthUnits, weightUnits, temperatureUnits, speedUnits, timeUnits, volumeUnits};

        TextView outputTextView = findViewById(R.id.outputTextView);
        //outputTextView.setKeyListener(null);
        outputTextView.setEnabled(false);

        Spinner inputSpinner = findViewById(R.id.inputSpinner);
        ArrayAdapter<CharSequence> inputUnits = ArrayAdapter.createFromResource(this, R.array.all_units, android.R.layout.simple_spinner_item);
        inputUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(inputUnits);

        final Spinner outputSpinner = findViewById(R.id.outputSpinner);

        inputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String unit = ((TextView) adapterView.getChildAt(0)).getText().toString();
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(18.65f);

                for(int j = 0; j < allUnits.length; j++) {
                    for(String elem : allUnits[j]) {
                        if(elem.equals(unit)) {
                            int strArrayID;
                            if(j == 0)
                                strArrayID = R.array.length_units;
                            else if(j == 1)
                                strArrayID = R.array.weight_units;
                            else if(j == 2)
                                strArrayID = R.array.temperature_units;
                            else if(j == 3)
                                strArrayID = R.array.speed_units;
                            else if(j == 4)
                                strArrayID = R.array.time_units;
                            else
                                strArrayID = R.array.volume_units;

                            ArrayAdapter<CharSequence> outputUnits = ArrayAdapter.createFromResource(getApplicationContext(), strArrayID, android.R.layout.simple_spinner_item);
                            outputUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            outputSpinner.setAdapter(outputUnits);

                            outputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                                    ((TextView) adapterView.getChildAt(0)).setTextSize(18.65f);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}