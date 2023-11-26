import { CButton, CCard, CCardBody, CCardHeader, CCardTitle, CCol, CForm, CFormInput, CFormSelect, CInputGroup, CRow } from '@coreui/react'
import React, { useEffect, useRef, useState } from 'react'
import { getArticles, getUnites } from '../../besoinAchat/components/SelectionArticle'
import { makeRequest } from 'src/Api';
import DisplayError from '../../DisplayError/DisplayError';
import { useNavigate } from 'react-router-dom';

export var ajouterStock = (data) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/etatdestock/entree',
            requestType: 'POST',
            values: data,
            successCallback: (response) => {
                resolve(response)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    });
}

const AjoutStock = () => {
    const errorMessageRef = useRef()

    // Articles
    const [ articleSelection, setArticleSelection ] = useState([])
    useEffect(() => {
        getArticles().then((data) => {
            data.map((article, index) => {
                setArticleSelection(prev => [...prev, {label: article.nom, value: article.idArticle}])
            })
        })
    }, [])

    // Unites
    const [ uniteSelection, setUniteSelection ] = useState([])
    useEffect(() => {
        getUnites().then((data) => {
            data.map((unite, index) => {
                setUniteSelection(prev => [...prev, {label: unite.nom, value: unite.idUnite}])
            })
        })
    }, [])

    // Refs
    const articleRef = useRef()
    const quantiteRef = useRef()
    const uniteRef = useRef()
    const prixUnitaireRef = useRef()
    const dateEntreeRef = useRef()

    const navigate = useNavigate()

    const handleAjoutSubmit = (e) => {
        e.preventDefault();

        let entree = {
            entree: quantiteRef.current.value,
            dateMouvement: dateEntreeRef.current.value,
            idArticle: articleRef.current.value,
            idUnite: uniteRef.current.value,
            prixUnitaire: prixUnitaireRef.current.value
        }

        ajouterStock(entree).then((data) => {
            if (data.errorMessage) {
                errorMessageRef.current.showError(data.errorMessage);
            } else {
                return navigate("/etatstock")
            }
        })
    }

  return (
    <CCard>
        <DisplayError ref={errorMessageRef} />

        <CCardHeader>
            <CCardTitle>
                Ajout stock
            </CCardTitle>
        </CCardHeader>

        <CCardBody>
            <CForm className='d-flex flex-column gap-3' onSubmit={handleAjoutSubmit}>
                <CRow>
                    <CCol>
                        <CFormSelect floatingLabel="Article" ref={articleRef} options={articleSelection} />
                    </CCol>
                </CRow>

                <CRow>
                    <CCol>
                        <CInputGroup>
                            <CFormInput type='number' step={0.01} ref={quantiteRef} floatingLabel="Quantite" />
                            <CFormSelect floatingLabel='Unite' ref={uniteRef} options={uniteSelection} />
                        </CInputGroup>
                    </CCol>
                </CRow>

                <CRow>
                    <CCol>
                        <CFormInput type='number' step={0.01} ref={prixUnitaireRef} floatingLabel="Prix Unitaire" />
                    </CCol>
                </CRow>

                <CRow>
                    <CCol>
                        <CFormInput type='datetime-local' ref={dateEntreeRef} floatingLabel="Date entree" />
                    </CCol>
                </CRow>

                <CRow>
                    <CCol>
                        <CButton color='primary' type='sumbit' size='lg'>Ajouter</CButton>
                    </CCol>
                </CRow>
            </CForm>
        </CCardBody>
    </CCard>
  )
}

export default AjoutStock