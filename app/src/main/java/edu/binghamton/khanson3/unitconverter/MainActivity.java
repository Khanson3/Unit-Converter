package edu.binghamton.khanson3.unitconverter;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        final TextView inputTextView = findViewById(R.id.inputTextView);

        final TextView outputTextView = findViewById(R.id.outputTextView);
        outputTextView.setEnabled(false);

        final Spinner inputSpinner = findViewById(R.id.inputSpinner);
        ArrayAdapter<CharSequence> inputUnits = ArrayAdapter.createFromResource(this, R.array.all_units, android.R.layout.simple_spinner_item);
        inputUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(inputUnits);

        final Spinner outputSpinner = findViewById(R.id.outputSpinner);

        inputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                outputTextView.setText("");

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
                                    outputTextView.setText("");

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

        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str = converter(Double.parseDouble(inputTextView.getText().toString()), inputSpinner.getSelectedItem().toString(), outputSpinner.getSelectedItem().toString()) + "";
                    outputTextView.setText(str);
                } catch(Exception e) {}

            }
        });
    }

    public static double converter(double inputValue, String inputUnit, String outputUnit) {
        if(inputUnit.equals(outputUnit))
            return inputValue;

        switch(inputUnit) {
            //units of length
            case "in":
                switch(outputUnit) {
                    case "ft":
                        return inputValue / 12;
                    case "yd":
                        return inputValue / 36;
                    case "mi":
                        return inputValue / 63360;
                    case "mm":
                        return inputValue * 25.4;
                    case "cm":
                        return inputValue * 2.54;
                    case "m":
                        return inputValue * 0.0254;
                    case "km":
                        return inputValue * 0.0000254;
                }
            case "ft":
                switch(outputUnit) {
                    case "in":
                        return inputValue * 12;
                    case "yd":
                        return inputValue / 3;
                    case "mi":
                        return inputValue / 5280;
                    case "mm":
                        return inputValue * 304.8;
                    case "cm":
                        return inputValue * 30.48;
                    case "m":
                        return inputValue * 0.3048;
                    case "km":
                        return inputValue * 0.0003048;
                }
            case "yd":
                switch(outputUnit) {
                    case "in":
                        return inputValue * 36;
                    case "ft":
                        return inputValue * 3;
                    case "mi":
                        return inputValue / 1760;
                    case "mm":
                        return inputValue * 914.4;
                    case "cm":
                        return inputValue * 91.44;
                    case "m":
                        return inputValue * 0.9144;
                    case "km":
                        return inputValue * 0.0009144;
                }
            case "mi":
                switch(outputUnit) {
                    case "in":
                        return inputValue * 63360;
                    case "ft":
                        return inputValue * 5280;
                    case "yd":
                        return inputValue * 1760;
                    case "mm":
                        return inputValue * 1609344;
                    case "cm":
                        return inputValue * 160934.4;
                    case "m":
                        return inputValue * 1609.344;
                    case "km":
                        return inputValue * 1.609344;
                }
            case "mm":
                switch(outputUnit) {
                    case "in":
                        return inputValue / 25.4;
                    case "ft":
                        return inputValue * 5280;
                    case "yd":
                        return inputValue * 1760;
                    case "mi":
                        return inputValue / 1609344;
                    case "cm":
                        return inputValue * 0.1;
                    case "m":
                        return inputValue * 0.001;
                    case "km":
                        return inputValue * 0.000001;
                }
            case "cm":
                switch(outputUnit) {
                    case "in":
                        return inputValue / 2.54;
                    case "ft":
                        return inputValue / 30.48;
                    case "yd":
                        return inputValue / 91.44;
                    case "mi":
                        return inputValue / 160934.4;
                    case "mm":
                        return inputValue / 0.1;
                    case "m":
                        return inputValue * 0.01;
                    case "km":
                        return inputValue * 0.00001;
                }
            case "m":
                switch(outputUnit) {
                    case "in":
                        return inputValue / 0.0254;
                    case "ft":
                        return inputValue / 0.3048;
                    case "yd":
                        return inputValue / .9144;
                    case "mi":
                        return inputValue / 1609.344;
                    case "mm":
                        return inputValue / 0.001;
                    case "cm":
                        return inputValue / 0.01;
                    case "km":
                        return inputValue * 0.001;
                }
            case "km":
                switch(outputUnit) {
                    case "in":
                        return inputValue / 0.0000254;
                    case "ft":
                        return inputValue / 0.0003048;
                    case "yd":
                        return inputValue / .0009144;
                    case "mi":
                        return inputValue / 1.609344;
                    case "mm":
                        return inputValue / 0.000001;
                    case "cm":
                        return inputValue / 0.00001;
                    case "m":
                        return inputValue / 0.001;
                }

            //units of weight
            case "µg":
                switch(outputUnit) {
                    case "mg":
                        return inputValue * 0.001;
                    case "g":
                        return inputValue * 0.000001;
                    case "kg":
                        return inputValue * 0.000000001;
                    case "lb":
                        return inputValue / 453592370;
                    case "oz":
                        return inputValue / 28349523.1;
                    case "N":
                        return inputValue / 101971621.29779282;
                    case "stone":
                        return inputValue * 0.0000000000002;
                }
            case "mg":
                switch(outputUnit) {
                    case "µg":
                        return inputValue / 0.001;
                    case "g":
                        return inputValue * 0.001;
                    case "kg":
                        return inputValue * 0.000001;
                    case "lb":
                        return inputValue / 453592.37;
                    case "oz":
                        return inputValue / 28349.5231;
                    case "N":
                        return inputValue / 101971.62129779284;
                    case "stone":
                        return inputValue / 6350293.18;
                }
            case "g":
                switch(outputUnit) {
                    case "µg":
                        return inputValue / 0.000001;
                    case "mg":
                        return inputValue / 0.001;
                    case "kg":
                        return inputValue * 0.001;
                    case "lb":
                        return inputValue / 453.59237;
                    case "oz":
                        return inputValue / 28.3495231;
                    case "N":
                        return inputValue / 101.97162129779284;
                    case "stone":
                        return inputValue / 6350.29318;
                }
            case "kg":
                switch(outputUnit) {
                    case "µg":
                        return inputValue / .000000001;
                    case "mg":
                        return inputValue / .000001;
                    case "g":
                        return inputValue / 0.001;
                    case "lb":
                        return inputValue / .45359237;
                    case "oz":
                        return inputValue / .0283495231;
                    case "N":
                        return inputValue / .10197162129779284;
                    case "stone":
                        return inputValue / 6.35029318;
                }
            case "lb":
                switch(outputUnit) {
                    case "µg":
                        return inputValue * 453592370;
                    case "mg":
                        return inputValue * 453592.37;
                    case "g":
                        return inputValue * 453.59237;
                    case "kg":
                        return inputValue * .45359237;
                    case "oz":
                        return inputValue * 16;
                    case "N":
                        return inputValue * 4.4482216;
                    case "stone":
                        return inputValue / 14;
                }
            case "oz":
                switch(outputUnit) {
                    case "µg":
                        return inputValue * 28349523.1;
                    case "mg":
                        return inputValue * 28349.5231;
                    case "g":
                        return inputValue * 28.3495231;
                    case "kg":
                        return inputValue * .0283495231;
                    case "lb":
                        return inputValue / 16;
                    case "N":
                        return inputValue * 0.27801;
                    case "stone":
                        return inputValue / 224;
                }
            case "N":
                switch(outputUnit) {
                    case "µg":
                        return inputValue * 101971621.29779282;
                    case "mg":
                        return inputValue * 101971.62129779284;
                    case "g":
                        return inputValue * 101.97162129779284;
                    case "kg":
                        return inputValue * .10197162129779284;
                    case "lb":
                        return inputValue / 4.4482216;
                    case "oz":
                        return inputValue / 0.27801;
                    case "stone":
                        return inputValue / 62.27510279551201;
                }
            case "stone":
                switch(outputUnit) {
                    case "µg":
                        return inputValue / 0.0000000000002;
                    case "mg":
                        return inputValue * 6350293.18;
                    case "g":
                        return inputValue * 6350.29318;
                    case "kg":
                        return inputValue * 6.35029318;
                    case "lb":
                        return inputValue * 14;
                    case "oz":
                        return inputValue * 224;
                    case "N":
                        return inputValue * 62.27510279551201;
                }
            //units of temperature
            case "°C":
                switch(outputUnit) {
                    case "°F":
                        return (inputValue * 1.8) + 32;
                    case "K":
                        return inputValue + 273.15;
                }
            case "°F":
                switch(outputUnit) {
                    case "°C":
                        return ((inputValue - 32) * 5) / 9;
                    case "K":
                        return (((inputValue - 32) * 5) / 9) + 273.15;
                }
            case "K":
                switch(outputUnit) {
                    case "°C":
                        return inputValue - 273.15;
                    case "°F":
                        return ((inputValue - 273.15) * 1.8) + 32;
                }
            //units of speed
            case "mph":
                if (outputUnit.equals("km/hr"))
                    return inputValue * 1.609344;
            case "km/hr":
                if (outputUnit.equals("mph"))
                    return inputValue / 1.609344;
            //units of time
            case "ms":
                switch (outputUnit) {
                    case "s":
                        return inputValue * 0.001;
                    case "min":
                        return inputValue / 60000;
                    case "hr":
                        return inputValue / 3600000;
                    case "day":
                        return inputValue / 86400000;
                }
            case "s":
                switch (outputUnit) {
                    case "ms":
                        return inputValue / 0.001;
                    case "min":
                        return inputValue / 60;
                    case "hr":
                        return inputValue / 3600;
                    case "day":
                        return inputValue / 86400;
                }
            case "min":
                switch (outputUnit) {
                    case "ms":
                        return inputValue * 60000;
                    case "s":
                        return inputValue * 60;
                    case "hr":
                        return inputValue / 60;
                    case "day":
                        return inputValue / 1440;
                }
            case "hr":
                switch (outputUnit) {
                    case "ms":
                        return inputValue * 3600000;
                    case "s":
                        return inputValue * 3600;
                    case "min":
                        return inputValue * 60;
                    case "day":
                        return inputValue / 24;
                }
            case "day":
                switch (outputUnit) {
                    case "ms":
                        return inputValue * 86400000;
                    case "s":
                        return inputValue * 86400;
                    case "min":
                        return inputValue * 1440;
                    case "hr":
                        return inputValue * 24;
                }
            //units of volume
            case "mL":
                switch(outputUnit) {
                    case "L":
                        return inputValue * 0.001;
                    case "kL":
                        return inputValue * 0.000001;
                    case "cm³":
                        return inputValue;
                    case "ft³":
                        return inputValue / 28316.846591991;
                    case "fl oz":
                        return inputValue / 29.5735296;
                    case "cup":
                        return inputValue / 236.5882365;
                    case "pt":
                        return inputValue / 473.176473;
                    case "qt":
                        return inputValue / 946.352946;
                    case "gal":
                        return inputValue / 3785.411784;
                }
            case "L":
                switch(outputUnit) {
                    case "mL":
                        return inputValue / 0.001;
                    case "kL":
                        return inputValue * 0.001;
                    case "cm³":
                        return inputValue * 1000;
                    case "ft³":
                        return inputValue * 0.035315;
                    case "fl oz":
                        return inputValue * 33.81402265896594;
                    case "cup":
                        return inputValue * 4.22675283773037;
                    case "pt":
                        return inputValue * 2.11337641886519;
                    case "qt":
                        return inputValue * 1.05668820943259;
                    case "gal":
                        return inputValue * 0.26417205235815;
                }
            case "kL":
                switch(outputUnit) {
                    case "mL":
                        return inputValue / 0.000001;
                    case "L":
                        return inputValue / 0.001;
                    case "cm³":
                        return inputValue * 1000000;
                    case "ft³":
                        return inputValue * 35.314666572222;
                    case "fl oz":
                        return inputValue * 33814.0226589659423;
                    case "cup":
                        return inputValue * 4226.75282879765489;
                    case "pt":
                        return inputValue * 2113.37641886518732;
                    case "qt":
                        return inputValue * 1056.68820943259366;
                    case "gal":
                        return inputValue * 264.17205235814842;
                }
            case "cm³":
                switch(outputUnit) {
                    case "mL":
                        return inputValue;
                    case "L":
                        return inputValue / 1000;
                    case "kL":
                        return inputValue / 1000000;
                    case "ft³":
                        return inputValue / 28316.8466;
                    case "fl oz":
                        return inputValue / 29.573529564112;
                    case "cup":
                        return inputValue * 0.004;
                    case "pt":
                        return inputValue * 0.002113;
                    case "qt":
                        return inputValue * 0.001056688;
                    case "gal":
                        return inputValue * 0.00026417205;
                }
            case "ft³":
                switch(outputUnit) {
                    case "mL":
                        return inputValue * 28316.846591991;
                    case "L":
                        return inputValue / 0.035315;
                    case "kL":
                        return inputValue / 35.314666572222;
                    case "cm³":
                        return inputValue * 28316.8466;
                    case "fl oz":
                        return inputValue * 957.506;
                    case "cup":
                        return inputValue * 119.688;
                    case "pt":
                        return inputValue * 59.844155;
                    case "qt":
                        return inputValue * 29.9221;
                    case "gal":
                        return inputValue * 7.480519;
                }
            case "fl oz":
                switch(outputUnit) {
                    case "mL":
                        return inputValue * 29.5735296;
                    case "L":
                        return inputValue / 33.81402265896594;
                    case "kL":
                        return inputValue / 33814.0226589659423;
                    case "cm³":
                        return inputValue * 29.573529564112;
                    case "ft³":
                        return inputValue / 957.506;
                    case "cup":
                        return inputValue * 0.125;
                    case "pt":
                        return inputValue * 0.0625;
                    case "qt":
                        return inputValue * 0.03125;
                    case "gal":
                        return inputValue * 0.0078125;
                }
            case "cup":
                switch(outputUnit) {
                    case "mL":
                        return inputValue * 236.5882365;
                    case "L":
                        return inputValue / 4.22675283773037;
                    case "kL":
                        return inputValue / 4226.75282879765489;
                    case "cm³":
                        return inputValue / 0.004;
                    case "ft³":
                        return inputValue / 119.688;
                    case "fl oz":
                        return inputValue * 7.99765121;
                    case "pt":
                        return inputValue * 0.49985267231;
                    case "qt":
                        return inputValue * 0.24992635042;
                    case "gal":
                        return inputValue * 0.0624815876;
                }
            case "pt":
                switch(outputUnit) {
                    case "mL":
                        return inputValue * 473.176473;
                    case "L":
                        return inputValue / 2.11337641886519;
                    case "kL":
                        return inputValue / 2113.37641886518732;
                    case "cm³":
                        return inputValue / 0.002113;
                    case "ft³":
                        return inputValue / 59.844155;
                    case "fl oz":
                        return inputValue / 0.0625;
                    case "cup":
                        return inputValue / 0.49985267231;
                    case "qt":
                        return inputValue * 0.50000002853;
                    case "gal":
                        return inputValue * 0.12500000713;
                }
            case "qt":
                switch(outputUnit) {
                    case "mL":
                        return inputValue * 946.352946;
                    case "L":
                        return inputValue / 1.05668820943259;
                    case "kL":
                        return inputValue / 1056.68820943259366;
                    case "cm³":
                        return inputValue / 0.001056688;
                    case "ft³":
                        return inputValue / 29.9221;
                    case "fl oz":
                        return inputValue / 0.03125;
                    case "cup":
                        return inputValue / 0.24992635042;
                    case "pt":
                        return inputValue / 0.50000002853;
                    case "gal":
                        return inputValue * 0.25;
                }
            case "gal":
                switch(outputUnit) {
                    case "mL":
                        return inputValue * 3785.411784;
                    case "L":
                        return inputValue / 0.26417205235815;
                    case "kL":
                        return inputValue / 264.17205235814842;
                    case "cm³":
                        return inputValue / 0.00026417205;
                    case "ft³":
                        return inputValue / 7.480519;
                    case "fl oz":
                        return inputValue / 0.0078125;
                    case "cup":
                        return inputValue / 0.0624815876;
                    case "pt":
                        return inputValue / 0.12500000713;
                    case "qt":
                        return inputValue / 0.25;
                }
        }
        return 0;
    }
}