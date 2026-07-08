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
        <span className="text-xl md:text-2xl font-black bg-purple-600 border-4 border-purple-950 text-white px-6 py-2 rounded-full shadow-playful rotate-[-2deg] font-display uppercase tracking-widest">
          Кітап Оқу Кеші 🎉
        </span>
        <h1 className="text-4xl md:text-6xl lg:text-7xl font-black font-display tracking-wide text-purple-950 drop-shadow-md leading-tight text-stroke-playful mt-2">
          📚 Кітап әлемі ойындары
        </h1>
        <p className="text-lg md:text-2xl font-black font-display text-purple-800/80 max-w-2xl px-2">
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
          className="group px-10 md:px-16 py-6 md:py-8 bg-yellow-400 hover:bg-yellow-300 text-purple-950 text-3xl md:text-5xl font-black font-display tracking-wider rounded-[2.5rem] border-8 border-purple-950 shadow-playful hover:scale-105 transition-all duration-200 flex items-center gap-4 relative overflow-hidden active:scale-95"
        >
          <div className="absolute inset-0 bg-white/20 translate-y-full group-hover:translate-y-0 transition-transform duration-300" />
          <span>ОЙЫНДЫ БАСТАУ</span>
          <ArrowRight className="w-10 h-10 md:w-14 md:h-14 stroke-[4px] animate-pulse-slow" />
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
            className={`cursor-pointer bg-white border-8 border-purple-950 text-purple-950 rounded-[2.5rem] p-6 md:p-8 flex flex-col items-center gap-4 md:gap-6 shadow-playful hover:scale-105 hover:-translate-y-2 transition-all duration-300 relative group overflow-hidden`}
          >
            {/* Visual Frame */}
            <div className={`w-32 h-32 rounded-3xl bg-gradient-to-br ${card.bgColor} border-4 border-purple-950 flex items-center justify-center p-2 shadow-md relative`}>
              <Illustration type={card.illustration} className="w-full h-full object-contain" />
              <div className="absolute -top-3 -left-3 w-10 h-10 bg-white border-2 border-purple-950 rounded-xl flex items-center justify-center shadow">
                {card.icon}
              </div>
            </div>

            {/* Game Meta */}
            <div className="flex flex-col gap-2">
              <h3 className="text-2xl md:text-3xl font-black font-display text-purple-950 group-hover:text-purple-600 transition-colors">
                {card.title}
              </h3>
              <p className="text-sm md:text-base font-bold text-purple-900/70 leading-relaxed text-center">
                {card.description}
              </p>
            </div>
            
            {/* Hover Action indicator */}
            <div className="mt-auto pt-2 opacity-0 group-hover:opacity-100 transition-opacity flex items-center gap-1.5 text-purple-950 font-black font-display text-sm uppercase">
              Ойнау <ArrowRight className="w-4 h-4 stroke-[3px]" />
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
};
