package com.github.kylecharters;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

public class Group1 implements KeyListener{
	public static void main(String[] args){
		new Group1();
	}
	
	/*
	 * GROUP 1
	 */
	
	public Group1(){
		init();
		
		loadSounds(3);
	}
	
	private HashMap<Integer, Music> sounds = new HashMap<Integer, Music>();
	private HashMap<Integer, Boolean> isPlaying = new HashMap<Integer, Boolean>();
	
	public void init(){
		JFrame window = new JFrame("Audio");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(200, 200);
		window.setAlwaysOnTop(true);
		window.setAutoRequestFocus(true);
		window.addKeyListener(this);
		window.setVisible(true);
	}
	
	public void loadSounds(int s){
		TinySound.init();
		for(int i = 1; i <= s; i++){
			File file = new File(Integer.toString(i)+".wav");
			if(file != null){
				Music sound = TinySound.loadMusic(file);
				
				sounds.put(i, sound);
				isPlaying.put(i, false);
				
				System.out.println("File " + i + " Found");
			}
		}
	}
	
	public void unloadClips(){
		for(Entry<Integer, Music> i : sounds.entrySet()){
			i.getValue().unload();
		}
		sounds.clear();
		isPlaying.clear();
		TinySound.shutdown();
	}
	
	public void playSound(int num){
		System.out.println("Play Sound "+num);
		for(Entry<Integer, Music> i : sounds.entrySet()){
			if(i.getKey() == num){
				i.getValue().play(true);;
				isPlaying.put(i.getKey(), true);
			}
		}
	}
	
	public void stopSound(int num){
		System.out.println("Stop Sound "+num);
		for(Entry<Integer, Music> i : sounds.entrySet()){
			if(i.getKey() == num){
				i.getValue().setLoop(false);
				while(i.getValue().playing()){
					
				}
				isPlaying.put(i.getKey(), false);
			}
		}
	}
	
	public boolean isPlaying(int num){
		for(Entry<Integer, Boolean> i : isPlaying.entrySet()){
			if(i.getKey() == num){
				return i.getValue();
			}
		}
		return false;
	}
	
	/*
	 * INPUT PROCESSING
	 */
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){

			playSound(1);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			playSound(2);
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			playSound(3);
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			stopSound(1);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			stopSound(2);
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			stopSound(3);
		}
	}

	@Override
	public void keyTyped(KeyEvent e){
	}
}
