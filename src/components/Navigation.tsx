'use client';

import React from 'react';
import { useGame, ScreenType } from '../context/GameContext';
import { Volume2, VolumeX, Music, Home, Trophy, Gamepad2 } from 'lucide-react';

export const Header: React.FC = () => {
  const { 
    activeScreen, 
    setActiveScreen, 
    audioEnabled, 
    setAudioEnabled,
    musicTheme,
    setMusicTheme
  } = useGame();

  const handleNavClick = (screen: ScreenType) => {
    setActiveScreen(screen);
  };

  const navItems = [
    { id: 'welcome', label: 'Басты бет', icon: <Home className="w-5 h-5" /> },
    { id: 'games', label: 'Ойындар', icon: <Gamepad2 className="w-5 h-5" /> },
    { id: 'scoreboard', label: 'Ұпай', icon: <Trophy className="w-5 h-5" /> }
  ];

  return (
    <header className="w-full bg-slate-900/60 backdrop-blur-xl border-b border-white/10 px-4 md:px-8 py-3.5 md:py-4 flex flex-col sm:flex-row items-center justify-between gap-4 z-40 relative shadow-2xl">
      {/* Brand logo */}
      <div 
        onClick={() => handleNavClick('welcome')}
        className="flex items-center gap-2 cursor-pointer group select-none"
      >
        <span className="text-3xl md:text-4xl animate-bounce-gentle">📚</span>
        <span className="text-2xl md:text-3xl font-black font-display tracking-wider text-gaming-gradient group-hover:opacity-80 transition-opacity">
          BOOK GAMES
        </span>
      </div>

      {/* Navigation menu */}
      <nav className="flex items-center gap-1 md:gap-2 bg-slate-950/60 p-1 rounded-2xl border border-white/10">
        {navItems.map((item) => {
          const isActive = activeScreen === item.id;
          return (
            <button
              key={item.id}
              onClick={() => handleNavClick(item.id as ScreenType)}
              className={`flex items-center gap-1.5 md:gap-2 px-3 md:px-5 py-2.5 rounded-xl text-sm md:text-base font-black font-display tracking-wide transition-all ${
                isActive 
                  ? 'bg-indigo-600 text-white shadow-[0_0_15px_rgba(99,102,241,0.4)]' 
                  : 'text-slate-400 hover:text-white hover:bg-white/5 hover:scale-105'
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
        {/* Toggle Sound */}
        <button
          onClick={() => setAudioEnabled(!audioEnabled)}
          className={`p-2.5 rounded-xl border transition-all active:scale-95 ${
            audioEnabled 
              ? 'bg-indigo-600 border-indigo-500 text-white shadow-[0_0_15px_rgba(99,102,241,0.4)] hover:bg-indigo-500' 
              : 'bg-slate-800 text-slate-500 border-white/5 shadow-none'
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
              className="p-2.5 rounded-xl bg-slate-800 text-slate-200 border border-white/10 flex items-center gap-1.5 hover:bg-slate-700 transition-colors shadow-md"
              title="Музыка түрін таңдау"
            >
              <Music className="w-5 h-5 stroke-[2.5px]" />
              <span className="text-xs font-black font-display hidden lg:inline uppercase">
                {musicTheme === 'cheerful' ? 'Көңілді' : musicTheme === 'calm' ? 'Қоңыр' : 'Ретро'}
              </span>
            </button>
            
            {/* Dropdown Menu */}
            <div className="absolute right-0 mt-2 w-36 bg-slate-900 border border-white/10 rounded-2xl shadow-2xl overflow-hidden opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-200 z-50">
              <button
                onClick={() => setMusicTheme('cheerful')}
                className={`w-full text-left px-4 py-2.5 text-xs font-black font-display hover:bg-white/5 ${musicTheme === 'cheerful' ? 'bg-indigo-600 text-white' : 'text-slate-300'}`}
              >
                🥳 Көңілді
              </button>
              <button
                onClick={() => setMusicTheme('calm')}
                className={`w-full text-left px-4 py-2.5 text-xs font-black font-display hover:bg-white/5 ${musicTheme === 'calm' ? 'bg-indigo-600 text-white' : 'text-slate-300'}`}
              >
                🍃 Қоңыр
              </button>
              <button
                onClick={() => setMusicTheme('retro')}
                className={`w-full text-left px-4 py-2.5 text-xs font-black font-display hover:bg-white/5 ${musicTheme === 'retro' ? 'bg-indigo-600 text-white' : 'text-slate-300'}`}
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
    <footer className="w-full bg-slate-950 border-t border-white/5 py-4 text-center z-40 relative">
      <span className="text-base font-black font-display tracking-wider text-slate-500 block">
        ✨ &quot;Жақсы ойын — жақсы көңіл күй.&quot; ✨
      </span>
    </footer>
  );
};
