// Browser Web Audio API sound effect and background music synthesizer for the kid-friendly game show.
// Avoids external audio file loading, ensuring 100% offline reliability.

class SoundManager {
  private ctx: AudioContext | null = null;
  private enabled: boolean = true;
  private musicInterval: any = null;
  private musicStep: number = 0;
  private isMusicPlaying: boolean = false;

  constructor() {
    // AudioContext will be initialized on first user interaction to satisfy browser policies.
  }

  private initContext() {
    if (!this.ctx) {
      const AudioCtx = window.AudioContext || (window as any).webkitAudioContext;
      if (AudioCtx) {
        this.ctx = new AudioCtx();
      }
    }
    if (this.ctx && this.ctx.state === 'suspended') {
      this.ctx.resume();
    }
  }

  public setEnabled(enabled: boolean) {
    this.enabled = enabled;
    if (enabled) {
      this.initContext();
      this.startBackgroundMusic();
    } else {
      this.stopBackgroundMusic();
    }
  }

  // Play a soft pop transition sound (pitch sweep up)
  public playPop() {
    if (!this.enabled) return;
    try {
      this.initContext();
      if (!this.ctx) return;

      const osc = this.ctx.createOscillator();
      const gain = this.ctx.createGain();

      osc.connect(gain);
      gain.connect(this.ctx.destination);

      const now = this.ctx.currentTime;
      osc.type = 'sine';
      osc.frequency.setValueAtTime(150, now);
      osc.frequency.exponentialRampToValueAtTime(600, now + 0.08);

      gain.gain.setValueAtTime(0.3, now);
      gain.gain.exponentialRampToValueAtTime(0.01, now + 0.08);

      osc.start(now);
      osc.stop(now + 0.09);
    } catch (e) {
      console.warn('Audio pop sound failed to play', e);
    }
  }

  // Play a woodblock/clock ticking sound
  public playTick() {
    if (!this.enabled) return;
    try {
      this.initContext();
      if (!this.ctx) return;

      const osc = this.ctx.createOscillator();
      const gain = this.ctx.createGain();

      osc.connect(gain);
      gain.connect(this.ctx.destination);

      const now = this.ctx.currentTime;
      osc.type = 'triangle';
      osc.frequency.setValueAtTime(1000, now);

      gain.gain.setValueAtTime(0.15, now);
      gain.gain.exponentialRampToValueAtTime(0.001, now + 0.03);

      osc.start(now);
      osc.stop(now + 0.04);
    } catch (e) {
      console.warn('Audio tick sound failed to play', e);
    }
  }

  // Play a happy major chime sound (correct answer arpeggio: C5 -> E5 -> G5 -> C6)
  public playChime() {
    if (!this.enabled) return;
    try {
      this.initContext();
      if (!this.ctx) return;

      const now = this.ctx.currentTime;
      const notes = [523.25, 659.25, 783.99, 1046.50]; // C5, E5, G5, C6
      const delay = 0.07;

      notes.forEach((freq, idx) => {
        if (!this.ctx) return;
        const osc = this.ctx.createOscillator();
        const gain = this.ctx.createGain();

        osc.connect(gain);
        gain.connect(this.ctx.destination);

        osc.type = 'triangle';
        osc.frequency.setValueAtTime(freq, now + idx * delay);

        gain.gain.setValueAtTime(0.2, now + idx * delay);
        gain.gain.exponentialRampToValueAtTime(0.001, now + idx * delay + 0.25);

        osc.start(now + idx * delay);
        osc.stop(now + idx * delay + 0.26);
      });
    } catch (e) {
      console.warn('Audio chime sound failed to play', e);
    }
  }

  // Play applause and celebration sound (filtered white noise + frequency sweeps)
  public playApplause() {
    if (!this.enabled) return;
    try {
      this.initContext();
      if (!this.ctx) return;

      const now = this.ctx.currentTime;
      const duration = 2.5;

      // Create white noise buffer
      const bufferSize = this.ctx.sampleRate * duration;
      const buffer = this.ctx.createBuffer(1, bufferSize, this.ctx.sampleRate);
      const data = buffer.getChannelData(0);
      for (let i = 0; i < bufferSize; i++) {
        data[i] = Math.random() * 2 - 1;
      }

      // Noise source
      const noise = this.ctx.createBufferSource();
      noise.buffer = buffer;

      // Filter to make it sound like clapping hands / applause rather than raw static noise
      const filter = this.ctx.createBiquadFilter();
      filter.type = 'bandpass';
      filter.frequency.value = 1000;
      filter.Q.value = 2.0;

      // Volume envelope for the applause swell
      const gain = this.ctx.createGain();
      gain.gain.setValueAtTime(0.01, now);
      gain.gain.linearRampToValueAtTime(0.25, now + 0.4); // swell
      gain.gain.setValueAtTime(0.25, now + 1.8);
      gain.gain.exponentialRampToValueAtTime(0.01, now + duration); // fade out

      noise.connect(filter);
      filter.connect(gain);
      gain.connect(this.ctx.destination);

      noise.start(now);
      noise.stop(now + duration);

      // Add a couple of happy synth whistles on top of the noise
      const whistleCount = 4;
      for (let w = 0; w < whistleCount; w++) {
        const wOsc = this.ctx.createOscillator();
        const wGain = this.ctx.createGain();

        wOsc.connect(wGain);
        wGain.connect(this.ctx.destination);

        const wStart = now + 0.2 + w * 0.4;
        const wDur = 0.4;

        wOsc.type = 'sine';
        wOsc.frequency.setValueAtTime(800 + Math.random() * 400, wStart);
        wOsc.frequency.exponentialRampToValueAtTime(1500 + Math.random() * 500, wStart + wDur);

        wGain.gain.setValueAtTime(0.001, wStart);
        wGain.gain.linearRampToValueAtTime(0.05, wStart + 0.1);
        wGain.gain.exponentialRampToValueAtTime(0.001, wStart + wDur);

        wOsc.start(wStart);
        wOsc.stop(wStart + wDur + 0.05);
      }
    } catch (e) {
      console.warn('Audio applause sound failed to play', e);
    }
  }

  private musicTheme: 'cheerful' | 'calm' | 'retro' = 'cheerful';

  public getMusicTheme() {
    return this.musicTheme;
  }

  public setMusicTheme(theme: 'cheerful' | 'calm' | 'retro') {
    this.musicTheme = theme;
    if (this.isMusicPlaying) {
      this.stopBackgroundMusic();
      this.startBackgroundMusic();
    }
  }

  // Synthesize soft, happy, and slow background music
  public startBackgroundMusic() {
    if (!this.enabled || this.isMusicPlaying) return;
    this.initContext();
    if (!this.ctx) return;

    this.isMusicPlaying = true;
    this.musicStep = 0;

    let melody: number[] = [];
    let harmony: number[] = [];
    let noteDuration = 0.55;
    let stepTime = 600; // milliseconds between notes
    let mType: OscillatorType = 'sine';
    let hType: OscillatorType = 'triangle';
    let mVol = 0.02;
    let hVol = 0.015;

    if (this.musicTheme === 'calm') {
      // Very slow, dreamy arpeggio
      melody = [261.63, 329.63, 392.00, 523.25, 392.00, 329.63, 293.66, 329.63];
      harmony = [130.81, 130.81, 130.81, 130.81, 174.61, 174.61, 174.61, 174.61];
      noteDuration = 0.75;
      stepTime = 800;
      mType = 'sine';
      hType = 'sine'; // ultra soft
      mVol = 0.012;
      hVol = 0.008;
    } else if (this.musicTheme === 'retro') {
      // Fast, 8-bit cute game melody
      melody = [523.25, 587.33, 659.25, 523.25, 659.25, 523.25, 587.33, 392.00, 440.00, 493.88, 523.25, 523.25];
      harmony = [261.63, 261.63, 261.63, 261.63, 196.00, 196.00, 196.00, 196.00, 220.00, 220.00, 220.00, 220.00];
      noteDuration = 0.25;
      stepTime = 320;
      mType = 'square'; // chiptune feel
      hType = 'triangle';
      mVol = 0.005;
      hVol = 0.005;
    } else {
      // Cheerful (default)
      melody = [
        261.63, 329.63, 392.00, 440.00,
        392.00, 329.63, 293.66, 261.63,
        349.23, 440.00, 523.25, 440.00,
        392.00, 329.63, 293.66, 293.66,
      ];
      harmony = [
        130.81, 130.81, 130.81, 130.81,
        196.00, 196.00, 196.00, 196.00,
        110.00, 110.00, 110.00, 110.00,
        174.61, 174.61, 174.61, 174.61,
      ];
      noteDuration = 0.55;
      stepTime = 600;
      mType = 'sine';
      hType = 'triangle';
      mVol = 0.02;
      hVol = 0.015;
    }

    this.musicInterval = setInterval(() => {
      try {
        if (!this.enabled || !this.ctx) return;
        
        // Resume if suspended by browser
        if (this.ctx.state === 'suspended') {
          this.ctx.resume();
        }

        const now = this.ctx.currentTime;
        const currentMelodyFreq = melody[this.musicStep % melody.length];
        const currentHarmonyFreq = harmony[this.musicStep % harmony.length];

        // 1. Play melody
        const mOsc = this.ctx.createOscillator();
        const mGain = this.ctx.createGain();
        mOsc.connect(mGain);
        mGain.connect(this.ctx.destination);

        mOsc.type = mType;
        mOsc.frequency.setValueAtTime(currentMelodyFreq, now);

        mGain.gain.setValueAtTime(0.0001, now);
        mGain.gain.linearRampToValueAtTime(mVol, now + 0.08); // soft attack
        mGain.gain.exponentialRampToValueAtTime(0.0001, now + noteDuration);

        mOsc.start(now);
        mOsc.stop(now + noteDuration + 0.05);

        // 2. Play quiet bass harmony (on beat 0 and 2 of each 4-beat cycle)
        if (this.musicStep % 2 === 0) {
          const hOsc = this.ctx.createOscillator();
          const hGain = this.ctx.createGain();
          hOsc.connect(hGain);
          hGain.connect(this.ctx.destination);

          hOsc.type = hType;
          hOsc.frequency.setValueAtTime(currentHarmonyFreq, now);

          hGain.gain.setValueAtTime(0.0001, now);
          hGain.gain.linearRampToValueAtTime(hVol, now + 0.12);
          hGain.gain.exponentialRampToValueAtTime(0.0001, now + noteDuration * 1.8);

          hOsc.start(now);
          hOsc.stop(now + noteDuration * 1.9);
        }

        this.musicStep++;
      } catch (e) {
        console.warn('Music step failed to play', e);
      }
    }, stepTime);
  }

  public stopBackgroundMusic() {
    if (this.musicInterval) {
      clearInterval(this.musicInterval);
      this.musicInterval = null;
    }
    this.isMusicPlaying = false;
  }
}

export const soundManager = new SoundManager();
