package in.karthi.learncodeonline.learndata;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Assesment extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv, lv1;

    ArrayList<HashMap<String, String>> questionList;
    ArrayList<HashMap<String, String>> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment);

        questionList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        lv1 = (ListView) findViewById(R.id.list1);

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Assesment.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://learncodeonline.in/api/android/datastructure.json";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("questions");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String question = c.getString("question");
                        String answer = c.getString("Answer");


                        // tmp hash map for single questions
                        HashMap<String, String> questions = new HashMap<>();
                        HashMap<String, String> answers = new HashMap<>();

                        // adding each child node to HashMap key => value
                        questions.put("question", question);
                        answers.put("Answer", answer);


                        // adding questions to questions list
                        questionList.add(answers);
                        //answerList.add(answers);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(Assesment.this, questionList,
                    R.layout.list_item, new String[]{ "question","Answer"},
                    new int[]{R.id.email, R.id.mobile});
            lv.setAdapter(adapter);
//            ListAdapter adapter1 = new SimpleAdapter(Assesment.this, answerList,
//                    R.layout.list_item, new String[]{ "question","Answer"},
//                    new int[]{R.id.email, R.id.mobile});
//            lv1.setAdapter(adapter1);
        }
    }

}
