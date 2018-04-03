
package com.example.galuh.wisatacilacap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Cilacap extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cilacap);

        ImageButton wisata=(ImageButton)findViewById(R.id.imageButton2);
        wisata.setOnClickListener(this);
        ImageButton alam=(ImageButton)findViewById(R.id.imageButton);
        alam.setOnClickListener(this);
        ImageButton kuliner=(ImageButton)findViewById(R.id.imageButton3);
        kuliner.setOnClickListener(this);
      //  ImageButton peta=(ImageButton)findViewById(R.id.imageButton4);
      //  peta.setOnClickListener(this);
        ImageButton home=(ImageButton)findViewById(R.id.imageButton5);
        home.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cilacap, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton2:
                Intent wis= new Intent(this,Wisata.class);
                wis.putExtra("kirim", "oke");
                startActivity(wis);
                //startActivity(gm);
                break;
            case R.id.imageButton:
                Intent a= new Intent(this,Alam.class);
                a.putExtra("kirim","oke");
                startActivity(a);
                //startActivity(gm);
                break;
            case R.id.imageButton3:
                Intent kul= new Intent(this,Kuliner.class);
                kul.putExtra("kirim", "oke");
                startActivity(kul);
                //startActivity(gm);
                break;
           /* case R.id.imageButton4:
                Intent map= new Intent(this,Peta.class);
                map.putExtra("kirim", "oke");
                startActivity(map);
                //startActivity(gm);
                break;*/
            case R.id.imageButton5:
                Intent hm=new Intent(this,Home.class);
                //startActivity(vd);
                startActivity(hm);
                break;


        }

    }
}
