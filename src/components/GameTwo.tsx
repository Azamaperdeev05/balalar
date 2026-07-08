'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { motion, AnimatePresence } from 'framer-motion';
import { BookOpen, SkipForward } from 'lucide-react';
import { Illustration } from './Illustration';

export const GameTwo: React.FC = () => {
  const { searchTask, nextSearchTask } = useGame();

  return (
    <div className="w-full max-w-4xl flex flex-col items-center gap-6 md:gap-8 p-4 z-10">
      
      {/* Game Badge */}
      <span className="text-lg md:text-xl font-bold bg-white/20 px-6 py-2.5 rounded-full border-2 border-white/40 tracking-widest uppercase font-display flex items-center gap-2 flex-shrink-0 text-white shadow-sm">
        <BookOpen className="w-5 h-5 stroke-[3px]" />
        Сөзді тауып көр 📖
      </span>

      {/* Main Task Card */}
      <motion.div
        key={searchTask}
        initial={{ scale: 0.95, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 12 }}
        className="w-full bg-white border-8 border-purple-950 text-purple-950 rounded-[3rem] p-6 md:p-10 flex flex-col items-center justify-center shadow-playful relative overflow-hidden"
      >
        {/* Rules explanation */}
        <p className="text-sm md:text-base font-bold text-purple-900/60 max-w-xl text-center mb-6 leading-relaxed">
          Әр топқа бірдей кітап беріледі. Төмендегі тапсырманы кітап беттерінен мүмкіндігінше ең бірінші болып тауып көрсеткен команда ұпай алады!
        </p>

        {/* Task display area */}
        <div className="w-full max-w-xl bg-purple-50 border-4 border-purple-950/20 rounded-[2rem] p-6 md:p-8 flex flex-col items-center gap-6 mb-6 shadow-inner min-h-[220px] justify-center">
          {/* Custom Illustration */}
          <div className="w-28 h-28 md:w-36 md:h-36 bg-white border-4 border-purple-950/15 rounded-2xl flex items-center justify-center p-2 shadow-sm">
            <Illustration type="book" className="w-full h-full object-contain" />
          </div>

          {/* Active task text */}
          <h2 className="text-2xl md:text-4xl font-black font-display text-purple-950 text-center tracking-wide leading-snug">
            {searchTask}
          </h2>
        </div>

        {/* Action Controls */}
        <div className="w-full max-w-xs">
          <button
            onClick={nextSearchTask}
            className="w-full py-4 px-6 bg-emerald-500 hover:bg-emerald-400 text-white font-black font-display text-lg md:text-xl rounded-2xl border-4 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2"
          >
            <SkipForward className="w-6 h-6 stroke-[3px]" />
            <span>КЕЛЕСІ ТАПСЫРМА</span>
          </button>
        </div>

      </motion.div>

    </div>
  );
};
