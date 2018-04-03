package com.example.galuh.wisatacilacap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Halamanutama extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanutama);

       // Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        this.setTitle("Menu Utama");
        ImageButton cilacap=(ImageButton)findViewById(R.id.imageButton2);
        cilacap.setOnClickListener(this);
        ImageButton sejarah=(ImageButton)findViewById(R.id.imageButton);
        sejarah.setOnClickListener(this);
        ImageButton profil=(ImageButton)findViewById(R.id.imageButton3);
        profil.setOnClickListener(this);
        ImageButton bantuan=(ImageButton)findViewById(R.id.imageButton4);
        bantuan.setOnClickListener(this);
        ImageButton keluar=(ImageButton)findViewById(R.id.imageButton5);
        keluar.setOnClickListener(this);
        //setResult(100);
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_halamanutama, menu);
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

    public void close(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Benar-Benar ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Halamanutama.this.finish();
                            }
                        })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        dialog.cancel();

                    }
                }).show();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            close();

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton2:
                Intent i=new Intent(this,Cilacap.class);
                i.putExtra("kirim","oke");
                startActivity(i);
                //startActivityForResult(i,100);
                break;
           /* case R.id.imageButton:
                Intent i2=new Intent(this,Sejarah.class);
                startActivity(i2);
                break;
            case R.id.imageButton3:
                Intent i3=new Intent(this,Profil.class);
                startActivity(i3);
                break;
            case R.id.imageButton4:
                Intent i4=new Intent(this,Bantuan.class);
                startActivity(i3);
                break;*/
            case R.id.imageButton5:
                close();
                break;
        }

    }
}
