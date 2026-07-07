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
  const getIllustrationType = (question: string): any => {
    const q = question.toLowerCase();
    if (q.includes('балмұздақ')) return 'icecream';
    if (q.includes('мультфильм')) return 'cartoon';
    if (q.includes('жануар')) return 'animals';
    if (q.includes('жазды') || q.includes('жаз ')) return 'summer';
    if (q.includes('кітап')) return 'book';
    if (q.includes('көңіл-күй')) return 'mood';
    if (q.includes('динозавр')) return 'dino';
    if (q.includes('піл')) return 'elephant';
    if (q.includes('айға') || q.includes('ұшып')) return 'jumping';
    if (q.includes('айдаһар')) return 'animals';
    return 'questions';
  };

  const illustrationType = getIllustrationType(questionObj.question);

  return (
    <div className="w-full h-full relative select-none">
      <AnimatePresence mode="wait">
        {!showingResult ? (
          /* QUESTION STATE */
          <motion.div
            key="question-screen"
            initial={animationsEnabled ? { opacity: 0 } : {}}
            animate={animationsEnabled ? { opacity: 1 } : {}}
            exit={animationsEnabled ? { opacity: 0 } : {}}
            className="w-full h-full bg-gradient-to-br from-amber-400 to-orange-500 flex flex-col items-center justify-center p-4 md:p-8 text-white overflow-hidden"
          >
            {/* Background Details */}
            <div className="absolute top-20 left-10 w-24 h-24 bg-white/10 rounded-full blur-xl pointer-events-none animate-float" />
            <div className="absolute bottom-20 right-10 w-32 h-32 bg-white/15 rounded-full blur-xl pointer-events-none animate-float-reverse" />

            <div className="w-full max-w-4xl flex flex-col items-center gap-4 md:gap-6 z-10 max-h-[85vh]">
              {/* Game Badge */}
              <span className="text-lg md:text-xl font-black bg-white/20 px-6 py-2.5 rounded-full border-2 border-white/40 tracking-widest uppercase font-display flex items-center gap-2 flex-shrink-0">
                <HelpCircle className="w-5 h-5 stroke-[3px]" />
                ИӘ НЕМЕСЕ ЖОҚ СҰРАҚТАРЫ
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

                {/* Question Text */}
                <h1 className="text-3xl md:text-5xl lg:text-6xl font-black text-center font-display tracking-wide leading-tight text-purple-950 max-w-3xl">
                  {questionObj.question}
                </h1>
                
                {questionObj.isFunny && (
                  <span className="absolute -top-3 -right-3 bg-purple-600 border-4 border-purple-950 text-white font-display font-black text-sm md:text-base px-3 py-1.5 rounded-2xl rotate-12 animate-pulse-slow">
                    Күлкілі! 🤪
                  </span>
                )}
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
                      className="w-16 h-16 md:w-20 md:h-20 bg-yellow-400 border-6 border-white text-purple-950 rounded-full flex items-center justify-center font-display font-black text-3xl md:text-4xl shadow-playful"
                    >
                      {timeLeft}
                    </motion.div>
                  </AnimatePresence>
                  <div className="text-left">
                    <div className="text-base md:text-xl font-black uppercase tracking-wider font-display text-yellow-300">
                      Жауабыңды ойлан!
                    </div>
                    <div className="text-xs md:text-sm font-bold text-white/95">
                      Қазір қимыл көрсетіледі...
                    </div>
                  </div>
                </div>
              )}
            </div>
          </motion.div>
        ) : (
          /* RESULT STATE (SPLIT SCREEN REVEAL) */
          <motion.div
            key="result-screen"
            initial={animationsEnabled ? { opacity: 0 } : {}}
            animate={animationsEnabled ? { opacity: 1 } : {}}
            exit={animationsEnabled ? { opacity: 0 } : {}}
            className="w-full h-full flex flex-col md:flex-row z-10 overflow-hidden"
          >
            {/* YES Side (Green) */}
            <div className="flex-1 bg-gradient-to-br from-emerald-400 to-green-600 flex flex-col items-center justify-center p-4 md:p-8 text-white border-b-8 md:border-b-0 md:border-r-8 border-purple-950/20 text-center relative overflow-hidden">
              <div className="absolute top-10 left-10 w-48 h-48 bg-white/5 rounded-full blur-2xl pointer-events-none animate-float" />
              
              <motion.div
                initial={animationsEnabled ? { y: 50, opacity: 0 } : {}}
                animate={animationsEnabled ? { y: 0, opacity: 1 } : {}}
                transition={{ type: 'spring', stiffness: 100, damping: 12, delay: 0.1 }}
                className="flex flex-col items-center gap-4 z-10 max-h-[85vh] w-full"
              >
                <span className="text-2xl md:text-3xl lg:text-4xl font-display font-bold bg-white/20 border-4 border-white px-6 py-2 rounded-2xl shadow-md rotate-[-5deg] animate-bounce-gentle flex-shrink-0">
                  ИӘ 🎉
                </span>
                
                <div className="w-28 h-28 md:w-40 md:h-40 bg-white/10 rounded-[2rem] border-4 border-white/20 flex items-center justify-center p-3 flex-shrink max-h-[22vh] max-w-[22vh] aspect-square">
                  <Illustration type="jumping" className="w-full h-full" />
                </div>

                <h2 className="text-2xl md:text-3xl lg:text-4xl font-bold font-display tracking-wide leading-tight text-white drop-shadow-md text-stroke-playful flex-shrink-0">
                  Қолдарыңды көтеріп билеңдер! 💃🕺
                </h2>
              </motion.div>
            </div>

            {/* NO Side (Blue) */}
            <div className="flex-1 bg-gradient-to-br from-sky-400 to-blue-600 flex flex-col items-center justify-center p-4 md:p-8 text-white text-center relative overflow-hidden">
              <div className="absolute bottom-10 right-10 w-48 h-48 bg-white/5 rounded-full blur-2xl pointer-events-none animate-float-reverse" />
              
              <motion.div
                initial={animationsEnabled ? { y: 50, opacity: 0 } : {}}
                animate={animationsEnabled ? { y: 0, opacity: 1 } : {}}
                transition={{ type: 'spring', stiffness: 100, damping: 12, delay: 0.2 }}
                className="flex flex-col items-center gap-4 z-10 max-h-[85vh] w-full"
              >
                <span className="text-2xl md:text-3xl lg:text-4xl font-display font-bold bg-white/20 border-4 border-white px-6 py-2 rounded-2xl shadow-md rotate-[5deg] animate-bounce-gentle flex-shrink-0" style={{ animationDelay: '0.5s' }}>
                  ЖОҚ 🤫
                </span>

                <div className="w-28 h-28 md:w-40 md:h-40 bg-white/10 rounded-[2rem] border-4 border-white/20 flex items-center justify-center p-3 flex-shrink max-h-[22vh] max-w-[22vh] aspect-square">
                  <Illustration type="sit" className="w-full h-full" />
                </div>

                <h2 className="text-2xl md:text-3xl lg:text-4xl font-bold font-display tracking-wide leading-tight text-white drop-shadow-md text-stroke-playful flex-shrink-0">
                  Отырыңдар! ⬇️
                </h2>
              </motion.div>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
};

export default GameTwoScreen;
