package com.example.osama.nimgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public int matchesNum[] = {1, 3, 5};
    public ImageView ivMatches[];
    public Button btnRemove[];
    public Button reset, done;
    public TextView player;
    public int row, turn;
    public boolean active, moveMade;
    public void newGame(){
        matchesNum[0] = 1;
        matchesNum[1] = 3;
        matchesNum[2] = 5;
        for(int i=0; i<ivMatches.length; i++)
            ivMatches[i].setAlpha(1.0f);
        row = 0;
        active = true;
        moveMade = false;
        turn = (int)(Math.random() + 0.5);

        player.setText("Player " + (1+turn) + "'s turn");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivMatches = new ImageView[9];
        ivMatches[0] = (ImageView)findViewById(R.id.r1c1);
        ivMatches[1] = (ImageView)findViewById(R.id.r2c1);
        ivMatches[2] = (ImageView)findViewById(R.id.r2c2);
        ivMatches[3] = (ImageView)findViewById(R.id.r2c3);
        ivMatches[4] = (ImageView)findViewById(R.id.r3c1);
        ivMatches[5] = (ImageView)findViewById(R.id.r3c2);
        ivMatches[6] = (ImageView)findViewById(R.id.r3c3);
        ivMatches[7] = (ImageView)findViewById(R.id.r3c4);
        ivMatches[8] = (ImageView)findViewById(R.id.r3c5);
        btnRemove = new Button[3];
        btnRemove[0] = (Button)findViewById(R.id.removeR1);
        btnRemove[1] = (Button)findViewById(R.id.removeR2);
        btnRemove[2] = (Button)findViewById(R.id.removeR3);
        for(int i=0; i<btnRemove.length; i++)
            btnRemove[i].setOnClickListener(onMove);

        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(onReset);

        player = (TextView)findViewById(R.id.turn);

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(onDone);

        newGame();
    }

    public View.OnClickListener onMove = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!active)
                return;

            switch(v.getId()){
                case R.id.removeR1:
                    if(matchesNum[0] > 0 && (row == 0 || row == 1) ){
                        ivMatches[matchesNum[0] -1].setAlpha(0f);
                        matchesNum[0]--;
                        row = 1;
                        moveMade = true;
                    }else if(row != 0 && row != 1){
                        Toast.makeText(MainActivity.this, "You can't remove from multiple rows in one turn!", Toast.LENGTH_SHORT).show();
                    }else if(matchesNum[0] == 0){
                        Toast.makeText(MainActivity.this, "The row is empty!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.removeR2:
                    if(matchesNum[1] > 0 && (row == 0 || row == 2)){
                        ivMatches[matchesNum[1]].setAlpha(0f);
                        matchesNum[1]--;
                        row = 2;
                        moveMade = true;
                    }else if(row != 0 && row != 2){
                        Toast.makeText(MainActivity.this, "You can't remove from multiple rows in one turn!", Toast.LENGTH_SHORT).show();
                    }else if(matchesNum[1] == 0){
                        Toast.makeText(MainActivity.this, "The row is empty!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.removeR3:
                    if(matchesNum[2] > 0 && (row == 0 || row == 3)){
                        ivMatches[matchesNum[2] + 3].setAlpha(0f);
                        matchesNum[2]--;
                        row = 3;
                        moveMade = true;
                    }else if(row != 0 && row != 2){
                        Toast.makeText(MainActivity.this, "You can't remove from multiple rows in one turn!", Toast.LENGTH_SHORT).show();
                    }else if(matchesNum[2] == 0){
                        Toast.makeText(MainActivity.this, "The row is empty!", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
            if(finished()){
                active = false;
                player.setText("Player " + (2-turn) + " won  " );
            }
        }
    };
    public View.OnClickListener onReset = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            newGame();
        }
    };

    public View.OnClickListener onDone = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            if(active && moveMade){
                moveMade = false;
                row=0;
                turn = 1-turn;
                player.setText("Player " + (1+turn) + "'s turn");
            }else if(active){
                Toast.makeText(MainActivity.this, "You have to make a move first", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public boolean finished(){
        return (matchesNum[0] == 0 && matchesNum[1] == 0 && matchesNum[2] == 0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
