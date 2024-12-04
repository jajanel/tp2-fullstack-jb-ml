import MaCritique from "./MaCritique.jsx";
import CritiquePrecedente from "./CritiquePrecedente.jsx";
import { useState } from "react";
import { ajouterCritique } from "../../scripts/http-critiques.js";

export default function ListeCritiques(props) {
    const [erreurServeur, setErreurServeur] = useState({ error: undefined, message: "Aucune erreur, pour l'instant.." });

    /**
     * Fonction pour appeller les fonctions de gestionCatalogueCritique pour créer une critique
     * @param event l'évènement de submit du formulaire
     * */
    function handleSubmitFormCreerCritique(event) {
        event.preventDefault();
        const formData = new FormData(event.target);

        const nouvelleCritique = {
            raceOiseau: props.race,
            temperament: formData.get("temperament"),
            beaute: formData.get("beaute"),
            utilisation: formData.get("utilisation"),
        }
        ajouterNouvelleCritique(nouvelleCritique);

        //TODO: VÉRIFIER CHAMPS:

        // si champs pas vides, alors ok
    }

    async function ajouterNouvelleCritique(critique) {
        try {
            const nouvelID = await ajouterCritique(critique);
            critique.id = nouvelID;
            props.setDataCritiques(old => [critique, ...old]);
        } catch (e) {
            console.log(e);
            setErreurServeur({ error: "error", message: e.message });
        }
    }

    return (
        <>
            <div>
                {props.estOuvertCritique && (
                    <div className="popup bg-dark-subtle">
                        <MaCritique
                            id={props.id}
                            categorie={props.categorie}
                            race={props.race}
                            creerCritique={handleSubmitFormCreerCritique}
                        />
                        <div className="my-4">
                            <div className="row text-start m-4">
                                <div className="col my-2">
                                    <h5 className="text-uppercase display-6 m-3 text-start">Visualiser les critiques</h5>
                                    <hr />
                                    {props.dataCritiques.map(critique => (
                                        <CritiquePrecedente
                                            key={critique.id}
                                            idCritique={critique.id}
                                            temperament={critique.temperament}
                                            beaute={critique.beaute}
                                            utilisation={critique.utilisation}
                                            stateDataCritique={[props.dataCritiques, props.setDataCritiques]}
                                            rechargerCritiques={props.fetchDataCritiqueParOiseau}
                                        />
                                    ))}
                                </div>
                            </div>
                        </div>
                        <div className="row px-4">
                            <div className="btn-wrapper text-center d-flex justify-content-start modal-footer">
                                <button className="btn btn-danger btn-lg" onClick={props.toggleModalCritique}>Fermer</button>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
}