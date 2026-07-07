import React, { useEffect, useRef } from 'react';
import { motion } from 'framer-motion';
import confetti from 'canvas-confetti';
import { Illustration } from '../Illustration';
import { soundManager } from '../../utils/SoundManager';
import { RotateCcw } from 'lucide-react';

interface FinishScreenProps {
  onRestart: () => void;
  animationsEnabled?: boolean;
}

export const FinishScreen: React.FC<FinishScreenProps> = ({
  onRestart,
  animationsEnabled = true,
}) => {
  const canvasRef = useRef<HTMLCanvasElement | null>(null);

  useEffect(() => {
    // Play the victory applause sound
    soundManager.playApplause();

    // Trigger massive canvas-confetti bursts
    if (animationsEnabled) {
      const end = Date.now() + 6 * 1000; // 6 seconds of confetti

      const frame = () => {
        confetti({
          particleCount: 5,
          angle: 60,
          spread: 55,
          origin: { x: 0 },
          colors: ['#facc15', '#f97316', '#3b82f6', '#10b981', '#a855f7'],
        });
        confetti({
          particleCount: 5,
          angle: 120,
          spread: 55,
          origin: { x: 1 },
          colors: ['#facc15', '#f97316', '#3b82f6', '#10b981', '#a855f7'],
        });

        if (Date.now() < end) {
          requestAnimationFrame(frame);
        }
      };

      frame();
    }
  }, [animationsEnabled]);

  // HTML5 Canvas Fireworks Animation
  useEffect(() => {
    if (!animationsEnabled || !canvasRef.current) return;

    const canvas = canvasRef.current;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    let animationFrameId: number;
    let width = (canvas.width = window.innerWidth);
    let height = (canvas.height = window.innerHeight);

    // Handle resizing
    const handleResize = () => {
      width = canvas.width = window.innerWidth;
      height = canvas.height = window.innerHeight;
    };
    window.addEventListener('resize', handleResize);

    // Particle class for fireworks
    class Particle {
      x: number;
      y: number;
      vx: number;
      vy: number;
      color: string;
      alpha: number;
      decay: number;
      gravity: number;

      constructor(x: number, y: number, color: string) {
        this.x = x;
        this.y = y;
        const angle = Math.random() * Math.PI * 2;
        const speed = Math.random() * 6 + 2;
        this.vx = Math.cos(angle) * speed;
        this.vy = Math.sin(angle) * speed;
        this.color = color;
        this.alpha = 1;
        this.decay = Math.random() * 0.015 + 0.01;
        this.gravity = 0.06;
      }

      update() {
        this.vy += this.gravity;
        this.x += this.vx;
        this.y += this.vy;
        this.alpha -= this.decay;
      }

      draw(c: CanvasRenderingContext2D) {
        c.save();
        c.globalAlpha = this.alpha;
        c.beginPath();
        c.arc(this.x, this.y, 4, 0, Math.PI * 2);
        c.fillStyle = this.color;
        c.shadowBlur = 10;
        c.shadowColor = this.color;
        c.fill();
        c.restore();
      }
    }

    // Firework shell class
    class Firework {
      x: number;
      y: number;
      targetY: number;
      vy: number;
      color: string;
      exploded: boolean;

      constructor() {
        this.x = Math.random() * width;
        this.y = height;
        this.targetY = Math.random() * (height * 0.4) + height * 0.15;
        this.vy = -(Math.random() * 5 + 8);
        const colors = ['#facc15', '#f97316', '#3b82f6', '#10b981', '#a855f7', '#ec4899'];
        this.color = colors[Math.floor(Math.random() * colors.length)];
        this.exploded = false;
      }

      update() {
        this.y += this.vy;
        if (this.vy < 0) {
          this.vy += 0.05; // decelerate upward
        }
        if (this.y <= this.targetY) {
          this.exploded = true;
        }
      }

      draw(c: CanvasRenderingContext2D) {
        c.save();
        c.beginPath();
        c.arc(this.x, this.y, 3, 0, Math.PI * 2);
        c.fillStyle = this.color;
        c.fill();
        c.restore();
      }
    }

    let fireworks: Firework[] = [];
    let particles: Particle[] = [];

    const animate = () => {
      // Semi-transparent clearing for trails
      ctx.fillStyle = 'rgba(254, 252, 240, 0.2)'; // matching bg-color but with transparency
      ctx.fillRect(0, 0, width, height);

      // Randomly spawn new fireworks
      if (Math.random() < 0.04 && fireworks.length < 5) {
        fireworks.push(new Firework());
      }

      // Update and draw fireworks
      fireworks = fireworks.filter((fw) => {
        fw.update();
        if (fw.exploded) {
          // Spawn explosion particles
          for (let i = 0; i < 40; i++) {
            particles.push(new Particle(fw.x, fw.y, fw.color));
          }
          return false;
        }
        fw.draw(ctx);
        return true;
      });

      // Update and draw particles
      particles = particles.filter((p) => {
        p.update();
        p.draw(ctx);
        return p.alpha > 0;
      });

      animationFrameId = requestAnimationFrame(animate);
    };

    animate();

    return () => {
      window.removeEventListener('resize', handleResize);
      cancelAnimationFrame(animationFrameId);
    };
  }, [animationsEnabled]);

  return (
    <div className="w-full h-full bg-[#fefcf0] flex flex-col items-center justify-center p-4 sm:p-8 md:p-12 text-center relative overflow-y-auto select-none py-8 sm:py-12">
      
      {/* Canvas for Fireworks */}
      <canvas ref={canvasRef} className="absolute inset-0 w-full h-full pointer-events-none z-0" />

      {/* Main Container */}
      <div className="max-w-4xl flex flex-col items-center gap-4 sm:gap-6 md:gap-8 z-10 my-auto w-full">
        
        {/* Massive Celebration Badge */}
        <motion.div
          initial={animationsEnabled ? { scale: 0, rotate: -20 } : {}}
          animate={animationsEnabled ? { scale: 1, rotate: 0 } : {}}
          transition={{ type: 'spring', stiffness: 120, damping: 10 }}
          className="text-5xl sm:text-7xl md:text-8xl mb-1 flex-shrink-0"
        >
          🎉
        </motion.div>

        {/* Victory Trophy Illustration */}
        <motion.div
          initial={animationsEnabled ? { y: 100, opacity: 0 } : {}}
          animate={animationsEnabled ? { y: 0, opacity: 1 } : {}}
          transition={{ type: 'spring', stiffness: 80, damping: 12, delay: 0.2 }}
          className="w-36 h-36 sm:w-52 sm:h-52 md:w-64 md:h-64 max-h-[22vh] max-w-[22vh] aspect-square bg-yellow-100/60 rounded-[2.5rem] border-4 border-yellow-300 flex items-center justify-center shadow-lg p-4 relative flex-shrink"
        >
          <Illustration type="finish" className="w-full h-full" />
        </motion.div>

        {/* Congratulations Header */}
        <motion.div
          initial={animationsEnabled ? { scale: 0.9, opacity: 0 } : {}}
          animate={animationsEnabled ? { scale: 1, opacity: 1 } : {}}
          transition={{ delay: 0.4 }}
          className="flex flex-col items-center gap-4"
        >
          <h1 className="text-5xl md:text-7xl font-bold font-display tracking-wide text-stroke-playful leading-none">
            Ойын Аяқталды! 🏆
          </h1>
          <p className="text-2xl md:text-4xl font-bold text-purple-800 font-display max-w-2xl leading-snug">
            Бүгін барлық ойынды өте жақсы ойнап шықтыңдар!
          </p>
          <span className="text-xl md:text-2xl font-extrabold text-orange-500 font-sans tracking-wide">
            Келесі кездескенше! 👋
          </span>
        </motion.div>

        {/* Restart/Play Again Button */}
        <motion.div
          initial={animationsEnabled ? { y: 50, opacity: 0 } : {}}
          animate={animationsEnabled ? { y: 0, opacity: 1 } : {}}
          transition={{ delay: 0.6 }}
          className="mt-6"
        >
          <button
            onClick={() => {
              soundManager.playPop();
              onRestart();
            }}
            className="px-10 py-5 rounded-[2rem] bg-gradient-to-r from-emerald-500 to-green-600 hover:from-emerald-400 hover:to-green-500 text-white font-display font-bold text-2xl md:text-3xl border-4 border-purple-950 shadow-playful hover:scale-105 active:scale-95 transition-all flex items-center gap-3 cursor-pointer"
          >
            <RotateCcw className="w-7 h-7 stroke-[3px]" />
            <span>Қайта ойнау 🔄</span>
          </button>
        </motion.div>

      </div>
    </div>
  );
};

export default FinishScreen;
