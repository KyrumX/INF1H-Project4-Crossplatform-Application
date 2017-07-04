package com.paladinzzz.game.screens.worldobjects.visitor;

import com.paladinzzz.game.screens.worldobjects.antObject;
import com.paladinzzz.game.screens.worldobjects.wormObject;

public class objectVisitor implements IObjectVisitor {

    @Override
    public void onAnt(antObject in) {
        in.getType();
    }

    @Override
    public void onWorm(wormObject in) {
        in.getType();
    }
}
