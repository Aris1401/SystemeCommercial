import { CButton, CCard, CCardBody, CCardText, CCardTitle, CCol, CForm, CFormInput, CFormLabel, CFormSelect, CFormTextarea, CInputGroup, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle, CNav, CNavItem, CNavLink, CRow } from '@coreui/react';
import React, { createRef, useEffect, useRef, useState } from 'react'
import ListeBesoinsAchat from './views/ListeBesoinsAchat';
import { makeRequest } from 'src/Api';
import SelectionArticle from './components/SelectionArticle';
import CIcon from '@coreui/icons-react';
import { cilPlus } from '@coreui/icons';
import { NavLink } from 'react-router-dom';
import DemandeAjoutArticle from '../demandeAjoutArticle/DemandeAjoutArticle';

const getValidBesoinsAchat = () => {
    return [];
}

export const getAllServices = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'services',
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data);
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    });
}

const BesoinAchat = () => {
    // Visibilite de modal d'ajout de besoin
    const [visibilityAjoutModal, setVisibilityAjoutModal] = useState(false);

    // Liste des services disponibles
    const [serviceOptions, setServiceOptions] = useState([]);
    useEffect(() => {
        // Resetting values
        setServiceOptions([]);

        getAllServices().then((data) => {
            data.forEach((service) => {
                // Creation d'objet de select
                let option = {
                    label: service.nom,
                    value: service.idService
                }

                setServiceOptions(prev => ([...prev, option]));
            })
        })
    }, [])

    // Toutes les selections article
    const [selectionsArticles, setSelectionsArticles] = useState([])
    useEffect(() => {
        setSelectionsArticles([])
    }, [])

    const handleAddingSelectionArticle = (e) => {
        let currentIndex = selectionsArticles.length;
        let selectionArticle = {
            index: currentIndex,
            ref: createRef()
        }

        setSelectionsArticles(prev => ([...prev, selectionArticle]))
    }

    const handleRemovingSelectionArticle = (e, index) => {
        const newArray = selectionsArticles.filter((_, i) => i !== index);
        setSelectionsArticles(newArray);
    }

    // Submitting form
    const serviceRef = useRef()
    const dateClotureRef = useRef()
    const descriptionRef = useRef()

    const handleBesoinAchatFormSubmit = (e) => {
        e.preventDefault();

        // Getting the values from the articles
        selectionsArticles.forEach((selectionArticle) => {
            console.log(selectionArticle.ref.current.getSelectedValues())
        })

        // Creating the request body
        let besoin = {
            idService: serviceRef.current.value,
            dateCloture: dateClotureRef.current.value,
            description: descriptionRef.current.value
        }

        makeRequest({
            url: 'besoinsachat',
            values: besoin,
            requestType: 'POST',
            successCallback: (data) => {
                Promise.all(selectionsArticles.forEach((article) => {
                    let selectionArticle = article.ref.current.getSelectedValues()

                    let articleBesoinAchat = {
                        idArticle: selectionArticle.article,
                        quantite: selectionArticle.quantite,
                        idUnite: selectionArticle.unite,
                        idBesoinAchat: data.idBesoinAchat,
                        descriptionArticleBesoin: selectionArticle.description
                    }
    
                    return makeRequest({
                        url: 'articlesbesoinachat',
                        values: articleBesoinAchat,
                        requestType: 'POST',
                    })
                })).then(() => {
                    setVisibilityAjoutModal(false)
                })
            },
            failureCallback: (error) => {
                alert(error)
                setVisibilityAjoutModal(false)
            }
        })
    }
    
    // Ajout article modal
    const demandeAjoutArticleRef = useRef()

    return (
        <>
            {/* Modal d'ajout de besoin */}
            <CModal
                size='lg'
                visible={visibilityAjoutModal}
                onClose={() => {
                    setVisibilityAjoutModal(false);
                }}
            >
                <CForm
                    onSubmit={handleBesoinAchatFormSubmit}
                >
                    <CModalHeader>
                        <CModalTitle>
                            Ajout de besoin achat
                        </CModalTitle>
                    </CModalHeader>

                    <CModalBody className='d-flex flex-column gap-2'>

                        <CRow>
                            <CCol>
                                <CFormSelect aria-label='Services' ref={serviceRef} floatingLabel='Services' options={['Services dipsonibles', ...serviceOptions]} />
                            </CCol>
                        </CRow>

                        <CRow>
                            <CCol>
                                <CFormInput ref={dateClotureRef} floatingLabel='Date de cloture' type='datetime-local' />
                            </CCol>
                        </CRow>

                        <CRow className='mt-2'>
                            <CCol className='d-flex flex-column gap-2'>
                                <h6>Articles</h6>

                                {selectionsArticles.map((selectionArticle, index) => {
                                    return <div key={index}>
                                        <SelectionArticle ref={selectionArticle.ref} index={selectionArticle.index} onRemove={handleRemovingSelectionArticle} />
                                    </div>;
                                })}

                                <CButton onClick={(e) => {
                                    handleAddingSelectionArticle(e);
                                }}><CIcon icon={cilPlus}></CIcon>Ajouter article</CButton>
                                <CButton
                                    onClick={(e) => {
                                        demandeAjoutArticleRef.current.showModal()
                                    }}
                                >Aucun article correspondant?</CButton>
                            </CCol>
                        </CRow>

                        <CRow className='mt-3'>
                            <CFormTextarea ref={descriptionRef} floatingLabel='description' rows={20} />
                        </CRow>
                    </CModalBody>

                    <CModalFooter>
                        <CButton type='submit'>Valider</CButton>
                    </CModalFooter>
                </CForm>
            </CModal>

            {/* Liste et bouton ajout de besoins achat */}
            <CCard className='p-2'>
                <CCardBody>
                    <CCardTitle>
                        <h3>
                            Besoins en cours
                        </h3>
                    </CCardTitle>

                    <CCardText>
                        <CButton onClick={(e) => {
                            setVisibilityAjoutModal(true)
                        }}>Faire une demande</CButton>
                    </CCardText>

                    <ListeBesoinsAchat visibilityAjoutModal={visibilityAjoutModal} />
                </CCardBody>
            </CCard>

            <DemandeAjoutArticle ref={demandeAjoutArticleRef} />
        </>
    );
}

export default BesoinAchat