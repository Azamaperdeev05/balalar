import React from 'react';
import { X, Volume2, VolumeX, Sparkles, Clock, Play, Fullscreen } from 'lucide-react';
import { soundManager } from '../utils/SoundManager';

interface Settings {
  timerDuration: number;
  autoPlay: boolean;
  soundEnabled: boolean;
  animationsEnabled: boolean;
  musicTheme: 'cheerful' | 'calm' | 'retro';
}

interface SettingsModalProps {
  isOpen: boolean;
  onClose: () => void;
  settings: Settings;
  onUpdateSettings: (newSettings: Partial<Settings>) => void;
  isFullscreen: boolean;
  onToggleFullscreen: () => void;
}

export const SettingsModal: React.FC<SettingsModalProps> = ({
  isOpen,
  onClose,
  settings,
  onUpdateSettings,
  isFullscreen,
  onToggleFullscreen,
}) => {
  if (!isOpen) return null;

  const timerOptions = [3, 5, 8, 10, 15];

  const handleToggle = (key: keyof Settings, currentValue: boolean) => {
    soundManager.playPop();
    onUpdateSettings({ [key]: !currentValue });
  };

  const handleSelectTimer = (duration: number) => {
    soundManager.playPop();
    onUpdateSettings({ timerDuration: duration });
  };

  return (
    <div className="fixed inset-0 bg-purple-950/70 backdrop-blur-md flex items-center justify-center p-4 z-50 select-none overflow-y-auto py-8 sm:py-12">
      {/* Modal Container */}
      <div className="w-full max-w-2xl bg-white border-8 border-purple-600 rounded-[2.5rem] shadow-playful relative p-5 sm:p-10 my-auto transform scale-100 transition-all duration-300">
        
        {/* Close Button */}
        <button
          onClick={() => {
            soundManager.playPop();
            onClose();
          }}
          className="absolute -top-6 right-2 sm:-right-6 w-14 h-14 sm:w-16 sm:h-16 bg-rose-500 hover:bg-rose-400 border-4 border-purple-950 text-white rounded-full flex items-center justify-center shadow-playful hover:scale-110 active:scale-95 transition-all cursor-pointer z-10"
        >
          <X className="w-7 h-7 sm:w-8 sm:h-8 stroke-[3.5px]" />
        </button>

        {/* Header */}
        <div className="text-center mb-6">
          <h1 className="text-3xl sm:text-4xl md:text-5xl font-black font-display tracking-wide text-stroke-playful">
            БАПТАУЛАР ⚙️
          </h1>
        </div>

        {/* Content Settings Rows */}
        <div className="flex flex-col gap-4 sm:gap-6">
          
          {/* Row 1: Timer Duration */}
          <div className="bg-purple-50 p-4 sm:p-6 rounded-3xl border-4 border-purple-200">
            <div className="flex items-center gap-3 mb-4 text-purple-950 font-display font-extrabold text-xl md:text-2xl">
              <Clock className="w-7 h-7 text-purple-600 stroke-[2.5px]" />
              <span>Таймер ұзақтығы (секунд):</span>
            </div>
            <div className="flex flex-wrap gap-3">
              {timerOptions.map((duration) => {
                const isSelected = settings.timerDuration === duration;
                return (
                  <button
                    key={duration}
                    onClick={() => handleSelectTimer(duration)}
                    className={`flex-1 min-w-[70px] py-3 sm:py-4 rounded-2xl font-display font-extrabold text-lg sm:text-xl md:text-2xl transition-all border-b-4 ${
                      isSelected
                        ? 'bg-purple-600 border-purple-800 text-white translate-y-0.5'
                        : 'bg-white border-purple-200 text-purple-800 hover:bg-purple-100 hover:border-purple-300'
                    }`}
                  >
                    {duration}с
                  </button>
                );
              })}
            </div>
          </div>

          {/* Row: Music Theme Selection */}
          <div className="bg-purple-50 p-4 sm:p-6 rounded-3xl border-4 border-purple-200">
            <div className="flex items-center gap-3 mb-4 text-purple-950 font-display font-extrabold text-xl md:text-2xl">
              <Sparkles className="w-7 h-7 text-purple-600 stroke-[2.5px]" />
              <span>Музыка стилі:</span>
            </div>
            <div className="flex flex-wrap gap-3">
              {[
                { id: 'cheerful', label: 'Көңілді 🎵' },
                { id: 'calm', label: 'Баяу 🧘' },
                { id: 'retro', label: 'Ойын (8-bit) 🎮' },
              ].map((theme) => {
                const isSelected = settings.musicTheme === theme.id;
                return (
                  <button
                    key={theme.id}
                    onClick={() => {
                      soundManager.playPop();
                      onUpdateSettings({ musicTheme: theme.id as any });
                      soundManager.setMusicTheme(theme.id as any);
                    }}
                    className={`flex-1 min-w-[130px] py-3 sm:py-4 rounded-2xl font-display font-extrabold text-base sm:text-lg md:text-xl transition-all border-b-4 ${
                      isSelected
                        ? 'bg-purple-600 border-purple-800 text-white translate-y-0.5'
                        : 'bg-white border-purple-200 text-purple-800 hover:bg-purple-100 hover:border-purple-300'
                    }`}
                  >
                    {theme.label}
                  </button>
                );
              })}
            </div>
          </div>

          {/* Settings Grid */}
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
            
            {/* Auto Play Toggle */}
            <div className="bg-purple-50 p-4 sm:p-6 rounded-3xl border-4 border-purple-200 flex flex-col justify-between gap-4">
              <div className="flex items-center gap-3 text-purple-950 font-display font-extrabold text-xl md:text-2xl">
                <Play className="w-6 h-6 text-purple-600 stroke-[2.5px]" />
                <span>Авто-ойнату</span>
              </div>
              <button
                onClick={() => handleToggle('autoPlay', settings.autoPlay)}
                className={`w-full py-3 sm:py-4 rounded-2xl font-display font-extrabold text-base sm:text-lg md:text-xl border-b-4 transition-all ${
                  settings.autoPlay
                    ? 'bg-emerald-500 border-emerald-700 text-white hover:bg-emerald-400'
                    : 'bg-rose-500 border-rose-700 text-white hover:bg-rose-400'
                }`}
              >
                {settings.autoPlay ? 'ҚОСУЛЫ (ON)' : 'ӨШІРУЛІ (OFF)'}
              </button>
            </div>

            {/* Sound Toggle */}
            <div className="bg-purple-50 p-4 sm:p-6 rounded-3xl border-4 border-purple-200 flex flex-col justify-between gap-4">
              <div className="flex items-center gap-3 text-purple-950 font-display font-extrabold text-xl md:text-2xl">
                {settings.soundEnabled ? (
                  <Volume2 className="w-6 h-6 text-purple-600 stroke-[2.5px]" />
                ) : (
                  <VolumeX className="w-6 h-6 text-rose-500 stroke-[2.5px]" />
                )}
                <span>Дыбыстар</span>
              </div>
              <button
                onClick={() => {
                  handleToggle('soundEnabled', settings.soundEnabled);
                  // Quick sound feedback if turned ON
                  if (!settings.soundEnabled) {
                    setTimeout(() => soundManager.playPop(), 100);
                  }
                }}
                className={`w-full py-3 sm:py-4 rounded-2xl font-display font-extrabold text-base sm:text-lg md:text-xl border-b-4 transition-all ${
                  settings.soundEnabled
                    ? 'bg-emerald-500 border-emerald-700 text-white hover:bg-emerald-400'
                    : 'bg-rose-500 border-rose-700 text-white hover:bg-rose-400'
                }`}
              >
                {settings.soundEnabled ? 'ДЫБЫС ҚОСУЛЫ' : 'ДЫБЫС ӨШІРУЛІ'}
              </button>
            </div>

            {/* Animation Toggle */}
            <div className="bg-purple-50 p-4 sm:p-6 rounded-3xl border-4 border-purple-200 flex flex-col justify-between gap-4">
              <div className="flex items-center gap-3 text-purple-950 font-display font-extrabold text-xl md:text-2xl">
                <Sparkles className="w-6 h-6 text-purple-600 stroke-[2.5px]" />
                <span>Анимациялар</span>
              </div>
              <button
                onClick={() => handleToggle('animationsEnabled', settings.animationsEnabled)}
                className={`w-full py-3 sm:py-4 rounded-2xl font-display font-extrabold text-base sm:text-lg md:text-xl border-b-4 transition-all ${
                  settings.animationsEnabled
                    ? 'bg-emerald-500 border-emerald-700 text-white hover:bg-emerald-400'
                    : 'bg-rose-500 border-rose-700 text-white hover:bg-rose-400'
                }`}
              >
                {settings.animationsEnabled ? 'БАРЛЫҒЫ ҚОСУЛЫ' : 'БАРЛЫҒЫ ӨШІРУЛІ'}
              </button>
            </div>

            {/* Fullscreen Button */}
            <div className="bg-purple-50 p-4 sm:p-6 rounded-3xl border-4 border-purple-200 flex flex-col justify-between gap-4">
              <div className="flex items-center gap-3 text-purple-950 font-display font-extrabold text-xl md:text-2xl">
                <Fullscreen className="w-6 h-6 text-purple-600 stroke-[2.5px]" />
                <span>Толық экран</span>
              </div>
              <button
                onClick={() => {
                  soundManager.playPop();
                  onToggleFullscreen();
                }}
                className="w-full py-3 sm:py-4 rounded-2xl bg-sky-500 border-b-4 border-sky-700 text-white hover:bg-sky-400 font-display font-extrabold text-base sm:text-lg md:text-xl transition-all"
              >
                {isFullscreen ? 'ЭКРАННАН ШЫҒУ' : 'ТОЛЫҚ ЭКРАНҒА ӨТУ'}
              </button>
            </div>

          </div>

          {/* Keyboard Shortcuts Hint */}
          <div className="text-center text-purple-600 text-xs sm:text-sm md:text-base font-bold bg-purple-50/50 p-4 rounded-2xl border border-dashed border-purple-300">
            Презентация пернелері: <kbd className="px-2 py-1 bg-white border border-purple-300 rounded shadow-sm">←</kbd> Артқа | <kbd className="px-2 py-1 bg-white border border-purple-300 rounded shadow-sm">→</kbd> Келесі | <kbd className="px-2 py-1 bg-white border border-purple-300 rounded shadow-sm">Space</kbd> Кідіріс | <kbd className="px-2 py-1 bg-white border border-purple-300 rounded shadow-sm">F</kbd> Толық экран
          </div>

        </div>
      </div>
    </div>
  );
};
export default SettingsModal;
