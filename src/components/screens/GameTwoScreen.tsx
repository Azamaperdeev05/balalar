import React from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import type { GameQuestion } from '../../data';
import { Illustration } from '../Illustration';
import { HelpCircle } from 'lucide-react';

interface GameTwoScreenProps {
  questionObj: GameQuestion;
  timeLeft: number;
  timerActive: boolean;
  showingResult: boolean;
  animationsEnabled?: boolean;
}

export const GameTwoScreen: React.FC<GameTwoScreenProps> = ({
  questionObj,
  timeLeft,
  timerActive,
  showingResult,
  animationsEnabled = true,
}) => {
  const illustrationType = 'book';

  return (
    <div className="w-full h-full relative select-none">
      <AnimatePresence mode="wait">
        {!showingResult ? (
          /* ACTIVE TASK STATE */
          <motion.div
            key="question-screen"
            initial={animationsEnabled ? { opacity: 0 } : {}}
            animate={animationsEnabled ? { opacity: 1 } : {}}
            exit={animationsEnabled ? { opacity: 0 } : {}}
            className="w-full h-full bg-gradient-to-br from-indigo-500 to-purple-700 flex flex-col items-center justify-center p-4 md:p-8 text-white overflow-hidden"
          >
            {/* Background Details */}
            <div className="absolute top-20 left-10 w-24 h-24 bg-white/10 rounded-full blur-xl pointer-events-none animate-float" />
            <div className="absolute bottom-20 right-10 w-32 h-32 bg-white/15 rounded-full blur-xl pointer-events-none animate-float-reverse" />

            <div className="w-full max-w-4xl flex flex-col items-center gap-4 md:gap-6 z-10 max-h-[85vh]">
              {/* Game Badge */}
              <span className="text-lg md:text-xl font-bold bg-white/20 px-6 py-2.5 rounded-full border-2 border-white/40 tracking-widest uppercase font-display flex items-center gap-2 flex-shrink-0">
                <HelpCircle className="w-5 h-5 stroke-[3px]" />
                СӨЗДІ ТАУЫП КӨР 📖
              </span>

              {/* Main Question Card */}
              <motion.div
                initial={animationsEnabled ? { scale: 0.9, opacity: 0 } : {}}
                animate={animationsEnabled ? { scale: 1, opacity: 1 } : {}}
                className="w-full bg-white border-8 border-purple-950 text-purple-950 rounded-[3rem] p-6 md:p-10 flex flex-col items-center justify-center shadow-playful relative flex-shrink overflow-hidden max-h-[50vh]"
              >
                {/* Illustration */}
                <div className="w-32 h-32 md:w-44 md:h-44 mb-2 bg-purple-50 rounded-[2rem] border-2 border-purple-100 flex items-center justify-center p-3 flex-shrink max-h-[20vh] max-w-[20vh] aspect-square">
                  <Illustration type={illustrationType} className="w-full h-full" />
                </div>

                {/* Question/Task Text */}
                <h1 className="text-3xl md:text-5xl lg:text-6xl font-bold text-center font-display tracking-wide leading-tight text-purple-950 max-w-3xl">
                  {questionObj.question}
                </h1>
              </motion.div>

              {/* Countdown Timer */}
              {timerActive && (
                <div className="flex items-center gap-4 mt-1 flex-shrink-0">
                  <AnimatePresence mode="popLayout">
                    <motion.div
                      key={`timer-${timeLeft}`}
                      initial={animationsEnabled ? { scale: 1.5, opacity: 0 } : {}}
                      animate={animationsEnabled ? { scale: 1, opacity: 1 } : {}}
                      exit={animationsEnabled ? { scale: 0.6, opacity: 0 } : {}}
                      transition={{ type: 'spring', stiffness: 200, damping: 10 }}
                      className="w-16 h-16 md:w-20 md:h-20 bg-yellow-400 border-6 border-white text-purple-950 rounded-full flex items-center justify-center font-display font-bold text-3xl md:text-4xl shadow-playful"
                    >
                      {timeLeft}
                    </motion.div>
                  </AnimatePresence>
                  <div className="text-left">
                    <div className="text-base md:text-xl font-bold uppercase tracking-wider font-display text-yellow-300">
                      Кітаптан тез табыңдар! 🔍
                    </div>
                    <div className="text-xs md:text-sm font-bold text-white/95">
                      Тапсырманы бірінші орындаған топ ұпай алады!
                    </div>
                  </div>
                </div>
              )}
            </div>
          </motion.div>
        ) : (
          /* RESULT STATE (REVEAL POINTS DISPATCH) */
          <motion.div
            key="result-screen"
            initial={animationsEnabled ? { opacity: 0 } : {}}
            animate={animationsEnabled ? { opacity: 1 } : {}}
            exit={animationsEnabled ? { opacity: 0 } : {}}
            className="w-full h-full bg-gradient-to-br from-emerald-500 to-teal-700 flex flex-col items-center justify-center p-4 md:p-8 text-white text-center relative overflow-hidden"
          >
            <div className="absolute top-10 left-10 w-48 h-48 bg-white/5 rounded-full blur-2xl pointer-events-none animate-float" />
            
            <motion.div
              initial={animationsEnabled ? { y: 50, opacity: 0 } : {}}
              animate={animationsEnabled ? { y: 0, opacity: 1 } : {}}
              transition={{ type: 'spring', stiffness: 100, damping: 12, delay: 0.1 }}
              className="flex flex-col items-center gap-6 z-10 max-h-[85vh] w-full max-w-3xl bg-white border-8 border-purple-950 text-purple-950 rounded-[3rem] p-8 md:p-12 shadow-playful"
            >
              <span className="text-2xl md:text-3xl lg:text-4xl font-display font-bold bg-purple-100 border-4 border-purple-950 px-6 py-2 rounded-2xl shadow-md rotate-[-3deg] animate-bounce-gentle flex-shrink-0 text-purple-950">
                Уақыт аяқталды! ⏰
              </span>
              
              <div className="w-28 h-28 md:w-40 md:h-40 bg-purple-50 rounded-[2rem] border-4 border-purple-950/20 flex items-center justify-center p-3 flex-shrink max-h-[22vh] max-w-[22vh] aspect-square">
                <Illustration type="finish" className="w-full h-full" />
              </div>

              <h2 className="text-2xl md:text-3xl lg:text-4xl font-bold font-display tracking-wide leading-tight text-purple-950 drop-shadow-sm flex-shrink-0">
                Тапсырманы бірінші болып тапқан топқа <span className="text-orange-500">1 ұпай</span> беріледі! 🏆
              </h2>
            </motion.div>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
};

export default GameTwoScreen;
