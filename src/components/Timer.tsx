'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { Play, Pause, RotateCcw } from 'lucide-react';
import { motion } from 'framer-motion';

export const Timer: React.FC = () => {
  const { 
    timeLeft, 
    timerActive, 
    startTimer, 
    pauseTimer, 
    resetTimer 
  } = useGame();

  // Color logic
  let colorClass = 'stroke-emerald-500 text-emerald-500';
  let bgFillClass = 'bg-emerald-50';
  let glowClass = 'shadow-emerald-500/20';

  if (timeLeft <= 20) {
    colorClass = 'stroke-red-500 text-red-500 animate-pulse';
    bgFillClass = 'bg-red-50';
    glowClass = 'shadow-red-500/40';
  } else if (timeLeft <= 60) {
    colorClass = 'stroke-amber-400 text-amber-500';
    bgFillClass = 'bg-amber-50/55';
    glowClass = 'shadow-amber-500/20';
  }

  // Circular progress calculations
  const radius = 60;
  const strokeWidth = 10;
  const circumference = 2 * Math.PI * radius;
  const strokeDashoffset = circumference * (1 - timeLeft / 120);

  return (
    <div className="flex flex-col items-center gap-4 bg-white border-6 border-purple-950 p-6 rounded-[2.5rem] shadow-playful relative w-full max-w-[280px]">
      
      {/* Timer Circle */}
      <div className={`relative w-40 h-40 flex items-center justify-center rounded-full shadow-lg ${bgFillClass} ${glowClass} transition-colors duration-500`}>
        {/* SVG Progress Circle */}
        <svg className="w-full h-full transform -rotate-90" viewBox="0 0 140 140">
          {/* Background Circle */}
          <circle
            cx="70"
            cy="70"
            r={radius}
            className="stroke-purple-100"
            strokeWidth={strokeWidth}
            fill="transparent"
          />
          {/* Active Progress Circle */}
          <motion.circle
            cx="70"
            cy="70"
            r={radius}
            className={`${colorClass} transition-all duration-1000 ease-linear`}
            strokeWidth={strokeWidth}
            fill="transparent"
            strokeDasharray={circumference}
            animate={{ strokeDashoffset }}
            strokeLinecap="round"
          />
        </svg>

        {/* Time Text */}
        <div className="absolute flex flex-col items-center justify-center text-center">
          <span className={`text-5xl font-black font-display tracking-tighter ${timeLeft <= 10 ? 'animate-bounce-gentle' : ''}`}>
            {timeLeft}
          </span>
          <span className="text-[10px] font-black font-display text-purple-950/40 uppercase tracking-widest -mt-1">
            СЕКУНД
          </span>
        </div>
      </div>

      {/* Timer Controls */}
      <div className="flex items-center gap-3 w-full">
        {timerActive ? (
          <button
            onClick={pauseTimer}
            className="flex-1 py-2.5 bg-yellow-400 hover:bg-yellow-300 text-purple-950 font-black font-display text-sm tracking-wide rounded-xl border-3 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-1.5"
            aria-label="Таймерді тоқтату"
          >
            <Pause className="w-4 h-4 fill-purple-950 stroke-[3px]" />
            <span>КҮТУ</span>
          </button>
        ) : (
          <button
            onClick={startTimer}
            disabled={timeLeft === 0}
            className={`flex-1 py-2.5 bg-emerald-500 hover:bg-emerald-400 text-white font-black font-display text-sm tracking-wide rounded-xl border-3 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-1.5 ${
              timeLeft === 0 ? 'opacity-50 pointer-events-none shadow-none border-gray-400 bg-gray-300 text-gray-500' : ''
            }`}
            aria-label="Таймерді қосу"
          >
            <Play className="w-4 h-4 fill-white stroke-[3px]" />
            <span>БАСТАУ</span>
          </button>
        )}

        <button
          onClick={resetTimer}
          className="p-2.5 bg-rose-500 hover:bg-rose-400 text-white rounded-xl border-3 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center"
          aria-label="Таймерді жаңарту"
          title="Таймерді қайта қосу"
        >
          <RotateCcw className="w-5 h-5 stroke-[3px]" />
        </button>
      </div>

    </div>
  );
};
