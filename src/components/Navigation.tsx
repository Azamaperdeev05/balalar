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
    <header className="w-full bg-white border-b-4 border-purple-950 px-4 md:px-8 py-3 md:py-4 flex flex-col sm:flex-row items-center justify-between gap-4 z-40 relative shadow-md">
      {/* Brand logo */}
      <div 
        onClick={() => handleNavClick('welcome')}
        className="flex items-center gap-2 cursor-pointer group select-none"
      >
        <span className="text-3xl md:text-4xl animate-bounce-gentle">📚</span>
        <span className="text-2xl md:text-3xl font-black font-display tracking-wider text-purple-950 group-hover:text-purple-600 transition-colors">
          BOOK GAMES
        </span>
      </div>

      {/* Navigation menu */}
      <nav className="flex items-center gap-1 md:gap-3 bg-purple-50 p-1.5 rounded-2xl border-2 border-purple-950/20">
        {navItems.map((item) => {
          const isActive = activeScreen === item.id;
          return (
            <button
              key={item.id}
              onClick={() => handleNavClick(item.id as ScreenType)}
              className={`flex items-center gap-1.5 md:gap-2 px-3 md:px-5 py-2 rounded-xl text-sm md:text-base font-black font-display tracking-wide transition-all ${
                isActive 
                  ? 'bg-purple-600 text-white shadow-playful border-2 border-purple-950' 
                  : 'text-purple-950 hover:bg-purple-100 hover:scale-105'
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
          className={`p-2.5 rounded-xl border-2 border-purple-950 shadow-playful transition-transform active:scale-95 ${
            audioEnabled 
              ? 'bg-yellow-400 text-purple-950 hover:bg-yellow-300' 
              : 'bg-gray-200 text-gray-500 border-gray-400 shadow-none'
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
              className="p-2.5 rounded-xl bg-purple-100 text-purple-950 border-2 border-purple-950 shadow-playful flex items-center gap-1.5 hover:bg-purple-200 transition-colors"
              title="Музыка түрін таңдау"
            >
              <Music className="w-5 h-5 stroke-[2.5px]" />
              <span className="text-xs font-black font-display hidden lg:inline uppercase">
                {musicTheme === 'cheerful' ? 'Көңілді' : musicTheme === 'calm' ? 'Қоңыр' : 'Ретро'}
              </span>
            </button>
            
            {/* Dropdown Menu */}
            <div className="absolute right-0 mt-2 w-36 bg-white border-2 border-purple-950 rounded-2xl shadow-xl overflow-hidden opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-200 z-50">
              <button
                onClick={() => setMusicTheme('cheerful')}
                className={`w-full text-left px-4 py-2 text-xs font-black font-display hover:bg-purple-50 ${musicTheme === 'cheerful' ? 'bg-purple-100 text-purple-900' : 'text-purple-950'}`}
              >
                🥳 Көңілді
              </button>
              <button
                onClick={() => setMusicTheme('calm')}
                className={`w-full text-left px-4 py-2 text-xs font-black font-display hover:bg-purple-50 ${musicTheme === 'calm' ? 'bg-purple-100 text-purple-900' : 'text-purple-950'}`}
              >
                🍃 Қоңыр
              </button>
              <button
                onClick={() => setMusicTheme('retro')}
                className={`w-full text-left px-4 py-2 text-xs font-black font-display hover:bg-purple-50 ${musicTheme === 'retro' ? 'bg-purple-100 text-purple-900' : 'text-purple-950'}`}
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
    <footer className="w-full bg-white border-t-4 border-purple-950 py-3 text-center z-40 relative">
      <span className="text-base md:text-lg font-black font-display tracking-wider text-purple-950 animate-pulse-slow block">
        ✨ &quot;Жақсы ойын — жақсы көңіл күй.&quot; ✨
      </span>
    </footer>
  );
};
