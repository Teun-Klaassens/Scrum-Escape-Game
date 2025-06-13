package org.scrumEscape.base;

public class Assistant {
    private final String name;
    private final String[] hints;
    private final String[] educationalTools;
    private final String[] motivationalMessages;
    
    public Assistant(String name, String[] hints, String[] educationalTools, String[] motivationalMessages) {
        this.name = name;
        this.hints = hints;
        this.educationalTools = educationalTools;
        this.motivationalMessages = motivationalMessages;
    }

    public String getName() {
        return name;
    }

    public String getHint() {
        return hints[(int) (Math.random() * hints.length)];
    }
    
    public String getEducationalTool() {
        return educationalTools[(int) (Math.random() * educationalTools.length)];
    }
    
    public String getMotivationalMessage() {
        return motivationalMessages[(int) (Math.random() * motivationalMessages.length)];
    }
}
