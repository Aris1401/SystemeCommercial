import { CContainer, CTable, CTableBody, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
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
    </CContainer>
  )
}

export default ListeOpenBesoinsAchat