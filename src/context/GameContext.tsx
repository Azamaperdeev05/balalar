'use client';

import React, { createContext, useContext, useState, useEffect, useRef } from 'react';
import { soundManager } from '../utils/SoundManager';
import confetti from 'canvas-confetti';

export type ScreenType = 'welcome' | 'games' | 'scoreboard';

export interface ProverbChallenge {
  scrambled: string[];
  correct: string;
}

export const CROCODILE_WORDS = [
  'Кітап', 'Қалам', 'Достық', 'Қазақстан', 'Домбыра',
  'Батыр', 'Күн', 'Ай', 'Ту', 'Қыран',
  'Бүркіт', 'Мектеп', 'Мұғалім', 'Оқушы', 'Ғарыш',
  'Компьютер', 'Телефон', 'Алма', 'Мысық', 'Ит',
  'Қарлығаш', 'Түлкі', 'Жылқы', 'Теңіз', 'Өзен',
  'Тау', 'Бұлт', 'Кемпірқосақ', 'Кітапхана', 'Арман'
];

export const WORD_SEARCH_TASKS = [
  '"Достық" сөзін тап.',
  'Кейіпкердің есімін тап.',
  'Бірінші тарауды тап.',
  'Соңғы тарауды тап.',
  '"Арман" сөзі кездесетін сөйлемді тап.',
  'Кітаптағы ең ұзын сөзді тап.',
  'Кітаптағы ең қысқа сөйлемді тап.',
  'Ең бірінші кездесетін адамның есімін тап.',
  'Екі рет кездесетін сөзді тап.',
  'Кітаптағы бірінші мақал-мәтелді тап.'
];

export const PROVERBS = [
  'Оқу – инемен құдық қазғандай.',
  'Кітап – білім бұлағы.',
  'Білім – таусылмас қазына.',
  'Бірлік бар жерде тірлік бар.',
  'Еңбек түбі – береке.',
  'Досы көпті жау алмайды.',
  'Өнер алды – қызыл тіл.'
];

// Helper to scramble a proverb into individual words
export const getScrambledProverb = (proverb: string): ProverbChallenge => {
  // Strip dots, dashes, commas to isolate clean words for reordering
  const cleanProverb = proverb.replace(/[.—]/g, '').trim();
  const words = cleanProverb.split(/\s+/).filter(Boolean);
  
  // Scramble the array
  const scrambled = [...words];
  let attempts = 0;
  while (attempts < 20) {
    for (let i = scrambled.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [scrambled[i], scrambled[j]] = [scrambled[j], scrambled[i]];
    }
    // Make sure scrambled version is actually different from the original
    if (scrambled.join(' ') !== words.join(' ')) break;
    attempts++;
  }

  return {
    scrambled,
    correct: cleanProverb
  };
};

interface GameContextProps {
  activeScreen: ScreenType;
  setActiveScreen: (screen: ScreenType) => void;
  activeGameIndex: number; // 0: Crocodile, 1: Word Search, 2: Proverbs
  setActiveGameIndex: (index: number) => void;
  
  // Scoreboard state
  scores: { team1: number; team2: number; team3: number };
  addScore: (team: 'team1' | 'team2' | 'team3', amount: number) => void;
  resetScores: () => void;
  
  // Timer state
  timeLeft: number;
  timerActive: boolean;
  startTimer: () => void;
  pauseTimer: () => void;
  resetTimer: () => void;
  
  // Game 1 state: Crocodile
  crocWord: string;
  isWordVisible: boolean;
  toggleWordVisibility: () => void;
  nextCrocWord: () => void;
  
  // Game 2 state: Word Search
  searchTask: string;
  nextSearchTask: () => void;
  
  // Game 3 state: Proverbs
  proverbChallenge: ProverbChallenge;
  proverbStatus: 'playing' | 'success';
  setProverbStatus: (status: 'playing' | 'success') => void;
  nextProverb: () => void;
  
  // Sound controls
  audioEnabled: boolean;
  setAudioEnabled: (enabled: boolean) => void;
  musicTheme: 'cheerful' | 'calm' | 'retro';
  setMusicTheme: (theme: 'cheerful' | 'calm' | 'retro') => void;
}

const GameContext = createContext<GameContextProps | undefined>(undefined);

export const GameProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [activeScreen, setActiveScreenState] = useState<ScreenType>('welcome');
  const [activeGameIndex, setActiveGameIndex] = useState<number>(0);
  
  // Scoreboard
  const [scores, setScores] = useState({ team1: 0, team2: 0, team3: 0 });
  
  // Timer
  const [timeLeft, setTimeLeft] = useState<number>(60);
  const [timerActive, setTimerActive] = useState<boolean>(false);
  const timerIntervalRef = useRef<NodeJS.Timeout | null>(null);
  
  // Game state trackers
  const [crocWord, setCrocWord] = useState<string>('');
  const [isWordVisible, setIsWordVisible] = useState<boolean>(false);
  
  const [searchTask, setSearchTask] = useState<string>('');
  
  const [proverbChallenge, setProverbChallenge] = useState<ProverbChallenge>({ scrambled: [], correct: '' });
  const [proverbStatus, setProverbStatus] = useState<'playing' | 'success'>('playing');
  const [proverbIndex, setProverbIndex] = useState<number>(0);
  
  // Audio state
  const [audioEnabled, setAudioEnabled] = useState<boolean>(true);
  const [musicTheme, setMusicThemeState] = useState<'cheerful' | 'calm' | 'retro'>('cheerful');

  // Trigger pop sound when navigation changes
  const setActiveScreen = (screen: ScreenType) => {
    soundManager.playPop();
    setActiveScreenState(screen);
  };

  // Sound initialization and activation on user interactions
  useEffect(() => {
    soundManager.setEnabled(audioEnabled);
  }, [audioEnabled]);

  useEffect(() => {
    soundManager.setMusicTheme(musicTheme);
  }, [musicTheme]);

  // Audio Context auto-unlock on mount or first click
  useEffect(() => {
    const handleUnlock = () => {
      soundManager.playPop();
      window.removeEventListener('click', handleUnlock);
    };
    window.addEventListener('click', handleUnlock);
    return () => window.removeEventListener('click', handleUnlock);
  }, []);

  // Set initial game states
  useEffect(() => {
    nextCrocWord();
    nextSearchTask();
    
    // Pick the first proverb challenge
    const challenge = getScrambledProverb(PROVERBS[0]);
    setProverbChallenge(challenge);
    setProverbIndex(0);
    setProverbStatus('playing');
  }, []);

  // Timer Tick implementation
  useEffect(() => {
    if (timerActive) {
      timerIntervalRef.current = setInterval(() => {
        setTimeLeft((prev) => {
          if (prev <= 1) {
            // Timer expired!
            clearInterval(timerIntervalRef.current!);
            setTimerActive(false);
            soundManager.playBell(); // Bell sound synthesized via AudioContext
            return 0;
          }
          // Tick sound on every second
          soundManager.playTick();
          return prev - 1;
        });
      }, 1000);
    } else {
      if (timerIntervalRef.current) {
        clearInterval(timerIntervalRef.current);
      }
    }

    return () => {
      if (timerIntervalRef.current) {
        clearInterval(timerIntervalRef.current);
      }
    };
  }, [timerActive]);

  // Score controller
  const addScore = (team: 'team1' | 'team2' | 'team3', amount: number) => {
    setScores((prev) => ({
      ...prev,
      [team]: prev[team] + amount
    }));
    // Play cheer/applause
    soundManager.playApplause();
    // Confetti effect
    confetti({
      particleCount: 100,
      spread: 70,
      origin: { y: 0.6 }
    });
  };

  const resetScores = () => {
    soundManager.playPop();
    setScores({ team1: 0, team2: 0, team3: 0 });
  };

  // Timer actions
  const startTimer = () => {
    soundManager.playPop();
    setTimerActive(true);
  };

  const pauseTimer = () => {
    soundManager.playPop();
    setTimerActive(false);
  };

  const resetTimer = () => {
    soundManager.playPop();
    setTimerActive(false);
    setTimeLeft(60);
  };

  // Crocodile Word Actions
  const toggleWordVisibility = () => {
    soundManager.playPop();
    setIsWordVisible((prev) => !prev);
  };

  const nextCrocWord = () => {
    soundManager.playPop();
    const randomIndex = Math.floor(Math.random() * CROCODILE_WORDS.length);
    setCrocWord(CROCODILE_WORDS[randomIndex]);
    setIsWordVisible(false);
    resetTimer();
  };

  // Word Search Actions
  const nextSearchTask = () => {
    soundManager.playPop();
    const randomIndex = Math.floor(Math.random() * WORD_SEARCH_TASKS.length);
    setSearchTask(WORD_SEARCH_TASKS[randomIndex]);
    resetTimer();
  };

  // Proverb Actions
  const nextProverb = () => {
    soundManager.playPop();
    const nextIdx = (proverbIndex + 1) % PROVERBS.length;
    setProverbIndex(nextIdx);
    const challenge = getScrambledProverb(PROVERBS[nextIdx]);
    setProverbChallenge(challenge);
    setProverbStatus('playing');
    resetTimer();
  };

  const setMusicTheme = (theme: 'cheerful' | 'calm' | 'retro') => {
    soundManager.playPop();
    setMusicThemeState(theme);
  };

  return (
    <GameContext.Provider
      value={{
        activeScreen,
        setActiveScreen,
        activeGameIndex,
        setActiveGameIndex: (idx) => {
          soundManager.playPop();
          setActiveGameIndex(idx);
          resetTimer();
        },
        scores,
        addScore,
        resetScores,
        timeLeft,
        timerActive,
        startTimer,
        pauseTimer,
        resetTimer,
        crocWord,
        isWordVisible,
        toggleWordVisibility,
        nextCrocWord,
        searchTask,
        nextSearchTask,
        proverbChallenge,
        proverbStatus,
        setProverbStatus,
        nextProverb,
        audioEnabled,
        setAudioEnabled,
        musicTheme,
        setMusicTheme
      }}
    >
      {children}
    </GameContext.Provider>
  );
};

export const useGame = () => {
  const context = useContext(GameContext);
  if (context === undefined) {
    throw new Error('useGame must be used within a GameProvider');
  }
  return context;
};
