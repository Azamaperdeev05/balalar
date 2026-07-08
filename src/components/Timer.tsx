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

  // Color logic according to specs: Green > 60s, Yellow > 20s, Red <= 20s
  let colorClass = 'stroke-[#22C55E] text-[#22C55E]';
  let glowClass = 'shadow-[0_0_20px_rgba(34,197,94,0.15)]';

  if (timeLeft <= 20) {
    colorClass = 'stroke-[#EF4444] text-[#EF4444] animate-pulse';
    glowClass = 'shadow-[0_0_25px_rgba(239,68,68,0.25)]';
  } else if (timeLeft <= 60) {
    colorClass = 'stroke-[#F59E0B] text-[#F59E0B]';
    glowClass = 'shadow-[0_0_20px_rgba(245,158,11,0.2)]';
  }

  // Circular progress calculations
  const radius = 60;
  const strokeWidth = 8;
  const circumference = 2 * Math.PI * radius;
  const strokeDashoffset = circumference * (1 - timeLeft / 120);

  return (
    <div className="flex flex-col items-center gap-5 bg-slate-800/80 backdrop-blur-xl border border-slate-700/50 p-6 rounded-3xl shadow-xl relative w-full max-w-[280px]">
      
      {/* Timer Circle */}
      <div className={`relative w-40 h-40 flex items-center justify-center rounded-full bg-slate-950/60 border border-slate-800 shadow-inner ${glowClass} transition-all duration-500`}>
        {/* SVG Progress Circle */}
        <svg className="w-full h-full transform -rotate-90" viewBox="0 0 140 140">
          {/* Background Circle */}
          <circle
            cx="70"
            cy="70"
            r={radius}
            className="stroke-slate-850"
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
          <span className={`text-5xl font-black font-display tracking-tighter text-white ${timeLeft <= 10 ? 'animate-bounce-gentle' : ''}`}>
            {timeLeft}
          </span>
          <span className="text-[10px] font-black font-display text-slate-500 uppercase tracking-widest -mt-1">
            СЕКУНД
          </span>
        </div>
      </div>

      {/* Timer Controls */}
      <div className="flex items-center gap-3 w-full">
        {timerActive ? (
          <button
            onClick={pauseTimer}
            className="flex-1 py-2.5 bg-amber-500 hover:bg-amber-400 border border-amber-500/50 text-slate-950 font-bold font-display text-sm tracking-wide rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-1.5"
            aria-label="Таймерді тоқтату"
          >
            <Pause className="w-4 h-4 fill-slate-950 stroke-[3px]" />
            <span>КҮТУ</span>
          </button>
        ) : (
          <button
            onClick={startTimer}
            disabled={timeLeft === 0}
            className={`flex-1 py-2.5 bg-emerald-600 hover:bg-emerald-500 border border-emerald-500/50 text-white font-bold font-display text-sm tracking-wide rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-1.5 ${
              timeLeft === 0 ? 'opacity-40 pointer-events-none shadow-none border-slate-800 bg-slate-900 text-slate-500' : ''
            }`}
            aria-label="Таймерді қосу"
          >
            <Play className="w-4 h-4 fill-white stroke-[3px]" />
            <span>БАСТАУ</span>
          </button>
        )}

        <button
          onClick={resetTimer}
          className="p-2.5 bg-rose-600 hover:bg-rose-500 border border-rose-500/50 text-white rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center"
          aria-label="Таймерді жаңарту"
          title="Таймерді қайта қосу"
        >
          <RotateCcw className="w-5 h-5 stroke-[3px]" />
        </button>
      </div>

    </div>
  );
};
