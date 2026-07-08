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
          <circle cx="200" cy="150" r="120" fill="#27272a" opacity="0.15"/>
          {/* Red Search Target Object */}
          <circle cx="160" cy="120" r="35" fill="#ef4444" stroke="#ffffff" strokeWidth="3"/>
          <path d="M 245 175 L 265 205 L 225 205 Z" fill="#27272a" stroke="#3f3f46" strokeWidth="3" strokeLinejoin="round"/>
          <rect x="220" y="95" width="45" height="45" rx="8" fill="#a1a1aa" stroke="#ffffff" strokeWidth="3"/>
          {/* Magnifying Glass */}
          <g transform="translate(40, 20)">
            <line x1="200" y1="200" x2="270" y2="270" stroke="#a1a1aa" strokeWidth="10" strokeLinecap="round"/>
            <circle cx="160" cy="160" r="50" fill="#6366f1" fillOpacity="0.1" stroke="#ffffff" strokeWidth="5"/>
            <circle cx="145" cy="145" r="20" fill="#ffffff" fillOpacity="0.2"/>
          </g>
        </svg>
      );

    case 'blue_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="120" fill="#27272a" opacity="0.15"/>
          {/* Blue Search Target Object */}
          <circle cx="160" cy="120" r="35" fill="#3b82f6" stroke="#ffffff" strokeWidth="3"/>
          <path d="M 245 175 L 265 205 L 225 205 Z" fill="#27272a" stroke="#3f3f46" strokeWidth="3" strokeLinejoin="round"/>
          <rect x="220" y="95" width="45" height="45" rx="8" fill="#a1a1aa" stroke="#ffffff" strokeWidth="3"/>
          {/* Magnifying Glass */}
          <g transform="translate(40, 20)">
            <line x1="200" y1="200" x2="270" y2="270" stroke="#a1a1aa" strokeWidth="10" strokeLinecap="round"/>
            <circle cx="160" cy="160" r="50" fill="#6366f1" fillOpacity="0.1" stroke="#ffffff" strokeWidth="5"/>
            <circle cx="145" cy="145" r="20" fill="#ffffff" fillOpacity="0.2"/>
          </g>
        </svg>
      );

    case 'green_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="120" fill="#27272a" opacity="0.15"/>
          {/* Green Search Target Object */}
          <circle cx="160" cy="120" r="35" fill="#22c55e" stroke="#ffffff" strokeWidth="3"/>
          <path d="M 245 175 L 265 205 L 225 205 Z" fill="#27272a" stroke="#3f3f46" strokeWidth="3" strokeLinejoin="round"/>
          <rect x="220" y="95" width="45" height="45" rx="8" fill="#a1a1aa" stroke="#ffffff" strokeWidth="3"/>
          {/* Magnifying Glass */}
          <g transform="translate(40, 20)">
            <line x1="200" y1="200" x2="270" y2="270" stroke="#a1a1aa" strokeWidth="10" strokeLinecap="round"/>
            <circle cx="160" cy="160" r="50" fill="#6366f1" fillOpacity="0.1" stroke="#ffffff" strokeWidth="5"/>
            <circle cx="145" cy="145" r="20" fill="#ffffff" fillOpacity="0.2"/>
          </g>
        </svg>
      );

    case 'round_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="110" fill="#27272a" opacity="0.15"/>
          {/* Round Objects */}
          <circle cx="200" cy="150" r="55" fill="#6366f1" stroke="#ffffff" strokeWidth="4"/>
          <circle cx="200" cy="150" r="38" fill="transparent" stroke="#ffffff" strokeWidth="2" strokeDasharray="6 4"/>
          <circle cx="95" cy="95" r="30" fill="#18181b" stroke="#3f3f46" strokeWidth="3"/>
          <line x1="95" y1="125" x2="95" y2="155" stroke="#3f3f46" strokeWidth="3"/>
          <circle cx="305" cy="205" r="28" fill="#a1a1aa" stroke="#ffffff" strokeWidth="3"/>
          <circle cx="115" cy="215" r="20" fill="#ffffff" stroke="#3f3f46" strokeWidth="3"/>
        </svg>
      );

    case 'letter_search':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="110" fill="#27272a" opacity="0.15"/>
          {/* Minimalist Chapan/T-shirt outline */}
          <path d="M 120 70 L 150 40 L 180 60 L 220 60 L 250 40 L 280 70 L 270 110 L 250 100 L 250 250 L 150 250 L 150 100 L 130 110 Z" fill="#18181b" stroke="#3f3f46" strokeWidth="4" strokeLinejoin="round"/>
          {/* Gold pattern line */}
          <path d="M 200 60 L 200 250" stroke="#6366f1" strokeWidth="4"/>
          {/* Floating Letters */}
          <g fontFamily="Nunito" fontWeight="black" fontSize="45" fill="#ffffff" stroke="none">
            <text x="175" y="130">Ә</text>
            <text x="215" y="180" fill="#6366f1">І</text>
            <text x="160" y="205" fill="#a1a1aa">Ң</text>
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
          <circle cx="200" cy="150" r="110" fill="#27272a" opacity="0.15"/>
          <g transform="translate(100, 30)">
            {/* Minimalist modern hand outline */}
            <path d="M 60 170 C 60 200 140 200 140 170 L 140 100 C 140 92 130 85 120 85 C 110 85 105 92 105 100 L 105 60 C 105 52 95 45 85 45 C 75 45 70 52 70 60 L 70 70 C 70 62 60 55 50 55 C 40 55 35 62 35 70 L 35 100 C 35 92 25 85 15 85 C 5 85 0 92 0 100 L 0 150 C 0 185 35 220 80 220 C 125 220 145 185 145 150 L 145 130 C 145 122 155 115 165 122 C 175 130 165 165 140 185" fill="#18181b" stroke="#3f3f46" strokeWidth="4" strokeLinejoin="round"/>
            {/* Indigo indicator light on the palm representing counting */}
            <circle cx="70" cy="140" r="12" fill="#6366f1" opacity="0.8"/>
            <text x="70" y="145" fill="#ffffff" fontSize="14" fontWeight="black" fontFamily="Fredoka" textAnchor="middle">5</text>
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
          <path d="M 80 150 C 80 80 180 60 260 90 C 340 120 340 210 280 240 C 220 270 140 260 100 230 C 85 215 70 185 80 150 Z" fill="#18181b" stroke="#3f3f46" strokeWidth="4" strokeLinejoin="round"/>
          
          {/* Palette Thumb Hole */}
          <ellipse cx="130" cy="200" rx="15" ry="10" fill="#09090b" stroke="#3f3f46" strokeWidth="3"/>

          {/* Minimal Grayscale & Indigo Blobs */}
          <circle cx="130" cy="110" r="15" fill="#3f3f46" stroke="#ffffff" strokeWidth="2"/>
          <circle cx="190" cy="100" r="15" fill="#a1a1aa" stroke="#ffffff" strokeWidth="2"/>
          <circle cx="250" cy="115" r="15" fill="#6366f1" stroke="#ffffff" strokeWidth="2"/> {/* Accent! */}
          <circle cx="285" cy="165" r="15" fill="#ffffff" stroke="#3f3f46" strokeWidth="2"/>
          <circle cx="240" cy="205" r="15" fill="#27272a" stroke="#3f3f46" strokeWidth="2"/>

          {/* Paint Brush */}
          <g transform="translate(140, 90) rotate(-35)">
            <path d="M 5 180 L 15 180 L 15 50 L 5 50 Z" fill="#27272a" stroke="#3f3f46" strokeWidth="3" strokeLinejoin="round"/>
            <path d="M 3 50 L 17 50 L 17 35 L 3 35 Z" fill="#52525b" stroke="#3f3f46" strokeWidth="3"/>
            <path d="M 3 35 C 3 15 10 0 10 0 C 10 0 17 15 17 35 Z" fill="#ffffff" stroke="#3f3f46" strokeWidth="3"/>
            <path d="M 6 15 C 8 8 10 0 10 0 C 10 0 12 8 14 15 Z" fill="#6366f1"/> {/* Accent! */}
          </g>
        </svg>
      );

    case 'questions':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M 60 170 Q 80 130 120 140 Q 160 110 200 130 Q 240 110 280 140 Q 320 130 340 170 Q 370 200 340 230 Q 310 250 280 240 Q 200 250 120 240 Q 90 250 60 230 Q 30 200 60 170 Z" fill="#27272a" opacity="0.15"/>
          <g>
            <path d="M 80 80 Q 80 50 130 50 L 270 50 Q 320 50 320 80 L 320 160 Q 320 190 270 190 L 220 190 L 180 230 L 180 190 L 130 190 Q 80 190 80 160 Z" fill="#18181b" stroke="#3f3f46" strokeWidth="4" strokeLinejoin="round"/>
            <text x="180" y="145" fill="#6366f1" fontSize="90" fontWeight="bold" fontFamily="Fredoka" stroke="#ffffff" strokeWidth="2" textAnchor="middle">?</text>
          </g>
        </svg>
      );

    case 'celebration':
    default:
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="200" cy="150" r="45" fill="#18181b" stroke="#3f3f46" strokeWidth="3"/>
          <g transform="translate(80, 100)">
            <path d="M 25 10 C 50 10 60 35 50 55 C 40 75 25 80 25 80 C 25 80 10 75 0 55 C -10 35 0 10 25 10 Z" fill="#6366f1" stroke="#ffffff" strokeWidth="3"/>
            <path d="M 25 80 L 25 110" stroke="#3f3f46" strokeWidth="2"/>
            <path d="M 25 80 L 22 84 L 28 84 Z" fill="#3f3f46"/>
          </g>
          <g transform="translate(270, 70)">
            <path d="M 25 10 C 50 10 60 35 50 55 C 40 75 25 80 25 80 C 25 80 10 75 0 55 C -10 35 0 10 25 10 Z" fill="#a1a1aa" stroke="#ffffff" strokeWidth="3"/>
            <path d="M 25 80 L 25 110" stroke="#3f3f46" strokeWidth="2"/>
            <path d="M 25 80 L 22 84 L 28 84 Z" fill="#3f3f46"/>
          </g>
        </svg>
      );
  }
};
export default Illustration;
