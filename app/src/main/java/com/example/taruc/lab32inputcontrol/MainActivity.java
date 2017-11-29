package com.example.taruc.lab32inputcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //linking ui to program
        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupAge);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);

        //Create an adaptor
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.age_group,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Position:" + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        double premium = 0;
        int pos;
        String gender;
        boolean smoker = false;
        pos = spinnerAge.getSelectedItemPosition();

        // Determine the gender of user
        int indexGender= radioGroupGender.getCheckedRadioButtonId();
        if(indexGender == R.id.radioButtonMale)
        {
            gender = "Male";
        }
        else
        {
            gender= "Female";
        }
        //check smoker
        if(checkBoxSmoker.isChecked())
        {
            smoker = true;
        }

        premium = calPayment(pos,gender,smoker);
        // Dsiplay premuim
        textViewPremium.setText(getString(R.string.premium)+premium);
    }


    public double calPayment(int pos,String gender, boolean smoker)
    {
        double payment =0;
        switch(pos){
            case 0:
                payment = payment + 50;
            case 1:
                payment = payment + 55;
            case 2:
                payment = payment + 60;
                if(gender == "Male")
                {
                    payment +=50;
                }
            case 3:
                payment = payment + 70;
                if(gender == "Male")
                {
                    payment +=100;
                }
                if(smoker == true)
                {
                    payment +=100;
                }
            case 4:
                payment = payment + 120;
                if(gender == "Male")
                {
                    payment +=100;
                }
                if(smoker == true)
                {
                    payment +=150;
                }
            case 5:
                payment = payment + 160;
                if(gender == "Male")
                {
                    payment +=50;
                }
                if(smoker == true)
                {
                    payment +=150;
                }
            case 6:
                payment = payment + 200;
                if(smoker == true)
                {
                    payment +=250;
                }
            case 7:
                payment = payment + 250;
                if(smoker == true)
                {
                    payment +=250;
                }
        }

        return payment;
    }
}
