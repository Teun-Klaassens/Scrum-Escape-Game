package org.scrumEscape.interfaces;

/**
 * Interface voor het hint systeem.
 * Implementeert het Strategy pattern om verschillende soorten hints mogelijk te maken.
 */
public interface Hint {
    /**
     * Geeft een hint terug voor de gegeven context
     * @param context De naam of context van waar de hint voor is
     * @return De hint tekst
     */
    String getHint(String context);
    
    /**
     * Geeft het type hint terug
     * @return De naam van het hint type
     */
    String getType();
}
