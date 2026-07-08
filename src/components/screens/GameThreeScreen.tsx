import React from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import type { GameChallenge } from '../../data';
import { Illustration } from '../Illustration';

interface GameThreeScreenProps {
  challenge: GameChallenge;
  timeLeft: number;
  timerActive: boolean;
  showingResult: boolean;
  animationsEnabled?: boolean;
}

export const GameThreeScreen: React.FC<GameThreeScreenProps> = ({
  challenge,
  timeLeft,
  timerActive,
  showingResult,
  animationsEnabled = true,
}) => {
  const illustrationType = showingResult ? 'finish' : 'book';

  return (
    <div className={`w-full h-full bg-gradient-to-br ${showingResult ? 'from-emerald-500 to-teal-700' : challenge.bgColor} fill-none flex flex-col items-center justify-between p-8 md:p-16 text-white relative select-none transition-colors duration-1000`}>
      {/* Background Bubbles */}
      <div className="absolute top-10 left-20 w-32 h-32 bg-white/10 rounded-full blur-xl pointer-events-none animate-float-slow" />
      <div className="absolute bottom-10 right-20 w-40 h-40 bg-white/15 rounded-full blur-xl pointer-events-none animate-float-reverse" />

      {/* Main Content Card Container */}
      <div className="flex-1 w-full max-w-5xl flex flex-col md:flex-row items-center justify-center gap-6 md:gap-12 z-10 my-auto p-4 max-h-[75vh]">
        
        {/* Left Side: Huge Icon Frame */}
        <motion.div
          key={`challenge-icon-${challenge.scrambled}-${showingResult}`}
          initial={animationsEnabled ? { scale: 0.8, opacity: 0, rotate: 5 } : {}}
          animate={animationsEnabled ? { scale: 1, opacity: 1, rotate: 0 } : {}}
          exit={animationsEnabled ? { scale: 0.8, opacity: 0 } : {}}
          transition={{ type: 'spring', stiffness: 100, damping: 12 }}
          className="w-52 h-52 md:w-80 md:h-80 max-h-[35vh] max-w-[35vh] aspect-square bg-white border-8 border-purple-950 text-purple-950 rounded-[3rem] flex items-center justify-center shadow-playful relative flex-shrink"
        >
          {/* Splash background pattern */}
          <div className="absolute inset-0 bg-grid-playful opacity-40 rounded-[2.3rem] overflow-hidden" />

          {/* Custom Illustration display */}
          <Illustration type={illustrationType} className="w-full h-full z-10 p-2 object-contain" />
          
          {/* Action Emoji Overlay */}
          <div className="absolute -top-6 -left-6 w-16 h-16 bg-yellow-400 border-4 border-purple-950 rounded-2xl flex items-center justify-center text-3xl shadow-playful animate-bounce-gentle z-30">
            {challenge.icon}
          </div>
          
          {/* Confetti mini bubbles */}
          <div className="absolute -bottom-6 -left-6 w-14 h-14 bg-yellow-300 rounded-full border-4 border-purple-950 z-20" />
          <div className="absolute -top-5 -right-5 w-12 h-12 bg-pink-300 rounded-full border-4 border-purple-950 z-20" />
        </motion.div>

        {/* Right Side: Title Instructions & Countdown */}
        <div className="flex flex-col items-center md:items-start text-center md:text-left gap-6 max-w-xl flex-shrink-0">
          
          <motion.div
            key={`challenge-title-${challenge.correct}-${showingResult}`}
            initial={animationsEnabled ? { y: 50, opacity: 0 } : {}}
            animate={animationsEnabled ? { y: 0, opacity: 1 } : {}}
            transition={{ type: 'spring', stiffness: 100, damping: 15 }}
            className="flex flex-col items-center md:items-start gap-4"
          >
            <span className="text-2xl md:text-3xl font-bold bg-white/20 px-6 py-2 rounded-full border-2 border-white/40 font-display tracking-widest uppercase">
              {showingResult ? 'Дұрыс жауабы! 💡' : 'Мақалды құрастыр! 🧩'}
            </span>
            <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold font-display tracking-wide text-white drop-shadow-md leading-tight text-stroke-playful">
              {showingResult ? challenge.correct : challenge.scrambled}
            </h1>
          </motion.div>

          {/* Countdown Clock or Score Info */}
          {!showingResult ? (
            timerActive && (
              <div className="flex items-center gap-6 mt-2">
                <AnimatePresence mode="popLayout">
                  <motion.div
                    key={`timer-${timeLeft}`}
                    initial={animationsEnabled ? { scale: 1.5, opacity: 0 } : {}}
                    animate={animationsEnabled ? { scale: 1, opacity: 1 } : {}}
                    exit={animationsEnabled ? { scale: 0.6, opacity: 0 } : {}}
                    transition={{ type: 'spring', stiffness: 200, damping: 10 }}
                    className="w-24 h-24 md:w-32 md:h-32 bg-yellow-400 border-8 border-white text-purple-950 rounded-full flex items-center justify-center font-display font-bold text-5xl md:text-7xl shadow-playful"
                  >
                    {timeLeft}
                  </motion.div>
                </AnimatePresence>
                <div className="text-left">
                  <div className="text-xl md:text-2xl font-bold uppercase tracking-wider font-display text-yellow-300">
                    Ретке келтіріңдер!
                  </div>
                  <div className="text-sm md:text-base font-bold text-white/90">
                    Сөздерді дұрыс жинап үлгер!
                  </div>
                </div>
              </div>
            )
          ) : (
            <div className="text-left mt-2 bg-white/10 p-4 rounded-3xl border-2 border-white/20">
              <div className="text-xl md:text-2xl font-bold text-yellow-300">
                Тапсырма аяқталды! 🏆
              </div>
              <div className="text-sm md:text-base font-bold text-white/95 mt-1">
                Дұрыс құрастырған командаға 1 ұпай беріледі!
              </div>
            </div>
          )}

        </div>

      </div>
    </div>
  );
};

export default GameThreeScreen;
