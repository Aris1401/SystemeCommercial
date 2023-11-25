import { CButton, CContainer, CModal, CModalBody, CModalHeader, CModalTitle, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/Api'
import ApercuBesoinAchat from '../components/ApercuBesoinAchat'

export const getOpenBesoinsAchat = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/besoinsachat/open',
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => [
                reject(error)
            ]
        })
    })
}

const ListeOpenBesoinsAchat = () => {
    // Obtenir tout les besoins encore ouverts
    const [ openBesoinsAchat, setOpenBesoinsAchat ] = useState([])
    useEffect(() => {
        getOpenBesoinsAchat().then((data) => {
            setOpenBesoinsAchat(data)
        })
    }, [])
    
    // TODO: Temporaire a remplacer

    // Besoins en natures
    const [ visibilityNatureModal, setVisibilityNatureModal ] = useState(false)
    const [ besoinsEnNature, setBesoinsEnNature ] = useState({})
    const [ besoinsEnNatureKeys, setBesoinsEnNatureKeys ] = useState([])
    useEffect(() => {
        openBesoinsAchat.forEach((besoinAchat) => {
            besoinAchat.articlesBesoinAchat.forEach((articleBesoinAchat) => {
                if (besoinsEnNature[articleBesoinAchat.article.idArticle]) {
                    setBesoinsEnNature(prev => {
                        if (prev) {
                            prev[articleBesoinAchat.article.idArticle].qte += articleBesoinAchat.quantite
                        }
                    })
                } else {
                        setBesoinsEnNature(prev => ({...prev, [articleBesoinAchat.article.idArticle] : {
                            nom: articleBesoinAchat.article.nom,
                            qte: articleBesoinAchat.quantite,
                            unite: articleBesoinAchat.unite.nom,
                        }}));
                }
                setBesoinsEnNatureKeys(prev => [...prev, articleBesoinAchat.article.idArticle])
            })
        })
    }, [openBesoinsAchat])

    useEffect(() => {
        console.log(besoinsEnNature)
    }, [besoinsEnNature])

  return (
    <CContainer className='mt-3'>
        <CTable>
            <CTableHead>
                <CTableRow>
                    <CTableHeaderCell scope='col'>Service</CTableHeaderCell>
                    <CTableHeaderCell scope='col'>Articles</CTableHeaderCell>
                    <CTableHeaderCell scope='col'></CTableHeaderCell>
                </CTableRow>
            </CTableHead>

            <CTableBody>
                { openBesoinsAchat.map((besoinAchat, index) => {
                    return <ApercuBesoinAchat key={index} besoinAchat={besoinAchat} />
                }) }
            </CTableBody>
        </CTable>

        <CButton
            onClick={(e) => {
                setVisibilityNatureModal(true)
            }}
        >Afficher besoins en nature</CButton>

        <CModal
            visible={visibilityNatureModal}
            onClose={() => {
                setVisibilityNatureModal(false)
            }}
        >
            <CModalHeader>
                <CModalTitle>
                    <h6>Besoins en nature</h6>
                </CModalTitle>
            </CModalHeader>

            <CModalBody>
                <CTable>
                    <CTableHead>
                        <CTableRow>
                            <CTableHeaderCell scope='col'>Article</CTableHeaderCell>
                            <CTableHeaderCell scope='col'>Quantite</CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>

                    <CTableBody>
                        { besoinsEnNatureKeys.map((key, index) => {
                            return besoinsEnNature && besoinsEnNature[key] ? (
                                <CTableRow key={key} >
                                    <CTableDataCell>
                                        <p className='badge text-bg-primary text-wrap'>{ `${besoinsEnNature[key] && besoinsEnNature[key].nom}` }</p>
                                    </CTableDataCell>

                                    <CTableDataCell>
                                        <p className='text-medium-emphasis'>{ `${besoinsEnNature[key] && besoinsEnNature[key].qte} ${besoinsEnNature[key] && besoinsEnNature[key].unite}` }</p>
                                    </CTableDataCell>
                                </CTableRow>
                            ) : null
                        }) }
                    </CTableBody>
                </CTable>
            </CModalBody>
        </CModal>
    </CContainer>
  )
}

export default ListeOpenBesoinsAchat