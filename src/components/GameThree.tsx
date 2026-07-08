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
  const [isError, setIsError] = useState<boolean>(false);

  // Reset local state in render when challenge changes to avoid cascading effect renders
  if (proverbChallenge !== prevChallenge) {
    setPlacedWords([]);
    setAvailableWords([...proverbChallenge.scrambled]);
    setIsError(false);
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

  // Synthesize a retro game buzzer/fail sound
  const playFailSound = () => {
    try {
      const AudioCtx = window.AudioContext || (window as any).webkitAudioContext;
      if (!AudioCtx) return;
      const ctx = new AudioCtx();
      const osc = ctx.createOscillator();
      const gain = ctx.createGain();
      osc.connect(gain);
      gain.connect(ctx.destination);
      
      const now = ctx.currentTime;
      osc.type = 'sawtooth';
      osc.frequency.setValueAtTime(180, now);
      osc.frequency.linearRampToValueAtTime(70, now + 0.35);
      
      gain.gain.setValueAtTime(0.2, now);
      gain.gain.exponentialRampToValueAtTime(0.001, now + 0.35);
      
      osc.start(now);
      osc.stop(now + 0.4);
    } catch (e) {
      console.warn('Fail audio synthesis failed', e);
    }
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
      } else {
        // Wrong order: trigger red glow, shake, buzzer sound
        setIsError(true);
        playFailSound();
        
        // Wait 1.2s, then shake back to normal and auto-reset/re-scramble words
        const timer = setTimeout(() => {
          setIsError(false);
          setPlacedWords([]);
          setAvailableWords([...proverbChallenge.scrambled]);
        }, 1200);
        
        return () => clearTimeout(timer);
      }
    }
  }, [placedWords, correctWords, setProverbStatus, proverbChallenge]);

  // Click-to-place actions
  const handleWordClick = (word: string, index: number, isPlaced: boolean) => {
    if (proverbStatus === 'success' || isError) return;
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
    if (isError) return;
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
    if (proverbStatus === 'success' || isError) return;

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
    if (proverbStatus === 'success' || isError) return;

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
    <div className="w-full max-w-4xl flex flex-col items-center gap-6 md:gap-8 p-4 z-10 select-none">
      
      <span className="text-xs md:text-sm font-semibold bg-slate-800 border border-slate-700 text-slate-300 px-5 py-2.5 rounded-xl uppercase tracking-wider font-display flex items-center gap-2 flex-shrink-0">
        <Layers className="w-5 h-5 stroke-[2.5px] text-indigo-400" />
        Мақалды құрастыр 🧩
      </span>

      {/* Main Game Card */}
      <motion.div
        key={proverbChallenge.correct}
        initial={{ scale: 0.98, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ type: 'spring', stiffness: 100, damping: 12 }}
        className="w-full bg-slate-800/80 border border-slate-700/50 text-white rounded-3xl p-6 md:p-10 flex flex-col items-center justify-center shadow-xl relative overflow-hidden"
      >
        {/* Rules explanation */}
        <p className="text-xs md:text-sm font-semibold text-slate-400 max-w-xl text-center mb-6 leading-relaxed">
          Төмендегі сөздерді сүйреп немесе ретімен басу арқылы мақал-мәтелді дұрыс құрастырыңдар!
        </p>

        {/* Dropped / Answer Display Board */}
        <motion.div 
          onDragOver={handleDragOver}
          onDrop={handleDropOnZone}
          animate={isError ? { x: [-8, 8, -8, 8, -4, 4, 0] } : {}}
          transition={{ duration: 0.5 }}
          className={`w-full bg-slate-950/60 border border-dashed rounded-2xl p-6 md:p-8 flex flex-wrap items-center justify-center gap-3 min-h-[140px] mb-6 shadow-inner transition-colors duration-300 ${
            proverbStatus === 'success' 
              ? 'border-emerald-500 bg-emerald-500/10' 
              : isError 
                ? 'border-rose-500 bg-rose-500/10 shadow-[0_0_15px_rgba(244,63,94,0.15)]' 
                : 'border-slate-850'
          }`}
        >
          {placedWords.length === 0 && (
            <div className="text-xs md:text-sm font-bold text-slate-650 uppercase tracking-wider font-display select-none">
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
                draggable={proverbStatus !== 'success' && !isError}
                onDragStart={(e) => handleDragStart(e as unknown as React.DragEvent, word, idx, 'placed')}
                onClick={() => handleWordClick(word, idx, true)}
                className={`px-4 py-2.5 rounded-xl border font-bold font-display text-base cursor-pointer select-none transition-all active:scale-95 ${
                  proverbStatus === 'success'
                    ? 'bg-emerald-650 text-white border-emerald-550 shadow-none'
                    : isError
                      ? 'bg-rose-950/80 border-rose-800 text-rose-200'
                      : 'bg-slate-900 hover:bg-slate-850 border-slate-800 text-white'
                }`}
              >
                {word}
              </motion.div>
            ))}
          </AnimatePresence>
        </motion.div>

        {/* Success message banner */}
        <AnimatePresence>
          {proverbStatus === 'success' && (
            <motion.div
              initial={{ scale: 0.8, opacity: 0 }}
              animate={{ scale: 1, opacity: 1 }}
              exit={{ scale: 0.8, opacity: 0 }}
              className="flex items-center gap-2 text-emerald-400 mb-6 bg-emerald-500/10 border border-emerald-500/30 px-5 py-2.5 rounded-xl font-bold font-display text-sm shadow-md"
            >
              <CheckCircle2 className="w-5 h-5 stroke-[2.5px] animate-bounce-gentle text-emerald-450" />
              <span>Дұрыс! Тамаша құрастырдыңыз!</span>
            </motion.div>
          )}
        </AnimatePresence>

        {/* Available Scrambled Words Pool */}
        {proverbStatus !== 'success' && (
          <div 
            onDragOver={handleDragOver}
            onDrop={handleDropOnAvailableZone}
            className="w-full bg-slate-950/40 border border-slate-850 rounded-2xl p-6 md:p-8 flex flex-wrap items-center justify-center gap-3 min-h-[120px] mb-8 shadow-inner"
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
                  className="px-4 py-2.5 bg-slate-900 hover:bg-slate-850 border border-slate-800 text-slate-300 rounded-xl font-bold font-display text-base shadow-md cursor-pointer select-none transition-all hover:scale-105 active:scale-95 hover:text-white"
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
              className="flex-1 py-3 px-6 bg-slate-950 hover:bg-slate-900 border border-slate-800 text-slate-450 font-bold font-display text-xs uppercase tracking-wider rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-1.5"
            >
              <RotateCcw className="w-4 h-4 stroke-[2.5px]" />
              <span>ТАЗАРТУ</span>
            </button>
          )}

          {/* Load next proverb */}
          <button
            onClick={nextProverb}
            className="flex-1 py-3.5 px-6 bg-gradient-to-r from-[#4F46E5] to-[#6366F1] border-indigo-500/30 text-white font-bold font-display text-sm uppercase tracking-wider rounded-xl shadow-lg hover:scale-105 active:scale-95 transition-all flex items-center justify-center gap-2 hover:shadow-[0_0_20px_rgba(99,102,241,0.25)]"
          >
            <SkipForward className="w-4 h-4 stroke-[2.5px]" />
            <span>КЕЛЕСІ ТАПСЫРМА</span>
          </button>
        </div>

      </motion.div>

    </div>
  );
};
