package org.scrumEscape.classes.Kamers;

import org.scrumEscape.base.Kamer;

import org.scrumEscape.base.Kamer;
import org.scrumEscape.classes.Monster;
import org.scrumEscape.controllers.MenuController;

import java.util.Scanner;

public class TIA extends Kamer {
    private Scanner scanner;
    private final Monster empiricismMonster;
    private int score = 0;
    private boolean gameCompleted = false;
    private final int PASSING_SCORE = 5;
    private final int TOTAL_QUESTIONS = 7;

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
            System.out.println("Correct! TIA staat voor Transparency, Inspection en Adaptation.");
            score++;
        } else {
            System.out.println("Dat is niet correct. TIA staat voor Transparency, Inspection, Adaptation.");
        }

        System.out.println("\nVraag 2: Wat betekent 'Transparency' in Scrum?");
        System.out.println("1) Alle teamleden hebben dezelfde fysieke werkplek");
        System.out.println("2) Het presenteren van feiten zoals ze zijn, waarbij iedereen dezelfde definitie van 'klaar' gebruikt");
        System.out.println("3) Alle code moet open source zijn");
        
        antwoord = getNumberInput(3);
        if (antwoord == 2) {
            System.out.println("Correct! Transparantie betekent dat feiten eerlijk worden gepresenteerd en dat iedereen dezelfde definitie heeft van belangrijke begrippen.");
            score++;
        } else {
            System.out.println("Dat is niet correct. Transparantie gaat over het eerlijk presenteren van feiten en het delen van een gemeenschappelijk begrip.");
        }


        System.out.println("\nVraag 3: Wie is verantwoordelijk voor 'Inspection' in Scrum?");
        System.out.println("1) Alleen de Scrum Master");
        System.out.println("2) Externe auditors");
        System.out.println("3) Iedereen in het Scrum Team");
        
        antwoord = getNumberInput(3);
        if (antwoord == 3) {
            System.out.println("Correct! Inspectie wordt uitgevoerd door iedereen in het Scrum Team. ");
            score++;
        } else {
            System.out.println("Dat is niet correct. Inspectie is een verantwoordelijkheid van iedereen in het Scrum Team.");
        }

        System.out.println("\nVraag 4: Wat is het doel van 'Adaptation' (Aanpassing) in Scrum?");
        System.out.println("1) Het aanpassen van het proces om beter te worden dan gisteren");
        System.out.println("2) Het aanpassen van de planning zodat alles altijd af komt");
        System.out.println("3) Het aanpassen van de requirements aan de wensen van het team");
        System.out.println("4) Het aanpassen van de werkuren naar een 4-daagse werkweek");
        
        antwoord = getNumberInput(4);
        if (antwoord == 1) {
            System.out.println("Correct! Aanpassing gaat over continue verbetering en het vermogen om te veranderen op basis van inspectie.");
            score++;
        } else {
            System.out.println("Dat is niet correct. Aanpassing gaat over continue verbetering gebaseerd op wat we leren uit inspectie.");
        }


        System.out.println("\nVraag 5: Wat betekent 'empiricisme' in de context van Scrum?");
        System.out.println("1) Dat alle beslissingen alleen door de managers worden genomen");
        System.out.println("2) Werken op basis van feiten, ervaring en bewijs in plaats van fictieve plannen");
        System.out.println("3) Dat alle werknemers even belangrijk zijn");
        System.out.println("4) Dat teams alleen experimenten doen");
        
        antwoord = getNumberInput(4);
        if (antwoord == 2) {
            System.out.println("Correct! Empiricisme betekent werken op basis van feiten, ervaring en bewijs.");
            score++;
        } else {
            System.out.println("Dat is niet correct. Empiricisme betekent werken op basis van feiten, ervaring en bewijs in plaats van fictieve plannen.");
        }


        System.out.println("\nVraag 6: Welke Scrum ceremonie is een goed voorbeeld van 'Inspection' in de praktijk?");
        System.out.println("1) Daily Scrum");
        System.out.println("2) Sprint Planning");
        System.out.println("3) Sprint Review");
        System.out.println("4) Alle bovenstaande");
        
        antwoord = getNumberInput(4);
        if (antwoord == 4) {
            System.out.println("Correct! Alle Scrum ceremonies bevatten een element van inspectie, maar op verschillende niveaus.");
            score++;
        } else {
            System.out.println("Dat is niet helemaal correct. Alle Scrum ceremonies bevatten elementen van inspectie, maar op verschillende niveaus.");
            System.out.println("Ongeldig antwoord gegeven.");
        }


        System.out.println("\nVraag 7: Welke Scrum ceremonie is het meest direct gericht op 'Adaptation'?");
        System.out.println("1) Sprint Planning");
        System.out.println("2) Sprint Retrospective");
        System.out.println("3) Sprint Review");
        System.out.println("4) Daily Scrum");
        
        antwoord = getNumberInput(4);
        if (antwoord == 2) {
            System.out.println("Correct! De Sprint Retrospective is specifiek gericht op het verbeteren van processen en samenwerking.");
            score++;
        } else {
            System.out.println("Dat is niet correct. De Sprint Retrospective is de ceremonie die specifiek gericht is op aanpassing en verbetering.");
            System.out.println("Ongeldig antwoord gegeven.");
        }
        
        return score;
    }
    
    private void showSuccessResult() {
        System.out.println("\n==== GEFELICITEERD! ====");
        System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord!");
        System.out.println("Je begrijpt de drie pijlers van empiricisme in Scrum:");
        System.out.println("- Transparency: Het presenteren van de feiten zoals ze zijn, zonder verborgen agenda's");
        System.out.println("- Inspection: Regelmatig controleren van het product en proces door het hele team");
        System.out.println("- Adaptation: Continu verbeteren op basis van wat we leren van inspectie");
        System.out.println("\nJe hebt alle kamers doorlopen en het spel uitgespeeld! Je bent nu een Scrum Master!");
        System.out.println("============================");
    }
    
    private void showFailureResult() {
        System.out.println("\n==== HELAAS ====");
        System.out.println("Je hebt " + score + " van de " + TOTAL_QUESTIONS + " vragen correct beantwoord, maar je had er minstens " + PASSING_SCORE + " nodig.");
        System.out.println("Het begrip van de drie pijlers van empiricisme is essentieel voor Scrum:");
        System.out.println("- Transparency (Transparantie)");
        System.out.println("- Inspection (Inspectie)");
        System.out.println("- Adaptation (Aanpassing)");
        System.out.println("\nHet empiricisme-monster verschijnt! Je team verliest zich in chaos zonder deze fundamentele principes!");
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
