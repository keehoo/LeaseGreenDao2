package com.kree.keehoo.leasegreendao2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kree.keehoo.leasegreendao2.model.DaoSession;
import com.kree.keehoo.leasegreendao2.model.Lease;
import com.kree.keehoo.leasegreendao2.model.LeaseDao;
import com.kree.keehoo.leasegreendao2.model.Person;
import com.kree.keehoo.leasegreendao2.model.PersonDao;

public class LeaseActivity extends AppCompatActivity {

    public EditText editTextItem;
    public EditText editTextComment;
    public Spinner spinnerPerson;
    public Button buttonSave;
    public Button buttonDelete;

    public Lease lease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease);

        Log.d("LeaseActivity", "   onCreate inside LeaseActivity");

        editTextItem = (EditText) findViewById(R.id.activity_lease_item);
        editTextComment = (EditText) findViewById(R.id.activity_lease_comment);
        spinnerPerson = (Spinner) findViewById(R.id.activity_lease_person);
        buttonSave = (Button) findViewById(R.id.activity_lease_save);
        buttonDelete = (Button) findViewById(R.id.activity_lease_delete);

        Intent intent = getIntent();
        Long leaseId = intent.getLongExtra("lease_id", 0L);
        if (leaseId != 0) {
            Log.d("LeaseActivity", "   leaseId != 0  --- leaseId to jest extra z intenta - lease_id ");
            DaoSession daoSession = ((LeaseApplication) getApplicationContext()).getDaoSession();
            LeaseDao leaseDao = daoSession.getLeaseDao();
            lease = leaseDao.load(leaseId);
            if (lease != null) {
                editTextItem.setText(lease.getItem());
                editTextComment.setText(lease.getComment());
            } else {
                lease = new Lease();
                if (lease == null) {
                    Log.d("LeaseActivity....", " lease nadal rowne jest null mimo wykonania lease = new Lease()");
                    finish();
                }
            }
        }

        Log.d("LeaseActivity", "At this point we should have a clear information on what is lease ");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(editTextItem.getText()) == null) {
                    Log.d("LeaseActivity", "text jest rowny null");
                    Toast.makeText(LeaseActivity.this, "String.valueOf(editTextItem.getText() == null !!!!!!!", Toast.LENGTH_LONG).show();
                }


                if (lease == null) {
                    Log.d("---LeaseActivity---", " =====lease refernce is NULL !!!!!!!==== Why is this still null, bitch??");
                    lease = new Lease();
                }
                    Log.d("LeaseActivity", "  OOOO   editTextItem value = " + editTextItem.getText().toString());
                    lease.setItem(editTextItem.getText().toString());
                    lease.setComment(editTextComment.getText().toString());
                    DaoSession daoSession = ((LeaseApplication) getApplicationContext()).getDaoSession();

                    // added in the fourth part of tutorial to randomize person, not select from spinner

                    Person person = new Person();
                    person.setName("John Doe " + Math.random());
                    PersonDao personDao = daoSession.getPersonDao();
                    personDao.insertOrReplace(person);

                    lease.setPerson(person);

                    daoSession.insertOrReplace(lease);
                    finish();
                }

        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoSession daoSession = ((LeaseApplication) getApplicationContext()).getDaoSession();
                daoSession.delete(lease);
                finish();
            }
        });

    }


}
