package com.example.galuh.wisatacilacap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Kuliner extends AppCompatActivity implements SearchView.OnQueryTextListener,AdapterView.OnItemClickListener{
    ListView lv;
    SearchView search_view;
    ArrayList<String> daftar;
    // private static ArrayList<String> aList;
    //  private static ArrayAdapter<String> adapter2;
    private static ArrayList<WorldPopulation> aList;//2
    private static ListViewAdapter3 adapter2; //2
    private String [][] coba=new String[1000][2];
    //ArrayAdapter<String> adapter;
    ListViewAdapter3 adapter;//2
    ArrayAdapter<String> adapter3;//2
    String n="semua";
    String [] nama;//2
    String [] gambar;//2
    String nn,kun,title;
    int y;//2
    ArrayList<WorldPopulation> arraylist = new ArrayList<WorldPopulation>(); //2
    private String surl = "http://10.0.2.2/wisataCilacap/android/kkuliner.php";
    private String url = "http://10.0.2.2/wisataCilacap/android/kuliner.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner);
        this.setTitle("Kuliner Cilacap");
        lv = (ListView) findViewById(R.id.list_view);
        search_view = (SearchView) findViewById(R.id.search_view);
        n="semua";
        readWebpage();
        search_view.setOnQueryTextListener(this);
       /* lv.setOnItemClickListener(this);
        ImageButton hom=(ImageButton)findViewById(R.id.button8);
        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Wisata.this, Halamanutama.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });*/

        lv.setOnItemClickListener(this);
        search_view.setOnQueryTextListener(this);
        lv.setAdapter(adapter);
        ImageButton hom=(ImageButton)findViewById(R.id.button8);
        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Kuliner.this, Halamanutama.class);
                // set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        }); //2
    }

    public static void sortList(int order)
    {
        Collections.sort(aList, new Sorter(order));
        adapter2.notifyDataSetChanged();
    }

    static class Sorter implements Comparator<Object> {
        int order=-1;
        Sorter(int order){
            this.order=order;
        }

        public int compare(Object obj1, Object obj2)
        {
            if(obj1.toString().compareTo(obj2.toString())==0) return 0;
            else if(obj1.toString().compareTo(obj2.toString())<0) return order;
            else return (-1*order );
        }
    }



    @Override
    public void onItemClick(AdapterView arg0, View view, int posisi, long arg3) {
        title = ((TextView) view.findViewById(R.id.name)).getText().toString();
        //Toast.makeText(MainActivity.this, "Anda memilih " + title, Toast.LENGTH_SHORT).show();
        nn=title;
        boolean ketemu=false;
        //n="cari";
        //readWebpage();
        for (int j=0;j<coba.length;j++){
            if(coba[j][0]==nn){
                ketemu=true;
                kun=coba[j][1];
                Intent intent = new Intent(Kuliner.this, Detailkuliner.class);
                intent.putExtra("title",title);
                intent.putExtra("kunci",kun);
                startActivity(intent);
                n="semua";
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // adapter.getFilter().filter(newText);
        adapter.filter(newText);//2
        lv.invalidate();//2
        return false;
    }

    public String getRequest(String Url){
        String sret;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Url);
        try{
            HttpResponse response = client.execute(request);
            sret= request(response);
        }catch(Exception ex){
            sret= "Failed Connect to server!";
        }
        return sret;
    }

    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }

    private class CallWebPageTask extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog;
        protected Kuliner applicationContext;
        @Override
        protected void onPreExecute() {
            this.dialog = ProgressDialog.show(applicationContext, "Loading..", "Akan menampilkan Data Kuliner", true);
        }
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            response = getRequest(urls[0]);
            return response;
        }
        @Override
        protected void onPostExecute(String result) {
            this.dialog.cancel();
            try {
                y=0;//2
                JSONArray data ;
                JSONObject jsonRootObject = new JSONObject(result);
                JSONArray jsonArray = jsonRootObject.optJSONArray("kuliner");
                data=jsonArray;
                daftar = new ArrayList<String>();
                if (n.equals("semua")){
                    for(int i=0; i < data.length(); i++){
                        JSONObject jsonObject = data.getJSONObject(i);
                        daftar.add(jsonObject.optString("namak").toString());
                        coba[i][0] = jsonObject.optString("namak").toString();
                        coba[i][1] = jsonObject.optString("linkk").toString();
                        y=y+1;//2
                        //ti[i]=jsonObject.optString("nama").toString();
                        //kun[i]=jsonObject.optString("kunci").toString();

                    }
                    nama=new String[y];//2
                    gambar=new String[y];//2
                    for (int x=0;x<y;x++){//2
                        nama[x]=coba[x][0];//2
                        gambar[x]=coba[x][1];//2
                    }
                    //  aList=daftar;
                    //  adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.daftar, R.id.name, daftar);

                    //aList=new ArrayList<>(Arrays.asList(adapter));
                    //adapter2=new ArrayAdapter<String>(getActivity(),R.layout.daftar,R.id.name,daftar);

                    for (int i = 0; i < nama.length; i++)
                    {
                        WorldPopulation wp = new WorldPopulation(nama[i], gambar[i]);
                        // Binds all strings into an array
                        arraylist.add(wp);
                    }//2
                    aList=arraylist;//2
                    adapter = new ListViewAdapter3(Kuliner.this, arraylist);//2
                    adapter2=adapter;
                    lv.setAdapter(adapter);

                }

                if (n.equals("cari")){

                    for(int i=0; i < data.length(); i++){
                        JSONObject jsonObject = data.getJSONObject(i);
                        kun=(jsonObject.optString("linkk").toString());
                    }
                    Intent intent = new Intent(Kuliner.this, Detailkuliner.class);
                    //intent.putExtra("kunci",kunci);
                    intent.putExtra("title",title);
                    intent.putExtra("kunci",kun);
                    startActivity(intent);
                    //ha.setKunci(kun);
                    n="semua";
                }
                //Vadapter adapter=new Vadapter(MainActivity.this,ti,kun);

            } catch (JSONException e) {e.printStackTrace();}
        }
    }

    public void readWebpage() {
        if (n.equals("semua")){
            //Toast.makeText(MainActivity.this, "masuk semua" , Toast.LENGTH_SHORT).show();
            CallWebPageTask task = new CallWebPageTask();
            task.applicationContext = Kuliner.this;
            task.execute(new String[]{surl});
        }
        else if (n.equals("cari")){
            //Toast.makeText(MainActivity.this, "masuk " + title, Toast.LENGTH_SHORT).show();
            CallWebPageTask task = new CallWebPageTask();
            task.applicationContext = Kuliner.this;
            String rl= url + "?kunci=" +title;
            task.execute(new String[]{rl});
        }
        else{
            Toast.makeText(Kuliner.this, "Eror " + title, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kuliner, menu);
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
