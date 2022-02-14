package com.example.admitad.repository;

public interface BaseRepository {
    String getName();

    void createTableIfMissing();

    void dropTableIfExists();
}
