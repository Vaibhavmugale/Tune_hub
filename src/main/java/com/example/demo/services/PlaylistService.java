package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Playlist;

public interface PlaylistService {

	void addplaylist(Playlist playlist);

	List<Playlist> fetchAllPlaylist();

}
