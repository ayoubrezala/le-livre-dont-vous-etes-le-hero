package main;
import java.util.List;

public class Livre {
    private final String titre;
    private final String auteur;
    private final List<Section> sections;

    public Livre(String titre, String auteur, List<Section> sections) {
        this.titre = titre;
        this.auteur = auteur;
        this.sections = sections;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public List<Section> getSections() {
        return sections;
    }
}


