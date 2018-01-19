package fr.utt.lo02.huitamericain;

/**
 * Cette énumération représente les différents événements du jeu. Il permet aux Observables d'envoyer une notification via notifyObservers(arg) avec un arg un Evenement que la vue traitera.
 * @author Lionel EA
 *
 */
public enum Evenement {
	inputError,
	posableError,
	piocher,
	carteJouee,
	debutTour,
	finTour,
	afficherCarte,
	vulnerable,
	pasVulnerable,
	carteSucces,
	carteEchec,
	contreCarteSucces,
	contreCarteEchec,
	fin;
}
