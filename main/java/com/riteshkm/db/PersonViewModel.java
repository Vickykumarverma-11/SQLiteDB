package com.riteshkm.db;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {


    private PersonRepository personRepository;
    private LiveData<List<Person>> AllPerson;


    public PersonViewModel(@NonNull Application application) {
        super(application);

        personRepository = new PersonRepository(application);
        AllPerson = personRepository.getAllPerson();

    }

    LiveData<List<Person>> getAllPerson() {
        return AllPerson;
    }

    public void insert(Person person) {
        personRepository.insert(person);
    }
    public void update(Person person) {
        personRepository.update(person);
    }
    public void delete(Person person) {
        personRepository.delete(person);
    }
    public void deleteAll() {
        personRepository.deleteAll();
    }


}
