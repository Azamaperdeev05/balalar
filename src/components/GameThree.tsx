'use client';

import React, { useState, useEffect } from 'react';
import { useGame } from '../context/GameContext';
import { motion, AnimatePresence } from 'framer-motion';
import { Layers, SkipForward, CheckCircle2, RotateCcw } from 'lucide-react';
import { soundManager } from '../utils/SoundManager';
import confetti from 'canvas-confetti';

export const GameThree: React.FC = () => {
  const { proverbChallenge, proverbStatus, setProverbStatus, nextProverb } = useGame();
  
  // Local state for dragging & reordering
  const [placedWords, setPlacedWords] = useState<string[]>([]);
  const [availableWords, setAvailableWords] = useState<string[]>(() => [...proverbChallenge.scrambled]);
  const [prevChallenge, setPrevChallenge] = useState(proverbChallenge);

  // Reset local state in render when challenge changes to avoid cascading effect renders
  if (proverbChallenge !== prevChallenge) {
    setPlacedWords([]);
    setAvailableWords([...proverbChallenge.scrambled]);
    setPrevChallenge(proverbChallenge);
  }

  // Get clean correct words for comparison
  const getCleanWords = (text: string) => {
    return text.replace(/[.—,–]/g, ' ').split(/\s+/).filter(Boolean);
  };

  const correctWords = getCleanWords(proverbChallenge.correct);

  // Clean word helper for matching
  const cleanWord = (w: string) => {
    return w.replace(/[.,\/#!$%\^&\*;:{}=\-_`~()?—–]/g, "").trim().toLowerCase();
  };

  // Check if current placedWords list matches the correct proverb
  useEffect(() => {
    if (placedWords.length === 0 || correctWords.length === 0) return;
    
    if (placedWords.length === correctWords.length) {
      const match = placedWords.every((w, idx) => cleanWord(w) === cleanWord(correctWords[idx]));
      
      if (match) {
        setProverbStatus('success');
        soundManager.playChime(); // Play success arpeggio chime
        confetti({
          particleCount: 150,
          spread: 80,
          origin: { y: 0.55 }
        });
      }
    }
  }, [placedWords, correctWords, setProverbStatus]);

  // Click-to-place actions
  const handleWordClick = (word: string, index: number, isPlaced: boolean) => {
    if (proverbStatus === 'success') return;
    soundManager.playPop();

    if (isPlaced) {
      // Remove from placed, add back to available
      const newPlaced = [...placedWords];
      newPlaced.splice(index, 1);
      setPlacedWords(newPlaced);
      setAvailableWords([...availableWords, word]);
    } else {
      // Remove from available, add to placed
      const newAvailable = [...availableWords];
      newAvailable.splice(index, 1);
      setAvailableWords(newAvailable);
      setPlacedWords([...placedWords, word]);
    }
  };

  // Reset current proverb attempt
  const handleResetAttempt = () => {
    soundManager.playPop();
    setPlacedWords([]);
    setAvailableWords([...proverbChallenge.scrambled]);
    setProverbStatus('playing');
  };

  // Drag and Drop implementation
  const handleDragStart = (e: React.DragEvent, word: string, index: number, source: 'available' | 'placed') => {
    e.dataTransfer.setData('text/plain', JSON.stringify({ word, index, source }));
  };

  const handleDragOver = (e: React.DragEvent) => {
    e.preventDefault();
  };

  const handleDropOnZone = (e: React.DragEvent) => {
    e.preventDefault();
    if (proverbStatus === 'success') return;

    try {
      const dataStr = e.dataTransfer.getData('text/plain');
      if (!dataStr) return;

      const { word, index, source } = JSON.parse(dataStr);
      if (source === 'available') {
        soundManager.playPop();
        // Remove from available
        const newAvailable = [...availableWords];
        newAvailable.splice(index, 1);
        setAvailableWords(newAvailable);
        // Append to placed
        setPlacedWords([...placedWords, word]);
      }
    } catch (err) {
      console.error('Drop handling failed', err);
    }
  };

  const handleDropOnAvailableZone = (e: React.DragEvent) => {
    e.preventDefault();
    if (proverbStatus === 'success') return;

    try {
      const dataStr = e.dataTransfer.getData('text/plain');
      if (!dataStr) return;

      const { word, index, source } = JSON.parse(dataStr);
      if (source === 'placed') {
        soundManager.playPop();
        // Remove from placed
        const newPlaced = [...placedWords];
        newPlaced.splice(index, 1);
        setPlacedWords(newPlaced);
        // Append to available
        setAvailableWords([...availableWords, word]);
      }
    } catch (err) {
      console.error('Drop handling failed', err);
    }
  };

  return (
    <div className="w-full max-w-4xl flex flex-col items-center gap-6 md:gap-8 p-4 z-10">
      
      <span className="text-sm md:text-base font-bold bg-indigo-600 border border-indigo-500 text-white px-5 py-2.5 rounded-full shadow-[0_0_15px_rgba(99,102,241,0.4)] tracking-wider uppercase font-display flex items-center gap-2 flex-shrink-0 rotate-[-1deg]">
        <Layers className="w-5 h-5 stroke-[2.5px]" />
        Мақалды құрастыр 🧩
      </span>

      {/* Main Game Card */}
      <motion.div
        key={proverbChallenge.correct}
        initial={{ scale: 0.95, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 12 }}
        className="w-full bg-zinc-900/40 backdrop-blur-xl border border-zinc-800 text-white rounded-2xl p-6 md:p-10 flex flex-col items-center justify-center shadow-xl relative overflow-hidden"
      >
        {/* Rules explanation */}
        <p className="text-xs md:text-sm font-semibold text-zinc-400 max-w-xl text-center mb-6 leading-relaxed">
          Төмендегі сөздерді сүйреп немесе ретімен басу арқылы мақал-мәтелді дұрыс құрастырыңдар!
        </p>

        {/* Dropped / Answer Display Board */}
        <div 
          onDragOver={handleDragOver}
          onDrop={handleDropOnZone}
          className={`w-full bg-zinc-950/60 border border-dashed border-zinc-800 rounded-2xl p-6 md:p-8 flex flex-wrap items-center justify-center gap-3 min-h-[140px] mb-6 shadow-inner transition-colors duration-300 ${
            proverbStatus === 'success' ? 'border-emerald-500 bg-emerald-500/10' : ''
          }`}
        >
          {placedWords.length === 0 && (
            <div className="text-xs md:text-sm font-bold text-zinc-600 uppercase tracking-wider font-display select-none">
              Сөздерді осында сүйреңіз немесе басыңыз
            </div>
          )}

          <AnimatePresence>
            {placedWords.map((word, idx) => (
              <motion.div
                key={`${word}-placed-${idx}`}
                initial={{ scale: 0.8, opacity: 0 }}
                animate={{ scale: 1, opacity: 1 }}
                exit={{ scale: 0.8, opacity: 0 }}
                draggable={proverbStatus !== 'success'}
                onDragStart={(e) => handleDragStart(e as unknown as React.DragEvent, word, idx, 'placed')}
                onClick={() => handleWordClick(word, idx, true)}
                className={`px-4 py-2.5 rounded-xl border font-bold font-display text-base cursor-pointer select-none transition-all active:scale-95 ${
                  proverbStatus === 'success'
                    ? 'bg-emerald-600 text-white border-emerald-500 shadow-none'
                    : 'bg-zinc-800 hover:bg-zinc-700 border-zinc-700 text-white'
                }`}
              >
                {word}
              </motion.div>
            ))}
          </AnimatePresence>
        </div>

        {/* Success message banner */}
        <AnimatePresence>
          {proverbStatus === 'success' && (
            <motion.div
              initial={{ scale: 0.8, opacity: 0 }}
              animate={{ scale: 1, opacity: 1 }}
              exit={{ scale: 0.8, opacity: 0 }}
              className="flex items-center gap-2 text-emerald-400 mb-6 bg-emerald-500/10 border border-emerald-500/30 px-5 py-2.5 rounded-xl font-bold font-display text-sm shadow-md"
            >
              <CheckCircle2 className="w-5 h-5 stroke-[2.5px] animate-bounce-gentle" />
              <span>Дұрыс! Тамаша құрастырдыңыз!</span>
            </motion.div>
          )}
        </AnimatePresence>

        {/* Available Scrambled Words Pool */}
        {proverbStatus !== 'success' && (
          <div 
            onDragOver={handleDragOver}
            onDrop={handleDropOnAvailableZone}
            className="w-full bg-zinc-950/40 border border-zinc-850 rounded-2xl p-6 md:p-8 flex flex-wrap items-center justify-center gap-3 min-h-[120px] mb-8 shadow-inner"
          >
            <AnimatePresence>
              {availableWords.map((word, idx) => (
                <motion.div
                  key={`${word}-avail-${idx}`}
                  initial={{ scale: 0.8, opacity: 0 }}
                  animate={{ scale: 1, opacity: 1 }}
                  exit={{ scale: 0.8, opacity: 0 }}
                  draggable
                  onDragStart={(e) => handleDragStart(e as unknown as React.DragEvent, word, idx, 'available')}
                  onClick={() => handleWordClick(word, idx, false)}
                  className="px-4 py-2.5 bg-zinc-900 hover:bg-zinc-800 border border-zinc-800 text-zinc-200 rounded-xl font-bold font-display text-base shadow-md cursor-pointer select-none transition-all hover:scale-105 active:scale-95 hover:text-white"
                >
                  {word}
                </motion.div>
              ))}
            </AnimatePresence>
          </div>
        )}

        {/* Action Controls */}
        <div className="flex items-center gap-4 w-full max-w-sm mt-2">
          {/* Reset attempt */}
          {proverbStatus !== 'success' && placedWords.length > 0 && (
            <button
              onClick={handleResetAttempt}
              className="flex-1 py-3 px-6 bg-zinc-950 hover:bg-zinc-900 border border-zinc-800 text-zinc-400 font-bold font-display text-sm tracking-wide rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-1.5"
            >
              <RotateCcw className="w-4 h-4 stroke-[2.5px]" />
              <span>ТАЗАРТУ</span>
            </button>
          )}

          {/* Load next proverb */}
          <button
            onClick={nextProverb}
            className="flex-1 py-3 px-6 bg-indigo-600 hover:bg-indigo-500 border border-indigo-500/50 text-white font-bold font-display text-sm tracking-wide rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2"
          >
            <SkipForward className="w-5 h-5 stroke-[2.5px]" />
            <span>КЕЛЕСІ ТАПСЫРМА</span>
          </button>
        </div>

      </motion.div>

    </div>
  );
};
