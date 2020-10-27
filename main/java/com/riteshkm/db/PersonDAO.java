package com.riteshkm.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void addPerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Update
    void updatePerson(Person person);

    @Query("DELETE FROM person_table")
    void deleteAllPerson();

    @Query("SELECT * FROM person_table ORDER BY " + "name_column ASC")
    LiveData<List<Person>> getAllPerson();


}
