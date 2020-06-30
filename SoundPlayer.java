import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundPlayer {

	AudioInputStream audioIn;
	Clip clip;
	
	public SoundPlayer() {
		
	}
	public void speakWord(String word) {
		
		try {
			
			audioIn = getWordStream(word);
			
			if (getClip() != null && getClip().isActive()) {
				
				getClip().stop();
			}
			
			setClip(AudioSystem.getClip());
			getClip().open(audioIn);
			getClip().start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	public AudioInputStream getWordStream(String word) {

		AudioInputStream completeStream = null;
		for (char c : word.toCharArray()) {
			try {

				URL url = this.getClass().getClassLoader().getResource("sounds/" + c + ".wav");
            
				if (completeStream == null) {

					// if this is the first noise, initialize the stream with it
					completeStream = AudioSystem.getAudioInputStream(url);

				} else {

					// if this isn't the first noise, add it to the stream
					AudioInputStream newStream = AudioSystem.getAudioInputStream(url);

					completeStream = new AudioInputStream(
							new SequenceInputStream(completeStream, newStream),
							completeStream.getFormat(), completeStream.getFrameLength() 
							+ newStream.getFrameLength());

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return completeStream;

	}
	public Clip getClip() {
		return clip;
	}
	public void setClip(Clip clip) {
		this.clip = clip;
	}

}
