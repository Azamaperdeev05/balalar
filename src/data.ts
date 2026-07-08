export interface GameAction {
  emoji: string;
  title: string;
  bgColor: string; // Tailwind class
}

export interface GameQuestion {
  question: string;
}

export interface GameChallenge {
  icon: string;
  scrambled: string;
  correct: string;
  bgColor: string;
  iconBg: string;
}

export interface Game {
  id: string;
  title: string;
  description: string;
  illustrationType: 'children' | 'clapping' | 'jumping' | 'colors' | 'questions' | 'finish' | 'book';
}

export const games: Game[] = [
  {
    id: 'game1',
    title: 'Крокодил',
    description: 'Жүргізуші балалардың біріне сөз айтады, сосын бала оны суретке салады, ал команда суреттер арқылы сөзді табуы керек.',
    illustrationType: 'jumping',
  },
  {
    id: 'game2',
    title: 'Сөзді тауып көр',
    description: 'Балалар 3 топқа бөлінеді. Әр топқа бірдей кітап беріледі. Жүргізуші белгілі бір тапсырма айтады, ал топ мүшелері кітаптан мүмкіндігінше тез тауып көрсетуі керек.',
    illustrationType: 'book',
  },
  {
    id: 'game3',
    title: 'Мақалды құрастыр',
    description: 'Араласқан сөздерді дұрыс ретке келтіріп, мақал-мәтел немесе қанатты сөз құрастырыңдар!',
    illustrationType: 'colors',
  },
];

// Game 1: Крокодил сөздері
export const game1Actions: GameAction[] = [
  { emoji: '🪕', title: 'Домбыра', bgColor: 'from-amber-400 to-orange-500' },
  { emoji: '⛺', title: 'Киіз үй', bgColor: 'from-emerald-400 to-green-600' },
  { emoji: '🐫', title: 'Түйе', bgColor: 'from-sky-400 to-blue-600' },
  { emoji: '🍉', title: 'Қарбыз', bgColor: 'from-indigo-400 to-purple-600' },
  { emoji: '🦅', title: 'Бүркіт', bgColor: 'from-pink-400 to-rose-600' },
  { emoji: '👑', title: 'Алтын адам', bgColor: 'from-yellow-400 to-amber-500' },
  { emoji: '🍲', title: 'Қазан', bgColor: 'from-rose-400 to-red-600' },
];

// Game 2: Сөзді тауып көр тапсырмалары
export const game2Questions: GameQuestion[] = [
  { question: '«Достық» сөзін табыңдар.' },
  { question: 'Кейіпкердің есімін табыңдар.' },
  { question: 'Бірінші тарау қай беттен басталады?' },
  { question: '«Арман» сөзі кездесетін сөйлемді табыңдар.' },
  { question: 'Кітаптағы ең ұзын сөзді табыңдар.' },
];

// Game 3: Мақалды құрастыр тапсырмалары
export const game3Challenges: GameChallenge[] = [
  {
    icon: '📖',
    scrambled: '“инемен, қазғандай, оқу, құдық”',
    correct: 'Оқу – инемен құдық қазғандай.',
    bgColor: 'from-blue-400 to-indigo-600',
    iconBg: 'bg-blue-100',
  },
  {
    icon: '📚',
    scrambled: '“бұлағы, кітап, білім”',
    correct: 'Кітап – білім бұлағы.',
    bgColor: 'from-red-400 to-rose-600',
    iconBg: 'bg-red-100',
  },
  {
    icon: '💎',
    scrambled: '“қазына, білім, таусылмас”',
    correct: 'Білім – таусылмас қазына.',
    bgColor: 'from-green-400 to-emerald-600',
    iconBg: 'bg-green-100',
  },
  {
    icon: '🤝',
    scrambled: '“бар, тірлік, бірлік, бар, жерде”',
    correct: 'Бірлік бар жерде тірлік бар.',
    bgColor: 'from-purple-400 to-violet-600',
    iconBg: 'bg-purple-100',
  },
  {
    icon: '🌾',
    scrambled: '“береке, түбі, еңбек”',
    correct: 'Еңбек түбі – береке.',
    bgColor: 'from-orange-400 to-amber-500',
    iconBg: 'bg-orange-100',
  },
  {
    icon: '🛡️',
    scrambled: '“алмайды, көпті, досы, жау”',
    correct: 'Досы көпті жау алмайды.',
    bgColor: 'from-teal-400 to-cyan-600',
    iconBg: 'bg-teal-100',
  },
  {
    icon: '🗣️',
    scrambled: '“тіл, өнер, қызыл”',
    correct: 'Өнер алды – қызыл тіл.',
    bgColor: 'from-pink-400 to-rose-500',
    iconBg: 'bg-pink-100',
  },
];
