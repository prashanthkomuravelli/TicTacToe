package com.hp.tictactoe;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    boolean gameIsactive=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void dropin(View view)
    {
        ImageView counter=(ImageView)view;
        int tappedcounter=Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2&&gameIsactive) {
            gamestate[tappedcounter]=activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.grey);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.blue);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);
            for( int[] winningPosition:winningPositions)
            {
                if(gamestate[winningPosition[0]]==gamestate[winningPosition[1]]
                        &&gamestate[winningPosition[0]]==gamestate[winningPosition[2]]
                        &&gamestate[winningPosition[0]]!=2)
                {
                    gameIsactive=false;
                    String msg;
                    if(gamestate[winningPosition[0]]==0)
                    {
                        msg="grey";
                    }
                    else
                    {
                        msg="blue";
                    }
                    TextView winnermessage=(TextView)findViewById(R.id.winnermessage);
                    winnermessage.setText(msg+" has Won! ");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                    layout.setVisibility(view.VISIBLE);
                }
                else
                {
                    boolean gameisover=true;
                    for(int counterstate:gamestate)
                    {
                        if(counterstate==2)
                        {
                            gameisover=false;
                        }
                    }
                    if(gameisover)
                    {
                        TextView winnermessage=(TextView)findViewById(R.id.winnermessage);
                        winnermessage.setText("    It's a draw!");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                        layout.setVisibility(view.VISIBLE);
                    }
                }
            }
        }
    }
    public void playagain(View view)
    {
        gameIsactive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);

        layout.setVisibility(View.INVISIBLE);
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        GridLayout gridlayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridlayout.getChildCount();i++)
        {
            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
