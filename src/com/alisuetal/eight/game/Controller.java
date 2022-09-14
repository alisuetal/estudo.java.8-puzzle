package com.alisuetal.eight.game;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private Block[] blocks; //Objetos que controlam os valores.
    private Map<Character, Directions> movements; //Mapeamento de movimentos possíveis com as teclas.
    private Character[] correctSequence = {'1', '2', '3', '4', '5', '6', '7', '8'};
    public Controller(){
        Character[] numbers = initNumbers(); //Iniciando sequência de números, dando-os ordem aleatórias.
        movements = initMovements(); //Inciando mapeamento de movimentos.

        blocks = new Block[]{
            new Block(new Directions[]{Directions.left, Directions.up}, numbers[0]), //0
            new Block(new Directions[]{Directions.left, Directions.right, Directions.up}, numbers[1]), //1
            new Block(new Directions[]{Directions.right, Directions.up}, numbers[2]), //2
            new Block(new Directions[]{Directions.left, Directions.up, Directions.down}, numbers[3]), //3
            new Block(new Directions[]{Directions.left, Directions.up, Directions.down, Directions.right}, numbers[4]), //4
            new Block(new Directions[]{Directions.right, Directions.up, Directions.down}, numbers[5]), //5
            new Block(new Directions[]{Directions.left, Directions.down}, numbers[6]), //6
            new Block(new Directions[]{Directions.left, Directions.right, Directions.down}, numbers[7]), //7
            new Block(new Directions[]{Directions.right, Directions.down}, ' '), //8
        }; //Iniciando jogo.
    }

    private Map<Character, Directions> initMovements() {
        Map<Character, Directions> movements = new HashMap<Character, Directions>();
        movements.put('A', Directions.left);
        movements.put('W', Directions.up);
        movements.put('S', Directions.down);
        movements.put('D', Directions.right);

        return movements;
    }

    private Character[] initNumbers() {
        Character[] numbers = correctSequence.clone(); //Sequência de números correta.
        List<Character> numbersToShuffle = Arrays.asList(numbers);
        Collections.shuffle(numbersToShuffle); //Embaralhamento de números do jogo.
        numbersToShuffle.toArray(numbers);

        return numbers;
    }

    public String getGame(){
        return String.format("[%s, %s, %s]\n[%s, %s, %s]\n[%s, %s, %s]", blocks[0].getValue(), blocks[1].getValue(), blocks[2].getValue(), blocks[3].getValue(), blocks[4].getValue(), blocks[5].getValue(), blocks[6].getValue(), blocks[7].getValue(), blocks[8].getValue()); //Exibição de jogo.
    }

    public boolean move(char movement){
        if(movements.get(movement) == null) //Verifica se o movimento é válido.
            return false;

        Directions direction = movements.get(movement);
        int emptyBlock = getEmptyBlock();

        if(!blocks[emptyBlock].getDirections().contains(direction)){ //Verifica se o bloco permite o movimento entrado.
            return false;
        }
        else{
            return setMovement(direction, emptyBlock); //Move os valores.
        }
    }

    private boolean setMovement(Directions direction, int emptyBlock) {
        int donorBlock; //Index do bloco que vai dar o seu valor ao bloco em branco.
        switch (direction){
            case up:
                donorBlock = emptyBlock+3;
                blocks[emptyBlock].setValue(blocks[donorBlock].getValue()); //Setando valor do bloco em branco, sendo igual ao doador.
                break;
            case down:
                donorBlock = emptyBlock-3;
                blocks[emptyBlock].setValue(blocks[donorBlock].getValue()); //Setando valor do bloco em branco, sendo igual ao doador.
                break;
            case left:
                donorBlock = emptyBlock+1;
                blocks[emptyBlock].setValue(blocks[donorBlock].getValue()); //Setando valor do bloco em branco, sendo igual ao doador.
                break;
            case right:
                donorBlock = emptyBlock-1;
                blocks[emptyBlock].setValue(blocks[donorBlock].getValue()); //Setando valor do bloco em branco, sendo igual ao doador.
                break;
            default:
                return false;
        }
        blocks[donorBlock].setValue(' '); //Esvaziando o bloco doador.
        return true;
    }

    public int getEmptyBlock(){ //Retornar o index do bloco em branco.
        int index = 0;
        for(Block block : blocks){
            if(block.getValue() == ' ')
                return index;
            index++;
        }
        return -1;
    }

    public boolean hasWon() { //Checar se o jogo já terminou.
        Character[] gameNumbers = new Character[8];

        for(Integer x = 0; x < 8; x++)
            gameNumbers[x] = blocks[x].getValue();

        if(Arrays.equals(correctSequence, gameNumbers))
            return true;
        return false;
    }
}
