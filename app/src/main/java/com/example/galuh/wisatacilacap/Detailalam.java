package com.example.galuh.wisatacilacap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class Detailalam extends Activity{
    String n,p;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailalam);
        TextView nama=(TextView)findViewById(R.id.textView);
        //TextView kunci=(TextView)findViewById(R.id.textView2);
        Bundle ex=getIntent().getExtras();
        n=ex.getString("title");
        p=ex.getString("kunci");
        nama.setText(n);
        mImageLoader = new ImageLoader(ApplicationClass.getInstance().getRequestQueue(), new BitmapLruCache());
        NetworkImageView imageView = (NetworkImageView)findViewById(R.id.image1);
        imageView.setImageUrl("http://10.0.2.2/wisataCilacap/images/"+p, mImageLoader);
        ImageButton hom=(ImageButton) findViewById(R.id.imageButton11);
        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detailalam.this, Halamanutama.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailalam, menu);
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
