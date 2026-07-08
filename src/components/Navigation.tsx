'use client';

import React, { useState, useEffect } from 'react';
import { useGame, ScreenType } from '../context/GameContext';
import { Volume2, VolumeX, Music, Home, Trophy, Gamepad2, Sun, Moon, Maximize2, Minimize2 } from 'lucide-react';

export const Header: React.FC = () => {
  const { 
    activeScreen, 
    setActiveScreen, 
    audioEnabled, 
    setAudioEnabled,
    musicTheme,
    setMusicTheme,
    theme,
    setTheme
  } = useGame();

  const handleNavClick = (screen: ScreenType) => {
    setActiveScreen(screen);
  };

  // Fullscreen state
  const [isFullscreen, setIsFullscreen] = useState(false);

  useEffect(() => {
    const onChange = () => setIsFullscreen(!!document.fullscreenElement);
    document.addEventListener('fullscreenchange', onChange);
    return () => document.removeEventListener('fullscreenchange', onChange);
  }, []);

  const toggleFullscreen = () => {
    if (!document.fullscreenElement) {
      document.documentElement.requestFullscreen();
    } else {
      document.exitFullscreen();
    }
  };

  const navItems = [
    { id: 'welcome', label: 'Басты бет', icon: <Home className="w-5 h-5" /> },
    { id: 'games', label: 'Ойындар', icon: <Gamepad2 className="w-5 h-5" /> },
    { id: 'scoreboard', label: 'Ұпай', icon: <Trophy className="w-5 h-5" /> }
  ];

  return (
    <header className="w-full bg-white/80 dark:bg-slate-900/80 backdrop-blur-xl border-b border-slate-200 dark:border-slate-800 px-4 md:px-8 py-3.5 md:py-4 flex flex-col sm:flex-row items-center justify-between gap-4 z-40 relative shadow-md dark:shadow-xl transition-colors">
      {/* Brand logo */}
      <div 
        onClick={() => handleNavClick('welcome')}
        className="flex items-center gap-2.5 cursor-pointer group select-none"
      >
        <span className="text-3xl md:text-4xl animate-bounce-gentle">📖</span>
        <span className="text-2xl md:text-3xl font-extrabold font-display tracking-tight bg-gradient-to-r from-[#4F46E5] to-[#06B6D4] bg-clip-text text-transparent group-hover:opacity-90 transition-opacity">
          BOOK GAMES
        </span>
      </div>

      {/* Navigation menu */}
      <nav className="flex items-center gap-1 md:gap-1.5 bg-slate-100 dark:bg-slate-950 p-1.5 rounded-2xl border border-slate-200 dark:border-slate-800 transition-colors">
        {navItems.map((item) => {
          const isActive = activeScreen === item.id;
          return (
            <button
              key={item.id}
              onClick={() => handleNavClick(item.id as ScreenType)}
              className={`flex items-center gap-1.5 md:gap-2 px-4 md:px-6 py-2.5 rounded-xl text-sm md:text-base font-bold font-display tracking-wide transition-all ${
                isActive 
                  ? 'bg-gradient-to-r from-[#4F46E5] to-[#6366F1] text-white shadow-[0_0_20px_rgba(99,102,241,0.3)]' 
                  : 'text-slate-600 dark:text-slate-400 hover:text-slate-900 dark:hover:text-slate-200 hover:bg-slate-200 dark:hover:bg-slate-800 hover:scale-103'
              }`}
            >
              {item.icon}
              <span className="hidden xs:inline">{item.label}</span>
            </button>
          );
        })}
      </nav>

      {/* Sound & Settings controls */}
      <div className="flex items-center gap-2">
        {/* Fullscreen toggle */}
        <button
          onClick={toggleFullscreen}
          className="p-2.5 rounded-xl border transition-all active:scale-95 bg-slate-100 text-slate-600 border-slate-200 dark:bg-slate-850 dark:text-slate-500 dark:border-slate-800 hover:bg-slate-200 dark:hover:bg-slate-800"
          aria-label="Толық экран"
          title={isFullscreen ? 'Толық экраннан шығу' : 'Толық экран'}
        >
          {isFullscreen
            ? <Minimize2 className="w-5 h-5 stroke-[2.5px]" />
            : <Maximize2 className="w-5 h-5 stroke-[2.5px]" />}
        </button>

        {/* Toggle Theme */}
        <button
          onClick={() => setTheme(theme === 'dark' ? 'light' : 'dark')}
          className={`p-2.5 rounded-xl border transition-all active:scale-95 bg-slate-100 text-slate-600 border-slate-200 dark:bg-slate-850 dark:text-slate-500 dark:border-slate-800 hover:bg-slate-200 dark:hover:bg-slate-800`}
          aria-label="Теманы ауыстыру"
          title={theme === 'dark' ? 'Жарық тема' : 'Қараңғы тема'}
        >
          {theme === 'dark' ? <Sun className="w-5 h-5 stroke-[2.5px]" /> : <Moon className="w-5 h-5 stroke-[2.5px]" />}
        </button>

        {/* Toggle Sound */}
        <button
          onClick={() => setAudioEnabled(!audioEnabled)}
          className={`p-2.5 rounded-xl border transition-all active:scale-95 ${
            audioEnabled 
              ? 'bg-gradient-to-r from-[#4F46E5] to-[#6366F1] border-indigo-500/30 text-white shadow-[0_0_15px_rgba(99,102,241,0.3)]' 
              : 'bg-slate-100 text-slate-600 border-slate-200 dark:bg-slate-850 dark:text-slate-500 dark:border-slate-800'
          }`}
          aria-label="Дыбысты қосу/өшіру"
          title={audioEnabled ? "Дыбысты өшіру" : "Дыбысты қосу"}
        >
          {audioEnabled ? <Volume2 className="w-5 h-5 stroke-[2.5px]" /> : <VolumeX className="w-5 h-5" />}
        </button>

        {/* Music theme select */}
        {audioEnabled && (
          <div className="relative group">
            <button 
              className="p-2.5 rounded-xl bg-slate-100 text-slate-600 border border-slate-200 dark:bg-slate-800 dark:text-slate-200 dark:border-slate-700 flex items-center gap-1.5 hover:bg-slate-200 dark:hover:bg-slate-700 transition-colors shadow-sm dark:shadow-md"
              title="Музыка түрін таңдау"
            >
              <Music className="w-5 h-5 stroke-[2.5px]" />
              <span className="text-xs font-bold font-display hidden lg:inline uppercase">
                {musicTheme === 'cheerful' ? 'Көңілді' : musicTheme === 'calm' ? 'Қоңыр' : 'Ретро'}
              </span>
            </button>
            
            {/* Dropdown Menu */}
            <div className="absolute right-0 mt-2 w-36 bg-white dark:bg-slate-900 border border-slate-200 dark:border-slate-800 rounded-2xl shadow-xl dark:shadow-2xl overflow-hidden opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-200 z-50">
              <button
                onClick={() => setMusicTheme('cheerful')}
                className={`w-full text-left px-4 py-2.5 text-xs font-bold font-display hover:bg-slate-100 dark:hover:bg-slate-800 ${musicTheme === 'cheerful' ? 'bg-indigo-50 dark:bg-indigo-600 text-indigo-700 dark:text-white' : 'text-slate-600 dark:text-slate-350'}`}
              >
                🥳 Көңілді
              </button>
              <button
                onClick={() => setMusicTheme('calm')}
                className={`w-full text-left px-4 py-2.5 text-xs font-bold font-display hover:bg-slate-100 dark:hover:bg-slate-800 ${musicTheme === 'calm' ? 'bg-indigo-50 dark:bg-indigo-600 text-indigo-700 dark:text-white' : 'text-slate-600 dark:text-slate-350'}`}
              >
                🍃 Қоңыр
              </button>
              <button
                onClick={() => setMusicTheme('retro')}
                className={`w-full text-left px-4 py-2.5 text-xs font-bold font-display hover:bg-slate-100 dark:hover:bg-slate-800 ${musicTheme === 'retro' ? 'bg-indigo-50 dark:bg-indigo-600 text-indigo-700 dark:text-white' : 'text-slate-600 dark:text-slate-350'}`}
              >
                👾 Ретро
              </button>
            </div>
          </div>
        )}
      </div>
    </header>
  );
};

export const Footer: React.FC = () => {
  return (
    <footer className="w-full bg-slate-100 dark:bg-slate-950 border-t border-slate-200 dark:border-slate-900 py-4.5 text-center z-40 relative transition-colors">
      <span className="text-xs md:text-sm font-semibold font-display tracking-wider text-slate-500 dark:text-slate-500 block">
        🎮 BOOK GAMES — Жасөспірімдерге арналған кітап ойындары платформасы
      </span>
    </footer>
  );
};
