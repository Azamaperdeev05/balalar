import React from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import type { GameAction } from '../../data';
import { Illustration } from '../Illustration';

interface GameOneScreenProps {
  action: GameAction;
  timeLeft: number;
  timerActive: boolean;
  animationsEnabled?: boolean;
}

export const GameOneScreen: React.FC<GameOneScreenProps> = ({
  action,
  timeLeft,
  timerActive,
  animationsEnabled = true,
}) => {
  // Determine relevant illustration for each action
  const getIllustrationType = (title: string): any => {
    const t = title.toLowerCase();
    if (t.includes('шапалақта')) return 'clapping';
    if (t.includes('секір')) return 'jumping';
    if (t.includes('айнал')) return 'spin';
    if (t.includes('отыр')) return 'sit';
    if (t.includes('тұр')) return 'stand';
    if (t.includes('көтер')) return 'raise_hands';
    if (t.includes('жүрек')) return 'heart';
    if (t.includes('амандас')) return 'greet';
    return 'children';
  };

  const illustrationType = getIllustrationType(action.title);

  return (
    <div className={`w-full h-full bg-gradient-to-br ${action.bgColor} flex flex-col items-center justify-between p-8 md:p-16 text-white relative select-none transition-colors duration-1000`}>
      {/* Floating Decorative Elements */}
      <div className="absolute top-20 left-10 w-24 h-24 bg-white/10 rounded-full blur-xl pointer-events-none animate-float" />
      <div className="absolute bottom-20 right-10 w-32 h-32 bg-white/15 rounded-full blur-xl pointer-events-none animate-float-reverse" />

      {/* Main Content Card Container */}
      <div className="flex-1 w-full max-w-5xl flex flex-col md:flex-row items-center justify-center gap-6 md:gap-12 z-10 my-auto p-4 max-h-[75vh]">
        
        {/* Left Side: Huge Illustration Container */}
        <motion.div 
          key={`illustration-${action.title}`}
          initial={animationsEnabled ? { scale: 0.8, opacity: 0, rotate: -5 } : {}}
          animate={animationsEnabled ? { scale: 1, opacity: 1, rotate: 0 } : {}}
          exit={animationsEnabled ? { scale: 0.8, opacity: 0 } : {}}
          transition={{ type: 'spring', stiffness: 100, damping: 12 }}
          className="w-52 h-52 md:w-80 md:h-80 max-h-[35vh] max-w-[35vh] aspect-square bg-white/20 backdrop-blur-md rounded-[3rem] border-8 border-white/40 flex items-center justify-center shadow-2xl p-6 relative flex-shrink"
        >
          <Illustration type={illustrationType} className="w-full h-full drop-shadow-lg" />
          
          {/* Action Emoji Overlay */}
          <div className="absolute -top-6 -left-6 w-16 h-16 bg-yellow-400 border-4 border-white rounded-2xl flex items-center justify-center text-3xl shadow-playful animate-bounce-gentle z-20">
            {action.emoji}
          </div>
        </motion.div>

        {/* Right Side: Giant Text & Countdown */}
        <div className="flex flex-col items-center md:items-start text-center md:text-left gap-6 max-w-xl flex-shrink-0">
          
          {/* Action Text */}
          <motion.div
            key={`title-${action.title}`}
            initial={animationsEnabled ? { y: 50, opacity: 0 } : {}}
            animate={animationsEnabled ? { y: 0, opacity: 1 } : {}}
            transition={{ type: 'spring', stiffness: 100, damping: 15 }}
            className="flex flex-col items-center md:items-start gap-4"
          >
            <span className="text-2xl md:text-3xl font-black bg-white/25 px-6 py-2 rounded-full border-2 border-white/40 font-display tracking-widest uppercase">
              Қайтала! 🙋‍♂️
            </span>
            <h1 className="text-5xl md:text-7xl lg:text-8xl font-black font-display tracking-wide text-white drop-shadow-md leading-tight">
              {action.title}
            </h1>
          </motion.div>

          {/* Countdown Display inside the card */}
          {timerActive && (
            <div className="flex items-center gap-6 mt-2">
              <AnimatePresence mode="popLayout">
                <motion.div
                  key={`timer-${timeLeft}`}
                  initial={animationsEnabled ? { scale: 1.5, opacity: 0 } : {}}
                  animate={animationsEnabled ? { scale: 1, opacity: 1 } : {}}
                  exit={animationsEnabled ? { scale: 0.6, opacity: 0 } : {}}
                  transition={{ type: 'spring', stiffness: 200, damping: 10 }}
                  className="w-24 h-24 md:w-32 md:h-32 bg-yellow-400 border-8 border-white text-purple-950 rounded-full flex items-center justify-center font-display font-black text-5xl md:text-7xl shadow-playful"
                >
                  {timeLeft}
                </motion.div>
              </AnimatePresence>
              <div className="text-left">
                <div className="text-xl md:text-2xl font-black uppercase tracking-wider font-display text-yellow-300">
                  Дайындал!
                </div>
                <div className="text-sm md:text-base font-bold text-white/90">
                  Қимылды жылдам орында
                </div>
              </div>
            </div>
          )}

        </div>

      </div>
    </div>
  );
};

export default GameOneScreen;
