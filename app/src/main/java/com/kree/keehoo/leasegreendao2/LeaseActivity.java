package com.kree.keehoo.leasegreendao2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.kree.keehoo.leasegreendao2.model.DaoSession;
import com.kree.keehoo.leasegreendao2.model.Lease;
import com.kree.keehoo.leasegreendao2.model.LeaseDao;

public class LeaseActivity extends AppCompatActivity{

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

        editTextItem = (EditText) findViewById(R.id.activity_lease_item);
        editTextComment = (EditText) findViewById(R.id.activity_lease_comment);
        spinnerPerson = (Spinner) findViewById(R.id.activity_lease_person);
        buttonSave = (Button) findViewById(R.id.activity_lease_save);
        buttonDelete = (Button) findViewById(R.id.activity_lease_delete);

        Intent intent = getIntent();
        Long leaseId = intent.getLongExtra("lease_id", 0);
        if(leaseId != 0 ) {
            DaoSession daoSession = ((LeaseApplication) getApplicationContext()).getDaoSession();

            LeaseDao leaseDao = daoSession.getLeaseDao();
            lease = leaseDao.load(leaseId);
            if(lease != null) {
                editTextItem.setText(lease.getItem());
                editTextComment.setText(lease.getComment());

            }
        }
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {if(String.valueOf(editTextItem.getText())==null)
                {
                    Log.d("LeaseActivity", "text jest rowny null");
                    Toast.makeText(LeaseActivity.this, "String.valueOf(editTextItem.getText() == null !!!!!!!", Toast.LENGTH_LONG).show();
                }

                lease.setItem(String.valueOf(editTextItem.getText()));
                lease.setComment(String.valueOf(editTextComment.getText()));
                DaoSession daoSession = ((LeaseApplication) getApplicationContext()).getDaoSession();
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
