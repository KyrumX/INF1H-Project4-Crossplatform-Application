package com.paladinzzz.game.screens.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.paladinzzz.game.screens.worldobjects.bounceObject;
import com.paladinzzz.game.screens.worldobjects.groundObject;
import com.paladinzzz.game.sprites.Mole;

//Deze klas registreert contact tussen twee fixtures in onze wereld
public class CollisionListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        boolean isAmole = false;
        boolean isBmole = false;
        Fixture fixOne = contact.getFixtureA();
        Fixture fixTwo = contact.getFixtureB();
        Object udA = fixOne.getBody().getUserData();
        Object udB = fixTwo.getBody().getUserData();

        if(udA instanceof Mole) {
            isAmole = true;
            isBmole = false;
        }
        else if (udB instanceof Mole) {
            isBmole = true;
            isAmole = false;
        }

        if((isAmole) && (udB instanceof bounceObject)) {
            ((Mole) udA).killMole();
        }
        else if((isBmole) && (udA instanceof bounceObject)) {
            ((Mole) udB).killMole();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
