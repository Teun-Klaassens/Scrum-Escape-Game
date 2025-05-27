package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;

import org.scrumEscape.classes.Monster;

import java.util.Scanner;

public class TIA extends Kamer {
    private Scanner scanner;
    private final Monster empiricismMonster;
    private int score = 0;
    private boolean gameCompleted = false;
    private final int PASSING_SCORE = 3;
    private final int TOTAL_QUESTIONS = 5;

    public TIA() {
        this.empiricismMonster = new Monster();
    }

    @Override
    public String getBeschrijving() {
        // TIA = Transparency, Inspection, Adaptation.
        // Leer dit!!
        return "In deze kamer het eindspel. Pas als je hier doorheen komt, heb je gewonnen! Zoek uit wat TIA is!";
    }

    @Override
    public void start() {
        scanner = new Scanner(System.in);
        
        showIntroduction();
        int totalScore = doQuiz();
        
        if (totalScore >= PASSING_SCORE) {
            showSuccessResult();
            gameCompleted = true;
        } else {
            showFailureResult();
        }
    }
    
    private void showIntroduction() {
        System.out.println("\n==== WELKOM BIJ DE FINALE KAMER: TIA ====");
        System.out.println("Dit is de laatste uitdaging. Toon je kennis van de onderdelen van Scrum");
        System.out.println("Je moet minimaal " + PASSING_SCORE + " van de " + TOTAL_QUESTIONS + " vragen goed beantwoorden om deze kamer te voltooien.");
        System.out.println("\nDruk op ENTER om te beginnen...");
        scanner.nextLine();
    }
    
    private int doQuiz() {
        score = 0;

        System.out.println("\nVraag 1: Waar staat 'TIA' voor in de context van Scrum?");
        System.out.println("1) Technical Implementation Analysis");
        System.out.println("2) Transparency, Inspection, Adaptation");
        
        int antwoord = getNumberInput(2);
        if (antwoord == 2) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Dat is niet correct.");
        }

        System.out.println("\nVraag 2: Wat betekent 'Transparency' in Scrum?");
        System.out.println("1) Alle teamleden hebben dezelfde fysieke werkplek");
        System.out.println("2) Het presenteren van feiten zoals ze zijn, waarbij iedereen dezelfde definitie van 'klaar' gebruikt");
        System.out.println("3) Alle code moet open source zijn");
        
        antwoord = getNumberInput(3);
        if (antwoord == 2) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Dat is niet correct.");
        }


        System.out.println("\nVraag 3: Wie is verantwoordelijk voor 'Inspection' in Scrum?");
        System.out.println("1) Alleen de Scrum Master");
        System.out.println("2) Externe auditors");
        System.out.println("3) Iedereen in het Scrum Team");
        
        antwoord = getNumberInput(3);
        if (antwoord == 3) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Dat is niet correct.");
        }

        System.out.println("\nVraag 4: Wat is het doel van 'Adaptation' in Scrum?");
        System.out.println("1) Het aanpassen van het proces om beter te worden dan gisteren");
        System.out.println("2) Het aanpassen van de planning zodat alles altijd af komt");
        System.out.println("3) Het aanpassen van de requirements aan de wensen van het team");
        
        antwoord = getNumberInput(4);
        if (antwoord == 1) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Dat is niet correct.");
        }


        System.out.println("\nVraag 5: Wat betekent 'empiricism' in de context van Scrum?");
        System.out.println("1) Dat alle beslissingen alleen door de managers worden genomen");
        System.out.println("2) Werken op basis van feiten, ervaring en bewijs in plaats van fictieve plannen");
        System.out.println("3) Dat alle werknemers even belangrijk zijn");
        System.out.println("4) Dat teams alleen experimenten doen");
        
        antwoord = getNumberInput(4);
        if (antwoord == 2) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Dat is niet correct.");
        }

        
        return score;
    }
    
    private void showSuccessResult() {
        System.out.println("\n==== GEFELICITEERD! ====");
        System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord!");
        System.out.println("Je begrijpt de drie pijlers van empiricism in Scrum");
        System.out.println("============================");
    }
    
    private void showFailureResult() {
        System.out.println("\n==== HELAAS ====");
        System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord, maar je had er minstens " + PASSING_SCORE + " nodig.");
        System.out.println("Het begrip van de drie pijlers van empiricism is essentieel voor Scrum:");
        System.out.println("- Transparency");
        System.out.println("- Inspection");
        System.out.println("- Adaptation ");
        System.out.println("\nHet empiricism-monster verschijnt! Je team verliest zich in chaos zonder deze fundamentele principes!");
        empiricismMonster.attack("TIA");
        System.out.println("\nProbeer het nog eens, en onthoud TIA: Transparency, Inspection, Adaptation!");
    }
    
    private int getNumberInput(int maxOptions) {
        int answer = 0;
        boolean validAnswer = false;
        
        while (!validAnswer) {
            System.out.print("\nJouw antwoord (1-" + maxOptions + "): ");
            try {
                answer = Integer.parseInt(scanner.nextLine());
                if (answer >= 1 && answer <= maxOptions) {
                    validAnswer = true;
                } else {
                    System.out.println("Voer een nummer in tussen 1 en " + maxOptions);
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer een geldig nummer in");
            }
        }
        return answer;
    }

    public boolean isGameCompleted() {
        return gameCompleted;
    }
}
