package com.example.notesapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "last_updated")
    private long lastUpdated;

    // добавляем флаг «избранное»
    @ColumnInfo(name = "is_favorite")
    private boolean favorite;

    // добавляем флаг «заблокирована»


    // Полный конструктор
    public Note(String title,
                String content,
                long lastUpdated,
                boolean favorite,
                boolean locked) {
        this.title = title;
        this.content = content;
        this.lastUpdated = lastUpdated;
        this.favorite = favorite;

    }

    // Пустой конструктор для Room
    public Note() { }

    // Геттеры / Сеттеры

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public long getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(long lastUpdated) { this.lastUpdated = lastUpdated; }

    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }


}
