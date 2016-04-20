package com.kree.keehoo.leasegreendao2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kree.keehoo.leasegreendao2.model.DaoMaster;
import com.kree.keehoo.leasegreendao2.model.DaoSession;
import com.kree.keehoo.leasegreendao2.model.Lease;
import com.kree.keehoo.leasegreendao2.model.LeaseDao;
import com.kree.keehoo.leasegreendao2.model.Person;
import com.kree.keehoo.leasegreendao2.model.PersonDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "lease-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        LeaseDao leaseDao = daoSession.getLeaseDao();
        List leaseList = leaseDao.loadAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityListAdapter adapter = new MainActivityListAdapter(leaseList);
        recyclerView.setAdapter(adapter);

        insertSampleData(daoSession);


    }
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
