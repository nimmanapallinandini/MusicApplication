package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Songs;
import com.example.demo.repositories.SongsRepository;
@Service
public class SongsServiceImplementation implements SongsService {
	@Autowired
SongsRepository srepo;

	@Override
	public String addSongs(Songs song) {
		srepo.save(song);
		
		
		return "song is added";
	}

	@Override
	public boolean songExists(String name) {
		Songs song=srepo.findByName(name);
		if(song==null) {
			return false;
		}else {
		return true;
	}

}

	@Override
	public List<Songs> fetchAllSongs() {
		List<Songs> songslist=srepo.findAll();
		return songslist;
	}

	@Override
	public void updateSongs(Songs song) {
		// TODO Auto-generated method stub
		srepo.save(song);
		
	}
}
