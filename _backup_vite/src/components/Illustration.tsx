import React from 'react';

interface IllustrationProps {
  type: 
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
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Background aura */}
          <circle cx="200" cy="150" r="110" fill="#fef3c7" opacity="0.6"/>
          {/* Neck */}
          <path d="M 200 40 L 200 160" stroke="#78350f" strokeWidth="8" strokeLinecap="round"/>
          <path d="M 197 40 L 197 160" stroke="#facc15" strokeWidth="2"/>
          {/* Head & Tuning Pegs */}
          <rect x="192" y="20" width="16" height="25" rx="5" fill="#78350f" stroke="#4c1d95" strokeWidth="4"/>
          <circle cx="185" cy="28" r="5" fill="#facc15" stroke="#4c1d95" strokeWidth="2"/>
          <circle cx="215" cy="38" r="5" fill="#facc15" stroke="#4c1d95" strokeWidth="2"/>
          {/* Body */}
          <path d="M 200 140 C 150 140 130 190 150 230 C 170 260 230 260 250 230 C 270 190 250 140 200 140 Z" fill="#b45309" stroke="#4c1d95" strokeWidth="5" strokeLinejoin="round"/>
          {/* Sound Hole */}
          <circle cx="200" cy="190" r="10" fill="#4c1d95"/>
          <circle cx="200" cy="205" r="5" fill="#4c1d95"/>
          {/* Strings */}
          <line x1="198" y1="40" x2="198" y2="240" stroke="#facc15" strokeWidth="2"/>
          <line x1="202" y1="40" x2="202" y2="240" stroke="#facc15" strokeWidth="2"/>
          {/* Bridge */}
          <rect x="185" y="235" width="30" height="8" rx="2" fill="#78350f" stroke="#4c1d95" strokeWidth="3"/>
        </svg>
      );

    case 'yurt':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Ground */}
          <path d="M 60 240 Q 200 260 340 240" stroke="#4c1d95" strokeWidth="6" strokeLinecap="round"/>
          {/* Dome Wall */}
          <path d="M 90 240 L 90 170 C 90 170 90 110 200 110 C 310 110 310 170 310 170 L 310 240 Z" fill="#fcfcfc" stroke="#4c1d95" strokeWidth="6" strokeLinejoin="round"/>
          {/* Shanyrak (Roof Wheel) */}
          <path d="M 140 135 C 140 100 260 100 260 135 Z" fill="#f59e0b" stroke="#4c1d95" strokeWidth="5"/>
          <ellipse cx="200" cy="120" rx="40" ry="12" fill="#d97706" stroke="#4c1d95" strokeWidth="4"/>
          <line x1="200" y1="108" x2="200" y2="132" stroke="#4c1d95" strokeWidth="3"/>
          <line x1="170" y1="120" x2="230" y2="120" stroke="#4c1d95" strokeWidth="3"/>
          {/* Red ornaments around the dome */}
          <path d="M 90 170 Q 200 185 310 170" stroke="#dc2626" strokeWidth="8" fill="none" opacity="0.85"/>
          <path d="M 120 170 L 140 190 L 160 170 L 180 190 L 200 170 L 220 190 L 240 170 L 260 190 L 280 170" stroke="#facc15" strokeWidth="3" fill="none"/>
          {/* Doorway */}
          <path d="M 175 240 L 175 190 C 175 185 225 185 225 190 L 225 240 Z" fill="#b91c1c" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
          {/* Kazakh patterns on door */}
          <path d="M 200 195 L 200 235" stroke="#facc15" strokeWidth="3"/>
          <circle cx="200" cy="210" r="6" fill="#facc15" stroke="#4c1d95" strokeWidth="2"/>
        </svg>
      );

    case 'camel':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Background desert sun */}
          <circle cx="200" cy="150" r="100" fill="#ffedd5" opacity="0.6"/>
          <circle cx="280" cy="90" r="40" fill="#fef08a" opacity="0.4"/>
          {/* Legs */}
          <rect x="130" y="190" width="12" height="70" rx="5" fill="#d97706" stroke="#4c1d95" strokeWidth="4"/>
          <rect x="160" y="190" width="12" height="65" rx="5" fill="#d97706" stroke="#4c1d95" strokeWidth="4"/>
          <rect x="220" y="190" width="12" height="70" rx="5" fill="#d97706" stroke="#4c1d95" strokeWidth="4"/>
          <rect x="250" y="190" width="12" height="65" rx="5" fill="#d97706" stroke="#4c1d95" strokeWidth="4"/>
          {/* Tail */}
          <path d="M 100 180 Q 80 190 85 220" stroke="#4c1d95" strokeWidth="4" strokeLinecap="round"/>
          <circle cx="85" cy="220" r="6" fill="#d97706" stroke="#4c1d95" strokeWidth="2"/>
          {/* Body & Double Humps */}
          <path d="M 105 180 C 100 150 140 110 160 145 C 180 110 220 110 240 145 C 265 130 285 160 280 190 L 115 190 Z" fill="#f59e0b" stroke="#4c1d95" strokeWidth="5" strokeLinejoin="round"/>
          {/* Long curved neck */}
          <path d="M 270 180 Q 320 160 300 100" stroke="#4c1d95" strokeWidth="14" strokeLinecap="round"/>
          <path d="M 270 180 Q 320 160 300 100" stroke="#f59e0b" strokeWidth="8" strokeLinecap="round"/>
          {/* Head */}
          <rect x="290" y="80" width="35" height="30" rx="10" fill="#f59e0b" stroke="#4c1d95" strokeWidth="4" transform="rotate(-15, 290, 80)"/>
          {/* Ears */}
          <path d="M 293 72 Q 288 60 293 58 Q 298 60 295 72" fill="#d97706" stroke="#4c1d95" strokeWidth="2"/>
          {/* Eyes & Smile */}
          <circle cx="310" cy="88" r="3" fill="#4c1d95"/>
          <path d="M 312 98 Q 307 103 302 98" stroke="#4c1d95" strokeWidth="3" strokeLinecap="round" fill="none"/>
        </svg>
      );

    case 'watermelon':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          <g transform="translate(0, 20)">
            {/* Outer Green Rind */}
            <path d="M 80 120 C 80 230 320 230 320 120 Z" fill="#16a34a" stroke="#4c1d95" strokeWidth="6" strokeLinejoin="round"/>
            {/* Inner Light Green Layer */}
            <path d="M 92 120 C 92 215 308 215 308 120 Z" fill="#bbf7d0"/>
            {/* Red Flesh */}
            <path d="M 104 120 C 104 200 296 200 296 120 Z" fill="#ef4444" stroke="#4c1d95" strokeWidth="5" strokeLinejoin="round"/>
            {/* Seeds */}
            <circle cx="150" cy="140" r="5" fill="#1e1b4b"/>
            <circle cx="170" cy="165" r="5" fill="#1e1b4b"/>
            <circle cx="200" cy="150" r="5" fill="#1e1b4b"/>
            <circle cx="230" cy="165" r="5" fill="#1e1b4b"/>
            <circle cx="250" cy="140" r="5" fill="#1e1b4b"/>
            {/* Cute Face */}
            <circle cx="185" cy="130" r="4" fill="#1e1b4b"/>
            <circle cx="215" cy="130" r="4" fill="#1e1b4b"/>
            <path d="M 195 138 Q 200 144 205 138" stroke="#1e1b4b" strokeWidth="3" strokeLinecap="round" fill="none"/>
            {/* Cheeks */}
            <circle cx="177" cy="133" r="5" fill="#fca5a5" opacity="0.8"/>
            <circle cx="223" cy="133" r="5" fill="#fca5a5" opacity="0.8"/>
          </g>
        </svg>
      );

    case 'eagle':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Blue sky/clouds backdrop */}
          <circle cx="200" cy="150" r="100" fill="#e0f2fe" opacity="0.6"/>
          <path d="M 140 210 Q 200 230 260 210" stroke="#bae6fd" strokeWidth="12" strokeLinecap="round" opacity="0.5"/>
          {/* Soaring Eagle */}
          <g transform="translate(10, 10)">
            {/* Left Wing */}
            <path d="M 180 120 C 130 90 60 90 40 120 C 50 140 120 150 180 135" fill="#78350f" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
            <path d="M 50 120 L 70 140 M 80 115 L 100 138 M 110 110 L 130 135" stroke="#facc15" strokeWidth="2"/>
            {/* Right Wing */}
            <path d="M 200 120 C 250 90 320 90 340 120 C 330 140 260 150 200 135" fill="#78350f" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
            <path d="M 330 120 L 310 140 M 300 115 L 280 138 M 270 110 L 250 135" stroke="#facc15" strokeWidth="2"/>
            {/* Tail */}
            <path d="M 170 140 L 190 190 L 210 140 Z" fill="#b45309" stroke="#4c1d95" strokeWidth="4"/>
            {/* Body */}
            <ellipse cx="190" cy="130" rx="20" ry="30" fill="#78350f" stroke="#4c1d95" strokeWidth="4"/>
            {/* White Neck & Head */}
            <path d="M 175 110 C 175 90 205 90 205 110 Z" fill="#ffffff" stroke="#4c1d95" strokeWidth="3"/>
            <circle cx="190" cy="95" r="15" fill="#ffffff" stroke="#4c1d95" strokeWidth="3"/>
            {/* Curved Beak */}
            <path d="M 195 85 Q 215 88 205 105 Q 192 100 195 85 Z" fill="#fbbf24" stroke="#4c1d95" strokeWidth="3" strokeLinejoin="round"/>
            {/* Eye */}
            <circle cx="186" cy="93" r="2.5" fill="#4c1d95"/>
          </g>
        </svg>
      );

    case 'golden_man':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Solar background */}
          <circle cx="200" cy="150" r="105" fill="#fef9c3" opacity="0.6"/>
          {/* Saka Pointed Helmet silhouette */}
          <g transform="translate(110, 30)">
            {/* Neck guard */}
            <path d="M 60 170 L 120 170 L 130 210 L 50 210 Z" fill="#eab308" stroke="#4c1d95" strokeWidth="5" strokeLinejoin="round"/>
            {/* Tall Cone Helmet */}
            <path d="M 50 170 L 90 20 L 130 170 Z" fill="#ca8a04" stroke="#4c1d95" strokeWidth="6" strokeLinejoin="round"/>
            <path d="M 65 170 L 90 40 L 115 170 Z" fill="#facc15" stroke="#4c1d95" strokeWidth="3"/>
            {/* Arrow/wing ornaments on helmet */}
            <path d="M 90 20 L 90 5" stroke="#4c1d95" strokeWidth="4" strokeLinecap="round"/>
            <path d="M 80 5 L 90 0 L 100 5" fill="#facc15" stroke="#4c1d95" strokeWidth="3"/>
            {/* Left side wing */}
            <path d="M 50 120 C 20 120 10 150 45 160 Z" fill="#facc15" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
            {/* Right side wing */}
            <path d="M 130 120 C 160 120 170 150 135 160 Z" fill="#facc15" stroke="#4c1d95" strokeWidth="4" strokeLinejoin="round"/>
            {/* Gold ornaments details */}
            <circle cx="90" cy="100" r="12" fill="#ca8a04" stroke="#4c1d95" strokeWidth="3"/>
            <circle cx="90" cy="100" r="6" fill="#facc15"/>
            <circle cx="90" cy="140" r="8" fill="#ca8a04" stroke="#4c1d95" strokeWidth="3"/>
          </g>
        </svg>
      );

    case 'qazan':
      return (
        <svg viewBox="0 0 400 300" className={className} fill="none" xmlns="http://www.w3.org/2000/svg">
          {/* Aura */}
          <circle cx="200" cy="150" r="100" fill="#f3f4f6" opacity="0.6"/>
          {/* Firewood flames under qazan */}
          <path d="M 150 230 Q 200 170 250 230" fill="#f97316" opacity="0.7"/>
          <path d="M 170 230 Q 200 190 230 230" fill="#ef4444" opacity="0.95"/>
          {/* Three legs */}
          <rect x="135" y="200" width="16" height="40" rx="5" fill="#1f2937" stroke="#4c1d95" strokeWidth="4" transform="rotate(25, 135, 200)"/>
          <rect x="250" y="200" width="16" height="40" rx="5" fill="#1f2937" stroke="#4c1d95" strokeWidth="4" transform="rotate(-25, 250, 200)"/>
          <rect x="192" y="210" width="16" height="35" rx="5" fill="#111827" stroke="#4c1d95" strokeWidth="4"/>
          {/* Side Loop Handles */}
          <circle cx="90" cy="140" r="22" fill="none" stroke="#4c1d95" strokeWidth="6"/>
          <circle cx="310" cy="140" r="22" fill="none" stroke="#4c1d95" strokeWidth="6"/>
          {/* Big Cauldron Bowl */}
          <path d="M 100 120 C 100 220 300 220 300 120 Z" fill="#374151" stroke="#4c1d95" strokeWidth="6" strokeLinejoin="round"/>
          <rect x="90" y="110" width="220" height="15" rx="7" fill="#1f2937" stroke="#4c1d95" strokeWidth="5"/>
          {/* Lid with handle */}
          <path d="M 110 110 C 110 90 290 90 290 110 Z" fill="#4b5563" stroke="#4c1d95" strokeWidth="5"/>
          <path d="M 180 85 C 180 70 220 70 220 85 Z" fill="none" stroke="#4c1d95" strokeWidth="5" strokeLinecap="round"/>
        </svg>
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
