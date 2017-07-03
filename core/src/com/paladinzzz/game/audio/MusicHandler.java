package com.paladinzzz.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.paladinzzz.game.util.Constants;

public class MusicHandler {

    private Music music = null;
    private boolean loop = true;

    public MusicHandler(String path, boolean looping){
        this.loop = looping;
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    public void setMusic(String path) {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void playMusic() {
        this.music.setLooping(loop);
        this.music.play();
    }

    public void setVolume (float volume){
        this.music.setVolume(volume);
    }
}
