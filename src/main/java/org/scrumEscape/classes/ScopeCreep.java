package org.scrumEscape.classes;

 public class ScopeCreep extends Monster {
     private boolean isOpgelost = false;

     @Override
     public void toonImpediment() {
         System.out.println("SCOPE CREEP!");
         System.out.println("Er zijn essentiële taken vergeten. De planning loopt uit de hand.");
         System.out.println("Het team heeft moeite om de sprint goed te plannen en wordt overweldigd door veranderende eisen!");
     }
        public void oplossen() {
            System.out.println("Je hebt de scope creep verslagen!");
            isOpgelost = true;
        }

 }
