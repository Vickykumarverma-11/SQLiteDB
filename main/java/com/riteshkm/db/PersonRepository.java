package com.riteshkm.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonRepository {
    
    private PersonDAO personDAO;
    private LiveData<List<Person>> allPerson;

    PersonRepository(Application application) {
        
        PersonDB db = PersonDB.getDatabase(application);
        personDAO = db.getPersonDAO();
        allPerson = personDAO.getAllPerson();
    }
    
    public void insert(Person person) {
        new InsertPersonAsynctask(personDAO).execute(person);
    }

    public void update(Person person) {
        new UpdatePersonAsynctask(personDAO).execute(person);
    }

    public void delete(Person person) {
        new DeletePersonAsynctask(personDAO).execute(person);
    }
    public void deleteAll() {
        new DeleteAllPersonAsynctask(personDAO).execute();
    }
    LiveData<List<Person>> getAllPerson() {
        return allPerson;
    }


    static class InsertPersonAsynctask extends AsyncTask<Person,Void,Void> {

        private PersonDAO personDAO;

        public InsertPersonAsynctask(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }
        @Override
        protected Void doInBackground(Person... people) {
            personDAO.addPerson(people[0]);
            return null;
        }


    }

    static class UpdatePersonAsynctask extends AsyncTask<Person,Void,Void> {

        private PersonDAO personDAO;

        public UpdatePersonAsynctask(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDAO.updatePerson(people[0]);
            return null;
        }
    }

    static class DeletePersonAsynctask extends AsyncTask<Person,Void,Void> {

        private PersonDAO personDAO;

        public DeletePersonAsynctask(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDAO.deletePerson(people[0]);
            return null;
        }
    }

    static class DeleteAllPersonAsynctask extends AsyncTask<Void,Void,Void> {

        private PersonDAO personDAO;

        public DeleteAllPersonAsynctask(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personDAO.deleteAllPerson();
            return null;
        }
    }



    
}
