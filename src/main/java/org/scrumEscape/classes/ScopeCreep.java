package org.scrumEscape.classes;

 public class ScopeCreep extends Monster {
     private boolean isOpgelost = false;

     @Override
     public void toonImpediment() {
         System.out.println("SCOPE CREEP is verschenen!");
     }

        public void oplossen() {
            System.out.println("Je hebt de scope creep verslagen!");
            isOpgelost = true;
        }

 }
