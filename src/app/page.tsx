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
    { index: 0, label: 'Крокодил', icon: <Palette className="w-5 h-5" />, color: 'from-amber-400 to-orange-500' },
    { index: 1, label: 'Сөзді тауып көр', icon: <BookOpen className="w-5 h-5" />, color: 'from-blue-400 to-indigo-600' },
    { index: 2, label: 'Мақалды құрастыр', icon: <Layers className="w-5 h-5" />, color: 'from-emerald-400 to-teal-600' }
  ];

  return (
    <div className="flex-1 flex flex-col h-full bg-grid-playful relative overflow-x-hidden">
      
      {/* Navigation Header */}
      <Header />

      {/* Main Presentation Work Space */}
      <main className="flex-1 w-full max-w-7xl mx-auto flex flex-col items-center justify-center py-6 px-4 md:px-8 z-10 relative">
        <AnimatePresence mode="wait">
          {activeScreen === 'welcome' && (
            <motion.div
              key="welcome-screen"
              initial={{ opacity: 0, scale: 0.98 }}
              animate={{ opacity: 1, scale: 1 }}
              exit={{ opacity: 0, scale: 0.98 }}
              transition={{ duration: 0.25 }}
              className="w-full"
            >
              <WelcomeScreen />
            </motion.div>
          )}

          {activeScreen === 'scoreboard' && (
            <motion.div
              key="scoreboard-screen"
              initial={{ opacity: 0, scale: 0.98 }}
              animate={{ opacity: 1, scale: 1 }}
              exit={{ opacity: 0, scale: 0.98 }}
              transition={{ duration: 0.25 }}
              className="w-full"
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
              className="w-full flex flex-col lg:flex-row items-center lg:items-start gap-8 lg:gap-10 py-4"
            >
              {/* Left Column: Game Selector and Active Game Card */}
              <div className="flex-1 flex flex-col items-center gap-6 w-full">
                {/* Game Tabs */}
                <div className="flex flex-wrap items-center justify-center gap-2 md:gap-3 bg-purple-100/50 p-2 rounded-[2rem] border-4 border-purple-950 w-full max-w-2xl shadow-md">
                  {gameTabs.map((tab) => {
                    const isActive = activeGameIndex === tab.index;
                    return (
                      <button
                        key={tab.index}
                        onClick={() => setActiveGameIndex(tab.index)}
                        className={`flex-1 min-w-[140px] flex items-center justify-center gap-2 px-4 py-3 rounded-2xl font-black font-display text-sm md:text-base border-3 transition-all ${
                          isActive
                            ? 'bg-purple-600 text-white border-purple-950 shadow-playful scale-105'
                            : 'bg-white text-purple-950 border-purple-950/20 hover:bg-purple-50 hover:scale-102'
                        }`}
                      >
                        {tab.icon}
                        <span>{tab.label}</span>
                      </button>
                    );
                  })}
                </div>

                {/* Active Game Area */}
                <div className="w-full flex justify-center">
                  {activeGameIndex === 0 && <GameOne />}
                  {activeGameIndex === 1 && <GameTwo />}
                  {activeGameIndex === 2 && <GameThree />}
                </div>
              </div>

              {/* Right Column: Timer Sidebar */}
              <div className="lg:sticky lg:top-8 flex flex-col items-center gap-4 flex-shrink-0 w-full max-w-[280px]">
                <Timer />
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </main>

      {/* Navigation Footer */}
      <Footer />

      {/* Floating background decorative bubbles */}
      <div className="absolute top-1/4 left-10 w-32 h-32 bg-indigo-200/10 rounded-full blur-2xl pointer-events-none animate-float" />
      <div className="absolute bottom-1/4 right-10 w-44 h-44 bg-pink-200/10 rounded-full blur-3xl pointer-events-none animate-float-reverse" />
      <div className="absolute bottom-10 left-1/3 w-28 h-28 bg-yellow-200/10 rounded-full blur-xl pointer-events-none animate-float-slow" />
      
    </div>
  );
}
