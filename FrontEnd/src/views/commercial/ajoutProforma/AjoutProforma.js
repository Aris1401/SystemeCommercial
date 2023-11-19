import { CButton, CCol, CForm, CFormInput, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle, CRow } from '@coreui/react';
import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react'
import { makeRequest } from 'src/Api';
import { validerDemandeProforma } from '../demandeProforma/DemandeProforma';

export var getDemandeProforma = (idDemandeProforma) => {
    if (idDemandeProforma === -1) return null;

    return new Promise((resolve, reject) => {
        makeRequest({
            url: `proformas/demande/${idDemandeProforma}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data);
            },
            failureCallback: (error) => {
                reject(error);
            }
        })
    })
}

export var ajouterProforma = (data) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'proformas',
            requestType: 'POST',
            values: data,
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export var ajouterProformaWithArticleBesoinAchat = (data, idArticleBesoinAchat) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `/proformas/articlebesoinachat/${idArticleBesoinAchat}`,
            requestType: 'POST',
            values: data,
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

const AjoutProforma = forwardRef((props, ref) => {
    // Modal visibility
    const [visibilityAjoutModal, setVisibilityAjoutModal] = useState(false)

    // Article besoin achat
    const [ idArticleBesoinAchat, setIdArticleBesoinAchat ] = useState(-1)

    // Demande de proforma
    const [idDemandeProforma, setIdDemandeProforma] = useState(-1)
    const [demandeProforma, setDemandeProforma] = useState()
    useImperativeHandle(ref, () => ({
        showModal: (idDemandeProforma, idArticleBesoinAchat) => {
            setVisibilityAjoutModal(true)
            setIdDemandeProforma(idDemandeProforma)
            setIdArticleBesoinAchat(idArticleBesoinAchat)
        },
        hideModal: () => {
            setIdDemandeProforma(-1)
            setVisibilityAjoutModal(false)
        }
    }));

    useEffect(() => {
        if (idDemandeProforma !== -1) {
            getDemandeProforma(idDemandeProforma).then((data) => {
                setDemandeProforma(data)
            })
        }
    }, [idDemandeProforma])

    // Validation d'ajout
    const quantiteRef = useRef()
    const prixUnitaireRef = useRef()
    const handleValidationAjoutProforma = (e) => {
        e.preventDefault();

        if (idDemandeProforma === -1 || idArticleBesoinAchat == -1) return;

        let proforma = {
            quantite: quantiteRef.current.value,
            prixUnitaire: prixUnitaireRef.current.value,
            idArticle: demandeProforma.articleBesoinAchat.article.idArticle,
            idFournisseur: demandeProforma.fournisseur.idFournisseur,
        }

        validerDemandeProforma(idDemandeProforma).then((data) => {
            ajouterProformaWithArticleBesoinAchat(proforma, idArticleBesoinAchat).then(() => {
                console.log(data);
                setVisibilityAjoutModal(false)
            })
        })
    }

    return (
        <CModal
            visible={visibilityAjoutModal}
            onClose={() => {
                setVisibilityAjoutModal(false)
            }}
            size='lg'
        >
            <CForm
                onSubmit={handleValidationAjoutProforma}
            >
                <CModalHeader>
                    <div className='d-flex flex-column'>
                        <CModalTitle>
                            Ajouter Proforma
                        </CModalTitle>
                        <div className='d-flex gap-2'>
                            <p className='badge text-bg-info text-wrap'>Fournisseur: {demandeProforma && demandeProforma.fournisseur.nom}</p>
                            <p className='badge text-bg-success text-wrap'>Article: {demandeProforma && demandeProforma.articleBesoinAchat.article.nom}</p>
                            <p className='badge text-bg-light    text-wrap'>Unite: {demandeProforma && demandeProforma.articleBesoinAchat.unite.nom}</p>
                        </div>
                    </div>
                </CModalHeader>

                <CModalBody>

                    <CRow>
                        <CCol>
                            <div className='d-flex g-2 row'>
                                <CFormInput floatingLabel="Quantite" ref={quantiteRef} type='number' className='col-6' step={0.01} />
                                <CFormInput floatingLabel="Prix unitaire" ref={prixUnitaireRef} type='number' className='col-6' step={0.01} />
                            </div>
                        </CCol>
                    </CRow>
                </CModalBody>

                <CModalFooter>
                    <CButton className='fw-semibold' type='submit'>Ajouter proforma</CButton>
                </CModalFooter>
            </CForm>
        </CModal>
    )
});

AjoutProforma.displayName = "AjoutProforma";
export default AjoutProforma