package com.alisuetal.eight.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Block {
    private Directions[] directions; //Direções suportadas pelo bloco.
    private char value; //Valor do bloco.

    public Block(Directions[] directions, char value){
        this.directions = directions;
        this.value = value;
    }
    public char getValue() {
        return value;
    }
    public List<Directions> getDirections(){
        return Arrays.stream(directions).toList();
    }

    public void setValue(char value){
        this.value = value;
    }
}
