package com.alisuetal.eight.game;

import java.io.IOException;

public class Main {
    //Código elaborado e desenvolvido por Alison Alves.
    //LinkedIn: https://www.linkedin.com/in/alison-alves/
    //GitHub: https://github.com/alisuetal
    public static void main(String[] args) throws IOException {
        Controller game = new Controller();
        char input = ' ';
        boolean hasWon;
        int move = 1;

        System.out.print("[*] Bem-vindo ao jogo dos 8.\n");

        do{
            hasWon = game.hasWon();
            if(hasWon){
                System.out.print("[*] Parábens! Jogo vencido com " + move + " jogadas!");
            }
            else{
                System.out.print("[*] Jogada: " + move + "\n");
                System.out.print("[*] Ações:\n| W = Mover para cima.\n| A = Mover para esquerda.\n| S = Mover para baixo.\n| D = Mover para direita.\n| Q = Sair do jogo.\n\n");
            }

            System.out.print(game.getGame());

            if(!hasWon){ //Só recebe direções se o jogo não tiver terminado.
                System.out.print("\n\n[?] Movimento escolido: ");
                input = (char) System.in.read();
                System.in.read();

                if(!game.move(String.valueOf(input).toUpperCase().charAt(0)) && String.valueOf(input).toUpperCase().charAt(0) != 'Q'){ //Recebe o input e faz validações para ignorar case.
                    System.out.print("[!] Movimento inválido. Por favor, tente novamente!");
                }

                System.out.print("\n\n\n");
                System.out.print("\033[H\033[2J"); //Limpa o console, se permitido.
                move++;
            }
        }while(input != 'Q' && input != 'q' && !hasWon); //Validações para continuar o jogo.
        System.out.print("\n\n[!] Obrigado por jogar!");
    }
}
