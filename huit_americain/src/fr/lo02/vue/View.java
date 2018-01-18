package fr.lo02.vue;

/**
 * Les classes qui implémentent cette interface sont des vues.
 * Le jeu peut posséder plusieurs vues, c'est pourquoi on crée une interface View pour pour pouvoir spécifier dans le contrôleur quelle vue on souhaite utiliser.
 * Dans notre cas, il y aura deux vues : ConsoleView et le GUI
 * @author Lionel EA
 *
 */
public interface View {	
	public void setLastInput(Object arg);
	
}
