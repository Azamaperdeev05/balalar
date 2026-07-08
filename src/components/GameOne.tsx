'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { motion, AnimatePresence } from 'framer-motion';
import { Palette, Eye, EyeOff, SkipForward } from 'lucide-react';
import { Illustration, IllustrationType } from './Illustration';

export const GameOne: React.FC = () => {
  const { crocWord, isWordVisible, toggleWordVisibility, nextCrocWord } = useGame();

  // Helper to resolve custom illustrations for the Crocodile words
  const getIllustrationType = (word: string): IllustrationType => {
    const w = word.toLowerCase();
    if (w.includes('домбыра')) return 'dombra';
    if (w.includes('киіз үй')) return 'yurt';
    if (w.includes('түйе')) return 'camel';
    if (w.includes('қарбыз')) return 'watermelon';
    if (w.includes('бүркіт')) return 'eagle';
    if (w.includes('алтын адам')) return 'golden_man';
    if (w.includes('қазан')) return 'qazan';
    if (w.includes('кітап') || w.includes('кітапхана') || w.includes('мектеп') || w.includes('мұғалім') || w.includes('оқушы')) return 'book';
    if (w.includes('мысық') || w.includes('ит') || w.includes('түлкі') || w.includes('жылқы') || w.includes('қыран')) return 'animals';
    return 'children';
  };

  const illustrationType = getIllustrationType(crocWord);

  return (
    <div className="w-full max-w-4xl flex flex-col items-center gap-6 md:gap-8 p-4 z-10">
      
      <span className="text-sm md:text-base font-black bg-purple-600 border-4 border-purple-950 text-white px-5 py-2 rounded-full shadow-md tracking-wider uppercase font-display flex items-center gap-2 flex-shrink-0 rotate-[-1deg]">
        <Palette className="w-5 h-5 stroke-[3px]" />
        Крокодил ойыны 🎨
      </span>

      {/* Main Crocodile Card */}
      <motion.div
        key={crocWord}
        initial={{ scale: 0.95, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 12 }}
        className="w-full bg-white border-8 border-purple-950 text-purple-950 rounded-[3rem] p-6 md:p-10 flex flex-col items-center justify-center shadow-playful relative overflow-hidden"
      >
        {/* Rules explanation */}
        <p className="text-sm md:text-base font-bold text-purple-900/60 max-w-xl text-center mb-6 leading-relaxed">
          Жүргізуші ойыншыға сөз жасырады. Ойыншы оны сөйлемей, тек қағазға сурет салу арқылы көрсетеді, ал командасы сөзді табуы керек.
        </p>

        {/* Word Display frame */}
        <div className="w-full max-w-md bg-purple-50 border-4 border-purple-950/20 rounded-[2rem] p-6 md:p-8 flex flex-col items-center gap-4 mb-6 shadow-inner min-h-[220px] justify-center relative">
          
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
                <div className="w-28 h-28 md:w-36 md:h-36 bg-white border-4 border-purple-950/15 rounded-2xl flex items-center justify-center p-2 shadow-sm">
                  <Illustration type={illustrationType} className="w-full h-full object-contain" />
                </div>
                
                {/* Active word string */}
                <h2 className="text-4xl md:text-6xl font-black font-display text-purple-950 tracking-wide text-center">
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
                <div className="text-lg md:text-xl font-black font-display text-purple-950/40 uppercase tracking-wider text-center mt-2">
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
            className={`w-full py-4 px-6 font-black font-display text-lg md:text-xl rounded-2xl border-4 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2 ${
              isWordVisible 
                ? 'bg-yellow-400 text-purple-950 hover:bg-yellow-300' 
                : 'bg-purple-600 text-white hover:bg-purple-500'
            }`}
          >
            {isWordVisible ? (
              <>
                <EyeOff className="w-6 h-6 stroke-[3px]" />
                <span>ЖАСЫРУ</span>
              </>
            ) : (
              <>
                <Eye className="w-6 h-6 stroke-[3px]" />
                <span>СӨЗДІ КӨРСЕТУ</span>
              </>
            )}
          </button>

          {/* Random Next word skip */}
          <button
            onClick={nextCrocWord}
            className="w-full py-4 px-6 bg-orange-500 hover:bg-orange-400 text-white font-black font-display text-lg md:text-xl rounded-2xl border-4 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2"
          >
            <SkipForward className="w-6 h-6 stroke-[3px]" />
            <span>КЕЛЕСІ СӨЗ</span>
          </button>
        </div>

      </motion.div>

    </div>
  );
};
