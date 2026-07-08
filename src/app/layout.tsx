import type { Metadata } from "next";
import { Nunito, Inter } from "next/font/google";
import "./globals.css";
import { GameProvider } from "../context/GameContext";

// Display/heading font — bold, rounded, energetic
const nunito = Nunito({
  variable: "--font-display",
  subsets: ["latin", "cyrillic"],
  weight: ["400", "500", "600", "700", "800", "900"],
  display: "swap",
});

// Body font — clean, readable
const inter = Inter({
  variable: "--font-sans",
  subsets: ["latin", "cyrillic"],
  weight: ["400", "500", "600", "700"],
  display: "swap",
});

export const metadata: Metadata = {
  title: "BOOK GAMES - Кітап оқу ойындары",
  description: "Балаларға арналған интерактивті кітап оқу ойындары",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="kk" className="h-full">
      <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
      </head>
      <body className={`${nunito.variable} ${inter.variable} font-sans h-full bg-slate-50 dark:bg-[#0F172A] text-slate-900 dark:text-slate-100 min-h-full flex flex-col antialiased transition-colors duration-300`}>
        <GameProvider>
          {children}
        </GameProvider>
      </body>
    </html>
  );
}
