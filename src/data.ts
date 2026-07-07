export interface GameAction {
  emoji: string;
  title: string;
  bgColor: string; // Tailwind class like 'from-yellow-400 to-orange-500'
}

export interface GameQuestion {
  question: string;
  isFunny?: boolean;
}

export interface GameChallenge {
  icon: string;
  title: string;
  bgColor: string;
  iconBg: string;
}

export interface Game {
  id: string;
  title: string;
  description: string;
  illustrationType: 'children' | 'clapping' | 'jumping' | 'colors' | 'questions' | 'finish';
}

export const games: Game[] = [
  {
    id: 'game1',
    title: 'Жүргізушінің артынан қайтала',
    description: 'Жүргізуші көрсеткен қимылдарды тез әрі көңілді қайталап үлгер!',
    illustrationType: 'jumping',
  },
  {
    id: 'game2',
    title: 'Иә немесе Жоқ',
    description: 'Сұрақтарға жылдам жауап бер! Егер ИӘ болса - биле, ЖОҚ болса - отыр!',
    illustrationType: 'questions',
  },
  {
    id: 'game3',
    title: 'Түсті тауып көр',
    description: 'Айналаңнан сұралған түстерді немесе заттарды тез тауып көрсет!',
    illustrationType: 'colors',
  },
];

export const game1Actions: GameAction[] = [
  { emoji: '👏', title: 'Қол шапалақта!', bgColor: 'from-amber-400 to-orange-500' },
  { emoji: '🤸', title: 'Екі рет секір!', bgColor: 'from-emerald-400 to-green-600' },
  { emoji: '🌀', title: 'Өз орныңда айнал!', bgColor: 'from-sky-400 to-blue-600' },
  { emoji: '⬇', title: 'Отыр!', bgColor: 'from-indigo-400 to-purple-600' },
  { emoji: '⬆', title: 'Тұр!', bgColor: 'from-pink-400 to-rose-600' },
  { emoji: '🙌', title: 'Екі қолыңды көтер!', bgColor: 'from-yellow-400 to-amber-500' },
  { emoji: '❤️', title: 'Жүрек көрсет!', bgColor: 'from-rose-400 to-red-600' },
  { emoji: '🤝', title: 'Досыңмен амандас!', bgColor: 'from-teal-400 to-cyan-600' },
];

export const game2Questions: GameQuestion[] = [
  { question: 'Балмұздақты жақсы көресің бе?' },
  { question: 'Мультфильм көресің бе?' },
  { question: 'Жануарларды жақсы көресің бе?' },
  { question: 'Жазды жақсы көресің бе?' },
  { question: 'Кітап оқисың ба?' },
  { question: 'Бүгін көңіл-күйің жақсы ма?' },
  { question: 'Бүгін динозавр жедің бе?', isFunny: true },
  { question: 'Үйіңде піл бар ма?', isFunny: true },
  { question: 'Айға ұшып көрдің бе?', isFunny: true },
  { question: 'Айдаһармен доссың ба?', isFunny: true },
];

export const game3Challenges: GameChallenge[] = [
  { icon: '🔵', title: 'Көк түсті тап!', bgColor: 'from-blue-400 to-indigo-600', iconBg: 'bg-blue-100' },
  { icon: '🔴', title: 'Қызыл түсті тап!', bgColor: 'from-red-400 to-rose-600', iconBg: 'bg-red-100' },
  { icon: '🟢', title: 'Жасыл түсті тап!', bgColor: 'from-green-400 to-emerald-600', iconBg: 'bg-green-100' },
  { icon: '⚪', title: 'Ақ түсті тап!', bgColor: 'from-gray-300 to-gray-500', iconBg: 'bg-gray-100' },
  { icon: '⚫', title: 'Қара түсті тап!', bgColor: 'from-gray-700 to-gray-900', iconBg: 'bg-gray-800' },
  { icon: '🔠', title: 'Киіміңнен әріп тап!', bgColor: 'from-purple-400 to-violet-600', iconBg: 'bg-purple-100' },
  { icon: '⭕', title: 'Дөңгелек зат тап!', bgColor: 'from-orange-400 to-amber-500', iconBg: 'bg-orange-100' },
  { icon: '✋', title: 'Саусағыңмен сан көрсет!', bgColor: 'from-teal-400 to-emerald-500', iconBg: 'bg-teal-100' },
  { icon: '❤️', title: 'Жүрек пішінін тап!', bgColor: 'from-pink-400 to-rose-500', iconBg: 'bg-pink-100' },
];
