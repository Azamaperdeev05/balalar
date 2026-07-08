import type { Metadata } from "next";
import { Manrope } from "next/font/google";
import "./globals.css";
import { GameProvider } from "../context/GameContext";

const manrope = Manrope({
  variable: "--font-manrope",
  subsets: ["latin", "cyrillic"],
  weight: ["400", "500", "600", "700", "800"],
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
      <body className={`${manrope.variable} font-sans h-full bg-[#0F172A] text-slate-100 min-h-full flex flex-col antialiased`}>
        <GameProvider>
          {children}
        </GameProvider>
      </body>
    </html>
  );
}
