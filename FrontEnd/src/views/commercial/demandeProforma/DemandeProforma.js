import { CButton, CCol, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow, CToast } from '@coreui/react';
import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react'
import { makeRequest } from 'src/Api';
import AjoutProforma from '../ajoutProforma/AjoutProforma';
import CIcon from '@coreui/icons-react';
import { cilCart, cilXCircle } from '@coreui/icons';

export const getFournisseurs = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'fournisseurs',
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export const faireDemandeProforma = (demande) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'proformas/demande',
            requestType: 'POST',
            values: demande,
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export const validerDemandeProforma = (idDemandeProforma) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `proformas/demande/valider/${idDemandeProforma}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export const checkDemandeProformaByFournisseurAndArticleBesoinAchat = (idFournisseur, idArticleBesoinAchat) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `/proformas/fournisseur/${idFournisseur}/ArticleBesoinAchat/${idArticleBesoinAchat}`,
            requestType: 'GET',
            successCallback: (response) => {
                resolve(response)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    });
}

export const getProformaArticleBesoinAchat = (idArticleBesoinAchat) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `proformas/articlebesoinachat/${idArticleBesoinAchat}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

const DemandeProformaBesoin = forwardRef((props, ref) => {
    useImperativeHandle(ref, () => ({
        showModal: (idArticleBesoinAchat) => {
            setVisibilityDemandeModal(true)
            setSelectedArticleBesoinAchat(idArticleBesoinAchat)
        },
        hideModal: () => {
            setVisibilityDemandeModal(false)
        },
    }))

    // Modal visibility
    const [visibilityDemandeModal, setVisibilityDemandeModal] = useState(false)

    // Obtenir les fournisseurs
    const [fournisseurs, setFournisseurs] = useState([])
    useEffect(() => {
        getFournisseurs().then((data) => [
            setFournisseurs(data)
        ])
    }, [])

    // Selected article besoin achat
    const [selectedArticleBesoinAchat, setSelectedArticleBesoinAchat] = useState(-1)

    const handleFaireDemande = (e, idFournisseur) => {
        if (selectedArticleBesoinAchat === -1) return;

        let demande = {
            idFournisseur: idFournisseur,
            idArticleBesoinAchat: selectedArticleBesoinAchat,
        }

        faireDemandeProforma(demande).then((data) => {
            setVisibilityDemandeModal(false)
        })
    }

    // Check de demande proforma
    const [demandeProforma, setDemandeProforma] = useState({});

    useEffect(() => {
        fournisseurs.forEach(fournisseur => {
            checkDemandeProformaByFournisseurAndArticleBesoinAchat(fournisseur.idFournisseur, selectedArticleBesoinAchat)
                .then(data => {
                    setDemandeProforma(prevState => ({
                        ...prevState,
                        [fournisseur.idFournisseur]: data
                    }));
                });
        });
    }, [fournisseurs, selectedArticleBesoinAchat, visibilityDemandeModal]);

    // Ajout profroma ref
    const ajoutProformaRef = useRef()

    // Proforma article besoin achat
    const [proformas, setProformas] = useState([])
    useEffect(() => {
        getProformaArticleBesoinAchat(selectedArticleBesoinAchat).then((data) => {
            setProformas(data)
        })
    }, [selectedArticleBesoinAchat])

    return (
        <>
            <CModal
                visible={visibilityDemandeModal}
                onClose={() => {
                    setVisibilityDemandeModal(false)
                }}
                size='xl'
            >
                <CModalHeader>
                    <CModalTitle>
                        Demande de proforma
                    </CModalTitle>
                </CModalHeader>

                <CModalBody>
                    <CRow>
                        <CCol>
                            <h6>Fournisseurs</h6>

                            <div className='table-responsive p-2' style={{ height: '300px' }}>
                                <CTable>
                                    <CTableHead>
                                        <CTableRow>
                                            <CTableHeaderCell scope='col'>Nom fournisseur</CTableHeaderCell>
                                            <CTableHeaderCell scope='col'>Type de produit</CTableHeaderCell>
                                            <CTableHeaderCell scope='col'>Nom repsonsable</CTableHeaderCell>
                                            <CTableHeaderCell scope='col'>Contact fournisseur</CTableHeaderCell>
                                            <CTableHeaderCell scope='col'>Email fournisseur</CTableHeaderCell>
                                            <CTableHeaderCell scope='col'></CTableHeaderCell>
                                        </CTableRow>
                                    </CTableHead>

                                    <CTableBody>
                                        {fournisseurs.map((fournisseur, index) => {
                                            return demandeProforma[fournisseur.idFournisseur] === "" || demandeProforma[fournisseur.idFournisseur] === undefined || ((demandeProforma[fournisseur.idFournisseur] !== "" || demandeProforma[fournisseur.idFournisseur] !== undefined) && demandeProforma[fournisseur.idFournisseur].statusDemande === 0) ?
                                                (
                                                    <CTableRow key={index}>
                                                        <CTableDataCell className='text-medium-emphasis'>
                                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.nom}</p>
                                                        </CTableDataCell>
                                                        <CTableDataCell>
                                                            <p className='badge text-bg-info text-wrap'>{fournisseur.typeProduit.nom}</p>
                                                        </CTableDataCell>
                                                        <CTableDataCell>
                                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.nomResponsable}</p>
                                                        </CTableDataCell>
                                                        <CTableDataCell>
                                                            <p style={{ fontSize: '.9rem' }}><b>(+261) </b>{fournisseur.contact}</p>
                                                        </CTableDataCell>
                                                        <CTableDataCell>
                                                            <p style={{ fontSize: '.9rem' }}>{fournisseur.email}</p>
                                                        </CTableDataCell>
                                                        <CTableDataCell>
                                                            {demandeProforma[fournisseur.idFournisseur] === "" ? (
                                                                <CButton
                                                                    style={{ fontSize: '.8rem' }}
                                                                    onClick={
                                                                        (e) => {
                                                                            handleFaireDemande(e, fournisseur.idFournisseur)
                                                                        }
                                                                    }
                                                                >
                                                                    Faire une demande
                                                                </CButton>
                                                            ) : (
                                                                <CButton style={{ fontSize: '.8rem' }} onClick={(e) => {
                                                                    ajoutProformaRef.current.showModal(demandeProforma[fournisseur.idFournisseur].idDemandeProforma, selectedArticleBesoinAchat)
                                                                }} className='btn-success'>Valider demande</CButton>
                                                            )}
                                                        </CTableDataCell>
                                                    </CTableRow>
                                                ) : null
                                        })}
                                    </CTableBody>
                                </CTable>
                            </div>
                        </CCol>
                    </CRow>

                    <CRow className='mt-3'>
                        <CCol>
                            <h6>Proformas</h6>

                            <CTable>
                                <CTableHead>
                                    <CTableRow>
                                        <CTableHeaderCell scope='col'></CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Fournisseur</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Date Obtention</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Prix Unitaire</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'>Quantite</CTableHeaderCell>
                                        <CTableHeaderCell scope='col'></CTableHeaderCell>
                                    </CTableRow>
                                </CTableHead>

                                <CTableBody>
                                    {proformas.map((proforma, index) => {
                                        return (
                                            <CTableRow key={proforma.dateObtention}>
                                                <CTableDataCell>
                                                    { index === 0 ? <CIcon icon={cilCart}></CIcon> : <CIcon icon={cilXCircle}></CIcon>}
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p className='fw-bold' style={{ fontSize: '.8rem' }}>{proforma.fournisseur.nom}</p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p className='text-medium-emphasis fw-semibold' style={{ fontSize: '.8rem' }}>
                                                        {new Date(proforma.dateObtention).toLocaleDateString()}
                                                    </p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p style={{ fontSize: '.8rem' }}>{proforma.prixUnitaire} Ar</p>
                                                </CTableDataCell>
                                                <CTableDataCell>
                                                    <p style={{ fontSize: '.8rem' }}>{proforma.quantite}</p>
                                                </CTableDataCell>
                                                <CTableDataCell>

                                                    <CButton style={{ fontSize: '.8rem' }}>Mettre a jour</CButton>

                                                </CTableDataCell>
                                            </CTableRow>
                                        )
                                    })}
                                </CTableBody>
                            </CTable>
                        </CCol>
                    </CRow>
                </CModalBody>
                <CModalFooter>
                    
                </CModalFooter>

            </CModal>

            <AjoutProforma ref={ajoutProformaRef} />
        </>
    );
});

DemandeProformaBesoin.displayName = "DemandeProformaBesoin"
export default DemandeProformaBesoin