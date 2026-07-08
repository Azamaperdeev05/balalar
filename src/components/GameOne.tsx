'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { motion, AnimatePresence } from 'framer-motion';
import { Palette, Eye, EyeOff, SkipForward } from 'lucide-react';

export const GameOne: React.FC = () => {
  const { crocWord, isWordVisible, toggleWordVisibility, nextCrocWord } = useGame();

  // Handcrafted simple, clean emoji stickers for all 30 words
  const getCrocEmoji = (word: string): string => {
    const w = word.trim().toLowerCase();
    switch (w) {
      case 'кітап': return '📖';
      case 'қалам': return '✍️';
      case 'достық': return '🤝';
      case 'қазақстан': return '🇰🇿';
      case 'домбыра': return '🪕';
      case 'батыр': return '🛡️';
      case 'күн': return '☀️';
      case 'ай': return '🌙';
      case 'ту': return '🚩';
      case 'қыран':
      case 'бүркіт': return '🦅';
      case 'мектеп': return '🏫';
      case 'мұғалім': return '🧑‍🏫';
      case 'оқушы': return '🎒';
      case 'ғарыш': return '🚀';
      case 'компьютер': return '💻';
      case 'телефон': return '📱';
      case 'алма': return '🍎';
      case 'мысық': return '🐱';
      case 'ит': return '🐶';
      case 'қарлығаш': return '🐦';
      case 'түлкі': return '🦊';
      case 'жылқы': return '🐴';
      case 'теңіз':
      case 'өзен': return '🌊';
      case 'тау': return '🏔️';
      case 'бұлт': return '☁️';
      case 'кемпірқосақ': return '🌈';
      case 'кітапхана': return '📚';
      case 'арман': return '💫';
      default: return '✨';
    }
  };

  const renderCrocEmojiSticker = (word: string) => {
    const emoji = getCrocEmoji(word);
    return (
      <div className="w-24 h-24 md:w-28 md:h-28 bg-slate-950/80 border border-slate-800 rounded-3xl flex items-center justify-center shadow-lg relative overflow-hidden group">
        {/* Soft background glow */}
        <div className="absolute inset-0 bg-gradient-to-tr from-indigo-500/10 to-cyan-500/10 opacity-70 group-hover:opacity-100 transition-opacity" />
        
        {/* Large sticker emoji */}
        <span className="text-5xl md:text-6xl drop-shadow-[0_4px_10px_rgba(0,0,0,0.5)] transform group-hover:scale-110 transition-transform duration-300 select-none">
          {emoji}
        </span>
      </div>
    );
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
                  {renderCrocEmojiSticker(crocWord)}
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
