import { useState, useEffect, useCallback, useRef } from 'react';
import confetti from 'canvas-confetti';
import { motion, AnimatePresence } from 'framer-motion';

// Import components
import { TopStatusBar, BottomControls } from './components/NavigationBar';
import { SettingsModal } from './components/SettingsModal';
import { BackgroundAnimation } from './components/BackgroundAnimation';
import { soundManager } from './utils/SoundManager';
import { Illustration } from './components/Illustration';

// Screens
import IntroScreen from './components/screens/IntroScreen';
import GameOneScreen from './components/screens/GameOneScreen';
import GameTwoScreen from './components/screens/GameTwoScreen';
import GameThreeScreen from './components/screens/GameThreeScreen';
import FinishScreen from './components/screens/FinishScreen';

// Data
import {
  games,
  game1Actions,
  game2Questions,
  game3Challenges
} from './data';
import type { Game } from './data';

interface Settings {
  timerDuration: number;
  autoPlay: boolean;
  soundEnabled: boolean;
  animationsEnabled: boolean;
  musicTheme: 'cheerful' | 'calm' | 'retro';
}

function App() {
  // Navigation Screens State: 'welcome' | 'game' | 'ending'
  const [activeScreen, setActiveScreen] = useState<'welcome' | 'game' | 'ending'>('welcome');
  
  // Game states
  const [gameIndex, setGameIndex] = useState<number>(0);
  // stepIndex: -1 (Intro Screen), 0..totalSteps-1 (Steps), totalSteps (Completed Outro Screen)
  const [stepIndex, setStepIndex] = useState<number>(-1);
  
  // Settings
  const [settings, setSettings] = useState<Settings>({
    timerDuration: 5,
    autoPlay: true,
    soundEnabled: true,
    animationsEnabled: true,
    musicTheme: 'cheerful',
  });
  const [isSettingsOpen, setIsSettingsOpen] = useState<boolean>(false);
  const [isFullscreen, setIsFullscreen] = useState<boolean>(false);

  // Timer states
  const [timeLeft, setTimeLeft] = useState<number>(5);
  const [timerActive, setTimerActive] = useState<boolean>(false);
  const [showingResult, setShowingResult] = useState<boolean>(false);

  // Refs for tracking timer
  const timerIntervalRef = useRef<ReturnType<typeof setInterval> | null>(null);

  // Active game info
  const currentGame: Game = games[gameIndex];
  
  // Steps count for each game
  const getStepsCount = (idx: number): number => {
    if (idx === 0) return game1Actions.length;
    if (idx === 1) return game2Questions.length;
    if (idx === 2) return game3Challenges.length;
    return 0;
  };

  const totalSteps = getStepsCount(gameIndex);

  // Sync sound manager enabled state
  useEffect(() => {
    soundManager.setEnabled(settings.soundEnabled);
  }, [settings.soundEnabled]);

  // Fullscreen event listener
  useEffect(() => {
    const handleFullscreenChange = () => {
      setIsFullscreen(!!document.fullscreenElement);
    };
    document.addEventListener('fullscreenchange', handleFullscreenChange);
    return () => document.removeEventListener('fullscreenchange', handleFullscreenChange);
  }, []);

  // Fullscreen Toggler
  const toggleFullscreen = useCallback(() => {
    if (!document.fullscreenElement) {
      document.documentElement.requestFullscreen().catch((err) => {
        console.error(`Error attempting to enable fullscreen: ${err.message}`);
      });
    } else {
      document.exitFullscreen();
    }
  }, []);

  // Helper to trigger Confetti on Game complete / Outro
  const triggerConfetti = () => {
    if (!settings.animationsEnabled) return;
    confetti({
      particleCount: 150,
      spread: 80,
      origin: { y: 0.6 },
      colors: ['#facc15', '#f97316', '#3b82f6', '#10b981', '#a855f7'],
    });
  };

  // State Navigation Logic: NEXT
  const handleNext = useCallback(() => {
    if (settings.soundEnabled) {
      soundManager.startBackgroundMusic();
    }
    setTimerActive(false);

    if (activeScreen === 'welcome') {
      soundManager.playPop();
      setActiveScreen('game');
      setGameIndex(0);
      setStepIndex(-1); // Game 1 Intro
      return;
    }

    if (activeScreen === 'game') {
      // If we are in Game 2 (Book search) or Game 3 (Proverb assembly) and not showing result yet, reveal result first!
      if ((gameIndex === 1 || gameIndex === 2) && stepIndex >= 0 && stepIndex < totalSteps && !showingResult) {
        setShowingResult(true);
        soundManager.playChime();
        if (settings.autoPlay) {
          setTimeLeft(5); // 5 seconds for action outcome
          setTimerActive(true);
        }
        return;
      }

      // If we are at the last step, go to completed outro screen
      if (stepIndex < totalSteps - 1) {
        setStepIndex((prev) => prev + 1);
        setShowingResult(false);
        setTimeLeft(settings.timerDuration);
        setTimerActive(true);
      } else if (stepIndex === totalSteps - 1) {
        // Transitioning to Outro screen
        setStepIndex(totalSteps);
        setShowingResult(false);
        setTimerActive(false);
        triggerConfetti();
        soundManager.playChime();
      } else {
        // We are on the completed outro screen, advance to next game or ending
        if (gameIndex < 2) {
          setGameIndex((prev) => prev + 1);
          setStepIndex(-1); // Next Game Intro
          setShowingResult(false);
        } else {
          // Finish Screen
          setActiveScreen('ending');
          setTimerActive(false);
        }
      }
      return;
    }

    if (activeScreen === 'ending') {
      setActiveScreen('welcome');
    }
  }, [activeScreen, gameIndex, stepIndex, totalSteps, showingResult, settings]);

  // State Navigation Logic: PREVIOUS
  const handlePrev = useCallback(() => {
    setTimerActive(false);

    if (activeScreen === 'welcome') return;

    if (activeScreen === 'ending') {
      setActiveScreen('game');
      setGameIndex(2);
      setStepIndex(getStepsCount(2)); // Game 3 completed screen
      setShowingResult(false);
      return;
    }

    if (activeScreen === 'game') {
      // If showing result, go back to question
      if (showingResult) {
        setShowingResult(false);
        setTimeLeft(settings.timerDuration);
        setTimerActive(true);
        return;
      }

      if (stepIndex > 0) {
        setStepIndex((prev) => prev - 1);
        // If we go back into a question in Game 2, don't show the result immediately
        setShowingResult(false);
        setTimeLeft(settings.timerDuration);
        setTimerActive(true);
      } else if (stepIndex === 0) {
        // Go back to game intro screen
        setStepIndex(-1);
        setTimerActive(false);
      } else if (stepIndex === -1) {
        // Go back to previous gamecompleted outro, or welcome screen
        if (gameIndex > 0) {
          const prevIdx = gameIndex - 1;
          setGameIndex(prevIdx);
          setStepIndex(getStepsCount(prevIdx)); // Outro of previous game
          setShowingResult(false);
        } else {
          // Go back to Welcome screen
          setActiveScreen('welcome');
        }
      } else if (stepIndex === totalSteps) {
        // We are on completed outro screen, go back to last step
        setStepIndex(totalSteps - 1);
        setShowingResult(false);
        setTimeLeft(settings.timerDuration);
        setTimerActive(true);
      }
    }
  }, [activeScreen, gameIndex, stepIndex, totalSteps, showingResult, settings]);

  // Restart current game / screen
  const handleRestart = useCallback(() => {
    setTimerActive(false);
    setShowingResult(false);
    if (activeScreen === 'game') {
      setStepIndex(-1); // Go to current game intro
    } else if (activeScreen === 'ending') {
      setActiveScreen('game');
      setGameIndex(0);
      setStepIndex(-1);
    } else {
      setActiveScreen('welcome');
    }
  }, [activeScreen]);

  // Toggle AutoPlay timer pause state
  const handleTogglePlay = useCallback(() => {
    // Only play/pause during active game steps
    if (activeScreen === 'game' && stepIndex >= 0 && stepIndex < totalSteps) {
      setTimerActive((prev) => !prev);
    }
  }, [activeScreen, stepIndex, totalSteps]);

  // Global Timer Tick logic
  useEffect(() => {
    if (timerActive && timeLeft > 0) {
      timerIntervalRef.current = setInterval(() => {
        setTimeLeft((prev) => {
          const nextVal = prev - 1;
          if (nextVal > 0) {
            soundManager.playTick(); // Tick sound on each second
          }
          return nextVal;
        });
      }, 1000);
    } else if (timeLeft === 0) {
      // Timer finished!
      if (gameIndex === 1 || gameIndex === 2) {
        // Game 2 (Book search) or Game 3 (Proverb assembly)
        if (!showingResult) {
          // Timer finished on task countdown -> reveal result/answer
          setShowingResult(true);
          soundManager.playChime();
          if (settings.autoPlay) {
            setTimeLeft(5); // 5 seconds of result display
            setTimerActive(true);
          } else {
            setTimerActive(false);
          }
        } else {
          // Timer finished on result display -> transition to next question
          handleNext();
        }
      } else {
        // Game 1 (Crocodile drawing)
        if (settings.autoPlay) {
          handleNext();
        } else {
          setTimerActive(false); // Stop and wait for host
        }
      }
    }

    return () => {
      if (timerIntervalRef.current) {
        clearInterval(timerIntervalRef.current);
      }
    };
  }, [timerActive, timeLeft, gameIndex, showingResult, settings.autoPlay, handleNext]);

  // Initialize timer when entering a step
  useEffect(() => {
    // Set timer active ONLY when we enter active game play slides (stepIndex between 0 and N-1)
    if (activeScreen === 'game' && stepIndex >= 0 && stepIndex < totalSteps) {
      setTimeLeft(settings.timerDuration);
      setTimerActive(true);
      setShowingResult(false);
    } else {
      setTimerActive(false);
    }
  }, [activeScreen, gameIndex, stepIndex, totalSteps, settings.timerDuration]);

  // Keyboard Event Handlers
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      // Don't trigger shortcuts if user is typing in settings (though there are no text inputs)
      if (isSettingsOpen) return;

      switch (e.key) {
        case 'ArrowRight':
          e.preventDefault();
          handleNext();
          break;
        case 'ArrowLeft':
          e.preventDefault();
          handlePrev();
          break;
        case ' ': // Spacebar
          e.preventDefault();
          handleTogglePlay();
          break;
        case 'f':
        case 'F':
          e.preventDefault();
          toggleFullscreen();
          break;
        default:
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [handleNext, handlePrev, handleTogglePlay, toggleFullscreen, isSettingsOpen]);

  // Active step rendering helpers
  const renderGameContent = () => {
    if (stepIndex === -1) {
      // Game Intro Splash Screen
      return (
        <IntroScreen
          title={currentGame.title}
          description={currentGame.description}
          illustrationType={currentGame.illustrationType}
          onStart={handleNext}
          animationsEnabled={settings.animationsEnabled}
        />
      );
    }

    if (stepIndex === totalSteps) {
      // Game completed intermediate Outro Screen
      const gameEndTitles = ['КЕРЕМЕТ! 👏', 'ТАМАША! 🎉', 'ҒАЖАП! 🌟'];
      const gameEndTitle = gameEndTitles[gameIndex];
      const nextBtnLabel = gameIndex < 2 ? 'Келесі ойын ➡️' : 'Аяқтау 🏆';
      
      return (
        <div className="w-full h-full flex flex-col items-center justify-center text-center p-4 md:p-8 bg-grid-playful select-none overflow-hidden">
          <div className="max-w-3xl flex flex-col items-center gap-4 bg-white border-8 border-purple-600 rounded-[3rem] p-6 md:p-10 shadow-playful relative max-h-[85vh] overflow-hidden w-full">
            <motion.h1
              initial={settings.animationsEnabled ? { scale: 0.6, rotate: -10 } : {}}
              animate={settings.animationsEnabled ? { scale: 1, rotate: 0 } : {}}
              className="text-4xl md:text-6xl lg:text-7xl font-bold font-display tracking-wide text-stroke-playful leading-tight mb-1"
            >
              {gameEndTitle}
            </motion.h1>

            <div className="w-36 h-36 md:w-52 md:h-52 bg-purple-50 rounded-[2rem] border-2 border-purple-100 flex items-center justify-center p-3 flex-shrink max-h-[22vh] max-w-[22vh] aspect-square">
              <Illustration type="finish" className="w-full h-full" />
            </div>

            <p className="text-lg md:text-xl lg:text-2xl font-bold text-purple-800 leading-snug max-w-2xl font-sans">
              Ойын аяқталды! Сендер өте жылдам әрі көңілді ойнадыңдар!
            </p>

            <button
              onClick={() => {
                soundManager.playPop();
                handleNext();
              }}
              className="px-10 py-4 md:px-12 md:py-5 rounded-[1.8rem] bg-gradient-to-r from-yellow-400 to-amber-500 hover:from-yellow-300 hover:to-amber-400 text-purple-950 font-display font-bold text-xl md:text-2xl tracking-wide shadow-playful border-4 border-purple-950 cursor-pointer hover:scale-105 active:scale-95 transition-all animate-bounce-gentle flex-shrink-0"
            >
              {nextBtnLabel}
            </button>
          </div>
        </div>
      );
    }

    // Active playing states
    if (gameIndex === 0) {
      // Game 1
      return (
        <GameOneScreen
          action={game1Actions[stepIndex]}
          timeLeft={timeLeft}
          timerActive={timerActive}
          animationsEnabled={settings.animationsEnabled}
        />
      );
    } else if (gameIndex === 1) {
      // Game 2
      return (
        <GameTwoScreen
          questionObj={game2Questions[stepIndex]}
          timeLeft={timeLeft}
          timerActive={timerActive}
          showingResult={showingResult}
          animationsEnabled={settings.animationsEnabled}
        />
      );
    } else if (gameIndex === 2) {
      // Game 3
      return (
        <GameThreeScreen
          challenge={game3Challenges[stepIndex]}
          timeLeft={timeLeft}
          timerActive={timerActive}
          showingResult={showingResult}
          animationsEnabled={settings.animationsEnabled}
        />
      );
    }

    return null;
  };

  return (
    <div className="w-full h-full flex flex-col justify-between relative overflow-hidden select-none bg-[#fefcf0]">
      
      {/* Playful Floating Background */}
      <BackgroundAnimation enabled={settings.animationsEnabled} />

      {/* Top Status Bar (Only visible during game screen phase) */}
      {activeScreen === 'game' && stepIndex >= 0 && stepIndex < totalSteps ? (
        <TopStatusBar
          gameTitle={currentGame.title}
          gameIndex={gameIndex}
          totalGames={games.length}
          currentStep={stepIndex + 1}
          totalSteps={totalSteps}
          timeLeft={timeLeft}
          timerActive={timerActive}
          showTimer={timerActive || ((gameIndex === 1 || gameIndex === 2) && showingResult)}
          animationsEnabled={settings.animationsEnabled}
        />
      ) : null}

      {/* Main Interactive Presentation Center Frame */}
      <div className="flex-1 w-full relative flex items-center justify-center overflow-hidden">
        <AnimatePresence mode="wait">
          {activeScreen === 'welcome' && (
            <motion.div
              key="app-welcome"
              initial={settings.animationsEnabled ? { opacity: 0, scale: 0.95 } : {}}
              animate={settings.animationsEnabled ? { opacity: 1, scale: 1 } : {}}
              exit={settings.animationsEnabled ? { opacity: 0, scale: 0.95 } : {}}
              className="w-full h-full flex flex-col items-center justify-center text-center p-6 md:p-12 select-none"
            >
              <div className="max-w-4xl flex flex-col items-center gap-4 bg-white/70 border-8 border-purple-500 rounded-[3rem] p-6 md:p-10 shadow-2xl relative max-h-[85vh] overflow-hidden">
                <span className="text-xl md:text-2xl font-black bg-purple-600 border-4 border-white text-white px-6 py-2 rounded-full shadow-md rotate-[-3deg] font-display flex-shrink-0">
                  КӨҢІЛДІ КЕШ 🥳
                </span>
                
                <h1 className="text-4xl md:text-6xl lg:text-7xl font-black font-display tracking-wide text-stroke-playful leading-tight">
                  Балалар Ойындары
                </h1>
                
                <div className="w-36 h-36 md:w-52 md:h-52 bg-purple-100/40 rounded-[2rem] border-2 border-purple-200 flex items-center justify-center p-3 flex-shrink max-h-[22vh] max-w-[22vh] aspect-square">
                  <Illustration type="celebration" className="w-full h-full" />
                </div>

                <p className="text-lg md:text-xl lg:text-2xl font-extrabold text-purple-800 leading-snug max-w-2xl font-sans">
                  Презентациялық ойындар жинағы. Балаларды экран алдында белсенді қозғалуға шақырыңыз!
                </p>

                <button
                  onClick={handleNext}
                  className="px-10 py-4 md:px-12 md:py-5 rounded-[1.8rem] bg-gradient-to-r from-orange-400 to-amber-500 hover:from-orange-300 hover:to-amber-400 text-white font-display font-black text-2xl md:text-3xl tracking-wide shadow-playful hover:scale-105 active:scale-95 transition-all border-4 border-purple-950 cursor-pointer animate-bounce-gentle flex-shrink-0"
                  style={{ animationDuration: '2s' }}
                >
                  БАСТАЙЫҚ 🚀
                </button>
              </div>
            </motion.div>
          )}

          {activeScreen === 'game' && (
            <motion.div
              key={`game-container-${gameIndex}-${stepIndex}`}
              initial={settings.animationsEnabled ? { opacity: 0, x: 100 } : {}}
              animate={settings.animationsEnabled ? { opacity: 1, x: 0 } : {}}
              exit={settings.animationsEnabled ? { opacity: 0, x: -100 } : {}}
              transition={{ type: 'spring', stiffness: 120, damping: 18 }}
              className="w-full h-full flex items-center justify-center overflow-hidden"
            >
              {renderGameContent()}
            </motion.div>
          )}

          {activeScreen === 'ending' && (
            <motion.div
              key="app-ending"
              initial={settings.animationsEnabled ? { opacity: 0 } : {}}
              animate={settings.animationsEnabled ? { opacity: 1 } : {}}
              exit={settings.animationsEnabled ? { opacity: 0 } : {}}
              className="w-full h-full"
            >
              <FinishScreen
                onRestart={handleRestart}
                animationsEnabled={settings.animationsEnabled}
              />
            </motion.div>
          )}
        </AnimatePresence>
      </div>

      {/* Bottom Navigation Controls (Visible on all screens with states) */}
      <BottomControls
        onPrev={handlePrev}
        onNext={handleNext}
        onTogglePlay={handleTogglePlay}
        onRestart={handleRestart}
        onOpenSettings={() => setIsSettingsOpen(true)}
        isPlaying={timerActive}
        hasPrev={activeScreen !== 'welcome'}
        hasNext={activeScreen !== 'ending'}
        autoPlay={activeScreen === 'game' && stepIndex >= 0 && stepIndex < totalSteps}
        animationsEnabled={settings.animationsEnabled}
      />

      {/* Settings Modal overlay */}
      <SettingsModal
        isOpen={isSettingsOpen}
        onClose={() => setIsSettingsOpen(false)}
        settings={settings}
        onUpdateSettings={(newSettings) => setSettings((prev) => ({ ...prev, ...newSettings }))}
        isFullscreen={isFullscreen}
        onToggleFullscreen={toggleFullscreen}
      />

    </div>
  );
}

export default App;
