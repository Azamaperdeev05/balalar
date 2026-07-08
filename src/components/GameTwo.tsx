import React from 'react';
import { useGame } from '../context/GameContext';
import { motion } from 'framer-motion';
import { BookOpen, SkipForward } from 'lucide-react';
import { Illustration, IllustrationType } from './Illustration';

export const GameTwo: React.FC = () => {
  const { searchTask, nextSearchTask } = useGame();

  // Helper to resolve the correct illustration for active search tasks
  const getIllustrationType = (task: string): IllustrationType => {
    const t = task.toLowerCase();
    if (t.includes('қызыл')) return 'red_search';
    if (t.includes('дөңгелек')) return 'round_search';
    if (t.includes('киім') || t.includes('әріп')) return 'letter_search';
    if (t.includes('саусақ')) return 'fingers_count';
    return 'book';
  };

  const illustrationType = getIllustrationType(searchTask);

  return (
    <div className="w-full max-w-4xl flex flex-col items-center gap-6 md:gap-8 p-4 z-10">
      
      <span className="text-sm md:text-base font-bold bg-indigo-600 border border-indigo-500 text-white px-5 py-2.5 rounded-full shadow-[0_0_15px_rgba(99,102,241,0.4)] tracking-wider uppercase font-display flex items-center gap-2 flex-shrink-0 rotate-[-1deg]">
        <BookOpen className="w-5 h-5 stroke-[2.5px]" />
        Сөзді тауып көр 📖
      </span>

      {/* Main Task Card */}
      <motion.div
        key={searchTask}
        initial={{ scale: 0.95, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 12 }}
        className="w-full bg-zinc-900/40 backdrop-blur-xl border border-zinc-800 text-white rounded-2xl p-6 md:p-10 flex flex-col items-center justify-center shadow-xl relative overflow-hidden"
      >
        {/* Rules explanation */}
        <p className="text-xs md:text-sm font-semibold text-zinc-400 max-w-xl text-center mb-6 leading-relaxed">
          Әр топқа бірдей кітап беріледі. Төмендегі тапсырманы кітап беттерінен немесе бөлмеден мүмкіндігінше ең бірінші болып тауып көрсеткен команда ұпай алады!
        </p>

        {/* Task display area */}
        <div className="w-full max-w-xl bg-zinc-950/60 border border-zinc-800/80 rounded-2xl p-6 md:p-8 flex flex-col items-center gap-6 mb-6 shadow-inner min-h-[220px] justify-center">
          {/* Custom Illustration */}
          <div className="w-28 h-28 md:w-36 md:h-36 bg-zinc-900 border border-zinc-800 rounded-xl flex items-center justify-center p-2.5 shadow-md">
            <Illustration type={illustrationType} className="w-full h-full object-contain filter grayscale brightness-125 group-hover:grayscale-0 transition-all duration-300" />
          </div>

          {/* Active task text */}
          <h2 className="text-xl md:text-2xl font-black font-display text-white text-center tracking-wide leading-snug">
            {searchTask}
          </h2>
        </div>

        {/* Action Controls */}
        <div className="w-full max-w-xs">
          <button
            onClick={nextSearchTask}
            className="w-full py-3 px-6 bg-indigo-600 hover:bg-indigo-500 border border-indigo-500/50 text-white font-bold font-display text-base rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2"
          >
            <SkipForward className="w-5 h-5 stroke-[2.5px]" />
            <span>КЕЛЕСІ ТАПСЫРМА</span>
          </button>
        </div>

      </motion.div>

    </div>
  );
};
