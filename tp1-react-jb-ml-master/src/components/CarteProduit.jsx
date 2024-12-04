import { useState, useEffect } from "react";
import DescriptionOiseau from "./DescriptionOiseau.jsx";
import ConfirmationSupression from "./ConfirmationSupression.jsx";
import ListeCritiques from "./ListeCritiques.jsx";
import { fetchCritiqueParOiseau, supprimerToutesCritiqueParOiseau, ajouterCritique } from "../scripts/http-critiques.js";

export default function CarteProduit(props) {
    const [estOuvertDescription, setEstOuvertDescription] = useState(false);
    const [estOuvertCritique, setEstOuvertCritique] = useState(false);
    const [estOuvertConfirmation, setEstOuvertConfirmation] = useState(false);
    const [oiseauASupprimer, setOiseauASupprimer] = useState(null);
    const [dataCritiques, setDataCritiques] = useState([]);
    const [erreurServeur, setErreurServeur] = useState({ error: undefined, message: "Aucune erreur, pour l'instant.." });
    const [isLoading, setIsLoading] = useState(false);

    const toggleModalDescription = () => {
        setEstOuvertDescription(!estOuvertDescription);
    };

    const toggleModalCritique = () => {
        setEstOuvertCritique(!estOuvertCritique);
    };

    useEffect(() => {
        if (estOuvertCritique) {
            fetchDataCritiqueParOiseau();
        }
    }, [estOuvertCritique]);

    const toggleModalConfirmation = () => {
        setEstOuvertConfirmation(!estOuvertConfirmation);
    };

    const handleSupprimerOiseau = (idOiseau) => {
        setOiseauASupprimer(idOiseau);
        toggleModalConfirmation();
    };

    const confirmerSuppressionOiseau = () => {
        if (oiseauASupprimer !== null) {
            props.tuerOiseau(oiseauASupprimer);
            setOiseauASupprimer(null);
            toggleModalConfirmation();
        }
    };



    async function fetchDataCritiqueParOiseau() {
        setIsLoading(true);
        try {
            const donneesServeur = await fetchCritiqueParOiseau(props.race);
            setDataCritiques(donneesServeur);
        } catch (erreurServeur) {
            setErreurServeur({ error: "Erreur de fetching des produits du serveur", message: erreurServeur.message });
        } finally {
            setIsLoading(false);
        }
    }

    return (
        <>
            <div className="card m-3 border-dark border-3 bg-secondary shadow-lg">
                <img src={props.srcImage} className="card-img-top" alt="image d'oiseau" />
                <div className="card-body">
                    <h4 className="card-title text-uppercase">{props.categorie} {props.race}</h4>
                </div>
                <div className="btn-wrapper text-center d-flex justify-content-center m-3">
                    <a className="btn btn-sm btn-success shadow boutonCarte me-4" onClick={toggleModalDescription}> description</a>
                    <DescriptionOiseau
                        id={props.id}
                        categorie={props.categorie}
                        race={props.race}
                        prix={props.prix}
                        origine={props.origine}
                        date={props.datePublication}
                        srcImage={props.srcImage}
                        estOuvertDescription={estOuvertDescription}
                        toggleModalDescription={toggleModalDescription}
                        tuerOiseau={confirmerSuppressionOiseau}
                    />
                    <a className="btn btn-sm btn-info shadow boutonCarte" onClick={toggleModalCritique}> critiques</a>
                    <ListeCritiques
                        key={props.id}
                        id={props.id}
                        categorie={props.categorie}
                        race={props.race}
                        estOuvertCritique={estOuvertCritique}
                        toggleModalCritique={toggleModalCritique}
                        dataCritiques={dataCritiques}
                        setDataCritiques={setDataCritiques}
                        isLoading={isLoading}
                        erreurServeur={erreurServeur}
                        fetchDataCritiqueParOiseau={fetchDataCritiqueParOiseau}
                    />
                </div>
                <div className="card-footer">
                    <div className="btn-wrapper text-center d-flex justify-content-between">
                        <p className="card-title text-muted">Supprimer l'oiseau</p>
                        <button type="button" className="btn btn-dark text-white btn-sm bouton-delete"
                                onClick={() => handleSupprimerOiseau(props.id)}><span aria-hidden="true">&times;</span>
                        </button>
                        <ConfirmationSupression
                            estOuvertConfirmation={estOuvertConfirmation}
                            toggleModalConfirmation={toggleModalConfirmation}
                            confirmerSuppressionOiseau={confirmerSuppressionOiseau}
                        />
                    </div>
                </div>
            </div>
        </>
    );
}