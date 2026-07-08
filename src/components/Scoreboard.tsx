'use client';

import React from 'react';
import { useGame } from '../context/GameContext';
import { motion, AnimatePresence } from 'framer-motion';
import { Trophy, RefreshCw, Plus } from 'lucide-react';

export const Scoreboard: React.FC = () => {
  const { scores, addScore, resetScores } = useGame();

  // Find the highest score to display the gold cup
  const maxScore = Math.max(scores.team1, scores.team2, scores.team3);
  const showCup = maxScore > 0;

  const teams: { id: 'team1' | 'team2' | 'team3'; label: string; score: number; bgColor: string; emoji: string }[] = [
    { id: 'team1', label: 'Команда 1', score: scores.team1, bgColor: 'from-blue-400 to-indigo-600', emoji: '🦁' },
    { id: 'team2', label: 'Команда 2', score: scores.team2, bgColor: 'from-pink-400 to-rose-600', emoji: '🦊' },
    { id: 'team3', label: 'Команда 3', score: scores.team3, bgColor: 'from-emerald-400 to-teal-600', emoji: '🐼' }
  ];

  return (
    <div className="w-full min-h-[75vh] flex flex-col items-center justify-center py-6 px-4 md:px-8 text-center select-none">
      
      {/* Page Header */}
      <motion.div
        initial={{ y: -20, opacity: 0 }}
        animate={{ y: 0, opacity: 1 }}
        className="max-w-xl flex flex-col items-center gap-2 mb-8 md:mb-12"
      >
        <span className="text-3xl md:text-5xl animate-bounce-gentle">🏆</span>
        <h1 className="text-4xl md:text-5xl font-black font-display tracking-wider text-white mt-2">
          Ұпайлар есебі
        </h1>
        <p className="text-xs md:text-sm font-semibold text-zinc-500 uppercase tracking-widest font-display">
          Командалардың жинаған ұпайлары
        </p>
      </motion.div>

      {/* Main Scoreboard Cards */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 md:gap-8 w-full max-w-5xl mb-12">
        {teams.map((team, idx) => {
          const isWinner = showCup && team.score === maxScore;

          return (
            <motion.div
              key={team.id}
              initial={{ scale: 0.9, opacity: 0 }}
              animate={{ scale: 1, opacity: 1 }}
              transition={{ type: 'spring', stiffness: 100, damping: 12, delay: idx * 0.1 }}
              className={`bg-zinc-900/40 backdrop-blur-xl border border-zinc-800 rounded-2xl p-6 md:p-8 flex flex-col items-center gap-6 shadow-xl relative transition-all duration-300 ${
                isWinner ? 'border-emerald-500/50 shadow-neon-success bg-zinc-900/60' : ''
              }`}
            >
              {/* Winner Cup Banner */}
              <AnimatePresence>
                {isWinner && (
                  <motion.div
                    initial={{ scale: 0, rotate: -5 }}
                    animate={{ scale: 1, rotate: 0 }}
                    exit={{ scale: 0 }}
                    className="absolute -top-4 bg-emerald-500 text-zinc-950 border border-emerald-400 px-3.5 py-1 rounded-xl flex items-center gap-1.5 font-bold font-display text-[10px] tracking-wider shadow-lg z-30 uppercase"
                  >
                    <Trophy className="w-3.5 h-3.5 stroke-[2.5px]" />
                    <span>КӨШБАСШЫ</span>
                  </motion.div>
                )}
              </AnimatePresence>

              {/* Team Icon Banner */}
              <div className={`w-16 h-16 rounded-xl bg-gradient-to-br ${team.bgColor} border border-zinc-800 flex items-center justify-center text-3xl shadow-md`}>
                {team.emoji}
              </div>

              {/* Team Name */}
              <h3 className="text-lg md:text-xl font-bold font-display text-white">
                {team.label}
              </h3>

              {/* Realtime Score counter */}
              <motion.div 
                key={team.score}
                initial={{ scale: 1.3, color: '#10b981' }}
                animate={{ scale: 1, color: '#ffffff' }}
                className="text-6xl md:text-7xl font-black font-display tracking-tight text-white select-none my-1"
              >
                {team.score}
              </motion.div>

              {/* Score Add buttons */}
              <div className="flex items-center gap-2 w-full mt-2">
                {[1, 2, 3].map((amount) => (
                  <button
                    key={amount}
                    onClick={() => addScore(team.id, amount)}
                    className="flex-1 py-2 bg-zinc-950 hover:bg-zinc-800 hover:text-white border border-zinc-800 text-zinc-400 rounded-xl font-bold font-display text-sm shadow-md hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-0.5"
                  >
                    <Plus className="w-3 h-3 stroke-[2.5px]" />
                    <span>{amount}</span>
                  </button>
                ))}
              </div>
            </motion.div>
          );
        })}
      </div>

      {/* Reset Scores action */}
      <motion.div
        initial={{ y: 20, opacity: 0 }}
        animate={{ y: 0, opacity: 1 }}
        transition={{ delay: 0.4 }}
      >
        <button
          onClick={resetScores}
          className="px-5 py-3 bg-zinc-950 hover:bg-zinc-900 border border-zinc-850 hover:border-zinc-800 text-zinc-500 hover:text-rose-400 font-bold font-display tracking-wider rounded-xl shadow-md hover:scale-103 active:scale-95 transition-all flex items-center gap-2 text-xs uppercase"
        >
          <RefreshCw className="w-3.5 h-3.5 stroke-[2.5px]" />
          <span>ЕСЕПТІ НӨЛДЕУ</span>
        </button>
      </motion.div>

    </div>
  );
};
