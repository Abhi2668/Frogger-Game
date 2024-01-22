package com.example.froggerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InitialConfigurationScreen extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private Spinner difficultiesSpinner;
    private String diff;

    private String pName;
    private String spriteChoice;
    private EditText playerName;
    private TextView displayDifficulty;
    private TextView displayName;

    private int selectedSprite;

    private Button startButton;
    private ImageButton b0;
    private ImageButton b1;
    private ImageButton b2;
    private ImageView fc;

    private Resources sprite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_configuration_screen);

        //NAME SELECTION
        playerName = (EditText) findViewById(R.id.playerName);
        playerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                displayName = findViewById(R.id.displayName);
                displayName.setText(playerName.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        //DIFFICULTIES SELECTION
        difficultiesSpinner = (Spinner) findViewById(R.id.difficultySelector);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.difficulties,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultiesSpinner.setAdapter(adapter);
        difficultiesSpinner.setOnItemSelectedListener(this);

        difficultiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                displayDifficulty = findViewById(R.id.displayDifficulty);
                displayDifficulty.setText(difficultiesSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //SPRITE SELECTION
        b0 = findViewById(R.id.sprintButton0);
        b1 = findViewById(R.id.sprintButton1);
        b2 = findViewById(R.id.sprintButton2);
        fc = findViewById(R.id.finalCharacter);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteChoice = "0";
                fc.setImageResource(R.drawable.blinky);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteChoice = "1";
                fc.setImageResource(R.drawable.clyde);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteChoice = "2";
                fc.setImageResource(R.drawable.inky);
            }
        });


        //RETRIEVE ALL VALUES AND GO TO NEXT SCREEN (IF POSSIBLE) ---------------------------
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String error = "";
                String n = playerName.getText().toString();
                error = verifyUsername(n, error);
                error = verifySpriteSelected(spriteChoice, error);


                if (error.length() > 0) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                } else {
                    //Retrieve name
                    pName = playerName.getText().toString();
                    //Retrieve difficulty
                    diff = difficultiesSpinner.getSelectedItem().toString();
                    //Retrieve Sprite

                    //GO TO NEW PAGE

                    Intent intent = new Intent(InitialConfigurationScreen.this,
                            PreGameScreen.class);
                    intent.putExtra("pName", pName);
                    intent.putExtra("diff", diff);
                    intent.putExtra("sprite", spriteChoice);
                    startActivity(intent);

                }
            }
        });



    }

    public String verifyUsername(String n, String error) {
        if (n == null || n.isEmpty()) {
            error = error + "Please enter a Name\n";
        } else if (n.isBlank()) {
            error = error + "Invalid Name - Cannot Be Only Whitespace\n";
        } else if (n.length() > 10) {
            error = error + "Name too long, reduce a bit!\n";
        }
        return error;
    }

    public String verifySpriteSelected(String spriteChoice, String error) {
        if (spriteChoice != "0" && spriteChoice != "1" && spriteChoice != "2") {
            error = error + "Please Choose a Sprite";
        }
        return error;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
        diff = (String) difficultiesSpinner.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}