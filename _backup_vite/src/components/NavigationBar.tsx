import React from 'react';
import { ArrowLeft, ArrowRight, Play, Pause, RotateCcw, Settings } from 'lucide-react';
import { soundManager } from '../utils/SoundManager';

interface TopStatusBarProps {
  gameTitle: string;
  gameIndex: number; // 0 to 2
  totalGames: number;
  currentStep: number;
  totalSteps: number;
  timeLeft: number;
  timerActive: boolean;
  showTimer: boolean;
  animationsEnabled?: boolean;
}

export const TopStatusBar: React.FC<TopStatusBarProps> = ({
  gameTitle,
  gameIndex,
  totalGames,
  currentStep,
  totalSteps,
  timeLeft,
  showTimer,
  animationsEnabled = true,
}) => {
  // Calculate progress percentage
  const progressPercent = totalSteps > 0 ? (currentStep / totalSteps) * 100 : 0;
  
  // Timer color
  const isTimeLow = timeLeft <= 2;
  const timerColorClass = isTimeLow 
    ? 'bg-rose-500 text-white border-rose-700 animate-pulse' 
    : 'bg-amber-400 text-purple-950 border-amber-500';

  return (
    <div className="w-full bg-white/90 backdrop-blur-md border-b-8 border-purple-100 px-8 py-4 flex items-center justify-between gap-6 shadow-md relative z-30 select-none">
      {/* Game Title & Stage */}
      <div className="flex flex-col items-start gap-1">
        <span className="text-xl md:text-2xl font-bold text-purple-500 tracking-wide uppercase font-display">
          Ойын {gameIndex + 1} / {totalGames}
        </span>
        <h2 className="text-2xl md:text-4xl font-extrabold text-purple-950 truncate max-w-[300px] md:max-w-[450px] font-display">
          {gameTitle}
        </h2>
      </div>

      {/* Progress Bar */}
      <div className="flex-1 max-w-xl hidden md:flex flex-col items-center gap-2">
        <div className="flex justify-between w-full text-lg font-bold text-purple-800">
          <span>Прогресс</span>
          <span>{currentStep} / {totalSteps}</span>
        </div>
        <div className="w-full h-8 bg-purple-100 rounded-full border-4 border-purple-200 overflow-hidden p-1">
          <div 
            className={`h-full bg-gradient-to-r from-emerald-400 to-green-500 rounded-full transition-all duration-500 ${
              animationsEnabled ? 'animate-pulse-slow' : ''
            }`}
            style={{ width: `${progressPercent}%` }}
          />
        </div>
      </div>

      {/* Countdown Timer */}
      {showTimer ? (
        <div className="flex items-center gap-4">
          <div className="text-right hidden sm:block">
            <div className="text-sm font-bold text-purple-700 uppercase tracking-wider font-display">Қалған уақыт</div>
            <div className="text-xs text-purple-500">Авто-ауысу қосулы</div>
          </div>
          
          {/* Big Circular Countdown or Shield Countdown */}
          <div 
            className={`w-16 h-16 md:w-20 md:h-20 rounded-full border-4 flex items-center justify-center font-display font-black text-3xl md:text-4xl shadow-playful transition-all duration-300 ${timerColorClass}`}
            style={{
              transform: isTimeLow && animationsEnabled ? 'scale(1.1)' : 'scale(1)'
            }}
          >
            {timeLeft}
          </div>
        </div>
      ) : (
        <div className="w-16 h-16 md:w-20 md:h-20 opacity-0" /> // spacer
      )}
    </div>
  );
};

interface BottomControlsProps {
  onPrev: () => void;
  onNext: () => void;
  onTogglePlay: () => void;
  onRestart: () => void;
  onOpenSettings: () => void;
  isPlaying: boolean;
  hasPrev: boolean;
  hasNext: boolean;
  autoPlay: boolean;
  animationsEnabled?: boolean;
}

export const BottomControls: React.FC<BottomControlsProps> = ({
  onPrev,
  onNext,
  onTogglePlay,
  onRestart,
  onOpenSettings,
  isPlaying,
  hasPrev,
  hasNext,
  autoPlay,
  animationsEnabled = true,
}) => {
  const handleClick = (callback: () => void) => {
    soundManager.playPop();
    callback();
  };

  return (
    <div className="w-full bg-purple-900 border-t-8 border-purple-950 px-6 py-4 flex items-center justify-between gap-4 shadow-2xl relative z-30 select-none">
      {/* Settings & Restart Buttons */}
      <div className="flex items-center gap-3">
        <button
          onClick={() => handleClick(onOpenSettings)}
          className={`p-4 md:p-5 rounded-2xl bg-purple-800 border-b-4 border-purple-950 hover:bg-purple-700 active:translate-y-1 active:border-b-0 text-purple-200 transition-all shadow-md flex items-center justify-center`}
          title="Баптаулар"
        >
          <Settings className="w-8 h-8 md:w-9 md:h-9" />
        </button>

        <button
          onClick={() => handleClick(onRestart)}
          className={`p-4 md:p-5 rounded-2xl bg-amber-500 border-b-4 border-amber-700 hover:bg-amber-400 active:translate-y-1 active:border-b-0 text-purple-950 transition-all shadow-md flex items-center justify-center`}
          title="Қайта бастау"
        >
          <RotateCcw className="w-8 h-8 md:w-9 md:h-9" />
        </button>
      </div>

      {/* Play/Pause (Autoplay status toggle) */}
      <div className="flex items-center">
        {autoPlay && (
          <button
            onClick={() => handleClick(onTogglePlay)}
            className={`px-8 py-4 md:py-5 rounded-2xl ${
              isPlaying 
                ? 'bg-orange-500 border-orange-700 text-white hover:bg-orange-400' 
                : 'bg-emerald-500 border-emerald-700 text-white hover:bg-emerald-400'
            } border-b-4 active:translate-y-1 active:border-b-0 transition-all shadow-md flex items-center gap-3 font-display font-bold text-xl md:text-2xl`}
          >
            {isPlaying ? (
              <>
                <Pause className="w-7 h-7 fill-white" />
                <span>Тоқтату</span>
              </>
            ) : (
              <>
                <Play className="w-7 h-7 fill-white" />
                <span>Жалғастыру</span>
              </>
            )}
          </button>
        )}
      </div>

      {/* Navigation Buttons (Prev & Next) */}
      <div className="flex items-center gap-3">
        <button
          onClick={() => handleClick(onPrev)}
          disabled={!hasPrev}
          className={`px-6 py-4 md:px-8 md:py-5 rounded-2xl font-display font-extrabold text-xl md:text-2xl flex items-center gap-2 transition-all ${
            hasPrev
              ? 'bg-sky-500 border-b-4 border-sky-700 hover:bg-sky-400 text-white active:translate-y-1 active:border-b-0 shadow-md'
              : 'bg-purple-950/40 text-purple-700 border-transparent cursor-not-allowed opacity-40'
          }`}
        >
          <ArrowLeft className="w-7 h-7 stroke-[3px]" />
          <span className="hidden sm:inline">Артқа</span>
        </button>

        <button
          onClick={() => handleClick(onNext)}
          disabled={!hasNext}
          className={`px-6 py-4 md:px-8 md:py-5 rounded-2xl font-display font-extrabold text-xl md:text-2xl flex items-center gap-2 transition-all ${
            hasNext
              ? 'bg-yellow-400 border-b-4 border-yellow-600 hover:bg-yellow-300 text-purple-950 active:translate-y-1 active:border-b-0 shadow-md'
              : 'bg-purple-950/40 text-purple-700 border-transparent cursor-not-allowed opacity-40'
          } ${hasNext && animationsEnabled ? 'animate-bounce-gentle' : ''}`}
          style={{ animationDuration: '3s' }}
        >
          <span className="hidden sm:inline">Келесі</span>
          <ArrowRight className="w-7 h-7 stroke-[3px]" />
        </button>
      </div>
    </div>
  );
};
