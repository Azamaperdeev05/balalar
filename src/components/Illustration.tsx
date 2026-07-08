import React from 'react';

export type IllustrationType = 
    | 'children' 
    | 'clapping' 
    | 'jumping' 
    | 'colors' 
    | 'questions' 
    | 'finish' 
    | 'celebration'
    | 'spin'
    | 'sit'
    | 'stand'
    | 'raise_hands'
    | 'heart'
    | 'greet'
    | 'icecream'
    | 'cartoon'
    | 'animals'
    | 'summer'
    | 'book'
    | 'mood'
    | 'dino'
    | 'elephant'
    | 'red_search'
    | 'blue_search'
    | 'green_search'
    | 'round_search'
    | 'letter_search'
    | 'fingers_count'
    | 'dombra'
    | 'yurt'
    | 'camel'
    | 'watermelon'
    | 'eagle'
    | 'golden_man'
    | 'qazan';

interface IllustrationProps {
  type: IllustrationType;
  className?: string;
}

export const Illustration: React.FC<IllustrationProps> = ({ type, className = "w-64 h-64" }) => {
  switch (type) {
    case 'icecream':
      return (
        <img 
          src="/icecream_kazakh.png" 
          alt="Ice Cream Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'cartoon':
      return (
        <img 
          src="/cartoon_kazakh.png" 
          alt="Cartoon Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'animals':
      return (
        <img 
          src="/animals_kazakh.png" 
          alt="Animals Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'summer':
      return (
        <img 
          src="/summer_kazakh.png" 
          alt="Summer Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'book':
      return (
        <img 
          src="/book_kazakh.png" 
          alt="Book Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'mood':
      return (
        <img 
          src="/mood_kazakh.png" 
          alt="Mood Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'dino':
      return (
        <img 
          src="/dino_kazakh.png" 
          alt="Dinosaur Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'elephant':
      return (
        <img 
          src="/elephant_kazakh.png" 
          alt="Elephant Kazakh" 
          className={`${className} object-contain rounded-3xl`}
        />
      );
    case 'red_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="120" fill="#fee2e2" opacity="0.6"/>
          {/* Red Objects */}
          <circle cx="160" cy="120" r="40" fill="#ef4444" stroke="#4c1d95" strokeWidth="4"/>
          <path d="M 240 180 L 260 210 L 220 210 Z" fill="#ef4444" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
          <rect x="220" y="100" width="50" height="50" rx="10" fill="#ef4444" stroke="#4c1d95" strokeWidth="4"/>
          {/* Magnifying Glass */}
          <g transform="translate(40, 20)">
            <line x1="200" y1="200" x2="270" y2="270" stroke="#4c1d95" strokeWidth="12" strokeLinecap="round"/>
            <circle cx="160" cy="160" r="50" fill="#38bdf8" fillOpacity="0.3" stroke="#4c1d95" strokeWidth="8"/>
            <circle cx="145" cy="145" r="25" fill="#ffffff" fillOpacity="0.4"/>
          </g>
        </svg>
      );

    case 'blue_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="120" fill="#dbeafe" opacity="0.6"/>
          {/* Blue Objects */}
          <circle cx="160" cy="120" r="40" fill="#3b82f6" stroke="#4c1d95" strokeWidth="4"/>
          <path d="M 240 180 L 260 210 L 220 210 Z" fill="#3b82f6" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
          <rect x="220" y="100" width="50" height="50" rx="10" fill="#3b82f6" stroke="#4c1d95" strokeWidth="4"/>
          {/* Magnifying Glass */}
          <g transform="translate(40, 20)">
            <line x1="200" y1="200" x2="270" y2="270" stroke="#4c1d95" strokeWidth="12" strokeLinecap="round"/>
            <circle cx="160" cy="160" r="50" fill="#38bdf8" fillOpacity="0.3" stroke="#4c1d95" strokeWidth="8"/>
            <circle cx="145" cy="145" r="25" fill="#ffffff" fillOpacity="0.4"/>
          </g>
        </svg>
      );

    case 'green_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="120" fill="#dcfce7" opacity="0.6"/>
          {/* Green Objects */}
          <circle cx="160" cy="120" r="40" fill="#22c55e" stroke="#4c1d95" strokeWidth="4"/>
          <path d="M 240 180 L 260 210 L 220 210 Z" fill="#22c55e" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
          <rect x="220" y="100" width="50" height="50" rx="10" fill="#22c55e" stroke="#4c1d95" strokeWidth="4"/>
          {/* Magnifying Glass */}
          <g transform="translate(40, 20)">
            <line x1="200" y1="200" x2="270" y2="270" stroke="#4c1d95" strokeWidth="12" strokeLinecap="round"/>
            <circle cx="160" cy="160" r="50" fill="#38bdf8" fillOpacity="0.3" stroke="#4c1d95" strokeWidth="8"/>
            <circle cx="145" cy="145" r="25" fill="#ffffff" fillOpacity="0.4"/>
          </g>
        </svg>
      );

    case 'round_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Round Objects */}
          <circle cx="200" cy="150" r="65" fill="#fb923c" stroke="#4c1d95" strokeWidth="5"/>
          <circle cx="200" cy="150" r="45" fill="#ffedd5" stroke="#4c1d95" strokeWidth="3" strokeDasharray="8 6"/>
          <circle cx="90" cy="90" r="40" fill="#38bdf8" stroke="#4c1d95" strokeWidth="4"/>
          <path d="M 90 130 L 90 170" stroke="#4c1d95" strokeWidth="3"/>
          <circle cx="310" cy="200" r="35" fill="#ec4899" stroke="#4c1d95" strokeWidth="4"/>
          <circle cx="110" cy="220" r="25" fill="#eab308" stroke="#4c1d95" strokeWidth="4"/>
        </svg>
      );

    case 'letter_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Cute T-shirt/Chapan outline */}
          <path d="M 120 70 L 150 40 L 180 60 L 220 60 L 250 40 L 280 70 L 270 110 L 250 100 L 250 250 L 150 250 L 150 100 L 130 110 Z" fill="#a855f7" stroke="#4c1d95" strokeWidth="6" strokeLinejoin="round"/>
          {/* Gold pattern line */}
          <path d="M 200 60 L 200 250" stroke="#facc15" strokeWidth="6"/>
          {/* Floating Letters */}
          <g fontFamily="Nunito" fontWeight="black" fontSize="50" fill="#ffffff" stroke="#4c1d95" strokeWidth="2">
            <text x="175" y="140">Ә</text>
            <text x="220" y="190" fill="#facc15">І</text>
            <text x="160" y="210" fill="#38bdf8">Ң</text>
          </g>
        </svg>
      );

    case 'dombra':
      return (
        <img 
          src="/dombra_kazakh.png" 
          alt="Dombra" 
          className={`${className} object-contain rounded-2xl`}
        />
      );

    case 'yurt':
      return (
        <img 
          src="/yurt_kazakh.png" 
          alt="Yurt" 
          className={`${className} object-contain rounded-2xl`}
        />
      );

    case 'camel':
      return (
        <img 
          src="/camel_kazakh.png" 
          alt="Camel" 
          className={`${className} object-contain rounded-2xl`}
        />
      );

    case 'watermelon':
      return (
        <img 
          src="/watermelon_kazakh.png" 
          alt="Watermelon" 
          className={`${className} object-contain rounded-2xl`}
        />
      );

    case 'eagle':
      return (
        <img 
          src="/eagle_kazakh.png" 
          alt="Eagle" 
          className={`${className} object-contain rounded-2xl`}
        />
      );

    case 'golden_man':
      return (
        <img 
          src="/golden_man_kazakh.png" 
          alt="Golden Man" 
          className={`${className} object-contain rounded-2xl`}
        />
      );

    case 'qazan':
      return (
        <img 
          src="/qazan_kazakh.png" 
          alt="Qazan" 
          className={`${className} object-contain rounded-2xl`}
        />
      );

    case 'fingers_count':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* High five hand character */}
          <g transform="translate(100, 30)">
            {/* Hand base */}
            <path d="M 60 170 C 60 210 140 210 140 170 L 140 110 C 140 100 130 90 120 90 C 110 90 105 100 105 110 L 105 70 C 105 60 95 50 85 50 C 75 50 70 60 70 70 L 70 80 C 70 70 60 60 50 60 C 40 60 35 70 35 80 L 35 110 C 35 100 25 90 15 90 C 5 90 0 100 0 110 L 0 160 C 0 200 40 240 90 240 C 140 240 160 200 160 160 L 160 140 C 160 130 170 120 180 130 C 190 140 180 180 150 200" fill="#fdba74" stroke="#4c1d95" strokeWidth="6" strokeLinejoin="round"/>
            {/* Happy Face on Palm */}
            <circle cx="65" cy="160" r="6" fill="#4c1d95"/>
            <circle cx="95" cy="160" r="6" fill="#4c1d95"/>
            <path d="M 70 180 Q 80 190 90 180" stroke="#4c1d95" strokeWidth="4" strokeLinecap="round"/>
          </g>
        </svg>
      );

    case 'jumping':
      return (
        <img 
          src="/jumping_kazakh.png" 
          alt="Jumping Kazakh Children" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'clapping':
      return (
        <img 
          src="/clapping_kazakh.png" 
          alt="Clapping Kazakh Children" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'finish':
      return (
        <img 
          src="/finish_kazakh.png" 
          alt="Celebrating Kazakh Children" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'spin':
      return (
        <img 
          src="/spin_kazakh.png" 
          alt="Spinning Kazakh Child" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'sit':
      return (
        <img 
          src="/sit_kazakh.png" 
          alt="Sitting Kazakh Children" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'stand':
      return (
        <img 
          src="/stand_kazakh.png" 
          alt="Standing Kazakh Child" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'raise_hands':
      return (
        <img 
          src="/raise_hands_kazakh.png" 
          alt="Kazakh Child Raising Hands" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'heart':
      return (
        <img 
          src="/heart_kazakh.png" 
          alt="Kazakh Child Heart Sign" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'greet':
      return (
        <img 
          src="/greet_kazakh.png" 
          alt="Kazakh Children Greeting" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'children':
      return (
        <img 
          src="/jumping_kazakh.png" 
          alt="Kazakh Children" 
          className={`${className} object-contain rounded-3xl`}
        />
      );

    case 'colors':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Paint Palette */}
          <path d="M 80 150 C 80 80 180 60 260 90 C 340 120 340 210 280 240 C 220 270 140 260 100 230 C 85 215 70 185 80 150 Z" fill="#dbeafe" stroke="#4c1d95" strokeWidth="5" strokeLinejoin="round"/>
          
          {/* Palette Thumb Hole */}
          <ellipse cx="130" cy="200" rx="15" ry="10" fill="#fefcf0" stroke="#4c1d95" strokeWidth="4"/>

          {/* Paint blobs */}
          <circle cx="130" cy="110" r="18" fill="#ef4444" stroke="#4c1d95" strokeWidth="3"/>
          <circle cx="190" cy="100" r="18" fill="#3b82f6" stroke="#4c1d95" strokeWidth="3"/>
          <circle cx="250" cy="115" r="18" fill="#22c55e" stroke="#4c1d95" strokeWidth="3"/>
          <circle cx="285" cy="165" r="18" fill="#eab308" stroke="#4c1d95" strokeWidth="3"/>
          <circle cx="240" cy="205" r="18" fill="#a855f7" stroke="#4c1d95" strokeWidth="3"/>

          {/* Paint Brush */}
          <g transform="translate(140, 90) rotate(-35)">
            <path d="M 5 180 L 15 180 L 15 50 L 5 50 Z" fill="#d97706" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
            <path d="M 3 50 L 17 50 L 17 35 L 3 35 Z" fill="#9ca3af" stroke="#4c1d95" strokeWidth="4"/>
            <path d="M 3 35 C 3 15 10 0 10 0 C 10 0 17 15 17 35 Z" fill="#f97316" stroke="#4c1d95" strokeWidth="4"/>
            <path d="M 6 15 C 8 8 10 0 10 0 C 10 0 12 8 14 15 Z" fill="#3b82f6"/>
          </g>
        </svg>
      );

    case 'questions':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M 60 170 Q 80 130 120 140 Q 160 110 200 130 Q 240 110 280 140 Q 320 130 340 170 Q 370 200 340 230 Q 310 250 280 240 Q 200 250 120 240 Q 90 250 60 230 Q 30 200 60 170 Z" fill="#f3e8ff" opacity="0.8"/>
          <g>
            <path d="M 80 80 Q 80 50 130 50 L 270 50 Q 320 50 320 80 L 320 160 Q 320 190 270 190 L 220 190 L 180 230 L 180 190 L 130 190 Q 80 190 80 160 Z" fill="#ffffff" stroke="#4c1d95" strokeWidth="5" strokeLinejoin="round"/>
            <text x="180" y="145" fill="#a855f7" fontSize="90" fontWeight="bold" fontFamily="Fredoka" stroke="#4c1d95" strokeWidth="3" textAnchor="middle">?</text>
          </g>
        </svg>
      );

    case 'celebration':
    default:
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="45" fill="#fef08a" stroke="#4c1d95" strokeWidth="4"/>
          <g transform="translate(80, 100)">
            <path d="M 25 10 C 50 10 60 35 50 55 C 40 75 25 80 25 80 C 25 80 10 75 0 55 C -10 35 0 10 25 10 Z" fill="#3b82f6" stroke="#4c1d95" strokeWidth="4"/>
            <path d="M 25 80 L 25 110" stroke="#4c1d95" strokeWidth="3"/>
            <path d="M 25 80 L 22 84 L 28 84 Z" fill="#4c1d95"/>
            <path d="M 12 30 C 12 20 20 18 20 18" stroke="#ffffff" strokeWidth="3" strokeLinecap="round" opacity="0.5"/>
          </g>
          <g transform="translate(270, 70)">
            <path d="M 25 10 C 50 10 60 35 50 55 C 40 75 25 80 25 80 C 25 80 10 75 0 55 C -10 35 0 10 25 10 Z" fill="#ec4899" stroke="#4c1d95" strokeWidth="4"/>
            <path d="M 25 80 L 25 110" stroke="#4c1d95" strokeWidth="3"/>
            <path d="M 25 80 L 22 84 L 28 84 Z" fill="#4c1d95"/>
            <path d="M 12 30 C 12 20 20 18 20 18" stroke="#ffffff" strokeWidth="3" strokeLinecap="round" opacity="0.5"/>
          </g>
        </svg>
      );
  }
};
export default Illustration;
