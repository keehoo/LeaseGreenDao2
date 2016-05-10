package com.kree.keehoo.leasegreendao2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kree.keehoo.leasegreendao2.model.DaoSession;
import com.kree.keehoo.leasegreendao2.model.Lease;
import com.kree.keehoo.leasegreendao2.model.LeaseDao;
import com.kree.keehoo.leasegreendao2.model.Person;
import com.kree.keehoo.leasegreendao2.model.PersonDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*@Override
    protected void onResume() {
        super.onResume();
        DaoSession daoSession = ((LeaseApplication) getApplicationContext()).getDaoSession();

        LeaseDao leaseDao = daoSession.getLeaseDao();
        List<Lease> leaseList = leaseDao.loadAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityListAdapter adapter = new MainActivityListAdapter(leaseList, this);
        recyclerView.setAdapter(adapter);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Main activity onCreate started");
        DaoSession daoSession = ((LeaseApplication) getApplicationContext()).getDaoSession();
        LeaseDao leaseDao = daoSession.getLeaseDao();
        List leaseList = leaseDao.loadAll();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityListAdapter adapter = new MainActivityListAdapter(leaseList, this);
        recyclerView.setAdapter(adapter);
        insertSampleData(daoSession);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, LeaseActivity.class);
            intent.putExtra("lease_id", 0L);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public boolean onOptionsItemSelected(Menu item) {
        getMenuInflater().inflate(R.menu.main_menu, item);
        Intent intent = new Intent(this, LeaseActivity.class);
        intent.putExtra("lease_id", 0);
        this.startActivityForResult(intent, 1);

        return true;
    }*/

    public void insertSampleData(DaoSession daoSession) {
        Person person = new Person();
        person.setName("John Doe");
        PersonDao personDao = daoSession.getPersonDao();
        personDao.insertOrReplace(person);

        Lease lease = new Lease();
        lease.setItem("My Nexus 6");
        lease.setPerson(person);
        LeaseDao leaseDao = daoSession.getLeaseDao();
        leaseDao.insertOrReplace(lease);
    }
}
