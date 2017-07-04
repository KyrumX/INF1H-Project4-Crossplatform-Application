package com.paladinzzz.game.screens.worldobjects.visitor;


import com.paladinzzz.game.screens.worldobjects.antObject;
import com.paladinzzz.game.screens.worldobjects.wormObject;

interface IObjectVisitor {
    void onAnt(antObject in);
    void onWorm(wormObject in);
}
