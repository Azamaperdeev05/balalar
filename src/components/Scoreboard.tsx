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
        <h1 className="text-4xl md:text-6xl font-black font-display tracking-wider text-stroke-playful mt-2">
          Ұпайлар есебі
        </h1>
        <p className="text-sm md:text-base font-bold text-purple-900/60 uppercase tracking-widest font-display">
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
              className={`bg-white border-8 border-purple-950 rounded-[3rem] p-6 md:p-8 flex flex-col items-center gap-6 shadow-playful relative transition-all duration-300 ${
                isWinner ? 'ring-8 ring-yellow-400 border-yellow-400 bg-yellow-50/20' : ''
              }`}
            >
              {/* Winner Cup Banner */}
              <AnimatePresence>
                {isWinner && (
                  <motion.div
                    initial={{ scale: 0, rotate: -20 }}
                    animate={{ scale: 1, rotate: 0 }}
                    exit={{ scale: 0 }}
                    className="absolute -top-10 bg-yellow-400 text-purple-950 border-4 border-purple-950 px-4 py-1.5 rounded-2xl flex items-center gap-1.5 font-black font-display text-sm shadow-playful z-30 animate-pulse-slow"
                  >
                    <Trophy className="w-5 h-5 fill-yellow-100 stroke-[2.5px]" />
                    <span>КӨШБАСШЫ</span>
                  </motion.div>
                )}
              </AnimatePresence>

              {/* Team Icon Banner */}
              <div className={`w-20 h-20 rounded-2xl bg-gradient-to-br ${team.bgColor} border-4 border-purple-950 flex items-center justify-center text-4xl shadow-md`}>
                {team.emoji}
              </div>

              {/* Team Name */}
              <h3 className="text-2xl md:text-3xl font-black font-display text-purple-950">
                {team.label}
              </h3>

              {/* Realtime Score counter */}
              <motion.div 
                key={team.score}
                initial={{ scale: 1.5, color: '#facc15' }}
                animate={{ scale: 1, color: '#1e1b4b' }}
                className="text-6xl md:text-8xl font-black font-display tracking-tight text-purple-950 select-none my-2 drop-shadow-sm"
              >
                {team.score}
              </motion.div>

              {/* Score Add buttons */}
              <div className="flex items-center gap-2 w-full mt-2">
                {[1, 2, 3].map((amount) => (
                  <button
                    key={amount}
                    onClick={() => addScore(team.id, amount)}
                    className="flex-1 py-3 bg-purple-600 hover:bg-purple-500 text-white rounded-2xl border-4 border-purple-950 font-black font-display text-lg md:text-xl shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-0.5"
                  >
                    <Plus className="w-4 h-4 stroke-[4px]" />
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
          className="px-6 py-4 bg-rose-500 hover:bg-rose-400 text-white font-black font-display tracking-wider border-4 border-purple-950 rounded-2xl shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center gap-2"
        >
          <RefreshCw className="w-5 h-5 stroke-[3px]" />
          <span>ЕСЕПТІ НӨЛДЕУ</span>
        </button>
      </motion.div>

    </div>
  );
};
