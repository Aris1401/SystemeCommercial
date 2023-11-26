import { CCard, CCardBody, CCardHeader, CCardTitle, CCol, CFormSelect, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react';
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/Api';

export const getEtatDeStock = (idArticle, idUnite) => {
    var url = `/etatdestock`;
    if (idArticle && !idUnite) url = `etatdestock/article/${idArticle}`;
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

    // Etat de stock
    const [ etatDeStock, setEtatDeStock ] = useState();
    useEffect(() => {
        getEtatDeStock().then((data) => {
            setEtatDeStock(data.data[0])
        })
    }, [update])

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
                        <CFormSelect style={{ width: '200px' }} floatingLabel="Article" options={[]}/>
                        <CFormSelect style={{ width: '200px' }} floatingLabel="Unite" options={[]}/>
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
                                        <CTableDataCell>{ new Date(ligneStock.dateCourante).toLocaleString() }</CTableDataCell>
                                        <CTableDataCell>
                                            <p className='badge text-bg-secondary text-wrap'>
                                                { ligneStock.article.nom }
                                            </p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            { ligneStock.quantite }
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            { ligneStock.unite.nom }
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p className='text-medium-emphasis'>
                                                { ligneStock.montant } Ar
                                            </p>
                                        </CTableDataCell>
                                        <CTableDataCell>
                                            <p className='text-medium-emphasis'>
                                                { ligneStock.prixUnitaireMoyen } Ar
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