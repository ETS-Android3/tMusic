package com.adropofliquid.tmusic.views.adapters.holder;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.adropofliquid.tmusic.R;
import com.adropofliquid.tmusic.items.AlbumItem;
import com.adropofliquid.tmusic.views.activity.MainActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
    private final ImageView songArt;
    private final TextView songName;
    private final TextView songArtist;
    private final ImageButton songOptions;
    private final Activity activity;


    public AlbumViewHolder(Activity activity,
                           @NonNull View itemView,
                           ArrayList<AlbumItem> songList) { //constructor
        super(itemView);
        this.activity = activity;

        songArt = itemView.findViewById(R.id.song_art);
        songName = itemView.findViewById(R.id.song_name);
        songArtist = itemView.findViewById(R.id.song_artist);
        songOptions = itemView.findViewById(R.id.song_options);

        itemView.setOnClickListener(v -> viewAlbumSongs(songList,getAdapterPosition()));
        itemView.setOnLongClickListener(v -> {
            showPopup(v);
            return true;
        });

        songOptions.setOnCreateContextMenuListener(this);
        songOptions.setOnClickListener(v -> songOptions.showContextMenu());
    }

    private void viewAlbumSongs(ArrayList<AlbumItem> albums, int adapterPosition) {
        ((MainActivity) activity).replaceFragment(MainActivity.ALBUM_LIST_VIEW, albums.get(adapterPosition).getName());
        //logList(albums, adapterPosition);
        //Log.v("Album", albums.get(adapterPosition).getId()+"");
    }

    public void bindSongsViews(AlbumItem songItem){
        Glide.with(activity).load(songItem.getAlbumArtUri())
                .error(R.drawable.miniplayer_default_album_art)
                .centerCrop()
                .into(songArt);

        songName.setText(songItem.getName());
        songArtist.setText(songItem.getArtist());
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.song_items, contextMenu);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(activity, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.song_items, popup.getMenu());
        popup.show();
    }
}
