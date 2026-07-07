import React from 'react';
import { motion } from 'framer-motion';
import type { Variants } from 'framer-motion';
import { Illustration } from '../Illustration';
import { soundManager } from '../../utils/SoundManager';

interface IntroScreenProps {
  title: string;
  description: string;
  illustrationType: 'children' | 'clapping' | 'jumping' | 'colors' | 'questions' | 'finish' | 'celebration';
  onStart: () => void;
  animationsEnabled?: boolean;
}

export const IntroScreen: React.FC<IntroScreenProps> = ({
  title,
  description,
  illustrationType,
  onStart,
  animationsEnabled = true,
}) => {
  const handleStart = () => {
    soundManager.playPop();
    onStart();
  };

  const containerVariants: Variants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: {
        staggerChildren: 0.15,
      },
    },
  };

  const itemVariants: Variants = {
    hidden: { y: 30, opacity: 0 },
    visible: {
      y: 0,
      opacity: 1,
      transition: {
        type: 'spring',
        stiffness: 100,
        damping: 15,
      },
    },
  };

  return (
    <motion.div
      variants={animationsEnabled ? containerVariants : undefined}
      initial="hidden"
      animate="visible"
      className="w-full h-full flex flex-col items-center justify-center text-center p-4 md:p-8 select-none bg-grid-playful overflow-hidden"
    >
      <div className="max-w-4xl flex flex-col items-center justify-center gap-4 md:gap-6 max-h-[85vh] w-full">
        
        {/* Game Title */}
        <motion.h1
          variants={animationsEnabled ? itemVariants : undefined}
          className="text-4xl md:text-5xl lg:text-6xl font-black font-display tracking-wide text-stroke-playful leading-tight mb-1"
        >
          {title}
        </motion.h1>

        {/* Game Illustration */}
        <motion.div
          variants={animationsEnabled ? itemVariants : undefined}
          className="w-36 h-36 md:w-52 md:h-52 max-h-[22vh] max-w-[22vh] aspect-square relative flex items-center justify-center bg-purple-100/50 rounded-[2rem] border-4 border-dashed border-purple-300 p-3 shadow-inner flex-shrink"
        >
          <Illustration type={illustrationType} className="w-full h-full" />
        </motion.div>

        {/* Game Description */}
        <motion.p
          variants={animationsEnabled ? itemVariants : undefined}
          className="text-xl md:text-2xl lg:text-3xl font-extrabold text-purple-800 leading-snug max-w-2xl font-sans"
        >
          {description}
        </motion.p>

        {/* Start Button */}
        <motion.div
          variants={animationsEnabled ? itemVariants : undefined}
          className="mt-2 flex-shrink-0"
        >
          <button
            onClick={handleStart}
            className="px-10 py-4 md:px-12 md:py-5 rounded-[1.8rem] bg-gradient-to-r from-orange-400 to-amber-500 hover:from-orange-300 hover:to-amber-400 text-white font-display font-black text-2xl md:text-3xl tracking-wide shadow-playful hover:scale-105 active:scale-95 transition-all border-4 border-purple-950 cursor-pointer animate-bounce-gentle"
            style={{ animationDuration: '2.5s' }}
          >
            БАСТАУ 🚀
          </button>
        </motion.div>
        
      </div>
    </motion.div>
  );
};

export default IntroScreen;
