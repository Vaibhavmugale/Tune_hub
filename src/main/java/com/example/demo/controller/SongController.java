package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

@Controller
public class SongController {
	@Autowired
	SongService service;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus=service.songExists(song.getName());
		if(songStatus==false) {
		service.addSong(song);
		}else {
			System.out.println("song alrady exist");
		}
		return "adminHome";
	}
	
	@GetMapping("/ViewSong")
	public String viewSongs(Model model) {
		List<Song> songList=service.fetchAllSongs();
		System.out.println(songList);
		model.addAttribute("songs",songList);
		return "displaySongs";
	}
	
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		
		boolean premiumUser = true;
		if(premiumUser == true) {
		List<Song> songList=service.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "displaySongs";
		}else {
			return "makePayment";
		}
	}
		
}
