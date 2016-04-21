package com.kree.keehoo.leasegreendao2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LeaseActivity extends AppCompatActivity {

    public EditText editTextItem;
    public EditText editTextComment;
    public Spinner spinnerPerson;
    public Button buttonSave;
    public Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lease);

        editTextItem = (EditText) findViewById(R.id.activity_lease_item);
        editTextComment = (EditText) findViewById(R.id.activity_lease_comment);
        spinnerPerson = (Spinner) findViewById(R.id.activity_lease_person);
        buttonSave = (Button) findViewById(R.id.activity_lease_save);
        buttonDelete = (Button) findViewById(R.id.activity_lease_delete);
    }

}
