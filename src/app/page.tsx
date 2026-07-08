'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { Header, Footer } from '../components/Navigation';
import { WelcomeScreen } from '../components/WelcomeScreen';
import { Scoreboard } from '../components/Scoreboard';
import { Timer } from '../components/Timer';
import { GameOne } from '../components/GameOne';
import { GameTwo } from '../components/GameTwo';
import { GameThree } from '../components/GameThree';
import { motion, AnimatePresence } from 'framer-motion';
import { Palette, BookOpen, Layers } from 'lucide-react';

export default function Home() {
  const { activeScreen, activeGameIndex, setActiveGameIndex } = useGame();

  const gameTabs = [
    { index: 0, label: 'Крокодил', icon: <Palette className="w-5 h-5" /> },
    { index: 1, label: 'Сөзді тауып көр', icon: <BookOpen className="w-5 h-5" /> },
    { index: 2, label: 'Мақалды құрастыр', icon: <Layers className="w-5 h-5" /> }
  ];

  return (
    <div className="flex-1 flex flex-col h-full bg-slate-50 dark:bg-[#0F172A] relative overflow-x-hidden transition-colors duration-300">

      {/* Decorative background blobs */}
      <div className="absolute top-1/4 left-10 w-64 h-64 bg-indigo-400/5 dark:bg-indigo-600/10 rounded-full blur-3xl pointer-events-none animate-float" />
      <div className="absolute bottom-1/4 right-10 w-80 h-80 bg-cyan-400/5 dark:bg-cyan-500/10 rounded-full blur-3xl pointer-events-none animate-float-reverse" />
      <div className="absolute bottom-10 left-1/3 w-48 h-48 bg-violet-400/5 dark:bg-violet-600/5 rounded-full blur-3xl pointer-events-none animate-float-slow" />

      {/* Navigation Header */}
      <Header />

      {/* Main Content */}
      <main className="flex-1 w-full max-w-7xl mx-auto px-4 md:px-8 py-6 md:py-10 flex flex-col items-center relative z-20">
        <AnimatePresence mode="wait">

          {activeScreen === 'welcome' && (
            <motion.div
              key="welcome-screen"
              initial={{ opacity: 0, y: 15 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -15 }}
              transition={{ duration: 0.25 }}
              className="w-full flex items-center justify-center py-6"
            >
              <WelcomeScreen />
            </motion.div>
          )}

          {activeScreen === 'scoreboard' && (
            <motion.div
              key="scoreboard-screen"
              initial={{ opacity: 0, y: 15 }}
              animate={{ opacity: 1, y: 0 }}
              exit={{ opacity: 0, y: -15 }}
              transition={{ duration: 0.25 }}
              className="w-full flex items-center justify-center py-6"
            >
              <Scoreboard />
            </motion.div>
          )}

          {activeScreen === 'games' && (
            <motion.div
              key="games-screen"
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              transition={{ duration: 0.2 }}
              className="w-full flex flex-col lg:flex-row items-center lg:items-start gap-8 lg:gap-10 py-4"
            >
              {/* Left: Game Area */}
              <div className="flex-1 flex flex-col items-center gap-6 w-full">

                {/* Game Tab Switcher */}
                <div className="flex flex-wrap items-center justify-center gap-2 bg-white dark:bg-slate-900 p-1.5 rounded-2xl border border-slate-200 dark:border-slate-800 w-full max-w-2xl shadow-sm dark:shadow-xl transition-colors">
                  {gameTabs.map((tab) => {
                    const isActive = activeGameIndex === tab.index;
                    return (
                      <button
                        key={tab.index}
                        onClick={() => setActiveGameIndex(tab.index)}
                        className={`flex-1 min-w-[130px] flex items-center justify-center gap-2 px-4 py-3 rounded-xl font-bold font-display text-sm transition-all ${
                          isActive
                            ? 'bg-gradient-to-r from-[#4F46E5] to-[#6366F1] text-white shadow-[0_0_20px_rgba(99,102,241,0.3)]'
                            : 'bg-transparent text-slate-500 dark:text-slate-400 hover:bg-slate-100 dark:hover:bg-slate-800 hover:text-slate-900 dark:hover:text-white'
                        }`}
                      >
                        {tab.icon}
                        <span>{tab.label}</span>
                      </button>
                    );
                  })}
                </div>

                {/* Active Game Component */}
                <div className="w-full flex justify-center">
                  {activeGameIndex === 0 && <GameOne />}
                  {activeGameIndex === 1 && <GameTwo />}
                  {activeGameIndex === 2 && <GameThree />}
                </div>
              </div>

              {/* Right: Timer */}
              <div className="lg:sticky lg:top-8 flex flex-col items-center gap-4 flex-shrink-0 w-full max-w-[280px]">
                <Timer />
              </div>
            </motion.div>
          )}

        </AnimatePresence>
      </main>

      {/* Footer */}
      <Footer />

    </div>
  );
}
