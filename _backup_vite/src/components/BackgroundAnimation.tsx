import React from 'react';
import { motion } from 'framer-motion';

interface BackgroundAnimationProps {
  enabled?: boolean;
}

export const BackgroundAnimation: React.FC<BackgroundAnimationProps> = ({ enabled = true }) => {
  if (!enabled) {
    return <div className="absolute inset-0 bg-[#fefcf0] bg-grid-playful -z-10" />;
  }

  // Floating clouds
  const clouds = [
    { id: 1, top: '15%', left: '-10%', size: 'w-36 h-20', delay: 0, duration: 25 },
    { id: 2, top: '45%', left: '-15%', size: 'w-48 h-24', delay: 5, duration: 32 },
    { id: 3, top: '75%', left: '-12%', size: 'w-40 h-20', delay: 12, duration: 28 },
  ];

  // Floating balloons
  const balloons = [
    { id: 1, color: 'bg-red-400', left: '10%', size: 'w-16 h-20', delay: 0, duration: 18 },
    { id: 2, color: 'bg-blue-400', left: '80%', size: 'w-20 h-24', delay: 3, duration: 22 },
    { id: 3, color: 'bg-yellow-400', left: '30%', size: 'w-14 h-18', delay: 7, duration: 20 },
    { id: 4, color: 'bg-purple-400', left: '70%', size: 'w-18 h-22', delay: 2, duration: 24 },
    { id: 5, color: 'bg-green-400', left: '50%', size: 'w-16 h-20', delay: 9, duration: 19 },
  ];

  return (
    <div className="absolute inset-0 bg-gradient-to-b from-[#fffbeb] to-[#fefcbf] bg-grid-playful overflow-hidden -z-10 select-none pointer-events-none">
      
      {/* Sun Ray background pulse */}
      <div className="absolute top-0 left-1/2 -translate-x-1/2 w-[600px] h-[600px] bg-yellow-100/30 rounded-full blur-3xl animate-pulse-slow" />

      {/* Floating Clouds */}
      {clouds.map((cloud) => (
        <motion.div
          key={`cloud-${cloud.id}`}
          className={`absolute ${cloud.size} bg-white rounded-full opacity-65 shadow-sm`}
          initial={{ x: '-10vw' }}
          animate={{ x: '110vw' }}
          transition={{
            repeat: Infinity,
            duration: cloud.duration,
            delay: cloud.delay,
            ease: 'linear',
          }}
          style={{ top: cloud.top }}
        >
          {/* Cloud bumps */}
          <div className="absolute -top-6 left-6 w-16 h-16 bg-white rounded-full" />
          <div className="absolute -top-8 left-16 w-20 h-20 bg-white rounded-full" />
        </motion.div>
      ))}

      {/* Floating Balloons */}
      {balloons.map((balloon) => (
        <motion.g
          key={`balloon-${balloon.id}`}
          className="absolute bottom-[-150px]"
          initial={{ y: 0, rotate: 0 }}
          animate={{ 
            y: '-120vh',
            rotate: [0, 8, -8, 8, 0],
            x: [0, 20, -20, 20, 0]
          }}
          transition={{
            y: {
              repeat: Infinity,
              duration: balloon.duration,
              delay: balloon.delay,
              ease: 'linear',
            },
            rotate: {
              repeat: Infinity,
              duration: 6,
              ease: 'easeInOut',
            },
            x: {
              repeat: Infinity,
              duration: 8,
              ease: 'easeInOut',
            }
          }}
          style={{ left: balloon.left }}
        >
          <div className={`relative ${balloon.size} ${balloon.color} rounded-[50%_50%_50%_50%/_40%_40%_60%_60%] border-4 border-purple-950/20 shadow-md flex items-center justify-center`}>
            {/* Glossy shine */}
            <div className="absolute top-3 left-3 w-4 h-8 bg-white/40 rounded-full" />
            
            {/* Ribbon tie */}
            <div className="absolute bottom-[-8px] left-1/2 -translate-x-1/2 w-0 h-0 border-l-[8px] border-l-transparent border-r-[8px] border-r-transparent border-b-[10px] border-b-purple-950/40" />
            
            {/* String */}
            <svg className="absolute bottom-[-50px] left-1/2 -translate-x-1/2 w-4 h-12" viewBox="0 0 20 50">
              <path d="M 10 0 Q 5 12 15 25 T 10 50" stroke="#7c3aed" strokeWidth="2" fill="none" opacity="0.5"/>
            </svg>
          </div>
        </motion.g>
      ))}

      {/* Sparkly mini stars */}
      <div className="absolute top-1/4 left-1/4 w-3 h-3 bg-yellow-400 rounded-full animate-ping" style={{ animationDuration: '3s' }} />
      <div className="absolute top-1/3 right-1/4 w-2 h-2 bg-pink-400 rounded-full animate-ping" style={{ animationDuration: '4s' }} />
      <div className="absolute bottom-1/3 left-10 w-2.5 h-2.5 bg-blue-400 rounded-full animate-ping" style={{ animationDuration: '5s' }} />

    </div>
  );
};

export default BackgroundAnimation;
