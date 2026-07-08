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
  const [availableWords, setAvailableWords] = useState<string[]>([]);
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
      
      {/* Game Badge */}
      <span className="text-lg md:text-xl font-bold bg-white/20 px-6 py-2.5 rounded-full border-2 border-white/40 tracking-widest uppercase font-display flex items-center gap-2 flex-shrink-0 text-white shadow-sm">
        <Layers className="w-5 h-5 stroke-[3px]" />
        Мақалды құрастыр 🧩
      </span>

      {/* Main Game Card */}
      <motion.div
        key={proverbChallenge.correct}
        initial={{ scale: 0.95, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 12 }}
        className="w-full bg-white border-8 border-purple-950 text-purple-950 rounded-[3rem] p-6 md:p-10 flex flex-col items-center justify-center shadow-playful relative overflow-hidden"
      >
        {/* Rules explanation */}
        <p className="text-sm md:text-base font-bold text-purple-900/60 max-w-xl text-center mb-6 leading-relaxed">
          Төмендегі сөздерді сүйреп немесе ретімен басу арқылы мақал-мәтелді дұрыс құрастырыңдар!
        </p>

        {/* Dropped / Answer Display Board */}
        <div 
          onDragOver={handleDragOver}
          onDrop={handleDropOnZone}
          className={`w-full bg-purple-50 border-4 border-dashed border-purple-950/20 rounded-[2.2rem] p-6 md:p-8 flex flex-wrap items-center justify-center gap-3 min-h-[140px] mb-6 shadow-inner transition-colors duration-300 ${
            proverbStatus === 'success' ? 'border-emerald-500 bg-emerald-50/50' : ''
          }`}
        >
          {placedWords.length === 0 && (
            <div className="text-sm md:text-base font-bold text-purple-950/30 uppercase tracking-wider font-display select-none">
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
                className={`px-5 py-3 rounded-2xl border-3 border-purple-950 font-black font-display text-lg md:text-xl shadow-playful cursor-pointer select-none transition-transform active:scale-95 ${
                  proverbStatus === 'success'
                    ? 'bg-emerald-500 text-white border-emerald-600 shadow-none'
                    : 'bg-yellow-400 hover:bg-yellow-300 text-purple-950'
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
              className="flex items-center gap-2 text-emerald-600 mb-6 bg-emerald-50 border-3 border-emerald-500 px-6 py-3 rounded-2xl font-black font-display text-lg md:text-xl shadow-md"
            >
              <CheckCircle2 className="w-6 h-6 stroke-[3px] animate-bounce-gentle" />
              <span>✅ Дұрыс! Тамаша құрастырдыңыз!</span>
            </motion.div>
          )}
        </AnimatePresence>

        {/* Available Scrambled Words Pool */}
        {proverbStatus !== 'success' && (
          <div 
            onDragOver={handleDragOver}
            onDrop={handleDropOnAvailableZone}
            className="w-full bg-gray-50 border-4 border-purple-950/10 rounded-[2.2rem] p-6 md:p-8 flex flex-wrap items-center justify-center gap-3 min-h-[120px] mb-8 shadow-inner"
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
                  className="px-5 py-3 bg-white hover:bg-purple-100 text-purple-950 rounded-2xl border-3 border-purple-950 font-black font-display text-lg md:text-xl shadow-playful cursor-pointer select-none transition-all hover:scale-105 active:scale-95"
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
              className="flex-1 py-4 px-6 bg-gray-200 hover:bg-gray-300 text-purple-950 font-black font-display text-base md:text-lg rounded-2xl border-4 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2"
            >
              <RotateCcw className="w-5 h-5 stroke-[3px]" />
              <span>ТАЗАРТУ</span>
            </button>
          )}

          {/* Load next proverb */}
          <button
            onClick={nextProverb}
            className="flex-1 py-4 px-6 bg-emerald-500 hover:bg-emerald-400 text-white font-black font-display text-lg md:text-xl rounded-2xl border-4 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2"
          >
            <SkipForward className="w-6 h-6 stroke-[3px]" />
            <span>КЕЛЕСІ ТАПСЫРМА</span>
          </button>
        </div>

      </motion.div>

    </div>
  );
};
