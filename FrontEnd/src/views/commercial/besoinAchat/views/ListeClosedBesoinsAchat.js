import { CButton, CContainer, CModal, CModalBody, CModalHeader, CModalTitle, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/Api'
import ApercuBesoinAchat from '../components/ApercuBesoinAchat'

export const getClosedBesoinsAchat = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/besoinsachat/closed',
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

const ListeClosedBesoinsAchat  = () => {
    // Obtenir tout les besoins encore ouverts
    const [ openBesoinsAchat, setOpenBesoinsAchat ] = useState([])
    useEffect(() => {
        getClosedBesoinsAchat().then((data) => {
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
                if (besoinsEnNature[articleBesoinAchat.article.nom]) {
                    setBesoinsEnNature(prev => prev[articleBesoinAchat.article.nom].qte += articleBesoinAchat.quantite)
                } else {
                    besoinsEnNature[articleBesoinAchat.article.nom] = {
                        qte: articleBesoinAchat.quantite,
                        unite: articleBesoinAchat.unite.nom,
                    }
                }
                setBesoinsEnNatureKeys(prev => [...prev, articleBesoinAchat.article.nom])
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

        

        <CModal
            visible={false}
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
                            return (
                                <CTableRow key={key} >
                                    <CTableDataCell>
                                        <p className='badge text-bg-primary text-wrap'>{ key }</p>
                                    </CTableDataCell>

                                    <CTableDataCell>
                                        <p className='text-medium-emphasis'>{ `${besoinsEnNature[key] && besoinsEnNature[key].qte} ${besoinsEnNature[key] && besoinsEnNature[key].unite}` }</p>
                                    </CTableDataCell>
                                </CTableRow>
                            )
                        }) }
                    </CTableBody>
                </CTable>
            </CModalBody>
        </CModal>
    </CContainer>
  )
}

export default ListeClosedBesoinsAchat