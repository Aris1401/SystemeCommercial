import { CButton, CCard, CCardBody, CCardHeader, CCardTitle, CCol, CFormSelect, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react';
import React, { useEffect, useRef, useState } from 'react'
import { makeRequest } from 'src/Api';
import { getArticles, getUnites } from '../../besoinAchat/components/SelectionArticle';

export const getEtatDeStock = (idArticle, idUnite) => {
    if (idArticle === "-1") idArticle = undefined;
    if (idUnite === "-1") idUnite = undefined;

    console.log("Article: " + idArticle + " | Unite: " + idUnite);

    var url = `/etatdestock`;
    if (idArticle && !idUnite) url = `etatdestock/article/${idArticle}`;
    else if (!idArticle && idUnite) url = `etatdestock/unite/${idUnite}`
    else if (idUnite && idArticle) url = `etatdestock/article/${idArticle}/unite/${idUnite}`;

    return new Promise((resolve, reject) => {
        makeRequest({
            url: url,
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

const EtatDeStock = () => {
    const [ update, setUpdate ] = useState(false);

    // Filtres
    const uniteRef = useRef()
    const articleRef = useRef()

    // Etat de stock
    const [ etatDeStock, setEtatDeStock ] = useState();
    useEffect(() => {
        getEtatDeStock(articleRef.current.value, uniteRef.current.value).then((data) => {
            setEtatDeStock(data.data[0])
        })
    }, [update])

    
    // Articles
    const [ articleSelection, setArticleSelection ] = useState([])
    useEffect(() => {
        getArticles().then((data) => {
            data.map((article) => {
                setArticleSelection(prev => [...prev, {label: article.nom, value: article.idArticle}])
            })
        })
    }, [update])

    // Unites
    const [ uniteSelection, setUniteSelection ] = useState([])
    useEffect(() => {
        getUnites().then((data) => {
            data.map((unite) => {
                setUniteSelection(prev => [...prev, {label: unite.nom, value: unite.idUnite}])
            })
        })
    }, [])

  return (
    <CCard>
        <CCardHeader>
            <CCardTitle>
                Etat de stock
            </CCardTitle>
        </CCardHeader>

        <CCardBody>
            <CRow>
                <CCol className='d-flex justify-content-between align-content-center'>
                    <div className='d-flex align-content-center'>
                        <h6>Filtres</h6>
                    </div>

                    <div className='d-flex gap-2'>
                        <CFormSelect ref={articleRef} style={{ width: '200px' }} floatingLabel="Article" options={
                            [{label: 'All', value: -1}, ...articleSelection]
                            }/>
                        <CFormSelect ref={uniteRef} style={{ width: '200px' }} floatingLabel="Unite" options={
                            [{label: 'All', value: -1}, ...uniteSelection]
                            }/>
                        <CButton
                            onClick={(e) => {
                                setUpdate(!update)
                            }}
                        >Filtrer</CButton>
                    </div>
                </CCol>
            </CRow>

            <CRow className='mt-3'>
                <CCol>
                    <CTable>
                        <CTableHead>
                            <CTableRow>
                                <CTableHeaderCell scope='col'>Date stock</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Article</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Quantite</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Unite</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Montant</CTableHeaderCell>
                                <CTableHeaderCell scope='col'>Prix Unitaire Moyen</CTableHeaderCell>
                            </CTableRow>
                        </CTableHead>

                        <CTableBody>
                            { etatDeStock && etatDeStock.ligneStocks.map((ligneStock, index) => {
                                return (
                                    <CTableRow key={index}>
                                        <CTableDataCell>{ ligneStock && new Date(ligneStock.dateCourante).toLocaleString() }</CTableDataCell>
                                        <CTableDataCell>
                                            <p className='badge text-bg-secondary text-wrap'>
                                                { ligneStock && ligneStock.article.nom }
                                            </p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            { ligneStock && ligneStock.quantite }
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            { ligneStock && ligneStock.unite.nom }
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p className='text-medium-emphasis'>
                                                { ligneStock && ligneStock.montant } Ar
                                            </p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p className='text-medium-emphasis'>
                                                { ligneStock && ligneStock.prixUnitaireMoyen } Ar
                                            </p>
                                        </CTableDataCell>
                                    </CTableRow>
                                )
                            }) }
                        </CTableBody>
                    </CTable>
                </CCol>
            </CRow>
        </CCardBody>
    </CCard>
  )
}

export default EtatDeStock