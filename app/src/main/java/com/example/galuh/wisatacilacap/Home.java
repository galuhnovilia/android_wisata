package com.example.galuh.wisatacilacap;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class Home extends AppCompatActivity {

    ProgressBar pgr;
    int progress=0;
    Handler h= new Handler() ;
    int finis=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("Wisata Cilacap");
        pgr=(ProgressBar)findViewById(R.id.progressBar2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++)
                {
                    progress+=20;
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            pgr.setProgress(progress);
                            if (progress==pgr.getMax()) {
                                // pgr.setVisibility(5);
                                Intent intent = new Intent(Home.this,
                                        Halamanutama.class);
                                startActivity(intent);
                                Home.this.finish();
                            }
                        }
                    });
                    try {

                        Thread.sleep(1000);

                    }catch (InterruptedException e){
                    }
                   /* finally {
                        //startActivity(new Intent(Unid.this,HalamanMenu.class));
                        Intent intent = new Intent(Unid.this,
                                HalamanMenu.class);
                        startActivity(intent);
                  }*/
                }
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
