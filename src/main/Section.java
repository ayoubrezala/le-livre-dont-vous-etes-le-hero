package main;
import java.util.List;

public class Section {
    private final String titre;
    private final String contenu;

    public Section(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }
}

