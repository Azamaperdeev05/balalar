'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { motion, AnimatePresence } from 'framer-motion';
import { Palette, Eye, EyeOff, SkipForward } from 'lucide-react';

export const GameOne: React.FC = () => {
  const { crocWord, isWordVisible, toggleWordVisibility, nextCrocWord } = useGame();

  // Handcrafted simple, clean B&W vector line-art outlines for all 30 words
  const renderCrocIllustration = (word: string) => {
    const w = word.trim().toLowerCase();
    
    switch (w) {
      case 'кітап':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M4 19.5V4.5A2.5 2.5 0 0 1 6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5Z" />
            <path d="M6 6h10" />
            <path d="M6 10h10" />
          </svg>
        );
      case 'қалам':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M12 20h9" />
            <path d="M16.5 3.5a2.12 2.12 0 0 1 3 3L7 19l-4 1 1-4Z" />
          </svg>
        );
      case 'достық':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M19 14c1.49-1.46 3-3.21 3-5.5A5.5 5.5 0 0 0 16.5 3c-1.76 0-3 .5-4.5 2-1.5-1.5-2.74-2-4.5-2A5.5 5.5 0 0 0 2 8.5c0 2.3 1.5 4.05 3 5.5l7 7Z" />
          </svg>
        );
      case 'қазақстан':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M3 12h18" />
            <path d="M12 3v18" />
            <circle cx="12" cy="12" r="6" />
          </svg>
        );
      case 'домбыра':
        return (
          <svg viewBox="0 0 100 100" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M50,70 C38,70 34,88 50,88 C66,88 62,70 50,70 Z" />
            <line x1="50" y1="70" x2="50" y2="20" />
            <path d="M48,20 L52,20 L52,12 L48,12 Z" />
            <circle cx="50" cy="79" r="2.5" fill="currentColor" />
          </svg>
        );
      case 'батыр':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10Z" />
          </svg>
        );
      case 'күн':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <circle cx="12" cy="12" r="4" />
            <path d="M12 2v2" />
            <path d="M12 20v2" />
            <path d="m4.93 4.93 1.41 1.41" />
            <path d="m17.66 17.66 1.41 1.41" />
            <path d="M2 12h2" />
            <path d="M20 12h2" />
            <path d="m6.34 17.66-1.41 1.41" />
            <path d="m19.07 4.93-1.41 1.41" />
          </svg>
        );
      case 'ай':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M12 3a6 6 0 0 0 9 9 9 9 0 1 1-9-9Z" />
          </svg>
        );
      case 'ту':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M4 15s1-1 4-1 5 2 8 2 4-1 4-1V3s-1 1-4 1-5-2-8-2-4 1-4 1z" />
            <line x1="4" y1="22" x2="4" y2="15" />
          </svg>
        );
      case 'қыран':
      case 'бүркіт':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M3 10c8 3 10 3 18 0-6 4-7 5-9 9-2-4-3-5-9-9Z" />
            <path d="M12 4v6" />
          </svg>
        );
      case 'мектеп':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z" />
            <polyline points="9 22 9 12 15 12 15 22" />
          </svg>
        );
      case 'мұғалім':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M22 10v6M2 10l10-5 10 5-10 5z" />
            <path d="M6 12v5c0 2 2 3 6 3s6-1 6-3v-5" />
          </svg>
        );
      case 'оқушы':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2" />
            <circle cx="12" cy="7" r="4" />
          </svg>
        );
      case 'ғарыш':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M4.5 16.5c-1.5 1.5-2.5 3.5-2.5 5.5C4 22 6 21 7.5 19.5" />
            <path d="m12 12 9-9" />
            <path d="M18 3h3v3" />
            <circle cx="12" cy="12" r="8" />
          </svg>
        );
      case 'компьютер':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <rect width="20" height="14" x="2" y="3" rx="2" />
            <line x1="8" y1="21" x2="16" y2="21" />
            <line x1="12" y1="17" x2="12" y2="21" />
          </svg>
        );
      case 'телефон':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <rect width="14" height="20" x="5" y="2" rx="2" ry="2" />
            <line x1="12" y1="18" x2="12.01" y2="18" />
          </svg>
        );
      case 'алма':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M12 22c5.523 0 10-4.477 10-10S17.523 2 12 2 2 6.477 2 12s4.477 10 10 10Z" />
            <path d="M12 6c0-2 2-3 2-3" />
          </svg>
        );
      case 'мысық':
        return (
          <svg viewBox="0 0 100 100" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round">
            <circle cx="50" cy="55" r="22" />
            <path d="M32,35 L40,46" />
            <path d="M68,35 L60,46" />
            <circle cx="43" cy="50" r="2" fill="currentColor" />
            <circle cx="57" cy="50" r="2" fill="currentColor" />
            <path d="M46,58 Q50,62 54,58" />
          </svg>
        );
      case 'ит':
        return (
          <svg viewBox="0 0 100 100" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round">
            <circle cx="50" cy="55" r="22" />
            <path d="M28,45 Q18,65 28,60" />
            <path d="M72,45 Q82,65 72,60" />
            <circle cx="43" cy="50" r="2" fill="currentColor" />
            <circle cx="57" cy="50" r="2" fill="currentColor" />
            <polygon points="48,58 52,58 50,62" fill="currentColor" />
          </svg>
        );
      case 'қарлығаш':
        return (
          <svg viewBox="0 0 100 100" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round">
            <path d="M15,50 C30,45 45,35 50,20 C55,35 70,45 85,50 C65,55 55,65 50,85 C45,65 35,55 15,50 Z" />
          </svg>
        );
      case 'түлкі':
        return (
          <svg viewBox="0 0 100 100" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round">
            <polygon points="50,80 20,30 80,30" />
            <polygon points="20,30 30,10 42,30" fill="currentColor" />
            <polygon points="80,30 70,10 58,30" fill="currentColor" />
          </svg>
        );
      case 'жылқы':
        return (
          <svg viewBox="0 0 100 100" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round">
            <path d="M25,75 C25,50 35,30 50,30 C65,30 75,45 75,55 L75,75" />
            <path d="M50,30 L60,15 L62,30" />
          </svg>
        );
      case 'теңіз':
      case 'өзен':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M2 6c.6.5 1.2 1 2.5 1 2.5 0 2.5-2 5-2 2.5 0 2.5 2 5 2 2.5 0 2.5-2 5-2 1.3 0 1.9.5 2.5 1" />
            <path d="M2 12c.6.5 1.2 1 2.5 1 2.5 0 2.5-2 5-2 2.5 0 2.5 2 5 2 2.5 0 2.5-2 5-2 1.3 0 1.9.5 2.5 1" />
            <path d="M2 18c.6.5 1.2 1 2.5 1 2.5 0 2.5-2 5-2 2.5 0 2.5 2 5 2 2.5 0 2.5-2 5-2 1.3 0 1.9.5 2.5 1" />
          </svg>
        );
      case 'тау':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="m8 3 4 8 5-5 5 15H2L8 3z" />
          </svg>
        );
      case 'бұлт':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M17.5 19A3.5 3.5 0 0 0 21 15.5c0-2.79-2.54-4.5-5-4.5-.42-1.93-2.04-3.5-4.17-3.5-3 0-5.3 2.4-5.3 5.5a5.5 5.5 0 0 0 5.3 5.5Z" />
          </svg>
        );
      case 'кемпірқосақ':
        return (
          <svg viewBox="0 0 100 100" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round">
            <path d="M20,80 A30,30 0 0,1 80,80" />
            <path d="M30,80 A20,20 0 0,1 70,80" />
            <path d="M40,80 A10,10 0 0,1 60,80" />
          </svg>
        );
      case 'кітапхана':
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <rect x="3" y="3" width="18" height="18" rx="2" />
            <path d="M9 3v18" />
            <path d="M15 3v18" />
            <path d="M3 9h18" />
            <path d="M3 15h18" />
          </svg>
        );
      case 'арман':
      default:
        return (
          <svg viewBox="0 0 24 24" className="w-16 h-16 md:w-20 md:h-20 text-slate-300" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
            <path d="M12 3v1m0 16v1m9-9h-1M4 12H3m15.364-6.364-1.414 1.414M6.05 17.95l-1.414 1.414m12.728 0-1.414-1.414M6.05 6.05 4.636 4.636M12 7a5 5 0 1 0 0 10 5 5 0 0 0 0-10Z" />
          </svg>
        );
    }
  };

  return (
    <div className="w-full max-w-4xl flex flex-col items-center gap-6 md:gap-8 p-4 z-10 select-none">
      
      <span className="text-xs md:text-sm font-semibold bg-slate-800 border border-slate-700 text-slate-300 px-5 py-2.5 rounded-xl uppercase tracking-wider font-display flex items-center gap-2 flex-shrink-0">
        <Palette className="w-5 h-5 stroke-[2.5px] text-indigo-400" />
        Крокодил ойыны 🎨
      </span>

      {/* Main Crocodile Card */}
      <motion.div
        key={crocWord}
        initial={{ scale: 0.98, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 12 }}
        className="w-full bg-slate-800/80 border border-slate-700/50 text-white rounded-3xl p-6 md:p-10 flex flex-col items-center justify-center shadow-xl relative overflow-hidden"
      >
        {/* Rules explanation */}
        <p className="text-xs md:text-sm font-semibold text-slate-400 max-w-xl text-center mb-6 leading-relaxed">
          Жүргізуші ойыншыға сөз жасырады. Ойыншы оны сөйлемей, тек қағазға сурет салу арқылы көрсетеді, ал командасы сөзді табуы керек.
        </p>

        {/* Word Display frame */}
        <div className="w-full max-w-md bg-slate-950/60 border border-slate-850 rounded-2xl p-6 md:p-8 flex flex-col items-center gap-4 mb-6 shadow-inner min-h-[220px] justify-center relative">
          
          <AnimatePresence mode="wait">
            {isWordVisible ? (
              <motion.div
                key="visible-word"
                initial={{ scale: 0.8, opacity: 0 }}
                animate={{ scale: 1, opacity: 1 }}
                exit={{ scale: 0.8, opacity: 0 }}
                className="flex flex-col items-center gap-4 w-full"
              >
                {/* Custom Illustration */}
                <div className="w-28 h-28 md:w-36 md:h-36 bg-slate-900 border border-slate-850 rounded-xl flex items-center justify-center p-2 shadow-md">
                  {renderCrocIllustration(crocWord)}
                </div>
                
                {/* Active word string */}
                <h2 className="text-3xl md:text-4xl font-extrabold font-display text-white tracking-wide text-center">
                  {crocWord}
                </h2>
              </motion.div>
            ) : (
              <motion.div
                key="hidden-word"
                initial={{ scale: 0.8, opacity: 0 }}
                animate={{ scale: 1, opacity: 1 }}
                exit={{ scale: 0.8, opacity: 0 }}
                className="flex flex-col items-center gap-2"
              >
                <div className="text-6xl md:text-7xl animate-pulse-slow">🤫</div>
                <div className="text-xs md:text-sm font-bold font-display text-slate-500 uppercase tracking-wider text-center mt-2">
                  Сөз жасырылды
                </div>
              </motion.div>
            )}
          </AnimatePresence>
        </div>

        {/* Action Controls inside the main card */}
        <div className="flex flex-col sm:flex-row items-center gap-4 w-full max-w-md">
          {/* Toggle word Visibility */}
          <button
            onClick={toggleWordVisibility}
            className={`w-full py-3.5 px-6 font-bold font-display text-sm uppercase tracking-wider rounded-xl border shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2 ${
              isWordVisible 
                ? 'bg-slate-950 border-slate-800 text-slate-400 hover:text-white hover:bg-slate-900' 
                : 'bg-gradient-to-r from-[#4F46E5] to-[#6366F1] border-indigo-500/30 text-white hover:shadow-[0_0_20px_rgba(99,102,241,0.25)]'
            }`}
          >
            {isWordVisible ? (
              <>
                <EyeOff className="w-4 h-4 stroke-[2.5px]" />
                <span>ЖАСЫРУ</span>
              </>
            ) : (
              <>
                <Eye className="w-4 h-4 stroke-[2.5px]" />
                <span>СӨЗДІ КӨРСЕТУ</span>
              </>
            )}
          </button>

          {/* Random Next word skip */}
          <button
            onClick={nextCrocWord}
            className="w-full py-3.5 px-6 bg-gradient-to-r from-[#06B6D4] to-[#4F46E5] border-cyan-500/30 text-white font-bold font-display text-sm uppercase tracking-wider rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2 hover:shadow-[0_0_20px_rgba(6,182,212,0.25)]"
          >
            <SkipForward className="w-4 h-4 stroke-[2.5px]" />
            <span>КЕЛЕСІ СӨЗ</span>
          </button>
        </div>

      </motion.div>

    </div>
  );
};
