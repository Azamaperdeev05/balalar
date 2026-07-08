'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { motion } from 'framer-motion';
import { Palette, BookOpen, Layers, ArrowRight } from 'lucide-react';
import { Illustration, IllustrationType } from './Illustration';

export const WelcomeScreen: React.FC = () => {
  const { setActiveScreen, setActiveGameIndex } = useGame();

  const gameCards: {
    index: number;
    title: string;
    description: string;
    icon: React.ReactNode;
    illustration: IllustrationType;
    bgColor: string;
    borderCol: string;
  }[] = [
    {
      index: 0,
      title: 'Крокодил 🎨',
      description: 'Жүргізуші бір ойыншыға сөз жасырады. Ойыншы оны сөйлемей, тек сурет салу арқылы көрсетеді, ал командасы сөзді табуы керек.',
      icon: <Palette className="w-8 h-8 text-pink-500" />,
      illustration: 'jumping',
      bgColor: 'from-amber-400 to-orange-500',
      borderCol: 'border-orange-500'
    },
    {
      index: 1,
      title: 'Сөзді тауып көр 📖',
      description: 'Жүргізуші берген тапсырмаларды кітаптан мүмкіндігінше ең бірінші болып тауып көрсеткен команда ұпай алады.',
      icon: <BookOpen className="w-8 h-8 text-blue-500" />,
      illustration: 'book',
      bgColor: 'from-blue-400 to-indigo-600',
      borderCol: 'border-blue-600'
    },
    {
      index: 2,
      title: 'Мақалды құрастыр 🧩',
      description: 'Араласқан сөздердің дұрыс ретін сүйреп немесе басу арқылы тауып, мақал-мәтелді бірінші болып құрастырыңдар.',
      icon: <Layers className="w-8 h-8 text-emerald-500" />,
      illustration: 'colors',
      bgColor: 'from-emerald-400 to-teal-600',
      borderCol: 'border-emerald-600'
    }
  ];

  const handleStartGame = (index: number) => {
    setActiveGameIndex(index);
    setActiveScreen('games');
  };

  return (
    <div className="w-full min-h-[80vh] flex flex-col items-center justify-center py-8 px-4 md:px-8 text-center relative select-none">
      {/* Title block */}
      <motion.div
        initial={{ y: -30, opacity: 0 }}
        animate={{ y: 0, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 15 }}
        className="max-w-3xl flex flex-col items-center gap-4 mb-8 md:mb-12"
      >
        <span className="text-xs md:text-sm font-semibold bg-white dark:bg-slate-800 border border-slate-200 dark:border-slate-700 text-slate-700 dark:text-slate-300 px-5 py-2.5 rounded-xl uppercase tracking-wider font-display shadow-sm">
          Кітап Оқу Кеші 🎉
        </span>
        <h1 className="text-4xl md:text-6xl lg:text-7xl font-extrabold font-display tracking-tight text-slate-900 dark:text-white mt-2 leading-tight">
          Кітап әлемі ойындары
        </h1>
        <p className="text-sm md:text-lg font-medium font-display text-slate-600 dark:text-slate-400 max-w-2xl px-2">
          Команда болып ойнаңыздар, кітап оқып, көңіл көтеріңіздер!
        </p>
      </motion.div>

      {/* Central big START button */}
      <motion.div
        initial={{ scale: 0.8, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 200, damping: 10, delay: 0.15 }}
        className="mb-12 md:mb-16"
      >
        <button
          onClick={() => handleStartGame(0)}
          className="group px-12 md:px-16 py-4.5 md:py-5 bg-gradient-to-r from-[#4F46E5] to-[#6366F1] hover:from-[#6366F1] hover:to-[#4F46E5] text-white text-xl md:text-2xl font-bold font-display tracking-wide rounded-2xl shadow-xl hover:shadow-[0_0_30px_rgba(99,102,241,0.3)] hover:scale-105 active:scale-95 transition-all flex items-center gap-3 relative overflow-hidden"
        >
          <span>ОЙЫНДЫ БАСТАУ</span>
          <ArrowRight className="w-6 h-6 md:w-8 md:h-8 stroke-[2.5px] text-white group-hover:translate-x-1 transition-transform" />
        </button>
      </motion.div>

      {/* Game Card Previews */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 md:gap-8 w-full max-w-6xl">
        {gameCards.map((card, idx) => (
          <motion.div
            key={card.index}
            initial={{ y: 50, opacity: 0 }}
            animate={{ y: 0, opacity: 1 }}
            transition={{ type: 'spring', stiffness: 100, damping: 12, delay: 0.3 + idx * 0.1 }}
            onClick={() => handleStartGame(card.index)}
            className="cursor-pointer bg-white/90 dark:bg-slate-800/80 border border-slate-200 dark:border-slate-700/50 text-slate-900 dark:text-white rounded-3xl p-8 flex flex-col items-center gap-6 shadow-xl hover:scale-[1.03] hover:border-indigo-500/50 hover:shadow-[0_0_30px_rgba(99,102,241,0.15)] transition-all duration-300 relative group overflow-hidden"
          >
            {/* Visual Frame */}
            <div className="w-24 h-24 rounded-xl bg-slate-50 dark:bg-slate-900 border border-slate-200 dark:border-slate-850 flex items-center justify-center p-2.5 shadow-inner relative">
              <Illustration type={card.illustration} className="w-full h-full object-contain filter grayscale dark:brightness-125 dark:contrast-75 group-hover:grayscale-0 transition-all duration-300" />
              <div className="absolute -top-3 -left-3 w-8 h-8 bg-white dark:bg-slate-800 border border-slate-200 dark:border-slate-700 rounded-lg flex items-center justify-center shadow-md">
                {card.icon}
              </div>
            </div>

            {/* Game Meta */}
            <div className="flex flex-col gap-2">
              <h3 className="text-lg md:text-xl font-bold font-display text-slate-900 dark:text-white group-hover:text-indigo-500 dark:group-hover:text-indigo-400 transition-colors">
                {card.title}
              </h3>
              <p className="text-xs md:text-sm font-medium text-slate-500 dark:text-slate-400 leading-relaxed text-center">
                {card.description}
              </p>
            </div>
            
            {/* Bottom Button inside Card */}
            <div className="w-full mt-auto pt-4 border-t border-slate-200 dark:border-slate-700/35">
              <button
                className="w-full py-2.5 bg-gradient-to-r from-indigo-600 to-indigo-500 hover:from-indigo-500 hover:to-indigo-600 text-white text-xs font-bold font-display tracking-wider uppercase rounded-xl transition-all hover:scale-102 active:scale-98 flex items-center justify-center gap-1.5 shadow"
              >
                <span>▶ Бастау</span>
              </button>
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
};
