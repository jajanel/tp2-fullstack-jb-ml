export default function MaCritique(props) {
    return (<>
        <div className="bg-warning-subtle rounded-3 p-3">
            <form id="critiqueForm" onSubmit={(event) => props.creerCritique(event, props.id)}>

                <div className="row text-start m-4">
                    <div className="col col-11">
                        <h5 className="text-uppercase display-6 m-3 text-start">Rédiger ma critique
                            pour: {props.categorie} {props.race} </h5>
                        <hr/>
                        {/*Trois range de 0 à 100 qui n'accepent que des entiers (bonds de 1), mais en réalité sont des doubles dans le backend (pour calclul de la note globale)*/}
                        <div className="pb-4">
                            <h4 className="form-label">Tempérament:</h4>
                            <div className="d-flex align-items-center">
                                <label className="me-2">0</label>
                                <input type="range" className="form-range form-select-lg" name="temperament" min={0}
                                       max={100} step="10"/>
                                <label className="ms-2">100</label>
                            </div>
                        </div>
                        <div className="pb-4">
                            <h4 className="form-label">Beauté:</h4>
                            <div className="d-flex align-items-center">
                                <label className="me-2">0</label>
                                <input type="range" className="form-range form-select-lg" name="beaute" min={0}
                                       max={100} step="10"/>
                                <label className="ms-2">100</label>
                            </div>
                        </div>
                        <div className="pb-4">
                            <h4 className="form-label">Utilisation:</h4>
                            <div className="d-flex align-items-center">
                                <label className="me-2">0</label>
                                <input type="range" className="form-range form-select-lg" name="utilisation" min={0}
                                       max={100} step="10"/>
                                <label className="ms-2">100</label>
                            </div>
                        </div>
                    </div>
                    <div className="col d-flex justify-content-center">
                        <div className="text-center text-uppercase form-select-lg"> ID #</div>
                    </div>
                </div>
                <hr/>
                {
                    props.chargementAjouter ?
                        <div className="btn-wrapper text-center d-flex justify-content-end">
                            <button className="btn btn-warning" type="button" disabled>
                                <span className="spinner-border spinner-border-sm" role="status"
                                      aria-hidden="true"></span>
                                Chargement
                            </button>
                        </div> :
                        !props.erreurServeur.error ?
                            <div className="btn-wrapper text-center d-flex justify-content-end ">
                                <button type="submit" className={"btn btn-success  btn-lg m-3"}>Ajouter</button>
                            </div>
                            :
                            <div className="btn-wrapper text-center d-flex justify-content-end ">
                                <button type="submit" className={"btn btn-danger disabled btn-lg m-3"}>Ajouter</button>
                            </div>

                }
            </form>
        </div>
    </>)
}