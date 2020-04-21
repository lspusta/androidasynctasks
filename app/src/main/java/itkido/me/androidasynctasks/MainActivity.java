package itkido.me.androidasynctasks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle;
    EditText editText;
    TextView txtDisplayEnteredText;
    Button btnDisplayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTitle = findViewById(R.id.txtTitle);
        editText = findViewById(R.id.editText);
        txtDisplayEnteredText = findViewById(R.id.txtDisplayEnteredText);
        btnDisplayText = findViewById(R.id.btnDisplayText);

        btnDisplayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "3";
                runner.execute(sleepTime);
            }
        });

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                //resp = "Slept for " + params[0] + " seconds";
                resp = editText.getText().toString();
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String textEntered) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            txtDisplayEnteredText.setText(textEntered);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Waiting",
                    "Wait for "+ "3" + " seconds");
        }

        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

}
