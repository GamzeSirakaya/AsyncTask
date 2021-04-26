package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        desc();
        action();
    }

    public void desc(){
        button=findViewById(R.id.button);
        textView=findViewById(R.id.button);
        progressBar=findViewById(R.id.progressBar);

    }
    public void action(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask().execute();
            }
        });
    }
    public class AsyncTask extends android.os.AsyncTask<String,Integer,String> {

        public AsyncTask(){
            super();
        }
        @Override
        protected String doInBackground(String... strings) {
            int sayac=0;
            try{
                while(sayac<100){
                    Thread.sleep(5000);
                    sayac++;
                    publishProgress(sayac);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Process Finished Succesfuly!" ;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                progressBar.setMin(50);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBar.setProgress(0,true);
            }
            textView.setText("İşlem Başlıyor...");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            textView.setText("işlem durumu %"+String.valueOf(values[0]));
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            textView.setText("işlem iptal edilidi");
        }
    }

}
