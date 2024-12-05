import MaCritique from "./MaCritique.jsx";
import CritiquePrecedente from "./CritiquePrecedente.jsx";
import {useState} from "react";
import {ajouterCritique} from "../scripts/http-critiques.js";

export default function ListeCritiques(props) {
    const [erreurServeur, setErreurServeur] = useState({error: undefined, message: "Aucune erreur, pour l'instant.."});
    const [chargementAjouter, setChargementAjouter] = useState(false);
    function handleSubmitFormCreerCritique(event) {
        event.preventDefault();
        const formData = new FormData(event.target);

        const nouvelleCritique = {
            raceOiseau: props.race,
            categorieOiseau: props.categorie,
            temperament: formData.get("temperament"),
            beaute: formData.get("beaute"),
            utilisation: formData.get("utilisation"),
        };
        ajouterNouvelleCritique(nouvelleCritique);
    }

    async function ajouterNouvelleCritique(critique) {
        setChargementAjouter(true);
        try {
            critique.id = await ajouterCritique(critique);
            props.setDataCritiques(old => [critique, ...old]);
        } catch (e) {
            console.log(e);
            setErreurServeur({error: "error", message: e.message});
        } finally {
            setChargementAjouter(false);
        }
    }

    return (<>
        <div>
            {props.estOuvertCritique && (<div className="popup bg-dark-subtle">
                <MaCritique
                    id={props.id}
                    categorie={props.categorie}
                    race={props.race}
                    creerCritique={handleSubmitFormCreerCritique}
                    setChargementAjouter={setChargementAjouter}
                    chargementAjouter={chargementAjouter}
                    erreurServeur={erreurServeur}
                    setErreurServeur={setErreurServeur}
                />
                <div className="my-4">
                    <div className="row text-start m-4">
                        <div className="col my-2">
                            <h5 className="text-uppercase display-6 m-4 text-start">Visualiser les critiques</h5>
                            <hr/>
                            {props.isLoading ? <div className="spinner-border text-primary" role="status">
                                <span className="visually-hidden">Chargement...</span>
                            </div> : !erreurServeur.error ?
                                <div className="row">
                                    {props.dataCritiques.map((critique, index) => (
                                        <div className="col-xl-4 col-lg-6 " key={critique.id}>
                                            <CritiquePrecedente
                                                idCritique={critique.id}
                                                temperament={critique.temperament}
                                                beaute={critique.beaute}
                                                utilisation={critique.utilisation}
                                                stateDataCritique={[props.dataCritiques, props.setDataCritiques]}
                                                rechargerCritiques={props.fetchDataCritiqueParOiseau}
                                                isLoading={props.isLoading}
                                                setIsLoading={props.setIsLoading}
                                            />
                                        </div>
                                    ))}
                                </div> :
                                <div className="alert alert-danger" role="alert">
                                    {erreurServeur.message}
                                </div>
                            }
                        </div>
                    </div>
                </div>
                <div className="row px-4 sticky-bottom">
                    <div className="btn-wrapper text-center d-flex justify-content-start modal-footer">
                        <button className="btn btn-danger " onClick={props.toggleModalCritique}>Fermer
                        </button>
                    </div>
                </div>
            </div>)}
        </div>
    </>);
}