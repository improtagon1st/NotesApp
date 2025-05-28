package com.example.notesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY last_updated DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE is_favorite = 1 ORDER BY last_updated DESC")
    LiveData<List<Note>> getFavoriteNotes();

    @Query("SELECT * FROM notes WHERE title LIKE :query OR content LIKE :query ORDER BY last_updated DESC")
    LiveData<List<Note>> searchNotes(String query);

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
