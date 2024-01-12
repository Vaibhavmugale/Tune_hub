package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;


@Controller
public class PlaylistController {
	@Autowired
	SongService songService;
	
	@Autowired 
	PlaylistService playlistservice;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs",songList);
		System.out.println(songList);
		return "createPlaylist";
	}
	
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
//		updating playlist table
		playlistservice.addplaylist(playlist);
		
		//updating song table
		List<Song> songlist=playlist.getSongs();
		for (Song s : songlist) {
			s.getPlaylist().add(playlist);
			songService.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> playist=playlistservice.fetchAllPlaylist();
		System.out.println(playist);
		model.addAttribute("playlist",playist);
		return "displayPlaylist";
	}

}
