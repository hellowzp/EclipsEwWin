package Audio;
import javax.sound.sampled.*;


/**
 * Deze klasse zal een audioplayer met de gewenste instellingen aanmaken,
 * die een bepaalde audio, ingegeven via String s, kan afspelen gedurende het spel.
 * Er zijn methodes om deze te starten, te stoppen en te sluiten.
 * 
 * @param s String url pointing to the desired audio clip
 * 
 * @author Seppe Van Hees & Stijn Van Crombrugge
 */



public class AudioPlayer {

	
private Clip clip;
	
	
	public AudioPlayer(String s) {
		
		try {
			
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(
						s
					)
				);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream dais =
				AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Het afspelen van de audio
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	// Het stoppen van de audio
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	// Het sluiten van de audioplayer
	public void close() {
		stop();
		clip.close();
	}
	
}














