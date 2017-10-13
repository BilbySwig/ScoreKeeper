/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package jaeginit.scorekeeper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int blueScore = 0;
    int blueStrikes=0;
    int blueOuts=0;
    int redScore = 0;
    int redStrikes=0;
    int redOuts=0;
    int Innings=0;
    boolean redPoss=true;

    String priceMsg = "Free";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view) {
        String tag = view.getTag().toString();
        if (tag.substring(0, 3).equals("red")) {
            if (tag.equals("redSc") && redPoss) {
                redScore += 1;
                int id = R.id.redScore;
                display(Integer.toString(redScore), id);
            } else if (tag.equals("redSt") && redPoss) {
                int id = R.id.redStrikes;
                redStrikes += 1;
                display(Integer.toString(redStrikes), id);
                if (redStrikes >= 3) {
                    redStrikes = 0;
                    display(Integer.toString(redStrikes), id);
                    redOuts += 1;
                    display(Integer.toString(redOuts),R.id.redOuts);
                }
                if (redOuts >= 3) {
                    redOuts = 0;
                    display(Integer.toString(redOuts),R.id.redOuts);
                    redPoss = false;
                }
            }
        }
        if (tag.substring(0, 4).equals("blue")) {
            if (tag.equals("blueSc")) {
                blueScore += 1;
                int id = R.id.blueScore;
                display(Integer.toString(blueScore), id);
            } else if (tag.equals("blueSt") && !redPoss) {
                int id = R.id.blueStrikes;
                blueStrikes += 1;
                display(Integer.toString(blueStrikes), id);
                if (blueStrikes >= 3) {
                    blueStrikes = 0;
                    display(Integer.toString(blueStrikes), id);
                    blueOuts += 1;
                    display(Integer.toString(blueOuts),R.id.blueOuts);
                }
                if (blueOuts >= 3) {
                    blueOuts = 0;
                    display(Integer.toString(blueOuts),R.id.blueOuts);
                    redPoss = true;
                    Innings += 1;
                    display(Integer.toString(Innings), R.id.binnings);
                    display(Integer.toString(Innings), R.id.rinnings);

                }
                if(Innings>=9 && redScore!=blueScore){
                    boolean winning=redScore>blueScore;
                    endGame(winning, view);
                }
            }
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(String str, int id) {
        TextView currTextView = (TextView) findViewById(id);
        currTextView.setText("" + str);
    }

    private void endGame(boolean winner, View view){
        if(winner) {
            display("WINNER", R.id.redTitleView);
            TextView text=(TextView)findViewById(R.id.redTitleView);
            text.setTextColor(0xffffd700);
            text.setTextSize(50);
        }
        else{
            display("WINNER", R.id.blueTitleView);
            TextView text=(TextView)findViewById(R.id.blueTitleView);
            text.setTextColor(0xffffd700);
            text.setTextSize(50);
        }
    }



}