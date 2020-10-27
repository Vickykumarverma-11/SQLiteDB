package com.riteshkm.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Person.class},version = 1,exportSchema = false)
public abstract class PersonDB extends RoomDatabase {


    private static PersonDB INSTANCE;

    public abstract PersonDAO getPersonDAO();

    public static synchronized PersonDB
    getDatabase (Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PersonDB.class, "person_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(dbcallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback dbcallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(INSTANCE).execute();
        }
    };

    static class PopulateDbAsynctask extends AsyncTask<Void,Void,Void> {

        private PersonDAO personDAO;

        public PopulateDbAsynctask(PersonDB personDB) {
            personDAO = personDB.getPersonDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            personDAO.addPerson(new Person("AAA",111));
            personDAO.addPerson(new Person("BBB",222));
            personDAO.addPerson(new Person("CCC",333));
            return null;
        }
    }

}
