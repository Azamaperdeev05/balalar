'use client';

import React, { useEffect } from 'react';
import { useGame } from '../context/GameContext';
import { motion } from 'framer-motion';
import { RotateCcw, Home } from 'lucide-react';
import confetti from 'canvas-confetti';

export const WinnerScreen: React.FC = () => {
  const { scores, teamNames, resetScores, setActiveScreen } = useGame();

  const maxScore = Math.max(scores.team1, scores.team2, scores.team3);

  const teams: { id: 'team1' | 'team2' | 'team3'; emoji: string; gradient: string }[] = [
    { id: 'team1', emoji: '🦁', gradient: 'from-blue-400 to-indigo-600' },
    { id: 'team2', emoji: '🦊', gradient: 'from-pink-400 to-rose-600' },
    { id: 'team3', emoji: '🐼', gradient: 'from-emerald-400 to-teal-600' },
  ];

  const winners = teams.filter(t => scores[t.id] === maxScore);
  const isTie = winners.length > 1;

  // Sort teams by score descending for podium
  const ranked = [...teams].sort((a, b) => scores[b.id] - scores[a.id]);

  // Fire confetti on mount
  useEffect(() => {
    const fire = () => {
      confetti({ particleCount: 120, spread: 80, origin: { x: 0.3, y: 0.5 } });
      confetti({ particleCount: 120, spread: 80, origin: { x: 0.7, y: 0.5 } });
    };
    fire();
    const t1 = setTimeout(fire, 600);
    const t2 = setTimeout(fire, 1400);
    return () => { clearTimeout(t1); clearTimeout(t2); };
  }, []);

  const handlePlayAgain = () => {
    resetScores();
    setActiveScreen('scoreboard');
  };

  return (
    <div className="w-full min-h-screen flex flex-col items-center justify-center py-10 px-4 text-center select-none relative overflow-hidden">

      {/* Background glow */}
      <div className="pointer-events-none absolute inset-0 overflow-hidden">
        <div className="absolute top-1/4 left-1/2 -translate-x-1/2 w-[600px] h-[600px] rounded-full bg-gradient-to-br from-yellow-300/20 via-amber-400/10 to-transparent blur-3xl" />
      </div>

      {/* Trophy + title */}
      <motion.div
        initial={{ scale: 0, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 120, damping: 10 }}
        className="mb-8"
      >
        <div className="text-7xl md:text-8xl mb-4 animate-bounce-gentle">🏆</div>
        <h1 className="text-4xl md:text-6xl font-black font-display text-slate-900 dark:text-white tracking-tight">
          {isTie ? 'Тең нәтиже!' : 'Жеңімпаз!'}
        </h1>
        <p className="text-slate-500 dark:text-slate-400 font-semibold text-sm uppercase tracking-widest mt-2 font-display">
          Ойын аяқталды
        </p>
      </motion.div>

      {/* Winner highlight */}
      <motion.div
        initial={{ y: 30, opacity: 0 }}
        animate={{ y: 0, opacity: 1 }}
        transition={{ delay: 0.3 }}
        className="flex flex-wrap items-center justify-center gap-4 mb-10"
      >
        {winners.map((w) => (
          <div
            key={w.id}
            className={`flex flex-col items-center gap-3 bg-gradient-to-br ${w.gradient} rounded-3xl px-10 py-6 shadow-2xl`}
          >
            <span className="text-5xl">{w.emoji}</span>
            <p className="text-white font-black font-display text-2xl md:text-3xl drop-shadow-md">
              {teamNames[w.id]}
            </p>
            <p className="text-white/80 font-bold font-display text-lg">
              {scores[w.id]} ұпай
            </p>
          </div>
        ))}
      </motion.div>

      {/* Podium — all 3 teams ranked */}
      <motion.div
        initial={{ y: 40, opacity: 0 }}
        animate={{ y: 0, opacity: 1 }}
        transition={{ delay: 0.5 }}
        className="flex items-end justify-center gap-3 mb-12 w-full max-w-md"
      >
        {/* 2nd place */}
        <div className="flex flex-col items-center gap-2 flex-1">
          <span className="text-2xl">{ranked[1]?.emoji}</span>
          <p className="text-xs font-bold font-display text-slate-600 dark:text-slate-400 truncate max-w-[80px]">{teamNames[ranked[1]?.id]}</p>
          <div className="w-full bg-slate-200 dark:bg-slate-700 rounded-t-xl flex items-center justify-center h-16 text-slate-600 dark:text-slate-300 font-black font-display text-xl">
            {scores[ranked[1]?.id]}
          </div>
          <span className="text-xs font-bold text-slate-400 font-display">🥈 2-орын</span>
        </div>

        {/* 1st place */}
        <div className="flex flex-col items-center gap-2 flex-1">
          <span className="text-3xl">{ranked[0]?.emoji}</span>
          <p className="text-xs font-bold font-display text-slate-700 dark:text-slate-200 truncate max-w-[80px]">{teamNames[ranked[0]?.id]}</p>
          <div className="w-full bg-gradient-to-t from-amber-400 to-yellow-300 rounded-t-xl flex items-center justify-center h-28 text-slate-900 font-black font-display text-2xl shadow-lg">
            {scores[ranked[0]?.id]}
          </div>
          <span className="text-xs font-bold text-amber-500 font-display">🥇 1-орын</span>
        </div>

        {/* 3rd place */}
        <div className="flex flex-col items-center gap-2 flex-1">
          <span className="text-2xl">{ranked[2]?.emoji}</span>
          <p className="text-xs font-bold font-display text-slate-600 dark:text-slate-400 truncate max-w-[80px]">{teamNames[ranked[2]?.id]}</p>
          <div className="w-full bg-slate-200 dark:bg-slate-700 rounded-t-xl flex items-center justify-center h-10 text-slate-600 dark:text-slate-300 font-black font-display text-lg">
            {scores[ranked[2]?.id]}
          </div>
          <span className="text-xs font-bold text-slate-400 font-display">🥉 3-орын</span>
        </div>
      </motion.div>

      {/* Action buttons */}
      <motion.div
        initial={{ y: 20, opacity: 0 }}
        animate={{ y: 0, opacity: 1 }}
        transition={{ delay: 0.7 }}
        className="flex items-center gap-4 flex-wrap justify-center"
      >
        <button
          onClick={handlePlayAgain}
          className="px-8 py-3.5 bg-gradient-to-r from-indigo-600 to-violet-600 hover:from-indigo-500 hover:to-violet-500 text-white font-bold font-display text-sm uppercase tracking-wide rounded-2xl shadow-xl hover:scale-105 active:scale-95 transition-all flex items-center gap-2"
        >
          <RotateCcw className="w-4 h-4 stroke-[2.5px]" />
          ҚАЙТА ОЙНАУ
        </button>
        <button
          onClick={() => setActiveScreen('welcome')}
          className="px-6 py-3.5 bg-white dark:bg-slate-800 border border-slate-200 dark:border-slate-700 text-slate-600 dark:text-slate-300 font-bold font-display text-sm uppercase tracking-wide rounded-2xl shadow-md hover:scale-105 active:scale-95 transition-all flex items-center gap-2"
        >
          <Home className="w-4 h-4 stroke-[2.5px]" />
          БАСТЫ БЕТ
        </button>
      </motion.div>

    </div>
  );
};
