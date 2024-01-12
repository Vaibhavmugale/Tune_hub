package com.example.demo.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	String artst;
	String genre;
	String link;
	@ManyToAny
	List<Playlist> playlist;
	public Song() {
		super();
	}
	public Song(int id, String name, String artst, String genre, String link, List<Playlist> playlist) {
		super();
		this.id = id;
		this.name = name;
		this.artst = artst;
		this.genre = genre;
		this.link = link;
		this.playlist = playlist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtst() {
		return artst;
	}
	public void setArtst(String artst) {
		this.artst = artst;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Playlist> getPlaylist() {
		return playlist;
	}
	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}
	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artst=" + artst + ", genre=" + genre + ", link=" + link
				+ ", playlist=" + playlist + "]";
	}
	
}
