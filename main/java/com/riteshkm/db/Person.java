package com.riteshkm.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")

public class Person {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "mob_column")
    private long mob;

    @NonNull
    @ColumnInfo(name = "name_column")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person(@NonNull String name,long mob) {
        this.mob = mob;
        this.name = name;
    }

    public long getMob() {
        return mob;
    }

    public void setMob(long mob) {
        this.mob = mob;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
