package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();

    private OnItemClickListener clickListener;
    private OnFavoriteClickListener favListener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = notes.get(position);

        holder.textViewTitle.setText(note.getTitle());

        String formattedDate = new SimpleDateFormat(
                "dd.MM.yyyy HH:mm", Locale.getDefault())
                .format(new Date(note.getLastUpdated()));
        holder.textViewDate.setText(formattedDate);

        int favRes = note.isFavorite()
                ? R.drawable.ic_star
                : R.drawable.ic_star_border;
        holder.imageFavorite.setImageResource(favRes);

        // клик по карточке — открытие/редактирование
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (clickListener != null && pos != RecyclerView.NO_POSITION) {
                clickListener.onItemClick(notes.get(pos));
            }
        });

        // клик по «звёздочке» — переключение избранного
        holder.imageFavorite.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (favListener != null && pos != RecyclerView.NO_POSITION) {
                favListener.onFavoriteClick(notes.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }


    public Note getNoteAt(int position) {
        return notes.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Note note);
    }
    public void setOnFavoriteClickListener(OnFavoriteClickListener listener) {
        this.favListener = listener;
    }


    static class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDate;
        ImageView imageFavorite;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDate  = itemView.findViewById(R.id.text_view_date);
            imageFavorite = itemView.findViewById(R.id.image_favorite);
        }
    }
}
