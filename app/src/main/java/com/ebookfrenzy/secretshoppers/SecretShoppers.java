package com.ebookfrenzy.secretshoppers;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class SecretShoppers extends AppCompatActivity
{
   // private static final String THE_NAME="THE_NAME";
//    private static final String THE_COMMENTS="THE COMMENTS";
  //  private static final String THE_SCORE="THE_SCORE";

    private double scoreBeforeSubmit;//the score if the user has not
    //pressed the submit button to get the score.
    //private double currentScore;//the score that is currently being counted.

  //  private double finalScore;//the end score


    EditText establishmentName;//name of the place
    EditText commentBoxText;//the comments the user enters
    EditText currentScore;//the current score

    private int [] checklistValues = new int [21];//19 values
    //for the check boxes and , radio, spinner


    CheckBox happyCheckBox;//happy_checkBox
    CheckBox fairCheckbox;//fair_checkBox
    CheckBox annoyedCheckbox;//annoyed_checkBox
    CheckBox veryCheckbox;//very_CheckBox
    CheckBox notveryCheckBox;//not_CheckBox
    CheckBox neededhelpCheckBox;//needed_help_CheckBox

    RadioGroup HelpRadioGroup;//employeeHelpRadioGroup
    RadioButton yesRadioButton;//yes_radioButton
    RadioButton noRadioButton;//no_radioButton
    RadioButton askRadioButtonl;//ask_RadioButton;

    Spinner ratingScaleSpinner;

    Button submitButton;//submitButton
    Button clearButton;//clearButton
    private String Textbox;
    private String TextboxTwo;
    private String colorChangerText;

    private String TAG="secretshoppers";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_shoppers);
        Log.d(TAG,"Oncreate");

        if(savedInstanceState == null)
        {
            //just started
            scoreBeforeSubmit=0;
           // currentScore = 0;

        }
        else
        {
            //app is being restored
           // scoreBeforeSubmit=savedInstanceState.getDouble(THE_SCORE);
            //currentScore=savedInstanceState.getDouble(THE_SCORE);

            Log.d(TAG,"onRestore");


        }

        //initialize the edit texts
        establishmentName = (EditText) findViewById(R.id.nameEstablishmentEditText);
        commentBoxText = (EditText)findViewById(R.id.commentEditText);
        currentScore = (EditText)findViewById(R.id.currentScoreEditText);

        //initialize the seekbar and add a change listener
        happyCheckBox=(CheckBox)findViewById(R.id.happyCheckBox);//happy_checkBox
        fairCheckbox=(CheckBox)findViewById(R.id.fairCheckBox);//fair_checkBox
        annoyedCheckbox=(CheckBox)findViewById(R.id.annoyedCheckBox);//annoyed_checkBox
        ////////////////////////////////////////
        veryCheckbox=(CheckBox)findViewById(R.id.veryCheckBox);//very_CheckBox
        notveryCheckBox=(CheckBox)findViewById(R.id.notCheckBox);//not_CheckBox
        neededhelpCheckBox=(CheckBox)findViewById(R.id.neededHelpCheckBox);//needed_help_CheckBox

        setUpEmployeeAttiude();
        setupKnowledge();


        yesRadioButton = (RadioButton)findViewById(R.id.yesRadioButton);//yes_radioButton
        noRadioButton= (RadioButton)findViewById(R.id.noRadioButton);//no_radioButton
        askRadioButtonl= (RadioButton)findViewById(R.id.askRadioButton);//ask_RadioButton;


       HelpRadioGroup = (RadioGroup) findViewById(R.id.employeeHelpRadioGroup);

        addChangeListenerToRadios();

        ratingScaleSpinner=(Spinner)findViewById(R.id.ratingScaleSpinner);
        ratingScaleSpinner.setPrompt("First Impression Rating");

       addItemSelectedListenerToSpinner();

        submitButton = (Button)findViewById(R.id.submitButton);
        clearButton = (Button)findViewById(R.id.clearButton);

        setupButtonOnClickListeners();
        setTipFromWaitressChecklist();

    }


    private void setUpEmployeeAttiude()
    {

        happyCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                checklistValues[0] = (happyCheckBox.isChecked())?60:0;
                setTipFromWaitressChecklist();

            }
        });



        fairCheckbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                checklistValues[1]= (fairCheckbox.isChecked())?0:0;
                //updateFinalScore();
                setTipFromWaitressChecklist();
            }
        });


        annoyedCheckbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                checklistValues[2]= (annoyedCheckbox.isChecked())?-10:0;
                //updateFinalScore();
                setTipFromWaitressChecklist();
            }
        });



    }


    private void setupKnowledge()
    {
        veryCheckbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                checklistValues[3] = (veryCheckbox.isChecked())?20:0;
                //updateFinalScore();
                setTipFromWaitressChecklist();

            }
        });

        notveryCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                checklistValues[4] = (notveryCheckBox.isChecked())?-10:0;
                //updateFinalScore();
                setTipFromWaitressChecklist();
            }
        });

        neededhelpCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                checklistValues[5] = (neededhelpCheckBox.isChecked())?-10:0;
                //updateFinalScore();
                setTipFromWaitressChecklist();

            }
        });

    }



    private void setTipFromWaitressChecklist()
    {
        int checklistTotal = 0;
        // Cycle through all the checklist values to calculate
        // a total amount based on waitress performance
        for(int item : checklistValues)
        {
            checklistTotal += item;
        }
        // Set tipAmountET
        currentScore.setText(new String(String.valueOf(checklistTotal)));
    }



    private void addChangeListenerToRadios()//yesRadioButton noRadioButton askRadioButtonl
    {
        HelpRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                checklistValues[6]=(yesRadioButton.isChecked())?10:0;
                checklistValues[7]=(noRadioButton.isChecked())?-40:0;
                checklistValues[8]=(askRadioButtonl.isChecked())?-50:0;

                setTipFromWaitressChecklist();
            }
        });
    }


    private void  addItemSelectedListenerToSpinner()
    {
        ratingScaleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                checklistValues[9]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("10"))?10:0;
                checklistValues[10]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("9"))?9:0;
                checklistValues[11]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("8"))?8:0;
                checklistValues[12]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("7"))?7:0;
                checklistValues[13]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("6"))?6:0;
                checklistValues[14]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("5"))?5:0;
                checklistValues[15]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("4"))?4:0;
                checklistValues[16]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("3"))?3:0;
                checklistValues[17]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("2"))?2:0;
                checklistValues[18]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("1"))?1:0;
                checklistValues[19]=(String.valueOf(ratingScaleSpinner.getSelectedItem()).equals("0"))?-1:0;
                setTipFromWaitressChecklist();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }



    public void getTheName()
    {
      String name=String.valueOf(establishmentName.getText());
      String addon=" Got the Score of"+" "+currentScore.getText();

        TextboxTwo = establishmentName.getText().toString();

        String empty="Please don't forget to enter a name";

        if (TextboxTwo.matches(""))
        {
            Toast.makeText(this,empty,Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,name+addon,Toast.LENGTH_LONG).show();
            currentScore.setTextColor(Color.parseColor("#f44242"));

        }



    }

    public void getTheComments()
    {
        Textbox = commentBoxText.getText().toString();
        String empty="Please don't forget to enter some comments.";
        String comments = "Thanks for the comment(s), we will keep them in our records";
        if (Textbox.matches(""))
        {
         Toast.makeText(this,empty,Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, comments, Toast.LENGTH_LONG).show();

            commentBoxText.setText("");
        }
    }



   private void setupButtonOnClickListeners()
    {
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             getTheName();
                getTheComments();


                       //currentScore.setText(new String(String.valueOf(checklistTotal)));
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                establishmentName.setText("");
                commentBoxText.setText("");
                currentScore.setText("");

            }
        });
    }




}
